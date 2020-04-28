package com.liaojl.test.concurrent.x;


import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

public class FutureTest {
    private static final Logger log = LoggerFactory.getLogger(FutureTest.class);

    @Test
    public void test1() throws Exception {
        HttpTask task = new HttpTask();

        log.info("main thread starts in thread id: " + Thread.currentThread().getId());

        // 新建一个Future
        CompletableFuture<String> futureNonBlocking = CompletableFuture.supplyAsync(() -> {
            try {
                return task.doHttp("http://guazi.com");
            } catch (Exception e) {
                log.error(e.getMessage(), e);
                throw new CompletionException(e);
            }
        });
        // 新建一个Future
        CompletableFuture<String> futureBlocking = CompletableFuture.supplyAsync(() -> {
            try {
                return task.doHttp("https://guazi.com");
            } catch (Exception e) {
                log.error(e.getMessage(), e);
                throw new CompletionException(e);
            }
        });

        // 非阻塞
        log.info("-----------------非阻塞位置1----------------------\n");
        // future.thenAcceptAsync() 方法不阻塞本线程，http请求成功后执行回调函数

        futureNonBlocking.thenAcceptAsync(result -> log.info("\n非阻塞 FUTURE:\n" + result + "\n"));


        log.info("-----------------非阻塞位置2----------------------\n");

        // 阻塞
        String message = futureBlocking.get();
        log.info("阻塞 future:\n" + message + "\n");
        // future.get() 方法阻塞本线程，直到http请求成功
        log.info("-----------------阻塞位置1----------------------\n");
        log.info("main thread ends");
        //防止主线程在其他线程运行完成之前退出,导致打印信息不全
        System.in.read();
    }
}