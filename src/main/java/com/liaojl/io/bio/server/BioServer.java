package com.liaojl.io.bio.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author LiaoJL
 * @description TODO
 * @file BioServer.java
 * @CopyRight (C) http://www.koal.com/
 * @email jinlongliao@foxmail.com
 * @date 2020/3/30 15:13
 */
public class BioServer {
    private int port;
    private static final Logger log = LoggerFactory.getLogger(BioServer.class);


    public BioServer(int port) {
        this.port = port;
    }

    public void start() throws IOException {
        System.out.printf("Server Ready\n");
        ServerSocket serverSocket = new ServerSocket(port);
        Socket socket = null;
        while (true) {
            socket = serverSocket.accept();
            System.out.printf("start new Thread Run\n");
            new TimeServerHandler(socket).start();
        }
    }

    public static void main(String[] args) {
        BioServer bioServer = new BioServer(9999);
        try {
            bioServer.start();
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            ;
        }
    }
}
