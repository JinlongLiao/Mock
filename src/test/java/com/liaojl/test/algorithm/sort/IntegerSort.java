package com.liaojl.test.algorithm.sort;


public abstract class IntegerSort implements Sort {
    protected int[] sort1 = new int[]{7, 23, 345, 322, 4, 2233};
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
