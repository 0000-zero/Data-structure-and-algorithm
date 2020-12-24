package com.at.queue;

import java.util.Scanner;

/**
 * @author zero
 * @create 2020-12-11 20:44
 */
public class ArrayQueue {

    public static void main(String[] args) {

        ArrayQ arrayQ = new ArrayQ(3);
        char key = ' ';// 接收用户输入的数据

        Scanner scanner = new Scanner(System.in);

        boolean loop = true;

        // 输入一个菜单
        while (loop) {

            System.out.println("s(show)：显示队列");
            System.out.println("e(exit)：退出程序");
            System.out.println("a(add)：添加数据到程序");
            System.out.println("g(get)：从队列取出数据");
            System.out.println("h(head)：查看队列头的数据");


            System.out.print("请选择：");

            key = scanner.next().charAt(0);

            switch (key) {
                case 's':
                    arrayQ.showQueue();
                    break;
                case 'a':
                    System.out.println("输入一个数：");
                    int value = scanner.nextInt();
                    arrayQ.addQueue(value);
                    break;
                case 'g':
                    try {
                        int queue = arrayQ.getQueue();
                        System.out.printf("取出的数据是：%d\n",queue);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }

                    break;
                case 'h':
                    try {
                        int headQueue = arrayQ.headQueue();
                        System.out.printf("队列头的数据是：%d\n",headQueue);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e':
                    scanner.close();
                    loop = false;
                    break;
            }

        }
        System.out.println("程序退出");

    }
}

//定义一个类模拟队列ArrayQ
class ArrayQ {

    private int maxSize;// 表示数组的最大容量
    private int front;// 队列头
    private int rear;// 队列尾
    private int[] arr;// 该数组用于存放数据模拟队列

    public ArrayQ(int arrMaxSize) {

        maxSize = arrMaxSize;
        arr = new int[maxSize];
        front = -1; // 指向队列头部，分析出front是指向队列头前一个位置
        rear = -1; // 指向队列尾部，指向队列的数据(即就是队列最后一个数据)
    }

    /**
     *  判断队列是否满
     * @return
     */
    public boolean isFull() {
        return rear == maxSize - 1;
    }

    /**
     *  判断队列是否为空
     * @return
     */
    public boolean isEmpty() {
        return front == rear;
    }

    /**
     *  向队列中添加数据
     * @param n
     */
    public void addQueue(int n) {

        // 添加之前判断队列是否已满
        if (isFull()) {
            System.out.println("队列已满，不能添加数据");
            return;
        }

        rear++;// 让rear后移
        arr[rear] = n;
    }

    /**
     *  取出队列数据
     * @return
     */
    public int getQueue() {

        // 判断队列是否为空
        if (isEmpty()) {
            throw new RuntimeException("队列为空，无法取数据...");
        }

        // 让front后移
        front++;

        return arr[front];

    }

    /**
     *  显示队列所有数据
     */
    public void showQueue() {

        if (isEmpty()) {
            System.out.println("队列为空，没有数据");
            return;
        }

        for (int i = 0; i < arr.length; i++) {
//			System.out.printf("arr[%d]=%d/n", i, arr[i]);
            System.out.println("arr["+i+"]="+arr[i]);

        }

    }

    /**
     *  显示队列的头部数据
     * @return
     */
    public int headQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空，没有数据");
        }

        return arr[front++];
    }

    // 显示队列尾部数据

}

