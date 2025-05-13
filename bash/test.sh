#!/bin/bash
# 192
pwd
#read -p '请输入文件名：' filename

cd /Users/xuzhiping/Downloads/bashDir

tr -s ' ' '\n' < test.txt | grep -v '^$' | sort | uniq -c | sort -nr | awk '{printf "%-15s %s\n",$2,$1}'
#tr -s ' ' '\n' < test.txt|grep -v '^$'|uniq -c|sort -nr|awk '{printf "%-15s %s\n", $2,$1}'
