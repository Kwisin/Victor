package com.example.demo.study.demo2407;


import java.util.HashSet;
import java.util.LinkedList;


public class Demo0709 {

    private volatile int counter = 0;

    public synchronized void increment() {
        counter++;
    }
//581 325 315    106 160 3796
    public static void main(String[] args) {
        Demo0709 example = new Demo0709();

        Runnable task = () -> {
            long start = System.nanoTime();
            for (int i = 0; i < 10000000; i++) {
                example.increment();
            }
            System.out.println(System.nanoTime() - start);
        };

        Thread thread1 = new Thread(task);
        Thread thread2 = new Thread(task);

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }



        System.out.println("Final counter value: " + example.counter);
    }

    public static boolean isHappy(int n) {

        //        快乐数」 定义为：
//
//        对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和。
//        然后重复这个过程直到这个数变为 1，也可能是 无限循环 但始终变不到 1。
//        如果这个过程 结果为 1，那么这个数就是快乐数。
//        如果 n 是 快乐数 就返回 true ；不是，则返回 false 。


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


class MinStack {
//        设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。
//
//        实现 MinStack 类:
//
//        MinStack() 初始化堆栈对象。
//        void push(int val) 将元素val推入堆栈。
//        void pop() 删除堆栈顶部的元素。
//        int top() 获取堆栈顶部的元素。
//        int getMin() 获取堆栈中的最小元素。

//    ["MinStack","push","push","push","push","pop","getMin","pop","getMin","pop","getMin"]
//    [[],[512],[-1024],[-1024],[512],[],[],[],[],[],[]]

    public Integer minValue = Integer.MAX_VALUE;
    public LinkedList<Integer> rawLinkList1 = new LinkedList<>();

    public MinStack() {

    }

    public void push(int val) {
        rawLinkList1.addFirst(val);

        if (val < minValue) {
            minValue = val;
        }

    }

    public void pop() {
        Integer integer = rawLinkList1.removeFirst();

        if (integer.equals(minValue)) {
            minValue = Integer.MAX_VALUE;
            for (int i = 0; i < rawLinkList1.size(); i++) {
                if (rawLinkList1.get(i) < minValue) {
                    minValue = rawLinkList1.get(i);
                }
            }
        }

    }

    public int top() {
        return rawLinkList1.getFirst();
    }

    public int getMin() {
        return minValue;
    }
}
