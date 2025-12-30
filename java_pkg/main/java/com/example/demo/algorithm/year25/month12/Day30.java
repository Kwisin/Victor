package com.example.demo.algorithm.year25.month12;

import java.util.*;

public class Day30 {
    public static void main(String[] args) {
        Day30 day30 = new Day30();

        // 1. 全排列
        System.out.println(day30.permute(new int[]{1, 2, 3}));
        // [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]

        // 2. 有效的括号
        System.out.println(day30.isValid("()[]{}"));  // true
        System.out.println(day30.isValid("([)]"));    // false

        // 3. 最小路径和
        int[][] grid = {{1, 3, 1}, {1, 5, 1}, {4, 2, 1}};
        System.out.println(day30.minPathSum(grid));  // 7

        // 4. 合并区间
        int[][] intervals = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
        System.out.println(Arrays.deepToString(day30.merge(intervals)));
        // [[1,6],[8,10],[15,18]]

        // 5. 三数之和
        int[] nums1 = {-1, 0, 1, 2, -1, -4};
        System.out.println(day30.threeSum(nums1));
        // 输出：[[-1,-1,2],[-1,0,1]]

        // 6. 单词搜索
        char[][] board = {
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}
        };
        System.out.println(day30.exist(board, "ABCCED")); // true
        System.out.println(day30.exist(board, "SEE"));    // true
        System.out.println(day30.exist(board, "ABCB"));   // false

        // 7. 从前序与中序遍历序列构造二叉树
        int[] preorder = {3, 9, 20, 15, 7};
        int[] inorder = {9, 3, 15, 20, 7};
        TreeNode root = day30.buildTree(preorder, inorder);
        // 简单验证：打印根节点值
        System.out.println(root != null ? root.val : "null"); // 3

        // 8. 最长公共子序列
        System.out.println(day30.longestCommonSubsequence("abcde", "ace")); // 3
    }

    /*
    46. 全排列 (Permutations)
    给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。

    示例 1：输入：nums = [1,2,3]
    输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
    */
    public List<List<Integer>> permute(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new ArrayList<>();
        }

        int length = nums.length;
        ArrayList<List<Integer>> ans = new ArrayList<>();
        permuteDFS(nums, new boolean[length], new ArrayList<>(), ans);

        return ans;
    }

    public void permuteDFS(int[] nums, boolean[] used, List<Integer> curr, List<List<Integer>> result) {
        if (curr.size() == nums.length) {
            result.add(new ArrayList<>(curr));
        }

        for (int i = 0; i < nums.length; i++) {
            if (used[i]) {
                continue;
            }
            curr.add(nums[i]);
            used[i] = true;
            permuteDFS(nums, used, curr, result);
            curr.remove(curr.size() - 1);
            used[i] = false;
        }
    }

    /*
    20. 有效的括号 (Valid Parentheses)
    给定一个只包括 '(',')','{','}','[',']' 的字符串 s ，判断字符串是否有效。
    有效字符串需满足：
    1. 左括号必须用相同类型的右括号闭合。
    2. 左括号必须以正确的顺序闭合。
    3. 每个右括号都有一个对应的相同类型的左括号。

    示例 1：输入：s = "()" -> 输出：true
    示例 2：输入：s = "()[]{}" -> 输出：true
    示例 3：输入：s = "(]" -> 输出：false
    */
    public boolean isValid(String s) {
        if (s == null || s.isEmpty()) {
            return true;
        }

        char[] charArray = s.toCharArray();
        Stack<Character> characters = new Stack<>();
        for (char c : charArray) {
            if (c == '(' || c == '[' || c == '{') {
                characters.push(c);
            } else {
                if (characters.isEmpty()) {
                    return false;
                }
                Character peek = characters.peek();
                if ((c == ')' && peek == '(') || (c == ']' && peek == '[') || (c == '}' && peek == '{')) {
                    characters.pop();
                } else {
                    return false;
                }

            }

        }

        return characters.isEmpty();
    }

    /*
    64. 最小路径和 (Minimum Path Sum)
    给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
    说明：每次只能向下或者向右移动一步。

    示例 1：
    输入：grid = [[1,3,1],
                 [1,5,1],
                 [4,2,1]]
    输出：7
    解释：路径 1→3→1→1→1 的总和最小。
    */
    public int minPathSum(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int row = grid.length;
        int col = grid[0].length;
        int[][] dp = new int[row][col];
        dp[0][0] = grid[0][0];

        for (int i = 1; i < col; i++) {
            dp[0][i] = grid[0][i] + dp[0][i - 1];
        }
        for (int i = 1; i < row; i++) {
            dp[i][0] = grid[i][0] + dp[i - 1][0];
        }

        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }

        return dp[row - 1][col - 1];
    }

    /*
    56. 合并区间 (Merge Intervals)
    以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。
    请你合并所有重叠的区间，并返回 一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间 。

    示例 1：
    输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
    输出：[[1,6],[8,10],[15,18]]
    解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
    */
    public int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return intervals;
        }

        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        Stack<int[]> ints = new Stack<>();
        ints.push(intervals[0]);

        for (int i = 1; i < intervals.length; i++) {
            // 前置区间
            int[] pre = ints.pop();
            int[] curr = intervals[i];

            if (curr[0] >= pre[0] && curr[0] <= pre[1]) {
                ints.push(new int[]{pre[0], Math.max(pre[1], curr[1])});
            } else {
                ints.push(pre);
                ints.push(curr);
            }
        }

        int[][] ans = new int[ints.size()][2];
        int index = ints.size() - 1;
        while (!ints.isEmpty()) {
            ans[index] = ints.pop();
            index--;
        }

        return ans;
    }

    /*
    15. 三数之和 (3Sum)
    给你一个整数数组 nums ，判断是否存在三元组 [nums[i], nums[j], nums[k]] 满足 i != j、i != k 且 j != k ，同时还满足 nums[i] + nums[j] + nums[k] == 0 。
    请你返回所有和为 0 且不重复的三元组。
    注意：答案中不可以包含重复的三元组。

    示例 1：
    输入：nums = [-1,0,1,2,-1,-4]
    [-4,-1,-1,0,1,2]
    输出：[[-1,-1,2],[-1,0,1]]
    */
    public List<List<Integer>> threeSum(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new ArrayList<>();
        }
        Arrays.sort(nums);

        List<List<Integer>> result = new ArrayList<>();
        threeSumDFS(nums, 0, 0, 0, new ArrayList<>(), result);

        return result;
    }

    public void threeSumDFS(int[] nums, int index, int sum, int target, List<Integer> curr, List<List<Integer>> result) {
        if (curr.size() == 3) {
            if (sum == target) {
                result.add(new ArrayList<>(curr));
            }
            return;
        }
        HashSet<Integer> exist = new HashSet<>();
        for (int i = index; i < nums.length; i++) {
            if (exist.contains(nums[i])) {
                continue;
            }
            exist.add(nums[i]);
            curr.add(nums[i]);
            threeSumDFS(nums, index + 1, sum + nums[i], target, curr, result);
            curr.remove(curr.size() - 1);
        }
    }

    /*
    79. 单词搜索 (Word Search)
    给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。如果 word 存在于网格中，返回 true ；否则，返回 false 。
    单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。

    示例 1：
    输入：board = [["A","B","C","E"],
                  ["S","F","C","S"],
                  ["A","D","E","E"]], word = "ABCCED"
    输出：true
    */
    public boolean exist(char[][] board, String word) {
        if (word == null || word.isEmpty()) {
            return true;
        }
        if (board == null || board.length == 0 || board[0].length == 0) {
            return false;
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == word.charAt(0) &&
                        existDFS(board, new boolean[board.length][board[0].length], word, new StringBuilder(), 0, i, j)) {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean existDFS(char[][] board, boolean[][] used, String word, StringBuilder curr, int index, int x, int y) {
        if (curr.toString().equals(word)) {
            return true;
        }
        if (x < 0 || y < 0 || x > board.length - 1 || y > board[0].length - 1 || used[x][y]) {
            return false;
        }

        if (board[x][y] != word.charAt(index)) {
            return false;
        }

        curr.append(board[x][y]);
        used[x][y] = true;
        if (existDFS(board, used, word, curr, index + 1, x + 1, y) ||
                existDFS(board, used, word, curr, index + 1, x - 1, y) ||
                existDFS(board, used, word, curr, index + 1, x, y + 1) ||
                existDFS(board, used, word, curr, index + 1, x, y - 1)) {
            return true;
        }
        curr.deleteCharAt(curr.length() - 1);
        used[x][y] = false;

        return false;
    }

    /*
    105. 从前序与中序遍历序列构造二叉树 (Construct Binary Tree from Preorder and Inorder Traversal)
    给定两个整数数组 preorder 和 inorder ，其中 preorder 是二叉树的先序遍历， inorder 是同一棵树的中序遍历，请构造二叉树并返回其根节点。

    示例 1：
    输入：preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
    输出：[3,9,20,null,null,15,7]
    */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || inorder == null) {
            return null;
        }
        List<Integer> preOrder = new ArrayList<>();
        List<Integer> inOrder = new ArrayList<>();
        for (int i = 0; i < inorder.length; i++) {
            preOrder.add(preorder[i]);
            inOrder.add(inorder[i]);
        }

        return buildTree2(preOrder, inOrder);
    }

    public TreeNode buildTree2(List<Integer> preorder, List<Integer> inorder) {

        if (preorder.size() == 1 && inorder.size() == 1) {
            return new TreeNode(preorder.get(0));
        }

        int root = preorder.get(0);
        int len = 0;
        for (Integer integer : inorder) {
            if (integer == root) {
                break;
            }
            len++;
        }

        // gap=1

        List<Integer> leftPre = preorder.subList(1, len + 1);
        List<Integer> leftIn = inorder.subList(0, len);

        List<Integer> rightPre = preorder.subList(len + 1, preorder.size());
        List<Integer> rightIn = inorder.subList(len + 1, inorder.size());


        return new TreeNode(root, buildTree2(leftPre, leftIn), buildTree2(rightPre, rightIn));
    }

    /*
    1143. 最长公共子序列 (Longest Common Subsequence)
    给定两个字符串 text1 和 text2，返回这两个字符串的最长 公共子序列 的长度。如果不存在 公共子序列 ，返回 0 。
    一个字符串的 子序列 是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。
    例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。
    两个字符串的 公共子序列 是这两个字符串所共同拥有的子序列。

    子序列，子串，子数组等问题？

    示例 1：

    cde ace
    输入：text1 = "cabcde", text2 = "abcac e"

    abce
    ca  ac
    [0,0,0,0,0,0,0]
    [0,0,0,1,1,1,1]
    [0,1,1,1,2,2,2]
    [0,1,2,2,2,2,2]
    [0,1,2,3,3,3,3]
    [0,1,2,3,3,3,3]
    [0,1,2,3,3,3,4]

    a,c,b,d
    a,e,f,g,c,d

    dp[i][j]: text1 的前 i 个字符和text2的前 j 个字符的公共序列长度
    dp[i][j]： text1[i] == text2[j] ? dp[i-1][j-1]+1 : dp[i-1][j-1]
    dp[i-1][j-1] == 0 ? Math.max(dp[i-1][j],dp[i][j-1]): (text1[i] == text2[j] ? dp[i-1][j-1]+1 : dp[i-1][j-1])
    输出：3
    解释：最长公共子序列是 "ace" ，它的长度为 3 。
    */
    public int longestCommonSubsequence(String text1, String text2) {

        int length1 = text1.length();
        int length2 = text2.length();
        int[][] dp = new int[length1 + 1][length2 + 1];

        for (int i = 1; i <= length1; i++) {
            for (int j = 1; j <= length2; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[length1][length2];
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
