package com.example.demo.study.demo2407;

public class Demo0715 {

    public static void main(String[] args) {
        int i = new FirstMissingPositive().firstMissingPositive(new int[]{1, 2, 3});
        System.out.printf(String.valueOf(i));

    }

}

// todo 41
class FirstMissingPositive {
    //给你一个未排序的整数数组 nums ，请你找出其中没有出现的最小的正整数。
    //
    //请你实现时间复杂度为 O(n) 并且只使用常数级别额外空间的解决方案。
    // 1，2，3，4，8，9    5
    //输入：nums = [3,4,-1,1] [1,2,3] 4
    //输出：2
    //解释：1 在数组中，但 2 没有。
    public int firstMissingPositive(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        for (int i = 0; i < nums.length; ) {
            if (nums[i] >= nums.length || nums[i] <= 0) {
                nums[i] = -1;
                i++;
                continue;
            }
            if (nums[i] == i) {
                i++;
                continue;
            }
            if (nums[nums[i]] < 0) {
                nums[nums[i]] = nums[i];
                nums[i] = -1;
                i++;
            } else {
                int temp = nums[nums[i]];
                nums[nums[i]] = nums[i];
                nums[i] = temp;
            }

        }

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < 0) {
                return i;
            }
        }

        return nums[nums.length - 1] + 1;
    }
}


