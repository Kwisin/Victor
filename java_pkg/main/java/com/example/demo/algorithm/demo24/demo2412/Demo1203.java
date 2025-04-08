package com.example.demo.algorithm.demo24.demo2412;


import java.util.*;

public class Demo1203 {

    public static void main(String[] args) {
        int i = new longestConsecutive().longestConsecutive(new int[]{0, -1});
        System.out.println();
    }


}

//49
/*
给你一个字符串数组，请你将 字母异位词 组合在一起。可以按任意顺序返回结果列表。

字母异位词 是由重新排列源单词的所有字母得到的一个新单词

示例 1:
输入: strs = ["eat", "tea", "tan", "ate", "nat", "bat"]
输出: [["bat"],["nat","tan"],["ate","eat","tea"]]
示例 2:
输入: strs = [""]
输出: [[""]]
示例 3:
输入: strs = ["a"]
输出: [["a"]]

1 <= strs.length <= 104
0 <= strs[i].length <= 100
strs[i] 仅包含小写字母
 */

// 思路：用一个List<Map> 来保存字母的映射
// List<List<String>>来保存对应List<Map>字母异位词结果

class groupAnagrams {


    //
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> stringMap = new HashMap<>();
        List<List<String>> result = new ArrayList<>();
        for (String item : strs) {
            char[] chars = item.toCharArray();
            Arrays.sort(chars);
            String newItem = String.valueOf(chars);
            List<String> orDefault = stringMap.getOrDefault(newItem, new ArrayList<>());
            orDefault.add(item);
            stringMap.put(newItem, orDefault);
        }

        for (String key : stringMap.keySet()) {
            result.add(stringMap.get(key));
        }


        return result;

    }

}

//128
/*
给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
请你设计并实现时间复杂度为 O(n) 的算法解决此问题。

示例 1：
输入：nums = [100,4,200,1,3,2]
输出：4
解释：最长数字连续序列是 [1, 2, 3, 4]。它的长度为 4。

示例 2：
输入：nums = [0,3,7,2,5,8,4,6,0,1]
0   0 1 2 3 4 5 6 7 8
输出：9

0 <= nums.length <= 105
-109 <= nums[i] <= 109
0<= <= 218
 */

class longestConsecutive {
    public int longestConsecutive(int[] nums) {

        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int item : nums) {
            if (item < min) {
                min = item;
            }
            if (item > max) {
                max = item;
            }
        }
        int[] ints;
        if (min < 0) {
            ints = new int[max - min + 1];
        } else {
            ints = new int[max + min + 1];
        }
        for (int item : nums) {
            if (min < 0) {
                ints[item - min] = 1;
            } else {
                ints[item] = 1;
            }
        }

        int result = 0;
        int start = 0;
        int end = 0;
        // 0,1,2,3,4,5,6,7,8
        //0,2,3,4,5,6,6,6,7,9,11,2,3
        // 1,0,1,1,0,1,1,1,1
        for (end = 0; end < ints.length; end++) {
            if (ints[end] == 0) {
                result = Math.max(result, end - start);
                start = end + 1;
            }
        }
        result = Math.max(result, end - start);
        return result;

    }
}

//452
/*
有一些球形气球贴在一堵用 XY 平面表示的墙面上。墙面上的气球记录在整数数组 points ，其中points[i] = [xstart, xend] 表示水平直径在 xstart 和 xend之间的气球。你不知道气球的确切 y 坐标。
一支弓箭可以沿着 x 轴从不同点 完全垂直 地射出。在坐标 x 处射出一支箭，若有一个气球的直径的开始和结束坐标为 xstart，xend， 且满足  xstart ≤ x ≤ xend，则该气球会被 引爆 。可以射出的弓箭的数量 没有限制 。 弓箭一旦被射出之后，可以无限地前进。
给你一个数组 points ，返回引爆所有气球所必须射出的 最小 弓箭数

示例 1：
输入：points = [[10,16],[2,8],[1,6],[7,12]]
输出：2
解释：气球可以用2支箭来爆破:
-在x = 6处射出箭，击破气球[2,8]和[1,6]。
-在x = 11处发射箭，击破气球[10,16]和[7,12]。

示例 2：
输入：points = [[1,2],[3,4],[5,6],[7,8]]
输出：4
解释：每个气球需要射出一支箭，总共需要4支箭。

示例 3：
输入：points = [[1,2],[2,3],[3,4],[4,5]]
输出：2
解释：气球可以用2支箭来爆破:
- 在x = 2处发射箭，击破气球[1,2]和[2,3]。
- 在x = 4处射出箭，击破气球[3,4]和[4,5]。

 1 <= points.length <= 105
points[i].length == 2
-231 <= xstart < xend <= 231 - 1
 */

class findMinArrowShots {
    public int findMinArrowShots(int[][] points) {

        Arrays.sort(points, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        /*
                   [  ]
             [   ]
          [   ]
        [   ]
         */
        int[] target = points[0];
        int result = 1;
        for (int[] item : points) {
            // 判断和target是否有交集
            if (item[0] >= target[0] && item[0] <= target[1]) {
                //有交集
                target = new int[]{Math.max(item[0], target[0]), Math.min(item[1], target[1])};
            } else {
                //没有交集
                result++;
                target = item;
            }
        }

        return result;

    }
}