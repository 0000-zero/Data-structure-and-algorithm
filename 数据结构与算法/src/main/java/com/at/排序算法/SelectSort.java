package com.at.排序算法;


/**
 * @author zero
 * @create 2020-12-12 15:35
 */
public class SelectSort {

    public static void main(String[] args) {

//		int[] arr = {101,34,119,1};

        //测试选择排序的速度
        int[] arr = new int[80000];
        //随机生成 80000 个数据
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random() * 800000);//生成80000个 在 [0,800000)之间的数
        }

        //起始时间
        long start = System.currentTimeMillis();

        selectSort(arr);
//		selectSort2(arr); // 错误的

        long end = System.currentTimeMillis();

//		System.out.println(Arrays.toString(arr));

        System.out.println("Time:" + (end - start));
    }

    private static void selectSort(int[] arr) {


        /*
         *
         * 第一次从arr[0]~arr[n-1]中选取最小值，与arr[0]交换，
         * 第二次从arr[1]~arr[n-1]中选取最小值，与arr[1]交换，
         * 第三次从arr[2]~arr[n-1]中选取最小值，与arr[2]交换，…，
         * 第i次从arr[i-1]~arr[n-1]中选取最小值，与arr[i-1]交换，…,
         * 第n-1次从arr[n-2]~arr[n-1]中选取最小值，与arr[n-2]交换，总共通过n-1次，
         * 得到一个按排序码从小到大排列的有序序列
         */


        //选择排序的时间复杂度  O(n^2)

//		int count = 0;

        for (int i = 0; i < arr.length - 1; i++) {

            int minIndex = i;
            int minVal = arr[minIndex];
            for (int j = i + 1; j < arr.length; j++) {
//				count++;
                if (minVal < arr[j]) {// > 从小到大    < 从大到小
                    minIndex = j;
                    minVal = arr[j];
                }
            }
            if (minIndex != i) {
                arr[minIndex] = arr[i];
                arr[i] = minVal;
            }

//			System.out.println("第"+(i +1)+"轮循环的结果："+Arrays.toString(arr));

        }

//		System.out.println("count:"+count);


        /******************第一轮********************/

//		//假设 起始时 0 索引处的值最小
//		int minIndex = 0;
//		int minVal = arr[minIndex];
//
//
//		//查找数组中最小的数及其对应的索引
//		for(int i = 1;i < arr.length;i++) {
//			if(minVal > arr[i]) {
//				minIndex = i;
//				minVal = arr[i];
//			}
//		}
//		int temp = 0;
//		//循环结束就找到了最小的数 将其放置在 arr[0] 处
//		temp = arr[0];
//		arr[0] = minVal;
//		arr[minIndex] = temp;
//
//
//		/******************第二轮********************/
//
//		minIndex = 1;
//		minVal = arr[minIndex];
//		//查找数组中最小的数及其对应的索引
//		for(int i = 2;i < arr.length;i++) {
//			if(minVal > arr[i]) {
//				minIndex = i;
//				minVal = arr[i];
//			}
//		}
//
//		//循环结束就找到了最小的数 将其放置在 arr[0] 处
//		temp = arr[1];
//		arr[1] = minVal;
//		arr[minIndex] = temp;
//
//
//		/******************第三轮********************/
//
//		minIndex = 2;
//		minVal = arr[minIndex];
//		//查找数组中最小的数及其对应的索引
//		for(int i = 3;i < arr.length;i++) {
//			if(minVal > arr[i]) {
//				minIndex = i;
//				minVal = arr[i];
//			}
//		}
//
//		//循环结束就找到了最小的数 将其放置在 arr[0] 处
//		temp = arr[2];
//		arr[2] = minVal;
//		arr[minIndex] = temp;

    }

    private static void selectSort2(int[] arr) { //慢


        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {

                if (arr[i] > arr[j]) {
                    int t = arr[i];
                    arr[i] = arr[j];
                    arr[j] = t;
                }
            }
//			System.out.println("第"+(i + 1)+"次的结果为："+Arrays.toString(arr));
        }
//		System.out.println(Arrays.toString(arr));
//		System.out.println("count:"+count);


    }


}
