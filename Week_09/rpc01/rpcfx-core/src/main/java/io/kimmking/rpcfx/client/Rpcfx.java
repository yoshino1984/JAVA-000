package io.kimmking.rpcfx.client;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.ParserConfig;
import io.kimmking.rpcfx.api.RpcfxRequest;
import io.kimmking.rpcfx.api.RpcfxResponse;
import io.kimmking.rpcfx.client.netty.RpcfxNettyClient;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.InvocationHandlerAdapter;
import net.bytebuddy.matcher.ElementMatchers;
import okhttp3.MediaType;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public final class Rpcfx {

    static {
        ParserConfig parserConfig = ParserConfig.getGlobalInstance();
        parserConfig.addAccept("io.kimmking");
        parserConfig.setAutoTypeSupport(true);
    }

    public static <T> T create(final Class<T> serviceClass, final String url)
        throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {

        // 0. 替换动态代理 -> 字节码增强
        return serviceClass.cast(getByteBuddyProxy(serviceClass, url));
    }

    private static <T> Object getByteBuddyProxy(Class<T> serviceClass, String url)
        throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        return new ByteBuddy()
                .subclass(Object.class)
                .name(serviceClass.getCanonicalName() + "$ByteBuddyProxy")
                .implement(serviceClass)
                .method(ElementMatchers.any())
                .intercept(InvocationHandlerAdapter.of(new RpcfxInvocationHandler(serviceClass, url)))
                .make()
                .load(Rpcfx.class.getClassLoader())
                .getLoaded()
                .getDeclaredConstructor()
                .newInstance();
    }

    public static class RpcfxInvocationHandler implements InvocationHandler {

        public static final MediaType JSONTYPE = MediaType.get("application/json; charset=utf-8");

        private final Class<?> serviceClass;
        private final String url;
        public <T> RpcfxInvocationHandler(Class<T> serviceClass, String url) {
            this.serviceClass = serviceClass;
            this.url = url;
        }

        // 可以尝试，自己去写对象序列化，二进制还是文本的，，，rpcfx是xml自定义序列化、反序列化，json: code.google.com/p/rpcfx
        // int byte char float double long bool
        // [], data class

        @Override
        public Object invoke(Object proxy, Method method, Object[] params) throws Throwable {
            RpcfxRequest request = new RpcfxRequest();
            request.setServiceClass(this.serviceClass.getName());
            request.setMethod(method.getName());
            request.setParams(params);

            RpcfxResponse response = post(request, url);
            // 这里判断response.status，处理异常
            // 考虑封装一个全局的RpcfxException
            if (response.isStatus()) {
                return response.getResult();
            } else {
                throw response.getException();
            }
        }

        private RpcfxResponse post(RpcfxRequest req, String url) throws Exception {
            String reqJson = JSON.toJSONString(req);
            System.out.println("req json: " + reqJson);

            // 1.可以复用client
            // 2.尝试使用httpclient或者netty client
            return RpcfxNettyClient.getInstance().getResponse(reqJson, url);
        }
    }
}
