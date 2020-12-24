package com.at.树.多路查找树;


/**
 * @author zero
 * @create 2020-12-18 17:42
 */
public class MultiTreeDemo {
    public static void main(String[] args) {

        DataItem item70 = new DataItem(70);
        DataItem item50 = new DataItem(50);
        DataItem item30 = new DataItem(30);
        DataItem item40 = new DataItem(40);
        DataItem item20 = new DataItem(20);
        DataItem item80 = new DataItem(80);
        DataItem item31 = new DataItem(31);
        DataItem item90 = new DataItem(90);
        DataItem item75 = new DataItem(75);
        DataItem item10 = new DataItem(10);
        DataItem item45 = new DataItem(45);
        DataItem item46 = new DataItem(46);


        MultiTree tree = new MultiTree();
        tree.insert(item70);
        tree.insert(item50);
        tree.insert(item30);
        tree.insert(item40);
        tree.insert(item20);
        tree.insert(item80);
        tree.insert(item31);
        tree.insert(item90);
        tree.insert(item75);
        tree.insert(item10);
        tree.insert(item45);
        tree.insert(item46);


    }
}

class MultiTree {

    HNode root = new HNode();

    public void insert(DataItem item) {

        HNode currNode = root;


        while (true) {
            if (currNode.dataItemIsfull()) {
                //已满 先分裂再插入
                split(currNode);
                //回到分裂的父节点
                currNode = currNode.parent;
                //继续向下寻找
                currNode = getNextChild(currNode, item.data);
                //在判断当前节点是否已满

            } else if (currNode.isLeaf()) {
                //如果是叶子节点，没满 就可以直接插入
                break;
            } else {
                //没有满 也不是叶子节点，获取到要插入的节点
                currNode = getNextChild(currNode, item.data);
            }
        }

        currNode.insert(item);

    }

    /* 查找下一个节点 */
    public HNode getNextChild(HNode curr, int insertVal) {
        int i = 0;
        int count = curr.count;
        for (; i < count; i++) {
            if (insertVal < curr.data[i].data) {
                return curr.getChild(i);
            }
        }
        return curr.getChild(i);
    }


    /* 分裂 */
    private void split(HNode currNode) {

        DataItem itemB, itemC;
        HNode child3, child4, parent;

        /*
            1.移除当前满节点的最右边的两个数据项 itemC itemB
            2.断开最右边的两个子节点 child3 child4
            3.创建一个新的节点，该新节点为当前满节点的同级节点
         */

        //移除最左边的两个数据项
        itemC = currNode.remove();
        itemB = currNode.remove();

        //断开最后面的两个子节点
        child3 = currNode.disConn(2);
        child4 = currNode.disConn(3);

        //创建一个节点 这个节点为当前节点的同级节点
        HNode rightNode = new HNode();

        /*
            4.判断当前节点是不是根节点
            5.根节点
                5.1.创建一个新的根节点
                5.2.将新的根节点设置为父节点
                5.3.连接父节点与子节点
            6.不是根节点
                6.2.获取父节点
         */
        if (currNode == root) {
            //满节点为父节点
            root = new HNode();
            //将新的根节点设置为父节点
            parent = root;
            //连接父节点与子节点
            root.connChild(0, currNode);
        } else {
            //满节点不是父节点
            parent = currNode.parent;
        }

        /*
            7.将从满节点取下的中间的那个节点(itemB)插入父节点，返回插入的位置(index)
            8.如果插入节点的位置小于父节点的数据项数量则需要移动子节点index后的所有子节点
         */
        int insertIndex = parent.insert(itemB);
        int count = parent.count;

        for (int i = count - 1; i > insertIndex; i--) {
            HNode hNode = parent.disConn(i);
            parent.connChild(i + 1, hNode);
        }

        /*
            9.新新建的同级的节点设置为父节点的索引index+1的子节点
            10.将itemB插入同级的那个节点，并将child3，child4 设置为其第一第二个子节点
         */
        parent.connChild(insertIndex + 1, rightNode);

        rightNode.insert(itemC);
        rightNode.connChild(0, child3);
        rightNode.connChild(1, child4);


    }


}

//节点
class HNode {

    int NUM = 4;

    //子节点
    HNode[] childern = new HNode[NUM];
    //当前节点存放的数据项
    DataItem[] data = new DataItem[NUM - 1];
    //记录当前节点实际存储有多少个数据项
    int count;

    //记录当前节点的父节点
    HNode parent;

    /* 插入方法 返回插入节点的下标 */
    public int insert(DataItem dataItem) {

        //从后向前查找该节点插入的位置
        for (int i = count - 1; i >= 0; i--) {
            //判断要添加的数据项与当前节点中数据项的大小
            if (dataItem.data < data[i].data) {
                //小于要插入的节点i位置处的值 使i位置的数据项后移
                data[i + 1] = data[i];
                continue;
            } else {
                //要插入的数据项大于当前节点i位置处的数据项
                //直接将数据项插入到当前节点的i数据项的后面
                data[i + 1] = dataItem;
                count++;
                return i + 1;
            }
        }

        //如果count为0 则说明当前节点是一个新的节点还没有添加过数据
        // 则直接将要插入的数据项添加到节点数据项的首位置
        data[0] = dataItem;
        count++;
        return 0;
    }

    /* 判断当前节点的数据项是否已满 */
    public boolean dataItemIsfull() {
        return count == NUM - 1;
    }

    /* 从后向前移除当前节点的数据项 */
    public DataItem remove() {
        DataItem temp = data[count - 1];
        data[--count] = null;
        return temp;
    }

    /* 断开当前节点index的子节点连接 */
    public HNode disConn(int index) {
        HNode hNode = childern[index];
        childern[index] = null;
        return hNode;
    }

    /* 建立连接 */
    public void connChild(int index, HNode child) {
        childern[index] = child;
        if (child != null) {
            child.parent = this;
        }
    }


    /* 获取相应的子节点 */
    public HNode getChild(int childIndex) {
        return childern[childIndex];
    }

    /* 判断当前节点是不是叶子节点 */
    public boolean isLeaf() {
        return childern[0] == null;
    }


}

//存放数据的数据项
class DataItem {
    int data;

    @Override
    public String toString() {
        return "DataItem[" +
                "data=" + data +
                ']';
    }

    public DataItem(int data) {
        this.data = data;
    }
}
