package com.example.demo.algorithm.year25.month05;

import java.util.*;

public class Demo18 {
    public static void main(String[] args) {
        List<List<String>> tickets = new ArrayList<>();
        tickets.add(Arrays.asList("JFK","SFO"));
        tickets.add(Arrays.asList("JFK","ATL"));
        tickets.add(Arrays.asList("SFO","ATL"));
        tickets.add(Arrays.asList("ATL","JFK"));
        tickets.add(Arrays.asList("ATL","SFO"));
        List<String> itinerary = new findItinerary().findItinerary(tickets);
        System.out.println();
    }

}

/* 332. 重新安排行程
给你一份航线列表 tickets ，其中 tickets[i] = [fromi, toi] 表示飞机出发和降落的机场地点。请你对该行程进行重新规划排序。

所有这些机票都属于一个从 JFK（肯尼迪国际机场）出发的先生，所以该行程必须从 JFK 开始。如果存在多种有效的行程，请你按字典排序返回最小的行程组合。

例如，行程 ["JFK", "LGA"] 与 ["JFK", "LGB"] 相比就更小，排序更靠前。
假定所有机票至少存在一种合理的行程。且所有的机票 必须都用一次 且 只能用一次。

输入：tickets = [["MUC","LHR"],["JFK","MUC"],["SFO","SJC"],["LHR","SFO"]]
输出：["JFK","MUC","LHR","SFO","SJC"]

输入：tickets = [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
输出：["JFK","ATL","JFK","SFO","ATL","SFO"]
解释：另一种有效的行程是 ["JFK","SFO","ATL","JFK","ATL","SFO"] ，但是它字典排序更大更靠后。
 */
class findItinerary {
    List<String> result = new ArrayList<>();

    public List<String> findItinerary(List<List<String>> tickets) {
        HashMap<String, List<String>> graphic = buildGraphic(tickets);
        ArrayList<String> array = new ArrayList<>();
        array.add("JFK");
        dfs(graphic, "JFK", new HashSet<>(), array, tickets.size() + 1);
        return this.result;
    }

    public void dfs(HashMap<String, List<String>> graphic, String curr, HashSet<String> exist, List<String> array, int length) {
        if (array.size() == length) {
            addNewResult(array);
            return;
        }
        List<String> targets = graphic.get(curr);
        for (String s : targets) {
            String s1 = curr + "-" + s;
            if (exist.contains(s1)) {
                continue;
            }
            exist.add(s1);
            array.add(s);
            dfs(graphic, s, exist, array, length);
            exist.remove(s1);
            array.remove(array.size() - 1);

        }


    }

    public void addNewResult(List<String> array) {
        if (this.result.isEmpty()) {
            this.result.addAll(array);
        }

        for (int i = 0; i < array.size(); i++) {
            String temp = array.get(i);
            String target = this.result.get(i);
            if (temp.equals(target)) {
                continue;
            }
            if (check(target, temp)) {
                this.result.clear();
                this.result.addAll(array);
            }else {
                break;
            }
        }
    }

    public boolean check(String target, String temp) {
        for (int i = 0; i < target.length(); i++) {
            if (temp.charAt(i) < target.charAt(i)) {
                return true;
            }
            if (temp.charAt(i) > target.charAt(i)) {
                return false;
            }
        }
        return false;
    }

    public HashMap<String, List<String>> buildGraphic(List<List<String>> tickets) {
        HashMap<String, List<String>> graphic = new HashMap<String, List<String>>();
        for (List<String> item : tickets) {
            List<String> list = graphic.getOrDefault(item.get(0), new ArrayList<>());
            list.add(item.get(1));
            graphic.put(item.get(0), list);
        }


        return graphic;

    }
}
