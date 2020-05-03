package com.liaojl.test.algorithm.sort.integer;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author LiaoJL
 * @description TODO 选择排序排序,不稳定排序
 * @file SelectSort.java
 * @email jinlongliao@foxmail.com
 * @date 2020/5/2 19:15
 */
public class SelectSort extends IntegerSort {
    private static final Logger log = LoggerFactory.getLogger(SelectSort.class);

    @Override
    public int[] intSort(int[] ints) {
        return sort2(ints);
    }


    /**
     * 选择排序示例：
     * 2,4,3
     * <pre>
     *     2,4,3
     *     ^
     *     2与4，3 等之后的数据对比,如果有比起小的数据则发生交换，否则保持。
     *     </b>结果：2,4,3 . 这样保持索引之前的数据都是最小的
     *     2,4,3
     *       ^
     *     4与3 等之后的数据对比,如果有比起小的数据则发生交换,
     *     </b>结果：2,3,4
     * </pre>
     *
     * @param ints
     * @return
     */
    private int[] sort1(int[] ints) {
        for (int i = 0; i < ints.length; i++) {
            int tem;
            int anInt0 = ints[i];
            for (int j = i + 1; j < ints.length; j++) {
                int anInt1 = ints[j];
                boolean bigger = anInt0 > anInt1;
                if (bigger) {
                    tem = anInt1;
                    ints[j] = anInt0;
                    ints[i] = tem;
                }
            }
        }
        return ints;
    }

    /**
     * 选择排序优化示例：
     * 2,4,3
     * </b>
     * 每次选择出最大&最小 两个数据，让后让 最小数据存放 索引出，最大数据放到 length-索引的位置，增加排序效果
     * <pre>
     *     2,4,3
     *     ^
     *     索引为0，最大值为 2，最小值为4 ，故 ints[0]=2; ints[2-0]=4
     *     </b>结果：2,3,4
     * </pre>
     *
     * @param ints
     * @return
     */
    private int[] sort2(int[] ints) {
        for (int i = 0; i < ints.length; i++) {
            int min = ints[i];
            int max = ints[ints.length - (i + 1)];
            for (int j = i + 1; j < ints.length - i; j++) {
                ints[i] = (min = min > ints[j] ? ints[j] : min);
                ints[ints.length - (i + 1)] = (max = max < ints[j] ? ints[j] : max);
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
