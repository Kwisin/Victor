package com.example.demo.study.demo2503;


import org.apache.el.stream.Stream;

import java.util.*;
import java.util.stream.Collectors;

public class Demo0303 {

    public static void main(String[] args) {
        int calculate = new calculate().calculate("-3545+2001*21/3-200+3*5+3*3/6+4/2*4*5");
        int i = new maximalSquare().maximalSquare(new char[][]{
                {'1', '0', '1', '0', '0'},
                {'1', '0', '1', '1', '1'},
                {'1', '1', '1', '1', '1'},
                {'1', '0', '0', '1', '0'}});
        System.out.println();
    }

}

// 229. 多数元素 II
/*
给定一个大小为 n 的整数数组，找出其中所有出现超过 ⌊ n/3 ⌋ 次的元素。

示例 1：
输入：nums = [3,2,3]
输出：[3]
示例 2：
输入：nums = [1]
输出：[1]
示例 3：
输入：nums = [1,2]
输出：[1,2]
 */
class majorityElement {
    public List<Integer> majorityElement(int[] nums) {
        int length = nums.length;
        if (length < 3) {
            return Arrays.stream(nums).boxed().collect(Collectors.toList());
        }
        int target = length / 3;

        HashMap<Integer, Integer> intCnt = new HashMap<>();
        for (int i = 0; i < length; i++) {
            int key = nums[i];
            Integer orDefault = intCnt.getOrDefault(key, 0);
            if (orDefault + length - 1 - i < target) {
                intCnt.remove(key);
                continue;
            }
            intCnt.put(key, orDefault + 1);
        }
        ArrayList<Integer> result = new ArrayList<>();
        intCnt.forEach((key, val) -> {
            if (val > target) {
                result.add(key);
            }
        });

        return result;
    }
}

// 221. 最大正方形
/*
在一个由 '0' 和 '1' 组成的二维矩阵内，找到只包含 '1' 的最大正方形，并返回其面积。

输入：matrix = [
['1","1","1","0","0"],
["1","1","2","1","1"],
["1","0","1","2","1"],
["1","0","0","1","0"]]
输出：4

输入：matrix = [
["0","1"],
["1","0"]]
输出：1
示例 3：

输入：matrix = [["0"]]
输出：0
 */
class maximalSquare {
    public int maximalSquare(char[][] matrix) {
        int row = matrix.length;
        if (row == 0)
            return 0;
        int col = matrix[0].length;
        if (col == 0)
            return 0;

        int result = 0;


        return result;
    }
}

// 227. 基本计算器 II
/*
给你一个字符串表达式 s ，请你实现一个基本计算器来计算并返回它的值。
整数除法仅保留整数部分。
你可以假设给定的表达式总是有效的。所有中间结果将在 [-231, 231 - 1] 的范围内。
注意：不允许使用任何将字符串作为数学表达式计算的内置函数，比如 eval() 。

示例 1：
输入：s = "+-3545+2001*21/3+-200+3*5+3"
-3545，2001*21/3 ,-200，3*5，3
2001*21/3，200
2001，21/3
    21，3
输出：7
示例 2：
输入：s = " 3/2 "
输出：1
示例 3：
输入：s = " 3+5 / 2 "
输出：5
 */
class calculate {
    public int calculate(String s) {
        int length = s.length();
        if (length == 0)
            return 0;
        for (int i = 0; i < length; i++) {
            if (s.charAt(i) == '-') {
                s = s.substring(0, i) + "+" + s.substring(i);
                i++;
            }
        }
        String[] addSplit = s.split("\\+");
        int result = 0;
        for (String item : addSplit) {
            try {
                if (item.equals("")) {
                    continue;
                }
                result += Integer.parseInt(item);
            } catch (NumberFormatException e) {
                result += getMulResult(item);
            }
        }

        return result;
    }

    public int getMulResult(String s) {
        String[] mulSplit = s.split("\\*");
        int result = 1;
        for (String item : mulSplit) {
            try {
                if (item.equals("")) {
                    continue;
                }
                result *= Integer.parseInt(item);
            } catch (NumberFormatException e) {
                result *= getDviResult(item);
            }
        }
        return result;
    }

    public int getDviResult(String s) {
        String[] dviSplit = s.split("/");
        int result = Integer.parseInt(dviSplit[0]);
        for (int i = 1; i < dviSplit.length; i++) {
            result /= Integer.parseInt(dviSplit[i]);
        }
        return result;
    }
}