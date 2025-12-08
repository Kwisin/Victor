package com.example.demo.algorithm.year25.month11;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;

public class Day26 {
    public static void main(String[] args) {
        int i = new Day26().sumMIn(new int[]{5,4,3,2,1,4,6,2,4,7});
        System.out.println(i);
    }

    /*
    503  给一个列表，找出这个循环列表中元素下一个比自身大的元素
     */
    public int[] findNextFromCycle(int[] nums) {
        int length = nums.length;
        int[] result = new int[length];
        Stack<Integer> minStack = new Stack<>();

        for (int i = 0; i < 2 * length; i++) {
            int index = i % length;
            if (minStack.isEmpty()) {
                minStack.push(i);
                continue;
            }
            //把前面所有比当前小的数出栈，并记录到result中
            while (!minStack.isEmpty() && nums[minStack.peek()] < nums[index]) {
                Integer pop = minStack.pop();
                result[pop] = nums[index];
            }

            if (i < length) {
                minStack.push(index);
            }

        }

        //只有最大的数会留在stack中
        while (!minStack.isEmpty()) {
            Integer pop = minStack.pop();
            result[pop] = -1;
        }

        return result;
    }

    /*
    901
    编写一个 StockSpanner 类，它收集某些股票的每日报价，并返回该股票当日价格的跨度。
    今天股票价格的跨度被定义为股票价格小于或等于今天价格的最大连续日数（从今天开始往回数，包括今天）。
    例如，如果未来7天股票的价格是 [100, 80, 60, 70, 60, 75, 85]，那么股票跨度将是 [1, 1, 1, 2, 1, 4, 6]。
    示例：
    输入：["StockSpanner","next","next","next","next","next","next","next"], [[],[100],[80],[60],[70],[60],[75],[85]]
    输出：[null,1,1,1,2,1,4,6]
    解释：
    首先，初始化 S = StockSpanner()，然后：
        S.next(100) 被调用并返回 1，
        S.next(80) 被调用并返回 1，
        S.next(60) 被调用并返回 1，
        S.next(70) 被调用并返回 2，
        S.next(60) 被调用并返回 1，
        S.next(75) 被调用并返回 4，
        S.next(85) 被调用并返回 6。
        注意：
     */

    public int[] findStockSpanner(int[] stockPrice) {
        if (stockPrice == null || stockPrice.length == 0) {
            return new int[0];
        }

        int length = stockPrice.length;
        int[] result = new int[length];
        Arrays.fill(result, 1);
        Stack<Integer> descStack = new Stack<>();

        for (int i = 0; i < length; i++) {
            int span = 1;
            while (!descStack.isEmpty() && stockPrice[i] >= stockPrice[descStack.peek()]) {
                Integer pop = descStack.pop();
                span += result[pop];
            }
            descStack.push(i);
            result[i] = span;
        }
        descStack.clear();
        return result;
    }

    /*
    402. 移掉K位数字
    给定一个以字符串表示的非负整数 num 和一个整数 k ，移除这个数中的 k 位数字，使得剩下的数字最小。
    注意:
    num 的长度可能超过 100，且至少包含 k 位数字。
    num 不会包含任何前导零，除了数字0本身。
    
    示例 1 :
    输入: num = "1432219", k = 3
    输出: "1219"
    解释: 移除掉三个数字 4, 3, 和 2 形成一个新的数字 1219，它是最小的。
    
    示例 2 :
    输入: num = "10200", k = 1
    输出: "200"
    解释: 移掉首位的 1，剩下的数字为 200. 注意输出不能有任何前导零。
    
    示例 3 :
    输入: num = "10", k = 2
    输出: "0"
    解释: 从原数字移除所有的数字，剩余为空就是0。

    其实就是挑出length-k个数字，使得结果最小
    */
    public String findMinStr(String num, int k) {
        if (num == null || num.isEmpty()) {
            return "0";
        }
        int length = num.length();
        int canRemove = k;

        Stack<Character> ascStack = new Stack<>();
        for (int i = 0; i < length; i++) {
            char curr = num.charAt(i);

            while (!ascStack.isEmpty() && ascStack.peek() > curr && canRemove > 0) {
                ascStack.pop();
                canRemove--;
            }

            ascStack.push(curr);
        }

        if (canRemove > 0) {
            for (int i = 0; i < canRemove; i++) {
                ascStack.pop();
            }
            canRemove = 0;
        }

        StringBuilder result = new StringBuilder();
        for (Character c : ascStack) {
            if (c == '0' && result.isEmpty()) {
                continue;
            }
            result.append(c);
        }

        return result.toString();
    }

    /*
    1081. 不同字符的最小子序列
    返回 s 字典序最小的子序列，该子序列包含 s 的所有不同字符，且只包含一次。
    
    注意：该题与 316 题相同
    
    示例 1：
    输入：s = "bcabc"
    输出："abc"
    
    示例 2：
    输入：s = "cbacdcbc"
    输出："acdb"
    
    提示：
    1 <= s.length <= 1000
    s 由小写英文字母组成
    */

    public String findMinStr2(String s) {
        if (s == null || s.isEmpty()) {
            return "";
        }
        // 统计每个字符有多少个，判断后面是否还有，有的话前面的才能移除
        HashMap<Character, Integer> characterIntegerHashMap = new HashMap<>();
        for (char c : s.toCharArray()) {
            Integer orDefault = characterIntegerHashMap.getOrDefault(c, 0);
            characterIntegerHashMap.put(c, orDefault + 1);
        }
        // 单调递增栈，出现更小的时候要考虑是否要往前移动
        Stack<Character> ascStack = new Stack<>();
        // 是否已存在字符
        HashSet<Character> exist = new HashSet<>();
        for (char c : s.toCharArray()) {
            while (!ascStack.isEmpty() && ascStack.peek() > c && characterIntegerHashMap.get(ascStack.peek()) >= 1) {
                Character pop = ascStack.pop();
                exist.remove(pop);
            }
            if (!exist.contains(c)) {
                ascStack.add(c);
                exist.add(c);
            }
            characterIntegerHashMap.put(c, characterIntegerHashMap.get(c) - 1);
        }

        return ascStack.toString();
    }

    /*
    907. 子数组的最小值之和
    给定一个整数数组 arr，找到 min(b) 的总和，其中 b 的范围为 arr 的每个（连续）子数组。
    
    由于答案可能很大，因此返回答案模 10^9 + 7。
    
    示例 1：
    输入：arr = [3,1,2,4]
    [3,4,5,2]
    [-1,0,1]
    [5,4,3,2,1,4,6,2,4,7]
[5,4,3,2,1,4,6,2,4,7]
[5,4,3,2,1,4,6,2,4,7]
    输出：17
    解释：
    子数组为 [3]，[1]，[2]，[4]，[3,1]，[1,2]，[2,4]，[3,1,2]，[1,2,4]，[3,1,2,4]。 
    最小值为 3，1，2，4，1，1，2，1，1，1，和为 17。
    
    示例 2：
    输入：arr = [11,81,94,43,3]
    [11,11,11,11,3]
    [   81,81,43,3]
    [      94,43,3]
    [         43,3]
    [            3]
    输出：444
    
    提示：
    1 <= arr.length <= 3 * 10^4
    1 <= arr[i] <= 3 * 10^4
    */
    public int sumMIn(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        long result = 0;
        Stack<Integer> ascStack = new Stack<>();
        int length = nums.length;
        int[] left = new int[length];
        int[] right = new int[length];
        for (int i = 0; i < length; i++) {
            while (!ascStack.isEmpty() && nums[i] < nums[ascStack.peek()]) {
                Integer pop = ascStack.pop();
            }
            ascStack.push(i);
            left[i] = ascStack.isEmpty() ? -1: left[ascStack.peek()];
        }
        ascStack.clear();
        for (int i = length - 1; i >= 0; i--) {
            while (!ascStack.isEmpty() && nums[i] < nums[ascStack.peek()]) {
                Integer pop = ascStack.pop();
            }
            ascStack.push(i);
            right[i] = ascStack.isEmpty()? length: right[ascStack.peek()];
        }

        for (int i = 0; i < length; i++) {
            result += (long) (((long) (i - left[i]) * (right[i] - i) * nums[i]) % (Math.pow(10, 9) + 7));
        }


        return Math.toIntExact(result);
    }

}
