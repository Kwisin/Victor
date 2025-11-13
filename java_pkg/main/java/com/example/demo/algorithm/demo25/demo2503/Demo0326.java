package com.example.demo.algorithm.demo25.demo2503;


import java.util.HashMap;

public class Demo0326 {

    public static void main(String[] args) {
        String s = new numberToWords().numberToWords(1123451211);
        System.out.println(s);
    }

}

// 273. 整数转换英文表示
/*
示例 1：

输入：num =  123
输出："One Hundred Twenty Three"
示例 2：

输入：num = 12 345
输出："Twelve Thousand Three Hundred Forty Five"
示例 3：

输入：num = 111 234 567

输出："One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
 */
class numberToWords {


    HashMap<Integer, String> positionMap = new HashMap<>();
    HashMap<Integer, String> tenMap = new HashMap<>();
    HashMap<Integer, String> single = new HashMap<>();

    public numberToWords() {
        this.positionMap.put(1, "Thousand");
        this.positionMap.put(2, "Million");
        this.positionMap.put(3, "Billion");

        this.tenMap.put(2, "Twenty");
        this.tenMap.put(3, "Thirty");
        this.tenMap.put(4, "Forty");
        this.tenMap.put(5, "Fifty");
        this.tenMap.put(6, "Sixty");
        this.tenMap.put(7, "Seventy");
        this.tenMap.put(8, "Eighty");
        this.tenMap.put(9, "Ninety");

        this.single.put(1, "One");
        this.single.put(2, "Two");
        this.single.put(3, "Three");
        this.single.put(4, "Four");
        this.single.put(5, "Five");
        this.single.put(6, "Six");
        this.single.put(7, "Seven");
        this.single.put(8, "Eight");
        this.single.put(9, "Nine");
        this.single.put(10, "Ten");
        this.single.put(11, "Eleven");
        this.single.put(12, "Twelve");
        this.single.put(13, "Thirteen");
        this.single.put(14, "Fourteen");
        this.single.put(15, "Fifteen");
        this.single.put(16, "Sixteen");
        this.single.put(17, "Seventeen");
        this.single.put(18, "Eighteen");
        this.single.put(19, "Nineteen");

    }

    public String numberToWords(int num) {
        if (num == 0) {
            return "Zero";
        }
        if (num < 20) {
            return this.single.get(num);
        }
        StringBuilder result = new StringBuilder();
        String s = String.valueOf(num);
        int length = s.length(); //9
        for (int i = 0; i < length; ) {
            int position = (length - i - 1) / 3; // 2
            int end = length - position * 3;
            String part = getPart(s.substring(i, end));
            if (!part.isEmpty()) {
                result.append(part).append(" ").
                        append(this.positionMap.getOrDefault(position, ""));
            }
            i = end;
        }
        return result.substring(result.charAt(0) == ' ' ? 1 : 0, result.charAt(result.length() - 1) == ' ' ? result.length() - 1 : result.length());
    }

    public String getPart(String s) {
        StringBuilder result = new StringBuilder();
        int length = s.length();
        char single = s.charAt(length - 1);
        char ten = '0';
        if (length >= 2) {
            ten = s.charAt(length - 2);
        }
        char hundred = '0';
        if (length >= 3) {
            hundred = s.charAt(length - 3);
        }

        if (hundred != '0') {
            result.append(" ").append(this.single.get(Integer.parseInt(String.valueOf(hundred)))).append(" ").append("Hundred");
        }
        if (ten != '0' && ten != '1') {
            result.append(" ").append(this.tenMap.get(Integer.parseInt(String.valueOf(ten))));
        }
        if (ten == '1') {
            String substring = s.substring(length - 2, length);
            result.append(" ").append(this.single.get(Integer.parseInt(substring)));
        } else if (single != '0') {
            result.append(" ").append(this.single.get(Integer.parseInt(String.valueOf(single))));
        }

        return result.toString();

    }
}


