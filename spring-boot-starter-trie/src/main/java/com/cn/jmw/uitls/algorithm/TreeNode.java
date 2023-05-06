package com.cn.jmw.uitls.algorithm;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author jmw
 * @Description TODO
 * @date 2023年04月07日 10:48
 * @Version 1.0
 */
public class TreeNode {

    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    int pre = -1, ans = Integer.MAX_VALUE;

    public int getMinimumDifference(TreeNode root) {
        dfs(root);
        return ans;
    }

    public void dfs(TreeNode node) {
        if (node == null) return;
        dfs(node.left);
        if (pre == -1) {
            pre = node.val;
        } else {
            ans = Math.min(ans, node.val - pre);
            pre = node.val;
        }
        dfs(node.right);
    }

    int ansroot;
    int rootvalue;

    public int findSecondMinimumValue(TreeNode root) {
        ans = -1;
        rootvalue = root.val;
        dfss(root);
        return ans;
    }

    public void dfss(TreeNode node) {
        if (node == null) {
            return;
        }
        if (ans != -1 && node.val >= ans) {
            return;
        }
        if (node.val > rootvalue) {
            ans = node.val;
        }
        dfss(node.left);
        dfss(node.right);
    }


    List<Integer> list = new ArrayList<>();
    int index = 0;

    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        List<Integer> seq1 = new ArrayList<Integer>();
        if (root1 != null) {
            dfsLeafSimilar(root1, seq1);
        }
        List<Integer> seq2 = new ArrayList<Integer>();
        if (root2 != null) {
            dfsLeafSimilar(root2, seq2);
        }
        return seq1.equals(seq2);
    }

    public void dfsLeafSimilar(TreeNode node, List<Integer> seq) {
        if (node.left == null && node.right == null) {
            seq.add(node.val);
        } else {
            if (node.left != null) {
                dfsLeafSimilar(node.left, seq);
            }
            if (node.right != null) {
                dfsLeafSimilar(node.right, seq);
            }
        }
    }

    public int rangeSumBST(TreeNode root, int low, int high) {
//递归边界条件判断
        if (root == null) return 0;
//当前节点以及他的右子树的值都太大了，不要了
        if (root.val > high) {
            return rangeSumBST(root.left, low, high);
        } //当前节点以及他的左子树的值都太小了，也不要了
        if (root.val < low) {
            return rangeSumBST(root.right, low, high);
        } //如果当前节点值在[low, high]之间，就留下
        return root.val + rangeSumBST(root.left, low, high) + rangeSumBST(root.right, low, high);
    }

    int indexX = -1, indexY = -2;

    public boolean isCousins(TreeNode root, int x, int y) {
        dfsx(root, x, 0);
        dfsy(root, y, 0);
        return indexX == indexY;
    }

    void dfsx(TreeNode root, int x, int index) {
        if (root == null) return;
        if (root.val == x) {
            indexX = index;
        }
        dfsx(root.left, x, index + 1);
        dfsx(root.right, x, index + 1);
    }


    void dfsy(TreeNode root, int x, int index) {
        if (root == null) return;
        if (root.val == x) {
            indexY = index;
        }
        dfsy(root.left, x, index + 1);
        dfsy(root.right, x, index + 1);
    }

    List<List<Integer>> arr = new ArrayList();

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        dfs(root, targetSum, 0, new ArrayList<>());
        return arr;
    }

    public void dfs(TreeNode root, int targetSum, int sum, List<Integer> list) {
        if (root == null) return;
        list.add(root.val);
        if ((root.left == null || root.right == null) && targetSum - root.val == sum) {

            List temp = new ArrayList<Integer>();
            temp.addAll(list);
            arr.add(temp);
        }
        dfs(root.left, targetSum, sum + root.val, list);
        dfs(root.right, targetSum, sum + root.val, list);
        list.remove(list.size() - 1);
    }

    TreeNode pre1;

    public void flatten(TreeNode root) {
        dfsflatten(root);
    }

    public void dfsflatten(TreeNode root) {
        if (root == null) return;
        root.right = root.left;
        root.left = null;
        pre1 = root;
        dfsflatten(root.left);
        dfsflatten(root.right);
    }

    public boolean isBalanced(TreeNode root) {
        return dfsisBalanced(root) >= 0;
    }

    public int dfsisBalanced(TreeNode root) {
        if (root == null) return 0;
        int left = dfsisBalanced(root.left);
        int right = dfsisBalanced(root.right);
        if (left == -1 || right == -1 || Math.abs(left - right) > 1) {
            return -1;
        }
        return Math.max(right,left)+1;
    }

    public int sumNumbers(TreeNode root) {
        return dfssumNumbers(root,0);
    }

    public int dfssumNumbers(TreeNode root,int pre){
        if (root==null)return 0;
        if (root.right==null&&root.left==null){
            return pre*10 + root.val;
        }else {
            return dfssumNumbers(root.left,root.val)+ dfssumNumbers(root.right,root.val);
        }
    }

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(1, new TreeNode(0, null, null), new TreeNode(48, new TreeNode(12, null, null), new TreeNode(49, null, null)));
//        int minimumDifference = new TreeNode().getMinimumDifference(treeNode);
//        System.out.println(minimumDifference);
//
//        SimpleDateFormat s1 = new SimpleDateFormat("ah\"时\"mm\"分\"");
//        System.out.println(s1.format(new Date()));

        System.out.println(new TreeNode().findSecondMinimumValue(treeNode));


        TreeNode treeNode3 = new TreeNodeCreate(new int[]{3, 5, 1, 6, 7, 4, 2, 0, 0, 0, 0, 0, 0, 9, 11, 0, 0, 8, 10}).create();
        TreeNode treeNode4 = new TreeNodeCreate(new int[]{3, 5, 1, 6, 2, 9, 8, 0, 0, 7, 4}).create();
        System.out.println(new TreeNode().leafSimilar(treeNode3, treeNode4));

        TreeNode treeNode5 = new TreeNodeCreate(new int[]{10, 5, 15, 3, 7, 0, 18}).create();
        System.out.println(new TreeNode().rangeSumBST(treeNode5, 7, 15));

        TreeNode treeNode6 = new TreeNodeCreate(new int[]{1, 2, 3, 0, 4}).create();
        System.out.println(new TreeNode().isCousins(treeNode6, 2, 3));

        TreeNode treeNode7 = new TreeNodeCreate(new int[]{5, 4, 8, 11, 0, 13, 4, 7, 2, 0, 0, 5, 1}).create();
        System.out.println(new TreeNode().pathSum(treeNode7, 22));

        TreeNode treeNode8 = new TreeNodeCreate(new int[]{1, 2, 5, 3, 4, 0, 6}).create();
        new TreeNode().flatten(treeNode8);

        TreeNode treeNode9 = new TreeNodeCreate(new int[]{4,9,0,5,1}).create();
        new TreeNode().isBalanced(treeNode9);


        System.out.println(new TreeNode().sumNumbers(treeNode9));
    }

    static class TreeNodeCreate {
        int[] array;

        public TreeNodeCreate(int[] array) {
            this.array = array;
        }

        public TreeNode create() {
            if (array.length == 0) {
                return null;
            }
            TreeNode treeNode = new TreeNode(array[0]);
            Deque<TreeNode> deque = new LinkedList<>();
            deque.add(treeNode);
            // 先左后右
            boolean isleft = true;
            for (int i = 1; i < array.length; i++) {
                TreeNode peek = deque.getFirst();
                if (isleft) {
                    if (array[i] != 0) {
                        peek.left = new TreeNode(array[i]);
                        //将左节点添加至队尾
                        deque.offer(peek.left);
                    }
                    isleft = false;

                } else {
                    if (array[i] != 0) {
                        peek.right = new TreeNode(array[i]);
                        deque.offer(peek.right);
                    }
                    // 删除队头第一个元素
                    deque.pollFirst();
                    isleft = true;
                }
            }
            return treeNode;
        }
    }
}
