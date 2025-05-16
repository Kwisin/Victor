package com.example.demo.algorithm.demo25.demo05;

public class Demo16 {
    public static void main(String[] args) {
        int rob = new rob().dp(new int[]{1,2,3,13,2,4,5});
        System.out.println();
    }
}

//214. 最短回文串
/*
给定一个字符串 s，你可以通过在字符串前面添加字符将其转换为回文串。找到并返回可以用这种方式转换的最短回文串。
示例 1：
输入：s = "aacecaaa"
[1,1,0,0,0,0,0,0]
[0,1,0,0,0,0,0,0]
[0,0,1,0,0,0,0,0]
[0,0,0,1,0,0,0,0]
[0,0,0,0,1,0,0,0]
[0,0,0,0,0,1,1,0]
[0,0,0,0,0,0,1,1]
[0,0,0,0,0,0,0,1]
aaaaabaaaaa
"aaaceca b acecaaa"
输出："aaacecaaa"
示例 2：
输入：s = "abcd"
输出："dcbabcd"
 */
class shortestPalindrome {
    public String shortestPalindrome(String s) {
        int size = s.length();
        int[][] dp = new int[size][size];
        for (int i = 0; i < size; i++) {
            dp[i][i] = 1;
            if (i + 1 < size) {
                dp[i][i + 1] = s.charAt(i) == s.charAt(i + 1) ? 1 : 0;
            }
        }
        for (int gap = 2; gap < size; gap++) {
            for (int i = 0; i < size; i++) {
                if (i + gap >= size) {
                    break;
                }
                dp[i][i + gap] = dp[i + 1][i + gap - 1] == 1 && s.charAt(i) == s.charAt(i + gap) ? 1 : 0;
            }
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (int n = size - 1; n >= 0; n--) {
            if (dp[0][n] == 1) {
                break;
            }
            stringBuilder.append(s.charAt(n));
        }
        return stringBuilder.reverse() + s;
    }
}


//198. 打家劫舍
/*
你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，
如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。

示例 1：
输入：[1,2,3,1]
输出：4
解释：偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。偷窃到的最高金额 = 1 + 3 = 4 。

示例 2：
输入：[2,7,9,3,1]
[1,2,3,13,2,4,5]
1[1,2,4,15,15,19,20]
0[0,1,2,4,15,15,19]
输出：12
解释：偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。偷窃到的最高金额 = 2 + 9 + 1 = 12
 */
class rob {
    private int money = 0;

    public int rob(int[] nums) {
        dfs(0, nums, new int[nums.length], 0);
        return this.money;
    }

    public void dfs(int curr, int[] nums, int[] index, int position) {
        if (position == index.length) {
            this.money = Math.max(this.money, curr);
            return;
        }

        // 不抢
        dfs(curr, nums, index, position + 1);


        // 抢
        if (position == 0 || index[position - 1] == 0) {
            index[position] = 1;
            dfs(curr + nums[position], nums, index, position + 1);
            index[position] = 0;
        }

    }

    public int dp(int[] nums) {
        int length = nums.length;
        if (length == 0) {
            return 0;
        }

        // 抢
        int[] lastRob = new int[length];
        lastRob[0] = nums[0];
        // 不抢
        int[] lastNotRob = new int[length];

        for (int i = 1; i < length; i++) {
            lastRob[i] = lastNotRob[i - 1] + nums[i];
            lastNotRob[i] = Math.max(lastRob[i - 1], lastNotRob[i - 1]);
        }

        return Math.max(lastRob[length - 1], lastNotRob[length - 1]);
    }
}
