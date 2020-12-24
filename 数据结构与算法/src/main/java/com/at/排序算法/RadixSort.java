package com.at.排序算法;


import java.util.Arrays;

/**
 * @author zero
 * @create 2020-12-12 19:55
 */
public class RadixSort {

    public static void main(String[] args) {


        int[] arr = {53, 3, 542, 748, 14, 214};


//		int[] arr = new int[8000000];
//		// 随机生成 80000 个数据
//		for (int i = 0; i < 8000000; i++) {
//			arr[i] = (int) (Math.random() * 80000000);// 生成80000个 在 [0,800000)之间的数
//		}
//
//		// 起始时间
//		long start = System.currentTimeMillis();

        radixSort(arr);

//		long end = System.currentTimeMillis();
//
//		System.out.println("Time:" + (end - start));

    }

    public static void radixSort(int[] arr) {

        /*
         * 将所有待比较数值统一为同样的数位长度，数位较短的数前面补零 然后，从最低位开始，依次进行一次排序 这样从最低位排序一直到最高位排序完成以后,
         * 数列就变成一个有序序列
         *
         */

        // 定义一个二维数组，表示 10 个同，每个同就是一个一维数组
        /*
         * 1.二维数组包含 10 个一维数组（0~9 的桶） 2.为防止放入数据的时候溢出将每个同的大小定义为 arr.length
         *
         * 3.基数排序就是用空间换时间的经典算法
         *
         */
        int[][] bucket = new int[10][arr.length];

        // 为记录每个桶中实际存放了多少数据，定义一个一维数组来记录各个桶的每次放入的数据个数
        int[] bucketElementCounts = new int[10];

        //获取最大数的位数
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        int maxLength = (max + "").length();


        for (int i = 0, n = 1; i < maxLength; i++, n *= 10) {

            for (int j = 0; j < arr.length; j++) {

                int digitOfElement = arr[j] / n % 10;

                // 将数据放入桶中
                bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
                bucketElementCounts[digitOfElement]++;

            }

            // 按照桶的顺序(一维数组的下标依次取出数据，放入原来数组)
            int index = 0;
            for (int k = 0; k < bucketElementCounts.length; k++) {

                // 如果桶中有数据，才将其放回原数组
                if (bucketElementCounts[k] != 0) {
                    // 循环该桶，将数据返回数组
                    for (int l = 0; l < bucketElementCounts[k]; l++) {
                        // 取出元素放入数组
                        arr[index++] = bucket[k][l];
                    }
                }

                // 将桶中数据返回原数组后需要将桶清空 bucketElementCounts[k] = 0
                bucketElementCounts[k] = 0;

            }

            System.out.println("第 " + (i + 1) + " 轮：" + Arrays.toString(arr));
        }

//		/***************** 第 1 轮 个位 *********************/
//
//		for (int j = 0; j < arr.length; j++) {
//			// 取出每个元素的各位的值 该值也表示将要放入第几个桶中
//			int digitOfElement = arr[j] % 10;
//
//			// 将数据放入桶中
//			bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
//			bucketElementCounts[digitOfElement]++;
//
//		}
//
//		// 按照桶的顺序(一维数组的下标依次取出数据，放入原来数组)
//		int index = 0;
//		for (int k = 0; k < bucketElementCounts.length; k++) {
//
//			// 如果桶中有数据，才将其放回原数组
//			if (bucketElementCounts[k] != 0) {
//				// 循环该桶，将数据返回数组
//				for (int l = 0; l < bucketElementCounts[k]; l++) {
//					// 取出元素放入数组
//					arr[index++] = bucket[k][l];
//				}
//			}
//
//			// 将桶中数据返回原数组后需要将桶清空 bucketElementCounts[k] = 0
//			bucketElementCounts[k] = 0;
//
//		}
//
//		System.out.println("第 1 轮：" + Arrays.toString(arr));
//
//		/****************** 第 2 轮 十位 ********************/
//
//		for (int j = 0; j < arr.length; j++) {
//			// 取出每个元素的各位的值 该值也表示将要放入第几个桶中
//			int digitOfElement = arr[j] / 10 % 10;
//
//			// 将数据放入桶中
//			bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
//			bucketElementCounts[digitOfElement]++;
//
//		}
//
//		// 按照桶的顺序(一维数组的下标依次取出数据，放入原来数组)
//		index = 0;
//		for (int k = 0; k < bucketElementCounts.length; k++) {
//
//			// 如果桶中有数据，才将其放回原数组
//			if (bucketElementCounts[k] != 0) {
//				// 循环该桶，将数据返回数组
//				for (int l = 0; l < bucketElementCounts[k]; l++) {
//					// 取出元素放入数组
//					arr[index++] = bucket[k][l];
//				}
//			}
//			bucketElementCounts[k] = 0;
//		}
//
//		System.out.println("第 2 轮：" + Arrays.toString(arr));
//
//		/***************** 第 3 轮 百位 *********************/
//
//		for (int j = 0; j < arr.length; j++) {
//			// 取出每个元素的各位的值 该值也表示将要放入第几个桶中
//			int digitOfElement = arr[j] / 10 % 10;
//
//			// 将数据放入桶中
//			bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
//			bucketElementCounts[digitOfElement]++;
//
//		}
//
//		// 按照桶的顺序(一维数组的下标依次取出数据，放入原来数组)
//		index = 0;
//		for (int k = 0; k < bucketElementCounts.length; k++) {
//
//			// 如果桶中有数据，才将其放回原数组
//			if (bucketElementCounts[k] != 0) {
//				// 循环该桶，将数据返回数组
//				for (int l = 0; l < bucketElementCounts[k]; l++) {
//					// 取出元素放入数组
//					arr[index++] = bucket[k][l];
//				}
//			}
//
//			bucketElementCounts[k] = 0;
//
//		}
//
//		System.out.println("第 3 轮：" + Arrays.toString(arr));
//
    }

}
