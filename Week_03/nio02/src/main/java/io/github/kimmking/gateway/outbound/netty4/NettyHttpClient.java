package io.github.kimmking.gateway.outbound.netty4;

import io.github.kimmking.gateway.outbound.netty4.NettyHttpClientOutboundHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.epoll.EpollChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpRequestEncoder;
import io.netty.handler.codec.http.HttpResponseDecoder;

import java.net.URI;

public class NettyHttpClient {

    private final NettyHttpClientOutboundHandler clientHandler = new NettyHttpClientOutboundHandler();

    public String handle(FullHttpRequest fullHttpRequest) throws Exception {
        EventLoopGroup workerGroup = new NioEventLoopGroup(1);

        try {
            Bootstrap b = new Bootstrap();
            b.group(workerGroup);
            b.channel(NioSocketChannel.class);
            b.option(ChannelOption.SO_KEEPALIVE, true);
            b.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                public void initChannel(SocketChannel ch) {
                    // 客户端接收到的是httpResponse响应，所以要使用HttpResponseDecoder进行解码
                    ch.pipeline().addLast(new HttpResponseDecoder());
                    // 客户端发送的是httpRequest，所以要使用HttpRequestEncoder进行编码
                    ch.pipeline().addLast(new HttpRequestEncoder());
                    ch.pipeline().addLast(clientHandler);
                }
            });

            URI uri = new URI(fullHttpRequest.uri());
            // Start the client.
            ChannelFuture f = b.connect(uri.getHost(), uri.getPort()).sync();
            f.channel().writeAndFlush(fullHttpRequest);
            f.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
        }

        return clientHandler.getData();
    }

}