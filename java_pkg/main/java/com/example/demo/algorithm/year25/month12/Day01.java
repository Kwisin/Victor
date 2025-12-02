package com.example.demo.algorithm.year25.month12;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class Day01 {
    public static void main(String[] args) {
        int[] ints = new Day01().maxSlidingWindow(new int[]{1,3,-1,-3,5,3,6,7}, 3);
        System.out.println(Arrays.toString(ints));
    }

    /*
    239. 滑动窗口最大值
    给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。
    你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
    
    返回滑动窗口中的最大值。
    
    示例 1：
    输入：nums = [1,3,-1,-3,5,3,6,7], k = 3
    [1,1,3,3,5,2,4,8,3]
    [0]
    []
    [3,3,5,5,-1,8,8,-1]
    [3,3,5,5,-1,8,8,8]
    [8 ,7,6,5,2,6,7,1,8]
    [-1,]

    [1,1,3,5,4,2,4,8,3]
    [3,5,5,5,4,8,8,8,3]
    [3,5,5,5,5,8,8,8,8]



    [3,5,5,5,4,8,8,8,3]
    [3,3,5,5,5,4,8,8,8]


    输出：[3,3,5,5,6,7]
    解释：
    滑动窗口的位置                最大值
    ---------------               -----
    [1  3  -1] -3  5  3  6  7       3
     1 [3  -1  -3] 5  3  6  7       3
     1  3 [-1  -3  5] 3  6  7       5
     1  3  -1 [-3  5  3] 6  7       5
     1  3  -1  -3 [5  3  6] 7       6
     1  3  -1  -3  5 [3  6  7]      7
    
    示例 2：
    输入：nums = [1], k = 1
    输出：[1]
    
    提示：
    1 <= nums.length <= 10^5
    -10^4 <= nums[i] <= 10^4
    1 <= k <= nums.length
    */
    public int[] maxSlidingWindow(int[] nums, int k) {
        //[1,1,3,5,4,2,4,8,3]
        /*
        [0]
        [0,1]
        [1,2] [3,3]
        [2,3] [3,5,5]
         */
        if (nums == null || nums.length == 0) {
            return new int[0];
        }
        int length = nums.length;
        int[] result = new int[length];
        Stack<Integer> stack = new Stack<>();
        ArrayList<Integer> reWrite = new ArrayList<>();
        // 从左往右
        for (int i = 0; i < length; i++) {
            result[i] = nums[i];
            reWrite.clear();
            while (!stack.isEmpty() && nums[stack.peek()] < nums[i] && i - stack.peek() < k) {
                Integer pop = stack.pop();
                if (i - pop < k - 1) {
                    reWrite.add(pop);
                }
                result[pop] = nums[i];
            }
            if (!reWrite.isEmpty()){
                stack.addAll(reWrite);
            }
            stack.push(i);
        }


        // 从左往右stack
        stack.clear();
        for (int i = length - 1; i >= 0; i--) {
            reWrite.clear();
            while (!stack.isEmpty() && nums[stack.peek()] < nums[i] && stack.peek() - i < k) {
                Integer pop = stack.pop();
                if (pop - i < k - 1) {
                    reWrite.add(pop);
                }
                result[pop] = Math.max(result[pop], nums[i]);
            }
            if (!reWrite.isEmpty()){
                stack.addAll(reWrite);
            }
            stack.push(i);
        }

        return result;

    }
}
