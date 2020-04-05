package com.liaojl.redis.lettuce;

import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        RedisClient redisClient = RedisClient.create("redis://localhost/0");
        StatefulRedisConnection<String, String> connection = redisClient.connect();

        log.info("Connected to Redis");
        connection.sync().set("key", "Hello World");

        connection.close();
        redisClient.shutdown();
    }
}
