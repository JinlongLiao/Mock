package com.liaojl.test.speed;

import com.alibaba.fastjson.JSON;
import com.caucho.hessian.io.HessianOutput;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.SneakyThrows;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;

/**
 * @author LiaoJL
 * @description TODO
 * @file JavaTest.java
 * @email jinlongliao@foxmail.com
 * @date 2020/4/20 14:08
 */
public class JavaTest {
    private static final Logger log = LoggerFactory.getLogger(JavaTest.class);

    @Test
    public void intTest() {
        Integer a = new Integer(77);
        Integer b = new Integer(77);
        int c = 128;
        Integer d = 128;
        Integer e = 128;
        int f = 127;
        Integer g = 127;
        Integer h = 127;

        log.info("a==b {}", a == b);
        log.info("c==d {}", c == d);
        log.info("e==d {}", e == d);
        log.info("f==g {}", f == g);
        log.info("g==h {}", g == h);
    }

    @Test
    public void stringTest() {
        String a = "aa";
        String b = "aa";
        String c = "a" + "a";
        String d = new String("aa");
        String e = "a";
        String f = e + e + a;
        log.info("a==b {}", a == b);
        log.info("c==b {}", c == b);
        log.info("d==b {}", d == b);

    }

    @SneakyThrows
    @Test
    public void fastJsonVsHession() {
        Pojo pojo = new Pojo();
        String gsonStr = null;
        String fastStr = null;
        String jackStr = null;
        byte[] hessionByte = null;

        int size = 100 * 10000;

        long t = System.currentTimeMillis();
        ObjectMapper objectMapper = new ObjectMapper();
        for (int i = 0; i < size; ++i) {
            jackStr = objectMapper.writeValueAsString(pojo);
        }
        log.info("jackStr:{}", jackStr);
        log.info("jackStr Time:{}", System.currentTimeMillis() - t);

        long t0 = System.currentTimeMillis();
        Gson gson = new GsonBuilder().create();
        for (int i = 0; i < size; ++i) {
            gsonStr = gson.toJson(pojo);
        }
        log.info("gsonStr:{}", gsonStr);
        log.info("gsonStr Time:{}", System.currentTimeMillis() - t0);
        long t1 = System.currentTimeMillis();
        for (int i = 0; i < size; ++i) {
            fastStr = JSON.toJSONString(pojo);
        }
        log.info("fastStr:{}", fastStr);
        log.info("fastStr :{}", System.currentTimeMillis() - t1);

        long t2 = System.currentTimeMillis();
        ByteArrayOutputStream os = new ByteArrayOutputStream(1024);
        for (int i = 0; i < size; ++i) {
            HessianOutput ho = new HessianOutput(os);
            ho.writeObject(pojo);
            os.reset();
        }
        log.info("hessionByte:{}", hessionByte);
        log.info("hessionByte :{}", System.currentTimeMillis() - t2);
    }
}
