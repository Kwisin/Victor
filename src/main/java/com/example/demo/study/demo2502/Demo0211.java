package com.example.demo.study.demo2502;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class Demo0211 {

    public static void main(String[] args) {
        boolean b = new wordBreak().wordBreak("catsandog", Arrays.asList("cats", "dog", "sand", "and", "cat"));
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
输入：word1 = "ho rse", word2 = "ros"
输出：3
解释：
horse -> rorse (将 'h' 替换为 'r')
rorse -> rose (删除 'r')
rose -> ros (删除 'e')

示例 2：
输入：word1 = "intention", word2 = "execution"
输出：5
解释：
intention -> inention (删除 't')
inention -> enention (将 'i' 替换为 'e')
enention -> exention (将 'n' 替换为 'x')
exention -> exection (将 'n' 替换为 'c')
exection -> execution (插入 'u')

[0,1,2,3]
[1,1,2,3]
[2,2,1,2]
[3,2,2,3/3]
[4，]
[5]
 */
class minDistance {
    public int minDistance(String word1, String word2) {
        return 0;

    }
}

// 162. 寻找峰值
/*
峰值元素是指其值严格大于左右相邻值的元素。
给你一个整数数组 nums，找到峰值元素并返回其索引。数组可能包含多个峰值，在这种情况下，返回 任何一个峰值 所在位置即可。
你可以假设 nums[-1] = nums[n] = -∞ 。
你必须实现时间复杂度为 O(log n) 的算法来解决此问题。

示例 1：
输入：nums = [1,2,3,1]
输出：2
解释：3 是峰值元素，你的函数应该返回其索引 2。
示例 2：
输入：nums = [1,2,3,4,5,6,7]
                    0,6,3
            0,3,1         3,6,4
       0,1,0   1,3,2
     0,0,0    2,3,2

输出：1 或 5
解释：你的函数可以返回索引 1，其峰值元素为 2；
    或者返回索引 5， 其峰值元素为 6。
 */

class findPeakElement {
    public int findPeakElement(int[] nums) {
        int length = nums.length;
        if (length == 1) {
            return 0;
        }
        if (length == 2) {
            return nums[0] > nums[1] ? 0 : 1;
        }
        return findPeak(nums, 0, length - 1, length / 2);
    }

    public int findPeak(int[] nums, int start, int end, int curr) {
        if (start > end) {
            return -1;
        }
        if (curr == 0 && nums[curr] > nums[curr + 1] ||
                curr == nums.length - 1 && nums[curr] > nums[curr - 1] ||
                nums[curr] > nums[curr + 1] && nums[curr] > nums[curr - 1]) {
            return curr;
        }

        int leftPeak = findPeak(nums, start, curr - 1, (start + curr - 1) / 2);
        if (leftPeak >= 0) {
            return leftPeak;
        }
        return findPeak(nums, curr + 1, end, (curr + 1 + end) / 2);
    }
}

// 165 比较版本号
/*
给你两个 版本号字符串 version1 和 version2 ，请你比较它们。版本号由被点 '.' 分开的修订号组成。修订号的值 是它 转换为整数 并忽略前导零。
比较版本号时，请按 从左到右的顺序 依次比较它们的修订号。如果其中一个版本字符串的修订号较少，则将缺失的修订号视为 0。

返回规则如下：
如果 version1 < version2 返回 -1，
如果 version1 > version2 返回 1，
除此之外返回 0。

示例 1：
输入：version1 = "1.2", version2 = "1.10"
输出：-1
解释：
version1 的第二个修订号为 "2"，version2 的第二个修订号为 "10"：2 < 10，所以 version1 < version2。

示例 2：
输入：version1 = "1.01", version2 = "1.001"
输出：0
解释：
忽略前导零，"01" 和 "001" 都代表相同的整数 "1"。

示例 3：
输入：version1 = "1.0", version2 = "1.0.0.0"
输出：0
解释：
version1 有更少的修订号，每个缺失的修订号按 "0" 处理。

提示：
1 <= version1.length, version2.length <= 500
version1 和 version2 仅包含数字和 '.'
version1 和 version2 都是 有效版本号
version1 和 version2 的所有修订号都可以存储在 32 位整数 中
 */
class compareVersion {
    public int compareVersion(String version1, String version2) {
        String[] version1Split = version1.split("\\.");
        String[] version2Split = version2.split("\\.");
        int length = Math.max(version1Split.length, version2Split.length);
        for (int i = 0; i < length; i++) {
            int version1Value = i < version1Split.length ? Integer.parseInt(version1Split[i]) : 0;
            int version2Value = i < version2Split.length ? Integer.parseInt(version2Split[i]) : 0;
            if (version1Value != version2Value) {
                return version1Value > version2Value ? 1 : -1;
            }
        }

        return 0;
    }
}

// 139. 单词拆分
/*
给你一个字符串 s 和一个字符串列表 wordDict 作为字典。如果可以利用字典中出现的一个或多个单词拼接出 s 则返回 true。

注意：不要求字典中出现的单词全部都使用，并且字典中的单词可以重复使用
示例 1：

输入: s = "leetcode", wordDict = ["leet", "code"]
输出: true
解释: 返回 true 因为 "leetcode" 可以由 "leet" 和 "code" 拼接成。
示例 2：

输入: s = "applepenapple", wordDict = ["apple", "pen"]
输出: true
解释: 返回 true 因为 "applepenapple" 可以由 "apple" "pen" "apple" 拼接成。
     注意，你可以重复使用字典中的单词。
示例 3：

输入: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]

[0,0,1,1,0,0]
输出: false
 */
class wordBreak {
    public boolean wordBreak(String s, List<String> wordDict) {
        if (s.length() == 0) {
            return true;
        }
        if (wordDict.size() == 0) {
            return false;
        }
        HashSet<String> stringHashSet = new HashSet<>(wordDict);
        ArrayList<Integer> checkPoint = new ArrayList<>();
        checkPoint.add(0);
        for (int i = 1; i <= s.length(); i++) {
            boolean flag = false;
            for (int pos : checkPoint) {
                if (stringHashSet.contains(s.substring(pos, i))) {
                    flag = true;
                    break;
                }
            }
            if (flag) {
                checkPoint.add(i);
            }
        }
        return checkPoint.get(checkPoint.size() - 1) == s.length();
    }
}