package com.at.查找算法;

import java.util.ArrayList;

/**
 * @author zero
 * @create 2020-12-13 12:52
 */
public class BinarySearch {
    public static void main(String[] args) {
//        int arr[] = {1, 8, 10, 89, 1000, 1000, 1000, 1000, 1234};

//		int i = binarySearch(arr, 0, arr.length, 1000);

        int[] arr = new int[100];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = i + 1;
        }

        ArrayList<Integer> list = binarySearch2(arr, 0, arr.length - 1, 19);

        System.out.println(list);
    }

    // 多个相同值得情况
    public static ArrayList<Integer> binarySearch2(int[] arr, int left, int right, int val) {

        System.out.println("0");

        if (left > right) {
            return new ArrayList<Integer>();
        }

        int mid = (left + right) / 2;
        int midVal = arr[mid];

        if (val > midVal) {// 向右查找
            return binarySearch2(arr, mid + 1, right, val);
        } else if (val < midVal) {
            return binarySearch2(arr, left, mid - 1, val);
        } else {
//			return mid;

            /*
             * 思路分析：
             * 1.在找到 mid 索引值时不马上返回
             * 2.向 mid 索引的左边扫描，将满足条件的元素下标存入一个集合
             * 3.向 mid索引的右边扫描，将满足条件的元素下标存入一个集合
             * 4.返回集合
             *
             */

            ArrayList<Integer> list = new ArrayList<Integer>();

            list.add(mid);

            // 向左
            int temp = mid - 1;

            while (true) {
                if (temp < 0 || arr[temp] != val) {
                    break;
                }
                list.add(temp);
                temp--;
            }

            // 向右
            temp = mid + 1;

            while (true) {
                if (temp > right || arr[temp] != val) {
                    break;
                }
                list.add(temp);
                temp++;
            }

            return list;

        }

    }

    public static int binarySearch(int[] arr, int left, int right, int val) {

        if (left > right) {
            return -1;
        }

        int mid = (left + right) / 2;
        int midVal = arr[mid];

        if (val > midVal) {// 向右查找
            return binarySearch(arr, mid + 1, right, val);
        } else if (val < midVal) {
            return binarySearch(arr, left, mid - 1, val);
        } else {
            return mid;
        }

    }


}
