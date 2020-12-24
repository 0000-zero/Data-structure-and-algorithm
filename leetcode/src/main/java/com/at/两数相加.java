package com.at;

import java.util.*;

/**
 * @author zero
 * @create 2020-12-15 20:23
 */
public class 两数相加 {

    /*
        给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。

        如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。

        您可以假设除了数字 0 之外，这两个数都不会以 0 开头。

        示例：

        输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
        输出：7 -> 0 -> 8
        原因：342 + 465 = 807

     */

    public static void main(String[] args) {

        ListNode l1 = new ListNode(9, new ListNode(9, new ListNode(9,new ListNode(9,new ListNode(9,new ListNode(9,new ListNode(9)))))));

        ListNode l2 = new ListNode(9, new ListNode(9, new ListNode(9,new ListNode(9))));

//        ListNode node = addTwoNumbers(l1, l2);
        ListNode node = addTwoNumbers1(l1, l2);

        while (node != null) {
            System.out.println(node.val);
            node = node.next;
        }


    }

    public static ListNode addTwoNumbers1(ListNode l1, ListNode l2) {

        ListNode l3 = new ListNode(0);
        ListNode curr = l3;
        int temp = 0;

        while (l1 != null || l2 != null || temp != 0){
            int r1 =  l1 != null ? l1.val : 0;
            int r2 =  l2 != null ? l2.val : 0;

            int res = temp + r1 + r2;

            temp = res/10;
            curr.next = new ListNode(res%10);
            curr = curr.next;

            if(l1 != null) l1 = l1.next;
            if(l2 != null) l2 = l2.next;

        }

        return l3.next;

    }

    //错误
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {


        ArrayList<Integer> list1 = new ArrayList<>();
        ArrayList<Integer> list2 = new ArrayList<>();

        while (l1 != null) {
            list1.add(l1.val);
            l1 = l1.next;
        }
        while (l2 != null) {
            list2.add(l2.val);
            l2 = l2.next;
        }

        int len = Math.max(list1.size(), list2.size());

        int temp = 0;

        ListNode res = new ListNode();
        ListNode t = res;
        for (int i = 0; i <= len; i++) {


            int i1 = 0;
            int i2 = 0;
            if (i < list1.size()) {
                i1 = list1.get(i);
            }
            if (i < list2.size()) {
                i2 = list2.get(i);
            }
            int r = i1 + i2 + temp;

            temp = r / 10;
            ListNode node = new ListNode(r % 10);
            t.next = node;
            t = t.next;
        }

        return res.next;
    }

}


//Definition for singly-linked list.
class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    @Override
    public String toString() {
        return "ListNode{" +
                "val=" + val +
                '}';
    }
}
