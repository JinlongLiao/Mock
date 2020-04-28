package com.liaojl.test.concurrent.x;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Supplier;

public class TimeMain {
    private static final Logger log = LoggerFactory.getLogger(TimeMain.class);

    public <U> U blockingGet(Supplier<U> supplier) throws InterruptedException, ExecutionException {

        // create a future
        CompletableFuture<U> future = CompletableFuture.supplyAsync(supplier);
        return future.get();

    }

    public <U> U blockingGetWithTimeConsuming(TimeConsumingUtil consuming, Supplier<U> supplier) throws InterruptedException, ExecutionException {
        Long begin = System.nanoTime();
        U result = blockingGet(supplier);
        Long end = System.nanoTime();
        consuming.setConsumingNanoTime(end - begin);
        return result;
    }


    public <U> CompletableFuture<U> nonBlockingGet(Supplier<U> supplier) {
        return CompletableFuture.supplyAsync(supplier);
    }

    @Test
    public void test() throws InterruptedException, ExecutionException, IOException {
        HttpTask task = new HttpTask();
        TimeConsumingUtil uu1 = new TimeConsumingUtil();
        String result = blockingGetWithTimeConsuming(uu1, () -> {
            try {
                return task.doHttp("http://baidu.com");
            } catch (Throwable ta) {
                log.error(ta.getMessage(), ta);
                throw new RuntimeException(ta);
            }
        });
//        String result = blockingGet(() -> {
//            try {
//                return task.doHttp("http://baidu.com");
//            } catch (Throwable ta) {
//                log.error(ta.getMessage(), ta);
//                throw new RuntimeException(ta);
//            }
//        });
        log.info("result in main: " + result);
        log.info("main thread ends");
        System.in.read();
    }
}