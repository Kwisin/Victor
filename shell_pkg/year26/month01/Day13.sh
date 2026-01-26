#!/bin/bash

# Day13: 进程资源监控 (Process Warden)
# 场景：服务器变慢了，需要找出资源消耗大户。
# 
# 任务：
# 1. 创建一个模拟的高 CPU/内存占用场景（由于脚本很难直接模拟高负载，我们重点放在查询命令上）
#    (你可以跳过创建步骤，直接对当前系统执行查询)
#
# 2. 找出 CPU 占用率最高的 5 个进程
#    要求输出格式：PID, %CPU, COMMAND
#
# 3. 找出 内存 占用率最高的 5 个进程
#    要求输出格式：PID, %MEM, COMMAND
#
# 4. 统计系统中 "java" 进程的数量 (如果没有 java 进程，统计 "bash" 或 "zsh")
#
# 5. (进阶) 查找并杀掉所有名字中包含 "sleep_test_process" 的进程
#    为了安全，我们先启动一个 sleep 进程作为靶子：
#    sleep 1000 & 
#    (请在脚本中写出查找并 kill 的命令，不仅是 kill PID)

echo "--- Top 5 CPU Processes ---"
# TODO: Write command here


echo -e "\n--- Top 5 Memory Processes ---"
# TODO: Write command here


echo -e "\n--- Count 'zsh' Processes ---"
# TODO: Write command here


# 靶子进程
sleep 60 &
TARGET_PID=$!
echo -e "\n--- Kill 'sleep' Process (PID: $TARGET_PID) ---"
# TODO: Write command to find 'sleep' by name and kill it (simulate: pkill or kill $(pgrep ...))


