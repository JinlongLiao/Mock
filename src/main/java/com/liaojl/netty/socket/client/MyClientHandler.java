package com.liaojl.netty.socket.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;


public class MyClientHandler extends SimpleChannelInboundHandler<String> {
    int size = 0;
    private static final Logger log = LoggerFactory.getLogger(MyClientHandler.class);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        //服务端的远程地址
        log.info(String.valueOf(ctx.channel().remoteAddress()));
        log.info("client output: " + msg);
        if (size++ < 10) {
            ctx.writeAndFlush("from client: " + LocalDateTime.now());
        }
    }

    /**
     * 当服务器端与客户端进行建立连接的时候会触发，如果没有触发读写操作，则客户端和客户端之间不会进行数据通信，也就是channelRead0不会执行，
     * 当通道连接的时候，触发channelActive方法向服务端发送数据触发服务器端的handler的channelRead0回调，然后
     * 服务端向客户端发送数据触发客户端的channelRead0，依次触发。
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush("来自与客户端的问题!");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.error(cause.getMessage(), cause);
        ctx.close();
    }
}
