#!/bin/bash

# Day12: 服务器安全检查 (Server Security Check)

# 场景描述：
# 你的老板让你对服务器进行一次快速的安全体检。你需要编写脚本自动完成以下检查。

# ==============================================================================
# 任务 1: 检查 SSH 暴力破解
# ==============================================================================
# 模拟 auth.log 文件 (包含 SSH 登录日志)
cat > auth.log << 'EOF'
Jan 12 10:00:01 server sshd[1234]: Failed password for root from 192.168.1.100 port 22 ssh2
Jan 12 10:00:02 server sshd[1234]: Failed password for root from 192.168.1.100 port 22 ssh2
Jan 12 10:00:03 server sshd[1234]: Failed password for root from 192.168.1.100 port 22 ssh2
Jan 12 10:00:05 server sshd[1234]: Failed password for user from 10.0.0.5 port 22 ssh2
Jan 12 10:00:10 server sshd[1234]: Accepted password for admin from 192.168.1.50 port 22 ssh2
Jan 12 10:00:20 server sshd[1234]: Failed password for root from 192.168.1.100 port 22 ssh2
EOF

# 1. 统计每个 IP 的登录失败次数
# 提示：
# - 找出包含 "Failed password" 的行
# - 提取 IP 地址 (通常是第 9 列或第 11 列，看日志格式，这里是 "from" 后面)
# - 排序、去重计数、按次数倒序
# TODO: 写出命令
mkdir test | cat > test.log << 'EOF'
test
EOF  # 生成文件夹+生成文件
echo  "" > test.log # 写入内容
echo  "" >> test.log # 写入内容

mv test.log auth.log # 重命名

grep -E "Failed password" auth.log | awk '{print $11}' | sort | uniq -c | sort -r # 解答

# --- 标准答案参考 ---
# 1. 使用 grep -oE 提取 IP 更稳健，避免列数变化问题
# 2. 使用 sort -nr 进行数值倒序排序（防止 10 排在 2 后面）
# grep "Failed password" auth.log | grep -oE "([0-9]{1,3}\.){3}[0-9]{1,3}" | sort | uniq -c | sort -nr


echo "--- SSH Failed Attempts ---"
# your_command_here


# ==============================================================================
# 任务 2: 检查开放端口
# ==============================================================================
# 模拟 netstat 输出
cat > netstat.txt << 'EOF'
Active Internet connections (only servers)
Proto Recv-Q Send-Q Local Address           Foreign Address         State       PID/Program name
tcp        0      0 0.0.0.0:22              0.0.0.0:*               LISTEN      1000/sshd
tcp        0      0 127.0.0.1:3306          0.0.0.0:*               LISTEN      1200/mysqld
tcp        0      0 0.0.0.0:80              0.0.0.0:*               LISTEN      1300/nginx
tcp6       0      0 :::8080                 :::*                    LISTEN      1400/java
EOF

# 2. 提取所有处于 LISTEN 状态的 TCP 端口号
# 期望输出：22, 3306, 80, 8080
# 提示：awk 提取 "Local Address"，再用 cut 或 awk 提取冒号后面的端口
# TODO: 写出命令

echo "--- Listening Ports ---"
# your_command_here


# ==============================================================================
# 任务 3: 磁盘大文件检查
# ==============================================================================
# 3. 找出当前目录（及子目录）下所有大于 10MB 的文件
# 提示：使用 find 命令，参数 -size
# 为了演示，我们先创建一个模拟的大文件
truncate -s 15M large_file.dat
touch small_file.txt

echo "--- Large Files (>10M) ---"
# TODO: 写出命令 (find ...)

# 清理模拟文件
rm auth.log netstat.txt large_file.dat small_file.txt

