package com.example.demo.algorithm.year25.month12;

import java.util.Arrays;
import java.util.Stack;

public class Day22 {
    public static void main(String[] args) {
        Day22 day22 = new Day22();
        int[] ints = {6, 0, 4, 3, 2, 1};
        day22.stackSolution(ints);
        System.out.println(Arrays.toString(ints));
    }
    
    /*
    31. 下一个排列
    整数数组的 下一个排列 是指其整数的下一个字典序更大的排列。更正式地，如果数组的所有排列根据其字典顺序从小到大排列在一个容器中，
    那么数组的 下一个排列 就是在这个有序容器中排在它后面的那个排列。如果不存在下一个更大的排列，那么这个数组必须重排为字典序最小的排列（即，其元素升序排列）。
    
    必须 原地 修改，只允许使用额外常数空间。
    
    示例 1：
    输入：nums = [1,2,3]
    输出：[1,3,2]
    
    示例 2：
    输入：nums = [3,2,1]
    输出：[1,2,3]
    
    示例 3：
    输入：nums = [1,1,5]
    输出：[1,5,1]
    
    提示：
    1 <= nums.length <= 100
    0 <= nums[i] <= 100
     */

    public void nextPermutation(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return;
        }

        // 1. 从后往前找第一个升序对 (i, i+1)，满足 nums[i] < nums[i+1]
        int i = nums.length - 2;
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }

        // 2. 如果找到了这样的 i (即不是全降序)
        if (i >= 0) {
            // 从后往前找第一个比 nums[i] 大的数
            int j = nums.length - 1;
            while (j > i && nums[j] <= nums[i]) {
                j--;
            }
            // 3. 交换 nums[i] 和 nums[j]
            swap(nums, i, j);
        }

        // 4. 反转 i 之后的所有元素 (变成升序)
        // 如果 i < 0 (全降序)，这里会反转整个数组，正好符合题目要求
        reverse(nums, i + 1, nums.length - 1);
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private void reverse(int[] nums, int start, int end) {
        while (start < end) {
            swap(nums, start, end);
            start++;
            end--;
        }
    }


    public void switchItem(int[] nums, int pre, int next) {
        int temp = nums[pre];
        nums[pre] = nums[next];
        nums[next] = temp;
    }

    public void stackSolution(int[] nums) {
        Stack<Integer> integers = new Stack<>();
        int curr = -1;
        int index = -1;
        for (int i = nums.length - 1; i >= 0; i--) {
            curr = i;
            while (!integers.isEmpty() && nums[i] < nums[integers.peek()]) {
                index = integers.pop();
            }
            if (index != -1) {
                break;
            }
            integers.push(i);
        }

        if (index == -1) {
            for (int i = 0; i < nums.length / 2; i++) {
                int temp = nums[i];
                nums[i] = nums[nums.length - i - 1];
                nums[nums.length - i - 1] = temp;
            }

            return;
        }

        int temp = nums[curr];
        nums[curr] = nums[index];
        nums[index] = temp;

    }

}
