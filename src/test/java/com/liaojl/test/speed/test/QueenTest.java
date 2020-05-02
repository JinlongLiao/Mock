package com.liaojl.test.speed.test;

import org.junit.Test;

public class QueenTest {
    public static class Queen {
        private int[] column; //同栏是否有皇后，1表示有
        private int[] rup; //右上至左下是否有皇后
        private int[] lup; //左上至右下是否有皇后
        private int[] queen; //解答
        private int num; //解答编号
        private int size;

        /**
         * 初始化数据
         */
        public Queen(int size) {
            this.size = size;
            column = new int[size];
            rup = new int[(2 * size)];
            lup = new int[(2 * size)];
            for (int i = 0; i < size; i++) {
                column[i] = 0;
            }
            for (int i = 0; i < (2 * size); i++) {
                //初始定义全部无皇后
                rup[i] = lup[i] = 0;
            }
            queen = new int[size];
        }

        public void backtrack() {
            this.backtrack(0);
        }

        public void backtrack(int i) {
            if (!(i < size)) {
                showAnswer();
            } else {
                for (int j = 0; j < size; j++) {
                    if ((column[j] == 0) && (rup[i + j] == 0) && (lup[i - j + size] == 0)) {
                        //若无皇后
                        queen[i] = j; //设定为占用
                        column[j] = rup[i + j] = lup[i - j + size] = 1;
                        backtrack(i + 1);  //循环调用
                        column[j] = rup[i + j] = lup[i - j + size] = 0;
                    }
                }
            }
        }

        protected void showAnswer() {
            num++;
            System.out.println("\n解答" + num);
            for (int y = 0; y < size; y++) {
                for (int x = 0; x < size; x++) {
                    if (queen[y] == x) {
                        System.out.print("Q");
                    } else {
                        System.out.print(".");
                    }
                }
                System.out.println();
            }
        }
    }

    @Test
    public void test() {
        Queen queen = new Queen(8);
        queen.backtrack();
    }
}