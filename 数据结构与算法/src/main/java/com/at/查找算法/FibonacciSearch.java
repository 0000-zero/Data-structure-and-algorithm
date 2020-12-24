package com.at.查找算法;

import java.util.Arrays;

/**
 * @author zero
 * @create 2020-12-14 12:36
 */
public class FibonacciSearch {
    private static int max = 20;


    public static void main(String[] args) {

        int[] arr = {1, 8, 10, 89, 1000, 1234};

        int i = fibSearch(arr, 10);

        System.out.println(i);

    }

    /**
     * @param arr
     * @param key 需要查找的值
     * @return
     */
    public static int fibSearch(int[] arr, int key) {

        System.out.println("0");

        int low = 0;
        int height = arr.length - 1;

        int k = 0;//表示斐波那契分隔数值的下标

        int mid = 0;//存放 mid 值

        int[] f = fib();//获取斐波那契数列

        //获取斐波那契的分割下标
        while (height > f[k] - 1) {
            k++;
        }

        //因为 f[k] 的值可能大于 arr 数组的长度，因此需要使用 Arrays 类构建一个新的数组 不足的部分用 0 填充
        int temp[] = Arrays.copyOf(arr, f[k]);//arr：原始数组  f[k]：新的长度

        //实际是用 arr 数组的最后一个元素填充
        for (int i = height + 1; i < temp.length; i++) {
            temp[i] = arr[height];
        }

        //寻找 key 值
        while (low <= height) {//满足该条件就一直找
            mid = low + f[k - 1] - 1;

            if (key < temp[mid]) {
                height = mid - 1;

                /*
                 * 为什么是 k--
                 * 说明：
                 * 	1.全部元素 = 前面的元素 + 后边的元素
                 * 	2.f[k] = f[k-1] + f[k-2]
                 * 	因为前面有 f[k-1] 个元素，所以可以继续拆分 f[k-1] = f[k-2]+f[k-3]
                 * 	即在 f[k-1] 的前面继续查找k--  即下次循环 mid = f[k-1-1] -1
                 */
                k--;
            } else if (key > temp[mid]) {

                low = mid + 1;

                /*
                 * 为什么是 k-=2
                 * 说明：
                 * 	1.全部元素 = 前面的元素 + 后边的元素
                 * 	2.f[k] = f[k-1] + f[k-2]
                 * 	因为前面有 f[k-2] 个元素，所以可以继续拆分 f[k-2] = f[k-3]+f[k-4]
                 * 	即在 f[k-2] 的前面继续查找 k-=2  即下次循环 mid = f[k-1-2] -1
                 */

                k -= 2;
            } else {//找到
                //需要确定，返回的是那个下标
                if (mid <= height) {
                    return mid;
                } else {
                    return height;
                }
            }

        }
        return -1;

    }


    //因为后面要使用 mid = low + F(k-1)-1 F 表示斐波那契数列
    //获取一个斐波那契数
    public static int[] fib() {
        int[] f = new int[max];
        f[0] = 1;
        f[1] = 1;

        for (int i = 2; i < max; i++) {
            f[i] = f[i - 1] + f[i - 2];
        }

        return f;

    }

}
