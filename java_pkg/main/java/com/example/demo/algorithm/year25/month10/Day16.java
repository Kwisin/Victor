package com.example.demo.algorithm.year25.month10;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Day16 {
    public static void main(String[] args) {

    }

    /*
    DNA序列 由一系列核苷酸组成，缩写为 'A', 'C', 'G' 和 'T'.。

        例如，"ACGAATTCCG" 是一个 DNA序列 。
        在研究 DNA 时，识别 DNA 中的重复序列非常有用。

        给定一个表示 DNA序列 的字符串 s ，
        返回所有在 DNA 分子中出现不止一次的 长度为 10 的序列(子字符串)。你可以按 任意顺序 返回答案。

        示例 1：

        输入：s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"
        输出：["AAAAACCCCC","CCCCCAAAAA"]
        示例 2：

        输入：s = "AAAAAAAAAAAAA"
        输出：["AAAAAAAAAA"]


        提示：

        0 <= s.length <= 105
        s[i]=='A'、'C'、'G' or 'T'

     */

    public List<String> findRepeatedDnaSequences(String s) {
        if (s == null || s.length() < 10) {
            return new ArrayList<>();
        }
        HashSet<String> strings = new HashSet<>();
        HashSet<String> result = new HashSet<>();
        int start = 0;
        for (; start <= s.length() - 10; start++) {
            String substring = s.substring(start, start + 10);
            if (strings.contains(substring) && !result.contains(substring)) {
                result.add(substring);
                continue;
            }
            strings.add(substring);
        }
        return new ArrayList<>(result);
    }



    /*
    282n\
    给定一个仅包含数字 0-9 的字符串 num 和一个目标值整数 target ，
    在 num 的数字之间添加 二元 运算符（不是一元）+、- 或 * ，返回 所有 能够得到 target 的表达式。
    注意，返回表达式中的操作数 不应该 包含前导零。
    注意，一个数字可以包含多个数位。

    示例 1:

        输入: num = "123", target = 6
        输出: ["1+2+3", "1*2*3"]
        解释: “1*2*3” 和 “1+2+3” 的值都是6。
        示例 2:

        输入: num = "232", target = 8
        输出: ["2*3+2", "2+3*2"]
        解释: “2*3+2” 和 “2+3*2” 的值都是8。
        示例 3:

        输入: num = "30456237490", target = 9191
        输出: []
        解释: 表达式 “3456237490” 无法得到 9191 。


     */


    public List<String> addOperators(String num, int target) {
        List<String> result = new ArrayList<>();
        addOperatorsDFS(num, target, 0, 0, 0, "", result);
        return result;
    }

    public void addOperatorsDFS(String num, int target, int index, long currSum, long preNum, String expression, List<String> result) {
        if (index == num.length() && currSum == target) {
            result.add(expression);
            return;
        }

        for (int i = index + 1; i <= num.length(); i++) {
            String currStr = num.substring(index, i);
            if (currStr.startsWith("0") && currStr.length() > 1) {
                // 做一个剪枝，0开头是不合法数字，直接跳过后续所有可能
                break;
            }
            long currNum = Long.parseLong(currStr);

            if (index == 0) {
                // 首次处理直接加进去
                addOperatorsDFS(num, target, i, currNum, currNum, currStr, result);
            } else {
                // 非首次处理考虑 + - *

                //+
                addOperatorsDFS(num, target, i, currSum + currNum, currNum, expression + "+" + currStr, result);

                //-
                addOperatorsDFS(num, target, i, currSum - currNum, -currNum, expression + "-" + currStr, result);

                //*
                addOperatorsDFS(num, target, i, currSum - preNum + preNum * currNum, preNum * currNum, expression + "*" + currStr, result);
            }

        }

    }
}
