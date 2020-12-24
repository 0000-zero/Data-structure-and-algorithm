package com.at.稀疏数组;

import java.io.*;

/**
 * @author zero
 * @create 2020-12-11 20:42
 */
public class SparseArray {

    public static void main(String[] args) {

        // Snipaste_2020-03-17_11-04-58.png

        // 1:表示黑子 2:表示蓝子

        // 创建 11*11的二位数组
        int chessArr1[][] = new int[11][11];
        chessArr1[1][2] = 1;
        chessArr1[2][3] = 2;
        chessArr1[5][7] = 1;

        // 遍历数组
        System.out.println("原始数组");
        for (int[] row : chessArr1) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }

        // 查询有多少个有效数据
        int sum = 0;
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (chessArr1[i][j] != 0) {
                    sum++;
                }
            }
        }
        System.out.println("有效数据：" + sum);

        // 将chessArr1转稀疏数组

        /*
         * 稀疏数组： 当数组中大部分元素为0，或同一个值时，可以使用稀疏数组来保存该数组 稀疏数组的处理方法： 1.记录数组一共有几行几列，有多少个不同的值
         * 2.将具有不同值得元素的行列及值记录在一个小规模的数组（稀疏数组）中，从而缩小程序的规模
         *
         * 稀疏数组：固定的三列分别为 row行 col列 val值 行为有效数据+1行
         *
         */

        int sparseArr[][] = new int[sum + 1][3];
        // 定义第一行 三个数据
        sparseArr[0][0] = 11;
        sparseArr[0][1] = 11;
        sparseArr[0][2] = sum;

        // 将数据存入稀疏数组
        int count = 0;// count 用于记录是第几个有效值
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (chessArr1[i][j] != 0) {
                    count++;
                    sparseArr[count][0] = i;// 第1列存入有效值的行
                    sparseArr[count][1] = j;// 有效值的列
                    sparseArr[count][2] = chessArr1[i][j];// 第3列存入有效值
                }
            }
        }
        System.out.println();

        // 打印得到的稀疏数组
        System.out.println("得到的稀疏数组");
        for (int i = 0; i < sparseArr.length; i++) {
            System.out.printf("%d\t%d\t%d\n", sparseArr[i][0], sparseArr[i][1], sparseArr[i][2]);
        }

        // 将稀疏数组转二维素组

        // 先读取稀疏数组的第一行，根据第一行数据，创建原始的二维数组
        int chessArr2[][] = new int[sparseArr[0][0]][sparseArr[0][1]];

        for (int i = 1; i < sparseArr.length; i++) {
            chessArr2[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
        }

        // 恢复二维数组
        System.out.println("恢复成元素组");
        for (int arr[] : chessArr2) {
            for (int data : arr) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }

        // 将数据读入文件
//		write(chessArr1);

        read("D:\\Java_work\\Data structure and algorithm\\day01\\writedate1584369987796.txt");

//		File file = new File("D:\\Java_work\\Data structure and algorithm\\day01\\writedate1584369987796.txt");
//		System.out.println(file);

    }

    public static void write(int[][] arr) {

        BufferedWriter bw = null;

        try {
            // 初始化流对象
            bw = new BufferedWriter(
                    new OutputStreamWriter(new FileOutputStream("writedate" + System.currentTimeMillis() + ".txt")));

            // 写数据

            for (int i = 0; i < arr.length; i++) {
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < arr[i].length; j++) {
                    sb.append(arr[i][j]);
                }
                bw.write(new String(sb));
                bw.newLine();
            }

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                if (bw != null) {
                    bw.close();
                }
            } catch (Exception e2) {
                // TODO: handle exception
            }
        }

    }

    public static void read(String str) {

        BufferedReader br = null;

        try {
            // 创建流对象
            br = new BufferedReader(new InputStreamReader(new FileInputStream(str)));

            // 读取数据
            String str1;
            while ((str1 = br.readLine()) != null) {

                System.out.println(str1);

//				int len = str1.length();
//
//				int i = Integer.parseInt(str1);
//
//				int arr[][] = new int[][length];
//
//				for() {
//
//				}

            }

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (Exception e2) {
                // TODO: handle exception
            }
        }

    }

}
