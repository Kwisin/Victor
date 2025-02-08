package com.example.demo.study.demo2502;


public class Demo0208 {

    public static void main(String[] args) {
        System.out.println();
    }


}

// 63
/*
给定一个 m x n 的整数数组 grid。一个机器人初始位于 左上角（即 grid[0][0]）。
机器人尝试移动到 右下角（即 grid[m - 1][n - 1]）。
机器人每次只能向下或者向右移动一步。
网格中的障碍物和空位置分别用 1 和 0 来表示。机器人的移动路径中不能包含 任何 有障碍物的方格。
返回机器人能够到达右下角的不同路径数量。

输入：obstacleGrid = [
[0,0,0],
[0,1,0],
[0,0,0]
]

[2,1,1],
[1,0,1],
[1,1,0]


[0,0,0,1,0],
[0,1,0,0,1],
[0,0,0,0,0]
[0,0,0,0,0]

[30,16,7,2,0],
[14,9,5,2,0],
[5,4,3,2,1]
[1,1,1,1,0]

输出：2
解释：3x3 网格的正中间有一个障碍物。
从左上角到右下角一共有 2 条不同的路径：
1. 向右 -> 向右 -> 向下 -> 向下
2. 向下 -> 向下 -> 向右 -> 向右

输入：obstacleGrid = [[0,1],[0,0]]
输出：1
 */

class uniquePathsWithObstacles {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int row = obstacleGrid.length;
        if (row == 0) {
            return 0;
        }
        int col = obstacleGrid[0].length;
        int[][] table = new int[row][col];
        if (table[row - 1][col - 1] == 1) {
            return 0;
        }
        table[row - 1][col - 1] = 1;
        for (int i = col - 2; i >= 0; i--) {
            table[row - 1][i] = obstacleGrid[row - 1][i] == 1 ? 0 : table[row - 1][i + 1];
        }
        for (int i = row - 2; i >= 0; i--) {
            table[i][col - 1] = obstacleGrid[i][col - 1] == 1 ? 0 : table[i + 1][col - 1];
        }

        for (int i = row - 2; i >= 0; i--) {
            for (int j = col - 2; j >= 0; j--) {
                table[i][j] = obstacleGrid[i][j] == 1 ? 0 : table[i + 1][j] + table[i][j + 1];
            }
        }

        return table[0][0];


    }
}