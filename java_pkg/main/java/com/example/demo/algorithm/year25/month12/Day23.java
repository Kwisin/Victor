package com.example.demo.algorithm.year25.month12;

import java.util.Stack;

public class Day23 {
    public static void main(String[] args) {
        Day23 day23 = new Day23();
        // 测试用例
        int min = day23.findMin(new int[]{4, 5, 6, 7, 0, 1, 2});
        System.out.println(min); // 应输出 4
    }

    /*
    33. 搜索旋转排序数组 (Search in Rotated Sorted Array)
    中等

    整数数组 nums 按升序排列，数组中的值 互不相同 。
    在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转，
    使数组变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。
    例如， [0,1,2,4,5,6,7] 在下标 3 处经旋转后可能变为 [4,5,6,7,0,1,2] 。

    给你 旋转后 的数组 nums 和一个整数 target ，如果 nums 中存在这个目标值 target ，则返回它的下标，否则返回 -1 。

    你必须设计一个时间复杂度为 O(log n) 的算法解决此问题。

    示例 1：
    输入：nums = [6,7,8,1,2,3,4,5], target = 0
    输出：4

    示例 2：
    输入：nums = [4,5,6,7,0,1,2], target = 3
    输出：-1

    示例 3：
    输入：nums = [1], target = 0
    输出：-1
     */
    public int search(int[] nums, int target) {
        return searchMid(nums, 0, nums.length - 1, target);
    }

    public int searchMid(int[] nums, int start, int end, int target) {
        if (start > end) {
            return -1;
        }

        int mid = (start + end) / 2;
        if (nums[mid] == target) {
            return mid;
        }

        if (nums[end] > nums[mid]) {
            // 右侧有序
            if (target > nums[mid] && target <= nums[end]) {
                return searchMid(nums, mid + 1, end, target);
            } else {
                return searchMid(nums, start, mid - 1, target);
            }

        } else {
            // 左侧有序
            if (target < nums[mid] && target >= nums[start]) {
                return searchMid(nums, start, mid - 1, target);
            } else {
                return searchMid(nums, mid + 1, end, target);
            }

        }
    }

    /*
    153. 寻找旋转排序数组中的最小值
    中等

    已知一个长度为 n 的数组，预先按照升序排列，经由 1 到 n 次 旋转 后，得到输入数组。
    例如，原数组 nums = [0,1,2,4,5,6,7] 在变化后可能得到：
    若旋转 4 次，则可以得到 [5,6,7,0,1,2,3,4]
    若旋转 7 次，则可以得到 [0,1,2,4,5,6,7]

    注意，数组 [a[0], a[1], a[2], ..., a[n-1]] 旋转一次 的结果为数组 [a[n-1], a[0], a[1], a[2], ..., a[n-2]] 。

    给你一个元素值 互不相同 的数组 nums ，它原来是一个升序排列的数组，并按上述情形进行了多次旋转。
    请你找出并返回数组中的 最小元素 。

    你必须设计一个时间复杂度为 O(log n) 的算法解决此问题。

    示例 1：
    输入：nums = [3,4,5,1,2]
    输出：1
    解释：原数组为 [1,2,3,4,5] ，旋转 3 次得到输入数组。

    示例 2：
    输入：nums = [4,5,6,7,0,1,2]
    输出：0
    解释：原数组为 [0,1,2,4,5,6,7] ，旋转 4 次得到输入数组。

    示例 3：
    输入：nums = [11,13,15,17]
    输出：11
    解释：原数组为 [11,13,15,17] ，旋转 4 次得到输入数组。
     */
    public int findMin(int[] nums) {
        return searchMin(nums, 0, nums.length - 1);
    }

    /**
     * 迭代解法 (推荐)
     * 时间复杂度 O(log N)，空间复杂度 O(1)
     */
    public int findMinIteration(int[] nums) {
        int left = 0;
        int right = nums.length - 1;

        // 循环不变量：最小值一定在 [left, right] 区间内
        while (left < right) {
            int mid = left + (right - left) / 2;

            // Case 1: nums[mid] < nums[right]
            // 说明 [mid, right] 这一段是升序的 (e.g., [..., 1, 2, 3])
            // 最小值肯定不在 (mid, right] 这一段里，但可能是 mid 自己
            // 所以我们收缩右边界到 mid
            if (nums[mid] < nums[right]) {
                right = mid;
            }
            // Case 2: nums[mid] > nums[right]
            // 说明 [mid, right] 这一段中间有个断崖 (e.g., [4, 5, ..., 1, 2])
            // 最小值肯定在 mid 的右边，且 mid 自己肯定不是最小值
            // 所以我们收缩左边界到 mid + 1
            else {
                left = mid + 1;
            }
        }
        // 当 left == right 时，剩下的那个就是最小值
        return nums[left];
    }

    public int searchMin(int[] nums, int start, int end) {
        if (start > end) {
            return Integer.MAX_VALUE;
        }

        int mid = (start + end) / 2;
        int leftMin = Integer.MAX_VALUE;
        int rightMin = Integer.MAX_VALUE;

        if (nums[end] > nums[mid]) {
            // 右侧有序
            rightMin = nums[mid];
            leftMin = searchMin(nums, start, mid - 1);
        } else {
            leftMin = nums[start];
            rightMin = searchMin(nums, mid + 1, end);
        }

        return Math.min(leftMin, rightMin);
    }

    /*
    162. 寻找峰值 (Find Peak Element)
    中等

    峰值元素是指其值严格大于左右相邻值的元素。

    给你一个整数数组 nums，找到峰值元素并返回其索引。数组可能包含多个峰值，在这种情况下，返回 任何一个峰值 所在位置即可。

    你可以假设 nums[-1] = nums[n] = -∞ 。

    你必须实现时间复杂度为 O(log n) 的算法来解决此问题。

    示例 1：
    输入：nums = [1,2,3,1]
    输出：2
    解释：3 是峰值元素，你的函数应该返回其索引 2。

    示例 2：
    输入：nums = [1,2,1,3,5,6,4]
    输出：1 或 5
    解释：你的函数可以返回索引 1，其峰值元素为 2；
         或者返回索引 5， 其峰值元素为 6。
     */
    public int findPeakElement(int[] nums) {
        int length = nums.length;
        if (length == 0) {
            return -1;
        }
        if (length == 1) {
            return 0;
        }
        if (nums[0] > nums[1]) {
            return 0;
        }
        if (nums[length - 1] > nums[length - 2]) {
            return length - 1;
        }
        return findPeak(nums, 0, length - 1);
    }


    public int findPeak(int[] nums, int start, int end) {
        if (start > end) {
            return -1;
        }

        int mid = (start + end) / 2;
        if (mid >= 1 && mid <= nums.length - 2 &&
                nums[mid] > nums[mid - 1] && nums[mid] > nums[mid + 1]) {
            return mid;
        }
        int peak = findPeak(nums, start, mid - 1);
        if (peak != -1) {
            return peak;
        }
        return findPeak(nums, mid + 1, end);
    }

    /*
    34. 在排序数组中查找元素的第一个和最后一个位置
    中等

    给你一个按照非递减顺序排列的整数数组 nums，和一个目标值 target。请你找出给定目标值在数组中的开始位置和结束位置。

    如果数组中不存在目标值 target，返回 [-1, -1]。

    你必须设计并实现时间复杂度为 O(log n) 的算法解决此问题。

    示例 1：
    输入：nums = [5,7,7, 8, 8, 8, 8,9,10], target = 8
    输出：[3,4]

    示例 2：
    输入：nums = [5,7,7,8,8,10], target = 6
    输出：[-1,-1]

    示例 3：
    输入：nums = [], target = 0
    输出：[-1,-1]
     */
    public int[] searchRange(int[] nums, int target) {
        int[] result = new int[]{-1, -1};
        if (nums == null || nums.length == 0) {
            return result;
        }

        // 1. 找左边界 (First occurrence)
        int leftBound = findBound(nums, target, true);
        
        // 如果左边界都没找到，那肯定没有 target
        if (leftBound == -1) {
            return result;
        }

        // 2. 找右边界 (Last occurrence)
        int rightBound = findBound(nums, target, false);

        result[0] = leftBound;
        result[1] = rightBound;
        return result;
    }

    /**
     * @param isLeft True 找左边界，False 找右边界
     */
    private int findBound(int[] nums, int target, boolean isLeft) {
        int left = 0;
        int right = nums.length - 1;
        int bound = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                bound = mid; // 记录当前位置，但别停！
                if (isLeft) {
                    right = mid - 1; // 找左边界，尝试往左收缩
                } else {
                    left = mid + 1;  // 找右边界，尝试往右收缩
                }
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return bound;
    }

}
