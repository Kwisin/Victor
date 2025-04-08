package com.example.demo.algorithm.demo24.demo2412;


public class Demo1205 {

    public static void main(String[] args) {
        System.out.println();
    }


}

//82
/*
给定一个已排序的链表的头 head ， 删除原始链表中所有重复数字的节点，只留下不同的数字 。返回 已排序的链表 。


输入：head = [1,2,3,3,4,4,5]
输出：[1,2,5]

输入：head = [1,1,1,2,3]
输出：[2,3]

链表中节点数目在范围 [0, 300] 内
-100 <= Node.val <= 100
题目数据保证链表已经按升序 排列
 */


/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode() {}
 * ListNode(int val) { this.val = val; }
 * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class deleteDuplicates {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode pre = new ListNode(-101);
        pre.next = head;

        ListNode current = pre;
        while (current.next != null) {
            if (current.next.next == null) {
                break;
            }
            if (current.next.val == current.next.next.val) {
                //找到第一个不等于当前val的node
                ListNode tempNode = current.next;
                while (tempNode != null && tempNode.val == current.next.val) {
                    tempNode = tempNode.next;
                }
                current.next = tempNode;
            } else {
                current = current.next;
            }
        }

        return pre.next;
    }
}

// 86
/*
给你一个链表的头节点 head 和一个特定值 x ，请你对链表进行分隔，使得所有 小于 x 的节点都出现在 大于或等于 x 的节点之前。
你应当 保留 两个分区中每个节点的初始相对位置


输入：head = [1,4,3,2,5,2], x = 3
输出：[1,2,2,4,3,5]

输入：head = [2,1], x = 2
输出：[1,2]

链表中节点的数目在范围 [0, 200] 内
-100 <= Node.val <= 100
-200 <= x <= 200


pre    target    tail
-1       3        3

 */

class partition {
    public ListNode partition(ListNode head, int x) {
        ListNode pre = new ListNode(-1);
        ListNode minPre = null;
        ListNode maxPre = null;

        ListNode current = head;
        while (current != null) {
            ListNode next = current.next;
            if (current.val < x) {
                ListNode next1;
                if (minPre == null) {
                    next1 = pre.next;
                    pre.next = current;
                } else {
                    next1 = minPre.next;
                    minPre.next = current;
                }
                current.next = next1;
                minPre = current;
            } else {
                if (maxPre == null) {
                    ListNode next1;
                    if (minPre == null) {
                        next1 = pre.next;
                        pre.next = current;
                    } else {
                        next1 = minPre.next;
                        minPre.next = current;
                    }
                    current.next = next1;
                } else {
                    ListNode next1 = maxPre.next;
                    maxPre.next = current;
                    current.next = next1;
                }
                maxPre = current;

            }
            current = next;
        }


        return pre.next;

    }
}

//146
/*
请你设计并实现一个满足  LRU (最近最少使用) 缓存 约束的数据结构。
实现 LRUCache 类：
LRUCache(int capacity) 以 正整数 作为容量 capacity 初始化 LRU 缓存
int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
void put(int key, int value) 如果关键字 key 已经存在，则变更其数据值 value ；
如果不存在，则向缓存中插入该组 key-value 。如果插入操作导致关键字数量超过 capacity ，则应该 逐出 最久未使用的关键字。
函数 get 和 put 必须以 O(1) 的平均时间复杂度运行


输入
["LRUCache", "put",   "put",   "get",   "put",   "get",   "put",   "get", "get", "get"]
[[2],       [1, 1],  [2, 2],    [1],    [3, 3],   [2],   [4, 4],    [1],   [3],   [4]]
输出
[null,       null,    null,      1,      null,     -1,    null,     -1,    3,     4]

解释
LRUCache lRUCache = new LRUCache(2);
lRUCache.put(1, 1); // 缓存是 {1=1}
lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
lRUCache.get(1);    // 返回 1
lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
lRUCache.get(2);    // 返回 -1 (未找到)
lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
lRUCache.get(1);    // 返回 -1 (未找到)
lRUCache.get(3);    // 返回 3
lRUCache.get(4);    // 返回 4

 */

//class Node {
//    public int key;
//    public int val;
//    public Node pre;
//    public Node next;
//
//    public Node(int key, int val) {
//        this.key = key;
//        this.val = val;
//    }
//}

//class LRUCache {
//
//    private int capacity;
//    private Map<Integer, Node> cache;
//    public Node head;
//    public Node tail;
//
//    public LRUCache(int capacity) {
//        this.capacity = capacity;
//        this.cache = new HashMap<>();
//        this.head = new Node(0, 0);
//        this.tail = new Node(0, 0);
//        this.head.next = this.tail;
//        this.tail.pre = this.head;
//    }
//
//    public int get(int key) {
//        Node node = cache.get(key);
//        if (node == null) {
//            return -1;
//        }
//        moveToHead(node);
//
//        return node.val;
//    }
//
//    public void put(int key, int value) {
//        Node node = cache.get(key);
//        if (node == null) {
//            node = new Node(key, value);
//            cache.put(key, node);
//            capacity--;
//        } else {
//            node.val = value;
//        }
//
//        // 移到最前面
//        moveToHead(node);
//
//
//        // 移除尾部
//        if (this.capacity < 0) {
//            Node removeTail = removeTail();
//            cache.put(removeTail.key, null);
//            capacity++;
//        }
//    }
//
//
//    public void moveToHead(Node node) {
//        Node next = this.head.next;
//        if (next != node) {
//            Node nextNode = node.next;
//            Node preNode = node.pre;
//            if (nextNode != null && preNode != null) {
//                nextNode.pre = preNode;
//                preNode.next = nextNode;
//            }
//
//            node.next = next;
//            next.pre = node;
//
//            node.pre = this.head;
//            this.head.next = node;
//        }
//
//    }
//
//    public Node removeTail() {
//        Node preNode = this.tail.pre;
//        Node newPreNode = preNode.pre;
//        if (newPreNode != null) {
//            newPreNode.next = tail;
//        }
//        tail.pre = newPreNode;
//        return preNode;
//    }
//}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */