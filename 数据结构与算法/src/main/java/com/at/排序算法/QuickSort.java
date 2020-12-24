package com.at.排序算法;

import java.util.Arrays;

/**
 * @author zero
 * @create 2020-12-12 14:13
 */
public class QuickSort {

    public static void main(String[] args) {


//		int[] arr = {-9,78,23,0,-11,-50,-10,90,-40,9};
//		int[] arr = {5,7,-1,19,10,0,12,-3,-5};
        int[] arr = {12, 32, 41, 68, 8, 28, 37, 18, 93, 61};

//		int[] arr = new int[8000000];
//		// 随机生成 80000 个数据
//		for (int i = 0; i < 8000000; i++) {
//			arr[i] = (int) (Math.random() * 800000000+800000000);// 生成80000个 在 [0,800000)之间的数
//		}

        // 起始时间
//		long start = System.currentTimeMillis();
//
        quick(arr, 0, arr.length - 1);

//		long end = System.currentTimeMillis();
//
//		System.out.println("Time:" + (end - start));
        // Time:31686 Time:11548
//		System.out.println(Arrays.toString(arr));


    }


    public static void quick(int[] arr, int left, int right) {

        int l = left;// 左下标
        int r = right;// 右下标
        int pivot = arr[(left + right) / 2];// 中轴值

        int temp = 0;

        while (l < r) {

            // 在 pivot 的左边一直找，直到找到大于等于 pivot 的值才退出
            while (arr[l] < pivot) {
                l++;
            }

            // 在 pivot 的右边一直找，直到找到小于等于 pivot 的值才退出
            while (arr[r] > pivot) {
                r--;
            }

            // 如果 l>=r 说明 pivot 的左右两边的值，已经按照左边全是小于 pivot 的值，右边全是大于等于 pivot 的值
            if (l >= r) {
                break;
            }

            temp = arr[r];
            arr[r] = arr[l];
            arr[l] = temp;

            // 交换完后 如果 arr[r] = pivot 让 l 向后移动
            if (arr[r] == pivot) {
                l++;
            }

            // 交换完后 如果 arr[l] = pivot 让 r 向前移动
            if (arr[l] == pivot) {
                r--;
            }
        }

        // 如果 l=r 必须 l++ r-- 否则会出现栈溢出
        if (l == r) {
            l++;
            r--;
        }

        // 向右递归
        if (left < r) {
            quick(arr, left, r);
            System.out.println(Arrays.toString(arr));
        }

        // 向左递归
        if (right > l) {
            quick(arr, l, right);
            System.out.println(Arrays.toString(arr));
        }

// 		System.out.println(Arrays.toString(arr));
    }

}
