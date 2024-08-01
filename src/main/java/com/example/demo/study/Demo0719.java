package com.example.demo.study;

public class Demo0719 {


    public static void main(String[] args) {
        largestRectangleArea largestRectangleArea = new largestRectangleArea();
        int[] ints = {2,4};
        int i = largestRectangleArea.largestRectangleArea(ints);
        System.out.println(i);
    }


}

class largestRectangleArea {

    //给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
    //
    //求在该柱状图中，能够勾勒出来的矩形的最大面积

    //输入：heights = [2,1,5,6,2,3]
    //输出：10
    //解释：最大的矩形为图中红色区域，面积为 10
    public int largestRectangleArea(int[] heights) {

        int length = heights.length;
        int[][] ints = new int[length][length];


        for (int i = 0; i < length; i++) {
            for (int j = i; j < length; j++) {
                if (i == j) {
                    ints[i][j] = heights[i];
                    continue;
                }
                ints[i][j] = Math.min(ints[i][j - 1], heights[j]);
            }
        }

        int max = 0;
        for (int i = 0; i < length; i++) {
            for (int j = i; j < length; j++) {
                max = Math.max(ints[i][j] * (j - i + 1), max);
            }
        }

        return max;
    }
}

//给你一个整数 n ，请你找出并返回第 n 个 丑数 。
//
//丑数 就是质因子只包含 2、3 和 5 的正整数。



