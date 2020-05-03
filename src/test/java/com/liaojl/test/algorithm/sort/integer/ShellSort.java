package com.liaojl.test.algorithm.sort.integer;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author LiaoJL
 * @description TODO 希尔排序,非稳定排序
 * @file ShellSort.java
 * @email jinlongliao@foxmail.com
 * @date 2020/5/2 23:35
 */
public class ShellSort extends InsertSort {
    private static final Logger log = LoggerFactory.getLogger(ShellSort.class);

    /**
     * 希尔排序 是插入排序的一种又称"缩小增量排序"
     * 插入排序示例:2,156,123,0,8,-1
     * <pre>
     * 1: 分组，分为 length/2 =2组，组距为3
     * [2,0],[156,8],[123,-1]
     * 组之内 进行插入排序: 0,8,-1,2,156,123
     * 2 ：再次分组，缩小组距 ，原组距/2=1,当组距为1 时等同插入排序
     * [ 0,8,-1,2,156,123]
     * 组之内 进行插入排序: -1,0,2,8,123,156
     *
     *
     * </pre>
     *
     * @param ints
     * @return
     */
    @Override
    public int[] intSort(int[] ints) {
        int length = ints.length;
        while (true) {
            length /= 2;
            for (int i = 0; i < length; i++) {
                for (int j = i + length; j < ints.length; j += length) {
                    if (ints[i] < ints[j]) {
                        int add = ints[i];
                        while (j <= i) {
                            int anInt = ints[j];
                            ints[j] = add;
                            add = anInt;
                            j += length;
                        }
                    }
                }
                System.out.println("");
            }
            if (length == 1) {
                break;
            }
        }
        return super.intSort(ints);
    }


    @Test
    public void test() {
        int[] ints = intSort(sort1);
        log.info("ints:{}", ints);
    }
}
