package com.liaojl.test.algorithm.sort.integer;


import com.liaojl.test.algorithm.sort.Sort;

public abstract class IntegerSort implements Sort {
    protected int[] sort1 = new int[]{2, 156, 123, 0, 8, -1};
    protected int[] sort2 = new int[]{1, 2, 3, 4, 5};

    /**
     * 排序
     *
     * @param objects
     * @return
     */
    public Object[] sort(Object[] objects) {
        int[] ints = new int[]{objects.length};
        int index = 0;
        for (Object object : objects) {
            ints[index++] = (int) object;
        }
        ints = intSort(ints);
        index = 0;
        for (int i : ints) {
            objects[index++] = i;
        }
        return objects;
    }

    abstract int[] intSort(int[] ints);
}
