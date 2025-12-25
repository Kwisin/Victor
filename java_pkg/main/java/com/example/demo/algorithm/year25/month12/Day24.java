package com.example.demo.algorithm.year25.month12;

public class Day24 {
    public static void main(String[] args) {
        Day24 day24 = new Day24();

        // 1. 搜索插入位置
        System.out.println(day24.searchInsert(new int[]{1, 3, 5, 6}, 9)); // 2

        // 2. x 的平方根
        System.out.println(day24.mySqrt(100)); // 2

        // 3. 搜索二维矩阵
        int[][] matrix = {{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 60}};
        System.out.println(day24.searchMatrix(matrix, 3)); // true

        // 4. 爱吃香蕉的珂珂
        System.out.println(day24.minEatingSpeed(new int[]{3,6,7,11,12,17}, 8)); // 4
    }

    /*
    35. 搜索插入位置 (Search Insert Position)
    简单
    给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。
    如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
    请必须使用时间复杂度为 O(log n) 的算法。

    示例 1: 输入: nums = [1,3,5,6], target = 5 -> 输出: 2
    示例 2: 输入: nums = [1,3,5,6], target = 2 -> 输出: 1
    */
    public int searchInsert(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    /*
    69. x 的平方根 (Sqrt(x))
    二分查找解法：在 [1, x] 范围内查找 k，使得 k <= x/k。
    */
    public int mySqrt(int x) {
        if (x == 0) return 0;
        int left = 1;
        int right = x;
        int ans = 0;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            // 防止溢出：使用除法而不是乘法
            if (mid <= x / mid) {
                ans = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return ans;
    }

    /*
    74. 搜索二维矩阵 (Search a 2D Matrix)
    中等
    给你一个满足下述两条属性的 m x n 整数矩阵：
    1. 每行中的整数从左到右按非严格递增顺序排列。
    2. 每行的第一个整数大于前一行的最后一个整数。
    给你一个整数 target ，如果 target 在矩阵中，返回 true ；否则，返回 false 。

    示例 1: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3 -> true
    */
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        int rowLen = matrix.length;
        int colLen = matrix[0].length;
        int rowLeft = 0;
        int rowRight = rowLen - 1;
        int[] toCheck = null;
        while (rowLeft <= rowRight) {
            int mid = rowLeft + (rowRight - rowLeft) / 2;
            if (matrix[mid][0] <= target && matrix[mid][colLen - 1] >= target) {
                toCheck = matrix[mid];
                break;
            }

            if (target < matrix[mid][0]) {
                rowRight = mid - 1;
            } else if (target > matrix[mid][colLen - 1]) {
                rowLeft = mid + 1;
            }
        }

        if (toCheck == null) {
            return false;
        }

        int colLeft = 0;
        int colRight = colLen - 1;
        while (colLeft < colRight) {
            int mid = colLeft + (colRight - colLeft) / 2;
            if (toCheck[mid] == target) {
                return true;
            }

            if (target < toCheck[mid]) {
                colRight = mid - 1;
            } else {
                colLeft = mid + 1;
            }
        }

        return false;
    }

    /*
    875. 爱吃香蕉的珂珂 (Koko Eating Bananas) - 经典"二分答案"题
    中等
    珂珂喜欢吃香蕉。这里有 n 堆香蕉，第 i 堆中有 piles[i] 根香蕉。警卫已经离开了，将在 h 小时后回来。
    珂珂可以决定她吃香蕉的速度 k （单位：根/小时）。每个小时，她将会选择一堆香蕉，从中吃掉 k 根。
    如果这堆香蕉少于 k 根，她将吃掉这堆的所有香蕉，然后这一小时内不会再吃更多的香蕉。
    
    珂珂喜欢慢慢吃，但仍然想在警卫回来前吃掉所有的香蕉。
    返回她可以在 h 小时内吃掉所有香蕉的最小速度 k（k 为整数）。

    示例 1: piles = [3,6,7,11,12], h = 8 -> 输出: 4
    6
    3/2 = 1
    示例 2: piles = [30,11,23,4,20], h = 5 -> 输出: 30
    */
    public int minEatingSpeed(int[] piles, int h) {
        // 速度范围：[1, max(piles)]
        int left = 1;
        int right = 0;
        for (int pile : piles) {
            right = Math.max(right, pile);
        }

        while (left < right) {
            int mid = left + (right - left) / 2;
            
            // 计算当前速度 mid 需要几小时
            int hours = 0;
            for (int pile : piles) {
                // 等价于 Math.ceil(pile / mid)
                hours += (pile + mid - 1) / mid;
            }
            
            // 核心逻辑：
            // 如果 hours <= h，说明速度够快（可能是正好的，也可能太快了），mid 可能是答案 -> 保留 mid，尝试更慢的速度
            if (hours <= h) {
                right = mid; 
            } 
            // 如果 hours > h，说明超时了（速度太慢），mid 肯定不行 -> 排除 mid，必须加速
            else {
                left = mid + 1;
            }
        }
        // 循环结束 left == right，即为最小可行速度
        return left;
    }
}
