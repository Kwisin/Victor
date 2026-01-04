package com.example.demo.algorithm.year26.month01;

public class Day01 {
    public static void main(String[] args) {

        /*
        group by 之后如何排序并且只取第一个，或者前几个？
        case when 的具体用法，还有其他的常见关键字用法吗？

        shell脚本的常用命令，尤其是在服务部署中常见的？
         */
        /*
        ================================================================================
        SQL 练习题 (请在数据库环境中运行，这里仅作记录)
        ================================================================================
        
        1. 部门工资最高的员工 (Department Highest Salary)
        表: Employee (id, name, salary, departmentId)
        表: Department (id, name)
        
        编写 SQL 查询，找出每个部门中薪资最高的员工。
        如果一个部门有多个员工薪资并列最高，都需要列出。
        
        输出格式: Department | Employee | Salary

        select * from Employee


        select name,salary,departmentId, ROW_NUMBER(PARTITION BY departmentId ORDER BY salary DESC) as rk
        from Employee left join Department on Employee.departmentId = Department.id and rk = 1


        select * from Employee left join Department on Employee.departmentId = Department.id group by departmentId

        // 这样也能实现根据先根据部分划分，再根据工资降序，但是怎么取出第一条呢  ，应该是每个Department中的第一个
        // group by之后能不能有方式取出第一个呢？odps中是有的
        select * from Employee order by  departmentId, salary desc;
        
        --------------------------------------------------------------------------------
        
        2. 第N高的薪水 (Nth Highest Salary)
        表: Employee (id, salary)
        
        编写一个 SQL 查询，获取 Employee 表中第 n 高的薪水（Salary）。
        如果不存在第 n 高的薪水，查询应返回 null。
        
        例如 n = 2 时，获取第二高的薪水。


        select * from Employee order salary desc limit n-1, n;
        
        --------------------------------------------------------------------------------
        
        3. 连续出现的数字 (Consecutive Numbers)
        表: Logs (id, num)
        
        编写一个 SQL 查询，查找所有至少连续出现三次的数字。
        
        示例:
        id | num
        1  | 1
        2  | 1
        3  | 1
        4  | 2
        5  | 1
        
        输出: 1


        case when 的具体用法是哪样的，MySQL 都能支持哪些用法
        
        ================================================================================
        Shell 脚本练习题 (请在终端运行)
        ================================================================================


        shell 的题目我完全没有思路，先给我讲解一下吧


        1. 词频统计 (Word Frequency)
        假设 words.txt 内容如下：
        the day is sunny the the
        the sunny is is
        
        编写一个 bash 脚本，计算文件中每个单词的出现频率。
        输出需要按照频率降序排列。
        
        期望输出:
        the 4
        is 3
        sunny 2
        day 1
        
        --------------------------------------------------------------------------------
        
        2. 有效电话号码 (Valid Phone Numbers)
        假设 file.txt 包含如下格式的电话号码：
        987-123-4567
        123 456 7890
        (123) 456-7890
        
        编写一个 bash 脚本，只打印出格式正确的电话号码。
        有效格式：
        - (xxx) xxx-xxxx
        - xxx-xxx-xxxx
        
        --------------------------------------------------------------------------------
        
        3. 转置文件 (Transpose File)
        假设 file.txt 内容如下：
        name age
        alice 21
        ryan 30
        
        编写一个 bash 脚本，将文件内容转置（行列互换）。
        
        期望输出:
        name alice ryan
        age 21 30
        
        --------------------------------------------------------------------------------
        
        4. 第十行 (Tenth Line)
        假设 file.txt 有很多行，编写一个 bash 脚本，只打印出文件的第 10 行。
        如果文件少于 10 行，什么也不打印。
        
        ================================================================================
        更多 SQL 练习
        ================================================================================

        4. 换座位 (Exchange Seats)
        表: Seat (id, student)
        id 是主键，且序列连续增长。
        
        编写 SQL 查询，交换相邻两名学生的座位号。如果学生人数是奇数，则最后一个学生的 id 不变。
        返回结果按照 id 升序排序。
        
        示例:
        id | student
        1  | Abbot
        2  | Doris
        3  | Emerson
        4  | Green
        5  | Jeames
        
        输出:
        id | student
        1  | Doris
        2  | Abbot
        3  | Green
        4  | Emerson
        5  | Jeames
        
        --------------------------------------------------------------------------------

        5. 上升的温度 (Rising Temperature)
        表: Weather (id, recordDate, temperature)
        
        编写 SQL 查询，查找与之前（昨天的）日期相比温度更高的所有日期的 id。
        
        示例:
        id | recordDate | temperature
        1  | 2015-01-01 | 10
        2  | 2015-01-02 | 25
        3  | 2015-01-03 | 20
        4  | 2015-01-04 | 30
        
        输出:
        id
        2
        4
        */
    }
}

