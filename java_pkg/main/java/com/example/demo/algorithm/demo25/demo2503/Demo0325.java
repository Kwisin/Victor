package com.example.demo.algorithm.demo25.demo2503;


public class Demo0325 {

    public static void main(String[] args) {
        int i = new maxProfit1().maxProfit(new int[]{1});
        System.out.println();
    }

}

// 309. 买卖股票的最佳时机含冷冻期
/*
给定一个整数数组prices，其中第  prices[i] 表示第 i 天的股票价格 。

设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:

卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。
注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票

示例 1:

输入: prices = [1,2,3,0,2]
输出: 3
解释: 对应的交易状态为: [买入, 卖出, 冷冻期, 买入, 卖出]
示例 2:

输入: prices = [1]
输出: 0
不持有股票 [[0,0], 1, 2, 2,]
持有股票   [[-1,-1,-1,2,]
 */
class maxProfit1 {

    /*
    public int maxProfit(int[] prices) {
        if (prices.length == 0) {
            return 0;
        }

        int n = prices.length;
        // f[i][0]: 手上持有股票的最大收益
        // f[i][1]: 手上不持有股票，并且处于冷冻期中的累计最大收益
        // f[i][2]: 手上不持有股票，并且不在冷冻期中的累计最大收益
        int[][] f = new int[n][3];
        f[0][0] = -prices[0];
        for (int i = 1; i < n; ++i) {
            f[i][0] = Math.max(f[i - 1][0], f[i - 1][2] - prices[i]);
            f[i][1] = f[i - 1][0] + prices[i];
            f[i][2] = Math.max(f[i - 1][1], f[i - 1][2]);
        }
        return Math.max(f[n - 1][1], f[n - 1][2]);
    }
     */

    int maxPrice = 0;

    public int maxProfit(int[] prices) {
//        int length = prices.length;
//        int[][] dp0 = new int[length][2]; // 不持有股票
//        int[][] dp1 = new int[length][2]; // 持有股票
//        dp0[0] = new int[]{0, 0};
//        dp1[0] = new int[]{0, -prices[0]};
//        for (int i = 1; i < length; i++) {
//            dp0[i] = (dp1[i - 1][1] + prices[i]) > dp0[i - 1][1] ?
//                    new int[]{1, dp1[i - 1][1] + prices[i]} : new int[]{0, dp0[i - 1][1]};
//            if (dp0[i - 1][0] == 1) {
//                dp1[i] = dp1[i - 1];
//            } else {
//                dp1[i] = (dp0[i - 1][1] - prices[i]) > dp1[i - 1][1] ?
//                        new int[]{0, dp0[i - 1][1] - prices[i]} : new int[]{0, dp1[i - 1][1]};
//            }
//        }
        for (int i = 0; i < prices.length; i++) {
            dfs(prices, i + 1, 0, false, false);
            dfs(prices, i + 1, -prices[i], false, true);
        }

        return this.maxPrice;
    }

    public void dfs(int[] prices, int curr, int price, boolean sale, boolean hold) {
        if (curr == prices.length) {
            this.maxPrice = Math.max(this.maxPrice, price);
        }

        for (int i = curr; i < prices.length; i++) {
            if (hold) {
                dfs(prices, curr + 1, price + prices[curr], true, false);
                dfs(prices, curr + 1, price, false, true);
            } else {
                if (!sale) {
                    dfs(prices, curr + 1, price - prices[curr], false, true);
                }
                dfs(prices, curr + 1, price, false, false);
            }
        }
    }
}

