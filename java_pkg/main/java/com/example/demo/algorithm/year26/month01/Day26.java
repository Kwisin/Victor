package com.example.demo.algorithm.year26.month01;

import java.util.*;

public class Day26 {
    public static void main(String[] args) {
        Day26 day26 = new Day26();

        /*
        ================================================================================
        算法题 (Algorithm) - 动态规划与回溯 (DP & Backtracking)
        ================================================================================
        */

        // 1. 322. 零钱兑换 (Coin Change) - Medium
        // 给你一个整数数组 coins，表示不同面额的硬币；以及一个整数 amount，表示总金额。
        // 计算并返回可以凑成总金额所需的 最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回 -1。
        // 你可以认为每种硬币的数量是无限的。
        int[] coins1 = {1, 2, 5};
        System.out.println(day26.coinChange(coins1, 11)); // 3 (11 = 5 + 5 + 1)

        int[] coins2 = {2};
        System.out.println(day26.coinChange(coins2, 3)); // -1

        int[] coins3 = {1};
        System.out.println(day26.coinChange(coins3, 0)); // 0

        // 2. 39. 组合总和 (Combination Sum) - Medium
        // 给你一个 无重复元素 的整数数组 candidates 和一个目标整数 target，
        // 找出 candidates 中可以使数字和为目标数 target 的所有 不同组合，并返回这些组合列表。
        // candidates 中的 同一个 数字可以 无限制重复被选取。
        // 如果至少一个数字的被选数量不同，则两种组合是不同的。
        int[] candidates1 = {2, 3, 6, 7};
        System.out.println(day26.combinationSum(candidates1, 7)); 
        // [[2,2,3], [7]]

        int[] candidates2 = {2, 3, 5};
        System.out.println(day26.combinationSum(candidates2, 8));
        // [[2,2,2,2], [2,3,3], [3,5]]

        // 3. 46. 全排列 (Permutations) - Medium
        // 给定一个不含重复数字的数组 nums，返回其所有可能的全排列。你可以按任意顺序返回答案。
        int[] nums1 = {1, 2, 3};
        System.out.println(day26.permute(nums1));
        // [[1,2,3], [1,3,2], [2,1,3], [2,3,1], [3,1,2], [3,2,1]]

        int[] nums2 = {0, 1};
        System.out.println(day26.permute(nums2));
        // [[0,1], [1,0]]

        /*
        ================================================================================
        SQL 练习题 - 分组统计与窗口函数
        ================================================================================

        1. 184. 部门工资最高的员工 (Department Highest Salary) - Medium
        
        表: Employee
        +--------------+---------+
        | Column Name  | Type    |
        +--------------+---------+
        | id           | int     |
        | name         | varchar |
        | salary       | int     |
        | departmentId | int     |
        +--------------+---------+
        id 是该表的主键（具有唯一值的列）。
        departmentId 是 Department 表中 id 的外键（reference 列）。
        该表的每一行都表示员工的 id、姓名和工资。它还包含他们所属部门的 id。
        
        表: Department
        +-------------+---------+
        | Column Name | Type    |
        +-------------+---------+
        | id          | int     |
        | name        | varchar |
        +-------------+---------+
        id 是该表的主键（具有唯一值的列）。
        该表的每一行都表示一个部门的 id 及其名称。
        
        查找出每个部门工资最高的员工。
        返回结果表 可以按 任意顺序 排列。
        
        示例 1:
        输入:
        Employee 表:
        +----+-------+--------+--------------+
        | id | name  | salary | departmentId |
        +----+-------+--------+--------------+
        | 1  | Joe   | 70000  | 1            |
        | 2  | Jim   | 90000  | 1            |
        | 3  | Henry | 80000  | 2            |
        | 4  | Sam   | 60000  | 2            |
        | 5  | Max   | 90000  | 1            |
        +----+-------+--------+--------------+
        
        Department 表:
        +----+-------+
        | id | name  |
        +----+-------+
        | 1  | IT    |
        | 2  | Sales |
        +----+-------+
        
        输出:
        +------------+----------+--------+
        | Department | Employee | Salary |
        +------------+----------+--------+
        | IT         | Jim      | 90000  |
        | IT         | Max      | 90000  |
        | Sales      | Henry    | 80000  |
        +------------+----------+--------+
        
        解释:
        Max 和 Jim 在 IT 部门的工资都是最高的，Henry 在 Sales 部门的工资最高。

        --------------------------------------------------------------------------------

        2. 178. 分数排名 (Rank Scores) - Medium
        
        表: Scores
        +-------------+---------+
        | Column Name | Type    |
        +-------------+---------+
        | id          | int     |
        | score       | decimal |
        +-------------+---------+
        id 是该表的主键（具有唯一值的列）。
        该表的每一行都包含了一场比赛的分数。
        
        查询并对分数进行排序。排名按以下规则计算:
        - 分数应按从高到低排列。
        - 如果两个分数相等，那么两个分数的排名应该相同。
        - 在排名相同的分数后，排名数应该是下一个连续的整数。
          换句话说，排名之间 不应该 有空缺的数字。
        
        按 score 降序返回结果表。
        
        示例 1:
        输入:
        Scores 表:
        +----+-------+
        | id | score |
        +----+-------+
        | 1  | 3.50  |
        | 2  | 3.65  |
        | 3  | 4.00  |
        | 4  | 3.85  |
        | 5  | 4.00  |
        | 6  | 3.65  |
        +----+-------+
        
        输出:
        +-------+------+
        | score | rank |
        +-------+------+
        | 4.00  | 1    |
        | 4.00  | 1    |
        | 3.85  | 2    |
        | 3.65  | 3    |
        | 3.65  | 3    |
        | 3.50  | 4    |
        +-------+------+
        
        解释:
        分数 4.00 有两个，排名都是 1。
        下一个排名是 2（不是 3），因为使用的是密集排名（DENSE_RANK）。

        */
    }

    /*
    322. 零钱兑换 (Coin Change)
    
    给你一个整数数组 coins，表示不同面额的硬币；以及一个整数 amount，表示总金额。
    
    计算并返回可以凑成总金额所需的 最少的硬币个数。
    如果没有任何一种硬币组合能组成总金额，返回 -1。
    
    你可以认为每种硬币的数量是无限的。
    
    示例 1:
    输入: coins = [1, 2, 5], amount = 11
    输出: 3
    解释: 11 = 5 + 5 + 1
    
    示例 2:
    输入: coins = [2], amount = 3
    输出: -1
    
    示例 3:
    输入: coins = [1], amount = 0
    输出: 0
    
    提示:
    - 1 <= coins.length <= 12
    - 1 <= coins[i] <= 2^31 - 1
    - 0 <= amount <= 10^4
    */
    public int coinChange(int[] coins, int amount) {
        return -1;
    }

    /*
    39. 组合总和 (Combination Sum)
    
    给你一个 无重复元素 的整数数组 candidates 和一个目标整数 target，
    找出 candidates 中可以使数字和为目标数 target 的所有 不同组合，并返回这些组合列表。
    
    candidates 中的 同一个 数字可以 无限制重复被选取。
    如果至少一个数字的被选数量不同，则两种组合是不同的。
    
    对于给定的输入，保证和为 target 的不同组合数少于 150 个。
    
    示例 1:
    输入: candidates = [2,3,6,7], target = 7
    输出: [[2,2,3],[7]]
    解释:
    2 和 3 可以形成一组候选，2 + 2 + 3 = 7 。注意 2 可以使用多次。
    7 也是一个候选， 7 = 7 。
    仅有这两种组合。
    
    示例 2:
    输入: candidates = [2,3,5], target = 8
    输出: [[2,2,2,2],[2,3,3],[3,5]]
    
    示例 3:
    输入: candidates = [2], target = 1
    输出: []
    
    提示:
    - 1 <= candidates.length <= 30
    - 2 <= candidates[i] <= 40
    - candidates 的所有元素 互不相同
    - 1 <= target <= 40
    */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        return new ArrayList<>();
    }

    /*
    46. 全排列 (Permutations)
    
    给定一个不含重复数字的数组 nums，返回其所有可能的全排列。你可以按任意顺序返回答案。
    
    示例 1:
    输入: nums = [1,2,3]
    输出: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
    
    示例 2:
    输入: nums = [0,1]
    输出: [[0,1],[1,0]]
    
    示例 3:
    输入: nums = [1]
    输出: [[1]]
    
    提示:
    - 1 <= nums.length <= 6
    - -10 <= nums[i] <= 10
    - nums 中的所有整数 互不相同
    */
    public List<List<Integer>> permute(int[] nums) {
        return new ArrayList<>();
    }
}
