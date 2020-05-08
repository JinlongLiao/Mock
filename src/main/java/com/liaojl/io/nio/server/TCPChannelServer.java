package com.liaojl.io.nio.server;


import com.liaojl.io.nio.client.NonBlockingTCPClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author LiaoJL
 * @description TODO
 * @file TCPChannelServer.java
 * @CopyRight (C) http://www.koal.com/
 * @email jinlongliao@foxmail.com
 * @date 2020/3/31 18:03
 */
public class TCPChannelServer {
    private static final Logger log = LoggerFactory.getLogger(TCPChannelServer.class);

    public static void main(String[] args) {
        Selector selector = null;
        try {
            // 1. open a selector
            selector = Selector.open();
            // 2. listen for server socket channel
            ServerSocketChannel socketChannel = ServerSocketChannel.open();
            // must to be nonblocking mode before register
            socketChannel.configureBlocking(false);
            // bind server socket channel to port 8899
            socketChannel.bind(new InetSocketAddress(NonBlockingTCPClient.port));
            // 3. register it with selector
            SelectionKey selectionKey = socketChannel.register(selector, SelectionKey.OP_ACCEPT);
//            selectionKey.attach(null);
            while (true) {
                // run forever
                // 4. select ready SelectionKey for I/O operation
                if (selector.select(3000) == 0) {
                    continue;
                }
                // 5. get selected keys
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                // 6. handle selected key's interest operations
                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();

                    if (key.isAcceptable()) {
                        ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
                        // get socket channel from server socket channel
                        SocketChannel clientChannel = serverSocketChannel.accept();
                        // must to be nonblocking before register with selector
                        clientChannel.configureBlocking(false);
                        // register socket channel to selector with OP_READ
                        clientChannel.register(key.selector(), SelectionKey.OP_READ);
                    }

                    if (key.isReadable()) {
                        // read bytes from socket channel to byte buffer
                        SocketChannel clientChannel = (SocketChannel) key.channel();
                        ByteBuffer readBuffer = ByteBuffer.allocate(10);
                        int readBytes = clientChannel.read(readBuffer);
                        if (readBytes == -1) {
                            log.info("closed.......");
                            clientChannel.close();
                        } else if (readBytes > 0) {
                            String s = new String(readBuffer.array());
                            log.info("Client said: " + s);
                            if (s.trim().equals("Hello")) {
                                // attachment is content used to write
                                key.interestOps(SelectionKey.OP_WRITE);
                                key.attach("Welcome!!!");
                            }
                        }
                    }

                    if (key.isValid() && key.isWritable()) {
                        SocketChannel clientChannel = (SocketChannel) key.channel();
                        // get content from attachment
                        String content = (String) key.attachment();
                        // write content to socket channel
                        clientChannel.write(ByteBuffer.wrap(content.getBytes()));
                        key.interestOps(SelectionKey.OP_READ);
                    }

                    // remove handled key from selected keys
                    iterator.remove();
                }
            }
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            ;
        } finally {
            // close selector
            if (selector != null) {
                try {
                    selector.close();
                } catch (IOException e) {
                    log.error(e.getMessage(), e);
                    ;
                }
            }
        }
    }
}
