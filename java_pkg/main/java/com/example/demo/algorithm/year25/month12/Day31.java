package com.example.demo.algorithm.year25.month12;

import java.util.*;

public class Day31 {
    public static void main(String[] args) {
        Day31 day31 = new Day31();

        // 1. 无重复字符的最长子串
        System.out.println(day31.lengthOfLongestSubstring("abcabcbb")); // 3
        System.out.println(day31.lengthOfLongestSubstring("bbbbb"));    // 1

        // 2. 最长连续序列
        int[] nums2 = {100, 4, 200, 1, 3, 2};
        System.out.println(day31.longestConsecutive(nums2)); // 4 (1, 2, 3, 4)

        // 3. 删除链表的倒数第 N 个结点
        ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        ListNode result = day31.removeNthFromEnd(head, 2);
        // 简单打印结果
        while (result != null) {
            System.out.print(result.val + " ");
            result = result.next;
        }
        System.out.println(); // 1 2 3 5

        // 4. 旋转图像
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        day31.rotate(matrix);
        System.out.println(Arrays.deepToString(matrix));
        // [[7,4,1],[8,5,2],[9,6,3]]

        // 5. 寻找重复数
        int[] nums1 = {1, 3, 4, 2, 2};
        System.out.println(day31.findDuplicate(nums1)); // 2

        // 6. 编辑距离
        System.out.println(day31.minDistance("horse", "ros")); // 3

        // 7. 不同的二叉搜索树
        System.out.println(day31.numTrees(3)); // 5

        // 8. 颜色分类
        int[] colors = {2, 0, 2, 1, 1, 0};
        day31.sortColors(colors);
        System.out.println(Arrays.toString(colors)); // [0, 0, 1, 1, 2, 2]
    }

    /*
    3. 无重复字符的最长子串 (Longest Substring Without Repeating Characters)
    给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。

    示例 1:
    输入: s = "abcabcbb"
    "cdefbabca"
    输出: 3
    解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
    */
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.isEmpty()) return 0;

        HashMap<Character, Integer> charPosition = new HashMap<>();
        int maxLength = 0;
        int start = -1;
        for (int i = 0; i < s.length(); i++) {
            char curr = s.charAt(i);
            if (!charPosition.containsKey(curr)) {
                charPosition.put(curr, i);
                if (start == -1) {
                    start = i;
                }
            } else {
                Integer position = charPosition.get(curr);
                start = position + 1;
                charPosition.put(curr, i);
            }

            maxLength = Math.max(maxLength, i - start + 1);
        }

        return maxLength;
    }

    /*
    128. 最长连续序列 (Longest Consecutive Sequence)
    给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
    请你设计并实现时间复杂度为 O(n) 的算法解决此问题。

    示例 1：
    输入：nums = [100,4,200,1,3,2]
    输出：4
    解释：最长数字连续序列是 [1, 2, 3, 4]。它的长度为 4。

    第一时间想到是的桶排序，但是会有越界的问题以及空间的问题

    其他的方法都不满足 O(n)的限制，应该还是桶排序的什么技巧没有掌握
    */
    public int longestConsecutive(int[] nums) {
        TreeMap<Integer, Integer> integerIntegerTreeMap = new TreeMap<>();


        return 0;
    }

    /*
    19. 删除链表的倒数第 N 个结点 (Remove Nth Node From End of List)
    给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。

    示例 1：
    输入：head = [1,2,3,4,5], n = 2
    输出：[1,2,3,5]

    遍历一遍确认长度，在遍历一遍移除节点
    */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null || head.next == null) return head;


        ListNode curr = head;
        int len = 0;
        while (curr != null) {
            len++;
            curr = curr.next;
        }

        if (len == n) {
            return head.next;
        }

        curr = head;
        int index = 0;
        while (curr != null) {
            index++;
            if (index == len - n) {
                curr.next = curr.next.next;
                break;
            }
            curr = curr.next;
        }

        return head;
    }

    /*
    48. 旋转图像 (Rotate Image)
    给定一个 n × n 的二维矩阵 matrix 表示一个图像。请你将图像顺时针旋转 90 度。
    你必须在 原地 旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要 使用另一个矩阵来旋转图像。

    示例 1：
    输入：matrix = [[1,2,3],
                   [4,5,6],
                   [7,8,9]]
    输入：matrix = [[1,4,7],
                   [2,5,8],
                   [3,6,9]]
    输出：[[7,4,1],
          [8,5,2],
          [9,6,3]]
    */
    public void rotate(int[][] matrix) {
        if (matrix == null || matrix.length == 0) return;
        int length = matrix.length;

        for (int i = 0; i < length; i++) {
            for (int j = i; j < length; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }

        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length / 2; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][length - j - 1];
                matrix[i][length - j - 1] = temp;
            }
        }

    }

    /*
    287. 寻找重复数 (Find the Duplicate Number)
    给定一个包含 n + 1 个整数的数组 nums ，其数字都在 [1, n] 范围内（包括 1 和 n），可知至少存在一个重复的整数。
    假设 nums 只有 一个重复的整数 ，返回 这个重复的数 。
    你设计的解决方案必须 不修改 数组 nums 且只用常量级 O(1) 的额外空间。

    示例 1：
    输入：nums = [1,3,4,2,2]
    输出：2

    快慢指针？
    */
    public int findDuplicate(int[] nums) {
        return 0;
    }

    /*
    72. 编辑距离 (Edit Distance)
    给你两个单词 word1 和 word2， 请返回将 word1 转换成 word2 所使用的最少操作数  。
    你可以对一个单词进行如下三种操作：
    1. 插入一个字符
    2. 删除一个字符
    3. 替换一个字符

    示例 1：
    输入：word1 = "h orse", word2 = "ro s"
    h  r -> h   ro
    '' ro - h   ro
    ho ro
    hors e   ros
    输出：3
    解释：
    horse -> rorse (将 'h' 替换为 'r')
    rorse -> rose (删除 'r')
    rose -> ros (删除 'e')

    [0,1,2,3]
    [1,1,2,3]
    [2,2,1,2]
    [3,2,2,2]
    [4,3,3,2]
    [5,4,4,3]

    word1[i] == word2[j] :dp[i][j] == dp[i-1][j-1]
    dp[i][j] = Math.min(dp[i-1][j],dp[i][j-1],dp[i-1][j-1])+1

    */
    public int minDistance(String word1, String word2) {
        if (word1 == null || word2 == null || word1.isEmpty() || word2.isEmpty()) return 0;

        int length1 = word1.length();
        int length2 = word2.length();
        int[][] dp = new int[length1 + 1][length2 + 1];
        dp[0][0] = 0;
        for (int i = 1; i < length2; i++) {
            dp[0][i] = i;
        }
        for (int i = 1; i < length1; i++) {
            dp[i][0] = i;
        }

        for (int i = 1; i < length1; i++) {
            for (int j = 1; j < length2; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                }
            }
        }

        return dp[length1][length2];
    }

    /*
    96. 不同的二叉搜索树 (Unique Binary Search Trees)
    给你一个整数 n ，求恰由 n 个节点组成且节点值从 1 到 n 互不相同的 二叉搜索树 有多少种？返回满足题意的二叉搜索树的种数。

    示例 1：
    输入：n = 3
    输出：5

    n=0   1
    n=1   1
    n=2   2
    n=3   dp[2]*dp[0]+dp[1]*dp[1]+dp[0]*dp[2] = 2+1+2
    n=4   dp[3]*dp[0] + dp[2]*dp[1] + dp[1]*dp[2] + dp[0]*dp[3]= 5+2+2+5

    */
    public int numTrees(int n) {
        if (n == 1 || n == 0) {
            return 1;
        }

        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 0; j < (i + 1) / 2; j++) {
                dp[i] += j == i - 1 - j ? dp[j] * dp[j] : dp[j] * dp[i - 1 - j] * 2;
            }
        }

        return dp[n];
    }

    /*
    75. 颜色分类 (Sort Colors)
    给定一个包含红色、白色和蓝色、共 n 个元素的数组 nums ，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
    我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
    必须在不使用库内置的 sort 函数的情况下解决这个问题。
    进阶：你能想出一个仅使用常数空间的一趟扫描算法吗？

    示例 1：
    输入：nums = [2,0,2,1,1,0]

    [0,2,1,0,0,2,0,1,2,0,1,1,1,0,2]
    [0,0,1,0,0,2,0, 1, 2,0,1,1,1,2,2]
    [0,0,0,0,0,1,0, 1, 1,1,1,2,2,2,2]

      [0,0,2,1,1,2]
      []
      []
      []
    输出：[0,0,1,1,2,2]
    */
    public void sortColors(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        int mid = (left + right) / 2;
        while (left < right) {
            if (nums[left] == 0) {
                left++;
                continue;
            }
            if (nums[right] == 2) {
                right--;
                continue;
            }
            if (nums[mid] == 1) {
                mid++;
                continue;
            }

            if (nums[left] == 2) {
                swap(nums, left, right);
                continue;
            }

            if (nums[right] == 0) {
                swap(nums, left, right);
                continue;
            }

            if (nums[mid] == 2) {
                if (mid < right){
                    swap(nums, mid, right);
                }
            } else if (nums[mid] == 0) {
                swap(nums, mid, left);
            }

        }

    }

    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
