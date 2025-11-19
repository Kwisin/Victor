package com.example.demo.algorithm.year25.month11;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Day17 {
    public static void main(String[] args) {

    }

    /* 306
    累加数 是一个字符串，组成它的数字可以形成累加序列。
    一个有效的 累加序列 必须 至少 包含 3 个数。除了最开始的两个数以外，序列中的每个后续数字必须是它之前两个数字之和。
    给你一个只包含数字 '0'-'9' 的字符串，编写一个算法来判断给定输入是否是 累加数 。如果是，返回 true ；否则，返回 false 。
    说明：累加序列里的数，除数字 0 之外，不会 以 0 开头，所以不会出现 1, 2, 03 或者 1, 02, 3 的情况。

    示例 1：
    输入："112358"
    输出：true
    解释：累加序列为: 1, 1, 2, 3, 5, 8 。1 + 1 = 2, 1 + 2 = 3, 2 + 3 = 5, 3 + 5 = 8
    示例 2：
    输入："199100199"
    输出：true
    解释：累加序列为: 1, 99, 100, 199。1 + 99 = 100, 99 + 100 = 199

    提示：
    1 <= num.length <= 35
    num 仅由数字（0 - 9）组成
     */

    public boolean isAdditiveNumber(String num) {
        if (num == null || num.length() < 3) {
            return false;
        }
        return isAdditiveNumberDfs(num, 0, -1, -1);
    }

    public boolean isAdditiveNumberDfs(String num, int curr, int preNum1, int preNum2) {
        if (curr == num.length()) {
            return true;
        }

        for (int i = curr + 1; i <= num.length(); i++) {
            String substring = num.substring(curr, i);
            if (substring.startsWith("0") && substring.length() > 1) {
                break;
            }
            int parseInt = Integer.parseInt(substring);
            if (preNum1 == -1) {
                if (isAdditiveNumberDfs(num, i, parseInt, preNum2)) {
                    return true;
                }
            } else if (preNum2 == -1) {
                if (isAdditiveNumberDfs(num, i, preNum1, parseInt)) {
                    return true;
                }
            } else if (preNum1 + preNum2 == parseInt) {
                if (isAdditiveNumberDfs(num, i, preNum2, parseInt)) {
                    return true;
                }
            }
        }
        return false;
    }


    /* 391
    给你一个数组 rectangles ，其中 rectangles[i] = [xi, yi, ai, bi] 表示一个坐标轴平行的矩形。
    这个矩形的左下顶点是 (xi, yi) ，右上顶点是 (ai, bi) 。
    如果所有矩形一起精确覆盖了某个矩形区域，则返回 true ；否则，返回 false

    输入：rectangles = [[1,1,3,3],[3,1,4,2],[3,2,4,4],[1,3,2,4],[2,3,3,4]]
    输出：true
    解释：5 个矩形一起可以精确地覆盖一个矩形区域。

    输入：rectangles = [[1,1,2,3],[1,3,2,4],[3,1,4,2],[3,2,4,4]]
    输出：false
    解释：两个矩形之间有间隔，无法覆盖成一个矩形。

    输入：rectangles = [[1,1,3,3],[3,1,4,2],[1,3,2,4],[2,2,4,4]]
    输出：false
    解释：因为中间有相交区域，虽然形成了矩形，但不是精确覆盖

    1 <= rectangles.length <= 2 * 104
    rectangles[i].length == 4
    -10^5 <= xi < ai <= 10^5
    -10^5 <= yi < bi <= 10^5
     */
    public boolean isRectangleCover(int[][] rectangles) {
        Arrays.sort(rectangles, (o1, o2) -> {
            if (o1[0] != o2[0]) {
                return o1[0] - o2[0];
            }
            return o1[1] - o2[1];
        });

        //后续就是遍历整个列表，看有没有重叠
        //如果有重叠直接false
        //如果没有重叠并且面积和等于最大矩形面积和并且左边最高点等于右边最高点，
        int minHeight = rectangles[0][1];
        int minWidth = rectangles[0][0];
        int maxHeight = rectangles[0][3];
        int maxWidth = rectangles[0][2];
        int area = calArea(rectangles[0]);
        for (int i = 1; i < rectangles.length; i++) {
            int[] preRectangle = rectangles[i - 1];
            int[] currRectangle = rectangles[i];
            if (checkCover(preRectangle, currRectangle)) {
                return false;
            }
            if (currRectangle[0] != preRectangle[0] && currRectangle[1] != preRectangle[3]) {

            }


        }
        return false;
    }

    public int calArea(int[] rectangle) {
        return (rectangle[2] - rectangle[0]) * (rectangle[3] - rectangle[1]);
    }

    public boolean checkCover(int[] preRectangle, int[] currRectangle) {
        return currRectangle[0] <= preRectangle[2] &&
                currRectangle[1] <= preRectangle[4] &&
                !(currRectangle[0] == preRectangle[2] && currRectangle[1] == preRectangle[4]);
    }
}
