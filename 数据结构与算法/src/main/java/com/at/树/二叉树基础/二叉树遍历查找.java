package com.at.树.二叉树基础;


/**
 * @author zero
 * @create 2020-12-14 20:47
 */
public class 二叉树遍历查找 {

    public static void main(String[] args) {

        HNode n11 = new HNode(11);
        HNode n21 = new HNode(21);
        HNode n41 = new HNode(41);
        HNode n51 = new HNode(51);
        HNode n31 = new HNode(31);
        HNode n61 = new HNode(61);
        HNode n71 = new HNode(71);

        n11.setLeft(n21);
        n11.setRight(n31);

        n21.setLeft(n41);
        n21.setRight(n51);

        n31.setLeft(n61);
        n31.setRight(n71);

        HBinaryTree tree = new HBinaryTree(n11);

//        tree.pre();
//        tree.infix();
//        tree.post();

//        tree.preSearch(61);
        tree.infixSearch(61);
//        tree.postSearch(61);

    }
}

class HBinaryTree{

    protected HNode root;

    public HBinaryTree() {
    }

    public HBinaryTree(HNode root) {
        this.root = root;
    }

    private boolean isEmpty(){
        if(root == null){
            System.out.println("二叉树为空");
            return false;
        }

        return true;
    }

    /* 遍历 */

    //前
    public void pre(){

        if(!isEmpty()) return;

        root.preOrder();

    }

    //中
    public void infix(){

        if (!isEmpty()) return;

        root.infixOrder();

    }

    //后
    public void post(){

        if(!isEmpty()) return;

        root.postOrder();
    }


    /* 查找 */
    private void print(HNode node,int no) {
        if(node == null){
            System.out.println("没有"+no+"节点");
        }else{
            System.out.println("要查询的节点为："+node);
        }
    }

    public void preSearch(int no){
        HNode node = root.preSearch(no);
        print(node,no);
    }

    public void infixSearch(int no){
        HNode node = root.infixSearch(no);
        print(node,no);
    }
    public void postSearch(int no){
        HNode node = root.postSearch(no);
        print(node,no);
    }



}


class HNode{
    private int no;
    private String name;

    //左右节点
    private HNode left;
    private HNode right;

    public HNode(int no) {
        this.no = no;
    }

    @Override
    public String toString() {
        return this.no+"";
    }

    /* 遍历 */

    //前序遍历  ==>   先输出父节点，再遍历左子树和右子树
    public void preOrder(){
        System.out.println(this);

        //向左遍历
        if(this.left != null){
            this.left.preOrder();
        }

        //向右遍历
        if(this.right != null){
            this.right.preOrder();
        }

    }

    //中序遍历 ==>  先遍历左子树，再输出父节点，再遍历右子树
    public void infixOrder(){

        //向左遍历
        if(this.left != null){
            this.left.infixOrder();
        }

        System.out.println(this);

        //向右遍历
        if(this.right != null){
            this.right.infixOrder();
        }

    }

    //后序遍历 ==>  先遍历左子树，再遍历右子树，最后输出父节点
    public void postOrder(){

        //向左遍历
        if(this.left != null){
            this.left.postOrder();
        }

        //向右遍历
        if(this.right != null){
            this.right.postOrder();
        }

        System.out.println(this);

    }

    /* 遍历 */

    /* 查找 */

    //前
    public HNode preSearch(int no){

        System.out.println("preSearch...");

        //判断当前节点的 no 是否等于要查找的 no
        if(this.no == no){
            return this;
        }

        HNode res = null;

        //向左
        if(this.left != null){
            res = this.left.preSearch(no);
        }

        // 如果在左子树中查找到了 则 resNode 的结果就不为 null 将其返回
        if (res != null) {
            return res;
        }

        //向右
        if(this.right != null){
            res = this.right.preSearch(no);
        }

        return res;
    }

    //中
    public HNode infixSearch(int no){

        HNode res = null;

        //左
        if(this.left != null){
            res = this.left.infixSearch(no);
        }

        // 判断左子树是否查找到结果
        if(res != null){
            return res;
        }

        System.out.println("infixSearch...");

        // 判断当前节点是不是要查找的节点
        if(this.no == no){
            return this;
        }

        //右
        if(this.right != null){
            res = this.right.infixSearch(no);
        }

        return res;

    }


    //后
    public HNode postSearch(int no){

        HNode res = null;

        if (this.left != null){
            res = this.left.postSearch(no);
        }

        if(res != null){
            return res;
        }

        if(this.right != null){
            res = this.right.postSearch(no);
        }

        if(res != null){
            return res;
        }

        System.out.println("postSearch...");

        if(this.no == no){
            return this;
        }

        return res;

    }

    /* 查找 */


    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HNode getLeft() {
        return left;
    }

    public void setLeft(HNode left) {
        this.left = left;
    }

    public HNode getRight() {
        return right;
    }

    public void setRight(HNode right) {
        this.right = right;
    }
}
