package com.liaojl.test.algorithm.sort.integer;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author LiaoJL
 * @description TODO 冒泡排序,稳定排序
 * @file BubbleSort.java
 * @email jinlongliao@foxmail.com
 * @date 2020/5/2 19:15
 */
public class BubbleSort extends IntegerSort {
    private static final Logger log = LoggerFactory.getLogger(BubbleSort.class);

    /**
     * 冒泡排序示例：
     * 2,4,3
     * <pre>
     *     2,4,3
     *     ^
     *     2与4 对比 2<4 不进行交换
     *     2,4,3
     *       ^
     *     4与3 对比 4>3 4,3 交换位置
     * </pre>
     * 一次移动索引，对相邻的两个数字对比排序，次循环，将最大数移动到最后面
     * </b>
     * 对于 特殊的 有序数组，在次排序的后判断 是否发生过移动，没有移动代表为，后面的数据已是 有序的
     *
     * @param ints
     * @return
     */
    @Override
    public int[] intSort(int[] ints) {
        for (int i = 0; i < ints.length; i++) {
            int swagger = 0;
            for (int j = 0; j < ints.length - i - 1; j++) {
                int tem;
                int anInt0 = ints[j];
                int anInt1 = ints[j + 1];
                boolean bigger = anInt0 > anInt1;
                if (bigger) {
                    tem = anInt1;
                    ints[j + 1] = anInt0;
                    ints[j] = tem;
                    swagger++;
                }
            }
            if (swagger == 0) {
                break;
            }
        }
        return ints;
    }

    @Test
    public void test() {
        int[] ints = intSort(sort2);
        log.info("ints:{}", ints);
    }

}
