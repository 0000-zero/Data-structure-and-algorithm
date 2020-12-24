package com.at.排序算法;

import java.util.Arrays;

/**
 * @author zero
 * @create 2020-12-12 16:12
 */
public class ShellSort {

    public static void main(String[] args) {

//		int[] arr = { 8, 9, 1, 7, 2, 3, 5, 4, 6, 0 };

        int[] arr = {90, 1, 4, 200, 34, 9, 0, 99, 8};


//		int[] arr = new int[80000000];
//		// 随机生成 80000 个数据
//		for (int i = 0; i < 80000000; i++) {
//			arr[i] = (int) (Math.random() * 800000000+800000000);// 生成80000个 在 [0,800000)之间的数
//		}
//
//
//		// 起始时间
//		long start = System.currentTimeMillis();
//
        shellSort(arr);
//		shellSort2(arr);
//
//		long end = System.currentTimeMillis();
//
//		System.out.println("Time:" + (end - start));

    }

    private static void shellSort2(int[] arr) {

        /********************移动式(插入排序)希尔排序*********************/

        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {

                int insertVal = arr[i];
                int insertIndex = i - gap;


                //Time:1148
//
                while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
                    arr[insertIndex + gap] = arr[insertIndex];
                    insertIndex -= gap;
                }
                insertIndex += gap;
                arr[insertIndex] = insertVal;
            }
        }

        System.out.println(Arrays.toString(arr));

    }

    private static void shellSort(int[] arr) {

        /********************交换式希尔排序*********************/

        /*
         * 希尔排序是把记录按下标的一定增量分组， 对每组使用直接插入排序算法排序；
         * 随着增量逐渐减少，每组包含的关键词越来越多，
         * 当增量减至1时，整个文件恰被分成一组，算法便终止
         *
         */

        int temp = 0;
        int count = 0;
        // gap 为每次的步长  gap/= 2 为分组数
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                // 遍历各组中所有的元素(共 5 组，每组两个元素)，步长为 5 即(8,3)(9,5)(1,4)(7,6)(2,0)
                for (int j = i - gap; j >= 0; j -= gap) {
                    if (arr[j] > arr[j + gap]) {
                        temp = arr[j];
                        arr[j] = arr[j + gap];
                        arr[j + gap] = temp;
                    }
                }
            }
            System.out.println("希尔排序第" + (++count) + "轮：" + Arrays.toString(arr));
        }

        System.out.println("希尔排序：" + Arrays.toString(arr));

        /***************** 希尔排序第一轮 ******************/


//		// 第一轮将 10 个数据分为 10/2=5 组 步长为 5   排序
//		for (int i = 5; i < arr.length; i++) {
//			// 遍历各组中所有的元素(共 5 组，每组两个元素)，步长为 5 即(8,3)(9,5)(1,4)(7,6)(2,0)
//			for (int j = i - 5; j >= 0; j -= 5) {
//				if (arr[j] > arr[j + 5]) {
//					temp = arr[j];
//					arr[j] = arr[j+5];
//					arr[j+5] = temp;
//				}
//			}
//		}
//		System.out.println("希尔排序第1轮："+Arrays.toString(arr));
//
//		/***************** 希尔排序第er轮 ******************/
//
//
//		// 第一轮将 10 个数据分为 10/2/2=2 组 步长为 2  排序
//		for (int i = 2; i < arr.length; i++) {
//			// 遍历各组中所有的元素(共 5 组，每组两个元素)，步长为 5 即(3,  1,  0,  9,  7)(5, 6,8, 4, 2)
//			for (int j = i - 2; j >= 0; j -= 2) {
//				if (arr[j] > arr[j + 2]) {
//					temp = arr[j];
//					arr[j] = arr[j+2];
//					arr[j+2] = temp;
//				}
//			}
//		}
//		System.out.println("希尔排序第2轮："+Arrays.toString(arr));
//
//
//		/***************** 希尔排序第三轮 ******************/
//
//
//		// 第一轮将 10 个数据分为 10/2/2/2=1 组 步长为 1  排序
//		for (int i = 1; i < arr.length; i++) {
//			// 遍历各组中所有的元素(共 5 组，每组两个元素)，步长为 1 即(0, 2, 1, 4, 3, 5, 7, 6, 9, 8)
//			for (int j = i - 1; j >= 0;j -= 1) {
//				if (arr[j] > arr[j + 1]) {
//					temp = arr[j];
//					arr[j] = arr[j+1];
//					arr[j+1] = temp;
//				}
//			}
//		}
//		System.out.println("希尔排序第3轮："+Arrays.toString(arr));

    }


}
