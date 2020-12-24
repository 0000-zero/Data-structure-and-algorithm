package com.at.查找算法;


/**
 * @author zero
 * @create 2020-12-13 13:05
 */
public class InterpolationSearch {

    //插值查找算法

    public static void main(String[] args) {

//        int[] arr = {1,2,3,4,5,6,7,8,9,10};

        int[] arr = new int[100];

        for(int i = 0;i < arr.length;i++) {
            arr[i] = i + 1;
        }

        int i = binarySearch(arr, 0, arr.length - 1, 80);

        System.out.println(i);

        int i1 = interpolationSearch(arr, 0, arr.length - 1, 10);
        System.out.println(i1);


    }

    //插值查找
    public static int interpolationSearch(int[] arr, int left, int right, int val) {
        System.out.println("interpolationSearch~~~~");

        if (left > right || val < arr[left] || val > arr[right]) {
            return -1;
        }

        int mid = left + (right - left) * (val - arr[left]) / (arr[right] - arr[left]);

        if (val < arr[mid]) {
            return interpolationSearch(arr, left, mid - 1, val);
        } else if (val > arr[mid]) {
            return interpolationSearch(arr, mid + 1, right, val);
        } else {
            return mid;
        }

    }

    //二分查找
    public static int binarySearch(int[] arr, int left, int right, int val) {

        System.out.println("binarySearch~~~~");

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
