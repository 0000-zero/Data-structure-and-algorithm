package com.at.常用算法;

/**
 * @author zero
 * @create 2020-12-21 19:12
 */
public class 动态规划 {

    /*
    动态规划算法介绍

        动态规划(Dynamic Programming)算法的核心思想是：将大问题划分为小问题进行解决，从而一步步获取最优解的处理算法

        动态规划算法与分治算法类似，其基本思想也是将待求解问题分解成若干个子问题，先求解子问题，然后从这些子问题的解得到原问题的解。

        与分治法不同的是，适合于用动态规划求解的问题，经分解得到子问题往往不是互相独立的。 ( 即下一个子阶段的求解是建立在上一个子阶段的解的基础上，进行进一步的求解 )

        动态规划可以通过填表的方式来逐步推进，得到最优解.

     */

    /*
    算法的主要思想，利用动态规划来解决。
    每次遍历到的第i个物品，根据w[i]和v[i]来确定是否需要将该物品放入背包中。
    即对于给定的n个物品，设v[i]、w[i]分别为第i个物品的价值和重量，C为背包的容量。
    再令v[i][j]表示在前i个物品中能够装入容量为j的背包中的最大价值。
    则我们有下面的结果：
        (1)  v[i][0]=v[0][j]=0; //表示 填入表 第一行和第一列是0
        (2) 当w[i]> j 时：v[i][j]=v[i-1][j]  
            // 当准备加入新增的商品的容量大于 当前背包的容量时，就直接使用上一个单元格的装入策略
        (3) 当j>=w[i]时： v[i][j]=max{v[i-1][j], v[i]+v[i-1][j-w[i]]}  
            // 当 准备加入的新增的商品的容量小于等于当前背包的容量,
            // 装入的方式:
            v[i-1][j]： 就是上一个单元格的装入的最大值
            v[i] : 表示当前商品的价值
            v[i-1][j-w[i]] ： 装入i-1商品，到剩余空间j-w[i]的最大值
            当j>=w[i]时： v[i][j]=max{v[i-1][j], v[i]+v[i-1][j-w[i]]} :
     */

    public static void main(String[] args) {

        //物品的总重量
        int[] w = {1,4,3};

        //物品的价值
        int[] val = {1500,3000,2000};

        //背包的容量
        int m = 4;
        //物品的个数
        int n = val.length;

        int[][] v = new int[n+1][m+1];

        //记录存放的商品的详情
        int[][] path = new int[n+1][m+1];

        // 处理第一行第一列
        for (int i = 0; i < v.length; i++) {
            v[i][0] = 0;
        }
        for (int i = 0; i < v[0].length; i++) {
            v[0][i] = 0;
        }


        for (int i = 1; i < v.length; i++) {
            for (int j = 1; j < v[i].length; j++) {
                if(w[i-1] > j){
                    //当前商品的重量大与背包的容量 则使用对应的上一个策略
                    v[i][j] = v[i-1][j];
                }else{
                    //背包的容量大于要存放的商品的容量
                    //v[i-1][j-w[i-1]] 上一个策略下在当前有效空间可以存放的最大价值的策略
//                    v[i][j] = Math.max(v[i-1][j],val[i-1]+v[i-1][j-w[i-1]]);
                    if(v[i-1][j] < (val[i-1] + v[i-1][j-w[i-1]])){
                        v[i][j] = val[i-1] + v[i-1][j-w[i-1]];
                        path[i][j] = 1;
                    }else{
                        v[i][j] = v[i-1][j];
                    }
                }
            }
        }




        for (int i = 0; i < v.length; i++) {
            for (int j = 0; j < v[i].length; j++) {
                System.out.print(v[i][j]+" ");
            }
            System.out.println();
        }

        System.out.println("-------------------------------------");

//        for (int i = 0; i < path.length; i++) {
//            for (int j = 0; j < path[i].length; j++) {
//                System.out.print(path[i][j] + " ");
//            }
//            System.out.println();
//        }
        int i = path.length-1;
        int j = path[0].length-1;
        while (i > 0 && j > 0){
            if(path[i][j] == 1){
                System.out.printf("第%d个商品放入到背包中\n",i);
                j = j-w[i-1];
            }
            i--;
        }



    }


}
