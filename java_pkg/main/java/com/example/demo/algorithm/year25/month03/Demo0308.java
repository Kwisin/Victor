package com.example.demo.algorithm.year25.month03;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Demo0308 {

    public static void main(String[] args) {
//        List<List<Integer>> lists = new subsetsWithDup().subsetsWithDup(new int[]{1, 2, 2});
        List<String> strings = new restoreIpAddresses().restoreIpAddresses("101023");
        System.out.println();
    }

}

// 90. 子集 II
/*
给你一个整数数组 nums ，其中可能包含重复元素，请你返回该数组所有可能的 子集（幂集）。
解集 不能 包含重复的子集。返回的解集中，子集可以按 任意顺序 排列。

示例 1：
输入：nums = [1,2,2]
输出：[[],[1],[1,2],[1,2,2],[2],[2,2]]
示例 2：
输入：nums = [0]
输出：[[],[0]]
 */
class subsetsWithDup {
    List<List<Integer>> result = new ArrayList<>();

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        int length = nums.length;
        if (length == 0) {
            return result;
        }
        Arrays.sort(nums);
        dfs(nums, 0, new ArrayList<>());
        return result;
    }

    public void dfs(int[] nums, int index, List<Integer> curr) {
        this.result.add(new ArrayList<>(curr));
        for (int i = index; i < nums.length; i++) {
            if (i > index && nums[i] == nums[i - 1]) {
                continue;
            }
            curr.add(nums[i]);
            dfs(nums, i + 1, curr);
            curr.remove(curr.size() - 1);
        }
    }
}

// 93. 复原 IP 地址
/*
有效 IP 地址 正好由四个整数（每个整数位于 0 到 255 之间组成，且不能含有前导 0），整数之间用 '.' 分隔。

例如："0.1.2.201" 和 "192.168.1.1" 是 有效 IP 地址，但是 "0.011.255.245"、"192.168.1.312" 和 "192.168@1.1" 是 无效 IP 地址。
给定一个只包含数字的字符串 s ，用以表示一个 IP 地址，返回所有可能的有效 IP 地址，这些地址可以通过在 s 中插入 '.' 来形成。你 不能 重新排序或删除 s 中的任何数字。你可以按 任何 顺序返回答案。



示例 1：

输入：s = "25525511135"
输出：["255.255.11.135","255.255.111.35"]
示例 2：

输入：s = "0000"
输出：["0.0.0.0"]
示例 3：

输入：s = "101023"
输出：["1.0.10.23","1.0.102.3","10.1.0.23","10.10.2.3","101.0.2.3"]
 */
class restoreIpAddresses {
    List<String> result = new ArrayList<String>();

    public List<String> restoreIpAddresses(String s) {
        if (s.length() > 12) {
            return result;
        }
        dfs(new String[4], 0, s);
        return result;
    }

    public void dfs(String[] curr, int index, String s) {
        if (checkRemain(s.length(), 4 - index)) {
            return;
        }
        if (index == 4 && s.isEmpty()) {
            result.add(String.join(".", curr));
            return;
        }
        if (s.isEmpty()) {
            return;
        }
        for (int i = 1; i <= 3 && i <= s.length(); i++) {
            String substring = s.substring(0, i);
            if (checkValid(substring)) {
                curr[index] = substring;
                dfs(curr, index + 1, s.substring(i));
                curr[index] = "";
            }
        }
    }

    public boolean checkValid(String s) {
        if (s.length() > 1 && s.charAt(0) == '0') {
            return false;
        }
        if (Integer.parseInt(s) > 255 || Integer.parseInt(s) < 0) {
            return false;
        }
        return true;
    }

    public boolean checkRemain(int remain, int need) {
        return remain > need * 3;
    }
}

