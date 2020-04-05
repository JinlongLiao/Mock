package com.liaojl.io.nnio.server;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author LiaoJL
 * @description TODO
 * @file TimeServerHandlerExecutor.java
 * @CopyRight (C) http://www.koal.com/
 * @email jinlongliao@foxmail.com
 * @date 2020/3/30 16:17
 */
public class TimeServerHandlerExecutor {

    private ExecutorService executor;

    public TimeServerHandlerExecutor(int maxPoolSize, int queueSize) {
        executor = new ThreadPoolExecutor(Runtime.getRuntime()
                .availableProcessors(), maxPoolSize, 120L, TimeUnit.SECONDS,
                new ArrayBlockingQueue(queueSize));
    }

    public void execute(java.lang.Runnable task) {
        executor.execute(task);
    }
}
