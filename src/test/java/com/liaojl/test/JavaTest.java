package com.liaojl.test;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
}
