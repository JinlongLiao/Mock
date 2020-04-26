package com.liaojl.test.concurrent;

import lombok.SneakyThrows;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Phaser;

public class Test3 {
    /**
     * 并发总数 ，类似CountDownLatch
     */
    public static final int PARTIES = 5;

    private Phaser phaser = new Phaser(PARTIES) {
        /**
         * Overridable method to perform an action upon impending phase
         * advance, and to control termination. This method is invoked
         * upon arrival of the party advancing this phaser (when all other
         * waiting parties are dormant).  If this method returns {@code
         * true}, this phaser will be set to a final termination state
         * upon advance, and subsequent calls to {@link #isTerminated}
         * will return true. Any (unchecked) Exception or Error thrown by
         * an invocation of this method is propagated to the party
         * attempting to advance this phaser, in which case no advance
         * occurs.
         *
         * <p>The arguments to this method provide the state of the phaser
         * prevailing for the current transition.  The effects of invoking
         * arrival, registration, and waiting methods on this phaser from
         * within {@code onAdvance} are unspecified and should not be
         * relied on.
         *
         * <p>If this phaser is a member of a tiered set of phasers, then
         * {@code onAdvance} is invoked only for its root phaser on each
         * advance.
         *
         * <p>To support the most common use cases, the default
         * implementation of this method returns {@code true} when the
         * number of registered parties has become zero as the result of a
         * party invoking {@code arriveAndDeregister}.  You can disable
         * this behavior, thus enabling continuation upon future
         * registrations, by overriding this method to always return
         * {@code false}:
         *
         * <pre> {@code
         * Phaser phaser = new Phaser() {
         *   protected boolean onAdvance(int phase, int parties) { return false; }
         * }}</pre>
         *
         * @param phase             the current phase number on entry to this method,
         *                          before this phaser is advanced
         * @param registeredParties the current number of registered parties
         * @return {@code true} if this phaser should terminate
         */
        @Override
        protected boolean onAdvance(int phase, int registeredParties) {
            log.info(" phase {},  registeredParties {} ", phase, registeredParties);
            registeredParties = 0;
            /**
             * phase 重启为零 失效为负数
             * 默认registeredParties 为0是 返回true 不进行阻塞
             */
            return super.onAdvance(phase, registeredParties);
        }
    };

    private static final Logger log = LoggerFactory.getLogger(Test3.class);

    @Test
    public void phaserTest() throws InterruptedException {
        Thread thread;
        for (int i = 0; i < PARTIES - 1; i++) {
            if (i > 1 && i < 3) {
                thread = new Thread(new Test3.MyThread(phaser, 1));
            } else {
                thread = new Thread(new Test3.MyThread(phaser));
            }
            thread.setName("线程" + (i + 1));
            thread.start();
        }
        Thread.sleep(1000);
        log.info("线程名：{} 到达屏障的顺序：{},已使用数：{},总数：{},未使用数：{},时间：{} 开始执行！！！",
                Thread.currentThread().getName(),
                phaser.getPhase(),
                phaser.getArrivedParties(),
                phaser.getRegisteredParties(),
                phaser.getUnarrivedParties(),
                System.currentTimeMillis());
        phaser.arriveAndDeregister();
        Thread.sleep(2000);
    }

    private static class MyThread implements Runnable {
        private static final Logger log = LoggerFactory.getLogger(Test3.MyThread.class);
        private final Phaser phaser;
        private long timeout = 0;

        public MyThread(Phaser phaser) {
            this.phaser = phaser;
        }

        public MyThread(Phaser phaser, long timeout) {
            this.phaser = phaser;
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
//            Thread.sleep((int) (Math.random() * 1000));
            /**
             * 线程阻塞
             */
            if (timeout < 1) {
                phaser.arriveAndAwaitAdvance();
                log.info("线程名：{} 到达屏障的顺序：{},总数：{},总数：{},总数：{},时间：{} 一起去打怪！！！",
                        Thread.currentThread().getName(),
                        phaser.getPhase(),
                        phaser.getArrivedParties(),
                        phaser.getRegisteredParties(),
                        phaser.getUnarrivedParties(),
                        System.currentTimeMillis());
            } else {
                /**
                 * 非阻塞，仅将信号量减一
                 */
                phaser.arriveAndDeregister();
                log.info("线程名：{} 到达屏障的顺序：{},总数：{},总数：{},总数：{},时间：{} lz不干了，先走了！！！",
                        Thread.currentThread().getName(),
                        phaser.getPhase(),
                        phaser.getArrivedParties(),
                        phaser.getRegisteredParties(),
                        phaser.getUnarrivedParties(),
                        System.currentTimeMillis());
            }

        }
    }

}
