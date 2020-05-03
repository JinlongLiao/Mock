package com.liaojl.test.algorithm.sort.integer;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author LiaoJL
 * @description TODO 快速排序 是对冒泡排序的一种改进,不稳定排序
 * @file QuickSort.java
 * @email jinlongliao@foxmail.com
 * @date 2020/5/3 09:33
 */
public class QuickSort extends IntegerSort {
    private static final Logger log = LoggerFactory.getLogger(QuickSort.class);

    @Override
    int[] intSort(int[] ints) {
        ints = qSort(ints, 0, ints.length - 1);
        return ints;
    }

    public int[] qSort(int ints[], int start, int end) {
        int left = start;
        int right = end;
        int middle = ints[(start + end) / 2];
        while (left < right) {
            /**
             * 找到左边大于中间量的数字
             */
            while (ints[left] < middle) {
                left++;
            }
            /**
             * 找到右边边小于于中间量的数字
             */
            while (ints[right] > middle) {
                right--;
            }
            if (left >= right) {
                break;
            }
            /**
             * 交换
             */
            int anInt = ints[left];
            ints[left] = ints[right];
            ints[right] = anInt;
            if (ints[left] == middle) {
                right--;
            }
            if (ints[right] == middle) {
                left++;
            }

        }

        return (ints);
    }

    @Test
    public void test() {
        int[] ints = intSort(sort1);
        log.info("ints:{}", ints);
    }
}
