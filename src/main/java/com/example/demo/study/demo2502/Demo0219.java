package com.example.demo.study.demo2502;


public class Demo0219 {

    public static void main(String[] args) {
        int i = new computeArea().computeArea(-2, -2, 2, 2, -2, -2, 2, 2);
        System.out.println();
    }

}

// 223. 矩形面积
/*
给你 二维 平面上两个 由直线构成且边与坐标轴平行/垂直 的矩形，请你计算并返回两个矩形覆盖的总面积。
每个矩形由其 左下 顶点和 右上 顶点坐标表示：

第一个矩形由其左下顶点 (ax1, ay1) 和右上顶点 (ax2, ay2) 定义。
第二个矩形由其左下顶点 (bx1, by1) 和右上顶点 (bx2, by2) 定义。

输入：ax1 = -3, ay1 = 0, ax2 = 3, ay2 = 4,    bx1 = 0, by1 = -1, bx2 = 9, by2 = 2
输出：45

0～3   0～2

24+27 -6

输入：ax1 = -2, ay1 = -2, ax2 = 2, ay2 = 2, bx1 = -2, by1 = -2, bx2 = 2, by2 = 2
输出：16
 */

class computeArea {
    public int computeArea(int ax1, int ay1, int ax2, int ay2, int bx1, int by1, int bx2, int by2) {
        int area1 = (ax2 - ax1) * (ay2 - ay1);
        int area2 = (bx2 - bx1) * (by2 - by1);

        // 求重叠面积
        int left = Math.max(ax1, bx1);
        int right = Math.min(ax2, bx2);
        int down = Math.max(ay1, by1);
        int up = Math.min(ay2, by2);
        if (down >= up || left >= right) {
            return area1 + area2;
        }
        return area1 + area2 - (right - left) * (up - down);
    }
}

// 204. 计数质数 todo
/*
给定整数 n ，返回 所有小于非负整数 n 的质数的数量 。

示例 1：
输入：n = 10
输出：4
解释：小于 10 的质数一共有 4 个, 它们是 2, 3, 5, 7 ,11 ,13,17,19, 23,29,31,37,41,43,47,  5*7 11*13。

示例 2：
输入：n = 0
输出：0

示例 3：
输入：n = 1
输出：0

0 <= n <= 5 * 10^6
10   4
20  8
30   10
40   12
50     15


 */
class countPrimes {
    public int countPrimes(int n) {
        return 0;
    }
}