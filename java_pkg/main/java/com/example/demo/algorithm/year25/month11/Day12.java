package com.example.demo.algorithm.year25.month11;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Day12 {
    public static void main(String[] args) {

    }

    //    164
    //    给定一个无序的数组 nums，返回 数组在排序之后，相邻元素之间最大的差值 。如果数组元素个数小于 2，则返回 0 。
    //    您必须编写一个在「线性时间」内运行并使用「线性额外空间」的算法。
    //
    //    示例 1:
    //    输入: nums = [3,6,9,1] 。8/4 = 2 。0，1，。2 ， 3。4，5 。6，7. 8, 9。
    // [1,3] [4,6] [7,9]   (2+1)*(index+1)-2

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
        if (nums == null || nums.length <= 2) {
            return 0;
        }

        // 找出最大值和最小值
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int num : nums) {
            min = Math.min(min, num);
            max = Math.max(max, num);
        }

        //分桶，根据平均间隙
        int average_gap = (max - min) / (nums.length - 1);
        int[] rightBound = new int[nums.length - 1];
        int[] leftBound = new int[nums.length - 1];
        for (int num : nums) {
            int index = (num - min - 1) / average_gap;
            rightBound[index] = Math.max(rightBound[index], num);
            leftBound[index] = Math.min(leftBound[index], num);
        }
        int max_gap = 0;
        for (int i = 1; i < rightBound.length; i++) {
            if (rightBound[i] == leftBound[i] && rightBound[i] == 0) {

            }
        }


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
    搞一个分桶，每个桶内放index*（valueDiff+1）~(index+1)*（valueDiff+1）之间的数，
    只要落在同一个桶内，就存在这样一对数满足要求。
    还要注意一个点就是要在indexDiff范围内，往前移动的时候要把之前的数从桶里拿掉

     */

    public boolean containsNearbyAlmostDuplicate(int[] nums, int indexDiff, int valueDiff) {
        Map<Long, Long> bucket = new HashMap<>();
        int bucketSize = valueDiff + 1;
        for (int i = 0; i < nums.length; i++) {
            if (i > indexDiff) {
                // 先把最前面一个数字拿掉
                int num = nums[i - indexDiff - 1];
                bucket.remove(getBucketIndex(num, bucketSize));
            }

            long curr = nums[i];
            Long bucketIndex = getBucketIndex(curr, bucketSize);
            if (bucket.containsKey(bucketIndex)) {
                // 撞桶
                return true;
            }
            // 检查相邻的桶
            if (bucket.containsKey(bucketIndex - 1) && Math.abs(curr - bucket.get(bucketIndex - 1)) <= valueDiff) {
                return true;
            }
            if (bucket.containsKey(bucketIndex + 1) && Math.abs(curr - bucket.get(bucketIndex + 1)) <= valueDiff) {
                return true;
            }
            bucket.put(bucketIndex, curr);

        }
        return false;
    }

    public Long getBucketIndex(long num, int bucketSize) {
        return (long) Math.floor(num / (double) bucketSize);
    }
}


