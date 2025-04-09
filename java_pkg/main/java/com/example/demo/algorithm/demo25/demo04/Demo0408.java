package com.example.demo.algorithm.demo25.demo04;


import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public class Demo0408 {
    public String name;
    public int age;

    public Demo0408(String name, int age) {
        this.name = name;
        this.age = age;
        Semaphore semaphore = new Semaphore(11);
        CyclicBarrier cyclicBarrier = new CyclicBarrier(10);
        CountDownLatch countDownLatch = new CountDownLatch(10);

    }


    public static void main(String[] args) {
        int i = new calculateMinimumHP().calculateMinimumHP(new int[][]{
                {-2,-3,3}, {-5,-10,1}, {10,30,-5}});
        System.out.println();

    }
}

//174. 地下城游戏
/*
恶魔们抓住了公主并将她关在了地下城 dungeon 的 右下角 。
地下城是由 m x n 个房间组成的二维网格。我们英勇的骑士最初被安置在 左上角 的房间里，他必须穿过地下城并通过对抗恶魔来拯救公主。
骑士的初始健康点数为一个正整数。如果他的健康点数在某一时刻降至 0 或以下，他会立即死亡。
有些房间由恶魔守卫，因此骑士在进入这些房间时会失去健康点数（若房间里的值为负整数，则表示骑士将损失健康点数）；
其他房间要么是空的（房间里的值为 0），要么包含增加骑士健康点数的魔法球（若房间里的值为正整数，则表示骑士将增加健康点数）。
为了尽快解救公主，骑士决定每次只 向右 或 向下 移动一步。
返回确保骑士能够拯救到公主所需的最低初始健康点数。
注意：任何房间都可能对骑士的健康点数造成威胁，也可能增加骑士的健康点数，包括骑士进入的左上角房间以及公主被监禁的右下角房间。

输入：dungeon = [
[-2,-3,3],
[-5,-4,-10],
[-2,30,-5]

[-2,-3,3],
[-5,-10,1],
[10,30,-5]

[-2,-3,3],
[-5,10,[16,6]],
[10,[1,31],[6,1]]


[[3,1],[6,1],[6,4]]
[[8,1],[6,11],[6,1]]
[[8,18],[8,48],[8,43]]


]
输出：7
解释：如果骑士遵循最佳路径：右 -> 右 -> 下 -> 下 ，则骑士的初始健康点数至少为 7 。

输入：dungeon = [[0]]
输出：1


[1,-3,3],
[0,-2,0],
[-3,-3,-3]



[1,2],[3,1],[3,4]
[1,2],[2,1],[3,4]
[3,1],[1,2],[3,1]

[3,4],[4,1],[1,4]
[6,6],[6,4],[4,4]
[10,7],[7,4],[4,1]
*/
class calculateMinimumHP {
//    public int calculateMinimumHP(int[][] dungeon) {
//        int row = dungeon.length;
//        int col = dungeon[0].length;
//        int[][][] ints = new int[row][col][2];
//        int temp = dungeon[0][0] >= 0 ? 1 : -dungeon[0][0] + 1;
//        ints[0][0] = new int[]{temp, temp + dungeon[0][0]};
//        for (int j = 1; j < col; j++) {
//            ints[0][j] = checkEnough(ints[0][j - 1], dungeon[0][j]);
//        }
//        for (int i = 1; i < row; i++) {
//            ints[i][0] = checkEnough(ints[i - 1][0], dungeon[i][0]);
//        }
//        for (int i = 1; i < row; i++) {
//            for (int j = 1; j < col; j++) {
//                int[] ints1 = checkEnough(ints[i - 1][j], dungeon[i][j]);
//                int[] ints2 = checkEnough(ints[i][j - 1], dungeon[i][j]);
//                if (ints1[0] == ints2[0]) {
//                    ints[i][j] = ints1[1] > ints2[1] ? ints1 : ints2;
//                } else {
//                    ints[i][j] = ints1[0] < ints2[0] ? ints1 : ints2;
//                }
//            }
//        }
//
//        return ints[row - 1][col - 1][0];
//    }
// 1,2    -3     1+x -3 = 1
//    public int[] checkEnough(int[] before, int newItem) {
//        int[] temp = new int[2];
//        if (before[1] + newItem > 0) {
//            temp[0] = before[0];
//            temp[1] = before[1] + newItem;
//            return temp;
//        }
//        // 2,1   5,4   -3   5,1
//        temp[0] = before[0] + 1 - before[1] - newItem;
//        temp[1] = 1;
//        return temp;
//    }

    public int calculateMinimumHP(int[][] dungeon) {
        int row = dungeon.length;
        int col = dungeon[0].length;
        int[][][] ints = new int[row][col][2];
        int temp = dungeon[row - 1][col - 1] >= 0 ? 1 : -dungeon[row - 1][col - 1] + 1;
        ints[row - 1][col - 1] = new int[]{temp, 1};
        for (int j = col - 2; j >= 0; j--) {
            ints[row - 1][j] = checkEnough(ints[row - 1][j + 1], dungeon[row - 1][j]);
        }
        for (int i = row - 2; i >= 0; i--) {
            ints[i][col - 1] = checkEnough(ints[i + 1][col - 1], dungeon[i][col - 1]);
        }
        for (int i = row - 2; i >= 0; i--) {
            for (int j = col - 2; j >= 0; j--) {
                int[] ints1 = checkEnough(ints[i + 1][j], dungeon[i][j]);
                int[] ints2 = checkEnough(ints[i][j + 1], dungeon[i][j]);
                ints[i][j] = ints1[0] < ints2[0] ? ints1 : ints2;
            }
        }

        return ints[0][0][0];
    }

    // 4,1  -3 ,0,1
    public int[] checkEnough(int[] before, int newItem) {
        int[] temp = new int[2];

        if (before[0] > newItem) {
            temp[0] = before[0] - newItem;
            temp[1] = before[0];
        } else {
            temp[0] = 1;
            temp[1] = 1 + newItem;
        }
        return temp;
    }
}

//