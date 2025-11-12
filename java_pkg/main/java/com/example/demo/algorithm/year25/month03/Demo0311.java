package com.example.demo.algorithm.year25.month03;


import java.util.ArrayList;
import java.util.List;

public class Demo0311 {

    public static void main(String[] args) {
//        boolean interleave = new isInterleave().isInterleave("aabcc", "dbbca", "aadbbcbcac");
        int i = new numTrees().numTrees(3);
        System.out.println();
    }

}

// 96. 不同的二叉搜索树
/*
给你一个整数 n ，求恰由 n 个节点组成且节点值从 1 到 n 互不相同的 二叉搜索树 有多少种？返回满足题意的二叉搜索树的种数。

输入：n = 3
输出：5
示例 2：
输入：n = 1
输出：1
 */
class numTrees {
    public int result = 0;

    public int numTrees(int n) {
        if (n == 0) {
            return this.result;
        }
        dfs(new ArrayList<>(), 0, n - 1, n);
        return this.result;
    }

    public void dfs(List<Integer> curr, int start, int end, int n) {
        if (curr.size() == n) {
            this.result++;
            return;
        }
        if (start > end) {
            return;
        }
        for (int i = start; i <= end; i++) {
            curr.add(i);
            dfs(curr, start, i - 1, n);
            dfs(curr, i + 1, end, n);
            curr.remove(curr.size() - 1);
        }
    }
}


// 97. 交错字符串
/*
给定三个字符串 s1、s2、s3，请你帮忙验证 s3 是否是由 s1 和 s2 交错 组成的。

两个字符串 s 和 t 交错 的定义与过程如下，其中每个字符串都会被分割成若干 非空 子字符串：

s = s1 + s2 + ... + sn
t = t1 + t2 + ... + tm
|n - m| <= 1
交错 是 s1 + t1 + s2 + t2 + s3 + t3 + ... 或者 t1 + s1 + t2 + s2 + t3 + s3 + ...
注意：a + b 意味着字符串 a 和 b 连接

输入：s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
输出：true
示例 2：
[1,1,1,0,0,0]
[0,0,1,1,0,0]
[0,]
[0,]
[0,]
[0,]

输入：s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
输出：false
示例 3：

输入：s1 = "", s2 = "", s3 = ""
输出：true
 */

class isInterleave {
    public boolean isInterleave(String s1, String s2, String s3) {
        int s1Length = s1.length();
        int s2Length = s2.length();
        int s3Length = s3.length();
        if (s3Length == 0) {
            return true;
        }
        if (s1Length == 0 && s2Length == 0) {
            return false;
        }
        if (s1Length + s2Length != s3Length) {
            return false;
        }
        boolean[][] dp = new boolean[s1Length + 1][s2Length + 1];
        dp[0][0] = true;
        for (int row = 0; row < s1Length + 1; row++) {
            for (int col = 0; col < s2Length + 1; col++) {
                if (row == 0 && col == 0) {
                    continue;
                }
                dp[row][col] = (row > 0 && dp[row - 1][col] && s1.charAt(row - 1) == s3.charAt(row + col - 1)) ||
                        (col > 0 && dp[row][col - 1] && s2.charAt(col - 1) == s3.charAt(row + col - 1));
            }
        }
        return dp[s1Length][s2Length];
    }
}

// 335. 路径交叉
/*
给你一个整数数组 distance 。

从 X-Y 平面上的点 (0,0) 开始，先向北移动 distance[0] 米，
然后向西移动 distance[1] 米，向南移动 distance[2] 米，向东移动 distance[3] 米，持续移动。
也就是说，每次移动后你的方位会发生逆时针变化。

判断你所经过的路径是否相交。如果相交，返回 true ；否则，返回 false 。
输入：distance = [2,1,1,2]
输出：true

输入：distance = [1,2,3,4]
输出：false

输入：distance = [1,1,1,1]
输出：true

class Solution {
    public boolean isSelfCrossing(int[] distance) {
        int n = distance.length;
        for (int i = 3; i < n; ++i) {
            // 第 1 类路径交叉的情况
            if (distance[i] >= distance[i - 2] && distance[i - 1] <= distance[i - 3]) {
                return true;
            }

            // 第 2 类路径交叉的情况
            if (i == 4 && (distance[3] == distance[1]
                && distance[4] >= distance[2] - distance[0])) {
                return true;
            }

            // 第 3 类路径交叉的情况
            if (i >= 5 && (distance[i - 3] - distance[i - 5] <= distance[i - 1]
                && distance[i - 1] <= distance[i - 3]
                && distance[i] >= distance[i - 2] - distance[i - 4]
                && distance[i - 2] > distance[i - 4])) {
                return true;
            }
        }
        return false;
    }
}
 */

class isSelfCrossing {
    public boolean isSelfCrossing(int[] distance) {

        int length = distance.length;
        ArrayList<int[]> xList = new ArrayList<>(length / 2 + 1);
        ArrayList<int[]> yList = new ArrayList<>(length / 2 + 1);
        int x = 0, y = 0;
        int dir = 1;//1 y+  2 x-  3  y-   4x+
        for (int item : distance) {
            switch (dir) {
                case 1:
                    boolean check = check(x, new int[]{y, y + item}, xList);
                    if (check) {
                        return true;
                    }

                case 2:
                case 3:
                case 4:
            }


        }

        return true;

    }

    public boolean check(int x, int[] range, ArrayList<int[]> list) {
        return true;

    }
}

