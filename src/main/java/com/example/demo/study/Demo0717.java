package com.example.demo.study;


import java.util.*;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

public class Demo0717 {
    private volatile int val = 0;
    private volatile int val1 = 0;

    public void addVal() {
        synchronized (this) {
            val++;
        }
        try {
            Thread.sleep(5);
        } catch (InterruptedException e) {

        }
        val1++;
    }

    public static void main(String[] args) {
        topKFrequent topKFrequent = new topKFrequent();
        int[] ints = {1, 3, 1, 1, 2, 5, 6, 5, 6, 7};
        int[] ints1 = topKFrequent.topKFrequent(ints, 3);
        System.out.println();
    }

    private void testThread() {
        Demo0717 demo0717 = new Demo0717();

        Runnable runnable2 = () -> {

            for (int i = 0; i < 1000; i++) {
                demo0717.addVal();
            }

        };

        Thread t1 = new Thread(runnable2, "t1");
        Thread t2 = new Thread(runnable2, "t2");

        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (Exception e) {

        }
        System.out.println(demo0717.val);
        System.out.println(demo0717.val1);


        ReentrantLock reentrantLock = new ReentrantLock();


        LockSupport.park();
    }


}


class topKFrequent {

    //给你一个整数数组 nums 和一个整数 k ，请你返回其中出现频率前 k 高的元素。你可以按 任意顺序 返回答案。
    public int[] topKFrequent(int[] nums, int k) {

        HashMap<Integer, Integer> integerIntegerHashMap = new HashMap<>();
        for (int item : nums) {
            integerIntegerHashMap.put(item, integerIntegerHashMap.getOrDefault(item, 0) + 1);
        }

        ArrayList<temp> temps = new ArrayList<>();
        integerIntegerHashMap.forEach((key, val) -> {
            temps.add(new temp(key, val));
        });


        PriorityQueue<int[]> queue = new PriorityQueue<int[]>(new Comparator<int[]>() {
            public int compare(int[] m, int[] n) {
                return m[1] - n[1];
            }
        });


        Collections.sort(temps, (t1, t2) -> t2.count - t1.count);

        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = temps.get(i).val;
        }

        return res;
    }
}


class temp {
    int val;
    int count;

    public temp(int val, int count) {
        this.val = val;
        this.count = count;
    }
}

