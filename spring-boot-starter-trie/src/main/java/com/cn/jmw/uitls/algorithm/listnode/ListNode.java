package com.cn.jmw.uitls.algorithm.listnode;

/**
 * @author jmw
 * @Description TODO
 * @date 2023年04月02日 23:15
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


    public ListNode reverseBetween(ListNode head, int left, int right) {
        // 设置 dummyNode 是这一类问题的一般做法
        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = head;
        ListNode pre = dummyNode;
        for (int i = 0; i < left - 1; i++) {
            pre = pre.next;
        }
        ListNode cur = pre.next;
        ListNode next;
        for (int i = 0; i < right - left; i++) {
            next = cur.next;
            cur.next = next.next;
            next.next = pre.next;
            pre.next = next;
        }
        return dummyNode.next;
    }

    //对比时间复杂度 n-1 < (n*n)/2 + n/2
    //存在 n>0 成立以上不等式

    public ListNode mergeKLists(ListNode[] lists) {
        ListNode ans = null;
        for (int i = 0; i < lists.length; ++i) {
            ans = mergeTwoLists(ans, lists[i]);
        }
        return ans;
    }

    public ListNode mergeTwoLists(ListNode a, ListNode b) {
        if (a == null || b == null) {
            return a != null ? a : b;
        }
        ListNode head = new ListNode(0);
        ListNode tail = head, aPtr = a, bPtr = b;
        while (aPtr != null && bPtr != null) {
            if (aPtr.val < bPtr.val) {
                tail.next = aPtr;
                aPtr = aPtr.next;
            } else {
                tail.next = bPtr;
                bPtr = bPtr.next;
            }
            tail = tail.next;
        }
        tail.next = (aPtr != null ? aPtr : bPtr);
        return head.next;
    }

    //分而治之，在合并
    public ListNode mergeKLists2(ListNode[] lists) {
        if (lists.length==0 && lists==null)return new ListNode();
        return divideAndRule(lists,0,lists.length-1);
    }

    //分治法
    public ListNode divideAndRule(ListNode[] listNode,int start,int end){
        if (start>end)return null;
        if (start==end)return listNode[end];

        int mid = (start+end)/2;
        ListNode left = divideAndRule(listNode,start,mid);
        ListNode right = divideAndRule(listNode,mid+1,end);
        return merge(left,right);
    }

    public ListNode merge(ListNode left,ListNode right){
        ListNode temp = new ListNode(-1);
        ListNode cur = temp;
        while (left!=null&&right!=null){

            if (left.val>right.val){
                cur.next = right;
                right = right.next;
            }else {
                cur.next = left;
                left = left.next;
            }
            cur = cur.next;
        }
        cur.next = left!=null?left:right;
        return temp.next;
    }

    public static void main(String[] args) {
        ListNode listNode = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        new ListNode().reverseBetween(listNode, 2, 4);

        ListNode listNode1 = new ListNode(1, new ListNode(2, new ListNode(3, null)));
        ListNode listNode2 = new ListNode(1, new ListNode(1, new ListNode(5, null)));
        ListNode listNode3 = new ListNode(2, new ListNode(3, null));
        ListNode[] list = new ListNode[]{listNode1, listNode2, listNode3};
        System.out.println(new ListNode().mergeKLists(list));
    }
}
