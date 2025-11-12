package com.example.demo.algorithm.year25.month03;

public class Demo0327 {

    public static void main(String[] args) {
        int i = new countRangeSum().countRangeSum(new int[]{-2147483647, 0, -2147483647, 2147483647}, -564, 3864);
        System.out.println();
    }

}

// 327. 区间和的个数
/*
给你一个整数数组 nums 以及两个整数 lower 和 upper 。求数组中，值位于范围 [lower, upper] （包含 lower 和 upper）之内的区间和的个数 。
区间和 S(i, j) 表示在 nums 中，位置从 i 到 j 的元素之和，包含 i 和 j (i ≤ j)。

示例 1：
输入：nums = [-2,5,-1], lower = -2, upper = 2
输出：3
解释：存在三个区间：[0,0]、[2,2] 和 [0,2] ，对应的区间和分别是：-2 、-1 、2 。

[-2,3,2]
[  ,5,4]
[  , ,-1]

示例 2：
输入：nums = [0], lower = 0, upper = 0
输出：1

[-2147483647,0,-2147483647,2147483647]

 todo 超时问题
 */

class countRangeSum {
    public int countRangeSum(int[] nums, int lower, int upper) {
        int result = 0;
        int length = nums.length;
        long curr;
        for (int i = 0; i < length; i++) {
            curr = nums[i];
            for (int j = i; j < length; j++) {
                if (j == i) {
                    curr = nums[j];
                } else {
                    curr = curr + nums[j];
                }
                result += curr >= lower && curr <= upper ? 1 : 0;
            }
        }

        return result;
    }
}

//
