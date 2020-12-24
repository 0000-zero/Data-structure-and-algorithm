package com.at.常用算法;


import java.util.Arrays;

/**
 * @author zero
 * @create 2020-12-22 13:43
 */


public class KMP算法 {

    //https://www.cnblogs.com/ZuoAndFutureGirl/p/9028287.html


    public static void main(String[] args) {

        String str1 = "BBC ABCDAB ABCDABCDABDE";
        String str2 = "ABCDABD";//ABCDABD

        System.out.println(kmp(str1, str2));

        System.out.println(kmpSearch(str1, str2));

    }


    public static int[] getNext(String str) {

        //通过str 获取 next匹配数组
        int[] next = new int[str.length()];
        next[0] = -1;

        int k = -1;
        int i = 0;

        while (i < str.length() - 1) {

            if (k == -1 || str.charAt(i) == str.charAt(k)) {
                i++;
                k++;


//                next[i] = k;

                //优化
                if (str.charAt(i) != str.charAt(k)) {
                    next[i] = k;
                } else {
                    next[i] = next[k];
                }
            } else {
                k = next[k];
            }

        }

        return next;
    }

    public static int kmpSearch(String s, String p) {

        int[] next = getNext(p);

        System.out.println(Arrays.toString(next));

        int i = 0;
        int j = 0;

        while (i < s.length() && j < p.length()) {
            if (j == -1 || s.charAt(i) == p.charAt(j)) {
                i++;
                j++;
            } else {
                j = next[j];
            }
        }

        if (j == p.length()) {
            return i - j;
        } else {
            return -1;
        }

    }

    public static int kmp(String str1, String str2) {

        int[] next = kmpNext(str2);
        System.out.println("next:" + Arrays.toString(next));

        for (int i = 0, j = 0; i < str1.length(); i++) {

            while (j > 0 && str1.charAt(i) != str2.charAt(j)) {
                j = next[j - 1];
            }

            if (str1.charAt(i) == str2.charAt(j)) {
                j++;
            }

            if (j == str2.length()) {
                return i - j + 1;
            }
        }

        return -1;


    }


    public static int[] kmpNext(String dest) {

        int next[] = new int[dest.length()];
        next[0] = 0;


        for (int i = 1, j = 0; i < dest.length(); i++) {

            //当 dest.charAt(i) != dest.charAt(j) 时则需要到 next数组中从后向前寻找一个与i处字符相同的，没有则为0
            while (j > 0 && dest.charAt(i) != dest.charAt(j)) {
                j = next[j - 1];
            }

            if (dest.charAt(i) == dest.charAt(j)) {
                j++;
            }
            next[i] = j;

        }


        return next;

    }

    /**
     * 暴力匹配算法
     * 如果用暴力匹配的思路，并假设现在str1匹配到 i 位置，子串str2匹配到 j 位置，则有:
     * 如果当前字符匹配成功（即str1[i] == str2[j]），则i++，j++，继续匹配下一个字符
     * 如果失配（即str1[i]! = str2[j]），令i = i - (j - 1)，j = 0。相当于每次匹配失败时，i 回溯，j 被置为0。
     * 用暴力方法解决的话就会有大量的回溯，每次只移动一位，若是不匹配，移动到下一位接着判断，浪费了大量的时间。(不可行!)
     *
     * @param str1
     * @param str2
     * @return
     */
    public static int violenceMatch(String str1, String str2) {

        int i = 0;
        int j = 0;

        while (i < str1.length() && j < str2.length()) {

            if (str1.charAt(i) == str2.charAt(j)) {
                i++;
                j++;
            } else {
                i = i - (j - 1);
                j = 0;
            }
        }

        if (j == str2.length()) {
            return i - j;
        } else {
            return -1;
        }

    }


}
