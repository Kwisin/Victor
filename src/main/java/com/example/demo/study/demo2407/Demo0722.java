package com.example.demo.study.demo2407;

import java.util.HashMap;

public class Demo0722 {

    public static void main(String[] args) {
        nthUglyNumber nthUglyNumber = new nthUglyNumber();
        int i = nthUglyNumber.nthUglyNumber(410);
        System.out.println();
    }

}


class nthUglyNumber {
    //给你一个整数 n ，请你找出并返回第 n 个 丑数 。
    //
    //丑数 就是质因子只包含 2、3 和 5 的正整数。
//    25
//        77?
//
//        1,2,3,5,7,11,13

    //输入：n = 10
    //输出：12
    //解释：[1, 2, 3, 4, 5, 6, 8, 9, 10, 12] 是由前 10 个丑数组成的序列。
    public int nthUglyNumber(int n) {
        if (n <= 5) {
            return n;
        }

        HashMap<Integer, Integer> integerIntegerHashMap = new HashMap<>();
        integerIntegerHashMap.put(1, 1);
        integerIntegerHashMap.put(2, 1);
        integerIntegerHashMap.put(3, 1);
        integerIntegerHashMap.put(4, 1);
        integerIntegerHashMap.put(5, 1);


        int current = 5;
        int res = 0;
        for (int i = 6; current <= n; i++) {
            if (i % 7 == 0) {
                integerIntegerHashMap.put(i, 0);
            }
            if ((i % 2 == 0 && integerIntegerHashMap.get(i / 2) == 1) || (i % 3 == 0 && integerIntegerHashMap.get(i / 3) == 1) || (i % 5 == 0 && integerIntegerHashMap.get(i / 5) == 1)) {
                integerIntegerHashMap.put(i, 1);
                current++;
            } else {
                integerIntegerHashMap.put(i, 0);
            }

            if (current == n) {
                res = i;
                break;
            }
        }
        return res;

    }
}


