package com.example.demo.algorithm.year24.demo2409;

import java.util.LinkedList;

public class Demo0915 {

    public static void main(String[] args) {

        isMatch isMatch = new isMatch();
//        boolean aa = isMatch.isMatch("aa", ".*");

        boolean aa = isMatch.isMatch("aab", "c*a*b");
        System.out.println();

    }


}
// 2390
//给你一个包含若干星号 * 的字符串 s 。
//
//在一步操作中，你可以：
//
//选中 s 中的一个星号。
//移除星号 左侧 最近的那个 非星号 字符，并移除该星号自身。
//返回移除 所有 星号之后的字符串。
//
//注意：
//
//生成的输入保证总是可以执行题面中描述的操作。
//可以证明结果字符串是唯一的

class removeStars {
    public String removeStars(String s) {
        LinkedList<Character> characters = new LinkedList<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '*') {
                characters.pop();
            } else {
                characters.addFirst(c);
            }
        }

        String res = "";
        while (!characters.isEmpty()) {
            res += characters.getLast();
            characters.removeLast();
        }

        return res;

    }
}


// 5
//给你一个字符串 s，找到 s 中最长的回文子串
class longestPalindrome {
    public String longestPalindrome(String s) {
        int length = s.length();
        if (length == 0 || length == 1) {
            return s;
        }
        int[][] ints = new int[length][length];
        int x = 0;
        int y = 0;

        // 初始化
        for (int i = 0; i < length; i++) {
            ints[i][i] = 1;
            if (i + 1 < length && s.charAt(i) == s.charAt(i + 1)) {
                ints[i][i + 1] = 2;
                x = i;
                y = i + 1;
            }
        }

        for (int gap = 2; gap < length; gap++) {
            for (int i = 0; i + gap < length; i++) {
                if (ints[i + 1][i + gap - 1] > 0 && s.charAt(i) == s.charAt(i + gap)) {
                    ints[i][i + gap] = ints[i + 1][i + gap - 1] + 2;
                    if (gap > y - x) {
                        x = i;
                        y = i + gap;
                    }
                }
            }
        }
        return s.substring(x, y + 1);
    }
}


// 10
//给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
//
//'.' 匹配任意单个字符
//'*' 匹配零个或多个前面的那一个元素
//所谓匹配，是要涵盖 整个 字符串 s 的，而不是部分字符串。给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。

//输入：s = "aa", p = "a*"
//输出：true
//解释：因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。

class isMatch {
    public boolean isMatch(String s, String p) {
        if (s.isEmpty()) {
            return true;
        }
        if (p.isEmpty()) {
            return false;
        }
        int i = 0;
        int j = 0;
        while (i < s.length() && j < p.length()) {
            char matchChar = p.charAt(j);
            char toMatchChar = s.charAt(i);
            if (matchChar == '.') {
                i++;
                j++;
            } else if (matchChar == '*') {
                char newMatchChar = p.charAt(j - 1);
                if (newMatchChar == toMatchChar || newMatchChar == '.') {
                    i++;
                } else {
                    j++;
                }
            } else {
                if (matchChar == toMatchChar) {
                    j++;
                    i++;
                } else {
                    if (p.charAt(j + 1) == '*') {
                        j += 2;
                    } else {
                        return false;
                    }
                }
            }
        }

        if (i != s.length()) {
            return false;
        }
        if (j == p.length()) {
            return true;
        }

        if (p.charAt(j) != '*') {
            if (j == p.length()) {
                return true;
            } else {
                return false;
            }
        }
        char c = p.charAt(j - 1);
        for (int index = j + 1; index < s.length(); index++) {
            if (s.charAt(index) == c) {
                j++;
            } else {
                return false;
            }
        }

        if (j == p.length() - 1) {
            return true;
        }
        return false;
    }
}