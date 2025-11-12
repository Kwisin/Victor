package com.example.demo.algorithm.year25.month05;

import java.util.*;

public class Demo17 {
    public static void main(String[] args) {
        ArrayList<String> strings = new ArrayList<>(Arrays.asList("hot", "dot", "dog", "lot", "log", "cog"));
        List<List<String>> ladders = new findLadders().findLadders("hit", "cog", strings);
        System.out.println();
    }
}

// 126. 单词接龙 II
/*
按字典 wordList 完成从单词 beginWord 到单词 endWord 转化，一个表示此过程的 转换序列 是形式上像 beginWord -> s1 -> s2 -> ... -> sk 这样的单词序列，并满足：

每对相邻的单词之间仅有单个字母不同。
转换过程中的每个单词 si（1 <= i <= k）必须是字典 wordList 中的单词。注意，beginWord 不必是字典 wordList 中的单词。sk == endWord
给你两个单词 beginWord 和 endWord ，以及一个字典 wordList 。请你找出并返回所有从 beginWord 到 endWord 的 最短转换序列 ，如果不存在这样的转换序列，返回一个空列表。
每个序列都应该以单词列表 [beginWord, s1, s2, ..., sk] 的形式返回。

示例 1：
输入：beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
输出：[["hit","hot","dot","dog","cog"],["hit","hot","lot","log","cog"]]
解释：存在 2 种最短的转换序列：
"hit" -> "hot" -> "dot" -> "dog" -> "cog"
"hit" -> "hot" -> "lot" -> "log" -> "cog"

示例 2：
输入：beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log"]
输出：[]
解释：endWord "cog" 不在字典 wordList 中，所以不存在符合要求的转换序列。

查找字典树
 */
class findLadders {
    List<List<String>> result = new ArrayList<>();
    int minSize = Integer.MAX_VALUE;

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        if (!wordList.contains(endWord)) {
            return null;
        }
        wordList.add(beginWord);
        HashMap<String, List<String>> stringListHashMap = buildGraphic(wordList);
        dfs(stringListHashMap, new HashSet<>(), new ArrayList<>(), beginWord, endWord);
        return this.result;
    }

    public void dfs(HashMap<String, List<String>> stringListHashMap, HashSet<String> exist, List<String> array, String curr, String endWord) {

        if (curr.equals(endWord)) {
            if (array.size() > minSize) {
                return;
            } else if (array.size() == minSize) {
                this.result.add(new ArrayList<>(array));
            } else {
                this.result.clear();
                this.result.add(new ArrayList<>(array));
                this.minSize = array.size();
            }
        }

        List<String> strings = stringListHashMap.get(curr);
        for (String s : strings) {
            if (exist.contains(s)) {
                continue;
            }
            exist.add(s);
            array.add(s);
            dfs(stringListHashMap, exist, array, s, endWord);
            exist.remove(s);
            array.remove(array.size() - 1);
        }
    }

    public HashMap<String, List<String>> buildGraphic(List<String> wordList) {
        HashSet<String> wordSet = new HashSet<>();
        for (int i = 0; i < wordList.size(); i++) {
            String curr = wordList.get(i);
            if (wordSet.contains(curr)) {
                wordList.remove(i);
            } else {
                wordSet.add(curr);
            }
        }


        HashMap<String, List<String>> graphic = new HashMap<>();
        for (int i = 0; i < wordList.size(); i++) {
            String target = wordList.get(i);
            int j = i + 1;
            while (j < wordList.size()) {
                String temp = wordList.get(j);
                if (check(target, temp)) {
                    List<String> targetList = graphic.getOrDefault(target, new ArrayList<>());
                    targetList.add(temp);
                    graphic.put(target, targetList);

                    List<String> tempList = graphic.getOrDefault(temp, new ArrayList<>());
                    tempList.add(target);
                    graphic.put(temp, tempList);
                }
                j++;
            }

        }

        return graphic;
    }

    public boolean check(String target, String temp) {
        if (target.length() != temp.length()) {
            return false;
        }
        int diff = 0;
        for (int i = 0; i < target.length(); i++) {
            diff += target.charAt(i) != temp.charAt(i) ? 1 : 0;
        }

        return diff == 1;
    }
}