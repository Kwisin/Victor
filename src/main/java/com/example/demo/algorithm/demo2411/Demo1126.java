package com.example.demo.algorithm.demo2411;

public class Demo1126 {

    public static void main(String[] args) {
        int trap = new trap().trap(new int[]{4,2,0,3,2,5});
        System.out.println();

    }


}

//134
/*
在一条环路上有 n 个加油站，其中第 i 个加油站有汽油 gas[i] 升。
你有一辆油箱容量无限的的汽车，从第 i 个加油站开往第 i+1 个加油站需要消耗汽油 cost[i] 升。你从其中的一个加油站出发，开始时油箱为空。
给定两个整数数组 gas 和 cost ，如果你可以按顺序绕环路行驶一周，则返回出发时加油站的编号，否则返回 -1 。如果存在解，则 保证 它是 唯一 的。

示例 1:
输入: gas = [1,2,3,4,5], cost = [3,4,5,1,2]
输出: 3
解释:
从 3 号加油站(索引为 3 处)出发，可获得 4 升汽油。此时油箱有 = 0 + 4 = 4 升汽油
开往 4 号加油站，此时油箱有 4 - 1 + 5 = 8 升汽油
开往 0 号加油站，此时油箱有 8 - 2 + 1 = 7 升汽油
开往 1 号加油站，此时油箱有 7 - 3 + 2 = 6 升汽油
开往 2 号加油站，此时油箱有 6 - 4 + 3 = 5 升汽油
开往 3 号加油站，你需要消耗 5 升汽油，正好足够你返回到 3 号加油站。
因此，3 可为起始索引。
示例 2:

输入: gas = [2,3,4], cost = [3,4,3]
输出: -1
解释:
你不能从 0 号或 1 号加油站出发，因为没有足够的汽油可以让你行驶到下一个加油站。
我们从 2 号加油站出发，可以获得 4 升汽油。 此时油箱有 = 0 + 4 = 4 升汽油
开往 0 号加油站，此时油箱有 4 - 3 + 2 = 3 升汽油
开往 1 号加油站，此时油箱有 3 - 3 + 3 = 3 升汽油
你无法返回 2 号加油站，因为返程需要消耗 4 升汽油，但是你的油箱只有 3 升汽油。
因此，无论怎样，你都不可能绕环路行驶一周。
 */

/*
gas 求和 如果小于 cost的和，那肯定不能转一圈
如果gas[i] < cost[i]，那么i肯定也不是起点

 */
class canCompleteCircuit {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int length = gas.length;
        for (int i = 0; i < length; ) {
            int curPos = i;
            int curGas = gas[i];
            while (true) {
                if (curGas < cost[curPos]) {
                    // 不能回到i的后面，已经检查过了
                    if (curPos < i) {
                        return -1;
                    }
                    i = curPos + 1;
                    break;
                }
                curGas = curGas - cost[curPos] + gas[(curPos + 1) % length];
                curPos = (curPos + 1) % length;
                if (curPos == i) {
                    return i;
                }
            }
        }

        return -1;
    }
}


//135
/*
n 个孩子站成一排。给你一个整数数组 ratings 表示每个孩子的评分。
你需要按照以下要求，给这些孩子分发糖果：
每个孩子至少分配到 1 个糖果。
相邻两个孩子评分更高的孩子会获得更多的糖果。
请你给每个孩子分发糖果，计算并返回需要准备的 最少糖果数目 。

示例 1：
输入：ratings = [1,0,2]
输出：5
解释：你可以分别给第一个、第二个、第三个孩子分发 2、1、2 颗糖果。
示例 2：
输入：ratings = [1,2,2]
输出：4
解释：你可以分别给第一个、第二个、第三个孩子分发 1、2、1 颗糖果。
     第三个孩子只得到 1 颗糖果，这满足题面中的两个条件。

     [1,0,2]
     [2,1,2]

     [1,2,2]
     [1,2,1]


   ratings[i-1] >= ratings[i]<= ratings[i+1]  ratings[i] = 1;
   ratings[i-1] < ratings[i] < ratings[i+1]  ratings[i] = ratings[i-1]+1
 */

/*
统计各个分数的人的位置，从小往大依次填值
 */
class candy {
    public int candy(int[] ratings) {
        int sum = 0;
        int length = ratings.length;
        int[] result = new int[length];
        for (int i = 0; i < length; i++) {
            result[i] = 1;
        }

        //左规则
        for (int i = 0; i < length; i++) {
            if (i - 1 < 0) {
                continue;
            }
            result[i] = ratings[i] > ratings[i - 1] ? result[i - 1] + 1 : result[i];
        }

        //右规则
        for (int i = length - 1; i >= 0; i--) {
            if (i + 1 >= length) {
                sum += result[i];
                continue;
            }
            result[i] = ratings[i] > ratings[i + 1] ? result[i + 1] + 1 : result[i];
            sum += result[i];
        }

        return sum;

    }
}

//42
/*
给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。

输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
输出：6
解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。

示例 2：
输入：height = [4,2,0,3,2,5]
输出：9
 */

class trap {
    public int trap(int[] height) {
        int length = height.length;
        int[] leftHeight = new int[length];
        leftHeight[0] = height[0];
        int[] rightHeight = new int[length];
        rightHeight[length - 1] = height[length - 1];
        int sum = 0;

        //左规则
        for (int i = 1; i < length; i++) {
            leftHeight[i] = Math.max(height[i], leftHeight[i - 1]);
        }

        //右规则
        for (int i = length - 2; i >= 0; i--) {
            rightHeight[i] = Math.max(height[i], rightHeight[i + 1]);
        }

        for (int i = 1; i < length - 1; i++) {
            sum += Math.min(leftHeight[i], rightHeight[i]) - height[i];
        }


        return sum;

    }
}