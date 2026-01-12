package com.example.demo.algorithm.year26.month01;

import java.util.*;

public class Day04 {
    public static void main(String[] args) {
        Day04 day04 = new Day04();

        /*
        ================================================================================
        算法题 (Algorithm)
        ================================================================================
        */

        // 1. 最长回文子串 (Longest Palindromic Substring)
        // 之前做过，复习一下 DP 或 中心扩展法
        System.out.println(day04.longestPalindrome("babad")); // "bab" 或 "aba"

        // 2. 接雨水 (Trapping Rain Water)
        // 经典难题，双指针或单调栈
        int[] height = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        System.out.println(day04.trap(height)); // 6


        /*
        ================================================================================
        SQL 练习题
        ================================================================================

        1. 超过5名学生的课 (Classes More Than 5 Students)
        表: Courses (student, class)
        
        编写一个 SQL 查询，找出所有至少有 5 个学生选修的课程。
        
        示例:
        student | class
        A       | Math
        B       | English
        C       | Math
        D       | Biology
        E       | Math
        F       | Computer
        G       | Math
        H       | Math
        I       | Math
        
        输出: Math
        (注意：学生在每个课中不应被重复计算)


        select class,count(distinct(student))as cn from Courses group by class having cn>=5;

        --------------------------------------------------------------------------------

        2. 体育馆的人流量 (Human Traffic of Stadium)
        表: Stadium (id, visit_date, people)
        visit_date 是主键，id 是自增的。
        
        编写 SQL 查询，找出人流量高峰期。
        高峰期定义：至少连续三行记录中的人流量不少于 100。
        
        示例:
        id | visit_date | people
        1  | 2017-01-01 | 10
        2  | 2017-01-02 | 109
        3  | 2017-01-03 | 150
        4  | 2017-01-04 | 99
        5  | 2017-01-05 | 145
        6  | 2017-01-06 | 1455
        7  | 2017-01-07 | 199
        8  | 2017-01-09 | 188
        
        输出:
        id | visit_date | people
        5  | 2017-01-05 | 145
        6  | 2017-01-06 | 1455
        7  | 2017-01-07 | 199
        8  | 2017-01-09 | 188
        
        (注意：id 5,6,7,8 满足条件，虽然日期不一定连续，但 id 是连续的)



        LAG(id,1) OVER(ORDER BY ID ASC)
        LAG(id,2) OVER(ORDER BY ID ASC)
         LEAD(id,1) OVER(ORDER BY ID ASC)
        LEAD(id,2) OVER(ORDER BY ID ASC)



        select id,visit_date,people from (
        select id,
        visit_date,
        people,
        LAG(id,1) (ORDER BY id) as lag1,
        LAG(id,2) (ORDER BY id) as lag2,
        LEAD(id,1) (ORDER BY id)as lead1,
        LEAD(id,2) (ORDER BY id) as lead2
         from Stadium
         where people>=100
         order by id asc) as t where( t.id-1 = lag1 and t.id-2 = lag2) or
         ( t.id-1 = lag1 and t.id+1 = lead1) or
         ( t.id+1 = lead1 and t.id+2 = lead2);

        ================================================================================
        Shell 脚本练习题
        ================================================================================

        1. 统计每个单词出现的次数 (Word Count)
        这次稍微复杂一点，假设 words.txt 内容如下：
        
        http://google.com/search
        http://twitter.com/login
        http://google.com/mail
        http://google.com/search
        http://wiki.org/Main_Page
        
        请统计每个域名（如 google.com, twitter.com）出现的次数，按次数降序排列。
        
        --------------------------------------------------------------------------------

        2. 监控内存使用 (Monitor Memory)
        编写一个脚本，检测当前系统的内存使用率。
        如果内存使用率超过 80%，则输出 "Warning: High Memory Usage: xx%"。
        
        提示：可以使用 `free` 命令配合 `awk`。

        --------------------------------------------------------------------------------

        3. 日志大清理行动 (Log Cleanup Mission)
        编写一个综合脚本，模拟一次完整的日志分析与清理流程。
        要求实现以下步骤（不需要完全自动化，写出关键命令即可）：

        第一阶段：环境准备
        1. 创建目录结构：workspace/logs 和 workspace/backup
        2. 在 logs 目录下创建 5 个 .log 文件 (app.log, error.log 等) 和 1 个干扰文件 readme.txt
        3. 往 access.log 写入模拟数据（包含 IP 和状态码，如 "192.168.1.1 200 OK"）

        mkdir workspace
        mkdir log && mkdir backup
        echo "192.168.1.1 200 OK" > access.log
        echo "192.168.1.2 200 OK" >> access.log
        cat > access.log << 'EOF'
        162.168.1.2 200 OK
        172.168.1.2 200 OK
        162.168.1.2 200 OK
        162.168.1.2 200 OK
        172.168.1.2 200 OK
        192.168.1.2 200 OK
        EOF

        第二阶段：分析
        4. 查找 logs 目录下所有 .log 文件
        5. 统计 access.log 中哪个 IP 访问次数最多（使用 awk, sort, uniq）
        find workspace/logs "*.log"
        cat access.log

        第三阶段：清理
        6. 将 access.log 备份到 backup 目录
        7. 将 logs 目录打包成 logs.tar.gz
        8. 删除原 logs 目录
        */
    }

    /*
    5. 最长回文子串 (Longest Palindromic Substring)
    给你一个字符串 s，找到 s 中最长的回文子串。
    cabcaccb
    bccaccc
    cccc
    [1,0,0,0,0,0,0]
    [0,1,0,0,0,0,0]
    [0,0,1,0,0,0,0]
    [0,0,0,1,0,0,0]
    [0,0,0,0,1,0,0]
    [0,0,0,0,0,1,0]
    [0,0,0,0,0,0,1]

    */
    public String longestPalindrome(String s) {
        if (s == null || s.isEmpty()) {
            return "";
        }
        int length = s.length();
        int[] dp = new int[length];
        dp[0] = 1;
        for (int i = 1; i < length; i++) {
            if (s.charAt(i) == s.charAt(i - 1)) {
                dp[i] = dp[i - 1] + 1;
            } else {
                int i1 = dp[i - 1];
                dp[i] = i - i1 - 1 >= 0 && s.charAt(i - i1 - 1) == s.charAt(i) ? i1 + 2 : 1;
            }
        }


        return "";
    }

    /*
    42. 接雨水 (Trapping Rain Water)
    给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。

    示例 1：
    输入：height = [0,1,0,0,2,1,0,1,3,2,1,2,1]
    输出：6
    */
    public int trap(int[] height) {
        Stack<Integer> stack = new Stack<>();
        int ans = 0;
        for (int i = 0; i < height.length; i++) {
            int curr = height[i];
            while (!stack.isEmpty() && height[stack.peek()] < curr) {
                Integer pop = stack.pop();
                int leftIndex = stack.isEmpty() ? -1 : stack.peek();
                int leftHeight = stack.isEmpty() ? 0 : height[leftIndex];
                ans += (i - leftIndex) * (Math.min(curr, leftHeight) - height[pop]);
            }

            stack.push(i);
        }


        return ans;
    }
}
