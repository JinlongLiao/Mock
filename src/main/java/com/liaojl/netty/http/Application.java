package com.liaojl.netty.http;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Application {
    private static final Logger log = LoggerFactory.getLogger(Application.class);
    private static final String DEFAULT_URL = "/";

    public static void main(String[] args) {
        HttpServer httpServer = new HttpServer(8080,DEFAULT_URL);
        try {
            httpServer.start();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

    }
}