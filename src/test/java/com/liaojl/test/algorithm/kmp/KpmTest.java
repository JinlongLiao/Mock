package com.liaojl.test.algorithm.kmp;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.constraints.NotNull;

public class KpmTest {
    private static final Logger log = LoggerFactory.getLogger(KpmTest.class);

    @Test
    public void test() {
        int size = 10000;
        String ps = "abb";
        String target = "1gg2abababcabaabbca0";
        long time1 = System.currentTimeMillis();

        for (int i = 0; i < size; i++) {
            String t1 = target;
            String p1 = ps;
            kmp(t1, p1);
        }
        long time2 = System.currentTimeMillis();
        long totle1 = time2 - time1;


        long time3 = System.currentTimeMillis();

        for (int i = 0; i < size; i++) {
            String t1 = target;
            String p1 = ps;
            t1.replace(p1, "");

        }
        long time4 = System.currentTimeMillis();
        long totle2 = time4 - time3;
        log.info("totle1:{}", totle1);
        log.info("totle2:{}", totle2);
    }

    /**
     * <blockquote>
     * <pre>
     * 1. 首先获取{@code ps}  的next[]
     * <p>
     * 例如 ：[0,1,0]
     * <p>
     * {@code target} caaab
     * {@code ps} aab
     * 1:caaab
     *    aab
     * 没有相等的移动一位
     * 2:caaab
     *    aab
     * 有两位相等 -> next[2]=0 为0时 +1
     * 3:caaab
     *     aab
     * 匹配成功
     *
     * </pre></blockquote>
     *
     * @param target 目标字符串
     * @param ps     匹配字符串
     */
    public int kmp(String target, String ps) {

        int[] next = getNext(ps);
        char[] psChars = ps.toCharArray();
        char[] targetChars = target.toCharArray();
        int index = -1;
        int i = 0;
        for (int j = 0; j < psChars.length; ) {
            if (i + j > targetChars.length - 1) {
                break;
            }
            if (targetChars[i + j] == psChars[j]) {
                ++j;
                if (j == psChars.length) {
                    index = i;
                }
            } else {
                i += next[j] == 0 ? 1 : next[j];
                j = 0;
            }
        }
        return index;
    }

    /**
     * <blockquote><pre>
     * 例如：ababbc 获取next[]
     * next[0] 默认为0
     * 1：ababbc
     *     ababbc
     *    00
     * 总共匹配0 获取移动量 next[0] 为0是 自动加一
     * 2：ababbc
     *      ababbc
     *    0012
     * 总共匹配2 获取移动量 next[2]=1
     * 3:ababbc
     *       ababbc
     *   00120
     * 总共匹配0 获取移动量 next[0] 为0是 自动加一
     * 4:ababbc
     *        ababbc
     *  001200
     *  结束
     * </pre></blockquote>
     *
     * @param ps
     * @return
     */
    public int[] getNext(@NotNull String ps) {
        int length = ps.length();
        char[] chars = ps.toCharArray();
        int[] next = new int[length];
        next[0] = 0;
        if (length > 1) {
            int i = 1;
            for (int j = 0; i + j < length; ) {
                if (chars[i + j] == chars[j]) {
                    next[i + j] = j + 1;
                    j++;
                } else {
                    i += next[j] == 0 ? 1 : next[j];
                    j = 0;
                }
            }
        }
        return next;
    }
}
