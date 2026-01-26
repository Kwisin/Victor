package com.example.demo.algorithm.year26.month01;

import java.util.*;

public class Day13 {
    public static void main(String[] args) {
        Day13 day13 = new Day13();

        /*
        ================================================================================
        算法题 (Algorithm) - 图论与堆 (Graph & Heap)
        ================================================================================
        */

        // 1. 200. 岛屿数量 (Number of Islands) - Medium
        // 给你一个由 '1' (陆地) 和 '0' (水) 组成的的二维网格，请你计算网格中岛屿的数量。
        // 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
        // 此外，你可以假设该网格的四条边均被水包围。
        char[][] grid1 = {
                {'1', '1', '1', '1', '0'},
                {'1', '1', '0', '1', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '0', '0', '0'}
        };
        System.out.println(day13.numIslands(grid1)); // 1

        char[][] grid2 = {
                {'1', '1', '0', '0', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '1', '0', '0'},
                {'0', '0', '0', '1', '1'}
        };
        System.out.println(day13.numIslands(grid2)); // 3

        // 2. 207. 课程表 (Course Schedule) - Medium
        // 你这个学期必须选修 numCourses 门课程，记为 0 到 numCourses - 1 。
        // 在选修某些课程之前需要一些先修课程。 先修课程按数组 prerequisites 给出，其中 prerequisites[i] = [ai, bi] ，
        // 表示如果要学习课程 ai 则 必须 先学习课程  bi 。
        // 请你判断是否可能完成所有课程的学习？
        int[][] prerequisites1 = {{1, 0}};
        System.out.println(day13.canFinish(2, prerequisites1)); // true

        int[][] prerequisites2 = {{1, 0}, {0, 1}};
        System.out.println(day13.canFinish(2, prerequisites2)); // false

        // 3. 215. 数组中的第K个最大元素 (Kth Largest Element in an Array) - Medium
        // 给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
        // 请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
        // 你必须设计并实现时间复杂度为 O(n) 的算法解决此问题。
        int[] nums3 = {3, 2, 1, 5, 6, 4};
        System.out.println(day13.findKthLargest(nums3, 2)); // 5

        int[] nums4 = {3, 2, 3, 1, 2, 4, 5, 5, 6};
        System.out.println(day13.findKthLargest(nums4, 4)); // 4

        /*
        ================================================================================
        SQL 练习题 - 逻辑判断与空值处理
        ================================================================================

        1. 626. 换座位 (Exchange Seats) - Medium
        表: Seat
        +-------------+---------+
        | Column Name | Type    |
        +-------------+---------+
        | id          | int     |
        | student     | varchar |
        +-------------+---------+
        id 是该表的主键（自增序列）。
        该表的每一行表示学生的姓名和 ID。
        id 是一个连续递增的数字。
        
        编写 SQL 查询来交换每两个连续的学生的座位号。
        如果学生的数量是奇数，则最后一个学生的 id 不交换。
        按 id 升序返回结果表。
        
        示例 1:
        输入: 
        Seat 表:
        +----+---------+
        | id | student |
        +----+---------+
        | 1  | Abbot   |
        | 2  | Doris   |
        | 3  | Emerson |
        | 4  | Green   |
        | 5  | Jeames  |
        +----+---------+
        
        输出: 
        +----+---------+
        | id | student |
        +----+---------+
        | 1  | Doris   |
        | 2  | Abbot   |
        | 3  | Green   |
        | 4  | Emerson |
        | 5  | Jeames  |
        +----+---------+
        
        解释: 
        请注意，如果学生人数是奇数，则不需要更换最后一个同学的座位。

        --------------------------------------------------------------------------------

        2. 176. 第二高的薪水 (Second Highest Salary) - Medium
        表: Employee
        +-------------+------+
        | Column Name | Type |
        +-------------+------+
        | id          | int  |
        | salary      | int  |
        +-------------+------+
        id 是这个表的主键。
        表的每一行包含员工的工资信息。
        
        查询并返回 Employee 表中第二高的不同薪水。
        如果不存在第二高的薪水，查询应该返回 null（Pandas 则返回 None）。
        
        示例 1:
        输入: 
        Employee 表:
        +----+--------+
        | id | salary |
        +----+--------+
        | 1  | 100    |
        | 2  | 200    |
        | 3  | 300    |
        +----+--------+
        
        输出: 
        +---------------------+
        | SecondHighestSalary |
        +---------------------+
        | 200                 |
        +---------------------+

        示例 2:
        输入: 
        Employee 表:
        +----+--------+
        | id | salary |
        +----+--------+
        | 1  | 100    |
        +----+--------+
        
        输出: 
        +---------------------+
        | SecondHighestSalary |
        +---------------------+
        | null                |
        +---------------------+

        */
    }

    /*
    200. 岛屿数量 (Number of Islands) - Medium
    
    给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
    
    岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
    
    此外，你可以假设该网格的四条边均被水包围。
    
    示例 1:
    输入: grid = [
      ["1","1","1","1","0"],
      ["1","1","0","1","0"],
      ["1","1","0","0","0"],
      ["0","0","0","0","0"]
    ]
    输出: 1
    
    示例 2:
    输入: grid = [
      ["1","1","0","0","0"],
      ["1","1","0","0","0"],
      ["0","0","1","0","0"],
      ["0","0","0","1","1"]
    ]
    输出: 3
    
    提示:
    - m == grid.length
    - n == grid[i].length
    - 1 <= m, n <= 300
    - grid[i][j] 的值为 '0' 或 '1'
    */
    public int numIslands(char[][] grid) {
        return 0;
    }

    /*
    207. 课程表 (Course Schedule) - Medium
    
    你这个学期必须选修 numCourses 门课程，记为 0 到 numCourses - 1。
    
    在选修某些课程之前需要一些先修课程。先修课程按数组 prerequisites 给出，
    其中 prerequisites[i] = [ai, bi]，表示如果要学习课程 ai 则 必须 先学习课程 bi。
    
    例如，先修课程对 [0, 1] 表示：想要学习课程 0，你需要先完成课程 1。
    
    请你判断是否可能完成所有课程的学习？如果可以，返回 true；否则，返回 false。
    
    示例 1:
    输入: numCourses = 2, prerequisites = [[1,0]]
    输出: true
    解释: 总共有 2 门课程。学习课程 1 之前，你需要完成课程 0。这是可能的。
    
    示例 2:
    输入: numCourses = 2, prerequisites = [[1,0],[0,1]]
    输出: false
    解释: 总共有 2 门课程。学习课程 1 之前，你需要先完成​课程 0；
          并且学习课程 0 之前，你还应先完成课程 1。这是不可能的。
    
    提示:
    - 1 <= numCourses <= 2000
    - 0 <= prerequisites.length <= 5000
    - prerequisites[i].length == 2
    - 0 <= ai, bi < numCourses
    - prerequisites[i] 中的所有课程对 互不相同
    */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if (numCourses == 0) {
            return true;
        }
        if (prerequisites.length == 0) {
            return false;
        }

        // 标识从 index出发下一个课程号
        int[] next = new int[numCourses];
        Arrays.fill(next, -1);
        for (int[] item : prerequisites) {
            next[item[1]] = item[0];
        }

        // 记录从 index出发最远的距离，避免重复计算
        int[] distance = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            if (distance[i] == 0) {

                ArrayList<Integer> curr = new ArrayList<>();
                canFinishDFS(new HashSet<>(), i, curr, next);
                // 已存在课程序列，直接返回
                if (curr.size() >= numCourses) {
                    return true;
                }

                // 没达到数量要求，更新 distance
                int dis = curr.size();
                for (Integer j : curr) {
                    distance[j] = dis;
                    dis--;
                }
            }
        }


        return false;
    }


    public void canFinishDFS(HashSet<Integer> visited, int start, List<Integer> curr, int[] next) {
        if (visited.contains(start) || next[start] == -1) {
            return;
        }
        visited.add(start);
        curr.add(start);
        canFinishDFS(visited, next[start], curr, next);
    }

    /*
    215. 数组中的第K个最大元素 (Kth Largest Element in an Array) - Medium
    
    给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
    
    请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
    
    你必须设计并实现时间复杂度为 O(n) 的算法解决此问题。
    
    示例 1:
    输入: nums = [3,2,1,5,6,4], k = 2
    输出: 5
    
    示例 2:
    输入: nums = [3,2,3,1,2,4,5,5,6], k = 4
    输出: 4
    
    提示:
    - 1 <= k <= nums.length <= 10^5
    - -10^4 <= nums[i] <= 10^4
    */
    public int findKthLargest(int[] nums, int k) {
        return 0;
    }
}

