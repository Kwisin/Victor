package com.example.demo.study.demo2501;


import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Demo0113 {

    public static void main(String[] args) {
//        List<List<Integer>> lists = new combinationSum().combinationSum(new int[]{2, 3, 6, 7}, 7);
        int i = new totalNQueens().totalNQueens(4);
        System.out.println();
    }


}

// 39
/*
给你一个 无重复元素 的整数数组 candidates 和一个目标整数 target，
找出 candidates 中可以使数字和为目标数 target 的 所有 不同组合 ，并以列表形式返回。
你可以按 任意顺序 返回这些组合。
candidates 中的 同一个 数字可以 无限制重复被选取。如果至少一个数字的被选数量不同，则两种组合是不同的。
对于给定的输入，保证和为 target 的不同组合数少于 150 个。


示例 1：
输入：candidates = [2,3,6,7], target = 7
输出：[[2,2,3],[7]]
解释：
2 和 3 可以形成一组候选，2 + 2 + 3 = 7 。注意 2 可以使用多次。
7 也是一个候选， 7 = 7 。
仅有这两种组合。

示例 2：
输入: candidates = [2,3,5], target = 8
输出: [[2,2,2,2],[2,3,3],[3,5]]

示例 3：
输入: candidates = [2], target = 1
输出: []
 */

class combinationSum {
    public List<List<Integer>> result;

    public combinationSum() {
        this.result = new ArrayList<>();
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        if (candidates.length == 0 || target <= 0) {
            return this.result;
        }

        Arrays.sort(candidates);
        if (target < candidates[0]) {
            return this.result;
        }

        dfs(candidates, target, 0, new ArrayDeque<>(), 0);

        return this.result;
    }

    public void dfs(int[] candidates, int target, int index, ArrayDeque<Integer> curr, int currVal) {
        if (currVal == target) {
            this.result.add(new ArrayList<>(curr));
            return;
        }
        for (int i = index; i < candidates.length; i++) {
            if (candidates[i] > target - currVal) {
                break;
            }
            curr.addLast(candidates[i]);
            currVal += candidates[i];
            dfs(candidates, target, i, curr, currVal);
            curr.removeLast();
            currVal -= candidates[i];
        }
    }
}

// 52
/*
n 皇后问题 研究的是如何将 n 个皇后放置在 n × n 的棋盘上，并且使皇后彼此之间不能相互攻击。
给你一个整数 n ，返回 n 皇后问题 不同的解决方案的数量。
 */

class totalNQueens {
    public int[][] valid;
    public int result = 0;

    public int totalNQueens(int n) {
        if (n == 0 || n == 1) {
            return n;
        }
        this.valid = new int[n][n];
        dfs(n, 0);

        return this.result;
    }

    public void dfs(int n, int index) {
        if (index == n) {
            this.result++;
            return;
        }
        for (int i = 0; i < n; i++) {
            if (this.valid[index][i] == 1) {
                continue;
            }
            setValid(index, i, n);
            dfs(n, index + 1);
            reverseValid(index, i, n);
        }

    }

    public boolean checkPositionValid(int x, int y, int length) {
        return x >= 0 && x < length && y >= 0 && y < length;

    }

    public void setValid(int x, int y, int length) {
        this.valid[x][y] = 1;
        for (int i = 1; i < length; i++) {
            if (checkPositionValid(x - i, y - i, length)) {
                this.valid[x - i][y - i] = 1;
            }
            if (checkPositionValid(x - i, y, length)) {
                this.valid[x - i][y] = 1;
            }
            if (checkPositionValid(x - i, y + i, length)) {
                this.valid[x - i][y + i] = 1;
            }

            if (checkPositionValid(x, y - i, length)) {
                this.valid[x][y - i] = 1;
            }
            if (checkPositionValid(x, y + i, length)) {
                this.valid[x][y + i] = 1;
            }

            if (checkPositionValid(x + i, y + i, length)) {
                this.valid[x + i][y + i] = 1;
            }
            if (checkPositionValid(x + i, y, length)) {
                this.valid[x + i][y] = 1;
            }
            if (checkPositionValid(x + i, y - i, length)) {
                this.valid[x + i][y - i] = 1;
            }
        }

    }

    public void reverseValid(int x, int y, int length) {
        this.valid[x][y] = 0;
        for (int i = 0; i < length; i++) {
            if (checkPositionValid(x - i, y - i, length)) {
                this.valid[x - i][y - i] = 0;
            }
            if (checkPositionValid(x - i, y, length)) {
                this.valid[x - i][y] = 0;
            }
            if (checkPositionValid(x - i, y + i, length)) {
                this.valid[x - i][y + i] = 0;
            }

            if (checkPositionValid(x, y - i, length)) {
                this.valid[x][y - i] = 0;
            }
            if (checkPositionValid(x, y + i, length)) {
                this.valid[x][y + i] = 0;
            }

            if (checkPositionValid(x + i, y + i, length)) {
                this.valid[x + i][y + i] = 0;
            }
            if (checkPositionValid(x + i, y, length)) {
                this.valid[x + i][y] = 0;
            }
            if (checkPositionValid(x + i, y - i, length)) {
                this.valid[x + i][y - i] = 0;
            }
        }
    }
}

// 22
/*
数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。

示例 1：
输入：n = 3
输出：["((()))","(()())","(())()","()(())","()()()"]

示例 2：
输入：n = 1
输出：["()"]
 */

class generateParenthesis {
    public List<String> generateParenthesis(int n) {
        return null;

    }
}

// 79
/*
给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。如果 word 存在于网格中，返回 true ；否则，返回 false 。
单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。

输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
输出：true

输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "SEE"
输出：true

输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCB"
输出：false

 */

class exist {
    public boolean exist(char[][] board, String word) {
        return true;

    }
}