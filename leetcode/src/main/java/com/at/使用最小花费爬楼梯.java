package com.at;

/**
 * @author zero
 * @create 2020-12-21 20:44
 */
public class 使用最小花费爬楼梯 {
    public static void main(String[] args) {


        int[] cost = {5, 10, 15,20,25,30};

        System.out.println(minCostClimbingStairs1(cost));
        System.out.println(minCostClimbingStairs(cost));


    }

    public static int minCostClimbingStairs1(int[] cost){


        //到达第一步第二步台阶消耗0

        int d1 = 0; //到达前一步最小值
        int d2 = 0; //到达前两步最小值

        for (int i = 2; i <= cost.length; i++) {
            //最小 值为到达前一步台阶的或前两步加上在前一步台阶或在前两部台阶的消耗值中的最小值
            int curr = d1 + cost[i - 1] > d2 + cost[i - 2] ? d2 + cost[i - 2] :d1 + cost[i - 1];
            d2 = d1;
            d1 = curr;
        }

        return d1;
    }

    public static int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        int[] dp = new int[n + 1];
        dp[0] = dp[1] = 0;
        for (int i = 2; i <= n; i++) {
            dp[i] = Math.min(dp[i - 1] + cost[i - 1], dp[i - 2] + cost[i - 2]);
        }
        return dp[n];
    }


}
