package com.liaojl.test.rpc.rmi.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.server.RMISocketFactory;


public class CustomerSocketFactory extends RMISocketFactory {
    private static final Logger log = LoggerFactory.getLogger(CustomerSocketFactory.class);
    public static final int DEFAULT_PORT = 8855;

    @Override
    public Socket createSocket(String host, int port) throws IOException {
        return new Socket(host, port);
    }

    @Override
    public ServerSocket createServerSocket(int port) throws IOException {
        if (port == 0) {
            port = DEFAULT_PORT;
        }
        log.info("RMI 通信端口 : " + port);
        return new ServerSocket(port);
    }
}