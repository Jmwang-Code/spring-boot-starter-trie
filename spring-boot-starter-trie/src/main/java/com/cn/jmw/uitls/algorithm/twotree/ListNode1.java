package com.cn.jmw.uitls.algorithm.twotree;

/**
 * @author jmw
 * @Description TODO
 * @date 2023年03月30日 11:15
 * @Version 1.0
 */
public class ListNode1 {
    int val;
    ListNode1 next;

    ListNode1() {
    }

    ListNode1(int val) {
        this.val = val;
    }

    ListNode1(int val, ListNode1 next) {
        this.val = val;
        this.next = next;
    }

    //删除倒数第N个元素
    public ListNode1 removeNthFromEnd(ListNode1 head, int n) {
        ListNode1 dummy = new ListNode1(0, head);
        int length = getLength(head);
        ListNode1 cur = dummy;
        for (int i = 1; i < length - n + 1; ++i) {
            cur = cur.next;
        }
        cur.next = cur.next.next;
        ListNode1 ans = dummy.next;
        return ans;
    }

    //链表经典双指针用法 做差寻找 位移差值
    public ListNode1 removeNthFromEnd2(ListNode1 head, int n) {
        ListNode1 dummy = new ListNode1(0, head);
        ListNode1 first = head;
        ListNode1 second = dummy;
        for (int i = 0; i < n; ++i) {
            first = first.next;
        }
        while (first != null) {
            first = first.next;
            second = second.next;
        }
        second.next = second.next.next;
        ListNode1 ans = dummy.next;
        return ans;
    }
    public int getLength(ListNode1 head) {
        int length = 0;
        while (head != null) {
            ++length;
            head = head.next;
        }
        return length;
    }

    //节点逆转
    public ListNode1 nodeReverse(ListNode1 node){
        ListNode1 current = null, prev = null;

        while (node != null) {
            current = node;
            node = node.next;
            current.next = prev;
            prev = current;
        }
        return prev;
    }

    public ListNode1 nodeReverse2(ListNode1 head) {
        //记录pre节点和 当前节点
        ListNode1 current = head,pre = null;
        while (current!=null){
            //保留下一个节点
            ListNode1 next = current.next;
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
        ListNode1 listNode1 = new ListNode1(1, new ListNode1(2, new ListNode1(3, new ListNode1(4, new ListNode1(5)))));
        System.out.println(new ListNode1().removeNthFromEnd(listNode1,2));
        System.out.println(new ListNode1().nodeReverse2(listNode1));
    }
}