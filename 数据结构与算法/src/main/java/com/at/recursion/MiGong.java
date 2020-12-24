package com.at.recursion;

/**
 * @author zero
 * @create 2020-12-12 12:24
 */
public class MiGong {

    public static void main(String[] args) {

        /* 设计迷宫 */
        int[][] migong = new int[8][7];
        //左右为墙
        for (int i = 0; i < 8; i++) {
            migong[i][0] = migong[i][6] = 1;
        }
        //上下为墙
        for (int i = 0; i < 7; i++) {
            migong[0][i] = migong[7][i] = 1;
        }

        //挡板
        migong[3][1] = migong[3][2] = 1;
        migong[1][2] = migong[2][2] = 1;
        /* 设计迷宫 */

        System.out.println("迷宫1:");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(migong[i][j] + " ");
            }
            System.out.println();
        }

        /**
         * 从[1][1] 处开始 如果能到[6][5] 这表示成功 退出
         *      没走的为0 走了能通的为2 走了不通的为3 墙为1
         */
        boolean way = getWay(migong, 1, 1);

        System.out.println("迷宫2:");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(migong[i][j] + " ");
            }
            System.out.println();
        }



    }

    /**
     * 规则：下右上左
     *
     * @param migong
     * @param i
     * @param j
     */
    public static boolean getWay(int[][] migong, int i, int j) {
        if (migong[6][5] == 2) {
            //成功
            return true;
        } else {
            if (migong[i][j] == 0) {
                //先假设可以走通
                migong[i][j] = 2;
                //下右上左
                if (getWay(migong, i + 1, j)) {
                    return true;
                } else if (getWay(migong, i, j + 1)) {
                    return true;
                } else if (getWay(migong, i, j - 1)) {
                    return true;
                } else if (getWay(migong, i - 1, j)) {
                    return true;
                } else {
                    //走不通
                    migong[i][j] = 3;
                    return false;
                }
            } else {
                // 1,2,3
                return false;
            }
        }
    }


}
