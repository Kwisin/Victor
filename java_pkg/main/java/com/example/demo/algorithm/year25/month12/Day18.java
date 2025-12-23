package com.example.demo.algorithm.year25.month12;

import java.util.*;

public class Day18 {
    public static void main(String[] args) {

    }

    /*
    300. 最长递增子序列
    给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
    
    子序列 是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。
    例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。
    
    示例 1：
    输入：nums = [10,9,2,5,3,7,101,18]
    输出：4
    解释：最长递增子序列是 [2,3,7,101]，因此长度为 4 。
    
    示例 2：
    输入：nums = [0,1,0,3,2,3]
    输出：4
    
    示例 3：
    输入：nums = [7,7,7,7,7,7,7]
    输出：1
    
    提示：
    1 <= nums.length <= 2500
    -10^4 <= nums[i] <= 10^4
    
    进阶：你能将算法的时间复杂度降低到 O(n log(n)) 吗?
     */
    
    /*
    public int getLongest(int[] nums) {

        if (nums == null || nums.length == 0) {
            return 0;
        }

        int length = nums.length;
        int[] curr = new int[length];
        return getLongestDFS(nums, curr, 0, 0);
    }

    public int getLongestDFS(int[] nums, int[] curr, int index, int max) {
        if (index == nums.length) {
            return max;
        }

        for (int i = index; i < nums.length; i++) {
            if (index == 0 || nums[i] > curr[index - 1]) {
                curr[index] = nums[i];
                int longestDFS = getLongestDFS(nums, curr, i + 1, max + 1);
                max = Math.max(longestDFS, max);
            }
        }

        return max;
    }

    
    /*
    322. 零钱兑换
    给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。
    计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回 -1 。
    你可以认为每种硬币的数量是无限的。
    
    示例 1：
    输入：coins = [1, 2, 5], amount = 11
    输出：3 
    解释：11 = 5 + 5 + 1
    
    示例 2：
    输入：coins = [2], amount = 3
    输出：-1
    
    示例 3：
    输入：coins = [1], amount = 0
    输出：0
    
    提示：
    1 <= coins.length <= 12
    1 <= coins[i] <= 2^31 - 1
    0 <= amount <= 10^4
     */
    
    /*
    
    /*
    200. 岛屿数量
    给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
    岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
    此外，你可以假设该网格的四条边均被水包围。
    
    示例 1：
    输入：grid = [
      ["1","1","1","1","0"],
      ["1","1","0","1","0"],
      ["1","1","0","0","0"],
      ["0","0","0","0","0"]
    ]
    输出：1
    
    示例 2：
    输入：grid = [
      ["1","1","0","0","0"],
      ["1","1","0","0","0"],
      ["0","0","1","0","0"],
      ["0","0","0","1","1"]
    ]
    输出：3
    
    提示：
    m == grid.length
    n == grid[i].length
    1 <= m, n <= 300
    grid[i][j] 的值为 '0' 或 '1'
     */
    
    /*
    3. 无重复字符的最长子串
    给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
    
    示例 1:
    输入: s = "abcabcbb"
    输出: 3 
    解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
    
    示例 2:
    输入: s = "bbbbb"
    输出: 1
    解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
    
    示例 3:
    输入: s = "pwwkew"
    输出: 3
    解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
         请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
         
    提示：
    0 <= s.length <= 5 * 10^4
    s 由英文字母、数字、符号和空格组成
     */
    
    /*
    146. LRU 缓存
    ... (保持不变)
     */
    
    /*
    70. 爬楼梯
    假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
    每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
    
    示例 1：
    输入：n = 2
    输出：2
    解释：有两种方法可以爬到楼顶。
    1. 1 阶 + 1 阶
    2. 2 阶
    
    示例 2：
    输入：n = 3
    输出：3
    
    提示：
    1 <= n <= 45
     */

    
    
    /*
    
    /*
    198. 打家劫舍
    你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，
    如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
    
    给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
    
    示例 1：
    输入：[1,2,3,1]
    输出：4
    1 2 4 3
    0 1 2 4

    示例 2：
    输入：[2,7,9,3,1]
    输出：12
    
    提示：
    1 <= nums.length <= 100
    0 <= nums[i] <= 400
     */

    // 基础版：一排房子
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        
        int prevMax = 0;  // dp[i-2]
        int currMax = 0;  // dp[i-1]
        
        for (int num : nums) {
            int temp = currMax;
            // 核心方程：今天最大 = max(昨天最大, 前天最大 + 今天钱)
            currMax = Math.max(currMax, prevMax + num);
            prevMax = temp;
        }
        
        return currMax;
    }

    // 进阶版：房子首尾相连 (LeetCode 213)
    public int robCircle(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        
        // 拆成两种情况：不含尾 vs 不含头
        return Math.max(robRange(nums, 0, nums.length - 2), 
                        robRange(nums, 1, nums.length - 1));
    }

    private int robRange(int[] nums, int start, int end) {
        int prevMax = 0, currMax = 0;
        for (int i = start; i <= end; i++) {
            int temp = currMax;
            currMax = Math.max(currMax, prevMax + nums[i]);
            prevMax = temp;
        }
        return currMax;
    }


    
    /*
    
    /*
    1143. 最长公共子序列
    给定两个字符串 text1 和 text2，返回这两个字符串的最长 公共子序列 的长度。如果不存在 公共子序列 ，返回 0 。
    
    一个字符串的 子序列 是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。
    
    示例 1：
    输入：text1 = "abcde", text2 = "ace" 
    输出：3  
    解释：最长公共子序列是 "ace" ，它的长度为 3 。
    
    示例 2：
    输入：text1 = "abc", text2 = "abc"
    输出：3
    
    提示：
    1 <= text1.length, text2.length <= 1000
    text1 和 text2 仅由小写英文字符组成。
    dp[i][j] =
    dp[2][1]  dp[1][0]
     */

    
    /*
    
    /*
    518. 零钱兑换 II
    给你一个整数数组 coins 表示不同面额的硬币，另给一个整数 amount 表示总金额。
    请你计算并返回可以凑成总金额的硬币组合数。如果任何硬币组合都无法凑出总金额，返回 0 。
    假设每一种面额的硬币有无限个。 
    
    注意：是组合数，不是排列数（顺序不同的序列视为同一种组合）。
    
    示例 1：
    输入：amount = 5, coins = [1, 2, 5]
    输出：4
    解释：有四种方式可以凑成总金额：
    5=5
    5=2+2+1
    5=2+1+1+1
    5=1+1+1+1+1
    dp[5] = dp[4]+dp[3]+dp[0]
    dp[4] = max(dp[2]+dp[3]) = 2
    dp[3] = max(dp[1]+dp[2]) = 2
    dp[2] = 2
    dp[1] = 1


    提示：
    1 <= amount <= 5000
    1 <= coins.length <= 500
     */
    
    /*
    
    /*
    62. 不同路径
    一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。
    机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。
    问总共有多少条不同的路径？
    
    示例 1：
    输入：m = 3, n = 7
    输出：28
    
    提示：
    1 <= m, n <= 100
    题目数据保证答案小于等于 2 * 10^9
     */
}
