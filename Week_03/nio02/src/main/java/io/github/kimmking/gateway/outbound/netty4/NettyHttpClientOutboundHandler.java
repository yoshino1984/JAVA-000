package io.github.kimmking.gateway.outbound.netty4;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.HttpContent;
import io.netty.util.CharsetUtil;

public class NettyHttpClientOutboundHandler  extends ChannelInboundHandlerAdapter {
    private String data;

    public String getData() {
        return data;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
//        System.out.println("client " + ctx.channel().remoteAddress() + "connected");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        if (msg instanceof HttpContent) {
            HttpContent content = (HttpContent) msg;
            ByteBuf buf = content.content();
            data = buf.toString(CharsetUtil.UTF_8);
            buf.release();
        }
//        System.out.println(data);
    }
}