package com.liaojl.test.tree;

import com.google.gson.Gson;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author LiaoJL
 * @description TODO
 * @file TreeTest.java
 * @CopyRight (C) http://www.koal.com/
 * @email jinlongliao@foxmail.com
 * @date 2020/4/21 11:18
 */
public class TreeTest {
    private static final Logger log = LoggerFactory.getLogger(TreeTest.class);

    @Test
    public void testAvlTree() {
        AvlTree<Integer> t = new AvlTree<>();
        final int NUMS = 200;
        final int GAP = 17;
        log.info("Checking... (no more output means success)");
        for (int i = GAP; i != 0; i = (i + GAP) % NUMS) {
            t.insert(i);
        }
        t.printTree();
        log.info(new Gson().toJson(t));
    }

    @Test
    public void testRbTree() {
        DRBTree<Integer> tree = new DRBTree<>();
        int[] a = {10, 20, 30, 40, 50, 60, 70, 80, 90};
//红黑树添加测试
        for (int i = 0; i < a.length; i++) {
            tree.insert(a[i]);
        }

        DRBTree.RBNode<Integer> src = tree.getRoot();
        log.info(new Gson().toJson(src));
    }
}
