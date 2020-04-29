package com.liaojl.test.concurrent;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

/**
 * @author LiaoJL
 * @description TODO
 * @file Test4.java
 * @CopyRight (C) http://www.koal.com/
 * @email jinlongliao@foxmail.com
 * @date 2020/4/28 15:09
 */
public class Test4 {
    private static final Logger log = LoggerFactory.getLogger(Test4.class);

    private ThreadPoolExecutor threadPoolExecutorLink = new ThreadPoolExecutor(
            3,
            5,
            2,
            TimeUnit.SECONDS, new LinkedBlockingQueue<>());
    private ThreadPoolExecutor threadPoolExecutorArray = new ThreadPoolExecutor(
            3,
            5,
            2,
            TimeUnit.SECONDS, new ArrayBlockingQueue<>(5));
    private ThreadPoolExecutor threadPoolExecutorSync = new ThreadPoolExecutor(
            3,
            5,
            2,
            TimeUnit.SECONDS, new SynchronousQueue<>());
    private ThreadPoolExecutor threadPoolExecutor = null;

    @Before
    public void before() {
        threadPoolExecutor = threadPoolExecutorLink;
//        threadPoolExecutor = threadPoolExecutorArray;
//        threadPoolExecutor = threadPoolExecutorSync;
    }

    @Test
    public void threadPoolExecutor1() throws InterruptedException {
        for (int i = 0; i < 100000; i++) {
            threadPoolExecutor.execute(() -> {
                log.info("线程：{}", Thread.currentThread().getId());
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    log.error(e.getMessage(), e);
                }
            });
        }
        log.info("A:Core:{}", threadPoolExecutor.getCorePoolSize());
        log.info("A:Pool:{}", threadPoolExecutor.getPoolSize());
        log.info("A:Queue:{}", threadPoolExecutor.getQueue().size());
        Thread.sleep(20000);
        log.info("B:Core:{}", threadPoolExecutor.getCorePoolSize());
        log.info("B:Pool:{}", threadPoolExecutor.getPoolSize());
        log.info("B:Queue:{}", threadPoolExecutor.getQueue().size());

    }
}
