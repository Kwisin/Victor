package com.example.demo.algorithm.year26.month01;

import java.util.*;

public class Day06 {
    public static void main(String[] args) {
        Day06 day06 = new Day06();

        /*
        ================================================================================
        算法题 (Algorithm)
        ================================================================================
        */

        // 1. 复原 IP 地址 (Restore IP Addresses)
        // 回溯算法：需要判断由字符串分割出的数字是否在 0-255 之间，且不能有前导零。
        System.out.println(day06.restoreIpAddresses("25525511135"));
        // 输出: ["255.255.11.135","255.255.111.35"]

        // 2. 目标和 (Target Sum)
        // 动态规划 (0-1 背包变种)：给你一个整数数组 nums 和一个整数 target 。
        // 向数组中的每个整数前添加 '+' 或 '-' ，然后串联起所有整数，可以构造一个 表达式 。
        // 返回可以通过上述方法构造的、运算结果等于 target 的不同 表达式 的数目。
        int[] nums = {1, 1, 1, 1, 1};
        System.out.println(day06.findTargetSumWays(nums, 3)); // 5

        // 3. 最大的以 1 为边界的正方形 (Largest 1-Bordered Square)
        // 矩阵/前缀和：给你一个由若干 0 和 1 组成的二维网格 grid，请你找出边界全部由 1 组成的最大 正方形 子网格，并返回该子网格中的元素数量。
        int[][] grid = {
                {1, 1, 1},
                {1, 0, 1},
                {1, 1, 1}
        };
        System.out.println(day06.largest1BorderedSquare(grid)); // 9


        /*
        ================================================================================
        SQL 练习题
        ================================================================================

        1. 游戏玩法分析 IV (Game Play Analysis IV)
        表: Activity (player_id, device_id, event_date, games_played)
        
        编写一个 SQL 查询，报告在首次登录的第二天再次登录的玩家的比率，四舍五入到小数点后两位。
        换句话说，你需要计算从首次登录日期开始至少连续两天登录的玩家的数量，然后除以玩家总数。
        
        示例:
        player_id | event_date
        1         | 2016-03-01
        1         | 2016-03-02
        2         | 2017-06-25
        3         | 2016-03-02
        3         | 2018-07-03
        
        输出: 0.33
        解释: 只有玩家 1 在首次登录后第二天再次登录，所以 1/3 = 0.33

        --------------------------------------------------------------------------------

        2. 确认率 (Confirmation Rate)
        表: Signups (user_id, time_stamp), Confirmations (user_id, time_stamp, action)
        action 是 'confirmed' 或 'timeout'
        
        用户的 确认率 是 'confirmed' 消息的数量除以请求的确认消息的总数。没有请求任何确认消息的用户的确认率为 0 。
        编写一个 SQL 查询来查找每个用户的确认率。将确认率四舍五入到小数点后两位。
        
        输出格式: user_id | confirmation_rate

        ================================================================================
        Shell 脚本练习题
        ================================================================================

        请查看 shell_pkg/year26/month01/Day06.sh 文件，完成今天的脚本任务。
        任务主题：系统巡检与告警 (System Health Check)
        1. CPU 负载检测
        2. 磁盘空间检测
        3. 发送模拟告警
        */
    }

    /*
    93. 复原 IP 地址 (Restore IP Addresses)
    有效 IP 地址 正好由四个整数（每个整数位于 0 到 255 之间组成，且不能含有前导 0），整数之间用 '.' 分隔。
    例如："0.1.2.201" 和 "192.168.1.1" 是 有效 IP 地址，但是 "0.011.255.245"、"192.168.1.312" 和 "192.168@1.1" 是 无效 IP 地址。
    给定一个只包含数字的字符串 s ，用以表示一个 IP 地址，返回所有可能的有效 IP 地址，这些地址可以通过在 s 中插入 '.' 来形成。
    */
    public List<String> restoreIpAddresses(String s) {
        return new ArrayList<>();
    }

    /*
    494. 目标和 (Target Sum)
    给你一个非负整数数组 nums 和一个整数 target 。
    向数组中的每个整数前添加 '+' 或 '-' ，然后串联起所有整数，可以构造一个 表达式 ：
    例如，nums = [2, 1] ，可以在 2 之前添加 '+' ，在 1 之前添加 '-' ，然后串联起来得到表达式 "+2-1" 。
    返回可以通过上述方法构造的、运算结果等于 target 的不同 表达式 的数目。
    */
    public int findTargetSumWays(int[] nums, int target) {
        return 0;
    }

    /*
    1139. 最大的以 1 为边界的正方形 (Largest 1-Bordered Square)
    给你一个由若干 0 和 1 组成的二维网格 grid，请你找出边界全部由 1 组成的最大 正方形 子网格，并返回该子网格中的元素数量。如果不存在，则返回 0。
    
    示例 1：
    输入：grid = [[1,1,1],[1,0,1],[1,1,1]]
    输出：9
    */
    public int largest1BorderedSquare(int[][] grid) {
        return 0;
    }
}

