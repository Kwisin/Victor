package com.example.demo.study;

import java.util.*;
import java.util.stream.Collectors;

public class Demo0711 {

    public static void main(String[] args) {


        MaximizedCapital maximizedCapital = new MaximizedCapital();

        System.out.println(maximizedCapital.findMaximizedCapital(3, 0, new int[]{1, 2, 3, 4}, new int[]{0, 1, 2, 0}));

    }

}


class MaximizedCapital {
    //    假设 力扣（LeetCode）即将开始 IPO 。为了以更高的价格将股票卖给风险投资公司，力扣 希望在 IPO 之前开展一些项目以增加其资本。
    //    由于资源有限，它只能在 IPO 之前完成最多 k 个不同的项目。帮助 力扣 设计完成最多 k 个不同项目后得到最大总资本的方式。
    //    给你 n 个项目。对于每个项目 i ，它都有一个纯利润 profits[i] ，和启动该项目需要的最小资本 capital[i] 。
    //    最初，你的资本为 w 。当你完成一个项目时，你将获得纯利润，且利润将被添加到你的总资本中。
    //    总而言之，从给定项目中选择 最多 k 个不同项目的列表，以 最大化最终资本 ，并输出最终可获得的最多资本。
    //    答案保证在 32 位有符号整数范围内。

    //    输入：k = 2, w = 0, profits = [1,2,3], capital = [0,1,1]
    //输出：4

    //    输入：k = 4, w = 3, profits = [5,8,1,3,7], capital = [0,3,1,5,6]
    //输出：4

    //输入：k = 3, w = 0, profits = [1,2,3], capital = [0,1,2]
    //输出：6
    public int findMaximizedCapital1(int k, int w, int[] profits, int[] capital) {
        int result = w;
        HashSet<Integer> integerHashSet = new HashSet<>();
        for (int i = 0; i < k; i++) {
            int index = -1;
            int maxProfit = Integer.MIN_VALUE;
            for (int j = 0; j < capital.length; j++) {
                if (integerHashSet.contains(j) || capital[j] > result) {
                    continue;
                }
                if (profits[j] >= maxProfit) {
                    index = j;
                    maxProfit = profits[j];
                }

            }

            if (index < 0) {
                return result;
            }

            integerHashSet.add(index);
            result += maxProfit;
        }
        return result;
    }

    public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        int n = profits.length;
        int curr = 0;
        int[][] arr = new int[n][2];

        for (int i = 0; i < n; ++i) {
            arr[i][0] = capital[i];
            arr[i][1] = profits[i];
        }
        List<Integer> collect = Arrays.stream(profits).boxed().collect(Collectors.toList());
        Arrays.sort(arr, Comparator.comparingInt(a -> a[0]));
        PriorityQueue<Integer> pq = new PriorityQueue<>((x, y) -> y - x);
        for (int i = 0; i < k; ++i) {
            while (curr < n && arr[curr][0] <= w) {
                pq.add(arr[curr][1]);
                curr++;
            }
            if (!pq.isEmpty()) {
                w += pq.poll();
            } else {
                break;
            }
        }

        return w;
    }

}


