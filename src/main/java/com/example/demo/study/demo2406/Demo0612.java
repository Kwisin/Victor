package com.example.demo.study.demo2406;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.function.Function;

public class Demo0612 {

    public static void main(String[] args) {

        Function<Float, Integer> before = (f) -> f.intValue();

        Function<Integer, String> f = (integer) -> String.valueOf(integer + 1);

        Function<String, String> after = (s) -> s + 1;

        Function<Float, String> floatStringFunction = f.compose(before).andThen(after);
        String apply = floatStringFunction.apply(Float.valueOf("2.0"));
        System.out.println(apply);

        ArrayList<Integer> integerArrayList = new ArrayList<>();

        try {
            FileInputStream fileInputStream = new FileInputStream("");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        String[] array = {"apple", "banana", "cherry"};

        integerArrayList.forEach((item) -> {

        });
        LinkedList<String> stringLinkedList = new LinkedList<>();

        HashMap<String, String> stringHashMap = new HashMap<>();
        HashSet<String> stringHashSet = new HashSet<>();

        Vector<Integer> integerVector = new Vector<>();

        Vector<Integer> integers = new Vector<>(integerArrayList);


        Stack<Integer> integerStack = new Stack<>();
        ArrayDeque<Integer> integerArrayDeque = new ArrayDeque<>();

        PriorityQueue<Integer> integerPriorityQueue = new PriorityQueue<>();



    }


}
