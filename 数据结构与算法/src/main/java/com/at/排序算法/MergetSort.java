package com.at.排序算法;


import java.util.Arrays;

/**
 * @author zero
 * @create 2020-12-12 18:43
 */
public class MergetSort {
    public static void main(String[] args) {

//		int[] arr = {8,4,5,7,1,3,6,2,0,9,34,-29};
//
        int[] arr = { 90, 1, 4, 200, 34, 9, 0, 99, 8, 7 };
        int[] temp = new int[arr.length];

//
//		mergetSort(arr, 0, arr.length - 1, temp);
//
//		System.out.println(Arrays.toString(arr));

//		int[] arr = new int[8000000];
//		int[] temp = new int[8000000];
//		// 随机生成 80000 个数据
//		for (int i = 0; i < 8000000; i++) {
//			arr[i] = (int) (Math.random() * 800000000+800000000);// 生成80000个 在 [0,800000)之间的数
//		}
//
//		// 起始时间
//		long start = System.currentTimeMillis();
//
        mergetSort(arr, 0, arr.length - 1, temp);
//
//		long end = System.currentTimeMillis();
//
//		System.out.println("Time:" + (end - start));


    }

    /**
     * 分 + 合
     * @param arr
     * @param left
     * @param right
     * @param temp
     */
    public static void mergetSort(int[] arr,int left,int right,int[] temp) {

        if(left < right) {
            int mid = (left + right) / 2;//中间索引

            //向左递归
            mergetSort(arr, left, mid, temp);

            //向右递归
            mergetSort(arr, mid + 1, right, temp);

            //每次分完都合并
            merget(arr, left, mid, right, temp);

        }



    }

    /**
     * 合并的方法
     *
     * @param arr 原始数组
     * @param left	左边有序序列的初始索引
     * @param mid	中间索引
     * @param right 右边索引
     * @param temp 做中转的数组
     */
    public static void merget(int[] arr,int left,int mid,int right,int[] temp) {

        int i = left;//初始化 i 左边有序序列的初始索引
        int j = mid + 1;//初始化 j 右边有序序列的初始索引
        int t = 0;//指向 temp 数组的当前索引

        //第一步
        //先将左右两边(有序)的数据按照规则填充到 temp 数组  直到左右两边的有序序列有一边处理完毕
        while(i <= mid && j <= right) {
            if(arr[i] <= arr[j]) {
                //左边小于右边的，将左边的填充到 temp 数组中 t,i 分别后移
                temp[t] = arr[i];
                i++;
                t++;
            }else {
                //反之 左边大于右边的，将右边的填充到 temp 数组中 t,j 分别后移
                temp[t] = arr[j];
                j++;
                t++;
            }
        }

        //第二步
        //将剩余数据的一边的数据依次全部填充到temp中
        while(i <= mid) {//左边有剩余
            temp[t] = arr[i];
            t++;
            i++;
        }
        while(j <= right) {//右边有剩余
            temp[t] = arr[j];
            t++;
            j++;
        }

        //第三步
        //将temp数组的元素拷贝到 arr 数组中     注意:并不是每次都拷贝所有

        t = 0;
        int tempLeft = left;

        /*
         * 第一次合并：tempLeft = 0,right = 1
         * 第二次合并：tempLeft = 2,right = 3
         * 第三次合并：tempLeft = 0,right = 3
         *
         * 第四次合并：tempLeft = 4,right = 5
         * 第四次合并：tempLeft = 6,right = 7
         * 第四次合并：tempLeft = 4,right = 7
         *
         * 第四次合并：tempLeft = 0,right = 7
         *
         */

        while(tempLeft <= right) {
            arr[tempLeft] = temp[t];
            t++;
            tempLeft++;
        }

        System.out.println(Arrays.toString(arr));
    }

}
