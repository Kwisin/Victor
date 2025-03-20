package com.example.demo.algorithm.demo2411;

import java.util.ArrayList;
import java.util.List;

public class Demo1128 {

    public static void main(String[] args) {
        int t = new minSubArrayLen().minSubArrayLen(11, new int[]{1,2,3,4,5});
        System.out.println();
    }


}

//68
/*
给定一个单词数组 words 和一个长度 maxWidth ，重新排版单词，使其成为每行恰好有 maxWidth 个字符，且左右两端对齐的文本。
你应该使用 “贪心算法” 来放置给定的单词；也就是说，尽可能多地往每行中放置单词。必要时可用空格 ' ' 填充，使得每行恰好有 maxWidth 个字符。
要求尽可能均匀分配单词间的空格数量。如果某一行单词间的空格不能均匀分配，则左侧放置的空格数要多于右侧的空格数。
文本的最后一行应为左对齐，且单词之间不插入额外的空格。

注意:
单词是指由非空格字符组成的字符序列。
每个单词的长度大于 0，小于等于 maxWidth。
输入单词数组 words 至少包含一个单词。

示例 1:
输入: words = ["This", "is", "an", "example", "of", "text", "justification."], maxWidth = 16
[4,2,2,7,2,4,14]


哪几个应该在一行

在一行的单词怎么分配
4+1+2+1+2 = 10
16-10 = 6 /2 =3

7+1+2+1+4 = 15
16 - 15 = 1
输出:
[
   "This    is    an",
   "example  of text",
   "justification.  "
]

示例 2:
输入:words = ["What","must","be","acknowledgment","shall","be"], maxWidth = 16
输出:
[
  "What   must   be",
  "acknowledgment  ",
  "shall be        "
]
解释: 注意最后一行的格式应为 "shall be    " 而不是 "shall     be",
     因为最后一行应为左对齐，而不是左右两端对齐。
     第二行同样为左对齐，这是因为这行只包含一个单词。

示例 3:
输入:words = ["Science","is","what","we","understand","well","enough","to","explain","to","a","computer.","Art","is","everything","else","we","do"]，maxWidth = 20
输出:
[
  "Science  is  what we",
  "understand      well",
  "enough to explain to",
  "a  computer.  Art is",
  "everything  else  we",
  "do                  "
 */


class fullJustify {
    public List<String> fullJustify(String[] words, int maxWidth) {
        int length = words.length;
        if (length == 0 || maxWidth == 0) {
            return new ArrayList<>();
        }

        int[] wordLengthList = new int[length];
        for (int i = 0; i < length; i++) {
            wordLengthList[i] = words[i].length();
        }

        ArrayList<String> result = new ArrayList<>();
        int start = 0;
        int end = 0;
        int currentLength = 0;

        while (true) {
            if (wordLengthList[end] + currentLength + (end - start) <= maxWidth) {
                currentLength += wordLengthList[end];
                end++;
                if (end == length) {
                    result.add(generateRowString(start, end - 1, maxWidth, words).toString());
                    break;
                }
            } else {
                result.add(generateRowString(start, end - 1, maxWidth, words).toString());
                start = end;
                currentLength = 0;
            }


        }
        return result;

    }

    // 生成一行字符串
    public StringBuilder generateRowString(int start, int end, int maxWidth, String[] words) {
        StringBuilder result = new StringBuilder();
        int gap = end - start;// 单词空隙数
        // 最后一行靠左对齐
        if (end == words.length - 1) {
            gap++;
        }

        int currentLength = 0;
        for (int i = start; i <= end; i++) {
            currentLength += words[i].length();
        }

        int m = maxWidth - currentLength; // 剩余空格数
        if (start == end) {
            // 只有一个单词，左对齐
            result.append(words[start]);
            return appendWhite(result, m);
        }
        int average = m / gap; // 每个空隙平均空格数
        int remain = m - (gap * average); // // 多余空格左边多一个

        int currGap = 1;
        for (; start <= end; start++) {
            result.append(words[start]);

            if (end == words.length - 1) {
                if (currGap == gap) {
                    result = appendWhite(result, m - gap + 1);
                } else {
                    result = appendWhite(result, 1);
                }
            } else if (currGap <= gap) {
                result = appendWhite(result, average);
                if (currGap <= remain) {
                    result = appendWhite(result, 1);
                }
            }

            currGap++;
        }

        return result;
    }

    public StringBuilder appendWhite(StringBuilder result, int cnt) {
        for (int i = 0; i < cnt; i++) {
            result.append(" ");
        }

        return result;
    }
}

//167
/*
给你一个下标从 1 开始的整数数组 numbers ，该数组已按 非递减顺序排列  ，请你从数组中找出满足相加之和等于目标数 target 的两个数。
如果设这两个数分别是 numbers[index1] 和 numbers[index2] ，则 1 <= index1 < index2 <= numbers.length 。
以长度为 2 的整数数组 [index1, index2] 的形式返回这两个整数的下标 index1 和 index2。
你可以假设每个输入 只对应唯一的答案 ，而且你 不可以 重复使用相同的元素。
你所设计的解决方案必须只使用常量级的额外空间。

示例 1：
输入：numbers = [2,7,11,15], target = 9
输出：[1,2]
解释：2 与 7 之和等于目标数 9 。因此 index1 = 1, index2 = 2 。返回 [1, 2]。

示例 2：
输入：numbers = [2,3,4], target = 6
输出：[1,3]
解释：2 与 4 之和等于目标数 6 。因此 index1 = 1, index2 = 3 。返回 [1, 3]。

示例 3：
输入：numbers = [-1,0], target = -1
输出：[1,2]
解释：-1 与 0 之和等于目标数 -1 。因此 index1 = 1, index2 = 2 。返回 [1, 2]。
 */


class twoSum {
    public int[] twoSum(int[] numbers, int target) {

        int left = 0;
        int right = numbers.length - 1;
        while (left < right) {
            if (numbers[left] + numbers[right] == target) {
                break;
            } else if (numbers[left] + numbers[right] > target) {
                right--;
            } else {
                left++;
            }
        }

        return new int[]{left + 1, right + 1};

    }
}

// 209
/*
给定一个含有 n 个正整数的数组和一个正整数 target 。
找出该数组中满足其总和大于等于 target 的长度最小的子数组
 [numsl, numsl+1, ..., numsr-1, numsr] ，并返回其长度。如果不存在符合条件的子数组，返回 0 。

 示例 1：
输入：target = 7, nums = [2,3,1,2,4,3]
输出：2
解释：子数组 [4,3] 是该条件下的长度最小的子数组。

示例 2：
输入：target = 4, nums = [1,4,4]
输出：1

示例 3：
输入：target = 11, nums = [1,1,1,1,1,1,1,1]
输出：0
 */

class minSubArrayLen {
    public int minSubArrayLen(int target, int[] nums) {
        int length = nums.length;
        int sum = 0;
        for (int i = 0; i < length; i++) {
            sum += nums[i];
        }
        if (sum < target) {
            return 0;
        }

        int start = 0, end = 0;
        int currentSum = nums[0];
        int min = Integer.MAX_VALUE;
        while (end < length && start <= end) {
            if (currentSum < target) {
                end++;
                if (end == length) {
                    break;
                }
                currentSum += nums[end];
            } else if (currentSum > target) {
                min = Math.min(min, end - start + 1);
                currentSum -= nums[start];
                start++;
            } else {
                min = Math.min(min, end - start + 1);
                currentSum -= nums[start];
                start++;
                end++;
                if (end == length) {
                    break;
                }
                currentSum += nums[end];
            }
        }
        return min;

    }
}

