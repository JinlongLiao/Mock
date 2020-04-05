package com.liaojl.io.nnio.server;

import com.liaojl.io.bio.server.TimeServerHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TimeServer {
    private static final Logger log = LoggerFactory.getLogger(TimeServer.class);

    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        int port = 9999;
        if (args != null && args.length > 0) {
            try {
                port = Integer.valueOf(args[0]);
            } catch (NumberFormatException e) {
                // 采用默认值
            }
        }
        ServerSocket server = null;
        try {
            server = new ServerSocket(port);
            log.info("The time server is start in port : " + port);
            Socket socket = null;
            TimeServerHandlerExecutor singleExecutor = new TimeServerHandlerExecutor(
                    50, 10000);
            while (true) {
                socket = server.accept();
                log.info("收到来自客户端的请求");
                singleExecutor.execute(new TimeServerHandler(socket));
            }
        } finally {
            if (server != null) {
                log.info("The time server close");
                server.close();
            }
        }
    }
}

