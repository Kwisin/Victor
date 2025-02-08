package com.example.demo.study.demo2502;


import java.util.ArrayList;
import java.util.HashMap;

public class Demo0208 {

    public static void main(String[] args) {
        System.out.println();
    }


}

// 63
/*
给定一个 m x n 的整数数组 grid。一个机器人初始位于 左上角（即 grid[0][0]）。
机器人尝试移动到 右下角（即 grid[m - 1][n - 1]）。
机器人每次只能向下或者向右移动一步。
网格中的障碍物和空位置分别用 1 和 0 来表示。机器人的移动路径中不能包含 任何 有障碍物的方格。
返回机器人能够到达右下角的不同路径数量。

输入：obstacleGrid = [
[0,0,0],
[0,1,0],
[0,0,0]
]

[2,1,1],
[1,0,1],
[1,1,0]


[0,0,0,1,0],
[0,1,0,0,1],
[0,0,0,0,0]
[0,0,0,0,0]

[30,16,7,2,0],
[14,9,5,2,0],
[5,4,3,2,1]
[1,1,1,1,0]

输出：2
解释：3x3 网格的正中间有一个障碍物。
从左上角到右下角一共有 2 条不同的路径：
1. 向右 -> 向右 -> 向下 -> 向下
2. 向下 -> 向下 -> 向右 -> 向右

输入：obstacleGrid = [[0,1],[0,0]]
输出：1
 */

class uniquePathsWithObstacles {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int row = obstacleGrid.length;
        if (row == 0) {
            return 0;
        }
        int col = obstacleGrid[0].length;
        int[][] table = new int[row][col];
        if (table[row - 1][col - 1] == 1) {
            return 0;
        }
        table[row - 1][col - 1] = 1;
        for (int i = col - 2; i >= 0; i--) {
            table[row - 1][i] = obstacleGrid[row - 1][i] == 1 ? 0 : table[row - 1][i + 1];
        }
        for (int i = row - 2; i >= 0; i--) {
            table[i][col - 1] = obstacleGrid[i][col - 1] == 1 ? 0 : table[i + 1][col - 1];
        }

        for (int i = row - 2; i >= 0; i--) {
            for (int j = col - 2; j >= 0; j--) {
                table[i][j] = obstacleGrid[i][j] == 1 ? 0 : table[i + 1][j] + table[i][j + 1];
            }
        }

        return table[0][0];


    }
}

// 166
/*
给定两个整数，分别表示分数的分子 numerator 和分母 denominator，以 字符串形式返回小数 。
如果小数部分为循环小数，则将循环的部分括在括号内。
如果存在多个答案，只需返回 任意一个 。
对于所有给定的输入，保证 答案字符串的长度小于 104

示例 1：
输入：numerator = 1, denominator = 2
输出："0.5"

示例 2：
输入：numerator = 2, denominator = 1
输出："2"

示例 3：
输入：numerator = 4, denominator = 333
输出："0.(012)"
 */

class fractionToDecimal {
    public String fractionToDecimal(int numerator, int denominator) {
        if (denominator == 0) {
            return "";
        }
        if (numerator == 0) {
            return "0";
        }

        long newNumerator = numerator;
        long newDenominator = denominator;
        String flag = "";
        if (newNumerator < 0 && newDenominator > 0) {
            newNumerator = -newNumerator;
            flag = "-";
        } else if (newNumerator > 0 && newDenominator < 0) {
            newDenominator = -newDenominator;
            flag = "-";
        } else if (newNumerator < 0 && newDenominator < 0) {
            newDenominator = -newDenominator;
            newNumerator = -newNumerator;
        }

        if (newNumerator % newDenominator == 0) {
            return flag + newNumerator / newDenominator;
        }

        long first = newNumerator / newDenominator;
        long remain = newNumerator - first * newDenominator;
        return flag + first + "." + getResult(remain, newDenominator);
    }

    public String getResult(long remain, long denominator) {
        StringBuilder result = new StringBuilder();
        HashMap<Long, Integer> remainPositionMap = new HashMap<>();
        int curr = 0;
        remainPositionMap.put(remain, curr);
        remain *= 10;
        while (remain != 0) {
            if (remainPositionMap.containsKey(remain)) {
                Integer integer = remainPositionMap.get(remain);
                result.insert(integer, "(");
                result.append(")");
                break;
            } else {
                remainPositionMap.put(remain, curr);
            }

            long temp = remain >= denominator ? remain / denominator : 0;
            remain = remain >= denominator ? (remain % denominator) * 10 : remain * 10;
            result.append(temp);

            curr++;
        }

        return result.toString();
    }
}


// LCR 138  类似  65
/*
有效数字（按顺序）可以分成以下几个部分：

若干空格
一个 小数 或者 整数
（可选）一个 'e' 或 'E' ，后面跟着一个 整数
若干空格
小数（按顺序）可以分成以下几个部分：

（可选）一个符号字符（'+' 或 '-'）
下述格式之一：
至少一位数字，后面跟着一个点 '.'
至少一位数字，后面跟着一个点 '.' ，后面再跟着至少一位数字
一个点 '.' ，后面跟着至少一位数字
整数（按顺序）可以分成以下几个部分：

（可选）一个符号字符（'+' 或 '-'）
至少一位数字
部分有效数字列举如下：["2", "0089", "-0.1", "+3.14", "4.", "-.9", "2e10", "-90E3", "3e+7", "+6e-1", "53.5e93", "-123.456e789"]
部分无效数字列举如下：["abc", "1a", "1e", "e3", "99e2.5", "--6", "-+3", "95a54e53"]
给你一个字符串 s ，如果 s 是一个 有效数字 ，请返回 true 。


示例 1：
输入：s = "0"
输出：true
示例 2：
输入：s = "e"
输出：false
示例 3：
输入：s = "."
输出：false
 */

class validNumber {

    public boolean validNumber(String s) {
        String s1 = s.toLowerCase().replace(" ","");
        HashMap<Character, ArrayList<Integer>> characterPositionHashMap = new HashMap<>();
        for (int i = 0; i < s1.length(); i++) {
            char c = s1.charAt(i);
            ArrayList<Integer> orDefault = characterPositionHashMap.getOrDefault(c, new ArrayList<>());
            if (c == 'e' || c == '.' || c == 'E') {
                if (orDefault.size() > 0) {
                    return false;
                }
            } else if ((c != '-' && c != '+') && (c < '0' || c > '9')) {
                return false;
            }
            orDefault.add(i);
            characterPositionHashMap.put(c, orDefault);
        }

        ArrayList<Integer> ePositions = characterPositionHashMap.getOrDefault('e', new ArrayList<>());
        ArrayList<Integer> pointPositions = characterPositionHashMap.getOrDefault('.', new ArrayList<>());
        if (ePositions.size() > 0) {
            if (pointPositions.size() > 0 && ePositions.get(0) < pointPositions.get(0)) {
                return false;
            }
            String[] es = s1.split("e");
            if (es.length != 2) {
                return false;
            }
            return checkNum(es[0]) && checkNum(es[1]);
        }

        return checkNum(s1);
    }


    public boolean checkNum(String s) {
        try {
            Integer.valueOf(s);
        } catch (Exception e) {
            try {
                Double.valueOf(s);
            } catch (Exception doubleE) {
                return false;
            }

        }
        return true;
    }
}