package com.at.queue;

import java.util.Scanner;

/**
 * @author zero
 * @create 2020-12-11 20:44
 */

public class CircleArrayQueue {

    public static void main(String[] args) {

        System.out.println("测试环形队列。。。");

        // 创建队列
        CircleArray queue = new CircleArray(4);

        char key = ' ';// 接收用户输入
        Scanner scanner = new Scanner(System.in);

        boolean loop = true;

        while (loop) {

            System.out.println("s(show)：显示队列");
            System.out.println("a(add)：添加数据到队列");
            System.out.println("g(get)：从队列取出数据");
            System.out.println("h(head)：查看队列头的数据");
            System.out.println("e(exit)：退出程序");

            System.out.println("请输入选择：");

            key = scanner.next().charAt(0);
            switch (key) {
                case 's':
                    queue.showQueue();
                    break;
                case 'a':
                    System.out.println("输入添加的数据：");
                    int a = scanner.nextInt();
                    queue.addQueue(a);
                    break;
                case 'g':
                    try {
                        int queue2 = queue.getQueue();
                        System.out.println("取出的数据是："+queue2);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    int headQueue = queue.headQueue();
                    System.out.println(headQueue);
                    break;
                case 'e':
                    scanner.close();
                    loop = false;
                    break;


            }

        }

    }

}

class CircleArray {

    private int maxSize;// 表示数组的最大容量
    private int front;// front 指向队列的第一个元素
    private int rear;// 队列尾
    private int[] arr;

    /**
     * 初始化数组
     *
     * @param maxSize
     */
    public CircleArray(int maxSize) {
        this.maxSize = maxSize;

        arr = new int[this.maxSize];

    }

    /**
     * 判断队列是否已满
     *
     * @return
     */
    public boolean isFull() {
        return (rear + 1) % maxSize == front;

    }

    /**
     * 判断队列是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return rear == front;
    }

    /**
     * 向队列中添加数据
     *
     * @param a
     */
    public void addQueue(int a) {

        if (isFull()) {
            System.out.println("队列已满，不能添加数据");
            return;
        }

        // 添加数据

        arr[rear] = a;// 因为rear为最后一个元素的后一位
        // rear后移
        rear = (rear + 1) % maxSize;

    }

    /**
     * 获取队列的数据
     *
     * @return
     */
    public int getQueue() {

        if (isEmpty()) {
            throw new RuntimeException("队列中没有数据");
        }

        int value = arr[front];// front 是指向队列的第一个元素

        front = (front + 1) % maxSize;// 将 front后移 必须取模， 取模是防止下标越界异常

        return value;

    }

    /**
     * 显示队列中所有数据
     */
    public void showQueue() {

        if (isEmpty()) {
            System.out.println("队列为空。。。");
            return;
        }

        // 取出队列所有数据

        for (int i = front; i < front + size(); i++) {
            System.out.printf("arr[%d]=%d\n", i % maxSize, arr[i % maxSize]);
        }

    }

    /**
     *
     * @return 返回队列中实际元素的个数
     */
    public int size() {
        return (rear + maxSize - front) % maxSize;
    }

    public int headQueue() {

        if (isEmpty()) {
            new RuntimeException("队列为空");
        }

        return arr[front];

    }

}

