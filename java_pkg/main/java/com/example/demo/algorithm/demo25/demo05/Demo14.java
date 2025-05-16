package com.example.demo.algorithm.demo25.demo05;

import java.util.ArrayList;
import java.util.List;

public class Demo14 {
    public static void main(String[] args) {
        solveNQueens solveNQueens = new solveNQueens();
        List<List<String>> lists = solveNQueens.solveNQueens(2);
        System.out.println();
    }


}

// 51
    /*
    51. N 皇后
        按照国际象棋的规则，皇后可以攻击与之处在同一行或同一列或同一斜线上的棋子。
        n 皇后问题 研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
        给你一个整数 n ，返回所有不同的 n 皇后问题 的解决方案。
        每一种解法包含一个不同的 n 皇后问题 的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。

        输入：n = 4
        输出：[
        [".Q..",
         "...Q",
         "Q...",
         "..Q."],
        ["..Q.",
         "Q...",
          "...Q",
          ".Q.."]
          ]
        解释：如上图所示，4 皇后问题存在两个不同的解法。
        示例 2：
        输入：n = 1
        输出：[["Q"]]
     */

class solveNQueens {
    private List<List<String>> list = new ArrayList<>();

    public List<List<String>> solveNQueens(int n) {
        int[] ints = new int[n];
        for (int i = 0; i < n; i++) {
            ints[i] = -1;
        }

        dfs(ints, n, 0);
        return this.list;
    }

    public void dfs(int[] exist, int n, int depth) {
        if (exist[n - 1] >= 0) {
            this.list.add(genStringByPosition(exist));
            return;
        }
        for (int i = 0; i < n; i++) {
            if (check(exist, depth, i)) {
                exist[depth] = i;
                dfs(exist, n, depth + 1);
                exist[depth] = -1;
            }
        }
    }

    public boolean check(int[] exist, int depth, int position) {
        for (int i = 0; i < exist.length; i++) {
            if (exist[i] == -1) {
                return true;
            }
            if (i == depth || exist[i] == position || (i - depth) * (i - depth) == (exist[i] - position) * (exist[i] - position)) {
                return false;
            }
        }
        return true;
    }

    public List<String> genStringByPosition(int[] exist) {
        ArrayList<String> result = new ArrayList<>();
        StringBuilder raw = new StringBuilder("");
        for (int i = 0; i < exist.length; i++) {
            raw.append(".");
        }
        for (int item : exist) {
            result.add(raw.substring(0, item) + "Q" + raw.substring(item + 1, exist.length));
        }
        return result;
    }
}


