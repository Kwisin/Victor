package com.example.demo.algorithm.year26.month01;

import java.util.*;

public class Day12 {
    public static void main(String[] args) {
        Day12 day12 = new Day12();

        /*
        ================================================================================
        算法题 (Algorithm) - 滑动窗口 & 贪心
        ================================================================================
        */

        // 1. 最小覆盖子串 (Minimum Window Substring) - Hard
        // 输入：s = "ADOBECODEBANC", t = "ABC"
        // 输出："BANC"
        System.out.println(day12.minWindow("ADOBECODEBANC", "ABC"));
        System.out.println(day12.minWindow("a", "a")); // "a"
        System.out.println(day12.minWindow("a", "aa")); // ""

        // 2. 跳跃游戏 (Jump Game) - Medium
        int[] nums1 = {2, 3, 1, 1, 4};
        System.out.println(day12.canJump(nums1)); // true

        int[] nums1_2 = {3, 2, 1, 0, 4};
        System.out.println(day12.canJump(nums1_2)); // false

        // 3. 跳跃游戏 II (Jump Game II) - Medium
        int[] nums2 = {2, 3, 1, 1, 4};
        System.out.println(day12.jump(nums2)); // 2

        int[] nums2_2 = {2, 3, 0, 1, 4};
        System.out.println(day12.jump(nums2_2)); // 2


        /*
        ================================================================================
        SQL 练习题 - 聚合与日期函数
        ================================================================================

        1. 每天的日活跃用户数 (Daily Active Users)
        表: Activity (user_id, session_id, session_date, session_type)
        
        编写 SQL 查询，统计每天的日活跃用户数（DAU）。
        注意：同一用户在同一天多次活动只算一次。
        
        示例:
        Activity 表:
        +---------+------------+--------------+--------------+
        | user_id | session_id | session_date | session_type |
        +---------+------------+--------------+--------------+
        | 1       | 1          | 2019-07-20   | open_session |
        | 1       | 2          | 2019-07-20   | scroll_down  |
        | 1       | 3          | 2019-07-20   | end_session  |
        | 2       | 4          | 2019-07-20   | open_session |
        | 2       | 4          | 2019-07-21   | open_session |
        | 2       | 4          | 2019-07-21   | end_session  |
        | 3       | 5          | 2019-07-21   | open_session |
        | 3       | 5          | 2019-07-21   | scroll_down  |
        | 3       | 5          | 2019-07-21   | end_session  |
        +---------+------------+--------------+--------------+

        输出:
        +------------+--------------+
        | day        | active_users |
        +------------+--------------+
        | 2019-07-20 | 2            |
        | 2019-07-21 | 2            |
        +------------+--------------+


        select session_date as day,count(distinct(user_id)) as active_users from Activity group by session_date;


        --------------------------------------------------------------------------------

        2. 570. 至少有5名直接下属的经理
        表: Employee (id, name, department, managerId)
        
        编写一个SQL查询来查找至少有5名直接下属的经理的姓名。
        
        示例:
        Employee 表:
        +-----+-------+------------+-----------+
        | id  | name  | department | managerId |
        +-----+-------+------------+-----------+
        | 101 | John  | A          | None      |
        | 102 | Dan   | A          | 101       |
        | 103 | James | A          | 101       |
        | 104 | Amy   | A          | 101       |
        | 105 | Anne  | A          | 101       |
        | 106 | Ron   | B          | 101       |
        +-----+-------+------------+-----------+
        
        输出:
        +------+
        | name |
        +------+
        | John |
        +------+
        select name from Employee inner join (
        select managerId,count(distinct(name)) as cnt from Employee group by managerId) t
        on Employee.id = t.managerId where t.cnt >=5;

        ================================================================================
        Shell 脚本练习题 - 文本处理与网络
        ================================================================================

        请查看 shell_pkg/year26/month01/Day12.sh (需创建)
        任务主题：服务器安全检查 (Server Security Check)
        1. 检查 SSH 登录失败次数 (解析 /var/log/secure 或 auth.log)
        2. 检查开放端口 (netstat/ss)
        3. 检查大文件 (find)
        */
    }

    /*
    76. 最小覆盖子串 (Minimum Window Substring)
    给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
    
    注意：
    对于 t 中重复字符，我们寻找的子字符串中该字符数量必须不少于 t 中该字符数量。
    如果 s 中存在这样的子串，我们保证它是唯一的答案。

    示例 1：
    输入：s = "ADO BE CODE BA NC", t = "ABC"
    输出："BANC"
    解释：最小覆盖子串 "BANC" 包含来自字符串 t 的 'A'、'B' 和 'C'。

    示例 2：
    输入：s = "a", t = "a"
    输出："a"

    示例 3：
    输入：s = "a", t = "aa"
    输出：""
    解释：t 中两个字符 'a' 均应包含在 s 的子串中，
    因此没有符合条件的子字符串，返回空字符串。
    */
    public String minWindow(String s, String t) {
        if (s == null || s.length() < t.length()) {
            return "";
        }

        HashMap<Character, Integer> characterCount = new HashMap<>();
        for (char c : t.toCharArray()) {
            characterCount.put(c, characterCount.getOrDefault(c, 0) + 1);
        }

        String ans = "";
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i++) {
            char curr = s.charAt(i);
            if (characterCount.containsKey(curr)) {
                deque.addLast(i);
                characterCount.put(curr, characterCount.get(curr) - 1);
                while (checkFinish(characterCount)) {
                    Integer start = deque.pollFirst();
                    char toRemove = s.charAt(start);
                    characterCount.put(toRemove, characterCount.get(toRemove) + 1);

                    if (ans.isEmpty() || (i - start + 1) < ans.length()) {
                        ans = s.substring(start, i + 1);
                        if (ans.length() == t.length()) {
                            return ans;
                        }
                    }
                }
            }
        }


        return ans;
    }


    public boolean checkFinish(Map<Character, Integer> characterCount) {
        for (char c : characterCount.keySet()) {
            if (characterCount.get(c) > 0) {
                return false;
            }
        }

        return true;
    }

    /*
    55. 跳跃游戏 (Jump Game)
    给你一个非负整数数组 nums ，你最初位于数组的 第一个下标 。数组中的每个元素代表你在该位置可以跳跃的最大长度。
    判断你是否能够到达最后一个下标。

    示例 1：
    输入：nums = [2,3,1,1,4]
    输出：true
    解释：可以先跳 1 步，从下标 0 到达下标 1, 然后再从下标 1 跳 3 步到达最后一个下标。

    示例 2：
    输入：nums = [3,2,1,0,4]
    输出：false
    解释：无论怎样，总会到达下标 3 的位置。但该下标的最大跳跃长度是 0 ， 所以永远不可能到达最后一个下标。
    */
    public boolean canJump(int[] nums) {
        if (nums == null || nums.length == 0) {
            return true;
        }
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            nums[i] = nums[i] + i;
        }

        for (int i = 0; i < length; ) {
            if (nums[i] + i >= length - 1) {
                return true;
            }

            i = selectNext(nums, i + 1, i + nums[i]);
        }


        return false;
    }


    public int selectNext(int[] nums, int start, int end) {
        int index = -1;
        int max = Integer.MIN_VALUE;
        for (int i = start; i <= end; i++) {
            if (nums[i] + i > max) {
                max = nums[i] + i;
                index = i;
            }
        }


        return index;
    }

    /*
    45. 跳跃游戏 II (Jump Game II)
    给你一个非负整数数组 nums ，你最初位于数组的 第一个下标 。数组中的每个元素代表你在该位置可以跳跃的最大长度。
    你的目标是使用最少的跳跃次数到达数组的最后一个位置。
    假设你总是可以到达数组的最后一个位置。

    示例 1:
    输入: nums = [2,3,1,1,4]
    输出: 2
    解释: 跳到最后一个位置的最小跳跃数是 2。
         从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。

    示例 2:
    输入: nums = [2,3,0,1,4]
    输出: 2
    dp[0][4] = -1
    [0,0,0,0,-1]
    [0,0,0,0,1]
    [0,0,0,0,-1]
    [0,0,0,0,1]

    */
    public int jump(int[] nums) {
        return 0;
    }
}

