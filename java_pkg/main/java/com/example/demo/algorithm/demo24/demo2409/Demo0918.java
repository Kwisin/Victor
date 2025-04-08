package com.example.demo.algorithm.demo24.demo2409;

import java.util.*;

public class Demo0918 {

    public static void main(String[] args) {
        List<String> strings = new generateParenthesis().generateParenthesis(3);
        System.out.println();

        longestValidParentheses longestValidParentheses = new longestValidParentheses();
        int i = longestValidParentheses.longestValidParentheses("(()())()())");
        System.out.println();

    }


}


// 22
// 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合

//示例 1：
//
//输入：n = 3
//输出：["((()))","(()())","(())()","()(())","()()()"]
// ()
// ()()  (())
// ()()()   (()())  ,  (())()  ()(())  ((()))
// ()()()() (()()()), ()(()())  (()())()  ((()())), ()(())()  (())()()  ((())()), ()()(())  (()(())), ()((())),((()))(), (((())))
//示例 2：
//
//输入：n = 1
//输出：["()"]


class generateParenthesis {
    public List<String> generateParenthesis(int n) {
        if (n == 0) {
            return Collections.singletonList("");
        }

        LinkedList<String> strings = new LinkedList<>();
        strings.addLast("()");
        while (!strings.isEmpty()) {
            String current = strings.getFirst();
            if (current.length() == 2 * n) {
                break;
            }
            strings.removeFirst();
            strings.addLast(current + "()");
            strings.addLast("()" + current);
            strings.addLast("(" + current + ")");
        }

        HashSet<String> stringHashSet = new HashSet<>();
        ArrayList<String> result = new ArrayList<>();
        while (!strings.isEmpty()) {
            String current = strings.pop();
            if (stringHashSet.contains(current)) {
                continue;
            }

            result.add(current);
            stringHashSet.add(current);
        }

        return result;
    }
}

//32
//给你一个只包含 '(' 和 ')' 的字符串，找出最长有效（格式正确且连续）括号子串的长度。

//示例 1：
//
//        输入：s = "(()"
//        输出：2
//        解释：最长有效括号子串是 "()"
//        示例 2：
//
//        输入：s = ")()())"
//        输出：4
//        解释：最长有效括号子串是 "()()"
//        示例 3：
//
//        输入：s = ""
//        输出：0

class longestValidParentheses {
    public int longestValidParentheses(String s) {
        if (s.length() <= 1) {
            return 0;
        }
        int length = s.length();

        // 初始化
        int max = 0;
        int[][] table = new int[length][length];
        for (int i = 0; i < length; i++) {
            table[i][i] = 0;
            if (i + 1 < length && s.charAt(i) == '(' && s.charAt(i + 1) == ')') {
                table[i][i + 1] = 2;
                max = 2;
            }
        }


        for (int gap = 2; gap < length; gap++) {
            for (int i = 0; i + gap < length; i++) {
                int row = i;
                int col = i + gap;
                if ((gap + 1) % 2 == 1) {
                    table[row][col] = 0;
                    continue;
                }

                int len1 = 0;
                if (table[row + 1][col - 1] > 0 && s.charAt(row) == '(' && s.charAt(col) == ')') {
                    len1 = table[row + 1][col - 1] + 2;
                }

                int len2 = 0;
                if (table[row][col - 1] == 0 && s.charAt(col - 1) == '(' && s.charAt(col) == ')') {
                    len2 = gap + 1;
                }

                table[row][col] = Math.max(len1, len2);
                if (table[row][col] > max) {
                    max = table[row][col];
                }
            }
        }


        return max;

    }
}

//42
//给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。

//输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
//输出：6
//解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。
//示例 2：
//
//输入：height = [4,2,0,3,2,5]
//输出：9

class trap {
    public int trap(int[] height) {

        return 0;

    }
}

// 3D接雨水