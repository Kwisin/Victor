package com.example.demo.algorithm.year25.month12;

import java.util.*;

public class Day08 {
    public static void main(String[] args) {

    }

    /*
    295. 数据流的中位数
    中位数是有序整数列表中的中间值。如果列表的大小是偶数，则没有中间值，中位数是两个中间值的平均值。
    
    例如：
    [2,3,4] 的中位数是 3
    [2,3] 的中位数是 (2 + 3) / 2 = 2.5
    
    设计一个支持以下两种操作的数据结构：
    - void addNum(int num) - 从数据流中添加一个整数到数据结构中。
    - double findMedian() - 返回目前所有元素的中位数。
    
    示例：
    输入
    ["MedianFinder", "addNum", "addNum", "findMedian", "addNum", "findMedian"]
    [[], [1], [2], [], [3], []]
    输出
    [null, null, null, 1.5, null, 2.0]
    
    解释
    MedianFinder medianFinder = new MedianFinder();
    medianFinder.addNum(1);    // arr = [1]
    medianFinder.addNum(2);    // arr = [1, 2]
    medianFinder.findMedian(); // 返回 1.5 ((1 + 2) / 2)
    medianFinder.addNum(3);    // arr = [1, 2, 3]
    medianFinder.findMedian(); // 返回 2.0
    
    提示：
    -10^5 <= num <= 10^5
    在调用 findMedian 之前，数据结构中至少有一个元素
    最多 5 * 10^4 次调用 addNum 和 findMedian
     */
    class FindMedianNode {
        FindMedianNode pre;
        FindMedianNode next;
        int val;

        public FindMedianNode(int val) {
            this.val = val;
        }
    }

    class FindMedian {
        FindMedianNode medianNode1;
        FindMedianNode medianNode2;
        int size;

        public FindMedian() {
            this.medianNode1 = null;
            this.medianNode2 = null;
            this.size = 0;
        }

        void addNum(int num) {
            FindMedianNode curr = new FindMedianNode(num);
            if (medianNode1 == null && medianNode2 == null) {
                medianNode1 = curr;
                medianNode2 = curr;
                return;
            }

            if (this.medianNode1 != null && num < this.medianNode1.val) {
                // 从 head 向后找合适的位置
                FindMedianNode temp = this.medianNode1;
                while (true) {
                    if (num < temp.val) {
                        if (temp.pre == null) {
                            temp.pre = curr;
                            curr.next = temp;
                            this.size++;
                            break;
                        } else if (num > temp.pre.val) {
                            temp.pre.next = curr;
                            curr.pre = temp.pre;
                            curr.next = temp;
                            temp.pre = curr;
                            this.size++;
                            break;
                        }
                        temp = temp.pre;
                    }
                }
            }

            // 从medianNode1向后找合适的位置

//            TreeMap<Integer, Integer> integerIntegerTreeMap = new TreeMap<>();
//            integerIntegerTreeMap.lastKey();
//            integerIntegerTreeMap.firstKey();
//            integerIntegerTreeMap


        }

        double findMedian() {
            return (double) (this.medianNode1.val + this.medianNode2.val) / 2;
        }
    }

    /*
    480. 滑动窗口中位数
    中位数是有序序列最中间的那个数。如果序列的长度是偶数，则没有最中间的数；此时中位数是最中间的两个数的平均数。
    
    例如：
    [2,3,4]，中位数是 3
    [2,3]，中位数是 (2 + 3) / 2 = 2.5
    
    给你一个数组 nums，有一个长度为 k 的窗口从最左端滑动到最右端。窗口中有 k 个数，每次窗口向右移动 1 位。
    你的任务是找出每次窗口移动后得到的新窗口中元素的中位数，并输出由它们组成的数组。
    
    示例：
    输入：nums = [1,3,-1,-3,5,3,6,7], k = 3
    输出：[1.00000,-1.00000,-1.00000,3.00000,5.00000,6.00000]
    解释：
    窗口位置                      中位数
    ---------------               -----
    [1  3  -1] -3  5  3  6  7       1
     1 [3  -1  -3] 5  3  6  7      -1
     1  3 [-1  -3  5] 3  6  7      -1
     1  3  -1 [-3  5  3] 6  7       3
     1  3  -1  -3 [5  3  6] 7       5
     1  3  -1  -3  5 [3  6  7]      6
    
    提示：
    1 <= k <= nums.length <= 10^5
    -2^31 <= nums[i] <= 2^31 - 1
     */
    public double[] FindMedianK(int[] nums, int k) {
        double[] result = new double[nums.length - k + 1];
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        HashMap<Integer, Integer> numCount = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int curr = nums[i];
            numCount.put(curr, numCount.getOrDefault(curr, 0) + 1);

            maxHeap.offer(curr);
            minHeap.offer(maxHeap.poll());

            if (minHeap.size() > maxHeap.size() + 1) {
                maxHeap.offer(minHeap.poll());
            }

            if (i >= k) {
                // 移除 i-k 位置元素
                int toRemove = nums[i - k];
                numCount.put(toRemove, numCount.get(toRemove) - 1);
                if (numCount.get(toRemove) == 0) {
                    numCount.remove(toRemove);
                }
            }

            if (i >= k - 1) {
                while (!minHeap.isEmpty() && (!numCount.containsKey(minHeap.peek()) || numCount.get(minHeap.peek()) == 0)) {
                    minHeap.poll();
                }
                while (!maxHeap.isEmpty() && (!numCount.containsKey(maxHeap.peek()) || numCount.get(maxHeap.peek()) == 0)) {
                    maxHeap.poll();
                }
                // 再平衡
                while (minHeap.size() - maxHeap.size() > 1) {
                    maxHeap.offer(minHeap.poll());
                }
                while (maxHeap.size() - minHeap.size() > 1) {
                    minHeap.offer(maxHeap.poll());
                }
                // 填充中位数
                if (k % 2 == 1) {
                    if (!minHeap.isEmpty()) {
                        result[i - k + 1] = (double) minHeap.peek();
                    }
                } else {
                    if (!maxHeap.isEmpty() && !minHeap.isEmpty()) {
                        result[i - k + 1] = (double) (maxHeap.peek() + minHeap.peek()) / 2;
                    }
                }

            }

        }

        return result;


    }


    /*
    699. 掉落的方块
    在二维平面上，有一些方块（正方形）从上方垂直掉落，掉在一条无限长的数轴（即 x 轴）上。
    
    给你一个二维整数数组 positions ，其中 positions[i] = [lefti, sideLengthi] 表示第 i 个方块掉落的位置是从 
    位置 lefti 到 lefti + sideLengthi - 1 的所有整数对应的 x 轴位置，边长为 sideLengthi 。
    
    每个方块的底边平行于数轴（即 x 轴），并且从一个比目前所有的落地方块更高的高度掉落。在上一个方块结束掉落，
    并保持静止后，才开始掉落新方块。方块的底边具有非常大的粘性，并将保持固定在它们所接触的任何长度表面上
    （无论是数轴还是其他方块）。邻接掉落的边不会过早地粘合在一起，因为只有底边才具有粘性。
    
    返回一个堆叠高度列表 ans 。每一个堆叠高度 ans[i] 表示在通过 positions[0], positions[1], ..., positions[i] 
    表示的方块掉落结束后，目前所有已经落稳的方块堆叠的最高高度。
    
    示例 1:
    输入: [[1, 2], [2, 3], [6, 1]]
    [1,3,2]
    [1,2,2],[2,5,5]
    [1,2,2],[2,5,5],[6,7,1]
    输出: [2, 5, 5]
    解释:
    第一个方块从位置 [1, 3] 掉落后，堆叠高度为 2。
    第二个方块从位置 [2, 5] 掉落后，堆叠高度为 5。
    第三个方块从位置 [6, 7] 掉落后，堆叠高度保持为 5。
    
    示例 2:
    输入: [[100, 100], [200, 100]]
    输出: [100, 100]
    解释: 相邻的方块不会过早地卡住，只有它们的底边才能粘在一起。
    
    提示：
    1 <= positions.length <= 1000
    1 <= lefti <= 10^8
    1 <= sideLengthi <= 10^6
     */
    public int[] GetHigh(int[][] nums) {
        int[] result = new int[nums.length];
        int max = nums[0][2];
        TreeMap<Integer, int[]> treeMap = new TreeMap<>();
        for (int i = 0; i < nums.length; i++) {
            int[] num = nums[i];
            int start = num[0];
            int end = num[0] + num[1];
            int height = num[1];
            // 找到 start < end 的方块
            boolean merge = false;
            for (Map.Entry<Integer, int[]> entry : treeMap.entrySet()) {
                int[] value = entry.getValue();
                Integer key = entry.getKey();
                if (key > end) {
                    break;
                }
                if (value[1] < start) {
                    continue;
                }

                if (value[0] < start && value[1] > start) {
                    value[1] = start;
                    merge = true;
                    treeMap.put(start, new int[]{start, end, height + value[2]});
                    max = Math.max(max, height + value[2]);
                } else if (value[0] < end && value[1] > end) {
                    treeMap.remove(value[0]);
                    treeMap.put(end, new int[]{end, value[1], value[2]});
                    merge = true;
                    treeMap.put(start, new int[]{start, end, height + value[2]});
                    max = Math.max(max, height + value[2]);
                }
            }

            if (!merge) {
                treeMap.put(start, new int[]{start, end, height});
                max = Math.max(max, height);
            }

            result[i] = max;

        }


        return result;
    }


    /*
    715. Range 模块
    Range 模块是跟踪数字范围的模块。设计一个数据结构来跟踪表示为 半开区间 的范围并查询它们。
    
    半开区间 [left, right) 表示所有 left <= x < right 的实数 x 。
    
    实现 RangeModule 类:
    - RangeModule() 初始化数据结构的对象。
    - void addRange(int left, int right) 添加 半开区间 [left, right)，跟踪该区间中的每个实数。
      添加与当前跟踪的数字部分重叠的区间时，应当添加在区间 [left, right) 中尚未跟踪的任何数字到该区间中。
    - boolean queryRange(int left, int right) 只有在当前正在跟踪区间 [left, right) 中的每一个实数时，
      才返回 true ，否则返回 false 。
    - void removeRange(int left, int right) 停止跟踪 半开区间 [left, right) 中当前正在跟踪的每个实数。
    
    示例 1：
    输入
    ["RangeModule", "addRange", "removeRange", "queryRange", "queryRange", "queryRange"]
    [[], [10, 20], [14, 16], [10, 14], [13, 15], [16, 17]]
    输出
    [null, null, null, true, false, true]
    
    解释
    RangeModule rangeModule = new RangeModule();
    rangeModule.addRange(10, 20);
    rangeModule.removeRange(14, 16);
    rangeModule.queryRange(10, 14); // 返回 true （区间 [10, 14) 中的每个数都正在被跟踪）
    rangeModule.queryRange(13, 15); // 返回 false（未跟踪区间 [13, 15) 中像 14, 14.03, 14.17 这样的数字）
    rangeModule.queryRange(16, 17); // 返回 true （尽管执行了删除操作，区间 [16, 17) 中的数字 16 仍然会被跟踪）
    
    提示：
    1 <= left < right <= 10^9
    在单个测试用例中，对 addRange 、queryRange 和 removeRange 的调用总数不超过 10^4 次
     */
    class rangeModule {
        TreeMap<Integer, Integer> treeMap;

        public rangeModule() {
            this.treeMap = new TreeMap<>();
        }

        public void addRange(int left, int right) {
            Map.Entry<Integer, Integer> start = this.treeMap.lowerEntry(left);
            Map.Entry<Integer, Integer> preStart = start;
            Map.Entry<Integer, Integer> end = this.treeMap.higherEntry(right);
            boolean merge = false;
            while (start.getKey() < end.getKey()) {

                if (merge) {
                    // 前后没有交集
                    if (start.getKey() > preStart.getValue()) {
                        break;
                    }

                    // 有交集
                    this.treeMap.remove(start.getKey());
                    preStart.setValue(Math.max(preStart.getValue(), start.getValue()));
                }

                if (left >= start.getKey() && left <= start.getValue()) {
                    start.setValue(Math.max(start.getValue(), right));
                    merge = true;
                } else if (right >= start.getKey() && right <= start.getValue()) {
                    this.treeMap.remove(start.getKey());
                    this.treeMap.put(Math.min(left, start.getKey()), start.getValue());
                    merge = true;
                }

                preStart = start;
                start = this.treeMap.higherEntry(start.getKey());
            }

            if (!merge) {
                this.treeMap.put(left, right);
            }
        }

        public boolean queryRange(int left, int right) {
            Map.Entry<Integer, Integer> start = this.treeMap.lowerEntry(left);
            Map.Entry<Integer, Integer> end = this.treeMap.higherEntry(right);
            while (start.getKey() < end.getKey()) {
                if (start.getKey() <= left && start.getValue() >= right) {
                    return true;
                }
                start = this.treeMap.higherEntry(start.getKey());
            }

            return false;
        }

        public void removeRange(int left, int right) {
            if (!queryRange(left, right)) {
                return;
            }
            Map.Entry<Integer, Integer> start = this.treeMap.lowerEntry(left);
            Map.Entry<Integer, Integer> end = this.treeMap.higherEntry(right);
            while (start.getKey() < end.getKey()) {
                if (start.getKey() <= left && start.getValue() >= right) {
                    if (start.getKey() == left && start.getValue() == right) {
                        this.treeMap.remove(start.getKey());
                        return;
                    }
                    if (start.getKey() == left) {
                        this.treeMap.remove(start.getKey());
                        this.treeMap.put(right, start.getValue());
                        return;
                    }
                    if (start.getValue() == right) {
                        start.setValue(left);
                        return;
                    }
                    this.treeMap.put(right, start.getValue());
                    start.setValue(left);
                    return;
                }

                start = this.treeMap.higherEntry(start.getKey());
            }
        }


    }

    
    /*
    855. 考场就座
    在考场里，有 n 个座位排成一行，编号为 0 到 n - 1 。
    
    当学生进入考场后，他必须坐在离最近的人最远的座位上。如果有多个这样的座位，他会坐在编号最小的座位上。
    (另外，如果考场里没有人，那么学生就坐在 0 号座位上。)
    
    设计一个模拟考场座位分配的类，实现以下功能：
    - ExamRoom(int n) 用座位数量 n 初始化考场对象。
    - int seat() 返回下一个学生应该坐的座位编号。
    - void leave(int p) 指定坐在座位 p 的学生离开。题目保证座位 p 上会有一位学生。
    
    示例 1：
    输入
    ["ExamRoom", "seat", "seat", "seat", "seat", "leave", "seat"]
    [[10], [], [], [], [], [4], []]
    输出
    [null, 0, 9, 4, 2, null, 5]
    [0,1,2,3,4,5,6,7,8,9]
     0       5         10               20
     0   2   4         9               20
         2   4         9               20
     0       4   6     9               20
    解释
    ExamRoom examRoom = new ExamRoom(10);
    examRoom.seat(); // 返回 0，房间里没有人，学生坐在 0 号座位。
    examRoom.seat(); // 返回 9，学生最后坐在 9 号座位。
    examRoom.seat(); // 返回 4，学生最后坐在 4 号座位。
    examRoom.seat(); // 返回 2，学生最后坐在 2 号座位。
    examRoom.leave(4);
    examRoom.seat(); // 返回 5，学生最后坐在 5 号座位。
    
    提示：
    1 <= n <= 10^9
    保证有学生在调用 leave(p) 时正坐在座位 p 上。
    在调用 seat() 和 leave(p) 时，最多有 10^4 名学生在考场。


    暴力方法：维护一个个区间，遍历每个区间找出中间值，计算距离当前各个座位的距离，找出最大的，如果头尾离开的话还要考虑头尾座位

    或者每次通过大根堆，选举出两个范围最大的区间是不是就可以了，再补充上头尾座位，计算距离当前各个座位的距离？
     */



}
