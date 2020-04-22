package com.liaojl.test.concurrent;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Exchanger;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Test1 {
    private static final Logger log = LoggerFactory.getLogger(Test1.class);

    @Test
    public void semaphoreTest() throws InterruptedException {
        Semaphore semaphore = new Semaphore(2);
        semaphoreTestLog(semaphore);
//         永久阻塞
//        semaphore.acquire(2);
//      线程阻塞10S
        boolean b = semaphore.tryAcquire(3, TimeUnit.SECONDS);
        semaphoreTestLog(semaphore);
        log.info("Semaphore get 10 3 Sec {}", b);
        semaphore.release(3);
        semaphoreTestLog(semaphore);
//        比当前的总量大时 会发生扩充多余部分
        semaphore.release(4);
        semaphoreTestLog(semaphore);
    }

    /**
     * 打印 semaphore 日志
     *
     * @param semaphore
     */
    private void semaphoreTestLog(Semaphore semaphore) {
        log.info("Semaphore size {},可用数：{}，阻塞长度:{}", semaphore.availablePermits(), semaphore.drainPermits(), semaphore.getQueueLength());
    }

    public static class MyThread implements Runnable {
        private final Exchanger<String> exchanger;
        private final String message;
        private static final Logger log = LoggerFactory.getLogger(MyThread.class);

        public MyThread(Exchanger<String> exchanger, String message) {
            super();
            this.exchanger = exchanger;
            this.message = message;

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
        @Override
        public void run() {
            try {
                log.info("线程名称：[{}]，获取的信息：{}", Thread.currentThread().getName(), exchanger.exchange(message));
            } catch (InterruptedException e) {
                log.error(e.getMessage(), e);
            }
        }
    }

    @Test
    public void exchangerTest() throws InterruptedException {
        /**
         * 无法指定 发送的消息接收方的 信息交换，其必须双方都进行交换才可获得消息
         */
        List<String> messageList = Arrays.asList("A的信息", "B 的信息", "C 的信息", "D 的信息");
        Exchanger<String> exchanger = new Exchanger<>();
        for (String message : messageList) {
            Thread thread = new Thread(new MyThread(exchanger, message));
            thread.setName(message);
            thread.start();
        }
        Thread.sleep(1000);
    }
}
