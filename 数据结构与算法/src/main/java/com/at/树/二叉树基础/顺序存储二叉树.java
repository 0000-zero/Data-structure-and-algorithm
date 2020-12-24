package com.at.树.二叉树基础;

/**
 * @author zero
 * @create 2020-12-15 11:17
 */
public class 顺序存储二叉树 {

    public static void main(String[] args) {


        int[] arr = {1, 2, 3, 4, 5, 6, 7};

        new OrderStoreTree(arr).preOrderStore(0);

        System.out.println("---------------------------------------");

        new OrderStoreTree(arr).infixOrderStore(0);

    }

}

class OrderStoreTree {

    int arr[];

    public OrderStoreTree(int[] arr) {
        this.arr = arr;
    }

    public boolean isEmpty() {
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空无法遍历");
            return true;
        }

        return false;

    }

    //定义一个个方法完成二叉树存储
    public void preOrderStore(int index) {

        if (isEmpty()) return;

        /*
            第n个元素的左子节点为  2 * n + 1
            第n个元素的右子节点为  2 * n + 2
            第n个元素的父节点为  (n-1) / 2
         */

        System.out.println("要存储的元素：" + arr[index]);

        if ((2 * index + 1) < arr.length) {
            preOrderStore(index * 2 + 1);
        }

        if ((2 * index + 2) < arr.length) {
            preOrderStore(2 * index + 2);
        }
    }

    //定义一个个方法完成二叉树存储
    public void infixOrderStore(int index) {

        if (isEmpty()) return;

        /*
            第n个元素的左子节点为  2 * n + 1
            第n个元素的右子节点为  2 * n + 2
            第n个元素的父节点为  (n-1) / 2
         */

        if((2*index+1) < arr.length){
            infixOrderStore((2*index+1));
        }

        System.out.println("要存储的元素：" + arr[index]);

        if((2*index+2)<arr.length) {
            infixOrderStore((2*index+2));
        }

    }


}