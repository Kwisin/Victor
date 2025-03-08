package com.example.demo.study.demo2501;


import java.util.Arrays;

public class Demo0119 {

    public static void main(String[] args) {
        int i = new trap().trap(new int[]{0,1,0,2,1,0,1,3,2,1,2,1});
        System.out.println();
    }


}

// 41
class firstMissingPositive {
    public int firstMissingPositive(int[] nums) {
        int length = nums.length;
        if (length == 0) {
            return 0;
        }
        int currIndex = 0;
        while (currIndex < length) {
            int currVal = nums[currIndex];
            if (currVal < 1 || currVal > length || currVal == currIndex + 1 || currVal == nums[currVal - 1]) {
                currIndex++;
                continue;
            }
            // swap
            int temp = nums[currVal - 1];
            nums[currVal - 1] = currVal;
            nums[currIndex] = temp;
        }

        for (int i = 0; i < length; i++) {
            if (i + 1 != nums[i]) {
                return i + 1;
            }
        }
        return length + 1;
    }
}

// 42
class trap {
    public int trap(int[] height) {
        int result = 0;
        int length = height.length;
        if (length == 0) {
            return 0;
        }
        int[] leftMax = new int[length];
        leftMax[0] = height[0];
        for (int i = 1; i < length; i++) {
            leftMax[i] = Math.max(leftMax[i - 1], height[i]);
        }

        int rightMax = height[length - 1];
        for (int i = length - 2; i > 0; i--) {
            rightMax = Math.max(rightMax, height[i]);
            result += Math.min(leftMax[i], rightMax) - height[i];
        }
        return result;
    }
}

