package com.liaojl.test.concurrent;

import lombok.SneakyThrows;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class Test2 {
    private static final Logger log = LoggerFactory.getLogger(Test2.class);
    public static final int SIZE = 5;

    /**
     * 同生共死
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

    /**
     * 有困难兄弟线上，我收人头
     */
    @Test
    public void cyclicBarrierTest() throws InterruptedException {
//        CyclicBarrier cyclicBarrier = new CyclicBarrier(SIZE);
        CyclicBarrier cyclicBarrier = new CyclicBarrier(SIZE,
                () -> log.info("线程名：{} ， 时间：{} 坐等收人头！！！",
                        Thread.currentThread().getName(),
                        System.currentTimeMillis()));
        Thread thread = null;
        /**
         * 当最后一个 await 完成 阻塞才完成
         * true  最先执行 但后续的变为同步任务，他们要同步执行完成
         * false 最后执行，其执行顺序为随意，任务的执行时间 是他们最后一个完成await 的时间
         */
        boolean iAmFirst = false;
        for (int i = 0; i < SIZE; i++) {
            if (2 > i && i < 4) {
                /**
                 * 当阻塞超时是 抛出错误异常，但不会影响后续执行，仅仅是错误提醒
                 * <code>
                 *    Exception in thread "线程2" java.util.concurrent.TimeoutException
                 * 	at java.util.concurrent.CyclicBarrier.dowait(CyclicBarrier.java:257)
                 * 	at java.util.concurrent.CyclicBarrier.await(CyclicBarrier.java:435)
                 * 	at com.liaojl.test.concurrent.Test2$MyThread2.run(Test2.java:180)
                 * 	at java.lang.Thread.run(Thread.java:748)
                 * </code>
                 */
                thread = new Thread(new MyThread2(cyclicBarrier, 1, iAmFirst));
            } else {
                thread = new Thread(new MyThread2(cyclicBarrier, iAmFirst));
            }
            thread.setName("线程" + (i + 1));
            thread.start();
        }
        Thread.sleep(5000);
    }

    private static class MyThread2 implements Runnable {
        private static final Logger log = LoggerFactory.getLogger(MyThread.class);
        private final CyclicBarrier cyclicBarrier;
        private boolean first;
        private long timeout = 0;

        /**
         * @param cyclicBarrier
         * @param first         是否最先执行
         */
        public MyThread2(CyclicBarrier cyclicBarrier, boolean first) {
            this.cyclicBarrier = cyclicBarrier;
            this.first = first;
        }

        /**
         * @param cyclicBarrier
         * @param timeout       延迟时间 纳秒
         * @param first         是否最先执行
         */
        public MyThread2(CyclicBarrier cyclicBarrier, long timeout, boolean first) {
            this.cyclicBarrier = cyclicBarrier;
            this.timeout = timeout;
            this.first = first;
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
            long millis = (long) (Math.random() * 1000);
            Thread.sleep(millis);
            if (first) {
                /**
                 * 线程阻塞
                 */
                if (timeout < 1) {
                    cyclicBarrier.await();
                } else {
                    cyclicBarrier.await(timeout, TimeUnit.NANOSECONDS);
                    log.info("线程名：{}  lz不当炮灰，先走了！！！",
                            Thread.currentThread().getName());
                }
            }
            log.info("线程名：{} 总数：{},到达屏障总数：{},时间：{} 一起去打怪！！！",
                    Thread.currentThread().getName(),
                    cyclicBarrier.getParties(),
                    cyclicBarrier.getNumberWaiting(),
                    System.currentTimeMillis());
            if (!first) {
                /**
                 * 线程阻塞
                 */
                if (timeout < 1) {
                    cyclicBarrier.await();
                } else {
                    cyclicBarrier.await(timeout, TimeUnit.NANOSECONDS);
                    log.info("线程名：{} lz不当炮灰，先走了！！！",
                            Thread.currentThread().getName());
                }
            }
        }
    }

}
