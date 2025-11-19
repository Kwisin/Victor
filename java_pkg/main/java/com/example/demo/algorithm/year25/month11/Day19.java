package com.example.demo.algorithm.year25.month11;

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
     */
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {


    }




    /*321
    给你两个整数数组 nums1 和 nums2，它们的长度分别为 m 和 n。
    数组 nums1 和 nums2 分别代表两个数各位上的数字。同时你也会得到一个整数 k。

    请你利用这两个数组中的数字创建一个长度为 k <= m + n 的最大数。同一数组中数字的相对顺序必须保持不变。

    返回代表答案的长度为 k 的数组。
    示例 1：
    输入：nums1 = [3,4,6,5], nums2 = [9,1,2,5,8,3], k = 5
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
     */

    public int[] maxNumber(int[] nums1, int[] nums2, int k) {

    }
}
