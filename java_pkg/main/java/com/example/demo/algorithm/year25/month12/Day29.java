package com.example.demo.algorithm.year25.month12;


import java.util.*;

public class Day29 {
    public static void main(String[] args) {
        Day29 day29 = new Day29();

        // 1. 爬楼梯
        System.out.println(day29.climbStairs(3)); // 3

        // 2. 最大子数组和
        System.out.println(day29.maxSubArray(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4})); // 6

        // 3. 最长回文子串
        System.out.println(day29.longestPalindrome("babad")); // "bab" 或 "aba"

        // 4. 字母异位词分组
        System.out.println(day29.groupAnagrams(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"}));
        // [["bat"],["nat","tan"],["ate","eat","tea"]]
    }

    /*
    70. 爬楼梯 (Climbing Stairs)
    简单 - 动态规划入门题
    假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
    每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？

    示例 1：输入：n = 2 -> 输出：2 (1+1 或 2)
    示例 2：输入：n = 3 -> 输出：3 (1+1+1 或 1+2 或 2+1)
    */
    public int climbStairs(int n) {

        int pre = 1, pre1 = 2;
        int result = 0;
        int curr = 3;
        while (curr <= n) {
            result = pre + pre1;

            pre = pre1;
            pre1 = result;

            curr++;
        }
        return result;
    }

    /*
    53. 最大子数组和 (Maximum Subarray)
    中等 - 动态规划/贪心
    给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。

    示例 1：输入：nums = [-2,1,-3,4,-1,2,1,-5,4] -> 输出：6
    -2，1，-2，4，3，5，6，1，5

    [-2,1,1,4,4,2,1,-5,4]
    [-2,1,1,4,-1,2,1,-5,4]
    [-2,1,-3,4,-1,2,1,-5,4]
    [-2,1,-3,4,-1,2,1,-5,4]
    [-2,1,-3,4,-1,2,1,-5,4]
    [-2,1,-3,4,-1,2,1,-5,4]
    [-2,1,-3,4,-1,2,1,-5,4]
    [-2,1,-3,4,-1,2,1,-5,4]
    [-2,1,-3,4,-1,2,1,-5,4]
    dp[i][j] : nums[i]~nums[j]的最大和
    dp[i] :以 i 结尾的最大值

    解释：连续子数组 [4,-1,2,1] 的和最大，为 6 。
    */
    public int maxSubArray(int[] nums) {
        int max = Integer.MIN_VALUE;
        for (int i = 1; i < nums.length; i++) {
            nums[i] = Math.max(nums[i - 1] + nums[i], nums[i]);
            max = Math.max(max, nums[i]);
        }

        return max;
    }

    /*
    5. 最长回文子串 (Longest Palindromic Substring)
    中等 - 动态规划/中心扩展
    给你一个字符串 s，找到 s 中最长的回文子串。

    示例 1：输入：s = "babad" -> 输出："bab" (或 "aba")

    cbccdbba

    dp[i][j]:从 i 到 j 的子串是不是回文的
    dp[i+1][j-1] == 1 && char[i] == char[j]

    示例 2：输入：s = "cbbd" -> 输出："bb"
    */
    public String longestPalindrome(String s) {
        int length = s.length();
        int[][] dp = new int[length][length];
        int maxLen = 0;
        String maxStr = "";
        for (int i = 0; i < length; i++) {
            dp[i][i] = 1;
        }
        //0,1 1,2
        for (int gap = 1; gap < length; gap++) {
            for (int i = 0; i + gap < length; i++) {
                int j = i + gap;
                boolean b = s.charAt(i) == s.charAt(j);
                if (i + 1 > j - 1) {
                    dp[i][j] = b ? 2 : 0;
                } else {
                    dp[i][j] = dp[i + 1][j - 1] > 0 && b ? dp[i + 1][j - 1] + 2 : 0;
                }

                if (dp[i][j] > maxLen) {
                    maxLen = dp[i][j];
                    maxStr = s.substring(i, j);
                }


            }
        }


        return maxStr;
    }

    /*
    49. 字母异位词分组 (Group Anagrams)
    中等 - 哈希表
    给你一个字符串数组，请你将 字母异位词 组合在一起。可以按任意顺序返回结果列表。
    字母异位词 是由重新排列源单词的所有字母得到的一个新单词。

    示例 1：
    输入: strs = ["eat","tea","tan","ate","nat","bat"]
    输出: [["bat"],["nat","tan"],["ate","eat","tea"]]
    */
    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, List<String>> stringListHashMap = new HashMap<>();
        for (String s : strs) {
            boolean exist = false;
            for (String key : stringListHashMap.keySet()) {
                if (checkExist(key, s)) {
                    stringListHashMap.get(key).add(s);
                    exist = true;
                }
            }

            if (!exist) {
                stringListHashMap.put(s, Arrays.asList(s));
            }

        }

        ArrayList<List<String>> ans = new ArrayList<>();
        for (String key : stringListHashMap.keySet()) {
            ans.add(stringListHashMap.get(key));
        }
        return ans;
    }

    public boolean checkExist(String oldStr, String newStr) {
        if (oldStr.length() != newStr.length()) {
            return false;
        }

        HashMap<Character, Integer> characterIntegerHashMap = new HashMap<>();
        for (int i = 0; i < oldStr.length(); i++) {
            char c = oldStr.charAt(i);
            characterIntegerHashMap.put(c, characterIntegerHashMap.getOrDefault(c, 0) + 1);
        }

        for (int i = 0; i < newStr.length(); i++) {
            char c = newStr.charAt(i);
            if (!characterIntegerHashMap.containsKey(c)) {
                return false;
            }

            characterIntegerHashMap.put(c, characterIntegerHashMap.get(c) - 1);
        }

        for (char c : characterIntegerHashMap.keySet()) {
            if (characterIntegerHashMap.get(c) != 0) {
                return false;
            }
        }

        return true;
    }
}
