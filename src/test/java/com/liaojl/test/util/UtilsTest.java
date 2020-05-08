package com.liaojl.test.util;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.Charset;

/**
 * @author LiaoJL
 * @description TODO
 * @file UtilsTest.java
 * @email jinlongliao@foxmail.com
 * @date 2020/5/8 23:23
 */
public class UtilsTest {
    private static final Logger log = LoggerFactory.getLogger(UtilsTest.class);

    /**
     * 测试自定义BloomFilter
     */
    @Test
    public void testMyBloomFilter() {
        String value = "stone2083@yahoo.cn";
        SimpleBloomFilter filter = new SimpleBloomFilter();
        log.info("{}", filter.contains(value));
        filter.add(value);
        log.info("{}", filter.contains(value));
    }

    /**
     * 测试 Guavar BloomFilter
     * 1 funnel
     * 2 expectedInsertions 预故插入的数量
     * 3 fpp 假阳性的概率
     */
    @Test
    public void testGuavarBloomFilter() {
        // 100w bit长度 ,0.01%误判率
        // bf对象则会生成 299534 个long数组,使用13次hash计算.
        BloomFilter<String> bf = BloomFilter
                .create(Funnels.stringFunnel(Charset.defaultCharset()), 100 * 10000, 0.0001d);

        System.out.println(bf.mightContain("quding")); // false
        bf.put("quding"); // bitCount=13
        System.out.println(bf.mightContain("quding")); // true
        bf.put("quding1"); // bitCount=26

    }
}
