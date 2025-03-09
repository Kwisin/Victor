package com.example.demo.study.demo2503;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Demo0309 {

    public static void main(String[] args) {
        List<TreeNode> treeNodes = new generateTrees().generateTrees(3);
        System.out.println();
    }

}

// 95. 不同的二叉搜索树 II
/*
给你一个整数 n ，请你生成并返回所有由 n 个节点组成且节点值从 1 到 n 互不相同的不同 二叉搜索树 。可以按 任意顺序 返回答案。

输入：n = 3
输出：[[1,null,2,null,3],[1,null,3,2],[2,1,3],[3,1,null,null,2],[3,2,null,1]]
示例 2：

输入：n = 1
输出：[[1]]
 */
class TreeNode {
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
}

class generateTrees {
    List<TreeNode> result = new ArrayList<TreeNode>();

    public List<TreeNode> generateTrees(int n) {
        dfs(1, n, new ArrayList<>(), n);
        return result;
    }

    public void dfs(int start, int end, List<TreeNode> curr, int n) {

        if (start > n || end > n || start < 0 || end < 0) {
            return;
        }
        if (curr.size() == n) {
            this.result.add(curr.get(0));
            return;
        }
        if (start > end) {
            return;
        }

        for (int i = start; i <= end; i++) {
            TreeNode treeNode = new TreeNode(i);
            if (!curr.isEmpty()) {
                TreeNode pre = curr.get(curr.size() - 1);
                if (pre.val > i) {
                    pre.left = treeNode;
                } else {
                    pre.right = treeNode;
                }
            }
            curr.add(treeNode);
            dfs(start, i - 1, curr, n);
            dfs(i + 1, end, curr, n);
            curr.remove(curr.size() - 1);
        }
    }
}

// 149. 直线上最多的点数
/*
给你一个数组 points ，其中 points[i] = [xi, yi] 表示 X-Y 平面上的一个点。求最多有多少个点在同一条直线上

输入：points = [[1,1],[2,2],[3,3]]
输出：3

输入：points = [[1,1],[3,2],[5,3],[4,1],[2,3],[1,4]]
输出：4
 */
class maxPoints {
    public int maxPoints(int[][] points) {
        return 0;

    }
}


