package com.cn.jmw.uitls.algorithm.fastandslowpointer;

/**
 * @author jmw
 * @Description 链表慢指针
 * @date 2023年02月13日 15:09
 * @Version 1.0
 */
public class FastAndSlowPointer {

    /**
     * @Param [head]
     * @return com.cn.jmw.uitls.fastandslowpointer.ListNode
     * @exception
     * @Date 2023/2/13 15:10
     * 876. 链表的中间结点
     */
    public ListNode middleNode(ListNode head) {
        if (head.next==null){
            return head;
        }
        ListNode fast = head;
        ListNode slow = head;
        int i=1;
        while (fast.next!=null && fast.next.next !=null){
            slow = slow.next;
            fast = fast.next.next;
            if (fast.next == null){
                i= i+1;
            }else{
                i =i+2;
            }
        }
        return i%2==0?slow:slow.next;
    }
}
 class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}