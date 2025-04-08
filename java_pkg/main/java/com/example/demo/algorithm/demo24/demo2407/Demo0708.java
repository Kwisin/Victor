package com.example.demo.algorithm.demo24.demo2407;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Demo0708 {

    public static void main(String[] args) {

//        输入：s = "the sky is blue"
//        输出："blue is sky the"


    }

    //AC
    public static String getReverseString(String s) {
        String[] split = s.split("\\s+");
        StringBuilder result = new StringBuilder();
        int end = 0;
        if (split.length >= 1 && Objects.equals(split[0], "")) {
            end = 1;
        }
        for (int i = split.length - 1; i >= end; i--) {
            if (i == end) {
                result.append(split[i]);
            } else {
                result.append(split[i]).append(" ");
            }
        }

        List<String> strings = Arrays.asList(s.split("\\s+"));
        Collections.reverse(strings);
        strings.forEach(System.out::println);

        return result.toString();
    }


}
