package com.example.demo.algorithm.year25.month12;

import java.util.Stack;

public class Day02 {
    public static void main(String[] args) {
        int water = new Day02().getWater(new int[]{4,2,0,3,2,5});
        System.out.println(water);
    }

    /*
    42. 接雨水
    给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
    
    示例 1：1,2,3,0
    输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
                  [0,0,1,0,1,2,1,0,0,1,0,0]

    输出：6
    解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。
    
    示例 2：
    输入：height = [4,2,0,3,2,5]
    输出：9
    
    提示：
    n == height.length
    1 <= n <= 2 * 10^4
    0 <= height[i] <= 10^5
    */
    public int getWater(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        int length = height.length;
        Stack<Integer> stack = new Stack<>();
        int[] left = new int[length];
        int[] right = new int[length];
        for (int i = 0; i < length; i++) {
            if (!stack.isEmpty() && height[i] <= height[stack.peek()]) {
                left[i] = height[stack.peek()];
                continue;
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            Integer pop = stack.pop();
            left[pop] = height[pop];
        }

        for (int i = length - 1; i >= 0; i--) {
            if (!stack.isEmpty() && height[i] <= height[stack.peek()]) {
                right[i] = height[stack.peek()];
                continue;
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            Integer pop = stack.pop();
            right[pop] = height[pop];
        }

        int result = 0;
        for (int i = 0; i < length; i++) {
            int min = Math.min(left[i], right[i]);
            result += min > height[i] ? min - height[i] : 0;
        }

        return result;

    }

}
