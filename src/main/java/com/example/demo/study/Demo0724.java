package com.example.demo.study;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Stack;

public class Demo0724 {

    public static void main(String[] args) {

        numIslands numIslands = new numIslands();
        char[][] temp = {{'1', '1', '1'},
            {'0', '1', '0'},
            {'1', '1', '1'}};
        int insert1 = numIslands.numIslands(temp);
        System.out.println();

    }

}

class insert {
    //给你一个 无重叠的 ，按照区间起始端点排序的区间列表 intervals，其中 intervals[i] = [starti, endi] 表示第 i 个区间的开始和结束，并且 intervals 按照 starti 升序排列。同样给定一个区间 newInterval = [start, end] 表示另一个区间的开始和结束。
    //
    //在 intervals 中插入区间 newInterval，使得 intervals 依然按照 starti 升序排列，且区间之间不重叠（如果有必要的话，可以合并区间）。
    //
    //返回插入之后的 intervals。
    //
    //注意 你不需要原地修改 intervals。你可以创建一个新数组然后返回它。

    //示例 1：
    //
    //输入：intervals = [[1,3],[6,9]], newInterval = [2,5]
    //输出：[[1,5],[6,9]]
    //示例 2：
    //
    //输入：intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
    //输出：[[1,2],[3,10],[12,16]]
    //解释：这是因为新的区间 [4,8] 与 [3,5],[6,7],[8,10] 重叠。

    //0 <= intervals.length <= 104
    //intervals[i].length == 2
    //0 <= starti <= endi <= 105
    //intervals 根据 starti 按 升序 排列
    //newInterval.length == 2
    //0 <= start <= end <= 105

    // 双指针
    public int[][] insert(int[][] intervals, int[] newInterval) {
        if (intervals.length == 0) {
            int[][] ints = new int[1][2];
            ints[0] = newInterval;
            return ints;
        }
        int i = 0, j = 0;
        ArrayList<tempVal> tempVals = new ArrayList<>(intervals.length + 1);
        tempVals.add(new tempVal(newInterval[0], newInterval[1]));
        while (j < intervals.length) {
            tempVal tempVal = tempVals.get(i);
            if (tempVal.right < intervals[j][0]) {
                //在当前对比区间的左边，不合并
                tempVals.add(new tempVal(intervals[j][0], intervals[j][1]));
                i++;
                j++;
            } else if (tempVal.left > intervals[j][1]) {
                //在当前对比区间的右边，不合并
                tempVals.set(i, new tempVal(intervals[j][0], intervals[j][1]));
                tempVals.add(tempVal);
                i++;
                j++;
            } else {
                // 合并
                tempVal.left = Math.min(tempVal.left, intervals[j][0]);
                tempVal.right = Math.max(tempVal.right, intervals[j][1]);
                tempVals.set(i, tempVal);
                j++;
            }
        }

        int[][] res = new int[tempVals.size()][2];
        for (int index = 0; index < tempVals.size(); index++) {
            res[index] = new int[]{tempVals.get(index).left, tempVals.get(index).right};
        }

        return res;
    }
}

class tempVal {
    int left;
    int right;

    public tempVal(int left, int right) {
        this.left = left;
        this.right = right;
    }
}

class evalRPN {
    //给你一个字符串数组 tokens ，表示一个根据 逆波兰表示法 表示的算术表达式。
    //
    //请你计算该表达式。返回一个表示表达式值的整数。
    //注意：
    //有效的算符为 '+'、'-'、'*' 和 '/' 。
    //每个操作数（运算对象）都可以是一个整数或者另一个表达式。
    //两个整数之间的除法总是 向零截断 。
    //表达式中不含除零运算。
    //输入是一个根据逆波兰表示法表示的算术表达式。
    //答案及所有中间计算结果可以用 32 位 整数表示。

    //    示例 1：
    //    输入：tokens = ["2","1","+","3","*"]
    //    输出：9
    //    解释：该算式转化为常见的中缀算术表达式为：((2 + 1) * 3) = 9

    //    示例 2：
    //    输入：tokens = ["4","13","5","/","+"]
    //    输出：6
    //    解释：该算式转化为常见的中缀算术表达式为：(4 + (13 / 5)) = 6

    //    示例 3：
    //    输入：tokens = ["10","6","9","3","+","-11","*","/","*","17","+","5","+"]
    //    输出：22
    //    解释：该算式转化为常见的中缀算术表达式为：
    //        ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
    //        = ((10 * (6 / (12 * -11))) + 17) + 5
    //        = ((10 * (6 / -132)) + 17) + 5
    //        = ((10 * 0) + 17) + 5
    //        = (0 + 17) + 5
    //        = 17 + 5
    //        = 22


    public int evalRPN(String[] tokens) {

        if (tokens.length == 0) {
            return 0;
        }

        Stack<Integer> stringStack = new Stack<>();
        for (String s : tokens) {
            if (s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/")) {
                Integer interAfter = stringStack.pop();
                Integer interBefore = stringStack.pop();
                Integer tempVal;
                if (s.equals("+")) {
                    tempVal = interBefore + interAfter;
                } else if (s.equals("-")) {
                    tempVal = interBefore - interAfter;
                } else if (s.equals("*")) {
                    tempVal = interBefore * interAfter;
                } else {
                    tempVal = interBefore / interAfter;
                }
                stringStack.push(tempVal);
            } else {
                stringStack.push(Integer.valueOf(s));
            }
        }


        return stringStack.pop();

    }
}


class numIslands {
    //给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
    //
    //岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
    //
    //此外，你可以假设该网格的四条边均被水包围。

    //示例 1：
    //
    //输入：grid = [
    //  ["1","1","1","1","0"],
    //  ["1","1","0","1","0"],
    //  ["1","1","0","0","0"],
    //  ["0","0","0","0","0"]
    //]
    //输出：1


    //示例 2：
    //
    //输入：grid = [
//      ["1","1","0","0","0"],
//      ["1","1","0","0","0"],
//      ["0","0","1","0","0"],
//      ["0","0","0","1","1"]
    //]
    //输出：3
//    [["1","1","1"],
//        ["0","1","0"],
//        ["1","1","1"]]

    //广度优先遍历
    public int numIslands(char[][] grid) {

        int isLandNum = 0;

        if (grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        HashSet<String> stringHashSet = new HashSet<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '0') {
                    stringHashSet.add(i + "-" + j);
                    continue;
                }
                if (stringHashSet.contains(i + "-" + j)) {
                    continue;
                }

                isLandNum++;
                stringHashSet.add(i + "-" + j);

                //加入队列
                //队列不为空，向四周找出范围
                ArrayDeque<int[]> ints = new ArrayDeque<>();
                ints.add(new int[]{i, j});
                while (!ints.isEmpty()) {
                    int[] pop = ints.pop();
                    int x = pop[0];
                    int y = pop[1];
                    stringHashSet.add(x + "-" + y);

                    if (x + 1 < grid.length && grid[x + 1][y] == '1') {
                        if (!stringHashSet.contains(String.valueOf(x + 1) + "-" + y)) {
                            ints.add(new int[]{x + 1, y});
                        }
                    }
                    if (y + 1 < grid[0].length && grid[x][y + 1] == '1') {
                        if (!stringHashSet.contains(x + "-" + String.valueOf(y + 1))) {
                            ints.add(new int[]{x, y + 1});
                        }
                    }

                    if (x - 1 >= 0 && grid[x - 1][y] == '1') {
                        if (!stringHashSet.contains(String.valueOf(x - 1) + "-" + y)) {
                            ints.add(new int[]{x - 1, y});
                        }
                    }
                    if (y - 1 >= 0 && grid[x][y - 1] == '1') {
                        if (!stringHashSet.contains(x + "-" + String.valueOf(y - 1))) {
                            ints.add(new int[]{x, y - 1});
                        }
                    }
                }


            }
        }

        return isLandNum;
    }
}


