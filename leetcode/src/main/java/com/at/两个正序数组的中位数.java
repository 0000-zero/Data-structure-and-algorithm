package com.at;


import java.util.Arrays;

/**
 * @author zero
 * @create 2020-12-17 10:16
 */
public class 两个正序数组的中位数 {

    /*
            定两个大小为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的中位数。

            进阶：你能设计一个时间复杂度为 O(log (m+n)) 的算法解决此问题吗？

             

            示例 1：

            输入：nums1 = [1,3], nums2 = [2]
            输出：2.00000
            解释：合并数组 = [1,2,3] ，中位数 2
            示例 2：

            输入：nums1 = [1,2], nums2 = [3,4]
            输出：2.50000
            解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5
            示例 3：

            输入：nums1 = [0,0], nums2 = [0,0]
            输出：0.00000
            示例 4：

            输入：nums1 = [], nums2 = [1]
            输出：1.00000
            示例 5：

            输入：nums1 = [2], nums2 = []
            输出：2.00000
             

            提示：

            nums1.length == m
            nums2.length == n
            0 <= m <= 1000
            0 <= n <= 1000
            1 <= m + n <= 2000
            -106 <= nums1[i], nums2[i] <= 106

     */

    public static void main(String[] args) {

        int[] nums1 = {1, 3, 9};
        int[] nums2 = {2, 9, 10};

        double medianSortedArrays = findMedianSortedArrays1(nums1, nums2);
        System.out.println(medianSortedArrays);

    }


    //有错
    public static double findMedianSortedArrays1(int[] nums1, int[] nums2) {

        int r = 0;
        int l = 0;

        //一共有count个元素 count为奇数 那么结果为 下标为count/2 的那个元素 为偶数结果为 (count/2 + count/2-1)/2
        int count = nums1.length + nums2.length;
        int mid = (nums1.length + nums2.length) / 2;



        while (r < nums1.length && l < nums2.length) {

            if (mid == (r + l)) {
                break;
            }

            if (nums1[r] > nums2[l]) {
                l++;
            } else {
                r++;
            }


        }


        while (r < nums1.length) {

            if (mid == (r + l)) {
                break;
            }
            r++;
        }

        while (l < nums2.length) {
            if (mid == (r + l)) {
                break;
            }
            l++;

        }

        double res;

        if (count % 2 == 0) {
            //偶数
            res = (nums1[r] + nums2[l]) / 2.0;

        } else {
            //奇数
            res = Math.min(nums1[r], nums2[l]);
        }


        return res;

    }


    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {

        int r = 0;
        int l = 0;
        int index = 0;
        int[] arr = new int[nums1.length + nums2.length];

        while (r < nums1.length && l < nums2.length) {
            if (nums1[r] > nums2[l]) {
                arr[index] = nums2[l];
                index++;
                l++;
            } else {
                arr[index] = nums1[r];
                index++;
                r++;
            }
        }

        while (r < nums1.length) {
            arr[index] = nums1[r];
            r++;
            index++;
        }

        while (l < nums2.length) {
            arr[index] = nums2[l];
            index++;
            l++;
        }

        int mid = arr.length / 2;

        return arr.length % 2 == 0 ? (arr[mid] + arr[mid - 1]) / 2.0 : arr[mid];


    }


}
