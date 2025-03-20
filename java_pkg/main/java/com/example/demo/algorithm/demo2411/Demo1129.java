package com.example.demo.algorithm.demo2411;

import java.util.*;

public class Demo1129 {

    public static void main(String[] args) {
        List<Integer> result = new findSubstring().findSubstring("ling mind raboo fooo wing ding barr wingmonkeypoundcake", new String[]{"fooo","barr","wing","ding","wing"});
        System.out.println();
    }


}

//30
/*
给定一个字符串 s 和一个字符串数组 words。 words 中所有字符串 长度相同。
s 中的 串联子串 是指一个包含  words 中所有字符串以任意顺序排列连接起来的子串。
例如，如果 words = ["ab","cd","ef"]， 那么 "abcdef"， "abefcd"，"cdabef"， "cdefab"，"efabcd"， 和 "efcdab" 都是串联子串。
"acdbef" 不是串联子串，因为他不是任何 words 排列的连接。
返回所有串联子串在 s 中的开始索引。你可以以 任意顺序 返回答案

示例 1：
输入：s = "barfoothefoobarman", words = ["foo","bar"]
输出：[0,9]
解释：因为 words.length == 2 同时 words[i].length == 3，连接的子字符串的长度必须为 6。
子串 "barfoo" 开始位置是 0。它是 words 中以 ["bar","foo"] 顺序排列的连接。
子串 "foobar" 开始位置是 9。它是 words 中以 ["foo","bar"] 顺序排列的连接。
输出顺序无关紧要。返回 [9,0] 也是可以的。

示例 2：
输入：s = "wordgoodgoodgoodbestword", words = ["word","good","best","word"]
输出：[]
解释：因为 words.length == 4 并且 words[i].length == 4，所以串联子串的长度必须为 16。
s 中没有子串长度为 16 并且等于 words 的任何顺序排列的连接。
所以我们返回一个空数组。

示例 3：
输入：s = "barfoofoobarthefoobarman", words = ["bar","foo","the"]
输出：[6,9,12]
解释：因为 words.length == 3 并且 words[i].length == 3，所以串联子串的长度必须为 9。
子串 "foobarthe" 开始位置是 6。它是 words 中以 ["foo","bar","the"] 顺序排列的连接。
子串 "barthefoo" 开始位置是 9。它是 words 中以 ["bar","the","foo"] 顺序排列的连接。
子串 "thefoobar" 开始位置是 12。它是 words 中以 ["the","foo","bar"] 顺序排列的连接。

 */
class findSubstring {
    private Map<String, Integer> cntMap;

    public List<Integer> findSubstring(String s, String[] words) {

        int wordsLength = words.length;
        int stringLength = s.length();
        List<Integer> result = new ArrayList<>();
        if (wordsLength == 0) {
            return result;
        }
        this.cntMap = new HashMap<>();
        for (String word : words) {
            this.cntMap.put(word, this.cntMap.getOrDefault(word, 0) + 1);
        }
        int wordLength = words[0].length();

        int start = 0;
        int end = wordsLength * wordLength;
        String oldString = "";
        String newString = "";

        while (end <= stringLength) {
            newString = s.substring(start, end);
            if (checkSubString(oldString, newString, wordLength)) {
                result.add(start);
            }
            oldString = newString;
            start += wordLength;
            end += wordLength;
        }

        return result;
    }

    public boolean checkSubString(String oldString, String newString, int wordLength) {
        int newStringLength = newString.length();
        if (oldString.equals("")) {
            for (int i = 0; i < newStringLength; i += wordLength) {
                String item = newString.substring(i, i + wordLength);
                if (cntMap.containsKey(item)) {
                    this.cntMap.put(item, this.cntMap.get(item) - 1);
                }
            }
        } else {
            String outString = oldString.substring(0, wordLength);
            String inString = newString.substring(newStringLength - wordLength, newStringLength);
            if (this.cntMap.containsKey(inString)) {
                this.cntMap.put(inString, this.cntMap.getOrDefault(inString, 0) - 1);
            }
            if (this.cntMap.containsKey(outString)) {
                this.cntMap.put(outString, this.cntMap.getOrDefault(outString, 0) + 1);
            }
        }


        return checkCntMap();
    }

    public boolean checkCntMap() {
        for (String key : this.cntMap.keySet()) {
            if (this.cntMap.get(key) != 0) {
                return false;
            }
        }

        return true;
    }
}


// 48
/*
给定一个 n × n 的二维矩阵 matrix 表示一个图像。请你将图像顺时针旋转 90 度。
你必须在 原地 旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要 使用另一个矩阵来旋转图像。

输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
输出：[[7,4,1],[8,5,2],[9,6,3]]

输入：matrix = [[5,1,9,11],[2,4,8,10],[13,3,6,7],[15,14,12,16]]
输出：[[15,13,2,5],[14,3,4,1],[12,6,8,9],[16,7,10,11]]
 */

class rotate {
    public void rotate(int[][] matrix) {

    }
}


// 289
/*
生命游戏 ，简称为 生命 ，是英国数学家约翰·何顿·康威在 1970 年发明的细胞自动机。
给定一个包含 m × n 个格子的面板，每一个格子都可以看成是一个细胞。每个细胞都具有一个初始状态： 1 即为 活细胞 （live），或 0 即为 死细胞 （dead）。
每个细胞与其八个相邻位置（水平，垂直，对角线）的细胞都遵循以下四条生存定律：

如果活细胞周围八个位置的活细胞数少于两个，则该位置活细胞死亡；
如果活细胞周围八个位置有两个或三个活细胞，则该位置活细胞仍然存活；
如果活细胞周围八个位置有超过三个活细胞，则该位置活细胞死亡；
如果死细胞周围正好有三个活细胞，则该位置死细胞复活；
下一个状态是通过将上述规则同时应用于当前状态下的每个细胞所形成的，其中细胞的出生和死亡是 同时 发生的。给你 m x n 网格面板 board 的当前状态，返回下一个状态。
给定当前 board 的状态，更新 board 到下一个状态。

注意 你不需要返回任何东西。
输入：board = [[0,1,0],[0,0,1],[1,1,1],[0,0,0]]
输出：[[0,0,0],[1,0,1],[0,1,1],[0,1,0]]

输入：board = [[1,1],[1,0]]
输出：[[1,1],[1,1]]
 */

class gameOfLife {
    public void gameOfLife(int[][] board) {



    }
}