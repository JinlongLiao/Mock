package com.liaojl.test.algorithm.sort;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author LiaoJL
 * @description TODO 插入排序，稳定排序
 * @file InsertSort.java
 * @email jinlongliao@foxmail.com
 * @date 2020/5/2 22:12
 */
public class InsertSort extends IntegerSort {
    private static final Logger log = LoggerFactory.getLogger(InsertSort.class);

    /**
     * 插入排序示例：
     * 2,4,3
     * </b>
     * 索引之前，当做有序数组，将其后的数据，依次作为 节点插入
     * <pre>
     *     2,4,3
     *     ^
     *     将2 作为有序数组，4作为节点插入得:2,4
     *     2,4,3
     *       ^
     *     将2,4 作为有序数组，3作为节点插入得:2,3,4
     *
     * </pre>
     *
     * @param ints
     * @return
     */
    @Override
    int[] intSort(int[] ints) {
        for (int i = 1; i < ints.length; i++) {
            for (int j = 0; j < i; j++) {
                if (ints[i] < ints[j]) {
                    int add = ints[i];
                    while (j <= i) {
                        int anInt = ints[j];
                        ints[j] = add;
                        add = anInt;
                        j += 1;
                    }
                }
            }
        }
        return ints;
    }

    @Test
    public void test() {
        int[] ints = intSort(sort1);
        log.info("ints:{}", ints);
    }
}
