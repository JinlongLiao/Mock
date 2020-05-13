package com.liaojl.test.vm;

import org.junit.Test;
import org.openjdk.jol.info.ClassLayout;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ObjectTest {
    private static final Logger log = LoggerFactory.getLogger(ObjectTest.class);


    @Test
    public void test1() throws InterruptedException {
//        Thread.sleep(5000);
        Object o = new Object();
        log.info("{}", ClassLayout.parseInstance(o).toPrintable());
        synchronized (o) {
            log.info("{}", ClassLayout.parseInstance(o).toPrintable());
        }
    }

    @Test
    public void testNumberToSquare() {
        int i = Integer.numberOfLeadingZeros(10);
        int n = -1 >>> i;

        log.info("I :{}", i);
        log.info("N :{}", n + 1);
    }
}
