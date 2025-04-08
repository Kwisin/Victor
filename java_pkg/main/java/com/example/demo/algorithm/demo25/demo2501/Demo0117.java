package com.example.demo.algorithm.demo25.demo2501;


import java.util.*;

public class Demo0117 {

    public static void main(String[] args) {
        int i = new maxSatisfaction().maxSatisfaction(new int[]{4,3,2});
        System.out.println();
    }


}

// 1402. 做菜顺序
/*
一个厨师收集了他 n 道菜的满意程度 satisfaction ，这个厨师做出每道菜的时间都是 1 单位时间。
一道菜的 「 like-time 系数 」定义为烹饪这道菜结束的时间（包含之前每道菜所花费的时间）乘以这道菜的满意程度，也就是 time[i]*satisfaction[i] 。
返回厨师在准备了一定数量的菜肴后可以获得的最大 like-time 系数 总和。
你可以按 任意 顺序安排做菜的顺序，你也可以选择放弃做某些菜来获得更大的总和。

示例 1：
输入：satisfaction = [-1,-8,0,5,-9]
[-9,-8,-1,0,5]


输出：14
解释：去掉第二道和最后一道菜，最大的 like-time 系数和为 (-1*1 + 0*2 + 5*3 = 14) 。每道菜都需要花费 1 单位时间完成。
示例 2：
输入：satisfaction = [4,3,2]
输出：20
解释：可以按照任意顺序做菜 (2*1 + 3*2 + 4*3 = 20)

示例 3：
输入：satisfaction = [-1,-4,-5]

输出：0
解释：大家都不喜欢这些菜，所以不做任何菜就可以获得最大的 like-time 系数
[-9,-8,-3,-1,0,5]  4   -16   -7,1,4,5,5,0
-3*1+-1*2*3*0+4*5 = 15
-1*1+0*2+5*3 = 14

//abcde  acde
[1 1 1 1]
[1 1 1 1]
[1 2     ]
[1   3   ]
[1      4]

 */

class maxSatisfaction {
    public int maxSatisfaction(int[] satisfaction) {
        int length = satisfaction.length;
        if (length == 0) {
            return 0;
        }
        Arrays.sort(satisfaction);
        if (satisfaction[length - 1] <= 0) {
            return 0;
        }

        int[] sum = new int[length];
        int total = 0;
        for (int item : satisfaction) {
            total += item;
        }
        sum[0] = total - satisfaction[0];
        for (int i = 1; i < length; i++) {
            sum[i] = sum[i - 1] - satisfaction[i];
        }

        int round = 1;
        int result = 0;
        for (int i = 0; i < length; i++) {
            if (satisfaction[i] > 0 || sum[i] + satisfaction[i] >= 0) {
                result += satisfaction[i] * round;
                round++;
            }
        }
        return result;
    }
}
