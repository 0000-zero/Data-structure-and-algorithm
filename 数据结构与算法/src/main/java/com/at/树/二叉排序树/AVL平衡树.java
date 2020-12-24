package com.at.树.二叉排序树;

/**
 * @author zero
 * @create 2020-12-17 20:54
 */
public class AVL平衡树 {
    public static void main(String[] args) {
        //int arr[] = {4, 3, 6, 5, 7, 8};
//        int arr[] = {10, 12, 8, 9, 7, 6};
        int arr[] = { 10, 11, 7, 6, 8, 9 };
//        int arr[] = {2,1,6,5,7,3};

        AVLtree avLtree = new AVLtree();

        for (int i : arr) {
            avLtree.avlAdd(new AVLNode(i));
        }

        avLtree.infix();

        System.out.println("--------------------------");

        System.out.println("树高：" + avLtree.getRoot().treeHeight());
        System.out.println("左树：" + avLtree.getRoot().leftHeight());
        System.out.println("右树：" + avLtree.getRoot().rightHeight());

    }
}

class AVLtree extends BSTtree {


    public AVLNode getRoot() {
        return (AVLNode) root;
    }

    /* 添加 */
    public void avlAdd(AVLNode node) {
        if (root == null) {
            root = node;
        } else {
            ((AVLNode) root).avlAdd(node);
        }
    }

}

class AVLNode extends HNode {

    public AVLNode(int val) {
        super(val);
    }


    /*  统计树的高度 */
    public int treeHeight() {
        return Math.max(left == null ? 0 : ((AVLNode) left).treeHeight(), right == null ? 0 : ((AVLNode) right).treeHeight()) + 1;
    }

    /* 计算左子树高度 */
    public int leftHeight() {
        if (left == null) return 0;
        return ((AVLNode) left).treeHeight();
    }

    /* 计算右子树高度 */
    public int rightHeight() {
        if (right == null) return 0;
        return ((AVLNode) right).treeHeight();
    }

    /* 左旋转 移动右边的向左去平衡*/
    public void leftRotate() {

        //创建一个新的节点等于当前节点的值
        AVLNode newNode = new AVLNode(val);
        //将新节点的左节点设置为当前节点的左节点
        newNode.left = left;
        //将新节点的右节点设置为当前节点的右节点的左节点
        newNode.right = right.left;
        //将当前节点的权值设置为当前节点的右节点的权值
        val = right.val;
        //将当前节点的左节点设置为新的节点
        left = newNode;
        //将当前节点的右节点设置为当前节点的右节点的右节点
        right = right.right;

    }


    /* 右旋转 移动左边的向右去平衡*/
    public void rightRotate() {

        //创建一个新节点
        AVLNode newNode = new AVLNode(val);
        //将新节点的右节点设置为当前节点的右节点
        newNode.right = right;
        //将新节点的左节点的设置为当前节点的左节点的右节点
        newNode.left = left.right;
        //将当前节点的权值设置为左节点的权值
        val = left.val;
        //将当前节点的右节点设置为新的节点
        right = newNode;
        //将当前节点的左节点设置为当前节点的左节点的左节点
        left = left.left;

    }


    /* 平衡二叉树添加 */
    public void avlAdd(AVLNode node) {
        if (this.val > node.val) {
            if (this.left == null) this.left = node;
            else ((AVLNode) this.left).avlAdd(node);
        } else {
            if (this.right == null) this.right = node;
            else ((AVLNode) this.right).avlAdd(node);
        }

        //添加一个节点后判断是否左右子树的高度差大于1
        int i = rightHeight();
        int i1 = leftHeight();

        //右大于左向左旋转
        if (rightHeight() - leftHeight() > 1) {
            //如果它的右子树的左子树高度大于它的右子树的右子树高度
            if(right != null && ((AVLNode)right).leftHeight() > ((AVLNode)right).rightHeight()){
                //先对右子树进行右旋转
                ((AVLNode) right).rightRotate();
            }

            leftRotate();
            return;
        }

        //左大于右向右旋转
        if (leftHeight() - rightHeight() > 1) {
             /*
                1. 当符号右旋转的条件时
                2. 如果它的左子树的右子树高度大于它的左子树的高度
                3. 先对当前这个结点的左节点进行左旋转
                4. 在对当前结点进行右旋转的操作即可
             */
             //如它的左子树的右子树高度大与左子树的高度
             if(left != null && ((AVLNode)left).rightHeight() > ((AVLNode)left).leftHeight()){
                 //先对当前节点的左子树左旋转
                 ((AVLNode)left).leftRotate();
             }
             //然后在对当前节点右旋转
            rightRotate();
        }


    }


}
