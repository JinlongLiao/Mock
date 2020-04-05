package com.liaojl.redis.jedis;

import redis.clients.jedis.Jedis;

import static com.liaojl.redis.jedis.Sub.love;

public class Push {
    public static void main(String[] args) {
        Jedis jedis = RedisUtil.getJedis();
        int size = 100;
        for (int i = 0; i < size; i++) {
            jedis.publish(love, "LOVE PanLu\t" + i + 1 + "\t年");
        }
    }
}
