package com.example.demo.study.demo2502;


import java.util.Arrays;

public class Demo0214 {

    public static void main(String[] args) {
        int rob = new rob().rob(new int[]{1, 2, 3, 1, 1, 1});
        System.out.println();
    }

}

// 177. 第N高的薪水
/*
CREATE FUNCTION getNthHighestSalary(N INT) RETURNS INT
BEGIN
  RETURN (
  declare s int default 0;

      # Write your MySQL query statement below.
      select salary from Employee order by salary desc limit N-1,N;

  );
END
 */

// 213. 打家劫舍 II
/*
你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。这个地方所有的房屋都 围成一圈 ，这意味着第一个房屋和最后一个房屋是紧挨着的。
同时，相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警 。
给定一个代表每个房屋存放金额的非负整数数组，计算你 在不触动警报装置的情况下 ，今晚能够偷窃到的最高金额。

示例 1：
输入：nums = [2,3,2]
输出：3
解释：你不能先偷窃 1 号房屋（金额 = 2），然后偷窃 3 号房屋（金额 = 2）, 因为他们是相邻的。

示例 2：
输入：nums = [1,2,3,1,1,1]
输出：4
解释：你可以先偷窃 1 号房屋（金额 = 1），然后偷窃 3 号房屋（金额 = 3）。
     偷窃到的最高金额 = 1 + 3 = 4 。

示例 3：
输入：nums = [1,2,3]
输出：3

           0               1
       0          1       0
   0      1     0       0   1
 0   1  0     0   1
s0
s0  [0,2,3]
s1  [2,3,4]

s0  [0[0],1[1],2[0],4[1],4[1],5[1]]
s1  [1[1],2[0],4[1],3[0],5[1],5[1]]
 */
class rob {
    public int rob(int[] nums) {
        int length = nums.length;
        if (length <= 2) {
            return 0;
        }

        return dfs(nums, new int[length], 0, 0);
    }

    public int dfs(int[] nums, int[] used, int currPos, int currVal) {
        if (currPos == nums.length) {
            return currVal;
        }
        int notValue = dfs(nums, used, currPos + 1, currVal);
        int yesValue = 0;
        if (currPos == 0 || used[currPos - 1] == 0 && !(used[0] == 1 && currPos == nums.length - 1)) {
            used[currPos] = 1;
            yesValue = dfs(nums, used, currPos + 1, currVal + nums[currPos]);
        }
        return Math.max(notValue, yesValue);
    }
}