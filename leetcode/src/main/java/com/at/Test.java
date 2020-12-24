package com.at;

import java.util.Arrays;

/**
 * @author zero
 * @create 2020-12-23 22:05
 */
public class Test {
    public static void main(String[] args) {


        int[][] arr = {{1, 2, 8, 9}, {2, 4, 9, 12}, {4, 7, 10, 13}, {6, 8, 11, 15}};

//        for (int[] ints : arr) {
//            System.out.println(Arrays.toString(ints));
//        }
//
        System.out.println(Find1(7, arr));

//        int a[] = {1, 2, 8, 9};
//        System.out.println(binarySearch(a,9 ,0, arr.length - 1));

    }

    public static boolean Find(int target, int[][] array) {

        for (int[] ints : array) {
            for (int anInt : ints) {
                if (anInt == target) {
                    return true;
                }
            }
        }

        return false;
    }

    public static boolean Find1(int target, int[][] array) {

        if (array == null || array.length == 0 || array[0].length == 0) {
            return false;
        }

        int hang = 0;
        int lie = array.length - 1;

        while (hang < array[0].length && lie >= 0) {
            //目标大下移 目标小左移
            int val = array[hang][lie];
            if (target == val) {
                return true;
            } else if (target > val) {
                hang++;
            } else {
                lie--;
            }

        }


        return false;
    }


}
