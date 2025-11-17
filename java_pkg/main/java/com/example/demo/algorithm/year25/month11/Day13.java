package com.example.demo.algorithm.year25.month11;

import java.util.Arrays;

public class Day13 {
    public static void main(String[] args) {


    }

    /*  319
    初始时有 n 个灯泡处于关闭状态。第一轮，你将会打开所有灯泡。接下来的第二轮，你将会每两个灯泡关闭第二个。
    第三轮，你每三个灯泡就切换第三个灯泡的开关（即，打开变关闭，关闭变打开）。第 i 轮，你每 i 个灯泡就切换第 i 个灯泡的开关。直到第 n 轮，你只需要切换最后一个灯泡的开关。
    找出并返回 n 轮后有多少个亮着的灯泡。


    10
    第二轮：10/2 = 5，会操作五个开关
    第三轮：10/3 = 3，会操作三个开关
    第四轮：10/4 = 2，会操作两个开关
    第五轮：10/5 = 2，还是会操作五个开关
    [1,1,1,1,1,1,1,1,1,1] 10个
    [1,0,1,0,1,0,1,0,1,0] 5个
    [1,0,0,0,1,1,1,0,0,0] 5-1+1-1个 = 4

    示例 1：输入：n = 3
    输出：1
    解释：
    初始时, 灯泡状态 [关闭, 关闭, 关闭].
    第一轮后, 灯泡状态 [开启, 开启, 开启].
    第二轮后, 灯泡状态 [开启, 关闭, 开启].
    第三轮后, 灯泡状态 [开启, 关闭, 关闭].
    你应该返回 1，因为只有一个灯泡还亮着。
    示例 2：
    输入：n = 0
    输出：0
    示例 3：
    输入：n = 1
    输出：1

    提示：
    0 <= n <= 10^9

    约瑟夫环：模拟O(n²) → 递推公式O(n) → 直接公式O(1)
    楼梯问题：递归O(2ⁿ) → DP O(n) → 斐波那契公式O(log n)
    组合数计算：阶乘O(n) → 杨辉三角O(n²) → 卢卡斯定理O(log n)
     */

    public int bulbSwitch(int n) {
        // 初始化灯的状态和亮灯数
        int[] ints = new int[n];
        Arrays.fill(ints, 1);
        int curr = n;

        for (int i = 2; i <= n; i++) {
            int checkIndex = i - 1;
            while (checkIndex < n) {
                if (ints[checkIndex] == 1) {
                    //关灯
                    ints[checkIndex] = 0;
                    curr--;
                } else {
                    //开灯
                    ints[checkIndex] = 1;
                    curr++;
                }
                checkIndex += i;
            }

        }
        return curr;
    }

    /*  335
    给你一个整数数组 distance 。
    从 X-Y 平面上的点 (0,0) 开始，先向北移动 distance[0] 米，然后向西移动 distance[1] 米，向南移动 distance[2] 米，
    向东移动 distance[3] 米，持续移动。也就是说，每次移动后你的方位会发生逆时针变化。
    判断你所经过的路径是否相交。如果相交，返回 true ；否则，返回 false 。

    输入：distance = [2,1,1,2]
    输出：true
    输入：distance = [1,2,3,4]
    输出：false
    输入：distance = [1,1,1,1]
    输出：true

    提示：
    1 <= distance.length <= 10^5
    1 <= distance[i] <= 10^5
     */

    public boolean isSelfCrossing(int[] distance) {
        int length = distance.length;
        if (length < 4) {
            return false;
        }
        /*
        len(n)>= len(n-2)  &&  len(n-1)<=len(n-3)
        len(n)>=len(n-2)-len(n-4) && len(n-1)>=len(n-3) - len(n-5)
        len(n)>= len(n-2)-len(n-4). && len(n-1)=len(n-3)
         */
        for (int curr = 3; curr < length; curr++) {
            if (distance[curr] >= distance[curr - 2] && distance[curr - 1] <= distance[curr - 3]) {
                return true;
            }
            if (curr >= 5 && distance[curr] >= (distance[curr - 2] - distance[curr - 4]) &&
                    distance[curr - 1] >= (distance[curr - 3] - distance[curr - 5])) {
                return true;
            }
            if (curr >= 4 && distance[curr]>=distance[curr-2]-distance[curr-4] && distance[curr-1]==distance[curr-3]){
                return true;
            }

        }
        return false;
    }
}

