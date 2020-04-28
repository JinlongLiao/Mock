package com.liaojl.test.tree;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author LiaoJL
 * @description TODO 二叉平衡树简单实现
 * @file AvlTree.java
 * @email jinlongliao@foxmail.com
 * @date 2020/4/21 12:45
 */
public class AvlTree<T extends Comparable<? super T>> {
    private static class AvlNode<T> {
        //avl树节点
        AvlNode(T theElement) {
            this(theElement, null, null);
        }

        AvlNode(T theElement, AvlNode<T> lt, AvlNode<T> rt) {
            element = theElement;
            left = lt;
            right = rt;
            height = 0;
        }

        // 节点中的数据
        T element;
        // 左儿子
        AvlNode<T> left;
        // 右儿子
        AvlNode<T> right;
        // 节点的高度
        int height;
    }

    //avl树根
    private AvlNode<T> root;

    public AvlTree() {
        root = null;
    }

    public AvlNode<T> getRoot() {
        return root;
    }

    //在avl树中插入数据，重复数据复略
    public void insert(T x) {
        root = insert(x, root);
    }

    //在avl中删除数据,这里并未实现
    public void remove(T x) {
        log.info("Sorry, remove unimplemented");
    }

    //在avl树中找最小的数据
    public T findMin() {
        if (isEmpty())
            log.info("树空");
        ;
        return findMin(root).element;
    }

    //在avl树中找最大的数据
    public T findMax() {
        if (isEmpty())
            log.info("树空");
        return findMax(root).element;
    }

    //搜索
    public boolean contains(T x) {
        return contains(x, root);
    }

    public void makeEmpty() {
        root = null;
    }

    public boolean isEmpty() {
        return root == null;
    }

    /**
     * 排序输出avl树
     */
    public void printTree() {
        if (isEmpty()) {
            log.info("Empty tree");
        } else {
            printTree(root);
        }
    }

    /**
     * @param node 要插入的节点
     * @param root 树根节点
     * @return
     */
    private AvlNode<T> insert(T node, AvlNode<T> root) {
        if (root == null) {
            return new AvlNode<T>(node, null, null);
        }

        int compareResult = node.compareTo(root.element);

        int heightRight = height(root.right);
        int heightLeft = height(root.left);
        if (compareResult < 0) {
            //将x插入左子树中,使用递归思想
            root.left = insert(node, root.left);
            //打破平衡
            if (heightLeft - heightRight > 1)
                if (node.compareTo(root.left.element) < 0) {
                    /**
                     *LL型（左左型）
                     */
                    root = rotateWithLeftChild(root);
                } else {
                    /**
                     * LR型（左右型）
                     */
                    root = doubleWithLeftChild(root);
                }
        } else if (compareResult > 0) {
            root.right = insert(node, root.right);//将x插入右子树中
            if (heightRight - heightLeft == 2)
            /**
             * 打破平衡
             */
                if (node.compareTo(root.right.element) > 0) {
                    //RR型（右右型）
                    root = rotateWithRightChild(root);
                } else {                    //RL型
                    root = doubleWithRightChild(root);
                }
        } else
            ;  // 重复数据，什么也不做
        /**
         * 更新树操作
         */
        root.height = Math.max(heightLeft, heightRight) + 1;
        return root;
    }

    //找最小
    private AvlNode<T> findMin(AvlNode<T> t) {
        if (t == null)
            return t;
        while (t.left != null)
            t = t.left;
        return t;
    }

    //找最大
    private AvlNode<T> findMax(AvlNode<T> t) {
        if (t == null)
            return t;
        while (t.right != null)
            t = t.right;
        return t;
    }

    //搜索（查找）
    private boolean contains(T x, AvlNode t) {
        while (t != null) {
            int compareResult = x.compareTo((T) t.element);

            if (compareResult < 0)
                t = t.left;
            else if (compareResult > 0)
                t = t.right;
            else
                return true;    // Match
        }
        return false;   // No match
    }

    /**
     * 中序遍历avl树
     */

    private void printTree(AvlNode<T> t) {
        if (t != null) {
            printTree(t.left);
            log.info("node: {},height:{}", t.element, t.height);
            printTree(t.right);
        }
    }

    /**
     * 求高度
     *
     * @param node
     * @return
     */
    public int height(AvlNode<T> node) {
        return node == null ? -1 : node.height;
    }

    //带左子树旋转,适用于LL型
    private AvlNode<T> rotateWithLeftChild(AvlNode<T> k2) {
        AvlNode<T> k1 = k2.left;
        k2.left = k1.right;
        k1.right = k2;
        k2.height = Math.max(height(k2.left), height(k2.right)) + 1;
        k1.height = Math.max(height(k1.left), k2.height) + 1;
        return k1;
    }

    //带右子树旋转，适用于RR型
    private AvlNode<T> rotateWithRightChild(AvlNode<T> k1) {
        AvlNode<T> k2 = k1.right;
        k1.right = k2.left;
        k2.left = k1;
        k1.height = Math.max(height(k1.left), height(k1.right)) + 1;
        k2.height = Math.max(height(k2.right), k1.height) + 1;
        return k2;
    }

    //双旋转，适用于LR型
    private AvlNode<T> doubleWithLeftChild(AvlNode<T> k3) {
        k3.left = rotateWithRightChild(k3.left);
        return rotateWithLeftChild(k3);
    }

    //双旋转,适用于RL型
    private AvlNode<T> doubleWithRightChild(AvlNode<T> k1) {
        k1.right = rotateWithLeftChild(k1.right);
        return rotateWithRightChild(k1);
    }

    private static final Logger log = LoggerFactory.getLogger(AvlTree.class);

}