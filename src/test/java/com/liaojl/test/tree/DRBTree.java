package com.liaojl.test.tree;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author LiaoJL
 * @description TODO 红黑树
 * @file DRBTree.java
 * @email jinlongliao@foxmail.com
 * @date 2020/4/21 11:17
 */
public class DRBTree<T extends Comparable<T>> {

    private RBNode<T> root;
    private static final Logger log = LoggerFactory.getLogger(DRBTree.class);

    public RBNode<T> getRoot() {
        return root;
    }

    private enum ColorEnum {
        RED(true),
        BLACK(false);
        private boolean color;

        ColorEnum(boolean color) {
            this.color = color;
        }

        public boolean isColor() {
            return color;
        }
    }

    public static class RBNode<T extends Comparable<T>> {

        private ColorEnum color;
        private T key;
        private transient RBNode<T> parentNode;
        private RBNode<T> leftChildNode;
        private RBNode<T> rightChildNode;

        /**
         * @param col
         * @param key
         * @param parentNode
         * @param leftChildNode
         * @param rightChild
         */
        public RBNode(ColorEnum col, T key, RBNode<T> parentNode, RBNode<T> leftChildNode, RBNode<T> rightChild) {
            this.color = col;
            this.key = key;
            this.parentNode = parentNode;
            this.leftChildNode = leftChildNode;
            this.rightChildNode = rightChild;
        }

        public T getKey() {
            return this.key;
        }
    }

    private RBNode<T> parentOf(RBNode<T> node) {
        if (node != null) {
            return node.parentNode;
        }

        return null;
    }

    private ColorEnum colorOf(RBNode<T> node) {
        if (node != null) {
            return node.color;
        }
        return ColorEnum.BLACK;
    }

    public void setParent(RBNode<T> node, RBNode<T> parent) {
        if (node != null) {
            node.parentNode = parent;
        }
    }

    public void setColor(RBNode<T> node, ColorEnum color) {
        if (node != null) {
            node.color = color;
        }
    }

    public boolean isRed(RBNode<T> node) {
        return (node != null && node.color == ColorEnum.RED) ? true : false;
    }

    public boolean isBlack(RBNode<T> node) {
        return !isRed(node);
    }

    public void setRed(RBNode<T> node) {
        if (node != null) {
            node.color = ColorEnum.RED;
        }
    }

    public void setBlack(RBNode<T> node) {
        if (node != null) {

            node.color = ColorEnum.BLACK;
        }
    }

    //寻找为key值的节点
    public RBNode<T> search(T key, RBNode<T> node) {

        if (node != null) {
            int com = key.compareTo(node.key);
            if (com < 0) {
                return search(key, node.leftChildNode);
            } else if (com > 0) {
                return search(key, node.rightChildNode);
            } else {
                return node;
            }
        }
        return null;

    }

    //寻找后继节点，即大于该节点的最小节点
    public RBNode<T> min(RBNode<T> node) {
        if (node.leftChildNode == null) {
            return node;
        }

        while (node.leftChildNode != null) {
            node = node.leftChildNode;
        }

        return node;
    }

    public RBNode successor(RBNode<T> node) {
        if (node.rightChildNode != null) {
            return min(node.rightChildNode);
        }

        RBNode<T> y = node.parentNode;
        while ((y != null) && (y.rightChildNode == node)) {
            node = y;
            y = y.parentNode;
        }
        return y;
    }

    /**
     * 对某个节点进行左旋转
     *
     * @param node
     */
    public void leftRotation(RBNode<T> node) {
        RBNode<T> y = node.rightChildNode;

        if (y.leftChildNode != null) {
            y.leftChildNode.parentNode = node;
        }

        node.rightChildNode = y.leftChildNode;
        y.leftChildNode = node;
        y.parentNode = node.parentNode;


        if (node.parentNode != null) {
            if (node.parentNode.leftChildNode == node) {
                node.parentNode.leftChildNode = y;
            } else {
                node.parentNode.rightChildNode = y;
            }
        } else {
            this.root = y;
        }
        node.parentNode = y;

    }

    /**
     * 对某个节点进行右旋
     *
     * @param node
     */
    public void rightRotation(RBNode<T> node) {
        RBNode<T> y = node.leftChildNode;

        if (y.rightChildNode != null) {
            y.rightChildNode.parentNode = node;
        }

        y.parentNode = node.parentNode;
        node.leftChildNode = y.rightChildNode;
        y.rightChildNode = node;

        if (node.parentNode != null) {
            if (node.parentNode.leftChildNode == node) {
                node.parentNode.leftChildNode = y;
            } else {
                node.parentNode.rightChildNode = y;
            }
        } else {
            this.root = y;
        }
        node.parentNode = y;
    }


    //红黑树添加修复
    public void insertFixUp(RBNode<T> node) {
        RBNode<T> parent, gparent;
        while (((parent = parentOf(node)) != null) && isRed(parent)) {
            gparent = parentOf(parent);
            if (gparent.leftChildNode == parent) {
                RBNode<T> uncle = gparent.rightChildNode;
                if (isRed(uncle)) {
                    setBlack(parent);
                    setBlack(uncle);
                    setRed(gparent);

                    node = gparent;
                    continue;
                } else {
                    if (parent.rightChildNode == node) {
                        leftRotation(parent);
                        RBNode<T> temp = node;
                        node = parent;
                        parent = temp;
                    }

                    setBlack(parent);
                    setRed(gparent);
                    rightRotation(gparent);

                }
            } else {

                RBNode<T> uncle = gparent.leftChildNode;
                if (isRed(uncle)) {
                    setBlack(parent);
                    setBlack(uncle);
                    setRed(gparent);

                    node = gparent;
                    continue;
                } else {
                    if (parent.leftChildNode == node) {
                        rightRotation(parent);
                        RBNode<T> temp = node;
                        node = parent;
                        parent = temp;
                    }
                    setBlack(parent);
                    setRed(gparent);
                    leftRotation(gparent);
                }
            }
        }

        if (root == node) {
            setBlack(node);
        }
    }

    //红黑树删除修复
    public void deleteFixUp(RBNode<T> node, RBNode<T> parent) {

        RBNode<T> other;
        while (isBlack(node) && node != this.root) {

            if (parent.leftChildNode == node) {
                other = parent.rightChildNode;
                if (isRed(other)) {
                    setRed(parent);
                    setBlack(other);
                    leftRotation(parent);
                    continue;
                } else {
                    if (isBlack(other.leftChildNode) && isBlack(other.rightChildNode)) {
                        setRed(other);
                        node = parent;
                        parent = parentOf(node);


                    } else if (isRed(other.leftChildNode) && isBlack(other.rightChildNode)) {
                        setRed(other);
                        setBlack(other.leftChildNode);
                        rightRotation(other);
                    } else if (isRed(other.rightChildNode)) {
                        setColor(other, colorOf(parent));
                        setBlack(parent);
                        setBlack(other.rightChildNode);
                        leftRotation(parent);
                        break;
                    }
                }
            } else {
                other = parent.leftChildNode;
                if (isRed(other)) {
                    setBlack(other);
                    setRed(parent);
                    rightRotation(parent);
                    continue;
                } else {
                    if (isBlack(other.leftChildNode) && isBlack(other.rightChildNode)) {
                        setRed(other);
                        node = parent;
                        parent = parentOf(node);


                    } else if (isRed(other.rightChildNode) && isBlack(other.leftChildNode)) {
                        setRed(parent);
                        setBlack(other.rightChildNode);
                        leftRotation(other);
                    } else if (isRed(other.leftChildNode)) {
                        setColor(other, colorOf(parent));
                        setBlack(parent);
                        setBlack(other.leftChildNode);
                        rightRotation(parent);
                        break;
                    }
                }
            }
        }

        setBlack(node);

    }


    //红黑树添加操作
    public void insert(RBNode<T> node) {

        int com;
        RBNode<T> x = this.root;
        RBNode<T> y = null;
        while (x != null) {
            y = x;
            com = node.key.compareTo(x.key);

            if (com < 0) {
                x = x.leftChildNode;
            } else {
                x = x.rightChildNode;
            }

        }

        node.parentNode = y;

        if (y != null) {
            com = node.key.compareTo(y.key);
            if (com < 0) {
                y.leftChildNode = node;
            } else {
                y.rightChildNode = node;
            }
        } else {
            this.root = node;
        }
        setRed(node);
        insertFixUp(node);
    }

    public void insert(T key) {

        RBNode<T> node = new RBNode<T>(ColorEnum.BLACK, key, null, null, null);

        if (node != null) {
            insert(node);
        }
    }

    //红黑树删除操作
    public void delete(RBNode<T> node) {

        RBNode<T> child, parent, replace;
        ColorEnum color = ColorEnum.RED;
        if (node.leftChildNode != null && node.rightChildNode != null) {
            replace = successor(node);

            parent = parentOf(replace);
            child = replace.rightChildNode;
            color = colorOf(replace);

            if (node == parentOf(replace)) {
                parent = replace;
            } else {
                if (child != null) {
                    setParent(child, parentOf(replace));
                }
                replace.parentNode.leftChildNode = child;
                replace.rightChildNode = node.rightChildNode;
                setParent(node.rightChildNode, replace);
            }

            setParent(replace, parentOf(node));
            replace.leftChildNode = node.leftChildNode;
            setParent(node.leftChildNode, replace);
            setColor(replace, colorOf(node));

            if (parentOf(node) != null) {
                if (node.parentNode.leftChildNode == node) {
                    node.parentNode.leftChildNode = replace;
                } else {
                    node.parentNode.rightChildNode = replace;
                }
            } else {
                this.root = replace;
            }

        } else {

            if (node.leftChildNode != null) {
                replace = node.leftChildNode;
            } else {
                replace = node.rightChildNode;
            }

            parent = parentOf(node);

            if (parent != null) {
                if (parent.leftChildNode == node) {
                    parent.leftChildNode = replace;
                } else {
                    parent.rightChildNode = replace;
                }
            } else {
                this.root = replace;
            }
            setParent(replace, parent);

            color = colorOf(node);
            child = replace;
        }
        if (color == ColorEnum.BLACK) {
            deleteFixUp(child, parent);
        }

    }

    public void delete(T key) {
        RBNode<T> node;
        if ((node = search(key, this.root)) != null) {
            delete(node);
        }
    }

    //前序遍历
    public void preOrder(RBNode<T> node) {
        if (node != null) {
            System.out.print(node.key + " ");
            preOrder(node.leftChildNode);
            preOrder(node.rightChildNode);
        }
    }

    public void preOrder() {
        preOrder(this.root);
    }

    //中序遍历
    public void inOrder(RBNode<T> node) {
        if (node != null) {
            inOrder(node.leftChildNode);
            System.out.print(node.key + " ");
            inOrder(node.rightChildNode);
        }
    }

    public void inOrder() {
        inOrder(this.root);
    }

    //后序遍历
    public void postOrder(RBNode<T> node) {
        if (node != null) {
            postOrder(node.leftChildNode);
            postOrder(node.rightChildNode);
            System.out.print(node.key + " ");
        }
    }

    public void postOrder() {
        postOrder(this.root);
    }

    //打印红黑树
    public void print(RBNode<T> node, int direction) {
        if (node != null) {
            if (direction == 0) {
                log.debug("%2d(%s) is root\n", node.key, node.color == ColorEnum.BLACK ? "R" : "B");
            } else {
                log.debug("%2d(%s) is %s child 父节点 %2d\n", node.key, node.color == ColorEnum.BLACK ? "R" : "B",
                        direction == -1 ? "left" : "right", node.parentNode.key);
            }
            print(node.leftChildNode, -1);
            print(node.rightChildNode, 1);
        }
    }

    public void print() {
        print(this.root, 0);
    }
}
