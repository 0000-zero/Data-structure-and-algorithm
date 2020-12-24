package com.at.链表;

/**
 * @author zero
 * @create 2020-12-11 20:46
 */

public class Josepu {
    public static void main(String[] args) {

        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        circleSingleLinkedList.addBoy(5);
        circleSingleLinkedList.showBoy();

        circleSingleLinkedList.countBoy(1, 2, 5);

    }

    //

}

//创建一个环形单项链表
class CircleSingleLinkedList {
    // 创建一个 first 节点，没有编号，在后面赋值 辅助构建环形链表，first指向第一个节点不动，以后添加的每一个节点的next域都指向该first节点
    Boy first = null;

    /**
     *
     * @param startNo  表示从第几个节点开始数数
     * @param countNum 表示数几下
     * @param nums     表示最初要多少个节点在圈中
     */
    public void countBoy(int startNo, int countNum, int nums) {
        // 数据校验
        if (first == null || startNo < 1 || startNo > nums) {
            System.out.println("数据输入有误，请重新输入");
            return;
        }

        // 创建 辅助指针 帮助节点出圈 创建一个辅助指针 helper 事先指向环形链表的最后的节点
        Boy helper = first;
        while (true) {
            if (helper.getNext() == first) {
                break;
            }
            helper = helper.getNext();
        }

        // 报数前，先让 first 和 helper 移动 k-1 次
        for (int j = 0; j < startNo - 1; j++) {
            first = first.getNext();
            helper = helper.getNext();
        }
        // 当 节点 报数时 让 first 和 helper 指针同时 同时移动 m-1 次然后出圈
        while (true) {
            if (helper == first) {// 圈中只有一个节点
                break;
            }
            // 让 first 与 helper 指针同时移动 countNum-1 次然后出圈
            for (int j = 0; j < countNum - 1; j++) {
                first = first.getNext();
                helper = helper.getNext();
            }
            // 此时 first 指向的节点，就是要出圈的节点
            System.out.printf("节点 %d 出圈 \n", first.getNo());
            // 让first出圈
            first = first.getNext();
            helper.setNext(first);
        }
        System.out.printf("最后留在圈中的节点为%d \n", first.getNo());
    }

    // 添加小孩节点，构成一个环形链表
    public void addBoy(int nums) {
        // 对 nums 做一个数据校验
        if (nums < 1) {
            System.out.println("nums数据错误");
            return;
        }

        Boy curBoy = null;// 辅助指针，帮助创建环形链表

        // 使用 for 循环创建环形链表
        for (int i = 1; i <= nums; i++) {
            // 根据编号，创建 节点
            Boy boy = new Boy(i);
            // 如果是第一个节点 让其自成环
            if (i == 1) {
                first = boy;
                first.setNext(first);
                curBoy = first;
            } else {
                curBoy.setNext(boy);

                curBoy = boy;
                curBoy.setNext(first);

//				boy.setNext(first);
//				curBoy = boy;
            }

        }
    }

    // 遍历当前环形链表
    public void showBoy() {
        // 判断链表是否为空 根据 first
        if (first == null) {
            System.out.println("链表为空");
            return;
        }
        // 创建 辅助指针完成遍历
        Boy curBoy = first;
        while (true) {
            System.out.printf("Boy 的编号 %d \n", curBoy.getNo());
            if (curBoy.getNext() == first) {// 当 curBoy 再次指向 first 时 说明 遍历结束
                break;
            }
            curBoy = curBoy.getNext();
        }

    }

}

class Boy {
    private int no;// 编号
    private Boy next;// 指向下一个节点 默认为 null

    public Boy(int no) {
        super();
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }

}
