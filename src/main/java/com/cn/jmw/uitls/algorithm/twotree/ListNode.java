package com.cn.jmw.uitls.algorithm.twotree;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jmw
 * @Description TODO
 * @date 2023年03月30日 11:15
 * @Version 1.0
 */
public class ListNode {
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

    //删除倒数第N个元素
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head);
        int length = getLength(head);
        ListNode cur = dummy;
        for (int i = 1; i < length - n + 1; ++i) {
            cur = cur.next;
        }
        cur.next = cur.next.next;
        ListNode ans = dummy.next;
        return ans;
    }

    //链表经典双指针用法 做差寻找 位移差值
    public ListNode removeNthFromEnd2(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head);
        ListNode first = head;
        ListNode second = dummy;
        for (int i = 0; i < n; ++i) {
            first = first.next;
        }
        while (first != null) {
            first = first.next;
            second = second.next;
        }
        second.next = second.next.next;
        ListNode ans = dummy.next;
        return ans;
    }
    public int getLength(ListNode head) {
        int length = 0;
        while (head != null) {
            ++length;
            head = head.next;
        }
        return length;
    }

    //节点逆转
    public ListNode nodeReverse(ListNode node){
        ListNode current = null, prev = null;

        while (node != null) {
            current = node;
            node = node.next;
            current.next = prev;
            prev = current;
        }
        return prev;
    }

    public ListNode nodeReverse2(ListNode head) {
        //记录pre节点和 当前节点
        ListNode current = head,pre = null;
        while (current!=null){
            //保留下一个节点
            ListNode next = current.next;
            //连接节点
            current.next = pre;
            //记录下层循环pre值
            pre = current;
            //记录下层循环current值
            current = next;
        }
        return pre;
    }

    public static void main(String[] args) {
        ListNode listNode = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        System.out.println(new ListNode().removeNthFromEnd(listNode,2));
        System.out.println(new ListNode().nodeReverse2(listNode));
    }
}