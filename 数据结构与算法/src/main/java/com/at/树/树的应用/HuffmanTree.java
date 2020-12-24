package com.at.树.树的应用;


import java.util.ArrayList;
import java.util.Collections;

/**
 * @author zero
 * @create 2020-12-16 10:07
 */
public class HuffmanTree {

    public static void main(String[] args) {

//        int[] arr = {13, 7, 8, 3, 29, 6, 1};

        int[] arr = { 37, 53, 60, 28, 40, 70, 45, 24, 55, 12 };

        HNode root = huffmanThree(arr);

        preOrder(root);


    }

    public static void preOrder(HNode root){
        if(root == null){
            System.out.println("该树为空");
        }else{
            root.preOrder();
        }
    }

    /*
        构成赫夫曼树的步骤：
            1.从小到大进行排序, 将每一个数据，每个数据都是一个节点 ， 每个节点可以看成是一颗最简单的二叉树
            2.取出根节点权值最小的两颗二叉树
            3.组成一颗新的二叉树, 该新的二叉树的根节点的权值是前面两颗二叉树根节点权值的和
            4.再将这颗新的二叉树，以根节点的权值大小 再次排序， 不断重复  1-2-3-4 的步骤，直到数列中，所有的数据都被处理，就得到一颗赫夫曼树

     */
    public static HNode huffmanThree(int[] arr){

        ArrayList<HNode> list = new ArrayList<>();

        for (int i = 0; i < arr.length; i++) {
            list.add(new HNode(arr[i]));
        }


        while (list.size() > 1) {
            //排序
            Collections.sort(list);

            //从有序的集合中取出最小的两个节点
            HNode leftN = list.get(0);
            HNode rightN = list.get(1);

            //构建最小的两个节点的父节点，并把他们构建成一颗树
            HNode parent = new HNode(leftN.val + rightN.val);
            parent.left = leftN;
            parent.right = rightN;

            //移除两个最小的节点并把新构建的二叉树的父节点加入集合
            list.remove(leftN);
            list.remove(rightN);
            list.add(parent);

            //一直循环直到list 中只剩一个节点 即树的根节点
        }

        return list.get(0);


    }



}

class HNode implements Comparable<HNode>{

    //权值
    int val;

    HNode left;
    HNode right;

    //前序
    public void preOrder(){

        System.out.println(this);

        if(this.left != null) this.left.preOrder();

        if(this.right != null) this.right.preOrder();

    }

    public HNode(int val) {
        this.val = val;
    }

    @Override
    public int compareTo(HNode o) {
        //从小到大
        return this.val - o.val;
    }

    @Override
    public String toString() {
        return "val:"+val;
    }
}
