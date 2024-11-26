package com.example.demo.study.demo2411;

import java.util.*;

public class Demo1125 {

    public static void main(String[] args) {
        RandomizedSet randomizedSet = new RandomizedSet();
        randomizedSet.insert(0);
        randomizedSet.insert(1);
        randomizedSet.remove(0);
        randomizedSet.insert(2);
        randomizedSet.remove(1);
        int random2 = randomizedSet.getRandom();
        System.out.println();
    }


}

//122
//给你一个整数数组 prices ，其中 prices[i] 表示某支股票第 i 天的价格。
//
//在每一天，你可以决定是否购买和/或出售股票。你在任何时候 最多 只能持有 一股 股票。你也可以先购买，然后在 同一天 出售。
//
//返回 你能获得的 最大 利润 。


//输入：prices = [7,1,5,3,6,4]
//输出：7
//解释：在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5 - 1 = 4。
//随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6 - 3 = 3。
//最大总利润为 4 + 3 = 7
//示例 2：
//
//输入：prices = [1,2,3,4,5]
//输出：4
//解释：在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5 - 1 = 4。
//最大总利润为 4
//示例 3：
//
//输入：prices = [7,6,4,3,1]
//输出：0
//解释：在这种情况下, 交易无法获得正利润，所以不参与交易可以获得最大利润，最大利润为 0


/*
这种是有状态的，没有持有股票 flag[n][0] ，持有股票 flag[n][1]，具体的值就是当前的盈利额
今天没有股票两种情况，要么昨天没有股票，要么昨天有股票今天卖出
flag[n][0] = max(flag[n-1][1]+prices[n],flag[n-1][0])

今天有股票两种情况，要么昨天有股票，要么昨天没有股票今天买入
flag[n][1] = max(flag[n-1][0]-prices[n],flag[n-1][1])
 */



class maxProfit {
    public int maxProfit(int[] prices) {
        return 0;

        //
    }
}


// 380
//实现RandomizedSet 类：
//
//RandomizedSet() 初始化 RandomizedSet 对象
//bool insert(int val) 当元素 val 不存在时，向集合中插入该项，并返回 true ；否则，返回 false 。
//bool remove(int val) 当元素 val 存在时，从集合中移除该项，并返回 true ；否则，返回 false 。
//int getRandom() 随机返回现有集合中的一项（测试用例保证调用此方法时集合中至少存在一个元素）。每个元素应该有 相同的概率 被返回。
//你必须实现类的所有函数，并满足每个函数的 平均 时间复杂度为 O(1)。


//输入
//["RandomizedSet", "insert", "remove", "insert", "getRandom", "remove", "insert", "getRandom"]
//[[], [1], [2], [2], [], [1], [2], []]
//输出
//[null, true, false, true, 2, true, false, 2]
//
//解释
//RandomizedSet randomizedSet = new RandomizedSet();
//randomizedSet.insert(1); // 向集合中插入 1 。返回 true 表示 1 被成功地插入。
//randomizedSet.remove(2); // 返回 false ，表示集合中不存在 2 。
//randomizedSet.insert(2); // 向集合中插入 2 。返回 true 。集合现在包含 [1,2] 。
//randomizedSet.getRandom(); // getRandom 应随机返回 1 或 2 。
//randomizedSet.remove(1); // 从集合中移除 1 ，返回 true 。集合现在包含 [2] 。
//randomizedSet.insert(2); // 2 已在集合中，所以返回 false 。
//randomizedSet.getRandom(); // 由于 2 是集合中唯一的数字，getRandom 总是返回 2
// map[val][index]  list [index][val]   LinkedList node -> node


/*
首先insert和remove要O(1)判断是否存在，肯定是要用map存储
getRandom想要达到O(1)，必须要用数组存储
map中可以存储val值以及其在数组中对应的下标，关键在于remove的时候怎么能保证时间复杂度
如果正常移除其中某个位置，后面的位置回自动前移，map全部都要重新调整，时间复杂度不对
只有把最后一位和当前调换位置才能达成时间复杂度要求
 */
class RandomizedSet {

    private List<Integer> list;
    private Map<Integer, Integer> valMap;
    private Random random;

    public RandomizedSet() {
        this.random = new Random();
        this.list = new ArrayList<>();
        this.valMap = new HashMap<>();
    }

    public boolean insert(int val) {
        Integer integerVal = Integer.valueOf(val);
        if (this.valMap.containsKey(integerVal)) {
            return false;
        }
        // 在list里面新增一条
        this.list.add(integerVal);

        // 在map中记录位置
        this.valMap.put(integerVal, this.list.size() - 1);

        return true;
    }

    public boolean remove(int val) {
        Integer integerVal = Integer.valueOf(val);
        if (!this.valMap.containsKey(integerVal)) {
            return false;
        }

        //从map中取出位置
        Integer index = this.valMap.get(integerVal);

        //从两边移除
        this.list.remove(index.intValue());
        this.valMap.remove(integerVal);

        return true;
    }

    public int getRandom() {
        // 在list范围内去随机值
        int randomIndex = this.random.nextInt(0, this.list.size());

        // 从list中取val
        return this.list.get(randomIndex);
    }
}

//["RandomizedSet","insert","remove","insert","getRandom","remove","insert","getRandom"]
//     [[],          [-1],    [-2],   [-2],       [],       [-1],    [-2],     []]
//-2
//[null,true,false,true,-2,true,false,-1]
//[null,true,false,true,-1,true,false,-2]

