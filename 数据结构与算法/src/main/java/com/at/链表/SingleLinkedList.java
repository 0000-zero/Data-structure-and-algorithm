package com.at.链表;

import java.util.Stack;

/**
 * @author zero
 * @create 2020-12-11 20:46
 */

public class SingleLinkedList {
    public static void main(String[] args) {

        // 创建节点
        HeroNode hero1 = new HeroNode(1, "晁盖", "托塔天王");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");
        HeroNode hero5 = new HeroNode(5, "aa", "aaa");
        HeroNode hero6 = new HeroNode(6, "bb", "bb");

        // 创建一个链表 将节点加入链表
        SingleLinkedListManger singleLinkedListManger = new SingleLinkedListManger();

        singleLinkedListManger.add(hero1);
        singleLinkedListManger.add(hero4);
        singleLinkedListManger.add(hero5);
        singleLinkedListManger.add(hero2);
        singleLinkedListManger.add(hero3);

//		singleLinkedListManger.addOrder(hero6);
//		singleLinkedListManger.addOrder(hero1);
//		singleLinkedListManger.addOrder(hero4);
//		singleLinkedListManger.addOrder(hero2);
//		singleLinkedListManger.addOrder(hero3);
//		singleLinkedListManger.addOrder(hero5);


        //反转前
        singleLinkedListManger.list();
//		System.out.println("--------------------------------------------------------");

//		//反转后
//		reverseList(singleLinkedListManger.getHead());
//		singleLinkedListManger.list();

//		System.out.println("--------------------------------------------------------");

//		reversePrint(singleLinkedListManger.getHead());
//		System.out.println("--------------------------------------------------------");
        singleLinkedListManger.list();

		/*
		// 显示
		singleLinkedListManger.list();
		System.out.println("--------------------------------------------------------");
		HeroNode h = new HeroNode(2, "xiaolu", "玉麒麟---");
		singleLinkedListManger.update(h);

		// 显示
		singleLinkedListManger.list();
		System.out.println("--------------------------------------------------------");

		singleLinkedListManger.delete(hero6);
		singleLinkedListManger.delete(hero4);
		singleLinkedListManger.list();

		System.out.println("-----------------------------------------------------");

		System.out.println("有效节点的数量：" + getLength(singleLinkedListManger.getHead()));
		System.out.println("-----------------------------------------------------");

		HeroNode heroNode = getHeroNode(singleLinkedListManger.getHead(),1);
		System.out.println(heroNode);
		 */




    }
    public static void reversePrint(HeroNode head) {
        if(head.next == null) {//空连表
            return;
        }

        Stack<HeroNode> stack = new Stack<HeroNode>();
        HeroNode cur = head.next;
        //将所有节点压入栈中
        while(cur != null) {
            stack.push(cur);
            cur = cur.next;
        }

        //出栈
//		while(stack.size() > 0) {
        while(!stack.empty()) {
            System.out.println(stack.pop());
        }

    }

    /**
     * 将链表反转
     * @param head
     */
    public static void reverseList(HeroNode head) {
        //如果当前链表为空，或只有一个节点，就无需反转，直接返回
        if(head.next == null || head.next.next == null) {
            return;
        }
        //定义一个辅助的指针(变量)，帮助遍历原来的链表
        HeroNode cur = head.next;
        //定义一个节点用于保存当前节点的下一个节点
        HeroNode next = null;
        //定义一个临时的头结点
        HeroNode reverseHead = new HeroNode(0, "", "");
        //遍历原来的链表 每遍历一个节点，就将其取出，并放在临时链表  reverseHead 的最前端
        while(cur != null) {
            //先暂时保存当前节点的下一个节点
            next = cur.next;
            //将当前节点的下一个节点指向新链表的最前端
            cur.next = reverseHead.next;
            //将current连接到新的链表上
            reverseHead.next = cur;
            cur = next;//让 cur 后移
        }
        //将head.next 指向 reverseHead.next 实现单链表的反转
        head.next = reverseHead.next;

    }


    /*
     * 查找单链表中的倒数第 K 个节点
     * 	思路：
     * 		1.封装一个方法，接收 head 节点和  index (index表示倒数第 K 个节点)
     * 		2.先将链表遍历，得到链表的总长度 size
     * 		3.在从头遍历链表，遍历  size-index 个即可
     */
    public static HeroNode getHeroNode(HeroNode head,int index) {
        if(head.next == null) {//空连表
            return null;
        }
        //第一次遍历 得到节点个数
        int size = getLength(head);
        //第二次遍历 size-index 位置 得到倒数第 K 个节点
        if(index <= 0 || index > size) {
            return null;
        }
        HeroNode cur = head.next;
        for(int i = 0;i < size - index;i++) {
            cur = cur.next;
        }
        return cur;
    }



    /**
     *
     * @param head 链表头结点
     * @return 返回有效结点个数
     */
    public static int getLength(HeroNode head) {
        int length = 0;
        HeroNode cur = head.next;// 头结点的下一个节点
        while (cur != null) {
            length++;
            cur = cur.next;
        }
        return length;
    }

}

//定义一个SingleLinkedListManger类管理HeroNode 增删改查
class SingleLinkedListManger {

    // 先初始化一个头结点，头节点不能改动，起引导作用，不存放具体数据
    private HeroNode head = new HeroNode(0, "", "");

    public HeroNode getHead() {
        return head;
    }

    // 添加节点到单向链表
    public void add(HeroNode heroNode) {
        // 1.找到当前链表的最后一个节点 2.将最后这个节点的next 指向新的节点
        // 因为head节点不能改动，因此定义一个辅助temp进行遍历
        HeroNode temp = head;
        // 循环链表，找到链表最后(next = null)
        while (true) {
            // 找到链表的最后
            if (temp.next == null)
                break;
            // 将temp后移
            temp = temp.next;
        }
        // 结束循环时temp就指向链表的最后(循环一定能够结束)
        // 将最后的这个节点的next指向新的节点
        temp.next = heroNode;
    }

    // 显示链表[遍历]
    public void list() {
        // 判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空...");
            return;
        }
        // 因为head节点不能改动，因此定义一个辅助temp进行遍历
        HeroNode temp = head.next;// 让temp指向点前节点的下一个节点
        // 链表不为空
        while (true) {
            // 判断是否到链表最后
            if (temp == null) {
                break;
            }
            // 不是链表的最后 则输出节点信息
            System.out.println(temp);
            // 将temp后移
            temp = temp.next;
        }
    }

    // 第二种添加方法：根据排名将节点插入到指定位置 如果有这个排名则添加失败，并给出提示
    public void addOrder(HeroNode heroNode) {
        // 头结点不能动，因此需要通过一个辅助指针（变量）来帮助查找添加位置 因为是单链表，所以temp因位于添加位置的前一个节点
        HeroNode temp = head;
        // 判断要添加的节点是否已存在
        boolean flag = false;
        while (true) {
            if (temp.next == null) {// 说明temp已经在链表的最后
                break;
            }
            if (temp.next.no > heroNode.no) {// 找到要添加的位置
                break;
            } else if (temp.next.no == heroNode.no) {// 说明要添加的节点已存在
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            System.out.println("需要添加的节点 " + heroNode.no + "已存在！！！");
        } else {
            heroNode.next = temp.next;// 应先为节点添加
            temp.next = heroNode;// 在为temp节点添加
        }
    }

    // 修改节点的信息，根据 no 编号进行修改，即 no 编号不能改
    // 1.根据 newHeroNode 的 no 节点修改
    public void update(HeroNode newHeroNode) {
        // 判断是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        //
        HeroNode temp = head.next;
        boolean flag = false;
        while (true) {
            if (temp == null) {
                break;// 链表已经遍历完
            }
            if (temp.no == newHeroNode.no) {// 表示已找到
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.name = newHeroNode.name;
            temp.nickname = newHeroNode.nickname;
        } else {// 没有找到
            System.out.println("没有找到" + newHeroNode.no + "的节点，无法修改");
        }
    }

    // 删除节点
    public void delete(HeroNode heronode) {
        // 判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        HeroNode temp = head;
        // 判断要删除的节点是否存在
        boolean flag = false;
        while (true) {
            if (temp.next == null) {
                break;
            }
            if (temp.next.no == heronode.no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.next = temp.next.next;
        } else {
            System.out.println("没有找到要删除的节点");
        }
    }

}

//定义 HeroNode 每一个HeroNode 对象就是一个节点
class HeroNode {
    public int no; // 编号
    public String name;
    public String nickname; // 别称
    public HeroNode next; // 指向下一个节点

    public HeroNode(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "HeroNode [no=" + no + ", name=" + name + ", nickname=" + nickname + "]";
    }

}
