package com.example.demo.algorithm.year24.demo2412;


import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Demo1223 {
    public static void main(String[] args) {
//        int i = new ladderLength().ladderLength("hit", "cog", Arrays.asList(new String[]{"hot", "dot", "dog", "lot", "log", "cog"}));

        int i1 = new snakesAndLadders().snakesAndLadders(new int[][]{
                {-1, -1, -1, -1, -1, -1},
                {-1, -1, -1, -1, -1, -1},
                {-1, -1, -1, -1, -1, -1},
                {-1, 35, -1, -1, 13, -1},
                {-1, -1, -1, -1, -1, -1},
                {-1, 15, -1, -1, -1, -1}});
        System.out.println();
    }
}


// 909
/*
给你一个大小为 n x n 的整数矩阵 board ，方格按从 1 到 n2 编号，编号遵循 转行交替方式 ，从左下角开始 （即，从 board[n - 1][0] 开始）的每一行改变方向。
你一开始位于棋盘上的方格  1。每一回合，玩家需要从当前方格 curr 开始出发，按下述要求前进：
选定目标方格 next ，目标方格的编号在范围 [curr + 1, min(curr + 6, n2)] 。
该选择模拟了掷 六面体骰子 的情景，无论棋盘大小如何，玩家最多只能有 6 个目的地。
传送玩家：如果目标方格 next 处存在蛇或梯子，那么玩家会传送到蛇或梯子的目的地。否则，玩家传送到目标方格 next 。
当玩家到达编号 n2 的方格时，游戏结束。
如果 board[r][c] != -1 ，位于 r 行 c 列的棋盘格中可能存在 “蛇” 或 “梯子”。那个蛇或梯子的目的地将会是 board[r][c]。编号为 1 和 n2 的方格不是任何蛇或梯子的起点。
注意，玩家在每次掷骰的前进过程中最多只能爬过蛇或梯子一次：就算目的地是另一条蛇或梯子的起点，玩家也 不能 继续移动。
举个例子，假设棋盘是 [[-1,4],[-1,3]] ，第一次移动，玩家的目标方格是 2 。那么这个玩家将会顺着梯子到达方格 3 ，但 不能 顺着方格 3 上的梯子前往方格 4 。
（简单来说，类似飞行棋，玩家掷出骰子点数后移动对应格数，遇到单向的路径（即梯子或蛇）可以直接跳到路径的终点，但如果多个路径首尾相连，也不能连续跳多个路径）
返回达到编号为 n2 的方格所需的最少掷骰次数，如果不可能，则返回 -1。

输入：board = [
[-1,-1,-1,-1,-1,-1],
[-1,-1,-1,-1,-1,-1],
[-1,-1,-1,-1,-1,-1],
[-1,35,-1,-1,13,-1],
[-1,-1,-1,-1,-1,-1],
[-1,15,-1,-1,-1,-1]]
board = [
[36,35,34,33,32,31],
[25,26,27,28,29,30],
[24,23,22,21,20,19],
[13,14,15,16,17,18],
[12,11,10, 9, 8, 7],
[1 ,2 ,3 , 4, 5, 6]]
输出：4
解释：
首先，从方格 1 [第 5 行，第 0 列] 开始。
先决定移动到方格 2 ，并必须爬过梯子移动到到方格 15 。
然后决定移动到方格 17 [第 3 行，第 4 列]，必须爬过蛇到方格 13 。
接着决定移动到方格 14 ，且必须通过梯子移动到方格 35 。
最后决定移动到方格 36 , 游戏结束。
可以证明需要至少 4 次移动才能到达最后一个方格，所以答案是 4 。
示例 2：

输入：board = [
[-1,-1],
[-1,3]]
输出：1


提示：
n == board.length == board[i].length
2 <= n <= 20
board[i][j] 的值是 -1 或在范围 [1, n2] 内
编号为 1 和 n2 的方格上没有蛇或梯子
 */
class snakesAndLadders {
    private Map<Integer, List<Integer>> relation = new HashMap<>();

    public int snakesAndLadders(int[][] board) {
        int rowLength = board.length;
        int colLength = board[0].length;
        if (rowLength == 0 || colLength == 0) {
            return 0;
        }
        addRelation(board);

        ArrayDeque<Integer> arrayDeque = new ArrayDeque<>();
        HashSet<Integer> exist = new HashSet<>();

        arrayDeque.add(1);
        exist.add(1);

        ArrayDeque<Integer> temp = new ArrayDeque<>();
        int count = 0;
        while (!arrayDeque.isEmpty()) {
            Integer poll = arrayDeque.poll();
            exist.add(poll);
            if (poll == (rowLength) * (colLength)) {
                return count;
            }

            List<Integer> collect = relation.get(poll).stream().filter(new Predicate<Integer>() {
                @Override
                public boolean test(Integer integer) {
                    return !exist.contains(integer);
                }
            }).collect(Collectors.toList());

            temp.addAll(collect);
            if (arrayDeque.isEmpty()) {
                count++;
                arrayDeque = temp;
                temp = new ArrayDeque<>();
            }
        }
        return -1;
    }

    public void addRelation(int[][] board) {
        int rowLength = board.length;
        int colLength = board[0].length;
        int index = 1;
        int i = rowLength - 1, j = 0;
        boolean dir = true;

        do {
            ArrayList<Integer> value = new ArrayList<>();
            if (board[i][j] != -1) {
                value.add(board[i][j]);
            }
            for (int s = 1; s <= rowLength; s++) {
                value.add(Math.min(index + s, rowLength * rowLength));
            }
            relation.put(index, value);
            index++;
            j = dir ? j + 1 : j - 1;
            if (j == colLength) {
                dir = false;
                j--;
                i--;
            }
            if (j == -1) {
                dir = true;
                j++;
                i--;
            }
        } while (i != -1);

    }
}


// 433
/*
基因序列可以表示为一条由 8 个字符组成的字符串，其中每个字符都是 'A'、'C'、'G' 和 'T' 之一。
假设我们需要调查从基因序列 start 变为 end 所发生的基因变化。一次基因变化就意味着这个基因序列中的一个字符发生了变化。
例如，"AACCGGTT" --> "AACCGGTA" 就是一次基因变化。
另有一个基因库 bank 记录了所有有效的基因变化，只有基因库中的基因才是有效的基因序列。（变化后的基因必须位于基因库 bank 中）
给你两个基因序列 start 和 end ，以及一个基因库 bank ，请你找出并返回能够使 start 变化为 end 所需的最少变化次数。如果无法完成此基因变化，返回 -1 。
注意：起始基因序列 start 默认是有效的，但是它并不一定会出现在基因库中

输入：start = "AACCGGTT", end = "AACCGGTA", bank = ["AACCGGTA"]
输出：1

示例 2：
输入：start = "AACCGGTT", end = "AAACGGTA", bank = ["AACCGGTA","AACCGCTA","AAACGGTA"]
输出：2

示例 3：
输入：start = "AAAAACCC", end = "AACCCCCC", bank = ["AAAACCCC","AAACCCCC","AACCCCCC"]
输出：3

"AAAACCCC"
"CCCCCCCC"

["AAAACCCA","AAACCCCA","AACCCCCA","AACCCCCC","ACCCCCCC","CCCCCCCC","AAACCCCC","AACCCCCC"]

start.length == 8
end.length == 8
0 <= bank.length <= 10
bank[i].length == 8
start、end 和 bank[i] 仅由字符 ['A', 'C', 'G', 'T'] 组成
 */
class minMutation {
    private int min = -1;

    public int minMutation(String startGene, String endGene, String[] bank) {
        if (startGene.length() != endGene.length()) {
            return -1;
        }
        if (bank.length == 0 || !Arrays.stream(bank).toList().contains(endGene)) {
            return -1;
        }
        HashSet<String> exist = new HashSet<>();
        exist.add(startGene);
        dfs(startGene, endGene, bank, exist);
        return this.min;
    }

    public void dfs(String startGene, String endGene, String[] bank, HashSet<String> exist) {
        if (checkNext(startGene, endGene)) {
            if (this.min == -1 || this.min > exist.size()) {
                this.min = exist.size();
            }
            return;
        }

        for (String item : bank) {
            if (exist.contains(item)) {
                continue;
            }
            if (checkNext(startGene, item)) {
                exist.add(item);
                dfs(item, endGene, bank, exist);
                exist.remove(item);
            }
        }

    }

    public boolean checkNext(String raw, String target) {
        if (target.length() != raw.length()) {
            return false;
        }
        char[] rawChars = raw.toCharArray();
        char[] targetChars = target.toCharArray();
        boolean isNext = false;
        boolean first = false;
        for (int i = 0; i < raw.length(); i++) {
            if (rawChars[i] != targetChars[i]) {
                if (first) {
                    return false;
                }
                first = true;
                isNext = true;
            }
        }

        return isNext;
    }
}


// 127
/*
字典 wordList 中从单词 beginWord 到 endWord 的 转换序列 是一个按下述规格形成的序列 beginWord -> s1 -> s2 -> ... -> sk：

每一对相邻的单词只差一个字母。
 对于 1 <= i <= k 时，每个 si 都在 wordList 中。注意， beginWord 不需要在 wordList 中。
sk == endWord
给你两个单词 beginWord 和 endWord 和一个字典 wordList ，返回 从 beginWord 到 endWord 的 最短转换序列 中的 单词数目 。如果不存在这样的转换序列，返回 0 。

输入：beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
输出：5
解释：一个最短转换序列是 "hit" -> "hot" -> "dot" -> "dog" -> "cog", 返回它的长度 5。

示例 2：
输入：beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log"]
输出：0
解释：endWord "cog" 不在字典中，所以无法进行转换。

1 <= beginWord.length <= 10
endWord.length == beginWord.length
1 <= wordList.length <= 5000
wordList[i].length == beginWord.length
beginWord、endWord 和 wordList[i] 由小写英文字母组成
beginWord != endWord
wordList 中的所有字符串 互不相同
 */

class ladderLength {
    private int min = 0;

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (beginWord.length() != endWord.length()) {
            return 0;
        }
        if (wordList.size() == 0 || !wordList.contains(endWord)) {
            return 0;
        }
        HashSet<String> exist = new HashSet<>();
        exist.add(beginWord);
        dfs(beginWord, endWord, wordList, exist);
        return this.min;
    }

    public void dfs(String startGene, String endGene, List<String> wordList, HashSet<String> exist) {
        if (exist.size() > this.min && this.min > 0) {
            return;
        }
        if (checkNext(startGene, endGene)) {
            if (this.min == 0 || this.min > exist.size() + 1) {
                this.min = exist.size() + 1;
            }
            return;
        }

        for (String item : wordList) {
            if (exist.contains(item)) {
                continue;
            }
            if (checkNext(startGene, item)) {
                exist.add(item);
                dfs(item, endGene, wordList, exist);
                exist.remove(item);
            }
        }

    }

    public boolean checkNext(String raw, String target) {
        char[] rawChars = raw.toCharArray();
        char[] targetChars = target.toCharArray();
        boolean isNext = false;
        boolean first = false;
        for (int i = 0; i < raw.length(); i++) {
            if (rawChars[i] != targetChars[i]) {
                if (first) {
                    return false;
                }
                first = true;
                isNext = true;
            }
        }

        return isNext;
    }
}


// 答案
//class ladderLength {
//    Map<String, Integer> wordId = new HashMap<String, Integer>();
//    List<List<Integer>> edge = new ArrayList<List<Integer>>();
//    int nodeNum = 0;
//
//    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
//        for (String word : wordList) {
//            addEdge(word);
//        }
//        addEdge(beginWord);
//        if (!wordId.containsKey(endWord)) {
//            return 0;
//        }
//        int[] dis = new int[nodeNum];
//        Arrays.fill(dis, Integer.MAX_VALUE);
//        int beginId = wordId.get(beginWord), endId = wordId.get(endWord);
//        dis[beginId] = 0;
//
//        Queue<Integer> que = new LinkedList<Integer>();
//        que.offer(beginId);
//        while (!que.isEmpty()) {
//            int x = que.poll();
//            if (x == endId) {
//                return dis[endId] / 2 + 1;
//            }
//            for (int it : edge.get(x)) {
//                if (dis[it] == Integer.MAX_VALUE) {
//                    dis[it] = dis[x] + 1;
//                    que.offer(it);
//                }
//            }
//        }
//        return 0;
//    }
//
//    public void addEdge(String word) {
//        addWord(word);
//        int id1 = wordId.get(word);
//        char[] array = word.toCharArray();
//        int length = array.length;
//        for (int i = 0; i < length; ++i) {
//            char tmp = array[i];
//            array[i] = '*';
//            String newWord = new String(array);
//            addWord(newWord);
//            int id2 = wordId.get(newWord);
//            edge.get(id1).add(id2);
//            edge.get(id2).add(id1);
//            array[i] = tmp;
//        }
//    }
//
//    public void addWord(String word) {
//        if (!wordId.containsKey(word)) {
//            wordId.put(word, nodeNum++);
//            edge.add(new ArrayList<Integer>());
//        }
//    }
//}
