package com.at.链表;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zero
 * @create 2020-12-11 20:46
 */

public class DoubleLinkedList {
    public static void main(String[] args) {

        System.out.println("双向链表测试....");

        List<HeroNode2> listHero = new ArrayList<HeroNode2>();

        listHero.add(new HeroNode2(4, "林冲", "豹子头"));
        listHero.add(new HeroNode2(3, "吴用", "智多星"));
        listHero.add(new HeroNode2(2, "卢俊义", "玉麒麟"));
        listHero.add(new HeroNode2(1, "晁盖", "托塔天王"));

        DoubleLinkedManger manger = new DoubleLinkedManger();

//		for(HeroNode2 h : listHero) {
//			manger.add(h);
//		}
//
//		manger.list();
//		System.out.println("--------------------------------------------");
//
//		HeroNode2 h1 = new HeroNode2(3,"AA","AA");
//		manger.update(h1);
//		manger.list();
//		System.out.println("--------------------------------------------");
//
//		manger.delete(2);
//		manger.list();
//		System.out.println("--------------------------------------------");

        for(HeroNode2 h : listHero) {
            manger.addOrder(h);
        }

        manger.list();

        System.out.println("----------------");

        manger.reverseList();

    }
}

//创建一个双向链表类
class DoubleLinkedManger{

    HeroNode2 head = new HeroNode2(0, "", "");

    public HeroNode2 getHead() {
        return head;
    }

    public void addOrder(HeroNode2 heroNode2) {
        HeroNode2 temp = head;
        boolean flag = false;
        while(true) {
            if(temp.next == null) {
                break;
            }
            if(temp.next.no > heroNode2.no) {
                break;
            }else if(temp.next.no == heroNode2.no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if(flag) {
            System.out.println("编号为"+heroNode2.no+"的节点已存在");
        }else {
//			heroNode2.next = temp.next;
//			temp.next = heroNode2;
//			heroNode2.pre = temp;
//			temp.pre = heroNode2;

            if(temp.next != null) {
                temp.next.pre = heroNode2;
            }

            heroNode2.next = temp.next;
            temp.next = heroNode2;
            heroNode2.pre = temp;

        }

    }

    //删除一个节点     **对于双向链表可以直接找到要删除的节点而无需再找要删除的节点的上一个节点
    public void delete(int no) {
        HeroNode2 temp = head.next;
        if(temp == null) {
            System.out.println("链表为空");
            return;
        }
        boolean flag = false;
        while(true) {
            if(temp == null) {
                break;
            }
            if(temp.no == no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if(flag) {
            //删除节点  自我删除
            temp.pre.next = temp.next;
            //存在安全隐患  如果 temp 为最后一个节点 则 temp.next = null 那么 temp.next.pre 会出现空指针异常
//			temp.next.pre = temp.pre;
            if(temp.next != null) {
                temp.next.pre = temp.pre;
            }
        }else {
            System.out.println("没有你要删除的"+no+"节点");
        }

    }

    //修改节点
    public void update(HeroNode2 heroNode2) {
        HeroNode2 temp = head.next;
        if(temp == null) {
            System.out.println("链表为空");
            return;
        }
        boolean flag = false;
        while(true) {
            if(temp == null) {
                break;
            }
            if(temp.no == heroNode2.no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if(flag) {
            temp.name = heroNode2.name;
            temp.nickname = heroNode2.nickname;
        }else {
            System.out.println("没有编号为"+heroNode2.no+"的节点");
        }

    }


    //添加 节点到链表最后
    public void add(HeroNode2 heroNode2) {
        HeroNode2 temp = head;
        while(true) {
            if(temp.next == null) {
                break;
            }
            temp = temp.next;
        }
        //退出循环时已到达链表最后
        //形成双向链表
        temp.next = heroNode2;
        heroNode2.pre = temp;


    }

    //遍历
    public void list() {
        HeroNode2 temp = head.next;
        while(temp != null) {
//			if(temp.next == null) {
//				break;
//			}
            System.out.println(temp);
            temp = temp.next;
        }
    }

    public void reverseList() {

        HeroNode2 temp = head.next;

        HeroNode2 t = null;

        while(temp != null) {
            t = temp;
            temp = temp.next;
        }

        System.out.println(t);


        while(t.pre != null) {
            System.out.println(t);
            t = t.pre;
        }
//


    }

}


class HeroNode2 {
    public int no; // 编号
    public String name;
    public String nickname; // 别称
    public HeroNode2 next; // 指向下一个节点 默认为 null
    public HeroNode2 pre; // 指向上一个节点 默认为 null

    public HeroNode2() {
        super();
        // TODO Auto-generated constructor stub
    }

    public HeroNode2(int no, String name, String nickname) {
        super();
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "HeroNode2 [no=" + no + ", name=" + name + ", nickname=" + nickname + "]";
    }

}
