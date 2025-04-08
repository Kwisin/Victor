package com.example.demo.algorithm.demo25.demo2501;


import java.util.*;

public class Demo0113 {

    public static void main(String[] args) {
//        List<List<Integer>> lists = new combinationSum().combinationSum(new int[]{2, 3, 6, 7}, 7);
//        int i = new totalNQueens().totalNQueens(5);
        List<String> strings = new generateParenthesis().generateParenthesis(8);
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
[
[1,0,0,0,0],
[0,0,1,0,0],
[0,0,0,0,1],
[0,1,0,0,0],
[0,0,0,1,0]
]
 */

class totalNQueens {
    public int result = 0;

    public int totalNQueens(int n) {
        if (n == 0 || n == 1) {
            return n;
        }
        ArrayDeque<int[]> exist = new ArrayDeque<>();
        dfs(n, 0, exist);

        return this.result;
    }

    public void dfs(int n, int index, ArrayDeque<int[]> exist) {
        if (exist.size() == n) {
            this.result++;
            return;
        }
        for (int i = 0; i < n; i++) {
            if (!checkPositionValid(index, i, n, exist)) {
                continue;
            }
            exist.addLast(new int[]{index, i});
            dfs(n, index + 1, exist);
            exist.removeLast();
        }

    }

    public boolean checkPositionValid(int x, int y, int length, ArrayDeque<int[]> exist) {
        for (int[] item : exist) {
            if (item[0] == x || item[1] == y || (item[1] - y) / (item[0] - x) == 1 || (item[1] - y) / (item[0] - x) == -1) {
                return false;
            }
        }
        return true;
    }

//    public void setValid(int x, int y, int length) {
//        this.valid[x][y] = 1;
//        for (int i = 1; i < length; i++) {
//            if (checkPositionValid(x - i, y - i, length)) {
//                this.valid[x - i][y - i] = 1;
//            }
//            if (checkPositionValid(x - i, y, length)) {
//                this.valid[x - i][y] = 1;
//            }
//            if (checkPositionValid(x - i, y + i, length)) {
//                this.valid[x - i][y + i] = 1;
//            }
//
//            if (checkPositionValid(x, y - i, length)) {
//                this.valid[x][y - i] = 1;
//            }
//            if (checkPositionValid(x, y + i, length)) {
//                this.valid[x][y + i] = 1;
//            }
//
//            if (checkPositionValid(x + i, y + i, length)) {
//                this.valid[x + i][y + i] = 1;
//            }
//            if (checkPositionValid(x + i, y, length)) {
//                this.valid[x + i][y] = 1;
//            }
//            if (checkPositionValid(x + i, y - i, length)) {
//                this.valid[x + i][y - i] = 1;
//            }
//        }
//
//    }
//
//    public void reverseValid(int x, int y, int length) {
//        this.valid[x][y] = 0;
//        for (int i = 0; i < length; i++) {
//            if (checkPositionValid(x - i, y - i, length)) {
//                this.valid[x - i][y - i] = 0;
//            }
//            if (checkPositionValid(x - i, y, length)) {
//                this.valid[x - i][y] = 0;
//            }
//            if (checkPositionValid(x - i, y + i, length)) {
//                this.valid[x - i][y + i] = 0;
//            }
//
//            if (checkPositionValid(x, y - i, length)) {
//                this.valid[x][y - i] = 0;
//            }
//            if (checkPositionValid(x, y + i, length)) {
//                this.valid[x][y + i] = 0;
//            }
//
//            if (checkPositionValid(x + i, y + i, length)) {
//                this.valid[x + i][y + i] = 0;
//            }
//            if (checkPositionValid(x + i, y, length)) {
//                this.valid[x + i][y] = 0;
//            }
//            if (checkPositionValid(x + i, y - i, length)) {
//                this.valid[x + i][y - i] = 0;
//            }
//        }
//    }


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
    private List<String> result = new ArrayList<>();
    private HashSet<String> exist = new HashSet<>();

    public List<String> generateParenthesis(int n) {
        StringBuilder curr = new StringBuilder();
        for (int i = 0; i < n; i++) {
            curr.append("(");
        }
        dfs(curr.toString(), n);
        return result;
    }

    public void dfs(String curr, int n) {
        if (n == 0) {
            if (isValid(curr) && !this.exist.contains(curr)) {
                this.result.add(curr);
                this.exist.add(curr);
            }
            return;
        }
        for (int i = 0; i < curr.length(); i++) {
            String newCurr = curr.substring(0, i + 1) + ")" + curr.substring(i + 1);
            if (isNotValid(curr)) {
                continue;
            }
            dfs(newCurr, n - 1);
        }
    }

    public boolean isValid(String s) {
        int temp = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') {
                temp++;
            }
            if (c == ')') {
                temp--;
                if (temp < 0) {
                    return false;
                }
            }
        }
        return temp == 0;
    }

    public boolean isNotValid(String s) {
        int temp = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') {
                temp++;
            }
            if (c == ')') {
                temp--;
                if (temp < 0) {
                    return true;
                }
            }
        }
        return false;
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