package com.liaojl.test.concurrent;

import lombok.SneakyThrows;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class Test2 {
    private static final Logger log = LoggerFactory.getLogger(Test2.class);
    public static final int SIZE = 5;

    /**
     * 组团
     *
     * @throws InterruptedException
     */
    @Test
    public void countDownLatchTest() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        Thread thread = null;
        for (int i = 0; i < SIZE + 1; i++) {
            if (i > 1 && i < 3) {
                thread = new Thread(new MyThread(countDownLatch, 1));
            } else {
                thread = new Thread(new MyThread(countDownLatch));
            }
            thread.setName("线程" + (i + 1));
            thread.start();
        }
        Thread.sleep(100);
        log.info("傻了吧，阻塞住了");
        Thread.sleep(100);
        log.info("开始了");
        countDownLatch.countDown();
        Thread.sleep(100);


    }

    private static class MyThread implements Runnable {
        private static final Logger log = LoggerFactory.getLogger(MyThread.class);
        private final CountDownLatch countDownLatch;
        private long timeout = 0;

        public MyThread(CountDownLatch countDownLatch) {
            this.countDownLatch = countDownLatch;
        }

        public MyThread(CountDownLatch countDownLatch, long timeout) {
            this.countDownLatch = countDownLatch;
            this.timeout = timeout;
        }

        /**
         * When an object implementing interface <code>Runnable</code> is used
         * to create a thread, starting the thread causes the object's
         * <code>run</code> method to be called in that separately executing
         * thread.
         * <p>
         * The general contract of the method <code>run</code> is that it may
         * take any action whatsoever.
         *
         * @see Thread#run()
         */
        @SneakyThrows
        @Override
        public void run() {
            /**
             * 线程阻塞
             */
            if (timeout < 1) {
                countDownLatch.await();
            } else {
                countDownLatch.await(timeout, TimeUnit.NANOSECONDS);
                log.info("lz受不了，先走了");
            }
            log.info("线程名：{} 总数：{},时间：{} 一起去打怪！！！", Thread.currentThread().getName(), countDownLatch.getCount(), System.currentTimeMillis());
        }
    }

    @Test
    public void cyclicBarrierTest() {

    }
}
