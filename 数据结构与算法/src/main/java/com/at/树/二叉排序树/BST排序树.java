package com.at.树.二叉排序树;




import java.util.Stack;

/**
 * @author zero
 * @create 2020-12-17 14:21
 */
public class BST排序树 {
    public static void main(String[] args) {

        int arr[] = {7, 3, 10, 12, 5, 1, 9,2};

        BSTtree bsTtree = new BSTtree();

        for (int i : arr) {
            bsTtree.add(new HNode(i));
        }

//        bsTtree.pre();
        bsTtree.infix();
//        bsTtree.post();

        System.out.println("------------------------");

//        bsTtree.preThread(bsTtree.root);
//        bsTtree.preThreadOrder();

//        bsTtree.infixThread(bsTtree.root);
//        bsTtree.infixThreadOrder();

//        bsTtree.postThread(bsTtree.root);
//        bsTtree.postThreadOrder();


        System.out.println("------------------------------");


        bsTtree.delNode(3);
        bsTtree.delNode(12);
        bsTtree.delNode(2);
        bsTtree.delNode(7);
        bsTtree.delNode(9);
        bsTtree.delNode(1);
        bsTtree.delNode( 5);
        bsTtree.delNode(10);


        bsTtree.infix();

    }
}

class BSTtree {

    HNode root;

    //二叉排序树添加节点
    public void add(HNode node) {
        if (null == root) {
            root = node;
        } else {
            root.bstAdd(node);
        }
    }

    // ======================= 获取以node 为根节点的右最小节点的值 =========================
    public int getRightTreeMin(HNode node){
        HNode tar = node;
        //循环查找左子树，直到最后一个 这个即为最小节点
        while (tar.left != null) {
            tar = tar.left;
        }
        delNode(tar.val);
        return tar.val;
    }
    // ======================= 查找某个节点 查找某个节点的直接父节点 =========================
    public HNode getTar(int val){
        if(isEmpty(root)) return null;
        return root.getTarget(val);
    }
    public HNode getDirtParent(int val){
        if(isEmpty(root)) return null;
        return root.getDirectParent(val);
    }
    // ======================= 删除 =========================
    public void delNode(int val) {

        if (isEmpty(root)) return;

        /*
            删除节点
            思路
                (1) 需求先去找到要删除的结点  targetNode
                (2) 找到targetNode 的 父结点 parent

         */
        HNode tar = getTar(val);
        HNode parent = getDirtParent(val);

        //没有找到要删除的节点
        if (isEmpty(tar)) return;

        //当二叉树只有一个节点并且这个节点还是要删除的节带点直接将root置空
        if(root.left == null && root.right == null) {
            root= null;
            return;
        }

        if (tar.left == null && tar.right == null) {
            /*
             删除叶子节点 (比如：2, 5, 9, 12)
                思路
                (1) 需求先去找到要删除的结点  targetNode
                (2)  找到targetNode 的 父结点 parent
                (3)  确定 targetNode 是 parent的左子结点 还是右子结点
                (4)  根据前面的情况来对应删除
                    左子结点 parent.left = null
                    右子结点 parent.right = null;
             */

            //左右节点都为空
            //判断要删除的节点时左节点还时右节点
            if (parent.left != null && parent.left.val == tar.val) {
                //要删除的时左子节点
                parent.left = null;
            } else if (parent.right != null && parent.right.val == tar.val) {
                //要删除的节点在右
                parent.right = null;
            }
        } else if (tar.left != null && tar.right != null) {
            /*
            情况三 ： 删除有两颗子树的节点. (比如：7, 3，10 )
                思路
                (1) 需求先去找到要删除的结点  targetNode
                (2)  找到targetNode 的 父结点 parent
                (3)  从targetNode 的右子树找到最小的结点(右子树找最大的节点)
                (4) 用一个临时变量，将 最小(最大)结点的值保存 temp = 11
                (5)  删除该最小结点
                (6)  targetNode.value = temp
             */

            //获取到当前节点的右子树上的最小节点并将其删除
            int temp = getRightTreeMin(tar.right);
            tar.val = temp;
        } else {
            /*
            第二种情况: 删除只有一颗子树的节点 比如 1
                思路
                (1) 需求先去找到要删除的结点  targetNode
                (2)  找到targetNode 的 父结点 parent
                (3) 确定targetNode 的子结点是左子结点还是右子结点
                (4) targetNode 是 parent 的左子结点还是右子结点
                (5) 如果targetNode 有左子结点
                    5. 1 如果 targetNode 是 parent 的左子结点
                    parent.left = targetNode.left;
                    5.2  如果 targetNode 是 parent 的右子结点
                    parent.right = targetNode.left;
                (6) 如果targetNode 有右子结点
                    6.1 如果 targetNode 是 parent 的左子结点
                    parent.left = targetNode.right;
                    6.2 如果 targetNode 是 parent 的右子结点
                    parent.right = targetNode.right
             */
            if (tar.left != null) {
                //要删除的节点只有左子树
                if(parent != null){

                    if (parent.left.val == tar.val) {
                        //要删除的节点是parent的左节点
                        parent.left = tar.left;
                    } else {
                        //要删除的节点是parent的右节点
                        parent.right = tar.left;
                    }
                }else{
                    root = tar.left;
                }


            } else {
                //要删除的节点只有右子树
                if(parent != null){

                    if (parent.left.val == tar.val) {
                        parent.left = tar.right;
                    } else {
                        parent.right = tar.right;
                    }
                }else{
                    root = tar.right;
                }
            }

        }


    }


    // ======================= 排序 =========================
    public boolean isEmpty(HNode node) {
        if (node == null) {
            return true;
        }
        return false;
    }

    public void pre() {
        if (isEmpty(root)) return;
        root.preOrder();
    }

    public void infix() {
        if (isEmpty(root)) return;
        root.infixOrder();
    }

    public void post() {
        if (isEmpty(root)) return;
        root.postOrder();
    }

    // ======================= 线索化即对应的排序 =========================
    HNode pre;

    public void preThread(HNode node) {
        if (isEmpty(node)) return;
        //线索化当前节点
        if (node.left == null) {
            node.left = pre;
            node.leftType = 1;
        }
        if (pre != null && pre.right == null) {
            pre.right = node;
            pre.rightType = 1;
        }
        pre = node;
        //向左递归线索化
        if (node.leftType != 1) preThread(node.left);
        //向右递归线索化
        if (node.rightType != 1) preThread(node.right);

    }

    public void preThreadOrder() {
        HNode node = root;
        while (node != null) {
            while (node.leftType == 0) {
                System.out.println(node);
                node = node.left;
            }
            System.out.println(node);
            node = node.right;
        }

    }

    public void infixThread(HNode node) {
        if (isEmpty(node)) return;
        //向左递归线索化
        infixThread(node.left);
        //线索化当前节点
        if (node.left == null) {
            node.left = pre;
            node.leftType = 1;
        }
        if (pre != null && pre.right == null) {
            pre.right = node;
            pre.rightType = 1;
        }
        pre = node;
        //向右递归线索化
        infixThread(node.right);
    }

    public void infixThreadOrder() {
        HNode node = root;
        while (node != null) {
            while (node.leftType == 0) {
                node = node.left;
            }
            System.out.println(node);
            while (node.rightType == 1) {
                node = node.right;
                System.out.println(node);
            }
            node = node.right;
        }
    }

    public void postThread(HNode node) {
        if (isEmpty(node)) return;
        //向左递归线索化
        postThread(node.left);
        //向右递归线索化
        postThread(node.right);
        //线索化当前节点
        if (node.left == null) {
            node.left = pre;
            node.leftType = 1;
        }
        if (pre != null && pre.right == null) {
            pre.right = node;
            pre.rightType = 1;
        }
        pre = node;
    }

    public void postThreadOrder() {

        HNode node = root;

        Stack<HNode> stack = new Stack<>();

        while (node != null) {
            while (node.rightType == 0) {
                stack.add(node);
                node = node.right;
            }
            stack.add(node);
            node = node.left;
        }

        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }

    }


}

class HNode {

    //权值
    int val;

    HNode left;
    HNode right;

    int leftType;
    int rightType;

    public HNode(int val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return val + "";
    }




    /* 查找某个节点 */
    public HNode getTarget(int target) {
        if (this.val == target) {
            //当前节点就为要删除的节点
            return this;
        } else if (this.val > target) {
            //查找的值小与当前节点
            //当前节点的左节点不为空 向左递归寻找
            if (this.left == null) return null;
            return this.left.getTarget(target);
        } else {
            //查找的值大于或等于当前节点
            //向右递归查找
            if (this.right == null) return null;
            return this.right.getTarget(target);
        }
    }

    /* 查找某个节点的直接父节点 */
    public HNode getDirectParent(int target) {
        //当前节点即为要查找的节点的父节点
        if ((this.left != null && this.left.val == target) || (this.right != null && this.right.val == target)) {
            return this;
        } else {
            if (this.val > target && this.left != null) {
                //小于当前节点的值，向左递归查找
                return this.left.getDirectParent(target);
            } else if (this.val <= target && this.right != null) {
                //大于或等于当前节点的值
                return this.right.getDirectParent(target);
            } else {
                return null;
            }
        }

    }

    /* ========================二叉排序树添加========================= */
    public void bstAdd(HNode node) {
        //当前节点为空直接返回
        if (null == node) return;

        //左小右大

        //不为空添加
        if (this.val > node.val) {
            //如果当前节点的左子节点为空 则直接挂在左子节点
            if (null == this.left) this.left = node;
                //递归的向左子树添加
            else this.left.bstAdd(node);
        } else {
            if (null == this.right) this.right = node;
            else this.right.bstAdd(node);
        }

    }

    /* ========================二叉排序树添加========================= */


    /* ========================遍历========================= */
    public void preOrder() {
        System.out.println(this);
        if (this.left != null) this.left.preOrder();
        if (this.right != null) this.right.preOrder();
    }

    public void infixOrder() {
        if (this.left != null) this.left.infixOrder();
        System.out.println(this);
        if (this.right != null) this.right.infixOrder();
    }

    public void postOrder() {
        if (this.left != null) this.left.postOrder();
        if (this.right != null) this.right.postOrder();
        System.out.println(this);
    }
    /* ========================遍历========================= */

    /* ========================查找========================= */
    public HNode preSearch(int val) {
        if (this.val == val) return this;
        HNode node = null;
        if (this.left != null) node = this.left.preSearch(val);
        if (node != null) return node;
        if (this.right != null) node = this.right.preSearch(val);
        return node;
    }

    public HNode infixSearch(int val) {
        HNode node = null;
        if (this.left != null) node = this.left.infixSearch(val);
        if (node != null) return node;
        if (this.val == val) return this;
        if (this.right != null) node = this.right.infixSearch(val);
        return node;
    }

    public HNode postSearch(int val) {
        HNode node = null;
        if (this.left != null) node = this.left.postSearch(val);
        if (node != null) return node;
        if (this.right != null) node = this.right.postSearch(val);
        if (node != null) return node;
        if (this.val == val) return this;
        return node;
    }
    /* ========================查找========================= */

    /* ========================线索化========================= */

    /* ========================线索化========================= */


}
