package com.example.demo.algorithm.year24.demo2412;


import java.util.*;

public class Demo1216 {

    public static void main(String[] args) {
        List<List<String>> equations = new ArrayList<>();
        equations.add(Arrays.asList("a", "b"));
        equations.add(Arrays.asList("b", "c"));

        List<List<String>> queries = new ArrayList<>();
        queries.add(Arrays.asList("a", "c"));
        queries.add(Arrays.asList("b", "a"));
        queries.add(Arrays.asList("a", "e"));
        queries.add(Arrays.asList("a", "a"));
        queries.add(Arrays.asList("X", "X"));
        queries.add(Arrays.asList("b", "c"));
        queries.add(Arrays.asList("c", "b"));

        double[] doubles = new calcEquation().calcEquation(equations, new double[]{2.0, 3.0}, queries);
        System.out.println();
    }


}

// 130
/*
给你一个 m x n 的矩阵 board ，由若干字符 'X' 和 'O' 组成，捕获 所有 被围绕的区域：

连接：一个单元格与水平或垂直方向上相邻的单元格连接。
区域：连接所有 'O' 的单元格来形成一个区域。
围绕：如果您可以用 'X' 单元格 连接这个区域，并且区域中没有任何单元格位于 board 边缘，则该区域被 'X' 单元格围绕。
通过将输入矩阵 board 中的所有 'O' 替换为 'X' 来 捕获被围绕的区域。


输入：board = [['X','X','X','X'],
              ['X','O','O','X'],
              ['X','X','O','X'],
              ['X','O','X','X']]
输出：[['X','X','X','X'],
      ['X','X','X','X'],
      ['X','X','X','X'],
      ['X','O','X','X']]

输入：board = [['X']]
输出：[['X']]


[['O','X','X','O','X'],
 ['X','O','O','X','O'],
 ['X','O','X','O','X'],
 ['O','X','O','O','O'],
 ['X','X','O','X','O']]


 [['O','X','X','O','X'],
  ['X','X','X','X','O'],
  ['X','X','X','O','X'],
  ['O','X','O','O','O'],
  ['X','X','O','X','O']]

 [['O','X','X','O','X'],
  ['X','X','X','X','O'],
  ['X','X','X','X','X'],
  ['O','X','O','O','O'],
  ['X','X','O','X','O']]




  [['O','X','O','O','O','X'],
   ['O','O','X','X','X','O'],
   ['X','X','X','X','X','O'],
   ['O','O','O','O','X','X'],
   ['X','X','O','O','X','O'],
   ['O','O','X','X','X','X']]



   [['O','X','O','O','O','X'],
    ['O','O','X','X','X','O'],
    ['X','X','X','X','X','O'],
    ['O','O','O','X','X','X'],
    ['X','X','X','X','X','O'],
    ['O','O','X','X','X','X']]

    [['O','X','O','O','O','X'],
     ['O','O','X','X','X','O'],
     ['X','X','X','X','X','O'],
     ['O','O','O','O','X','X'],
     ['X','X','O','O','X','O'],
     ['O','O','X','X','X','X']]
 */

class solve {
    private int rowLength = 0;
    private int colLength = 0;

    public void solve(char[][] board) {
        this.rowLength = board.length;
        if (this.rowLength == 0) {
            return;
        }
        this.colLength = board[0].length;
        if (this.colLength == 0) {
            return;
        }

        HashSet<String> oBoundPosition = new HashSet<>();
        for (int i = 0; i < this.rowLength; i++) {
            dfs(i, 0, board, oBoundPosition);
            dfs(i, this.colLength - 1, board, oBoundPosition);
        }
        for (int i = 0; i < this.colLength; i++) {
            dfs(0, i, board, oBoundPosition);
            dfs(this.rowLength - 1, i, board, oBoundPosition);
        }
        for (int x = 0; x < this.rowLength; x++) {
            for (int y = 0; y < this.colLength; y++) {
                if (board[x][y] == 'O' && !oBoundPosition.contains(x + ":" + y)) {
                    board[x][y] = 'X';
                }
            }
        }

    }

    public void dfs(int x, int y, char[][] board, HashSet<String> oPosition) {
        if (checkOutOfBound(x, y)) {
            return;
        }
        if (board[x][y] != 'O') {
            return;
        }
        if (oPosition.contains(x + ":" + y)) {
            return;
        }
        oPosition.add(x + ":" + y);
        dfs(x - 1, y, board, oPosition);
        dfs(x + 1, y, board, oPosition);
        dfs(x, y - 1, board, oPosition);
        dfs(x, y + 1, board, oPosition);
    }

    public boolean checkOutOfBound(int x, int y) {
        return x < 0 || x >= this.rowLength || y < 0 || y >= this.colLength;
    }

    public boolean checkBound(int x, int y) {
        return x == 0 || x == this.rowLength - 1 || y == 0 || y == this.colLength - 1;
    }
}


// 133
/*
给你无向 连通 图中一个节点的引用，请你返回该图的 深拷贝（克隆）。
图中的每个节点都包含它的值 val（int） 和其邻居的列表（list[Node]）。
class Node {
    public int val;
    public List<Node> neighbors;
}
测试用例格式：
简单起见，每个节点的值都和它的索引相同。例如，第一个节点值为 1（val = 1），第二个节点值为 2（val = 2），以此类推。该图在测试用例中使用邻接列表表示。
邻接列表 是用于表示有限图的无序列表的集合。每个列表都描述了图中节点的邻居集。
给定节点将始终是图中的第一个节点（值为 1）。你必须将 给定节点的拷贝 作为对克隆图的引用返回。

输入：adjList = [[2,4],[1,3],[2,4],[1,3]]
输出：[[2,4],[1,3],[2,4],[1,3]]
解释：
图中有 4 个节点。
节点 1 的值是 1，它有两个邻居：节点 2 和 4 。
节点 2 的值是 2，它有两个邻居：节点 1 和 3 。
节点 3 的值是 3，它有两个邻居：节点 2 和 4 。
节点 4 的值是 4，它有两个邻居：节点 1 和 3 。

输入：adjList = [[]]
输出：[[]]
解释：输入包含一个空列表。该图仅仅只有一个值为 1 的节点，它没有任何邻居。


输入：adjList = []
输出：[]
解释：这个图是空的，它不含任何节点。
 */

/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
*/
//class Node {
//    public int val;
//    public List<Node> neighbors;
//
//    public Node() {
//        val = 0;
//        neighbors = new ArrayList<Node>();
//    }
//
//    public Node(int _val) {
//        val = _val;
//        neighbors = new ArrayList<Node>();
//    }
//
//    public Node(int _val, ArrayList<Node> _neighbors) {
//        val = _val;
//        neighbors = _neighbors;
//    }
//}
//
//class cloneGraph {
//    public Node cloneGraph(Node node) {
//        if (node == null) {
//            return null;
//        }
//        ArrayDeque<Node> cloneGraphQueue = new ArrayDeque<>();
//        HashSet<Integer> exist = new HashSet<>();
//
//        HashMap<Node, List<Node>> NodeListHashMap = new HashMap<>();
//        HashMap<Integer, Node> cloneValNodeHashMap = new HashMap<>();
//
//        exist.add(node.val);
//        cloneGraphQueue.add(node);
//
//        while (!cloneGraphQueue.isEmpty()) {
//            Node pop = cloneGraphQueue.pop();
//            Node Node = new Node(pop.val);
//            NodeListHashMap.put(Node, pop.neighbors);
//            cloneValNodeHashMap.put(pop.val, Node);
//
//            for (Node item : pop.neighbors) {
//                if (!exist.contains(item.val)) {
//                    cloneGraphQueue.add(item);
//                    exist.add(item.val);
//                }
//            }
//        }
//
//        for (Node key : NodeListHashMap.keySet()) {
//            ArrayList<Node> newNeighbors = new ArrayList<>();
//            for (Node item : NodeListHashMap.get(key)) {
//                newNeighbors.add(cloneValNodeHashMap.get(item.val));
//            }
//            key.neighbors = newNeighbors;
//        }
//
//        return cloneValNodeHashMap.get(1);
//    }
//}

// 399
/*
给你一个变量对数组 equations 和一个实数值数组 values 作为已知条件，其中 equations[i] = [Ai, Bi] 和 values[i] 共同表示等式 Ai / Bi = values[i] 。
每个 Ai 或 Bi 是一个表示单个变量的字符串。
另有一些以数组 queries 表示的问题，其中 queries[j] = [Cj, Dj] 表示第 j 个问题，请你根据已知条件找出 Cj / Dj = ? 的结果作为答案。
返回 所有问题的答案 。如果存在某个无法确定的答案，则用 -1.0 替代这个答案。如果问题中出现了给定的已知条件中没有出现的字符串，也需要用 -1.0 替代这个答案。
注意：输入总是有效的。你可以假设除法运算中不会出现除数为 0 的情况，且不存在任何矛盾的结果。
注意：未在等式列表中出现的变量是未定义的，因此无法确定它们的答案。

a/b = 2  a = 2b = 6c
b/c = 3  b = 3c

a/c =
b/a =
a/e =
a/a =
X/X =

输入：equations = [["a","b"],["b","c"]], values = [2.0,3.0], queries = [["a","c"],["b","a"],["a","e"],["a","a"],['X','X']]
输出：[6.00000,0.50000,-1.00000,1.00000,-1.00000]
解释：
条件：a / b = 2.0, b / c = 3.0
问题：a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ?
结果：[6.0, 0.5, -1.0, 1.0, -1.0 ]
注意：x 是未定义的 => -1.0

输入：equations = [["a","b"],["b","c"],["bc","cd"]], values = [1.5,2.5,5.0], queries = [["a","c"],["c","b"],["bc","cd"],["cd","bc"]]
输出：[3.75000,0.40000,5.00000,0.20000]

输入：equations = [["a","b"]], values = [0.5], queries = [["a","b"],["b","a"],["a","c"],['X',"y"]]
输出：[0.50000,2.00000,-1.00000,-1.00000]
 */

class calcEquation {
    private HashSet<String> zeroSet = new HashSet<>();

    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        if (queries.size() == 0 || equations.size() == 0 || values.length == 0) {
            return new double[]{};
        }

        HashMap<String, HashMap<String, Double>> itemEquationMap = new HashMap<>();
        for (int i = 0; i < equations.size(); i++) {
            List<String> equation = equations.get(i);
            double result = values[i];
            if (result == 0) {
                this.zeroSet.add(equation.get(0));
            } else {
                HashMap<String, Double> orDefault = itemEquationMap.getOrDefault(equation.get(0), new HashMap<>());
                orDefault.put(equation.get(1), result);
                itemEquationMap.put(equation.get(0), orDefault);

                HashMap<String, Double> orDefault1 = itemEquationMap.getOrDefault(equation.get(1), new HashMap<>());
                orDefault1.put(equation.get(0), 1 / result);
                itemEquationMap.put(equation.get(1), orDefault1);
            }

        }

        double[] res = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            String first = queries.get(i).get(0);
            String second = queries.get(i).get(1);

            HashSet<String> scan = new HashSet<>();
            double dfs = dfs(first, second, itemEquationMap, scan);
            res[i] = dfs;
        }
        return res;
    }

    public double dfs(String first, String second, HashMap<String, HashMap<String, Double>> itemEquationMap, HashSet<String> scan) {

        if (!itemEquationMap.containsKey(first) || !itemEquationMap.containsKey(second)) {
            return -1;
        }
        if (this.zeroSet.contains(first)) {
            return 0;
        }
        if (first.equals(second)) {
            return 1;
        }

        HashMap<String, Double> stringDoubleHashMap = itemEquationMap.get(first);
        if (stringDoubleHashMap.containsKey(second)) {
            return stringDoubleHashMap.get(second);
        }

        scan.add(first);
        for (String key : stringDoubleHashMap.keySet()) {
            if (!scan.contains(key)) {
                double dfs = dfs(key, second, itemEquationMap, scan);
                if (dfs != -1) {
                    return dfs * stringDoubleHashMap.get(key);
                }
            }
        }
        scan.remove(first);

        return -1;
    }
}

