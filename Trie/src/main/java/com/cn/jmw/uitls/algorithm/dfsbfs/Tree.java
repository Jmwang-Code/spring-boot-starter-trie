package com.cn.jmw.uitls.algorithm.dfsbfs;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @author jmw
 * @Description TODO
 * @date 2023年02月22日 17:31
 * @Version 1.0
 */
public class Tree {

    /**
     * 完全二叉树，非同父节点，连接关系，
     * 或者两个（非同父）节点路径问题
     */
    public Node connect1(Node root) {
        Node node = root;
        Queue<Node> queue = new ArrayDeque();
        queue.offer(node);
        int count = queue.size();
        while (!queue.isEmpty()){
            for (int i = 0; i < count; i++) {
                Node poll = queue.poll();
                if (i==count-1){
                    poll.next = null;
                }else {
                    Node poll1 = queue.peek();
                    poll.next = poll1;
                }
                if (poll.left!=null){
                    queue.offer(poll.left);
                    queue.offer(poll.right);
                }
            }
            count = queue.size();
        }
        return root;
    }

    public Node connect2(Node root) {
        if (root==null)return root;
        if(root.left!=null){
            root.left.next = root.right;
            root.right.next = root.next!=null?root.next.left:null;
            connect2(root.left);
            connect2(root.right);
        }
        return root;
    }

    public static void main(String[] args) {
//        Node node7 = new Node(7);
//        Node node6 = new Node(6);
//        Node node5 = new Node(5);
//        Node node4 = new Node(4);
//        Node node3 = new Node(3,node6,node7,null);
//        Node node2 = new Node(2,node4,node5,null);
//        Node node1 = new Node(1,node2,node3,null);
    }
}


class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {
    }

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
}