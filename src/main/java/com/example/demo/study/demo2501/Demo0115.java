package com.example.demo.study.demo2501;


import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Demo0115 {

    public static void main(String[] args) {
        int i = new pileBox().pileBox(new int[][]{{16, 19, 15},
                {3, 9, 16}, {19, 6, 11}, {8, 19, 15}, {5, 10, 20},
                {18, 19, 8}, {16, 1, 12}, {11, 6, 1}, {13, 1, 19},
                {4, 14, 6}, {19, 2, 19}, {18, 3, 14}, {15, 20, 10},
                {3, 20, 14}, {6, 20, 8}, {15, 17, 20}, {4, 17, 12},
                {2, 10, 17}, {5, 4, 1}, {3, 12, 7}, {10, 10, 16},
                {12, 5, 8}, {17, 16, 17}, {19, 19, 8}, {20, 11, 18},
                {20, 5, 8}, {9, 3, 5}, {4, 9, 9}, {17, 11, 8}, {2, 14, 19},
                {9, 6, 19}, {18, 2, 5}, {14, 18, 14}, {7, 14, 3}, {17, 10, 2},
                {19, 1, 13}, {9, 17, 2}, {1, 2, 3}, {17, 7, 6}, {18, 13, 2},
                {14, 8, 18}, {14, 5, 8}, {7, 19, 12}, {6, 19, 14}, {13, 17, 7},
                {2, 3, 18}, {17, 5, 16}, {9, 18, 19}, {11, 17, 12}, {1, 13, 13},
                {8, 17, 2}, {7, 20, 3}, {16, 14, 5}});
        System.out.println();
    }


}

// 427
/*
给你一个 n * n 矩阵 grid ，矩阵由若干 0 和 1 组成。请你用四叉树表示该矩阵 grid 。

你需要返回能表示矩阵 grid 的 四叉树 的根结点。

四叉树数据结构中，每个内部节点只有四个子节点。此外，每个节点都有两个属性：

val：储存叶子结点所代表的区域的值。1 对应 True，0 对应 False。注意，当 isLeaf 为 False 时，你可以把 True 或者 False 赋值给节点，两种值都会被判题机制 接受 。
isLeaf: 当这个节点是一个叶子结点时为 True，如果它有 4 个子节点则为 False 。
class Node {
    public boolean val;
    public boolean isLeaf;
    public Node topLeft;
    public Node topRight;
    public Node bottomLeft;
    public Node bottomRight;
}
我们可以按以下步骤为二维区域构建四叉树：

如果当前网格的值相同（即，全为 0 或者全为 1），将 isLeaf 设为 True ，将 val 设为网格相应的值，并将四个子节点都设为 Null 然后停止。
如果当前网格的值不同，将 isLeaf 设为 False， 将 val 设为任意值，然后如下图所示，将当前网格划分为四个子网格。
使用适当的子网格递归每个子节点。


如果你想了解更多关于四叉树的内容，可以参考 wiki 。

四叉树格式：

你不需要阅读本节来解决这个问题。只有当你想了解输出格式时才会这样做。输出为使用层序遍历后四叉树的序列化形式，其中 null 表示路径终止符，其下面不存在节点。

它与二叉树的序列化非常相似。唯一的区别是节点以列表形式表示 [isLeaf, val] 。

如果 isLeaf 或者 val 的值为 True ，则表示它在列表 [isLeaf, val] 中的值为 1 ；如果 isLeaf 或者 val 的值为 False ，则表示值为 0 。

输入：grid = [[0,1],[1,0]]
输出：[[0,1],[1,0],[1,1],[1,1],[1,0]]
解释：此示例的解释如下：
请注意，在下面四叉树的图示中，0 表示 false，1 表示 True 。


输入：grid = [[1,1,1,1,0,0,0,0],[1,1,1,1,0,0,0,0],[1,1,1,1,1,1,1,1],[1,1,1,1,1,1,1,1],[1,1,1,1,0,0,0,0],[1,1,1,1,0,0,0,0],[1,1,1,1,0,0,0,0],[1,1,1,1,0,0,0,0]]
输出：[[0,1],[1,1],[0,1],[1,1],[1,0],null,null,null,null,[1,0],[1,0],[1,1],[1,1]]
解释：网格中的所有值都不相同。我们将网格划分为四个子网格。
topLeft，bottomLeft 和 bottomRight 均具有相同的值。
topRight 具有不同的值，因此我们将其再分为 4 个子网格，这样每个子网格都具有相同的值。
解释如下图所示：



 */

/*
// Definition for a QuadTree node.
class Node {
    public boolean val;
    public boolean isLeaf;
    public Node topLeft;
    public Node topRight;
    public Node bottomLeft;
    public Node bottomRight;


    public Node() {
        this.val = false;
        this.isLeaf = false;
        this.topLeft = null;
        this.topRight = null;
        this.bottomLeft = null;
        this.bottomRight = null;
    }

    public Node(boolean val, boolean isLeaf) {
        this.val = val;
        this.isLeaf = isLeaf;
        this.topLeft = null;
        this.topRight = null;
        this.bottomLeft = null;
        this.bottomRight = null;
    }

    public Node(boolean val, boolean isLeaf, Node topLeft, Node topRight, Node bottomLeft, Node bottomRight) {
        this.val = val;
        this.isLeaf = isLeaf;
        this.topLeft = topLeft;
        this.topRight = topRight;
        this.bottomLeft = bottomLeft;
        this.bottomRight = bottomRight;
    }
}
*/

class Node {
    public boolean val;
    public boolean isLeaf;
    public Node topLeft;
    public Node topRight;
    public Node bottomLeft;
    public Node bottomRight;


    public Node() {
        this.val = false;
        this.isLeaf = false;
        this.topLeft = null;
        this.topRight = null;
        this.bottomLeft = null;
        this.bottomRight = null;
    }

    public Node(boolean val, boolean isLeaf) {
        this.val = val;
        this.isLeaf = isLeaf;
        this.topLeft = null;
        this.topRight = null;
        this.bottomLeft = null;
        this.bottomRight = null;
    }

    public Node(boolean val, boolean isLeaf, Node topLeft, Node topRight, Node bottomLeft, Node bottomRight) {
        this.val = val;
        this.isLeaf = isLeaf;
        this.topLeft = topLeft;
        this.topRight = topRight;
        this.bottomLeft = bottomLeft;
        this.bottomRight = bottomRight;
    }
}

class Solution {
    public Node construct(int[][] grid) {
        return null;

    }
}

// 53
/*
给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。子数组是数组中的一个连续部分。



示例 1：

输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
输出：6
解释：连续子数组 [4,-1,2,1] 的和最大，为 6 。
示例 2：

输入：nums = [1]
输出：1
示例 3：

输入：nums = [5,4,-1,7,8]
输出：23
 */

class maxSubArray {
    public int maxSubArray(int[] nums) {
        return 0;

    }
}

// 918
/*
给定一个长度为 n 的环形整数数组 nums ，返回 nums 的非空 子数组 的最大可能和 。

环形数组 意味着数组的末端将会与开头相连呈环状。形式上， nums[i] 的下一个元素是 nums[(i + 1) % n] ， nums[i] 的前一个元素是 nums[(i - 1 + n) % n] 。

子数组 最多只能包含固定缓冲区 nums 中的每个元素一次。形式上，对于子数组 nums[i], nums[i + 1], ..., nums[j]
，不存在 i <= k1, k2 <= j 其中 k1 % n == k2 % n 。

示例 1：

输入：nums = [1,-2,3,-2]
输出：3
解释：从子数组 [3] 得到最大和 3
示例 2：

输入：nums = [5,-3,5]
输出：10
解释：从子数组 [5,5] 得到最大和 5 + 5 = 10
示例 3：

输入：nums = [3,-2,2,-3]
输出：3
解释：从子数组 [3] 和 [3,-2,2] 都可以得到最大和 3


class Solution {
    public int maxSubarraySumCircular(int[] nums) {
        int n = nums.length;
        int[] leftMax = new int[n];
        // 对坐标为 0 处的元素单独处理，避免考虑子数组为空的情况
        leftMax[0] = nums[0];
        int leftSum = nums[0];
        int pre = nums[0];
        int res = nums[0];
        for (int i = 1; i < n; i++) {
            pre = Math.max(pre + nums[i], nums[i]);
            res = Math.max(res, pre);
            leftSum += nums[i];
            leftMax[i] = Math.max(leftMax[i - 1], leftSum);
        }

        // 从右到左枚举后缀，固定后缀，选择最大前缀
        int rightSum = 0;
        for (int i = n - 1; i > 0; i--) {
            rightSum += nums[i];
            res = Math.max(res, rightSum + leftMax[i - 1]);
        }
        return res;
    }
}

 */

class maxSubarraySumCircular {
    public int maxSubarraySumCircular(int[] nums) {
        return 0;

    }
}


// 2551 todo check
/*
你有 k 个背包。给你一个下标从 0 开始的整数数组 weights ，其中 weights[i] 是第 i 个珠子的重量。同时给你整数 k 。

请你按照如下规则将所有的珠子放进 k 个背包。

没有背包是空的。
如果第 i 个珠子和第 j 个珠子在同一个背包里，那么下标在 i 到 j 之间的所有珠子都必须在这同一个背包中。
如果一个背包有下标从 i 到 j 的所有珠子，那么这个背包的价格是 weights[i] + weights[j] 。
一个珠子分配方案的 分数 是所有 k 个背包的价格之和。

请你返回所有分配方案中，最大分数 与 最小分数 的 差值 为多少。

示例 1：

输入：weights = [1, 3, 5, 1], k = 2
[4,8,6]
[4,6,8]
输出：4
解释：
分配方案 [1],[3,5,1] 得到最小得分 (1+1) + (3+1) = 6 。
分配方案 [1,3],[5,1] 得到最大得分 (1+3) + (5+1) = 10 。
所以差值为 10 - 6 = 4 。
示例 2：

输入：weights = [1, 3], k = 2
输出：0
解释：唯一的分配方案为 [1],[3] 。
最大最小得分相等，所以返回 0 。

[1,  2,31,5,6,7,  1,  2,6,8,9] ,4
[2][9][2][11]  24

[1,2, 31, 5,6,7,1,2,6,8, 9]
[3][62][13][18]  21+75
96



86
[3,33,36,11,13,8,3,8,14,17]
[3,3,8,8,11,13,14,17,33,36]

[1][2,1][2][6,9]
[1,31][5,6][7,8][9]

min
[
[2,3,32,6,7,8,2,3,7,9,10],
[0,6, 6,0,0,0,0,0,0,0,0],  2 + 2+31   3+31+31         2+2+5  3+ 31+5   32+5+5
[0,0,0,0,0,0,0,0,0,0,0],
[0,0,0,0,0,0,0,0,0,0,0]
]

 */

class putMarbles {
    public long putMarbles(int[] weights, int k) {
        if (weights.length == k || weights.length == 0 || k == 0) {
            return 0;
        }


        return 0;


    }
}


//17.19. 消失的两个数字
/*
给定一个数组，包含从 1 到 N 所有的整数，但其中缺了两个数字。你能在 O(N) 时间内只用 O(1) 的空间找到它们吗？
以任意顺序返回这两个数字均可。

示例 1:
输入: [1]
输出: [2,3]

示例 2:
输入: [2,3]
输出: [1,4]

[2,3,4,6,1,5]
[3,2,4,6,1,5]
[4,2,3,6,1,5]
[6,2,3,4,1,5]
[5,2,3,4,1,6]
[1,2,8,4,5,6]
6,7
6,8

3,6

6,7
 */

class missingTwo {
    public int[] missingTwo(int[] nums) {
        int length = nums.length;
        int currIndex = 0;
        while (currIndex < length) {
            if (nums[currIndex] - 1 == currIndex || nums[currIndex] - 1 >= length) {
                currIndex++;
                continue;
            }
            int temp = nums[nums[currIndex] - 1];
            nums[nums[currIndex] - 1] = nums[currIndex];
            nums[currIndex] = temp;
        }


        int[] result = new int[2];
        currIndex = 0;
        for (int i = 0; i < length; i++) {
            if (i + 1 != nums[i]) {
                result[currIndex] = i + 1;
                currIndex++;
            }
        }
        if (currIndex == 0) {
            result[0] = length + 1;
            result[1] = length + 2;
        }
        if (currIndex == 1) {
            result[1] = nums[result[0] - 1] == length + 2 ? length + 1 : length + 2;
        }

        return result;
    }
}

//面试题 08.13. 堆箱子
/*

堆箱子。
给你一堆n个箱子，箱子宽 wi、深 di、高 hi。箱子不能翻转，将箱子堆起来时，下面箱子的宽度、高度和深度必须大于上面的箱子。
实现一种方法，搭出最高的一堆箱子。箱堆的高度为每个箱子高度的总和。

输入使用数组[wi, di, hi]表示每个箱子。
示例1:
 输入：box = [[1, 1, 1], [2, 2, 2], [3, 3, 3]]
 输出：6

示例2:
 输入：box = [[1, 1, 1], [2, 3, 4], [3, 4, 5],  [1, 5, 6],   [2, 6, 7]]
 输出：10


 [3, 6, 7],[2, 5, 6],[3, 4, 5],[1, 3, 4],[1, 1, 1]


提示:
箱子的数目不大于3000个。

[7,13,5,]

class Solution {
    public int pileBox(int[][] box) {
        Arrays.sort(box, (x, y) -> x[0] - y[0]);
        int[] dp = new int[box.length];
        int res = 0;
        for(int i = 0; i < box.length; ++i){
            for(int j = 0; j < i; ++j){
                // i 的三维都要比 j 大
                if(box[i][0] > box[j][0] && box[i][1] > box[j][1] && box[i][2] > box[j][2]){
                    //在 0 <= j < i 范围内找到最大的 dp[j]
                    dp[i] = Math.max(dp[i], dp[j]);
                }
            }
            //最后加上最底端箱子的高度
            dp[i] += box[i][2];
            res = Math.max(dp[i], res);
        }
        return res;
    }
}

 */

class Solution {
    public int pileBox(int[][] box) {
        Arrays.sort(box, (x, y) -> x[0] - y[0]);
        int[] dp = new int[box.length];
        int res = 0;
        for(int i = 0; i < box.length; ++i){
            for(int j = 0; j < i; ++j){
                // i 的三维都要比 j 大
                if(box[i][0] > box[j][0] && box[i][1] > box[j][1] && box[i][2] > box[j][2]){
                    //在 0 <= j < i 范围内找到最大的 dp[j]
                    dp[i] = Math.max(dp[i], dp[j]);
                }
            }
            //最后加上最底端箱子的高度
            dp[i] += box[i][2];
            res = Math.max(dp[i], res);
        }
        return res;
    }
}

class pileBox {
    public int pileBox(int[][] box) {

        PriorityQueue<int[]> ints = new PriorityQueue<>(((o1, o2) -> (o2[0] + o2[1] + o2[2]) - (o1[0] + o1[1] + o1[2])));

        for (int[] item : box) {
            ints.add(item);
        }

        ArrayList<ArrayDeque<int[]>> arrayDeques = new ArrayList<>();
        while (!ints.isEmpty()) {
            int[] curr = ints.poll();
            addToStack(curr, arrayDeques);
        }

        List<Integer> collect = arrayDeques.stream().mapToInt(item -> item.stream().mapToInt(one -> one[2]).sum()).boxed().sorted(Comparator.reverseOrder()).collect(Collectors.toList());

        return collect.get(0);
    }

    public void addToStack(int[] curr, ArrayList<ArrayDeque<int[]>> arrayDeques) {
        boolean addSuccess = false;
        for (ArrayDeque<int[]> item : arrayDeques) {
            int[] first = item.getFirst();
            if (first[0] > curr[0] && first[1] > curr[1] && first[2] > curr[2]) {
                item.addFirst(curr);
                addSuccess = true;
            }
        }

        if (!addSuccess) {
            ArrayDeque<int[]> newOne = new ArrayDeque<>();
            newOne.add(curr);
            arrayDeques.add(newOne);
        }
    }
}

// 300. 最长递增子序列
/*
给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。

子序列 是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的
子序列
。


示例 1：

输入：nums = [10,9,2,5,3,7,101,18]
输出：4
解释：最长递增子序列是 [2,3,7,101]，因此长度为 4 。
示例 2：

输入：nums = [0,1,0,3,2,3]
输出：4
示例 3：

输入：nums = [7,7,7,7,7,7,7]
输出：1
 */

class lengthOfLIS {
    public int lengthOfLIS(int[] nums) {

    }
}