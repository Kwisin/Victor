package com.example.demo.algorithm.demo25.demo2503;


import java.util.*;
import java.util.stream.Collectors;

public class Demo0319 {

    public static void main(String[] args) {
        System.out.println();
    }

}

// todo 218. 天际线问题
/*
城市的 天际线 是从远处观看该城市中所有建筑物形成的轮廓的外部轮廓。
给你所有建筑物的位置和高度，请返回 由这些建筑物形成的 天际线 。

每个建筑物的几何信息由数组 buildings 表示，其中三元组 buildings[i] = [lefti, righti, heighti] 表示：

lefti 是第 i 座建筑物左边缘的 x 坐标。
righti 是第 i 座建筑物右边缘的 x 坐标。
heighti 是第 i 座建筑物的高度。
你可以假设所有的建筑都是完美的长方形，在高度为 0 的绝对平坦的表面上。

天际线 应该表示为由 “关键点” 组成的列表，格式 [[x1,y1],[x2,y2],...] ，并按 x 坐标 进行 排序 。
关键点是水平线段的左端点。列表中最后一个点是最右侧建筑物的终点，y 坐标始终为 0 ，仅用于标记天际线的终点。
此外，任何两个相邻建筑物之间的地面都应被视为天际线轮廓的一部分。

注意：输出天际线中不得有连续的相同高度的水平线。例如 [...[2 3], [4 5], [7 5], [11 5], [12 7]...] 是不正确的答案；
三条高度为 5 的线应该在最终输出中合并为一个：[...[2 3], [4 5], [12 7], ...]

输入：buildings = [[2,9,10],[3,7,15],[5,12,12],[15,20,10],[19,24,8]]
输出：[[2,10],[3,15],[7,12],[12,0],[15,10],[20,8],[24,0]]
解释：
图 A 显示输入的所有建筑物的位置和高度，
图 B 显示由这些建筑物形成的天际线。图 B 中的红点表示输出列表中的关键点。

示例 2：
输入：buildings = [[0,2,3],[2,5,3]]
输出：[[0,3],[5,0]]

思路及算法

观察题目我们可以发现，关键点的横坐标总是落在建筑的左右边缘上。这样我们可以只考虑每一座建筑的边缘作为横坐标，这样其对应的纵坐标为「包含该横坐标」的所有建筑的最大高度。

观察示例一可以发现，当关键点为某建筑的右边缘时，该建筑的高度对关键点的纵坐标是没有贡献的。例如图中横坐标为 7 的关键点，虽然它落在红色建筑的右边缘，但红色建筑对其并纵坐标并没有贡献。
因此我们给出「包含该横坐标」的定义：建筑的左边缘小于等于该横坐标，右边缘大于该横坐标（也就是我们不考虑建筑的右边缘）。即对于包含横坐标 x 的建筑 i，有 x∈[left
i
​
 ,right
i
​
 )。

特别地，在部分情况下，「包含该横坐标」的建筑并不存在。
例如当图中只有一座建筑时，该建筑的左右边缘均对应一个关键点，当横坐标为其右边缘时，这唯一的建筑对其纵坐标没有贡献。因此该横坐标对应的纵坐标的大小为 0。

这样我们可以想到一个暴力的算法：O(n) 地枚举建筑的每一个边缘作为关键点的横坐标，过程中我们 O(n) 地检查每一座建筑是否「包含该横坐标」，找到最大高度，即为该关键点的纵坐标。该算法的时间复杂度是 O(n
2
 )，我们需要进行优化。

我们可以用优先队列来优化寻找最大高度的时间，在我们从左到右枚举横坐标的过程中，实时地更新该优先队列即可。这样无论何时，优先队列的队首元素即为最大高度。为了维护优先队列，我们需要使用「延迟删除」的技巧，即我们无需每次横坐标改变就立刻将优先队列中所有不符合条件的元素都删除，而只需要保证优先队列的队首元素「包含该横坐标」即可。

具体地，为了按顺序枚举横坐标，我们用数组 boundaries 保存所有的边缘，排序后遍历该数组即可。过程中，我们首先将「包含该横坐标」的建筑加入到优先队列中，然后不断检查优先队列的队首元素是否「包含该横坐标」，如果不「包含该横坐标」，我们就将该队首元素弹出队列，直到队空或队首元素「包含该横坐标」即可。最后我们用变量 maxn 记录最大高度（即纵坐标的值），当优先队列为空时，maxn=0，否则 maxn 即为队首元素。最后我们还需要再做一步检查：如果当前关键点的纵坐标大小与前一个关键点的纵坐标大小相同，则说明当前关键点无效，我们跳过该关键点即可。

在实际代码中，我们可以进行一个优化。因为每一座建筑的左边缘信息只被用作加入优先队列时的依据，当其加入优先队列后，我们只需要用到其高度信息（对最大高度有贡献）以及其右边缘信息（弹出优先队列的依据），因此只需要在优先队列中保存这两个元素即可。


class Solution {
    public List<List<Integer>> getSkyline(int[][] buildings) {
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>((a, b) -> b[1] - a[1]);
        List<Integer> boundaries = new ArrayList<Integer>();
        for (int[] building : buildings) {
            boundaries.add(building[0]);
            boundaries.add(building[1]);
        }
        Collections.sort(boundaries);

        List<List<Integer>> ret = new ArrayList<List<Integer>>();
        int n = buildings.length, idx = 0;
        for (int boundary : boundaries) {
            while (idx < n && buildings[idx][0] <= boundary) {
                pq.offer(new int[]{buildings[idx][1], buildings[idx][2]});
                idx++;
            }
            while (!pq.isEmpty() && pq.peek()[0] <= boundary) {
                pq.poll();
            }

            int maxn = pq.isEmpty() ? 0 : pq.peek()[1];
            if (ret.size() == 0 || maxn != ret.get(ret.size() - 1).get(1)) {
                ret.add(Arrays.asList(boundary, maxn));
            }
        }
        return ret;
    }
}

 */
class getSkyline2 {
    public List<List<Integer>> getSkyline(int[][] buildings) {
        Arrays.sort(buildings, (o1, o2) -> o1[0] - o2[0]);
        PriorityQueue<int[]> ints = new PriorityQueue<>((o1, o2) -> o1[0] - o2[0]);
        List<List<Integer>> result = new ArrayList<>();
        for (int[] item : buildings) {
            int[] left = {item[0], item[2]};
            int[] right = {item[1], 0};
            if (ints.isEmpty()) {
                ints.add(left);
                ints.add(right);
                continue;
            }

            int[] head = ints.poll();
            if (item[0] == head[0]) {
                head[1] = Math.max(head[1], item[1]);
                ints.add(head);
            } else {
                result.add(Arrays.stream(head).boxed().collect(Collectors.toList()));
            }
        }
        return result;
    }
}
