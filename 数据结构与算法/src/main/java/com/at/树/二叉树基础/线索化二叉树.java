package com.at.树.二叉树基础;


import java.util.Stack;

/**
 * @author zero
 * @create 2020-12-15 12:13
 */
public class 线索化二叉树 {
    public static void main(String[] args) {

        ThreadNode n11 = new ThreadNode(11);
        ThreadNode n21 = new ThreadNode(21);
        ThreadNode n31 = new ThreadNode(31);
        ThreadNode n14 = new ThreadNode(14);
        ThreadNode n15 = new ThreadNode(15);
        ThreadNode n61 = new ThreadNode(61);
        ThreadNode n71 = new ThreadNode(71);
        ThreadNode n81 = new ThreadNode(81);
        ThreadNode n91 = new ThreadNode(91);

        n11.setLeft(n21);
        n11.setRight(n31);

        n21.setLeft(n14);
        n21.setRight(n15);

        n14.setLeft(n81);
        n14.setRight(n91);

        n31.setLeft(n61);
        n31.setRight(n71);


        ThreadBinaryTree tree = new ThreadBinaryTree(n11);

//        tree.pre();
//        tree.infix();

        //中
//        tree.infixThread(n11);
//        System.out.println(n81.getLeft() + "," + n81.getRight() + "," + n15.getLeft() + "," + n15.getRight());
//        tree.infixThreadPrint();

        //前
//        tree.preThread(n11);
//        System.out.println(n81.getLeft() + "," + n81.getRight() + "," + n15.getLeft() + "," + n15.getRight());
//        tree.preThreadPrint();

        //后
//        tree.post();
        tree.postThread(n11);
        System.out.println(n81.getLeft() + "," + n81.getRight() + "," + n15.getLeft() + "," + n15.getRight());
        tree.postThreadPrint();


    }
}

class ThreadBinaryTree extends HBinaryTree {

    public ThreadBinaryTree(HNode root) {
        super(root);
    }

    //为了实现线索化，需要定义一个变量，该变量用于指向当前节点的前驱节点
    private ThreadNode pre;

    /* 前序线索化 */
    public void preThread(ThreadNode node) {

        //node 为空无法线索化
        if (node == null) return;

        //线索化当前节点
        if (node.getLeft() == null) {
            node.setLeft(pre);
            node.setLeftType(1);
        }

        if (pre != null && pre.getRight() == null) {
            pre.setRight(node);
            pre.setRightType(1);
        }

        pre = node;

        //向左线索化
        if (node.getLeftType() != 1) {
            preThread((ThreadNode) node.getLeft());
        }
        //向右线索化
        if (node.getRightType() != 1) {
            preThread((ThreadNode) node.getRight());
        }

    }

    //前序线索化遍历
    public void preThreadPrint() {

        ThreadNode node = (ThreadNode) this.root;

        while (node != null) {
            while (node.getLeftType() != 1) {
                System.out.println(node);
                node = (ThreadNode) node.getLeft();
            }
            System.out.println(node);
            node = (ThreadNode) node.getRight();
        }

    }

    /* 前序线索化 */

    /* 中序线索化 */
    public void infixThread(ThreadNode node) {

        //node 为空无法线索化
        if (node == null) return;

        //向左线索化
        infixThread((ThreadNode) node.getLeft());

        //线索化当前节点
        //前驱 ==> 当前节点的左为空时才需要线索化前驱
        if (node.getLeft() == null) {
            node.setLeft(pre);
            node.setLeftType(1);
        }

        //后继节点 ==>
        if (pre != null && pre.getRight() == null) {
            pre.setRight(node);
            pre.setRightType(1);
        }

        //让pre 指向 node
        pre = node;


        //向右线索化
        infixThread((ThreadNode) node.getRight());

    }

    //中序线索化遍历
    public void infixThreadPrint(){

        ThreadNode node = (ThreadNode) this.root;

        while (node != null){

            while (node.getLeftType() == 0){
                node = (ThreadNode) node.getLeft();
            }

            System.out.println(node);

            while (node.getRightType() == 1){
                node = (ThreadNode) node.getRight();
                System.out.println(node);
            }

            node = (ThreadNode) node.getRight();

        }


    }


    /* 中序线索化 */

    /* 后序线索化 */
    public void postThread(ThreadNode node) {

        if (node == null) return;

        //左
        postThread((ThreadNode) node.getLeft());

        //右
        postThread((ThreadNode) node.getRight());

        //当前
        if (node.getLeft() == null) {
            node.setLeft(pre);
            node.setLeftType(1);
        }

        if (pre != null && pre.getRight() == null) {
            pre.setRight(node);
            pre.setRightType(1);
        }

        pre = node;

    }

    //后序遍历
    public void postThreadPrint(){

        ThreadNode node = (ThreadNode) this.root;

        Stack<ThreadNode> stack = new Stack<>();

        while (node != null){
            while (node.getRightType() != 1){
                stack.push(node);
                node = (ThreadNode) node.getRight();
            }

            stack.push(node);

            node = (ThreadNode) node.getLeft();

        }

        while (!stack.isEmpty()){
            System.out.println(stack.pop());
        }



    }

    /* 后序线索化  */


    public ThreadNode getPre() {
        return pre;
    }

    public void setPre(ThreadNode pre) {
        this.pre = pre;
    }
}


class ThreadNode extends HNode {

    // leftType/rightType 为0 左右子树  leftType/rightType 为1前驱后继节点
    private int leftType;
    private int rightType;

    public ThreadNode(int no) {
        super(no);
    }


    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }
}
