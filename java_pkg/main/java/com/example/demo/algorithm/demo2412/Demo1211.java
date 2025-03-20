package com.example.demo.algorithm.demo2412;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Demo1211 {

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(3);

        TreeNode nextNode2 = new TreeNode(5);
        TreeNode nextNode3 = new TreeNode(1);
        node1.left = nextNode2;
        node1.right = nextNode3;

        TreeNode nextNode4 = new TreeNode(6);
        TreeNode nextNode5 = new TreeNode(2);
        nextNode2.left = nextNode4;
        nextNode2.right = nextNode5;

        TreeNode nextNode6 = new TreeNode(0);
        TreeNode nextNode7 = new TreeNode(8);
        nextNode3.left = nextNode6;
        nextNode3.right = nextNode7;

        TreeNode nextNode8 = new TreeNode(7);
        TreeNode nextNode9 = new TreeNode(4);

        nextNode5.left = nextNode8;
        nextNode5.right = nextNode9;

        TreeNode treeNode = new lowestCommonAncestor().lowestCommonAncestor(node1, nextNode4, nextNode6);
        System.out.println();
    }


}

//124
/*
二叉树中的 路径 被定义为一条节点序列，序列中每对相邻节点之间都存在一条边。同一个节点在一条路径序列中 至多出现一次。
该路径 至少包含一个 节点，且不一定经过根节点。
路径和 是路径中各节点值的总和。
给你一个二叉树的根节点 root ，返回其 最大路径和

输入：root = [1,2,3]
输出：6
解释：最优路径是 2 -> 1 -> 3 ，路径和为 2 + 1 + 3 = 6

输入：root = [-10,9,20,null,null,15,7]
输出：42
解释：最优路径是 15 -> 20 -> 7 ，路径和为 15 + 20 + 7 = 42

 */

class maxPathSum {
    private int max = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        maxPathSumTest(root);
        return this.max;
    }

    public int maxPathSumTest(TreeNode root) {
        if (root == null) {
            return 0;
        }
        TreeNode left = root.left;
        TreeNode right = root.right;
        int currVal = root.val;
        if (left == null && right == null) {
            this.max = Math.max(currVal, this.max);
            return currVal;
        }

        int leftMax = maxPathSumTest(left);
        int rightMax = maxPathSumTest(right);
        int sideMax = 0;
        int nodeMax = 0;
        if (left == null) {
            sideMax = Math.max(currVal, rightMax + currVal); // 路径可连接的最大值
            nodeMax = Math.max(rightMax, sideMax); // 节点下的最大值
        } else if (right == null) {
            sideMax = Math.max(currVal, leftMax + currVal); // 路径可连接的最大值
            nodeMax = Math.max(leftMax, sideMax); // 节点下的最大值
        } else {
            sideMax = Math.max(Math.max(leftMax, rightMax) + currVal, currVal); // 路径可连接的最大值
            nodeMax = Math.max(Math.max(Math.max(leftMax, rightMax), sideMax), leftMax + rightMax + currVal); // 节点下的最大值
        }

        this.max = Math.max(nodeMax, this.max);
        return sideMax;
    }
}


//173
/*
实现一个二叉搜索树迭代器类BSTIterator ，表示一个按中序遍历二叉搜索树（BST）的迭代器：
BSTIterator(TreeNode root) 初始化 BSTIterator 类的一个对象。BST 的根节点 root 会作为构造函数的一部分给出。
指针应初始化为一个不存在于 BST 中的数字，且该数字小于 BST 中的任何元素。
boolean hasNext() 如果向指针右侧遍历存在数字，则返回 true ；否则返回 false 。
int next()将指针向右移动，然后返回指针处的数字。
注意，指针初始化为一个不存在于 BST 中的数字，所以对 next() 的首次调用将返回 BST 中的最小元素。

你可以假设 next() 调用总是有效的，也就是说，当调用 next() 时，BST 的中序遍历中至少存在一个下一个数字。

输入
["BSTIterator", "next", "next", "hasNext", "next", "hasNext", "next", "hasNext", "next", "hasNext"]
[[[7, 3, 15, null, null, 9, 20]],  [],  [],  [],  [],  [],   [],  [],   [],   [] ]
输出
[null,                             3,   7,  true, 9,  true,  15,  true, 20,  false]

解释
BSTIterator bSTIterator = new BSTIterator([7, 3, 15, null, null, 9, 20]);
bSTIterator.next();    // 返回 3
bSTIterator.next();    // 返回 7
bSTIterator.hasNext(); // 返回 True
bSTIterator.next();    // 返回 9
bSTIterator.hasNext(); // 返回 True
bSTIterator.next();    // 返回 15
bSTIterator.hasNext(); // 返回 True
bSTIterator.next();    // 返回 20
bSTIterator.hasNext(); // 返回 False


进阶：

你可以设计一个满足下述条件的解决方案吗？next() 和 hasNext() 操作均摊时间复杂度为 O(1) ，
并使用 O(h) 内存。其中 h 是树的高度。
 */

class BSTIteratorNode {
    public TreeNode next;

    public BSTIteratorNode(TreeNode next) {
        this.next = next;
    }
}

class BSTIterator {
    private TreeNode head;
    private TreeNode tail;

    public BSTIterator(TreeNode root) {
        // 找出最小节点
        TreeNode current = root;
        while (current.left != null) {
            current = current.left;
        }
        head = new TreeNode(current.val - 1);

        // 中序遍历
        MidScan(root);
        System.out.println();

    }

    public void MidScan(TreeNode root) {
        if (root == null) {
            return;
        }
        TreeNode left = root.left;
        TreeNode right = root.right;

        // 先遍历左节点
        MidScan(left);

        // 中节点
        TreeNode newNode = new TreeNode(root.val);
        if (tail == null) {
            tail = newNode;
            head.right = tail;
        } else {
            tail.right = newNode;
            tail = newNode;
        }


        // 再遍历右节点
        MidScan(right);

    }


    public int next() {
        int result = head.right.val;
        head = head.right;
        return result;
    }

    public boolean hasNext() {
        return head.right != null;
    }
}


// 236
/*
给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。

百度百科中最近公共祖先的定义为：“对于有根树 T 的两个节点 p、q，最近公共祖先表示为一个节点 x，
满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”

输入：root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
输出：3
解释：节点 5 和节点 1 的最近公共祖先是节点 3 。

输入：root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
输出：5
解释：节点 5 和节点 4 的最近公共祖先是节点 5 。因为根据定义最近公共祖先节点可以为节点本身。

输入：root = [1,2], p = 1, q = 2
输出：1
 */

class lowestCommonAncestor {
    List<TreeNode> midScanList = new ArrayList<>();
    Map<TreeNode, Integer> nodeIndexMap = new HashMap<>();


    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        midScan(root);

        return lowestCommonAncestorTest(root, p, q);
    }

    public TreeNode lowestCommonAncestorTest(TreeNode root, TreeNode p, TreeNode q) {
        if (midScanList.size() == 0) {
            return null;
        }

        Integer rootIndex = nodeIndexMap.get(root);
        Integer pIndex = nodeIndexMap.get(p);
        Integer qIndex = nodeIndexMap.get(q);

        if ((Math.min(pIndex, qIndex) <= rootIndex) && (Math.max(pIndex, qIndex) >= rootIndex)) {
            return root;
        } else if (pIndex < rootIndex && qIndex < rootIndex) {
            return lowestCommonAncestorTest(root.left, p, q);
        }
        return lowestCommonAncestorTest(root.right, p, q);
    }

    public void midScan(TreeNode root) {
        if (root == null) {
            return;
        }
        TreeNode left = root.left;
        TreeNode right = root.right;
        midScan(left);

        this.midScanList.add(root);
        this.nodeIndexMap.put(root, this.midScanList.size() - 1);

        midScan(right);

    }
}