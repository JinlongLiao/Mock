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
import java.io.IOException;

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

    @Test
    public void fastJsonVsHession() throws IOException {
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

    private static final int SPLIT_SIZE = 2;

    @Test
    public void suString() {
        String charSequence = "KMP算法是一种改进的字符串匹配算法，由D.E.Knuth，J.H.Morris和V.R.Pratt提出的，因此人们称它为克努特—莫里斯—普拉特操作（简称KMP算法）。KMP算法的核心是利用匹配失败后的信息，尽量减少模式串与主串的匹配次数以达到快速匹配的目的。具体实现就是通过一个next()函数实现，函数本身包含了模式串的局部匹配信息。";
        charSequence = charSequence + charSequence + charSequence + charSequence;
        charSequence = charSequence + charSequence + charSequence + charSequence;
        charSequence = charSequence + charSequence + charSequence + charSequence;
        int length = charSequence.length();
        //两个分割
        long t1 = System.currentTimeMillis();

        for (int i = 0; i < length; i += SPLIT_SIZE) {
            String substring = charSequence.substring(i, (i + SPLIT_SIZE) > length ? length : (i + SPLIT_SIZE));
//            log.info("substring:{}", substring);
        }
        long time1 = System.currentTimeMillis() - t1;
        log.info("substring:{}", time1);

        long t2 = System.currentTimeMillis();
        char[] toCharArray = charSequence.toCharArray();
        int size = 0;
        while (size + SPLIT_SIZE < length) {
            String str = new String(toCharArray, size, SPLIT_SIZE);
            size += SPLIT_SIZE;
//            log.info("toCharArray:{}", str);
        }
        long time2 = System.currentTimeMillis() - t2;
        log.info("toCharArray:{}", time2);


    }

    @Test
    public void KMP() {
        String ts = "ABCSBUGEBDKFCBCDLD", ps = "ABB";
        char[] t = ts.toCharArray();
        char[] p = ps.toCharArray();
        // 主串的位置
        int i = 0;
        // 模式串的位置
        int j = 0;
        int[] next = getNext(p);
        while (i < t.length && j < p.length) {
            // 当j为-1时，要移动的是i，当然j也要归0
            if (j == -1 || t[i] == p[j]) {
                i++;
                j++;
            } else {
                // i不需要回溯了
                // i = i - j + 1;
                // j回到指定位置
                j = next[j];
            }
        }
        int result;
        if (j == p.length) {

            result = i - j;

        } else {

            result = -1;

        }
        log.info("result:{}", result);
    }

    public int[] getNext(char[] ps) {
        int[] next = new int[ps.length];
        next[0] = -1;
        int j = 0;
        int k = -1;
        while (j < ps.length - 1) {
            if (k == -1 || ps[j] == ps[k]) {
                // 当两个字符相等时要跳过
                if (ps[++j] == ps[++k]) {
                    next[j] = next[k];
                } else {
                    next[j] = k;
                }
            } else {
                k = next[k];
            }
        }
        return next;
    }
}
