package io.kimmking.rpcfx.client.netty;

import com.google.common.base.Charsets;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import io.kimmking.rpcfx.api.RpcfxResponse;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.*;
import lombok.extern.slf4j.Slf4j;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.channels.spi.SelectorProvider;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

@Slf4j
public class RpcfxNettyClient {

    private final static RpcfxNettyClient INSTANCE = new RpcfxNettyClient();

    public static RpcfxNettyClient getInstance(){
        return INSTANCE;
    }

    /**
     * 使用Map来保存用过的Channel，看下次相同的后台服务是否能够重用，起一个类似缓存的作用
     */
    private final ConcurrentHashMap<String, Channel> channelPool;
    private final EventLoopGroup clientGroup;

    private RpcfxNettyClient() {
        channelPool = new ConcurrentHashMap<>();
        clientGroup = new NioEventLoopGroup(0, new ThreadFactoryBuilder().setNameFormat("rpc-pool-%d").build(),
            SelectorProvider.provider());
    }

    /**
     * 调用channel发送请求，从handler中获取响应结果
     * @return 响应
     * @throws InterruptedException exception
     */
    public RpcfxResponse getResponse(String rpcRequest, String url) throws InterruptedException,
        URISyntaxException, UnsupportedEncodingException {

        FullHttpRequest fullHttpRequest = createFullHttpRequest(rpcRequest, new URI(url));

        URI uri = new URI(url);
        String cacheKey = uri.getHost() + ":" + uri.getPort();

        // 查看缓存池中是否有可重用的channel
        if (channelPool.containsKey(cacheKey)) {
            Channel channel = channelPool.get(cacheKey);
            if (!channel.isActive() || !channel.isWritable() || !channel.isOpen()) {
                log.debug("Channel can't reuse");
            } else {
                try {
                    RpcfxNettyInboundHandler handler = new RpcfxNettyInboundHandler();
                    channel.pipeline().replace("clientHandler", "clientHandler", handler);
                    channel.writeAndFlush(fullHttpRequest).sync();
                    return handler.getResponse(3, TimeUnit.SECONDS);
                } catch (Exception e) {
                    log.debug("channel reuse send msg failed!");
                    channel.close();
                    channelPool.remove(cacheKey);
                }
                log.debug("Handler is busy, please user new channel");
            }
        }

        // 没有或者不可用则新建
        // 并将最终的handler添加到pipeline中，拿到结果后返回
        RpcfxNettyInboundHandler handler = new RpcfxNettyInboundHandler();

        Channel channel = createChannel(uri.getHost(), uri.getPort());
        channel.pipeline().replace("clientHandler", "clientHandler", handler);
        channel.writeAndFlush(fullHttpRequest).sync();
        channelPool.put(cacheKey, channel);
        return handler.getResponse(3, TimeUnit.SECONDS);
    }

    private FullHttpRequest createFullHttpRequest(String content, URI uri) throws UnsupportedEncodingException {
        FullHttpRequest request = new DefaultFullHttpRequest(HttpVersion.HTTP_1_1, HttpMethod.POST,
            uri.toASCIIString(), Unpooled.wrappedBuffer(content.getBytes(Charsets.UTF_8)));

        request.headers().set(HttpHeaderNames.HOST, uri.getHost());
        request.headers().set(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE);
        request.headers().set(HttpHeaderNames.CONTENT_LENGTH, request.content().readableBytes());
        request.headers().set(HttpHeaderNames.CONTENT_TYPE, "application/json");

        return request;
    }

    /**
     * 返回新的Channel
     * @param address ip地址
     * @param port 端口
     * @return channel
     * @throws InterruptedException exception
     */
    private Channel createChannel(String address, int port) throws InterruptedException {
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(clientGroup)
            .option(ChannelOption.SO_REUSEADDR, true)
            .option(ChannelOption.TCP_NODELAY, true)
            .option(ChannelOption.AUTO_CLOSE, true)
            .option(ChannelOption.SO_KEEPALIVE, true)
            .channel(NioSocketChannel.class)
            .handler((new ChannelInitializer<SocketChannel>() {
                @Override
                public void initChannel(SocketChannel ch) {
                    // 客户端接收到的是httpResponse响应，所以要使用HttpResponseDecoder进行解码
                    ch.pipeline().addLast(new HttpResponseDecoder());
                    // 客户端发送的是httpRequest，所以要使用HttpRequestEncoder进行编码
                    ch.pipeline().addLast(new HttpRequestEncoder());
                    ch.pipeline().addLast("clientHandler", new RpcfxNettyInboundHandler());
                }
            }));
        return bootstrap.connect(address, port).sync().channel();
    }

    /**
     * 关闭线程池
     */
    public void destroy() {
        clientGroup.shutdownGracefully();
    }
}
