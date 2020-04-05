package com.liaojl.redis.jedis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;

public class Sub {
    public static String love = "LOVE";
    private static final Logger log = LoggerFactory.getLogger(Sub.class);

    public static void main(String[] args) {
        Jedis jedis = RedisUtil.getJedis();
        JedisPubSub jedisPubSub = new JedisPubSub();
        jedis.subscribe(jedisPubSub, love);
    }
}
