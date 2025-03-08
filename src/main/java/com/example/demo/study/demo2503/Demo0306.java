package com.example.demo.study.demo2503;


import javax.print.DocFlavor;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Demo0306 {

    public static void main(String[] args) {
//        boolean abcced = new exist().exist(new char[][]{
//                {'A', 'B', 'C', 'E'},
//                {'S', 'F', 'C', 'S'},
//                {'A', 'D', 'E', 'E'}}, "ABCB");
//        boolean result = new search().search(new int[]{2, 5, 6, 0, 0, 1, 2}, 0);
//        List<Integer> integers = new grayCode().grayCode(3);
        int i = 1 << 2;
        System.out.println();

    }

}

// 79. 单词搜索
/*
给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。
如果 word 存在于网格中，返回 true ；否则，返回 false 。
单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用

输入：board = [
["A","B","C","E"],
["S","F","C","S"],
["A","D","E","E"]], word = "ABCCED"
输出：true

输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "SEE"
输出：true

输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCB"
输出：false
 */
class exist {
    public boolean exist(char[][] board, String word) {
        int wordLen = word.length();
        if (wordLen == 0) {
            return true;
        }
        int row = board.length;
        if (row == 0) {
            return false;
        }
        int col = board[0].length;
        if (col == 0) {
            return false;
        }
        if (wordLen > row * col) {
            return false;
        }
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (board[i][j] == word.charAt(0)) {
                    if (dfs(board, i, j, new ArrayList<>(), word, 0, new HashSet<>()))
                        return true;
                }
            }
        }

        return false;
    }

    public boolean dfs(char[][] board, int row, int col, List<Character> curr, String word, int index, Set<String> exist) {
        if (curr.size() == word.length()) {
            return true;
        }
        String key = row + "" + col;
        if (row >= board.length || row < 0 || col >= board[0].length || col < 0 || board[row][col] != word.charAt(index) || exist.contains(key)) {
            return false;
        }
        curr.add(board[row][col]);
        exist.add(key);
        boolean b = dfs(board, row + 1, col, curr, word, index + 1, exist) ||
                dfs(board, row - 1, col, curr, word, index + 1, exist) ||
                dfs(board, row, col + 1, curr, word, index + 1, exist) ||
                dfs(board, row, col - 1, curr, word, index + 1, exist);
        curr.remove(curr.size() - 1);
        exist.remove(key);
        return b;
    }
}

// 81. 搜索旋转排序数组 II
/*
已知存在一个按非降序排列的整数数组 nums ，数组中的值不必互不相同。

在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转 ，
使数组变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。
例如， [0,1,2,4,4, 4,5,6,6,7] 在下标 5 处经旋转后可能变为 [4,5,6,6,7,0,1,2,4,4] 。

给你 旋转后 的数组 nums 和一个整数 target ，请你编写一个函数来判断给定的目标值是否存在于数组中。
如果 nums 中存在这个目标值 target ，则返回 true ，否则返回 false 。

你必须尽可能减少整个操作步骤。

示例 1：
输入：nums = [2,5,6,0,0,1,2], target = 6
输出：true
示例 2：
输入：nums = [2,5,6,0,0,1,2], target = 3
输出：false

提示：

1 <= nums.length <= 5000
-104 <= nums[i] <= 104
题目数据保证 nums 在预先未知的某个下标上进行了旋转
-104 <= target <= 104
 */
class search {
    public boolean search(int[] nums, int target) {
        if (target < nums[0] && target > nums[nums.length - 1]) {
            return false;
        }
        boolean dir = target >= nums[0];
        if (dir) {
            int i = 0;
            while (i <= nums.length - 1) {
                if (nums[i] == target) {
                    return true;
                }
                if (nums[i] > target || i == nums.length - 1 || nums[i + 1] < nums[i]) {
                    return false;
                }
                i++;
            }
        } else {
            int i = nums.length - 1;
            while (i >= 0) {
                if (nums[i] == target) {
                    return true;
                }
                if (nums[i] < target || i == 0 || nums[i] < nums[i - 1]) {
                    return false;
                }
                i--;
            }
        }
        return false;
    }
}

// 89. 格雷编码
/*
n 位格雷码序列 是一个由 2^n 个整数组成的序列，其中：
每个整数都在范围 [0, 2^n - 1] 内（含 0 和 2^n - 1）
第一个整数是 0
一个整数在序列中出现 不超过一次
每对 相邻 整数的二进制表示 恰好一位不同 ，且
第一个 和 最后一个 整数的二进制表示 恰好一位不同
给你一个整数 n ，返回任一有效的 n 位格雷码序列 。
000~111
[000,001]

示例 1：
输入：n = 2
输出：[0,1,3,2]
3
111
4
0000~1111
解释：
[0,1,3,2] 的二进制表示是 [00,01,11,10] 。
- 00 和 01 有一位不同
- 01 和 11 有一位不同
- 11 和 10 有一位不同
- 10 和 00 有一位不同
[0,2,3,1] 也是一个有效的格雷码序列，其二进制表示是 [00,10,11,01] 。
- 00 和 10 有一位不同
- 10 和 11 有一位不同
- 11 和 01 有一位不同
- 01 和 00 有一位不同

示例 2：
输入：n = 1
输出：[0,1]
 */
class grayCode {
    List<String> result = new ArrayList<>();

    public List<Integer> grayCode(int n) {
        ArrayList<String> curr = new ArrayList<>();
        HashSet<String> exist = new HashSet<>();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < n; i++) {
            stringBuilder.append("0");
        }
        curr.add(stringBuilder.toString());
        exist.add(stringBuilder.toString());
        dfs(curr, exist, (long) Math.pow(2, n));
        ArrayList<Integer> temp = new ArrayList<>();
        this.result.forEach(item -> temp.add(Integer.parseInt(item, 2)));
        return temp;
    }

    public boolean dfs(List<String> curr, Set<String> exist, long n) {
        if (curr.size() == n) {
            if (check(curr.get(0), curr.get(curr.size() - 1))) {
                this.result = new ArrayList<>(curr);
                return true;
            }
            return false;
        }
        String pre = curr.get(curr.size() - 1);
        for (int i = 0; i < pre.length(); i++) {
            char c = pre.charAt(i);
            String newString = pre.substring(0, i) + (c == '1' ? "0" : "1") + pre.substring(i + 1);
            if (!exist.contains(newString)) {
                curr.add(newString);
                exist.add(newString);
                boolean dfs = dfs(curr, exist, n);
                curr.remove(curr.size() - 1);
                exist.remove(newString);
                if (dfs) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean check(String s1, String s2) {
        boolean result = false;
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) == s2.charAt(i)) {
                continue;
            }
            if (result) {
                return false;
            }
            result = true;
        }
        return result;
    }


}