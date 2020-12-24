package com.at.常用算法;

/**
 * @author zero
 * @create 2020-12-20 13:27
 */
public class 二分查找 {

    public static void main(String[] args) {


        int[] arr = {1, 3, 8, 10, 11, 67, 100};

//        System.out.println(binarySearch(arr, 0, arr.length - 1, 90));
//        System.out.println(binarySearch1(arr, 0, arr.length - 1, 100));
        System.out.println(binarySearch2(arr, 0, arr.length-1, 1000));

    }

    //非递归版 二分查找
    public static int binarySearch2(int[] arr, int left, int right, int val) {

        int mid = 0;

        while (left <= right) {

            mid = (left + right) / 2;

            if (val > arr[mid]) {
                left = mid + 1;
            } else if (val < arr[mid]) {
                right = mid - 1;
            } else {
                return mid;
            }

        }

        return -1;

    }

    //递归版改进(仅当分布均匀时才能体现)
    public static int binarySearch1(int arr[], int left, int right, int val) {

        if (left > right || val < arr[left] || val > arr[right]) {
            return -1;
        }

        int mid = left + (right - left) * (val - arr[left]) / (arr[right] - arr[left]);

        if (val > arr[mid]) {
            return binarySearch1(arr, mid + 1, right, val);
        } else if (val < arr[mid]) {
            return binarySearch1(arr, right, mid - 1, val);
        } else {
            return mid;
        }

    }

    //递归 二分查找
    public static int binarySearch(int[] arr, int left, int right, int val) {

        if (left > right) {
            return -1;
        }

        int mid = (left + right) / 2;

        if (val > arr[mid]) {
            return binarySearch(arr, mid + 1, right, val);
        } else if (val < arr[mid]) {
            return binarySearch(arr, left, mid - 1, val);
        } else {
            return mid;
        }

    }


}
