package com.example.demo.algorithm.year24.demo2412;


import java.util.*;

public class Demo1217 {

    public static void main(String[] args) {

        boolean b = new canFinish().canFinish(5, new int[][]{{1,4},{2,4},{3,1},{3,2}});
        System.out.println();
    }


}


// 207
/*
5
[1,4],[2,4],[3,1],[3,2]
你这个学期必须选修 numCourses 门课程，记为 0 到 numCourses - 1 。
在选修某些课程之前需要一些先修课程。 先修课程按数组 prerequisites 给出，
其中 prerequisites[i] = [ai, bi] ，表示如果要学习课程 ai 则 必须 先学习课程  bi 。
例如，先修课程对 [0, 1] 表示：想要学习课程 0 ，你需要先完成课程 1 。
请你判断是否可能完成所有课程的学习？如果可以，返回 true ；否则，返回 false

输入：numCourses = 2, prerequisites = [[1,0]]
输出：true
解释：总共有 2 门课程。学习课程 1 之前，你需要完成课程 0 。这是可能的。

输入：numCourses = 2, prerequisites = [[1,0],[0,1]]
输出：false
解释：总共有 2 门课程。学习课程 1 之前，你需要先完成​课程 0 ；并且学习课程 0 之前，你还应先完成课程 1 。这是不可能的。

prerequisites[i]中的所有课程对 互不相同


[[0,10],[3,18],[5,5],[6,11],[11,14],[13,1],[15,1],[17,4]]

20

1 <= numCourses <= 2000
0 <= prerequisites.length <= 5000
prerequisites[i].length == 2
0 <= ai, bi < numCourses

 */

// todo check 题目理解问题
class canFinish {
    private int numCourses = -1;
    private boolean canFinish = false;

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if (numCourses == 0) {
            return true;
        }
        if (prerequisites.length == 0) {
            return true;
        }
        this.numCourses = numCourses;

        // 构造图
        HashMap<Integer, HashSet<Integer>> integerHashSetHashMap = new HashMap<>();
        for (int[] item : prerequisites) {
            HashSet<Integer> orDefault = integerHashSetHashMap.getOrDefault(item[0], new HashSet<Integer>());
            orDefault.add(item[1]);
            integerHashSetHashMap.put(item[0], orDefault);
        }

        for (Integer key : integerHashSetHashMap.keySet()) {
            HashSet<Integer> exist = new HashSet<>();
            int num = 1;
            dfs(key, integerHashSetHashMap, exist, num);
            if (this.canFinish) {
                return true;
            }
        }

        return false;
    }

    public void dfs(Integer key, HashMap<Integer, HashSet<Integer>> integerHashSetHashMap, HashSet<Integer> exist, int num) {
        HashSet<Integer> integerHashSet = integerHashSetHashMap.getOrDefault(key, new HashSet<>());
        if (integerHashSet.size() == 0) {
            this.canFinish = num == this.numCourses;
        } else {
            for (Integer item : integerHashSet) {
                if (exist.contains(item)) {
                    return;
                }
                num++;
                exist.add(item);
                dfs(item, integerHashSetHashMap, exist, num);
                exist.remove(item);
            }

        }
    }

}

//class canFinish {
//    List<List<Integer>> edges;
//    int[] visited;
//    boolean valid = true;
//
//    public boolean canFinish(int numCourses, int[][] prerequisites) {
//        edges = new ArrayList<List<Integer>>();
//        for (int i = 0; i < numCourses; ++i) {
//            edges.add(new ArrayList<Integer>());
//        }
//        visited = new int[numCourses];
//        for (int[] info : prerequisites) {
//            edges.get(info[1]).add(info[0]);
//        }
//        for (int i = 0; i < numCourses && valid; ++i) {
//            if (visited[i] == 0) {
//                dfs(i);
//            }
//        }
//        return valid;
//    }
//
//    public void dfs(int u) {
//        visited[u] = 1;
//        for (int v: edges.get(u)) {
//            if (visited[v] == 0) {
//                dfs(v);
//                if (!valid) {
//                    return;
//                }
//            } else if (visited[v] == 1) {
//                valid = false;
//                return;
//            }
//        }
//        visited[u] = 2;
//    }
//}

// 210
/*
现在你总共有 numCourses 门课需要选，记为 0 到 numCourses - 1。给你一个数组 prerequisites ，其中 prerequisites[i] = [ai, bi] ，表示在选修课程 ai 前 必须 先选修 bi 。

例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示：[0,1] 。
返回你为了学完所有课程所安排的学习顺序。可能会有多个正确的顺序，你只要返回 任意一种 就可以了。如果不可能完成所有课程，返回 一个空数组 。

示例 1：
输入：numCourses = 2, prerequisites = [[1,0]]
输出：[0,1]
解释：总共有 2 门课程。要学习课程 1，你需要先完成课程 0。因此，正确的课程顺序为 [0,1] 。

示例 2：
输入：numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
输出：[0,2,1,3]
解释：总共有 4 门课程。要学习课程 3，你应该先完成课程 1 和课程 2。并且课程 1 和课程 2 都应该排在课程 0 之后。
因此，一个正确的课程顺序是 [0,1,2,3] 。另一个正确的排序是 [0,2,1,3] 。

示例 3：
输入：numCourses = 1, prerequisites = []
输出：[0]


 */

class findOrder {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        return null;

    }
}