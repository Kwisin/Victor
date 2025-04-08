package com.example.demo.algorithm.demo25.demo2502;


import java.util.*;


public class Demo0206 {

    public static void main(String[] args) {
        List<List<Integer>> lists = new permuteUnique().permuteUnique(new int[]{1, 1, 3});
        System.out.println();
    }


}

// 47
/*
给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。

示例 1：
输入：nums = [1,1,2]
输出：
[[1,1,2],
 [1,2,1],
 [2,1,1]]

示例 2：
输入：nums = [1,2,3]
输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 */
class permuteUnique {
    private List<List<Integer>> result = new ArrayList<>();

    public List<List<Integer>> permuteUnique(int[] nums) {
        if (nums.length == 0) {
            return result;
        }
        Arrays.sort(nums);
        dfs(nums, new LinkedList<>(), new HashSet<>());
        return result;
    }

    public void dfs(int[] nums, LinkedList<Integer> curr, HashSet<Integer> indexStatus) {
        if (curr.size() == nums.length) {
            this.result.add(new ArrayList<>(curr));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (indexStatus.contains(i)) {
                continue;
            }
            if (i - 1 >= 0 && !indexStatus.contains(i - 1) && nums[i] == nums[i - 1]) {
                continue;
            }
            curr.addLast(nums[i]);
            indexStatus.add(i);
            dfs(nums, curr, indexStatus);
            curr.removeLast();
            indexStatus.remove(i);
        }

    }
}

// 301
/*
给你一个由若干括号和字母组成的字符串 s ，删除最小数量的无效括号，使得输入的字符串有效。

返回所有可能的结果。答案可以按 任意顺序 返回。

示例 1：

输入：s = "()())((())()()((("

(()))()((

输出：["(())()","()()()"]
示例 2：
[1,0,1,0,-1,-2,-1,-2,-1,0,1]

输入：s = "(a)())()"
输出：["(a())()","(a)()()"]
示例 3：

输入：s = ")("
输出：[""]
 */

class removeInvalidParentheses {
    public List<String> result = new ArrayList<>();

    public List<String> removeInvalidParentheses(String s) {
        HashSet<String> objects = new HashSet<>();
        objects.add(s);
        bfs(objects);
        return this.result;
    }

    public void bfs(HashSet<String> toCheck) {
        HashSet<String> newSet = new HashSet<>();
        while (toCheck.size() > 0) {
            for (String item : toCheck) {
                if (isValid(item)) {
                    this.result.add(item);
                }
                if (this.result.size() > 0) {
                    return;
                }
            }
            for (String item : toCheck) {
                for (int i = 0; i < item.length(); i++) {
                    newSet.add(item.substring(0, i) + item.substring(i + 1));
                }
            }
            toCheck = newSet;
        }
    }

    public boolean isValid(String s) {
        int temp = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') {
                temp++;
            }
            if (c == ')') {
                temp--;
                if (temp < 0) {
                    return false;
                }
            }
        }
        return temp == 0;
    }

    public void dfs(){

    }

}


