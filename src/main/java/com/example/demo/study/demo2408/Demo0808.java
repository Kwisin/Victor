package com.example.demo.study.demo2408;

import java.util.HashMap;

public class Demo0808 {

    public static void main(String[] args) {
        int[][] board = {{0, 1, 0}, {0, 0, 1}, {1, 1, 1}, {0, 0, 0}};
        gameOfLife gameOfLife = new gameOfLife();
        gameOfLife.gameOfLife(board);
        System.out.println();
    }


}


// todo
class gameOfLife {
    //给定一个包含 m × n 个格子的面板，每一个格子都可以看成是一个细胞。每个细胞都具有一个初始状态： 1 即为 活细胞 （live），或 0 即为 死细胞 （dead）。每个细胞与其八个相邻位置（水平，垂直，对角线）的细胞都遵循以下四条生存定律：
    //
    //如果活细胞周围八个位置的活细胞数少于两个，则该位置活细胞死亡；
    //如果活细胞周围八个位置有两个或三个活细胞，则该位置活细胞仍然存活；
    //如果活细胞周围八个位置有超过三个活细胞，则该位置活细胞死亡；
    //如果死细胞周围正好有三个活细胞，则该位置死细胞复活；
    //下一个状态是通过将上述规则同时应用于当前状态下的每个细胞所形成的，其中细胞的出生和死亡是同时发生的。给你 m x n 网格面板 board 的当前状态，返回下一个状态

    //0,1,0
    //0,0,1
    //1,1,1
    //0,0,0

    //[0,0,0]
    //[1,0,1]
    //[0,1,1]
    //[0,1,0]

    //输入：board = [[0,1,0],[0,0,1],[1,1,1],[0,0,0]]
    //输出：[[0,0,0],[1,0,1],[0,1,1],[0,1,0]]

    //输入：board = [[1,1],[1,0]]
    //输出：[[1,1],[1,1]]
    public void gameOfLife(int[][] board) {
        HashMap<String, Integer> stringIntegerHashMap = new HashMap<>();
        //先遍历一轮设置各个点位附近活细胞数
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                if (board[row][col] == 1) {
                    setAliveNum(row, col, 1, stringIntegerHashMap);
                }
            }
        }


        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                checkAlive(row, col, board, stringIntegerHashMap);
            }
        }

    }

    boolean checkAlive(int row, int col, int[][] board, HashMap<String, Integer> stringIntegerHashMap) {
        boolean result = false;
        int current = board[row][col];
        int aliveNum = stringIntegerHashMap.getOrDefault((row) + "" + (col), 0);

        if (current == 1) {
            if (aliveNum < 2 || aliveNum > 3) {
                result = true;
                // 变成死细胞
                board[row][col] = 0;
                setAliveNum(row, col, -1, stringIntegerHashMap);
            }
            return result;
        }

        if (current == 0) {
            if (aliveNum == 3) {
                result = true;
                // 变成活细胞
                board[row][col] = 1;
                setAliveNum(row, col, 1, stringIntegerHashMap);
            }
            return result;
        }

        return false;

    }


    void setAliveNum(int row, int col, int change, HashMap<String, Integer> stringIntegerHashMap) {
        stringIntegerHashMap.put((row - 1) + "" + (col - 1), stringIntegerHashMap.getOrDefault((row - 1) + "" + (col - 1), 0) + change);
        stringIntegerHashMap.put((row - 1) + "" + (col), stringIntegerHashMap.getOrDefault((row - 1) + "" + (col), 0) + change);
        stringIntegerHashMap.put((row - 1) + "" + (col + 1), stringIntegerHashMap.getOrDefault((row - 1) + "" + (col + 1), 0) + change);
        stringIntegerHashMap.put((row) + "" + (col - 1), stringIntegerHashMap.getOrDefault((row) + "" + (col - 1), 0) + change);
        stringIntegerHashMap.put((row) + "" + (col + 1), stringIntegerHashMap.getOrDefault((row) + "" + (col + 1), 0) + 1);
        stringIntegerHashMap.put((row + 1) + "" + (col - 1), stringIntegerHashMap.getOrDefault((row + 1) + "" + (col - 1), 0) + change);
        stringIntegerHashMap.put((row + 1) + "" + (col), stringIntegerHashMap.getOrDefault((row + 1) + "" + (col), 0) + change);
        stringIntegerHashMap.put((row + 1) + "" + (col + 1), stringIntegerHashMap.getOrDefault((row + 1) + "" + (col + 1), 0) + change);
    }
}


class rotate {
    //给定一个 n × n 的二维矩阵 matrix 表示一个图像。请你将图像顺时针旋转 90 度。
    //
    //你必须在 原地 旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要 使用另一个矩阵来旋转图像。

    //输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
    //输出：[[7,4,1],[8,5,2],[9,6,3]]

    //[5,1,9,7]   8,7,2,5   5,2,7,8
    //[2,4,8,7]   9,3,4,1   1,4,3,9
    //[7,3,6,7]   6,6,8,9   9,8,6,6
    //[8,9,6,3]   3,7,7,7   7,7,7,3

    //[3,6,9,8]
    //[7,6,3,7]
    //[7,8,4,2]
    //[7,9,1,5]
    //输入：matrix = [[5,1,9,11],[2,4,8,10],[13,3,6,7],[15,14,12,16]]
    //输出：[[15,13,2,5],[14,3,4,1],[12,6,8,9],[16,7,10,11]]
    public void rotate(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0 || matrix.length != matrix[0].length) {
            return;
        }

        //先对角线反转
        for (int i = 0; i < matrix.length; i++) {
            for (int j = i; j < matrix[0].length; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }

        //再中间反转
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length / 2; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][matrix[0].length - 1 - j];
                matrix[i][matrix[0].length - 1 - j] = temp;
            }
        }
    }
}

class containsNearbyDuplicate {
    //给你一个整数数组 nums 和一个整数 k ，判断数组中是否存在两个 不同的索引 i 和 j ，
    // 满足 nums[i] == nums[j] 且 abs(i - j) <= k 。如果存在，返回 true ；否则，返回 false

    //输入：nums = [1,2,3,1], k = 3
    //输出：true

    //输入：nums = [1,2,3,1,2,3], k = 2
    //输出：false
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        if (k == 0 || nums.length == 0) {
            return false;
        }

        HashMap<Integer, Integer> integerIntegerHashMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (integerIntegerHashMap.containsKey(nums[i])) {
                if ((i - integerIntegerHashMap.get(nums[i])) <= k) {
                    return true;
                }
            } else {
                integerIntegerHashMap.put(nums[i], i);
            }
        }
        return false;
    }
}