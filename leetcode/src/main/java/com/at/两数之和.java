package com.at;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zero
 * @create 2020-12-15 19:39
 */
public class 两数之和 {

    /*
        给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。

        你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。



        示例:

        给定 nums = [2, 7, 11, 15], target = 9

        因为 nums[0] + nums[1] = 2 + 7 = 9
        所以返回 [0, 1]

     */
    public static void main(String[] args) {

        int[] arr = {2, 7, 11, 15};


//        System.out.println(Arrays.toString(res(arr, 22)));
        System.out.println(Arrays.toString(res2(arr, 9)));


    }

    public static int[] res2(int arr[], int target) {

        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < arr.length; i++) {
            int r = target - arr[i];
            if (map.containsKey(r)) {
                return new int[]{map.get(r),i};
            }
            map.put(arr[i], i);
        }


        return null;

    }

    public static int[] res(int[] arr, int target) {

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                if ((target - arr[i]) == arr[j]) {
                    return new int[]{i, j};
                }
            }
        }

        return null;
    }


}
