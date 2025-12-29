package com.example.demo.algorithm.year25.month12;

import java.util.ArrayDeque;
import java.util.ArrayList;

public class Day26 {
    public static void main(String[] args) {
        Day26 day26 = new Day26();

        // 1. 路径总和
        TreeNode root1 = new TreeNode(5,
                new TreeNode(4, new TreeNode(11, new TreeNode(7), new TreeNode(2)), null),
                new TreeNode(8, new TreeNode(13), new TreeNode(4, null, new TreeNode(1))));
        System.out.println(day26.hasPathSum(root1, 22)); // true

        // 2. 二叉树的直径
        TreeNode root2 = new TreeNode(1, new TreeNode(2, new TreeNode(4), new TreeNode(5)), new TreeNode(3));
        System.out.println(day26.diameterOfBinaryTree(root2)); // 3

        // 3. 岛屿数量
        char[][] grid = {
                {'1', '1', '1', '1', '0'},
                {'1', '1', '0', '1', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '0', '0', '0'}
        };
        System.out.println(day26.numIslands(grid)); // 1

        // 4. 最近公共祖先
        TreeNode lca = day26.lowestCommonAncestor(root1, root1.left, root1.right);
        System.out.println(lca != null ? lca.val : "null");
    }

    public static class TreeNode {
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

    /*
    112. 路径总和 (Path Sum)
    必须是"根到叶子节点"的路径。
    */
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }
        
        // 如果是叶子节点，检查剩余值是否等于当前节点值
        if (root.left == null && root.right == null) {
            return targetSum == root.val;
        }
        
        // 递归检查左右子树，目标值减去当前值
        return hasPathSum(root.left, targetSum - root.val) || 
               hasPathSum(root.right, targetSum - root.val);
    }

    /*
    543. 二叉树的直径 (Diameter of Binary Tree)
    在计算高度的过程中，更新直径。
    */
    int maxDiameter = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        maxDiameter = 0;
        calculateHeight(root);
        return maxDiameter;
    }

    // 返回节点的高度（最长路径边数）
    private int calculateHeight(TreeNode node) {
        if (node == null) return 0;
        
        int leftHeight = calculateHeight(node.left);
        int rightHeight = calculateHeight(node.right);
        
        // 直径 = 左高度 + 右高度
        maxDiameter = Math.max(maxDiameter, leftHeight + rightHeight);
        
        // 返回当前节点的高度
        return Math.max(leftHeight, rightHeight) + 1;
    }

    /*
    200. 岛屿数量 (Number of Islands)
    DFS 沉岛策略：直接修改 grid，不需要额外 visited 数组。
    */
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        
        int count = 0;
        int rows = grid.length;
        int cols = grid[0].length;
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == '1') {
                    count++;
                    dfsIslands(grid, i, j); // 沉没这个岛屿
                }
            }
        }
        return count;
    }

    private void dfsIslands(char[][] grid, int r, int c) {
        // 越界或者已经是水('0')，停止
        if (r < 0 || r >= grid.length || c < 0 || c >= grid[0].length || grid[r][c] == '0') {
            return;
        }
        
        grid[r][c] = '0'; // 标记为已访问（变成水）
        
        // 访问上下左右
        dfsIslands(grid, r + 1, c);
        dfsIslands(grid, r - 1, c);
        dfsIslands(grid, r, c + 1);
        dfsIslands(grid, r, c - 1);
    }

    /*
    236. 二叉树的最近公共祖先 (LCA)
    经典递归解法：O(N)
    */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // 1. 终止条件：越过叶子，或者找到了 p 或 q
        if (root == null || root == p || root == q) {
            return root;
        }
        
        // 2. 递归在左右子树找
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        
        // 3. 判断逻辑
        // 如果左右都非空，说明 p 和 q 分居 root 两侧 -> root 就是 LCA
        if (left != null && right != null) {
            return root;
        }
        
        // 如果只有一边非空，说明 p 和 q 都在那一边 -> 返回那边的结果
        return left != null ? left : right;
    }
}
