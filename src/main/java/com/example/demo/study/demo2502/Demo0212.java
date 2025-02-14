package com.example.demo.study.demo2502;


import java.util.*;

public class Demo0212 {

    public static void main(String[] args) {
        String s = new largestNumber().largestNumber(new int[]{34323,3432});
        System.out.println();
    }

}

// 152. 乘积最大子数组 todo
/*
给你一个整数数组 nums ，请你找出数组中乘积最大的非空连续 子数组
（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。
测试用例的答案是一个 32-位 整数。

示例 1:
输入: nums = [2,3,-2,4]
输出: 6
解释: 子数组 [2,3] 有最大乘积 6。
示例 2:
输入: nums = [-2,0,-1]
输出: 0
解释: 结果不能为 2, 因为 [-2,-1] 不是子数组
[2,3,-4,8,32]
[2,6,-2,4]
[-2,0,0]

[-2,3,2,-4]

24


[-2,-6,0,0]
[0, 3, 6,-24]
[0,0,2,-8]
[0,0,0,-4]

f(i,j) 表示i～j的子数组最大乘积
f(i,j-1)   f(i+1,j)
f(i+1,j-1) *



Math.max(Math.max(ints[row][col - 1] * nums[col],ints[row + 1][col] * nums[row]) ,ints[row + 1][col - 1] * nums[row] * nums[col]))

 */
class maxProduct {
    public int maxProduct(int[] nums) {
        int length = nums.length;
        if (length == 1) {
            return nums[0];
        }
        int[][] ints = new int[length][length];
        for (int i = 0; i < length; i++) {
            ints[i][i] = nums[i];
        }

        int result = Integer.MIN_VALUE;
        for (int row = length - 2; row >= 0; row--) {
            for (int col = row + 1; col < length; col++) {
                if (col == row + 1) {
                    ints[row][col] = Math.max(ints[row][col - 1] * nums[col],
                            ints[row + 1][col] * nums[row]);
                } else {
                    ints[row][col] = Math.max(Math.max(ints[row][col - 1] * nums[col],
                            ints[row + 1][col] * nums[row]),
                            ints[row + 1][col - 1] * nums[row] * nums[col]);
                }
                ints[row][col] = Math.max(ints[row][col], nums[col]);
                result = Math.max(ints[row][col], result);
            }
        }


        return result;
    }
}

// 179
/*
给定一组非负整数 nums，重新排列每个数的顺序（每个数不可拆分）使之组成一个最大的整数。
注意：输出结果可能非常大，所以你需要返回一个字符串而不是整数

示例 1：
输入：nums = [10,2]
输出："210"
示例 2：
输入：nums = [3,32,34,5,9]
330
303

343
334

输出："9534330"

nums =
[111311,1113]

添加到测试用例
输出
"1113111113"
预期结果
"1113 111311"

nums =
[34323,3432]

添加到测试用例
输出
"34323 3432"
预期结果
"34323 4323"
 */

class largestNumber {
    public String largestNumber(int[] nums) {
        Integer[] integers1 = new Integer[nums.length];
        for (int i = 0; i < nums.length; i++) {
            integers1[i] = nums[i];
        }
        Arrays.sort(integers1, (o1, o2) -> {
            String o1Str = String.valueOf(o1);
            String o2Str = String.valueOf(o2);
            o1Str = o1Str + o2Str;
            o2Str = o2Str + o1Str;
            for (int i = 0; i < o1Str.length(); i++) {
                char o1Char = o1Str.charAt(i);
                char o2Char = o2Str.charAt(i);
                if (o2Char == o1Char) {
                    continue;
                }
                return o2Char > o1Char ? 1 : -1;
            }
            return 1;
        });
        if (integers1[0] == 0){
            return "0";
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (Integer item : integers1) {
            stringBuilder.append(item);
        }

        return stringBuilder.toString();
    }
}
