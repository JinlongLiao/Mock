package com.liaojl.netty.http;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.epoll.Epoll;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.epoll.EpollServerSocketChannel;
import io.netty.channel.kqueue.KQueue;
import io.netty.channel.kqueue.KQueueEventLoopGroup;
import io.netty.channel.kqueue.KQueueServerSocketChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;

/**
 * netty server
 * 2018/11/1.
 */
public class HttpServer {

    private int port;
    private String defaultUrl;
    private static final Logger log = LoggerFactory.getLogger(HttpServer.class);

    public HttpServer(int port, String defaultUrl) {
        this.defaultUrl = defaultUrl;
        this.port = port;
    }

    public void start() throws Exception {
        ServerBootstrap bootstrap = new ServerBootstrap();
        EventLoopGroup boss;
        EventLoopGroup work;
        if (Epoll.isAvailable()) {
            boss = new EpollEventLoopGroup();
            work = new EpollEventLoopGroup();
            bootstrap.option(ChannelOption.SO_BACKLOG, 1024).channel(EpollServerSocketChannel.class)
                    .childOption(ChannelOption.SO_LINGER, 0).childOption(ChannelOption.SO_REUSEADDR, true)
                    .childOption(ChannelOption.SO_KEEPALIVE, true).group(boss, work)
                    .handler(new LoggingHandler(LogLevel.DEBUG))
                    .childHandler(new HttpServerInitializer(defaultUrl));
            log.info(" epoll init");
        } else if (KQueue.isAvailable()) {
            boss = new KQueueEventLoopGroup();
            work = new KQueueEventLoopGroup();
            bootstrap.group(boss, work)
                    .channel(KQueueServerSocketChannel.class)
                    .childHandler(new ChannelInboundHandlerAdapter())
                    .childHandler(new HttpServerInitializer(defaultUrl))
                    .handler(new LoggingHandler(LogLevel.DEBUG));
            log.info(" epoll init");
        } else {
            boss = new NioEventLoopGroup();
            work = new NioEventLoopGroup();
            bootstrap.group(boss, work)
                    .handler(new LoggingHandler(LogLevel.DEBUG))
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new HttpServerInitializer(defaultUrl));
            log.info(" nio init");
        }

        ChannelFuture f = bootstrap.bind(new InetSocketAddress(port)).sync();
        log.info(" server start up on port : " + port);
        f.channel().closeFuture().sync();

    }

}