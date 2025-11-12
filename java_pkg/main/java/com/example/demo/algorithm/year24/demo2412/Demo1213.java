package com.example.demo.algorithm.year24.demo2412;


import java.util.*;
import java.util.stream.Collectors;

public class Demo1213 {

    public static void main(String[] args) {
        int[][] temp = {{1, 1, 0, 0, 0},
                {1, 1, 0, 1, 0},
                {0, 0, 1, 1, 0},
                {0, 0, 0, 1, 1}};
        int i = new largestIsland().largestIsland(temp);


        System.out.println();
    }


}

//199
/*
给定一个二叉树的 根节点 root，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。

输入：root = [1,2,3,null,5,null,4]
输出：[1,3,4]

输入：root = [1,2,3,4,null,null,null,5]
输出：[1,3,4,5]

输入：root = [1,null,3]
输出：[1,3]

输入：root = []
输出：[]


 */

class rightSideView {
    public List<Integer> rightSideView(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        ArrayList<Integer> result = new ArrayList<>();
        ArrayDeque<TreeNode> nodeQueue = new ArrayDeque<>();
        nodeQueue.add(root);
        ArrayDeque<TreeNode> nextLevelNodeQueue = new ArrayDeque<>();
        while (!nodeQueue.isEmpty()) {
            TreeNode pop = nodeQueue.pop();
            if (pop.left != null) {
                nextLevelNodeQueue.add(pop.left);
            }
            if (pop.right != null) {
                nextLevelNodeQueue.add(pop.right);
            }
            if (nodeQueue.isEmpty()) {
                result.add(pop.val);
                nodeQueue = nextLevelNodeQueue;
                nextLevelNodeQueue = new ArrayDeque<>();
            }
        }

        return result;
    }
}


// 102
/*
给你二叉树的根节点 root ，返回其节点值的 层序遍历 。 （即逐层地，从左到右访问所有节点）。

输入：root = [3,9,20,null,null,15,7]
输出：[[3],[9,20],[15,7]]

输入：root = [1]
输出：[[1]]

输入：root = []
输出：[]


 */

class levelOrder {
    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        ArrayList<List<Integer>> result = new ArrayList<>();
        result.add(Collections.singletonList(root.val));

        ArrayDeque<TreeNode> nodeQueue = new ArrayDeque<>();
        nodeQueue.add(root);

        ArrayDeque<TreeNode> nextLevelNodeQueue = new ArrayDeque<>();
        while (!nodeQueue.isEmpty()) {
            TreeNode pop = nodeQueue.pop();
            if (pop.left != null) {
                nextLevelNodeQueue.add(pop.left);
            }
            if (pop.right != null) {
                nextLevelNodeQueue.add(pop.right);
            }
            if (nodeQueue.isEmpty()) {
                nodeQueue = nextLevelNodeQueue;
                if (!nextLevelNodeQueue.isEmpty()) {
                    List<Integer> collect = nextLevelNodeQueue.stream().map((value) -> value.val).collect(Collectors.toList());
                    result.add(collect);
                }
                nextLevelNodeQueue = new ArrayDeque<>();
            }
        }

        return result;
    }
}

// 103
/*
给你二叉树的根节点 root ，返回其节点值的 锯齿形层序遍历 。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。

输入：root = [3,9,20,null,null,15,7]
输出：[[3],[20,9],[15,7]]

输入：root = [1]
输出：[[1]]

输入：root = []
输出：[]

 */

class zigzagLevelOrder {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        ArrayList<List<Integer>> result = new ArrayList<>();
        result.add(Collections.singletonList(root.val));

        ArrayDeque<TreeNode> nodeQueue = new ArrayDeque<>();
        nodeQueue.add(root);
        boolean dir = false;

        ArrayDeque<TreeNode> nextLevelNodeQueue = new ArrayDeque<>();
        while (!nodeQueue.isEmpty()) {
            TreeNode pop = nodeQueue.pop();
            if (pop.left != null) {
                nextLevelNodeQueue.add(pop.left);
            }
            if (pop.right != null) {
                nextLevelNodeQueue.add(pop.right);
            }
            if (nodeQueue.isEmpty()) {
                nodeQueue = nextLevelNodeQueue;
                if (!nextLevelNodeQueue.isEmpty()) {
                    List<Integer> collect = nextLevelNodeQueue.stream().
                            map((value) -> value.val).
                            collect(Collectors.toList());

                    if (!dir) {
                        Collections.reverse(collect);
                    }
                    dir = !dir;
                    result.add(collect);
                }
                nextLevelNodeQueue = new ArrayDeque<>();
            }
        }
        return result;
    }
}

// 230
/*
给定一个二叉搜索树的根节点 root ，和一个整数 k ，请你设计一个算法查找其中第 k 小的元素（从 1 开始计数）。

输入：root = [3,1,4,null,2], k = 1
输出：1

输入：root = [5,3,6,2,4,null,null,1], k = 3
输出：3

 */

class kthSmallest {
    TreeNode result = null;
    int k = 0;

    public int kthSmallest(TreeNode root, int k) {
        if (root == null) {
            return 0;
        }
        this.k = k;
        midScan(root);
        return result.val;
    }

    public void midScan(TreeNode root) {
        if (this.result != null) {
            return;
        }
        if (root == null) {
            return;
        }
        TreeNode left = root.left;
        TreeNode right = root.right;
        midScan(left);
        k--;
        if (k == 0) {
            this.result = root;
        }
        midScan(right);
    }
}

// 98
/*

给你一个二叉树的根节点 root ，判断其是否是一个有效的二叉搜索树。

有效 二叉搜索树定义如下：

节点的左
子树
只包含 小于 当前节点的数。
节点的右子树只包含 大于 当前节点的数。
所有左子树和右子树自身必须也是二叉搜索树。

输入：root = [2,1,3]
输出：true

输入：root = [5,1,4,null,null,3,6]
输出：false
解释：根节点的值是 5 ，但是右子节点的值是 4 。
 */

class isValidBST {
    private List<Integer> list = new ArrayList<>();
    private boolean result = true;

    public boolean isValidBST(TreeNode root) {
        midScan(root);
        if (!this.result) {
            return false;
        }
        for (int i = 0; i < this.list.size(); i++) {
            if (i + 1 < this.list.size() && this.list.get(i) >= this.list.get(i + 1)) {
                this.result = false;
            }
        }

        return this.result;
    }

    public void midScan(TreeNode root) {
        if (root == null) {
            return;
        }
        TreeNode left = root.left;
        TreeNode right = root.right;
        midScan(left);
        list.add(root.val);
        if ((left != null && left.val >= root.val) || (right != null && right.val <= root.val)) {
            this.result = false;
        }
        midScan(right);
    }
}

// 200
/*
给你一个由 1（陆地）和 0（水）组成的的二维网格，请你计算网格中岛屿的数量。
岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
此外，你可以假设该网格的四条边均被水包围。

示例 1：
输入：grid = [
  ["1","1","1","1","0"],
  ["1","1","0","1","0"],
  ["1","1","0","0","0"],
  ["0","0","0","0","0"]
]
输出：1

示例 2：
输入：grid = [
  ["1","1","0","0","0"],
  ["1","1","0","0","0"],
  ["0","0","1","0","0"],
  ["0","0","0","1","1"]
]
输出：3

m == grid.length
n == grid[i].length
1 <= m, n <= 300
grid[i][j] 的值为 0 或 1

 */

class numIslands {
    private char[][] grid;
    private int isLandNum = 0;

    public int numIslands(char[][] grid) {
        int rowLength = grid.length;
        if (grid.length == 0) {
            return 0;
        }
        int colLength = grid[0].length;
        if (colLength == 0) {
            return 0;
        }
        this.grid = grid;

        for (int row = 0; row < rowLength; row++) {
            for (int col = 0; col < colLength; col++) {
                if (this.grid[row][col] == 1) {
                    BFS(row, col);
                }
            }
        }

        return this.isLandNum;
    }

    public void BFS(int x, int y) {
        if (this.grid[x][y] != 1) {
            return;
        }

        ArrayDeque<int[]> arrayDeque = new ArrayDeque<>();
        arrayDeque.add(new int[]{x, y});

        while (!arrayDeque.isEmpty()) {
            int[] pop = arrayDeque.pop();
            int row = pop[0];
            int col = pop[1];
            if (row - 1 >= 0 && this.grid[row - 1][col] == 1) {
                arrayDeque.add(new int[]{row - 1, col});
            }
            if (row + 1 < this.grid.length && this.grid[row + 1][col] == 1) {
                arrayDeque.add(new int[]{row + 1, col});
            }
            if (col - 1 >= 0 && this.grid[row][col - 1] == 1) {
                arrayDeque.add(new int[]{row, col - 1});
            }
            if (col + 1 < this.grid[0].length && this.grid[row][col + 1] == 1) {
                arrayDeque.add(new int[]{row, col + 1});
            }
            this.grid[row][col] = 0;
        }

        this.isLandNum++;

    }
}

// 827
/*
给你一个大小为 n x n 二进制矩阵 grid 。最多 只能将一格 0 变成 1 。
返回执行此操作后，grid 中最大的岛屿面积是多少？
岛屿 由一组上、下、左、右四个方向相连的 1 形成。

示例 1:
输入: grid = [[1, 0], [0, 1]]
输出: 3
解释: 将一格0变成1，最终连通两个小岛得到面积为 3 的岛屿。

示例 2:
输入: grid = [[1, 1], [1, 0]]
输出: 4
解释: 将一格0变成1，岛屿的面积扩大为 4。

示例 3:
输入: grid = [[1, 1], [1, 1]]
输出: 4
解释: 没有0可以让我们变成1，面积依然为 4。
 */

class largestIsland {
    private int maxIsland = 0;

    public int largestIsland(int[][] grid) {
        int rowLength = grid.length;
        if (grid.length == 0) {
            return 0;
        }
        int colLength = grid[0].length;
        if (colLength == 0) {
            return 0;
        }

        boolean flag = true;
        for (int row = 0; row < rowLength; row++) {
            for (int col = 0; col < colLength; col++) {
                if (grid[row][col] == 0) {
                    flag = false;
                    BFS(grid, row, col);
                }
            }
        }
        if (flag) {
            return rowLength * colLength;
        }

        return this.maxIsland;


    }

    public void BFS(int[][] grid, int x, int y) {
        int[][] newGrid = deepCopy(grid);
        newGrid[x][y] = 1;
        int current = 0;
        ArrayDeque<int[]> arrayDeque = new ArrayDeque<>();
        arrayDeque.add(new int[]{x, y});

        while (!arrayDeque.isEmpty()) {
            int[] pop = arrayDeque.pop();

            int row = pop[0];
            int col = pop[1];
            if (newGrid[row][col] != 1) {
                continue;
            }
            newGrid[row][col] = 0;
            current++;

            if (row - 1 >= 0 && newGrid[row - 1][col] == 1) {
                arrayDeque.add(new int[]{row - 1, col});
            }
            if (row + 1 < newGrid.length && newGrid[row + 1][col] == 1) {
                arrayDeque.add(new int[]{row + 1, col});
            }
            if (col - 1 >= 0 && newGrid[row][col - 1] == 1) {
                arrayDeque.add(new int[]{row, col - 1});
            }
            if (col + 1 < newGrid[0].length && newGrid[row][col + 1] == 1) {
                arrayDeque.add(new int[]{row, col + 1});
            }
        }
        this.maxIsland = Math.max(this.maxIsland, current);
    }

    public int[][] deepCopy(int[][] grid) {
        int rowLength = grid.length;
        int colLength = grid[0].length;

        int[][] ints = new int[rowLength][colLength];
        for (int row = 0; row < rowLength; row++) {
            for (int col = 0; col < colLength; col++) {
                ints[row][col] = grid[row][col];
            }
        }

        return ints;
    }
}

