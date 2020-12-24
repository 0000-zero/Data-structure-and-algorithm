package com.at;

/**
 * @author zero
 * @create 2020-12-20 18:18
 */
public class 删除链表倒数第n个元素 {
    /*
        给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。

        示例：

        给定一个链表: 1->2->3->4->5, 和 n = 2.

        当删除了倒数第二个节点后，链表变为 1->2->3->5.
        说明：

        给定的 n 保证是有效的。

        进阶：

        你能尝试使用一趟扫描实现吗？

     */

    public static void main(String[] args) {

        ListNode head = new ListNode();

        head.next = new ListNode(1,new ListNode(2,new ListNode(3,new ListNode(4))));


        removeNthFromEnd1(head,2);

    }

    public static ListNode removeNthFromEnd1(ListNode head, int n) {
        int pos = getDelIndex(head, n);
        // 说明删除的是头节点
        if (pos == n)
            return head.next;
        return head;

    }

    //获取要删除的节点所在的位置
    public static int getDelIndex(ListNode node,int n){
        if(node == null) return 0;
        int pos = getDelIndex(node.next,n)+1;
        if(pos == n+1){
            node.next = node.next.next;
        }
        return pos;
    }

    public static ListNode removeNthFromEnd(ListNode head, int n) {


        ListNode del = head;
        ListNode ret = head;

        while (head.next != null){
            if(n<=0){
                del = del.next;
            }
            head = head.next;
            n--;
        }

        if(n == 1) return ret.next;

        del.next = del.next.next;
        return ret;

    }




}
