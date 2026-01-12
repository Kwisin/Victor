package com.example.demo.algorithm.year26.month01;

import java.util.*;

public class Day05 {
    public static void main(String[] args) {
        Day05 day05 = new Day05();

        /*
        ================================================================================
        算法题 (Algorithm)
        ================================================================================
        */

        // 1. 下一个排列 (Next Permutation)
        int[] nums1 = {1, 2, 3};
        day05.nextPermutation(nums1);
        System.out.println(Arrays.toString(nums1)); // [1, 3, 2]

        int[] nums1_2 = {3, 2, 1};
        day05.nextPermutation(nums1_2);
        System.out.println(Arrays.toString(nums1_2)); // [1, 2, 3]

        int[] nums1_3 = {1, 1, 5};
        day05.nextPermutation(nums1_3);
        System.out.println(Arrays.toString(nums1_3)); // [1, 5, 1]

        // 2. 乘积最大子数组 (Maximum Product Subarray)
        int[] nums2 = {2, 3, -2, 4};
        System.out.println(day05.maxProduct(nums2)); // 6

        int[] nums2_2 = {-2, 0, -1};
        System.out.println(day05.maxProduct(nums2_2)); // 0

        int[] nums2_3 = {-2};
        System.out.println(day05.maxProduct(nums2_3)); // -2

        // 3. 不同的子序列 (Distinct Subsequences)
        // 动态规划：dp[i][j] 表示 s[0..j] 中 t[0..i] 出现的次数
        System.out.println(day05.numDistinct("rabbbit", "rabbit")); // 3

        // 4. 加油站 (Gas Station)
        // 贪心算法：如果总油量减去总消耗大于等于零，那么一定可以跑完一圈
        int[] gas = {1, 2, 3, 4, 5};
        int[] cost = {3, 4, 5, 1, 2};
        System.out.println(day05.canCompleteCircuit(gas, cost)); // 3

        // 5. 单词拆分 (Word Break)
        // 动态规划 (中等)：dp[i] 表示 s[0..i-1] 是否可以被拆分
        // "leetcode", ["leet", "code"] -> true
        String s = "leetcode";
        List<String> wordDict = Arrays.asList("leet", "code");
        System.out.println(day05.wordBreak(s, wordDict)); // true

        // 6. 零钱兑换 (Coin Change)
        // 动态规划 (中等)：dp[i] 表示凑成金额 i 所需的最少硬币个数
        // coins = [1, 2, 5], amount = 11 -> 3 (5 + 5 + 1)
        int[] coins = {1, 2, 5};
        System.out.println(day05.coinChange(coins, 11)); // 3


        /*
        ================================================================================
        SQL 练习题
        ================================================================================

        1. 部门工资前三高的所有员工 (Department Top Three Salaries)
        表: Employee (id, name, salary, departmentId)
        表: Department (id, name)
        
        编写 SQL 查询，找出每个部门中收入排名前三的员工。
        
        示例:
        Employee 表:
        +----+-------+--------+--------------+
        | id | name  | salary | departmentId |
        +----+-------+--------+--------------+
        | 1  | Joe   | 85000  | 1            |
        | 2  | Henry | 80000  | 2            |
        | 3  | Sam   | 60000  | 2            |
        | 4  | Max   | 90000  | 1            |
        | 5  | Janet | 69000  | 1            |
        | 6  | Randy | 85000  | 1            |
        | 7  | Will  | 70000  | 1            |
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
        | IT         | Max      | 90000  |
        | IT         | Joe      | 85000  |
        | IT         | Randy    | 85000  |
        | IT         | Will     | 70000  |
        | Sales      | Henry    | 80000  |
        | Sales      | Sam      | 60000  |
        +------------+----------+--------+

        select employeeName,salary,departmentName from (
        select Employee.name as employeeName,
        Employee.salary as salary,
        Department.name as departmentName,
        RANK() OVER(partition by departmentId order by salary desc) as rk
        from Employee
        left join Department on Employee.departmentId = Department.id) t where t<=3;

        --------------------------------------------------------------------------------

        2. 分数排名 (Rank Scores)
        表: Scores (id, score)
        
        编写 SQL 查询对分数进行排序。排名按以下规则计算:
        - 分数应按从高到低排列。
        - 如果两个分数相等，那么两个分数的排名应该相同。
        - 在排名相同的分数后，排名数应该是连续的（不要跳过）。
        
        示例:
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
        select score,rank from (
        select score, RANK() OVER(ORDER BY score DESC) as rank from Scores) t order by rank asc;

        ================================================================================
        Shell 脚本练习题
        ================================================================================

        请查看 shell_pkg/year26/month01/Day05.sh 文件，完成其中的"日志分析与归档实战"。
        该题目包含了一整套流程：
        1. 环境准备与数据模拟
        2. 数据提取与分析（包含正则匹配）
        3. 重命名与归档（包含批量操作）
        4. 清理
        */
    }

    /*
    31. 下一个排列 (Next Permutation)
    整数数组的一个 排列  就是将其所有成员以序列或线性顺序排列。
    例如，arr = [1,2,3] ，以下这些都可以视作 arr 的排列：[1,2,3]、[1,3,2]、[3,1,2]、[2,3,1] 。
    
    "下一个排列" 是指其整数的下一个字典序更大的排列。
    更正式地，如果数组的所有排列根据其字典顺序从小到大排列在一个容器中，那么数组的 下一个排列 就是在这个有序容器中排在它后面的那个排列。
    如果不存在下一个更大的排列，那么这个数组必须重排为字典序最小的排列（即，其元素按升序排列）。

    示例 1：
    输入：nums = [1,2,3]
    输出：[1,3,2]
    
    示例 2：
    输入：nums = [3,2,1]
    输出：[1,2,3] (因为 321 是最大的，所以回到最小的 123)
    
    示例 3：
    输入：nums = [1,1,4,6,5,4,3,1] 11546  11645   11513446
    输出：[1,5,1]
    
    必须 原地 修改，只允许使用额外常数空间。

    要找的应该是挨得最近的
    */
    public void nextPermutation(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return;
        }
        int left = nums.length - 2;
        int right = nums.length - 1;
        while (left >= 0 && nums[left] >= nums[right]) {
            left--;
            right--;
        }
        if (left == -1) {
            reverse(nums, 0, nums.length - 1);
            return;
        }

        for (int i = nums.length - 1; i > left; i--) {
            if (nums[i] > nums[left]) {
                right = i;
                break;
            }
        }
        swap(nums, left, right);
        reverse(nums, left + 1, nums.length - 1);
    }

    public void swap(int[] nums, int i, int j) {
        int num = nums[i];
        nums[i] = nums[j];
        nums[j] = num;
    }

    public void reverse(int[] nums, int i, int j) {
        while (i < j) {
            swap(nums, i, j);
            i++;
            j--;
        }
    }

    /*
    152. 乘积最大子数组 (Maximum Product Subarray)
    给你一个整数数组 nums ，请你找出数组中乘积最大的非空连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。
    测试用例的答案是一个 32-位 整数。

    示例 1:
    输入: nums = [2,3,-2,-4,3,-2,1]
    2 6 -2  48  144   24    24
    2 3 -12 -4  -12  -288  -288
    [2,6,-2, 8]
    [0,3,-2, 24]
    [0,0,-2, 8]
    [0,0, 0,-4]


    [2,3,-2,-4,3,-2,1]
    [2,3,-2,-4,3,-2,1]
    [2,3,-2,-4,3,-2,1]
    [2,3,-2,-4,3,-2,1]
    [2,3,-2,-4,3,-2,1]
    [2,3,-2,-4,3,-2,1]
    [2,3,-2,-4,3,-2,1]

    dp[i][j]: 数组 nums 从 i 到 j 的最大乘积

    dp[i][j] = Math.max(dp[i+1][j]*nums[i],dp[i][j-1]*nums[j])

    输出: 6
    解释: 子数组 [2,3] 有最大乘积 6。
    */
    public int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int length = nums.length;
        int[] max = new int[length];
        int[] min = new int[length];
        max[0] = nums[0];
        min[0] = nums[0];
        int ans = nums[0];

        for (int i = 1; i < length; i++) {
            max[i] = Math.max(Math.max(max[i - 1] * nums[i], min[i - 1] * nums[i]), nums[i]);
            min[i] = Math.min(Math.min(max[i - 1] * nums[i], min[i - 1] * nums[i]), nums[i]);
            ans = Math.max(ans, max[i]);
        }

        return ans;
    }

    /*
    115. 不同的子序列 (Distinct Subsequences)
    给你两个字符串 s 和 t ，统计并返回在 s 的 子序列 中 t 出现的个数。
    题目数据保证答案符合 32 位带符号整数范围。

    示例 1：
    输入：s = "rar bbb it", t = "ra bb it"
    ra ra: dp[i-1][j]    s[i] == t[j] ? dp[i-1][j] + 1 : dp[i-1][j];
    rar r
    ra r
    r  ra               s[i] == t[j] ? dp[i-1][j-1]:0
    ra r
    [1,0,0,0]
    [1,1,0,0]
    [2,1,0,0]
    [2,0,0,0]
    [2,0,0,0]
    [2,0,0,0]

    rta rt
    [1,0]
    [1,1]
    [1,1]

    s.chatAt(i)  ==  t.charAt(j) ? dp[i-1][j-1]

    输出：3
    解释：
    如下所示, 有 3 种可以从 s 中得到 "rabbit" 的方案。
    (上箭头符号 ^ 表示选取的字母)
    rabbbit
    ^^^^ ^^
    rabbbit
    ^^ ^^^^
    rabbbit
    ^^^ ^^^
    */
    public int numDistinct(String s, String t) {
        return 0;
    }

    /*
    134. 加油站 (Gas Station)
    在一条环路上有 n 个加油站，其中第 i 个加油站有汽油 gas[i] 升。
    你有一辆油箱容量无限的的汽车，从第 i 个加油站开往第 i+1 个加油站需要消耗汽油 cost[i] 升。你从其中的一个加油站出发，开始时油箱为空。
    给定两个整数数组 gas 和 cost ，如果你可以绕环路行驶一周，则返回出发时加油站的编号，否则返回 -1 。如果存在解，则 保证 它是 唯一 的。

    示例 1:
    输入: gas = [1,2,3,4,5], cost = [3,4,5,1,2]
    输出: 3
    解释:
    从 3 号加油站(索引为 3 处)出发，可获得 4 升汽油。此时油箱有 = 0 + 4 = 4 升汽油
    开往 4 号加油站，此时油箱有 4 - 1 + 5 = 8 升汽油
    开往 0 号加油站，此时油箱有 8 - 2 + 1 = 7 升汽油
    开往 1 号加油站，此时油箱有 7 - 3 + 2 = 6 升汽油
    开往 2 号加油站，此时油箱有 6 - 4 + 3 = 5 升汽油
    开往 3 号加油站，你需要消耗 5 升汽油，正好足够你返回到 3 号加油站。
    因此，3 可为起始索引。
    */
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int len = gas.length;
        int[] gap = new int[len];
        for (int i = 0; i < len; i++) {
            gap[i] = gas[i] - cost[i];
        }

        for (int i = 0; i < len;) {
            if (gap[i] < 0) {
                i++;
                continue;
            }
            int index = checkComplement(gap, i);
            if (index == i){
                return i;
            }
            if (index < i) {
                return -1;
            }
            i = index+1;
        }

        return -1;
    }

    public int checkComplement(int[] gap, int start) {
        int curr = 0;
        int index = start;
        while (true) {
            curr += gap[index];
            if (curr < 0) {
                return index;
            }
            index = (index + 1) % gap.length;
            if (index == start) {
                return index;
            }
        }
    }

    /*
    139. 单词拆分 (Word Break)
    中等
    给你一个字符串 s 和一个字符串列表 wordDict 作为字典。如果可以利用字典中出现的单词拼接出 s ，则返回 true 。
    不要求字典中出现的单词全部都使用，并且字典中的单词可以重复使用。

    示例 1：
    输入: s = "leetcode", wordDict = ["leet", "code"]
    输出: true
    解释: 返回 true 因为 "leetcode" 可以由 "leet" 和 "code" 拼接成。

    示例 2：
    输入: s = "applepenapple", wordDict = ["apple", "pen"]
    输出: true
    解释: 返回 true 因为 "applepenapple" 可以由 "apple" "pen" "apple" 拼接成。
         注意，你可以重复使用字典中的单词。

    示例 3：
    输入: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
    输出: false
    */
    public boolean wordBreak(String s, List<String> wordDict) {
        return false;
    }

    /*
    322. 零钱兑换 (Coin Change)
    中等
    给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。
    计算并返回可以凑成总金额所需的 最少硬币个数 。如果没有任何一种硬币组合能组成总金额，返回 -1 。
    你可以认为每种硬币的数量是无限的。

    示例 1：
    输入：coins = [1, 2, 5], amount = 11
    输出：3
    解释：11 = 5 + 5 + 1

    示例 2：
    输入：coins = [2], amount = 3
    输出：-1

    示例 3：
    输入：coins = [1], amount = 0
    输出：0
    */
    public int coinChange(int[] coins, int amount) {
        return 0;
    }
}
