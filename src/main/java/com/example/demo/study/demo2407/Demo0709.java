package com.example.demo.study.demo2407;


import java.util.HashSet;
import java.util.LinkedList;
import java.util.Stack;


public class Demo0709 {

    //581 325 315    106 160 3796
    public static void main(String[] args) {
    }


}


// 202 AC
//快乐数」 定义为：
//对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和。
//然后重复这个过程直到这个数变为 1，也可能是 无限循环 但始终变不到 1。
//如果这个过程 结果为 1，那么这个数就是快乐数。
//如果 n 是 快乐数 就返回 true ；不是，则返回 false 。
class isHappy {
    public static boolean isHappy(int n) {
        String s = String.valueOf(n);
        HashSet<Integer> integers = new HashSet<>();
        while (true) {
            int value = getValue(n);
            if (value == 1) {
                return true;
            }
            if (integers.contains(value)) {
                return false;
            }
            integers.add(value);
            n = value;
        }
    }

    public static int getValue(int n) {
        String s = String.valueOf(n);
        int result = 0;

        for (char c : s.toCharArray()) {
            result += Integer.parseInt(String.valueOf(c)) * Integer.parseInt(String.valueOf(c));
        }

        return result;
    }
}


/*

 设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。
    实现 MinStack 类:
    MinStack() 初始化堆栈对象。
    void push(int val) 将元素val推入堆栈。
    void pop() 删除堆栈顶部的元素。
    int top() 获取堆栈顶部的元素。
    int getMin() 获取堆栈中的最小元素。

["MinStack","push","push","push","push","pop","getMin","pop","getMin","pop","getMin"]
[[],[512],[-1024],[-1024],[512],[],[],[],[],[],[]]*/
//155  最小栈  todo check
class MinStack {

    private LinkedList<Integer> dataStack;
    private LinkedList<Integer> minStack;
    private int min;

    public MinStack() {
        dataStack = new LinkedList<>();
        minStack = new LinkedList<>();
        min = Integer.MAX_VALUE;
    }

    public void push(int val) {
        dataStack.addLast(val);
        minStack.add(Math.min(val, min));
    }

    public void pop() {
        dataStack.pop();
        minStack.pop();
    }

    public int top() {
        return dataStack.getLast();
    }

    public int getMin() {
        return minStack.getLast();
    }
}
