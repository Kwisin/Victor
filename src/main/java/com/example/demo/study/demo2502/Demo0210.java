package com.example.demo.study.demo2502;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Demo0210 {

    public static void main(String[] args) {
        double v = new myPow().myPow(2, 300000000);
        System.out.println();
    }


}

// 913. 猫和老鼠
/*
两位玩家分别扮演猫和老鼠，在一张 无向 图上进行游戏，两人轮流行动。
图的形式是：graph[a] 是一个列表，由满足 ab 是图中的一条边的所有节点 b 组成。
老鼠从节点 1 开始，第一个出发；猫从节点 2 开始，第二个出发。在节点 0 处有一个洞。
在每个玩家的行动中，他们 必须 沿着图中与所在当前位置连通的一条边移动。例如，如果老鼠在节点 1 ，那么它必须移动到 graph[1] 中的任一节点。
此外，猫无法移动到洞中（节点 0）。
然后，游戏在出现以下三种情形之一时结束：
如果猫和老鼠出现在同一个节点，猫获胜。
如果老鼠到达洞中，老鼠获胜。
如果某一位置重复出现（即，玩家的位置和移动顺序都与上一次行动相同），游戏平局。
给你一张图 graph ，并假设两位玩家都都以最佳状态参与游戏：
如果老鼠获胜，则返回 1；
如果猫获胜，则返回 2；
如果平局，则返回 0 。

输入：graph = [[2,5],[3],[0,4,5],[1,4,5],[2,3],[0,2,3]]
输出：0
1
3
1,4,5
只要有一条路径能避开猫咪到0号位置就是老鼠赢
如果被逼到角落，走一步就会落到猫咪爪下
                                                   1，2
                            3，4                                         3，5
  1，2    4，2    5，2    1，3    4，3    5，3      1，2    4，2    5，2


输入：graph = [[1,3],[0],[3],[0,2]]
输出：1
 */
class catMouseGame {
    public int catMouseGame(int[][] graph) {
        return 0;
    }
}

// 50
/*
实现 pow(x, n) ，即计算 x 的整数 n 次幂函数（即，xn ）

示例 1：
输入：x = 2.00000, n = 10
输出：1024.00000
示例 2：
输入：x = 2.10000, n = 3
输出：9.26100
示例 3：
输入：x = 2.00000, n = -2
输出：0.25000
解释：2-2 = 1/22 = 1/4 = 0.25

10000
5000
2500
1250
625
624+1
312
156
78
39
38+1
19
18+1
9
8+1
4
2
1

100000000

1,2
2,3
3,5
5,8
8,13
13,21

 */
class myPow {
    public double myPow(double x, int n) {
        if (n == 0) {
            return 1;
        }
        if (n == 1) {
            return x;
        }
        if (n == 2) {
            return x * x;
        }
        boolean flag = false;
        double result;
        long newN = n;
        if (newN < 0) {
            flag = true;
            newN = -newN;
        }
        result = myLongPow(x, newN);
        if (flag) {
            return 1 / result;
        }
        return result;
    }


    public double myLongPow(double x, long n) {
        if (n == 0) {
            return 1;
        }
        if (n == 1) {
            return x;
        }
        if (n == 2) {
            return x * x;
        }
        double result;
        if (n % 2 == 0) {
            double v = myLongPow(x, n / 2);
            result = v * v;
        } else {
            result = myLongPow(x, n - 1) * x;
        }
        return result;
    }
}

// 72
/*

 */

class minDistance {
    public int minDistance(String word1, String word2) {
        return 0;

    }
}