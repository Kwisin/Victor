package com.example.demo.algorithm.demo2412;


import java.util.*;

public class Demo1225 {
    public static void main(String[] args) {
//        List<String> strings = new letterCombinations().letterCombinations("2");
        List<List<Integer>> combine = new combine().combine(4, 2);
        System.out.println();
    }
}


// 212
/*
给定一个 m x n 二维字符网格 board 和一个单词（字符串）列表 words， 返回所有二维网格上的单词 。
单词必须按照字母顺序，通过 相邻的单元格 内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。
同一个单元格内的字母在一个单词中不允许被重复使用。

输入：board = [
["o","a","a","n"],
["e","t","a","e"],
["i","h","k","r"],
["i","f","l","v"]],
 words = ["oath","pea","eat","rain"]
输出：["eat","oath"]

输入：board = [
["a","b"],
["c","d"]],
 words = ["abcb"]
输出：[]
 */

class findWords {
    public Map<Character, List<int[]>> charPosition;
    public HashSet<String> result;
    public int rowLength;
    public int colLength;
    public char[][] board;


    public findWords() {
        this.charPosition = new HashMap<>();
        this.result = new HashSet<>();
    }

    public List<String> findWords(char[][] board, String[] words) {
        if (board.length == 0 || board[0].length == 0) {
            return new ArrayList<>(result);
        }
        this.rowLength = board.length;
        this.colLength = board[0].length;
        this.board = board;
        initCharPosition(board);

        for (String item : words) {
            checkWord(item);
        }

        return new ArrayList<>(result);
    }

    public void initCharPosition(char[][] board) {
        for (int row = 0; row < this.rowLength; row++) {
            for (int col = 0; col < this.colLength; col++) {
                List<int[]> orDefault = this.charPosition.getOrDefault(board[row][col], new ArrayList<>());
                orDefault.add(new int[]{row, col});
                this.charPosition.put(board[row][col], orDefault);
            }
        }
    }

    public void checkWord(String word) {
        if (checkFinish(word)) {
            return;
        }
        char[] chars = word.toCharArray();
        List<int[]> orDefault = this.charPosition.getOrDefault(chars[0], new ArrayList<>());
        if (orDefault.size() == 0) {
            return;
        }

        for (int[] item : orDefault) {

            if (checkFinish(word) || checkNotExist(word)) {
                return;
            }
            dfs(item[0], item[1], 0, word, new HashSet<>());
        }

    }


    public void dfs(int row, int col, int index, String word, HashSet<Integer> exist) {
        if (checkFinish(word)) {
            return;
        }
        int unq = row * 3 + col * 2 + 1;
        if (exist.contains(unq)) {
            return;
        }
        if (row >= this.rowLength || row < 0 || col >= this.colLength || col < 0) {
            return;
        }
        char[] chars = word.toCharArray();
        if (chars[index] != this.board[row][col]) {
            return;
        }
        if (index == word.length() - 1) {
            this.result.add(word);
        }

        exist.add(unq);
        dfs(row - 1, col, index + 1, word, exist);
        dfs(row + 1, col, index + 1, word, exist);
        dfs(row, col - 1, index + 1, word, exist);
        dfs(row, col + 1, index + 1, word, exist);
        exist.remove(unq);
    }

    public boolean checkFinish(String word) {
        return this.result.contains(word);
    }

    public boolean checkNotExist(String word) {
        char[] chars = word.toCharArray();
        Map<Character, Integer> cnt = new HashMap<>();

        for (char c : chars) {
            if (!this.charPosition.containsKey(c)) {
                return true;
            }
            cnt.put(c, cnt.getOrDefault(c, 0) + 1);
        }

        for (char c : cnt.keySet()) {
            if (cnt.get(c) > this.charPosition.getOrDefault(c, new ArrayList<>()).size()) {
                return true;
            }

        }

        return false;

    }

}


// 17
/*
给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。

给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。

示例 1：
输入：digits = "23"
输出：["ad","ae","af","bd","be","bf","cd","ce","cf"]

示例 2：
输入：digits = ""
输出：[]

示例 3：
输入：digits = "2"
输出：["a","b","c"]

 */

class letterCombinations {
    Map<Character, Character[]> digitMap = new HashMap<>();
    HashSet<String> result = new HashSet<>();

    public letterCombinations() {
        this.digitMap.put('2', new Character[]{'a', 'b', 'c'});
        this.digitMap.put('3', new Character[]{'d', 'e', 'f'});
        this.digitMap.put('4', new Character[]{'g', 'h', 'i'});
        this.digitMap.put('5', new Character[]{'j', 'k', 'l'});
        this.digitMap.put('6', new Character[]{'m', 'n', 'o'});
        this.digitMap.put('7', new Character[]{'p', 'q', 'r', 's'});
        this.digitMap.put('8', new Character[]{'t', 'u', 'v'});
        this.digitMap.put('9', new Character[]{'w', 'x', 'y', 'z'});
    }

    public List<String> letterCombinations(String digits) {
        char[] chars = digits.toCharArray();
        if (chars.length == 0) {
            return new ArrayList<>();
        }
        dfs(digits, 0, "");
        return new ArrayList<>(this.result);
    }

    public void dfs(String digits, int index, String curr) {
        if (index > digits.length()) {
            return;
        }
        if (index == digits.length()) {
            this.result.add(curr);
            return;
        }
        char c = digits.charAt(index);
        Character[] characters = this.digitMap.get(c);
        for (Character item : characters) {
            dfs(digits, index + 1, curr.concat(String.valueOf(item)));
        }
    }
}

// 77
/*
给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合。
你可以按 任何顺序 返回答案。

示例 1：
输入：n = 4, k = 2
输出：
[
  [2,4],
  [3,4],
  [2,3],
  [1,2],
  [1,3],
  [1,4],
]

示例 2：
输入：n = 1, k = 1
输出：[[1]]
 */
class combine {
    public List<List<Integer>> result = new ArrayList<>();

    public List<List<Integer>> combine(int n, int k) {
        if (k == 0) {
            return new ArrayList<>();
        }
        dfs(k, n, 1, new ArrayDeque<>());

        return this.result;

    }

    public void dfs(int k, int n, int index, ArrayDeque<Integer> curr) {
        if (curr.size() == k) {
            this.result.add(new ArrayList<>(curr));
            return;
        }

        for (int i = index; i <= n; i++) {
            curr.addLast(i);
            dfs(k, n, i + 1, curr);
            curr.removeLast();
        }

    }
}


// 148
/*
给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表

输入：head = [4,2,1,3]
输出：[1,2,3,4]


输入：head = [-1,5,3,4,0]
输出：[-1,0,3,4,5]
 */

/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode() {}
 * ListNode(int val) { this.val = val; }
 * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class sortList {
    public ListNode sortList(ListNode head) {

        PriorityQueue<Integer> integerPriorityQueue = new PriorityQueue<>();
        ListNode curr = head;
        while (curr != null) {
            integerPriorityQueue.add(curr.val);
            curr = curr.next;
        }

        curr = head;
        while (curr != null) {
            curr.val = integerPriorityQueue.poll();
            curr = curr.next;
        }

        return head;
    }
}


