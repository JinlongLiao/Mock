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
    public void testNumberToNSquare() {
        int i = Integer.numberOfLeadingZeros(10);
        int n = -1 >>> i;

        log.info("I :{}", i);
        log.info("N :{}", n + 1);
    }

    /**
     * 0 iconst_1
     * 1 istore_1
     * 2 iinc 1 by 1
     * 5 getstatic #12 <java/lang/System.out>
     * 8 iload_1
     * 9 invokestatic #13 <java/lang/String.valueOf>
     * 12 iconst_0
     * 13 anewarray #2 <java/lang/Object>
     * 16 invokevirtual #14 <java/io/PrintStream.printf>
     * 19 pop
     * 20 goto 61 (+41)
     * 23 astore_2
     * 24 iinc 1 by 1
     * 27 getstatic #12 <java/lang/System.out>
     * 30 iload_1
     * 31 invokestatic #13 <java/lang/String.valueOf>
     * 34 iconst_0
     * 35 anewarray #2 <java/lang/Object>
     * 38 invokevirtual #14 <java/io/PrintStream.printf>
     * 41 pop
     * 42 return
     * 43 astore_3
     * 44 getstatic #12 <java/lang/System.out>
     * 47 iload_1
     * 48 invokestatic #13 <java/lang/String.valueOf>
     * 51 iconst_0
     * 52 anewarray #2 <java/lang/Object>
     * 55 invokevirtual #14 <java/io/PrintStream.printf>
     * 58 pop
     * 59 aload_3
     * 60 athrow
     * 61 return
     */
    @Test
    public void byteCodeTest() {
        int a = 1;
        try {
            a++;
        } catch (Exception e) {
            ++a;
            return;
        } finally {
            System.out.printf(String.valueOf(a));
        }
    }
}
