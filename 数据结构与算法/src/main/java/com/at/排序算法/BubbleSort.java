package com.at.排序算法;


/**
 * @author zero
 * @create 2020-12-12 14:04
 */
public class BubbleSort {

    public static void main(String[] args) {

//		int[] arr = { 3, 9, -1, 10, -2 };
//		int[] arr = { 3, 9, -1, 10, 20 };
//		int[] arr = { 3, 9, 11, 12, 20 };

        // 冒泡排序的时间复杂度为 O(n^2)

        //测试冒泡排序的速度
        int[] arr = new int[80000];
        //随机生成 80000 个数据
        for(int i = 0;i < 80000;i++) {
            arr[i] = (int)(Math.random()*800000);//生成80000个 在 [0,800000)之间的数
        }

        //起始时间
        long start = System.currentTimeMillis();


        int count = 0;

//		boolean flag = false;//标识变量，表示是否进行过交换

        // 外层循环的次数为 数组长度-1
        for (int i = 0; i < arr.length - 1; i++) {
            boolean flag = false;//标识变量，表示是否进行过交换
            // 内存循环的次数依次减少，每次大的循环都会将最大的数排在最右侧，所以在下次排序是就无需比较这些数
            for (int j = 0; j < arr.length - 1 - i; j++) {
//				count++;


                // 如果为逆序(从左至右)则调换两者的位置
                if (arr[j] > arr[j + 1]) {

                    //如果进行过交换则将标识变量置为false
                    flag = true;

                    int temp = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = temp;
                }
            }

            if(!flag) {//如果 flag 为false 则表示在一次的的循环中没有交换一次数据  则可以表明该数组已经为有序排列了就无需再次比较
                break;
            } /*
             * else { flag = false;//重置 flag 进行下次判断 }
             */

//			System.out.println("第" + (i + 1) + "轮循环结果：" + Arrays.toString(arr));
        }

        //结束时间
        long end = System.currentTimeMillis();

//		System.out.println(Arrays.toString(arr));
//		System.out.println("一共比较了：" + count + "次");
        System.out.println("共用时："+(end - start));

    }



}
