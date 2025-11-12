package com.example.demo.algorithm.year24.demo2412;


import java.util.*;

public class Demo1210 {

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        TreeNode nextNode2 = new TreeNode(2);
        TreeNode nextNode3 = new TreeNode(3);
        TreeNode nextNode4 = new TreeNode(4);
        TreeNode nextNode5 = new TreeNode(5);
        TreeNode nextNode6 = new TreeNode(6);
        node1.left = nextNode2;
        node1.right = nextNode3;

        nextNode2.left = nextNode4;

        nextNode3.right = nextNode5;

        nextNode5.right = nextNode6;

        sumNumbers flatten = new sumNumbers();
        int i = flatten.sumNumbers(node1);
        System.out.println();
    }


}

//114
/*
给你二叉树的根结点 root ，请你将它展开为一个单链表：

展开后的单链表应该同样使用 TreeNode ，其中 right 子指针指向链表中下一个结点，而左子指针始终为 null 。
展开后的单链表应该与二叉树 先序遍历 顺序相同

输入：root = [1,2,5,3,4,null,6]
输出：[1,null,2,null,3,null,4,null,5,null,6]
示例 2：

输入：root = []
输出：[]
示例 3：

输入：root = [0]
输出：[0]

提示：

树中结点数在范围 [0, 2000] 内
-100 <= Node.val <= 100


进阶：你可以使用原地算法（O(1) 额外空间）展开这棵树吗？
 */

class flatten {
    public void flatten(TreeNode root) {
        flattenSubTree(root);
    }

    public TreeNode flattenSubTree(TreeNode root) {
        if (root == null) {
            return null;
        }

        TreeNode left = root.left;
        TreeNode right = root.right;
        if (left == null && right == null) {
            return root;
        }
        root.left = null;
        root.right = null;

        TreeNode leftTreeNode = flattenSubTree(left);
        TreeNode rightTreeNode = flattenSubTree(right);
        if (leftTreeNode != null) {
            root.right = leftTreeNode;
        }

        if (rightTreeNode != null) {
            TreeNode curr = root;
            while (curr.right != null) {
                curr = curr.right;
            }
            curr.right = rightTreeNode;
        }

        return root;
    }
}

// 129
/*
给你一个二叉树的根节点 root ，树中每个节点都存放有一个 0 到 9 之间的数字。
每条从根节点到叶节点的路径都代表一个数字：

例如，从根节点到叶节点的路径 1 -> 2 -> 3 表示数字 123 。
计算从根节点到叶节点生成的 所有数字之和 。

叶节点 是指没有子节点的节点。

输入：root = [1,2,3]
输出：25
解释：
从根到叶子节点路径 1->2 代表数字 12
从根到叶子节点路径 1->3 代表数字 13
因此，数字总和 = 12 + 13 = 25

示例 2：
输入：root = [4,9,0,5,1]
输出：1026
解释：
从根到叶子节点路径 4->9->5 代表数字 495
从根到叶子节点路径 4->9->1 代表数字 491
从根到叶子节点路径 4->0 代表数字 40
因此，数字总和 = 495 + 491 + 40 = 1026


提示：

树中节点的数目在范围 [1, 1000] 内
0 <= Node.val <= 9
树的深度不超过 10
 */


class sumNumbers {
    public int sumNumbers(TreeNode root) {

        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        int result = 0;
        while (!queue.isEmpty()) {
            TreeNode poll = queue.poll();
            TreeNode left = poll.left;
            TreeNode right = poll.right;
            if (left == null && right == null) {
                // 叶子结点
                result += poll.val;
            } else {
                if (left != null) {
                    left.val += poll.val * 10;
                    queue.add(left);
                }
                if (right != null) {
                    right.val += poll.val * 10;
                    queue.add(right);
                }

            }
        }

        return result;
    }
}