package com.liaojl.test.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.BitSet;

public class SimpleBloomFilter {
    private static final Logger log = LoggerFactory.getLogger(SimpleBloomFilter.class);
    private int size;
    /**
     * 增加随机数减少Hash 重复
     */
    private int[] seeds;
    private BitSet bits;
    private SimpleHash[] func;

    public SimpleBloomFilter() {
        this(new int[]{5, 7, 11, 13, 31, 37, 61}, Integer.MAX_VALUE);
    }

    public SimpleBloomFilter(int[] seeds, int size) {
        this.seeds = seeds;
        this.size = size;
        this.bits = new BitSet(size);
        this.func = new SimpleHash[seeds.length];
        init();
    }

    private void init() {
        for (int i = 0; i < seeds.length; i++) {
            func[i] = new SimpleHash(this.size, seeds[i]);
        }
    }

    public void add(String value) {
        for (SimpleHash f : func) {
            bits.set(f.hash(value), true);
        }
    }

    public boolean contains(String value) {
        if (value == null) {
            return false;
        }
        boolean ret = true;
        for (SimpleHash f : func) {
            ret = ret && bits.get(f.hash(value));
        }
        return ret;
    }

    public static class SimpleHash {
        private int cap;
        private int seed;

        public SimpleHash(int cap, int seed) {
            this.cap = cap;
            this.seed = seed;
        }

        public int hash(String value) {
            int result = 0;
            int len = value.length();
            for (int i = 0; i < len; i++) {
                result = seed * result + value.charAt(i);
            }
            return (cap - 1) & result;
        }
    }
}