package com.example.demo.algorithm.demo25.demo2503;


import java.util.ArrayList;
import java.util.List;

public class Demo0309 {

    public static void main(String[] args) {
//        List<TreeNode> treeNodes = new generateTrees().generateTrees(3);
        int i = new maxPoints().maxPoints(new int[][]{{1, 1}, {3, 2}, {5, 3}, {4, 1}, {2, 3}, {1, 4}});
//        int i = new maxPoints().maxPoints(new int[][]{{1, 1}, {2, 2}, {3, 3}});
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
        return dfs(1, n);
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

    public List<TreeNode> dfs(int start, int end) {
        ArrayList<TreeNode> result = new ArrayList<>();
        if (start > end) {
            result.add(null);
            return result;
        }
        for (int i = start; i <= end; i++) {
            List<TreeNode> left = dfs(start, i - 1);
            List<TreeNode> right = dfs(i + 1, end);
            for (TreeNode l : left) {
                for (TreeNode r : right) {
                    TreeNode tempHead = new TreeNode(i);
                    tempHead.left = l;
                    tempHead.right = r;
                    result.add(tempHead);
                }
            }
        }
        return result;
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
        ArrayList<ArrayList<int[]>> arrayLists = new ArrayList<>();
        ArrayList<Integer> cnt = new ArrayList<>();
        int result = Integer.MIN_VALUE;
        for (int[] item : points) {
            int size = arrayLists.size();
            for (int i = 0; i < size; i++) {
                ArrayList<int[]> target = arrayLists.get(i);
                if (check(target, item)) {
                    if (target.size() <= 1) {
                        ArrayList<int[]> ints = new ArrayList<>(target);
                        ints.add(item);
                        arrayLists.add(ints);
                        cnt.add(2);
                        result = Math.max(result, 2);
                    } else {
                        cnt.set(i, cnt.get(i) + 1);
                        result = Math.max(result, cnt.get(i));
                    }
                }
            }
            // 作为新起点
            ArrayList<int[]> ints = new ArrayList<>();
            ints.add(item);
            arrayLists.add(ints);
            cnt.add(ints.size());
            result = Math.max(result, 1);
        }
        return result;
    }

    public boolean check(List<int[]> target, int[] item) {
        if (target.size() <= 1) {
            return true;
        }
        int[] ints = target.get(0);
        int[] ints1 = target.get(1);
        int i0 = ints1[0] - ints[0];
        int i1 = ints1[1] - ints[1];
        int j0 = item[0] - ints[0];
        int j1 = item[1] - ints[1];
        return j1 * i0 == i1 * j0;
    }
}


