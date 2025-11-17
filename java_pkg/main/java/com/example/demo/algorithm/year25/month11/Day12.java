package com.example.demo.algorithm.year25.month11;

public class Day12 {
    public static void main(String[] args) {

    }
    //    164
    //    给定一个无序的数组 nums，返回 数组在排序之后，相邻元素之间最大的差值 。如果数组元素个数小于 2，则返回 0 。
    //    您必须编写一个在「线性时间」内运行并使用「线性额外空间」的算法。
    //
    //    示例 1:
    //    输入: nums = [3,6,9,1]
    //    输出: 3
    //    解释: 排序后的数组是 [1,3,6,9], 其中相邻元素 (3,6) 和 (6,9) 之间都存在最大差值 3。
    //    示例 2:
    //    输入: nums = [10]
    //    输出: 0
    //    解释: 数组元素个数小于 2，因此返回 0。
    /*
    示例 1:
    输入: nums = [3,6,9,1]
    输出: 3
    解释: 排序后的数组是 [1,3,6,9], 其中相邻元素 (3,6) 和 (6,9) 之间都存在最大差值 3。
    示例 2:
    输入: nums = [10]
    输出: 0
    解释: 数组元素个数小于 2，因此返回 0。

    提示:
    1 <= nums.length <= 10^5
    0 <= nums[i] <= 10^9
     */
    public int maximumGap(int[] nums) {

        return 0;
    }

    /* 220
    给你一个整数数组 nums 和两个整数 indexDiff 和 valueDiff 。

    找出满足下述条件的下标对 (i, j)：
    i != j,
    abs(i - j) <= indexDiff
    abs(nums[i] - nums[j]) <= valueDiff
    如果存在，返回 true ；否则，返回 false 。

    示例 1：
    输入：nums = [1,2,3,1], indexDiff = 3, valueDiff = 0
    输出：true
    解释：可以找出 (i, j) = (0, 3) 。
    满足下述 3 个条件：
    i != j --> 0 != 3
    abs(i - j) <= indexDiff --> abs(0 - 3) <= 3
    abs(nums[i] - nums[j]) <= valueDiff --> abs(1 - 1) <= 0
    示例 2：
    输入：nums = [1,5,9,1,5,9], indexDiff = 2, valueDiff = 3
    输出：false
    解释：尝试所有可能的下标对 (i, j) ，均无法满足这 3 个条件，因此返回 false 。

    提示：
    2 <= nums.length <= 10^5
    -10^9 <= nums[i] <= 10^9
    1 <= indexDiff <= nums.length
    0 <= valueDiff <= 10^9
     */

    public boolean containsNearbyAlmostDuplicate(int[] nums, int indexDiff, int valueDiff) {
        return false;
    }
}


