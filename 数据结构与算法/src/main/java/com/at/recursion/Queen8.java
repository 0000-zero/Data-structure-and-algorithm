package com.at.recursion;

/**
 * @author zero
 * @create 2020-12-12 13:02
 */
public class Queen8 {


    static int max = 8;
    static int[] arr = new int[max];

    static int count = 0;
    static int judgeCount = 0;


    public static void main(String[] args) {

        Queen8 queen8 = new Queen8();
        queen8.check(0);

        System.out.println("一共有"+count+"种解法");
        System.out.println("一共判断了"+judgeCount+"次");

    }

    public void check(int n) {
        if (n == max) {
            print();
            return;
        }

        for (int i = 0; i < max; i++) {
            arr[n] = i;
            if (judge(n)) {
                check(n + 1);
            }
        }

    }

    //放置第n个皇后
    public boolean judge(int n) {
        judgeCount++;
        for (int i = 0; i < n; i++) {
            //arr[n] == arr[i] 同列
            //Math.abs(n-i)==Math.abs(arr[n]-arr[i]) 对角线
            if (arr[n] == arr[i] || Math.abs(n - i) == Math.abs(arr[n] - arr[i])) {
                return false;
            }
        }
        return true;
    }

    public void print() {
        count++;
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

}

