package com.liaojl.io.nio.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @author LiaoJL
 * @description TODO
 * @file NonBlockingTCPClient.java
 * @CopyRight (C) http://www.koal.com/
 * @email jinlongliao@foxmail.com
 * @date 2020/3/31 18:02
 */
public class NonBlockingTCPClient {
    private static final Logger log = LoggerFactory.getLogger(NonBlockingTCPClient.class);
    public static final int port = 8899;

    public static void main(String[] args) {
        byte[] data = "hello".getBytes();
        SocketChannel channel = null;
        try {
            // 1. open a socket channel
            channel = SocketChannel.open();
            // adjust to be nonblocking
            channel.configureBlocking(false);
            // 2. init connection to server and repeatedly poll with complete
            // connect() and finishConnect() are nonblocking operation, both return immediately
            if (!channel.connect(new InetSocketAddress(InetAddress.getLocalHost(), port))) {
                while (!channel.finishConnect()) {
                    log.info(".");
                }
            }
            log.info("Connected to server...");

            ByteBuffer writeBuffer = ByteBuffer.wrap(data);
            ByteBuffer readBuffer = ByteBuffer.allocate(data.length);
            int totalBytesReceived = 0;
            int bytesReceived;
            // 3. read and write bytes
            while (totalBytesReceived < data.length) {
                if (writeBuffer.hasRemaining()) {
                    channel.write(writeBuffer);
                }
                if ((bytesReceived = channel.read(readBuffer)) == -1) {
                    throw new SocketException("Connection closed prematurely");
                }
                totalBytesReceived += bytesReceived;
                log.info(".");
            }
            log.info("Server said: " + new String(readBuffer.array()));
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        } finally {
            // 4 .close socket channel
            try {
                if (channel != null) {
                    channel.close();
                }
            } catch (IOException e) {
                log.error(e.getMessage(), e);
            }
        }
    }
}
