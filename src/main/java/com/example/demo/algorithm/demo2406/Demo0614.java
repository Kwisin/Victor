package com.example.demo.algorithm.demo2406;

import java.util.Hashtable;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;

public class Demo0614 {

    private static boolean flag = true;

    public static void main(String[] args) {

        Hashtable<String, String> stringStringHashtable = new Hashtable<>();

        ConcurrentHashMap<String, Integer> stringIntegerConcurrentHashMap = new ConcurrentHashMap<>();

        Executors.newFixedThreadPool(10);
//        new ThreadPoolExecutor()


        Thread thread = new Thread(() -> {
            flag = false;
            System.out.println("");
        });

//        thread.setPriority();

        thread.run();
        thread.start();

//        new Thread()

        Callable<String> stringThrowableCheckedCallable = () -> {
            return "";
        };

//        stringThrowableCheckedCallable.


//        Thread thread1 = new Thread("");


//        thread.isInterrupted()

    }


}
