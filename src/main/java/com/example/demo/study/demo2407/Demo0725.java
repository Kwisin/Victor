package com.example.demo.study.demo2407;


import org.w3c.dom.ls.LSInput;

import java.util.*;

public class Demo0725 {

    public static void main(String[] args) {
        groupAnagrams groupAnagrams = new groupAnagrams();
        List<List<String>> lists = groupAnagrams.groupAnagrams(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"});
        System.out.println();
    }

}


class groupAnagrams {
    //给你一个字符串数组，请你将 字母异位词 组合在一起。可以按任意顺序返回结果列表。
    //字母异位词 是由重新排列源单词的所有字母得到的一个新单词。

    //示例 1:
    //输入: strs = ["eat", "tea", "tan", "ate", "nat", "bat"]
    //输出: [["bat"],["nat","tan"],["ate","eat","tea"]]

    //示例 2:
    //输入: strs = [""]
    //输出: [[""]]

    //示例 3:
    //输入: strs = ["a"]
    //输出: [["a"]]
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> arrayLists = new ArrayList<>();
        if (strs.length == 0) {
            return arrayLists;
        }

        if (strs.length == 1) {
            arrayLists.add(Arrays.asList(strs));
            return arrayLists;
        }

        HashSet<String> stringHashSet = new HashSet<>();
        for (int i = 0; i < strs.length; i++) {
            if (stringHashSet.contains(strs[i])) {
                continue;
            }
            ArrayList<String> temp = new ArrayList<>();
            temp.add(strs[i]);
            stringHashSet.add(strs[i]);
            for (int j = i + 1; j < strs.length; j++) {
                if (stringHashSet.contains(strs[j])) {
                    continue;
                }
                if (checkString(strs[i], strs[j])) {
                    temp.add(strs[j]);
                    stringHashSet.add(strs[j]);
                }
            }
            arrayLists.add(temp);
        }
        return arrayLists;
    }

    public boolean checkString(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }

        if (s1.equals(s2)) {
            return true;
        }

        HashMap<Character, Integer> characterIntegerHashMap = new HashMap<Character, Integer>();
        for (char c : s1.toCharArray()) {
            characterIntegerHashMap.put(c, characterIntegerHashMap.getOrDefault(c, 0) + 1);
        }

        for (char c : s2.toCharArray()) {
            if (!characterIntegerHashMap.containsKey(c)) {
                return false;
            }
            characterIntegerHashMap.put(c, characterIntegerHashMap.get(c) - 1);
        }

        for (Character c : characterIntegerHashMap.keySet()) {
            if (characterIntegerHashMap.get(c) != 0) {
                return false;
            }
        }
        return true;
    }
}


/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode() {}
 * ListNode(int val) { this.val = val; }
 * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 * <p>
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 * <p>
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 * <p>
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 * <p>
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 * <p>
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 * <p>
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */

class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

class reverseKGroup {
    //给你链表的头节点 head ，每 k 个节点一组进行翻转，请你返回修改后的链表。
    //k 是一个正整数，它的值小于或等于链表的长度。如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
    //你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换

    //输入：head = [1,2,3,4,5], k = 2
    //输出：[2,1,4,3,5]

    //输入：head = [1,2,3,4,5,7,8,9,9,9], k = 6

    // 5
//    0,4
//            1,3
//            2,2
//
//6
//            0,5  5
//            1,4  3
//            2,3  1

    //输出：[3,2,1,4,5]
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode newHead = new ListNode();
        ListNode newTail = new ListNode();
        ListNode current = head;
        int count = k;
        Stack<ListNode> nodeStack = new Stack<ListNode>();
        while (current != null) {
            if (count == k) {
//                newTail =
            }
            nodeStack.push(current);
            count--;
            if (count == 0) {
                ListNode listNode = reBuild(nodeStack);
            }
        }


        return head;


    }

    public ListNode reBuild(Stack<ListNode> nodeStack) {
        ListNode tempNode = new ListNode();
        while (!nodeStack.empty()) {
            ListNode item = nodeStack.pop();
            ListNode next = tempNode.next;
            tempNode.next = item;
            item.next = next;
        }


        return tempNode.next;
    }


}


class LRUCache {

    //请你设计并实现一个满足  LRU (最近最少使用) 缓存 约束的数据结构。
    //实现 LRUCache 类：
    //LRUCache(int capacity) 以 正整数 作为容量 capacity 初始化 LRU 缓存
    //int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
    //void put(int key, int value) 如果关键字 key 已经存在，则变更其数据值 value ；如果不存在，则向缓存中插入该组 key-value 。如果插入操作导致关键字数量超过 capacity ，则应该 逐出 最久未使用的关键字。
    //函数 get 和 put 必须以 O(1) 的平均时间复杂度运行。

    //输入
    //["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
    //[[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
    //输出
    //[null, null, null, 1, null, -1, null, -1, 3, 4


    // 使用频率
    // 缓存值
    // 大小
    //
    public LRUCache(int capacity) {
    }

    public int get(int key) {
        return 0;
    }

    public void put(int key, int value) {

    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
