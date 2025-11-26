package com.example.demo.algorithm.year25.month11;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Day19 {
    public static void main(String[] args) {

    }

    /* 310
    树是一个无向图，其中任何两个顶点只通过一条路径连接。 换句话说，任何一个没有简单环路的连通图都是一棵树。
    给你一棵包含 n 个节点的树，标记为 0 到 n - 1 。
    给定数字 n 和一个有 n - 1 条无向边的 edges 列表（每一个边都是一对标签），
    其中 edges[i] = [ai, bi] 表示树中节点 ai 和 bi 之间存在一条无向边。
    可选择树中任何一个节点作为根。当选择节点 x 作为根节点时，设结果树的高度为 h 。
    在所有可能的树中，具有最小高度的树（即，min(h)）被称为 最小高度树 。
    请你找到所有的 最小高度树 并按 任意顺序 返回它们的根节点标签列表。
    树的 高度 是指根节点和叶子节点之间最长向下路径上边的数量。

    输入：n = 4, edges = [[1,0],[1,2],[1,3]]
    输出：[1]
    解释：如图所示，当根是标签为 1 的节点时，树的高度是 1 ，这是唯一的最小高度树
    输入：n = 6, edges = [[3,0],[3,1],[3,2],[3,4],[5,4]]
    输出：[3,4]

    提示：
    1 <= n <= 2 * 104
    edges.length == n - 1
    0 <= ai, bi < n
    ai != bi
    所有 (ai, bi) 互不相同
    给定的输入 保证 是一棵树，并且 不会有重复的边


    我的基本思路：
    第一步先构造图，找出各自关联的点列表
    第二步从一个叶子节点出发通过深度优先遍历找出所有最长路径
    第三部找出最长路径的中间节点

    或者直接广度优先是不是更好？

     */
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        HashMap<Integer, List<Integer>> nodeRelateMap = new HashMap<>();
        for (int[] item : edges) {
            nodeRelateMap.getOrDefault(item[0], new ArrayList<>()).add(item[1]);
            nodeRelateMap.getOrDefault(item[1], new ArrayList<>()).add(item[0]);
        }

        ArrayList<Integer> root = new ArrayList<>();
        for (Integer key : nodeRelateMap.keySet()) {
            if (nodeRelateMap.get(key).size() == 1) {
                break;
            }
        }

        HashMap<Integer, Integer> integerIntegerHashMap = new HashMap<>();


        return null;
    }

    public void DFS(HashMap<Integer, List<Integer>> nodeRelateMap, int curr) {

    }




    /*321
    给你两个整数数组 nums1 和 nums2，它们的长度分别为 m 和 n。
    数组 nums1 和 nums2 分别代表两个数各位上的数字。同时你也会得到一个整数 k。

    请你利用这两个数组中的数字创建一个长度为 k <= m + n 的最大数。同一数组中数字的相对顺序必须保持不变。

    返回代表答案的长度为 k 的数组。
    示例 1：
    输入：nums1 = [3,4,6,5], nums2 = [1,1,2,5,8,9,3], k = 5
    [2,3,7,1,8,3,9,5]   [5,7,8,3,6,1,6,8]
    输出：[9,8,6,5,3]
    示例 2：
    输入：nums1 = [6,7], nums2 = [6,0,4], k = 5
    输出：[6,7,6,0,4]
    示例 3：
    输入：nums1 = [3,9], nums2 = [8,9], k = 3
    输出：[9,8,9]


    提示：
    m == nums1.length
    n == nums2.length
    1 <= m, n <= 500
    0 <= nums1[i], nums2[i] <= 9
    1 <= k <= m + n
    nums1 和 nums2 没有前导 0。

     一句话：在两边剩余长度满足条件前提下，找出最大的那个数
     */

    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        int length1 = nums1.length;
        int length2 = nums2.length;

        // 可以移除的元素数量
        int canRemove = length2 + length1 - k;
        int[] result = new int[k];
        int start1 = 0, start2 = 0;
        for (int i = 0; i < k; i++) {
            // 检查哪个数组还有元素
            boolean has1 = start1 < length1;
            boolean has2 = start2 < length2;

            if (!has1) {
                // nums1用完了，只能从nums2取
                int index2 = getMaxFromList(start2, canRemove, nums2);
                result[i] = nums2[index2];
                canRemove -= (index2 - start2);
                start2 = index2 + 1;
            } else if (!has2) {
                // nums2用完了，只能从nums1取
                int index1 = getMaxFromList(start1, canRemove, nums1);
                result[i] = nums1[index1];
                canRemove -= (index1 - start1);
                start1 = index1 + 1;
            } else {
                // 两个数组都有元素，正常比较
                int index1 = getMaxFromList(start1, canRemove, nums1);
                int max1 = nums1[index1];
                int index2 = getMaxFromList(start2, canRemove, nums2);
                int max2 = nums2[index2];

                if (max1 > max2) {
                    result[i] = max1;
                    canRemove -= (index1 - start1);
                    start1 = index1 + 1;
                } else if (max1 < max2) {
                    result[i] = max2;
                    canRemove -= (index2 - start2);
                    start2 = index2 + 1;
                } else {
                    // max1 == max2，直接比较这两个位置开始的子序列
                    if (compare(nums1, index1, nums2, index2) > 0) {
                        result[i] = max1;
                        canRemove -= (index1 - start1);
                        start1 = index1 + 1;
                    } else {
                        result[i] = max2;
                        canRemove -= (index2 - start2);
                        start2 = index2 + 1;
                    }
                }
            }
        }

        return result;
    }

    public int getMaxFromList(int start, int range, int[] nums) {
        int max = -1;
        int currIndex = 0;
        for (int j = start; j <= start + range && j < nums.length; j++) {
            if (nums[j] > max) {
                currIndex = j;
                max = nums[j];
            }
        }
        return currIndex;
    }

    /**
     * 字典序比较：从nums1[i]和nums2[j]开始，哪个序列更大
     *
     * @return 1: nums1更大, -1: nums2更大, 0: 相等
     */
    private int compare(int[] nums1, int i, int[] nums2, int j) {
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] > nums2[j]) {
                return 1;   // nums1更大
            } else if (nums1[i] < nums2[j]) {
                return -1;  // nums2更大
            }
            i++;
            j++;
        }
        // 前面都相等，剩余元素多的更大
        // 例如：[6,7] vs [6]，应该选[6,7]
        return (nums1.length - i) - (nums2.length - j);
    }

    // ========== 标准解法（经过LeetCode验证）==========

    /**
     * 标准解法：枚举分配 + 单调栈 + 合并
     * 时间复杂度：O(k² * (m + n))
     */
    public int[] maxNumber2(int[] nums1, int[] nums2, int k) {
        int m = nums1.length;
        int n = nums2.length;
        int[] maxSubsequence = new int[k];

        // 枚举从nums1取i个，从nums2取k-i个
        int start = Math.max(0, k - n);
        int end = Math.min(k, m);

        for (int i = start; i <= end; i++) {
            // 从nums1中选i个最大子序列
            int[] subsequence1 = maxSubsequenceFromArray(nums1, i);
            // 从nums2中选k-i个最大子序列
            int[] subsequence2 = maxSubsequenceFromArray(nums2, k - i);
            // 合并两个子序列
            int[] curMaxSubsequence = merge2(subsequence1, subsequence2);
            // 更新全局最大
            if (compare(curMaxSubsequence, 0, maxSubsequence, 0) > 0) {
                System.arraycopy(curMaxSubsequence, 0, maxSubsequence, 0, k);
            }
        }
        return maxSubsequence;
    }

    /**
     * 从数组中选k个数字，保持相对顺序，使其字典序最大（单调栈）
     */
    private int[] maxSubsequenceFromArray(int[] nums, int k) {
        int length = nums.length;
        int[] stack = new int[k];
        int top = -1;
        int remain = length - k;  // 还能丢弃多少个元素

        for (int i = 0; i < length; i++) {
            int num = nums[i];
            // 当栈顶元素小于当前元素，且还有丢弃配额时，弹出栈顶
            while (top >= 0 && stack[top] < num && remain > 0) {
                top--;
                remain--;
            }
            // 如果栈还没满，压入当前元素
            if (top < k - 1) {
                stack[++top] = num;
            } else {
                // 栈满了，丢弃当前元素
                remain--;
            }
        }
        return stack;
    }

    /**
     * 合并两个数组，使结果字典序最大
     */
    private int[] merge2(int[] subsequence1, int[] subsequence2) {
        int x = subsequence1.length, y = subsequence2.length;
        if (x == 0) {
            return subsequence2;
        }
        if (y == 0) {
            return subsequence1;
        }
        int mergeLength = x + y;
        int[] merged = new int[mergeLength];
        int index1 = 0, index2 = 0;

        for (int i = 0; i < mergeLength; i++) {
            if (compare(subsequence1, index1, subsequence2, index2) > 0) {
                merged[i] = subsequence1[index1++];
            } else {
                merged[i] = subsequence2[index2++];
            }
        }
        return merged;
    }


}
