package io.github.kimmking.gateway.filter;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;

/**
 * @author wangxin
 * 2020/11/4 00:13
 * @since
 **/
public class HttpRequestHeadFilter implements HttpRequestFilter {
    @Override
    public void filter(FullHttpRequest fullRequest, ChannelHandlerContext ctx) {
        String key = "nio";
        String val = "wangxin";
        fullRequest.headers().add(key, val);
    }
}
