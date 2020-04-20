package com.liaojl.netty.http.xml.client;

import com.liaojl.netty.http.xml.handle.HttpXmlRequest;
import com.liaojl.netty.http.xml.handle.HttpXmlResponse;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpXmlClientHandle extends SimpleChannelInboundHandler<HttpXmlResponse> {
    private static final Logger log = LoggerFactory.getLogger(HttpXmlClientHandle.class);

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        HttpXmlRequest request = new HttpXmlRequest(null, OrderFactory.create(123));
        ctx.writeAndFlush(request);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        log.error(cause.getMessage(), cause);
        ctx.close();
    }

    protected void messageReceived(ChannelHandlerContext ctx, HttpXmlResponse msg) throws Exception {
        log.info("The client receive response of http header is : " + msg.getHttpResponse().headers().names());
        log.info("The client receive response of http body is : " + msg.getResult());
    }

    /**
     * Is called for each message of type {@link I}.
     *
     * @param ctx the {@link ChannelHandlerContext} which this {@link SimpleChannelInboundHandler}
     *            belongs to
     * @param msg the message to handle
     * @throws Exception is thrown if an error occurred
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpXmlResponse msg) throws Exception {
        messageReceived(ctx, msg);
    }
}