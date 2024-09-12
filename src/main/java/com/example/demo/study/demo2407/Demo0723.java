package com.example.demo.study.demo2407;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

public class Demo0723 {

    public static void main(String[] args) {
        ladderLength ladderLength = new ladderLength();
        List<String> strings = Arrays.asList(new String[]{"hot","dot","dog","lot","log","cog"});
        int i = ladderLength.ladderLength("hit", "cog", strings);
        System.out.println(i);
    }

}


class ladderLength {

    // 字典 wordList 中从单词 beginWord 到 endWord 的 转换序列 是一个按下述规格形成的序列 beginWord -> s1 -> s2 -> ... -> sk：
    //
    //    每一对相邻的单词只差一个字母。
    //    对于 1 <= i <= k 时，每个 si 都在 wordList 中。注意， beginWord 不需要在 wordList 中。
    //    sk == endWord
    //    给你两个单词 beginWord 和 endWord 和一个字典 wordList ，返回 从 beginWord 到 endWord 的 最短转换序列 中的 单词数目 。如果不存在这样的转换序列，返回 0

    //输入：beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
    //输出：5
    //解释：一个最短转换序列是 "hit" -> "hot" -> "dot" -> "dog" -> "cog", 返回它的长度 5。

    //输入：beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log"]
    //输出：0
    //解释：endWord "cog" 不在字典中，所以无法进行转换


    //1 <= beginWord.length <= 10
    //endWord.length == beginWord.length
    //1 <= wordList.length <= 5000
    //wordList[i].length == beginWord.length
    //beginWord、endWord 和 wordList[i] 由小写英文字母组成
    //beginWord != endWord
    //wordList 中的所有字符串 互不相同

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        HashMap<String, Boolean> stringBooleanHashMap = new HashMap<>();
        PriorityQueue<Integer> integers = new PriorityQueue<>();
        ladderLength(beginWord, endWord, wordList, stringBooleanHashMap, integers);
        return integers.peek();
    }


    public void ladderLength(String beginWord, String endWord, List<String> wordList, HashMap<String, Boolean> stringBooleanHashMap, PriorityQueue<Integer> integers) {
        stringBooleanHashMap.put(beginWord, true);
        for (String s : wordList) {

            if (stringBooleanHashMap.containsKey(s)) {
                continue;
            }

            if (check(beginWord, s)) {
                if (s.equals(endWord)) {
                    stringBooleanHashMap.put(endWord, true);
                    integers.add(stringBooleanHashMap.size());
                    stringBooleanHashMap.remove(endWord);
                    stringBooleanHashMap.remove(beginWord);
                    return;
                }
                ladderLength(s, endWord, wordList, stringBooleanHashMap, integers);
            }

        }
        stringBooleanHashMap.remove(beginWord);
    }

    public boolean check(String word1, String word2) {
        if (word1.length() != word2.length()) {
            return false;
        }
        int diff = 0;

        char[] chars1 = word1.toCharArray();
        char[] chars2 = word2.toCharArray();

        for (int i = 0; i < chars1.length; i++) {
            if (chars1[i] != chars2[i]) {
                if (diff == 0) {
                    diff = 1;
                } else {
                    return false;
                }
            }
        }
        return true;
    }
}


