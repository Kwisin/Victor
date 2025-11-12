package com.example.demo.algorithm.year25.month01;


import java.util.*;


public class Demo0126 {

    public static void main(String[] args) {
        MedianFinder medianFinder = new MedianFinder();
        medianFinder.addNum(-1);
        medianFinder.addNum(-2);
        medianFinder.addNum(-3);
        medianFinder.addNum(-4);
        medianFinder.addNum(-5);
        double median = medianFinder.findMedian();
        System.out.println();
    }


}

// 295
class MedianFinder {

    private PriorityQueue<Integer> sortArray;

    public MedianFinder() {
        this.sortArray = new PriorityQueue<>();
    }

    public void addNum(int num) {
        this.sortArray.add(num);
    }

    public double findMedian() {
        int size = this.sortArray.size();
        if (size % 2 == 0) {

            // 2, 0,1   2/2,   2/2-1    4   1,2   4/2  4/2-1
            int i = size / 2;
            int i1 = i - 1;
            return (getItem(i) + getItem(i1)) / 2.0;
        }

        // 3,1   7,3
        return getItem((size - 1) / 2);
    }


    public Integer getItem(int i) {
        int index = 0;
        for (Integer item : this.sortArray) {
            if (index == i) {
                return item;
            }
            index++;
        }
        return 0;
    }
}
