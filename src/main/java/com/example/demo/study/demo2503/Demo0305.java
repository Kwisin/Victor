package com.example.demo.study.demo2503;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Demo0305 {

    public static void main(String[] args) {
        int i = new minDistance().minDistance("intention", "execution");
        System.out.println();
    }

}

// 72. 编辑距离
/*
给你两个单词 word1 和 word2， 请返回将 word1 转换成 word2 所使用的最少操作数  。
你可以对一个单词进行如下三种操作：
插入一个字符
删除一个字符
替换一个字符

示例 1：
输入：word1 = "horse", word2 = "ros"
输出：3
解释：
horse -> rorse (将 'h' 替换为 'r')
rorse -> rose (删除 'r')
rose -> ros (删除 'e')

示例 2：
输入：word1 = "in tention", word2 = "ex ecution"
""->"e"  插入
"i"->"e"
输出：5
解释：
intention -> inention (删除 't')
inention -> enention (将 'i' 替换为 'e')
enention -> exention (将 'n' 替换为 'x')
exention -> exection (将 'n' 替换为 'c')
exection -> execution (插入 'u')

[0,1,2,3,4,5,6,7,8,9]
[1,1,2,3,4,5,6,6,7,8]
[2,2,0,2,1,1,0,2,0,2]
[3,3,0,2,1,1,0,2,0,2]
[4,3,0,2,1,1,0,2,0,2]
[5,4,0,2,1,1,0,2,0,2]
[6,5,0,2,1,1,0,2,0,2]
[7,6,0,2,1,1,0,2,0,2]
[8,7,0,2,1,1,0,2,0,2]
[9,8,0,2,1,1,0,2,0,2]
word1[i] == word2[j] ?f(i-1,j-1):
f(i,j)
f(i-1,j-1)
 */
class minDistance {
    public int minDistance(String word1, String word2) {
        int word1Length = word1.length();
        int word2Length = word2.length();
        if (word1Length == 0) {
            return word2Length;
        }
        if (word2Length == 0) {
            return word1Length;
        }
        int[][] dp = new int[word1Length + 1][word2Length + 1];
        dp[0][0] = 0;
        for (int i = 1; i <= word1Length; i++) {
            dp[i][0] = dp[i - 1][0] + 1;
        }
        for (int i = 1; i <= word2Length; i++) {
            dp[0][i] = dp[0][i - 1] + 1;
        }
        for (int row = 1; row <= word1Length; row++) {
            for (int col = 1; col <= word2Length; col++) {
                if (word1.charAt(row - 1) == word2.charAt(col - 1)) {
                    dp[row][col] = dp[row - 1][col - 1];
                } else {
                    dp[row][col] = Math.min(Math.min(dp[row - 1][col - 1], dp[row - 1][col]), dp[row][col - 1]) + 1;
                }
            }
        }
        return dp[word1Length][word2Length];
    }
}


// 74. 搜索二维矩阵
/*
给你一个满足下述两条属性的 m x n 整数矩阵：
每行中的整数从左到右按非严格递增顺序排列。
每行的第一个整数大于前一行的最后一个整数。
给你一个整数 target ，如果 target 在矩阵中，返回 true ；否则，返回 false 。

输入：matrix = [
[1,3,5,7],
[10,11,16,20],
[23,30,34,60]], target = 3
输出：true

输入：matrix = [
[1,3,5,7],
[10,11,16,20],
[23,30,34,60]], target = 13
输出：false
 */
class searchMatrix {
    public boolean searchMatrix(int[][] matrix, int target) {
        int row = matrix.length;
        if (row == 0) {
            return false;
        }
        int col = matrix[0].length;
        if (col == 0) {
            return false;
        }
        if (target < matrix[0][0] || target > matrix[row - 1][col - 1]) {
            return false;
        }
        int[] temp = matrix[row - 1];
        for (int i = 0; i < row - 1; i++) {
            if (matrix[i][0] < target && matrix[i + 1][0] > target) {
                temp = matrix[i];
            }
        }
        for (int item : temp) {
            if (item == target) {
                return true;
            }
        }
        return false;
    }
}


// 75. 颜色分类
/*
给定一个包含红色、白色和蓝色、共 n 个元素的数组 nums ，原地 对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
必须在不使用库内置的 sort 函数的情况下解决这个问题。

示例 1：      i j k
 i   j   k
[0,0,1,2,2,1]
 i j k
[2,0,2,1,1,0]
输入：nums = [2,0,2,1,1,0]
输出：[0,0,1,1,2,2]
示例 2：
输入：nums = [2,0,1]
输出：[0,1,2]
 */
class sortColors {
    public void sortColors(int[] nums) {
        int i = -1, j = -1, k = -1;
        for (int t = 0; t < nums.length; t++) {
            if (nums[t] == 0) {
                i=0;
                swap(nums, i, t);
            }
        }
        for (int t = 0; t < nums.length; t++) {
            if (nums[t] == 1) {
                swap(nums, j, t);
            }
        }
        for (int t = 0; t < nums.length; t++) {
            if (nums[t] == 2) {
                swap(nums, k, t);
            }
        }

//        for (int t = k + 1; ) {
//
//        }

        return;
    }

    public void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}