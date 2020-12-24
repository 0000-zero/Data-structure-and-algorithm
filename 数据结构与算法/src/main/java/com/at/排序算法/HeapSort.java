package com.at.排序算法;

import java.util.Arrays;

/**
 * @author zero
 * @create 2020-12-15 18:17
 */
public class HeapSort {
    public static void main(String[] args) {

//        int arr[] = {4, 6, 8, 5, 9};
        int[] arr = {1, 9, 8, 3, 0, -1, 2, 4, 7};

        /*
            堆排序的基本思想是：
            将待排序序列构造成一个大顶堆
            此时，整个序列的最大值就是堆顶的根节点。
            将其与末尾元素进行交换，此时末尾就为最大值。
            然后将剩余n-1个元素重新构造成一个堆，这样会得到n个元素的次小值。如此反复执行，便能得到一个有序序列了。
         */

        heapSort(arr);

        System.out.println(Arrays.toString(arr));

    }

    public static void heapSort(int[] arr) {

        //将待排序序列构造成一个大顶堆
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            heap(arr, i, arr.length);
        }

        int temp = 0;
        //交换 此时根节点为最大的节点 将其与末尾元素进行交换，让末尾为最大值
        for (int j = arr.length - 1; j > 0; j--) {
            temp = arr[0];
            arr[0] = arr[j];
            arr[j] = temp;

            //继续调整
            heap(arr, 0, j);
        }


    }

    /**
     * @param arr
     * @param i      调整的起始位置
     * @param length 需要调整的长度
     */
    public static void heap(int[] arr, int i, int length) {

        int temp = arr[i];

        //从树的最后一个非叶子节点(arr.length/2-1) 开始调整从下往上
        for (int k = 2 * i + 1; k < length; k = 2 * k + 1) {

            //如果当前节点的右节点大于左节点 则让k指向右节点
            if (k + 1 < length && arr[k + 1] > arr[k]) {
                k++;
            }

            //叶子节点大于非叶子节点，叶子节点上移，成为非叶子节点
            if (arr[k] > temp) {
                arr[i] = arr[k];
                i = k;
            } else {
                break;
            }

        }

        arr[i] = temp;

    }


}
