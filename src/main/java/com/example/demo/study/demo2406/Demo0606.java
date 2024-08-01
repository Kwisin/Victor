package com.example.demo.study.demo2406;

import java.util.*;

public class Demo0606 {

    public static void main(String[] args) {

        int[] array = new int[]{1, 9, 0};
        int[] array1 = {1, 9, 0};
        int[] array2 = new int[3];
        String[] strArray = new String[]{"1", "2"};

        byte[] bytes = new byte[2];
        char[] chars = new char[3];


        //基本数据类型 byte short char int float long double boolean

        Map<String, Integer> stringIntegerHashMap = new HashMap<>();


        List<Integer> list = new ArrayList<>();
        LinkedList<Integer> integerLinkedList = new LinkedList<>();

        list.add(1);
        list.add(2);

        Collections.emptyMap();


        Integer[] integers = list.toArray(new Integer[0]);
        System.out.println(integers[0].intValue());


    }


}
