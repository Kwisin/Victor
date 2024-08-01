package com.example.demo.study;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Demo0708 {

    public static void main(String[] args) {

//        输入：s = "the sky is blue"
//        输出："blue is sky the"

        TestThread();

    }

    public static String getReverseString(String s) {
        String[] split = s.split("\\s+");
        String result = "";
        int end = 0;
        if (split.length >= 1 && split[0] == "") {
            end = 1;
        }
        for (int i = split.length - 1; i >= end; i--) {
            if (i == end) {
                result += split[i];
            } else {
                result += split[i] + " ";
            }
        }

        List<String> strings = Arrays.asList(s.split("\\s+"));
        Collections.reverse(strings);
        strings.forEach(item -> {
            System.out.println(item);
        });
        for (String t : strings) {
            System.out.println(t);
        }

        return result;
    }


    public static void TestThread () {
        AtomicInteger tempInt = new AtomicInteger();

        Thread thread = new Thread(() -> {
            tempInt.getAndIncrement();
        },"t1");

        Thread thread2 = new Thread(() -> {
            tempInt.getAndIncrement();
        },"t2");

        thread.start();
        thread2.start();


        System.out.println(tempInt);


    }

}
