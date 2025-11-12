package com.example.demo.algorithm.year25.month02;


public class Demo0207 {

    public static void main(String[] args) {
        boolean interleave = new isInterleave().isInterleave("aabcc", "dbbca", "aadbbcbcac");
        System.out.println();
    }


}

// 59
/*
给你一个正整数 n ，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的 n x n 正方形矩阵 matrix

输入：n = 3
输出：[
[1,2,3],
[8,9,4],
[7,6,5]
]

输入：n = 1
输出：[[1]]
 */

class generateMatrix {
    public int[][] generateMatrix(int n) {
        int dir = 1; // 1:右 2:下 3:左 4:上
        int[][] result = new int[n][n];
        int x = 0, y = 0;
        int curr = 1;
        while (curr <= n * n) {
            result[x][y] = curr;
            if (dir == 1) {
                if (y + 1 < n && result[x][y + 1] == 0) {
                    y++;
                } else {
                    x++;
                    dir = 2;
                }
            } else if (dir == 2) {
                if (x + 1 < n && result[x + 1][y] == 0) {
                    x++;
                } else {
                    y--;
                    dir = 3;
                }
            } else if (dir == 3) {
                if (y - 1 >= 0 && result[x][y - 1] == 0) {
                    y--;
                } else {
                    x--;
                    dir = 4;
                }
            } else {
                if (x - 1 >= 0 && result[x - 1][y] == 0) {
                    x--;
                } else {
                    y++;
                    dir = 1;
                }
            }
            curr++;
        }

        return result;


    }
}

// 91
/*
一条包含字母 A-Z 的消息通过以下映射进行了 编码 ：
"1" -> 'A'
"2" -> 'B'
...
"25" -> 'Y'
"26" -> 'Z'
然而，在 解码 已编码的消息时，你意识到有许多不同的方式来解码，因为有些编码被包含在其它编码当中（"2" 和 "5" 与 "25"）。
例如，"11106" 可以映射为：
"AAJF" ，将消息分组为 (1, 1, 10, 6)
"KJF" ，将消息分组为 (11, 10, 6)
消息不能分组为  (1, 11, 06) ，因为 "06" 不是一个合法编码（只有 "6" 是合法的）。
注意，可能存在无法解码的字符串。
给你一个只含数字的 非空 字符串 s ，请计算并返回 解码 方法的 总数 。如果没有合法的方式解码整个字符串，返回 0。
题目数据保证答案肯定是一个 32 位 的整数。

                              11106 2
                1(1106) 1                    11(106) 1
         1(106)        11(06)         1(06)         10(6)
   1(06)         10(6)

1(16)  11(6)

                               11191 5
                 1(1191) 3                     11(191) 2
         1(191) 2       11(91) 1        1(91)             19(1)
  1(91)         19(1)

1:1
91:1
1,9,1
19,1

100:

6:1
06:0
106:1
t =='0'? 0 : n[c+1]+1;


n[0,1]


示例 1：
输入：s = "12"
输出：2
解释：它可以解码为 "AB"（1 2）或者 "L"（12）。
示例 2：
输入：s = "226"
输出：3
解释：它可以解码为 "BZ" (2 26), "VF" (22 6), 或者 "BBF" (2 2 6) 。
示例 3：
输入：s = "06"
输出：0
解释："06" 无法映射到 "F" ，因为存在前导零（"6" 和 "06" 并不等价）。

提示：
1 <= s.length <= 100
s 只包含数字，并且可能包含前导零。
 */
class numDecodings {
    public int numDecodings(String s) {
        int length = s.length();
        int[] ints = new int[length];

        ints[length - 1] = checkStr(s.substring(length - 1)) ? 1 : 0;
        if (length == 1) {
            return ints[length - 1];
        }

        if (s.charAt(length - 2) == '0') {
            ints[length - 2] = 0;
        } else {
            ints[length - 2] = checkStr(s.substring(length - 2)) ? ints[length - 1] + 1 : ints[length - 1];
        }
        for (int curr = length - 3; curr >= 0; curr--) {
            ints[curr] += checkStr(s.substring(curr, curr + 1)) ? ints[curr + 1] : 0;
            ints[curr] += checkStr(s.substring(curr, curr + 2)) ? ints[curr + 2] : 0;
        }
        return ints[0];
    }

    public boolean checkStr(String s) {
        if (s.charAt(0) == '0') {
            return false;
        }
        return Integer.parseInt(s) <= 26;
    }

}

// 97
/*
给定三个字符串 s1、s2、s3，请你帮忙验证 s3 是否是由 s1 和 s2 交错 组成的。
两个字符串 s 和 t 交错 的定义与过程如下，其中每个字符串都会被分割成若干 非空子字符串：

s = s1 + s2 + ... + sn
t = t1 + t2 + ... + tm
|n - m| <= 1
交错 是 s1 + t1 + s2 + t2 + s3 + t3 + ... 或者 t1 + s1 + t2 + s2 + t3 + s3 + ...
注意：a + b 意味着字符串 a 和 b 连接。

输入：s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
a(abcc)    a(dbbca)   aa(dbbcbcac)
a(abcc)    ad(bbca)   aad(bbcbcac)
aa(bcc)


aa db
aa b
db
输出：true

输入：s1 = "aab cc", s2 = "dbb ca", s3 = "aad bb baccc"
s(i,j) 表示 s1的前i个字符    s2的前j个字符   和s3前i+j个字符   匹配的结果
s(0,0)   1    s(0,1)   0    s(0,2)   0    s(0,3)   0     s(0,4)   0     s(0,5)   0
s(1,0)   1    s(1,1)   0    0                   0               0               0
s(2,0)   1    s(2,1)   1    1                   1               0               0
s(3,0)   0    s(3,1)   0    1                   1
s(4,0)   0
s(5,0)   0



[1,0,0,0,0,0],
[1,0,0,0,0,0],
[1,0,0,0,0,0],
[0,0,0,0,0,0],
[0,0,0,0,0,0],
[0,0,0,0,0,0],

s(i-1,j-1) = 1?


输出：false

输入：s1 = "", s2 = "", s3 = ""
输出：true
 */
class isInterleave {
//    public boolean isInterleave(String s1, String s2, String s3) {
//        return dfs(s1, s2, s3) || dfs(s2, s1, s3);
//    }

    // 先s1 再s2   s1可以比s2多一个
    public boolean dfs(String s1, String s2, String s3) {
        boolean result = false;
        if (s1.charAt(0) != s3.charAt(0)) {
            return false;
        }
        if (s2.length() == 0 && s1.equals(s3)) {
            return true;
        }
        for (int f = 1; f < s1.length(); f++) {
            String s1f = s1.substring(0, f);
            String s3f = s3.substring(0, f);
            if (s1f.equals(s3f)) {
                if (s2.length() > 0) {
                    for (int s = 1; s < s2.length(); s++) {
                        String s2s = s2.substring(0, s);
                        String s3s = s3.substring(f, f + s);
                        if (s2s.equals(s3s)) {
                            String s1n = s1.substring(f);
                            String s2n = s2.substring(s);
                            String s3n = s3.substring(f + s);
                            result |= dfs(s1n, s2n, s3n);
                        }
                    }
                } else {
                    String s1n = s1.substring(f);
                    String s3n = s3.substring(f);
                    result |= dfs(s1n, "", s3n);
                }

            }

        }
        return result;
    }


    public boolean isInterleave(String s1, String s2, String s3) {
        int n = s1.length(), m = s2.length(), t = s3.length();

        if (n + m != t) {
            return false;
        }

        boolean[][] f = new boolean[n + 1][m + 1];

        f[0][0] = true;
        for (int i = 0; i <= n; ++i) {
            for (int j = 0; j <= m; ++j) {
                int p = i + j - 1;
                if (i > 0) {
                    char s1c = s1.charAt(i - 1);
                    char s3c = s3.charAt(p);
                    f[i][j] = f[i][j] || (f[i - 1][j] && s1c == s3c);
                }
                if (j > 0) {
                    char s2c = s2.charAt(j - 1);
                    char s3c = s3.charAt(p);
                    f[i][j] = f[i][j] || (f[i][j - 1] && s2c == s3c);
                }
            }
        }

        return f[n][m];
    }
}
