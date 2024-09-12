package com.example.demo.study.demo2407;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

public class Demo0722 {

    public static void main(String[] args) {
        nthUglyNumber nthUglyNumber = new nthUglyNumber();
        int i = nthUglyNumber.nthUglyNumber(410);
        System.out.println();
    }

}


// todo
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
        int count = 1;
        ArrayList<Integer> integerArrayList = new ArrayList<>();
        integerArrayList.add(1);
        PriorityQueue<Integer> integers = new PriorityQueue<>((v1, v2) -> v2 - v1);
        integers.add(1);
        int res = 0;
        int[] base = new int[]{2, 3, 5};
        while (count < n) {
            for (Integer item : integerArrayList) {
                for (int v : base) {
                    integerArrayList.add(item * v);
                    integers.add(item * v);
                    count++;
                    if (count == n) {
                        res = integers.peek();
                    }
                }
            }
        }

        return res;
    }
}


