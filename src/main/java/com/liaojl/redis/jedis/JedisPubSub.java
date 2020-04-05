package com.liaojl.redis.jedis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author LiaoJL
 * @description TODO
 * @file JedisPubSub.java
 * @CopyRight (C) http://www.koal.com/
 * @email jinlongliao@foxmail.com
 * @date 2020/3/30 23:18
 */
public class JedisPubSub extends redis.clients.jedis.JedisPubSub {
    private static final Logger log = LoggerFactory.getLogger(JedisPubSub.class);

    @Override
    public void onMessage(String channel, String message) {
        log.info("channel: {} message: {} ", channel, message);
    }
}
