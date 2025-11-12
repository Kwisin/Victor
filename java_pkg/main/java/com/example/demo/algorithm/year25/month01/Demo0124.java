package com.example.demo.algorithm.year25.month01;


import java.util.*;


public class Demo0124 {

    public static void main(String[] args) {
        List<List<Integer>> skyline = new getSkyline1().getSkyline(new int[][]{{2, 9, 10}, {2, 7, 15}, {11, 12, 12}, {15, 20, 10}, {19, 24, 8}});
        System.out.println();
    }


}

// 65
class isNumber {
    public boolean isNumber(String s) {
        String s1 = s.toLowerCase();
        HashMap<Character, ArrayList<Integer>> characterPositionHashMap = new HashMap<>();
        for (int i = 0; i < s1.length(); i++) {
            char c = s1.charAt(i);
            ArrayList<Integer> orDefault = characterPositionHashMap.getOrDefault(c, new ArrayList<>());
            if (c == 'e' || c == '.' || c == 'E') {
                if (orDefault.size() > 0) {
                    return false;
                }
            } else if ((c != '-' && c != '+') && (c < '0' || c > '9')) {
                return false;
            }
            orDefault.add(i);
            characterPositionHashMap.put(c, orDefault);
        }

        ArrayList<Integer> ePositions = characterPositionHashMap.getOrDefault('e', new ArrayList<>());
        ArrayList<Integer> pointPositions = characterPositionHashMap.getOrDefault('.', new ArrayList<>());
        if (ePositions.size() > 0) {
            if (pointPositions.size() > 0 && ePositions.get(0) < pointPositions.get(0)) {
                return false;
            }
            String[] es = s1.split("e");
            if (es.length != 2) {
                return false;
            }
            return checkNum(es[0]) && checkNum(es[1]);
        }

        return checkNum(s1);
    }


    public boolean checkNum(String s) {
        try {
            Integer.valueOf(s);
        } catch (Exception e) {
            try {
                Double.valueOf(s);
            } catch (Exception doubleE) {
                return false;
            }

        }
        return true;
    }
}


// 218
/*
[2,9,10],
[3,7,15],
[5,12,12],
[15,20,10],
[19,24,8]


2，10
3，15
//5，12
7，15
//9，10
12，12
15，10
//19，8
20，10
24，8



 */
class getSkyline {
    public List<List<Integer>> getSkyline(int[][] buildings) {
        return null;
    }
}

class getSkyline1 {
    public List<List<Integer>> getSkyline(int[][] bs) {
        List<List<Integer>> ans = new ArrayList<>();

        // 预处理所有的点，为了方便排序，对于左端点，令高度为负；对于右端点令高度为正
        List<int[]> ps = new ArrayList<>();
        for (int[] b : bs) {
            int l = b[0], r = b[1], h = b[2];
            ps.add(new int[]{l, -h});
            ps.add(new int[]{r, h});
        }

        // 先按照横坐标进行排序
        // 如果横坐标相同，则按照左端点排序
        // 如果相同的左/右端点，则按照高度进行排序
        Collections.sort(ps, (a, b)->{
            if (a[0] != b[0]) return a[0] - b[0];
            return a[1] - b[1];
        });

        // 大根堆
        PriorityQueue<Integer> q = new PriorityQueue<>((a, b)->b-a);
        int prev = 0;
        q.add(prev);
        for (int[] p : ps) {
            int point = p[0], height = p[1];
            if (height < 0) {
                // 如果是左端点，说明存在一条往右延伸的可记录的边，将高度存入优先队列
                q.add(-height);
            } else {
                // 如果是右端点，说明这条边结束了，将当前高度从队列中移除
                q.remove(height);
            }

            // 取出最高高度，如果当前不与前一矩形“上边”延展而来的那些边重合，则可以被记录
            int cur = q.peek();
            if (cur != prev) {
                List<Integer> list = new ArrayList<>();
                list.add(point);
                list.add(cur);
                ans.add(list);
                prev = cur;
            }
        }
        return ans;
    }
}