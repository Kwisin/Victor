package com.example.demo.study.demo2412;


import java.util.*;

public class Demo1204 {

    public static void main(String[] args) {
        int i = new longestConsecutive().longestConsecutive(new int[]{0, -1});
        System.out.println();
    }


}

//138
/*
给你一个长度为 n 的链表，每个节点包含一个额外增加的随机指针 random ，该指针可以指向链表中的任何节点或空节点。

构造这个链表的 深拷贝。 深拷贝应该正好由 n 个 全新 节点组成，其中每个新节点的值都设为其对应的原节点的值。新节点的 next 指针和 random 指针也都应指向复制链表中的新节点，并使原链表和复制链表中的这些指针能够表示相同的链表状态。复制链表中的指针都不应指向原链表中的节点 。

例如，如果原链表中有 X 和 Y 两个节点，其中 X.random --> Y 。那么在复制链表中对应的两个节点 x 和 y ，同样有 x.random --> y 。

返回复制链表的头节点。

用一个由 n 个节点组成的链表来表示输入/输出中的链表。每个节点用一个 [val, random_index] 表示：

val：一个表示 Node.val 的整数。
random_index：随机指针指向的节点索引（范围从 0 到 n-1）；如果不指向任何节点，则为  null 。
你的代码 只 接受原链表的头节点 head 作为传入参数。

输入：head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
输出：[[7,null],[13,0],[11,4],[10,2],[1,0]]

输入：head = [[1,1],[2,1]]
输出：[[1,1],[2,1]]

输入：head = [[3,null],[3,0],[3,null]]
输出：[[3,null],[3,0],[3,null]]

 */


// Definition for a Node.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}

class copyRandomList {
    public Node copyRandomList(Node head) {

    }
}


// 92
/*
给你单链表的头指针 head 和两个整数 left 和 right ，其中 left <= right 。请你反转从位置 left 到位置 right 的链表节点，返回 反转后的链表

输入：head = [1,2,3,4,5], left = 2, right = 4
输出：[1,4,3,2,5]

输入：head = [5], left = 1, right = 1
输出：[5]
 */

// Definition for singly-linked list.
class reverseBetweenNode {
    int val;
    reverseBetweenNode next;

    reverseBetweenNode() {
    }

    reverseBetweenNode(int val) {
        this.val = val;
    }

    reverseBetweenNode(int val, reverseBetweenNode next) {
        this.val = val;
        this.next = next;
    }
}

class reverseBetween {
    public reverseBetweenNode reverseBetween(reverseBetweenNode head, int left, int right) {

    }
}

// 25
/*
给你链表的头节点 head ，每 k 个节点一组进行翻转，请你返回修改后的链表。

k 是一个正整数，它的值小于或等于链表的长度。如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。

你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。

输入：head = [1,2,3,4,5], k = 2
输出：[2,1,4,3,5]


输入：head = [1,2,3,4,5], k = 3
输出：[3,2,1,4,5]


 */

//Definition for singly-linked list.
class reverseKGroupListNode {
    int val;
    reverseKGroupListNode next;

    reverseKGroupListNode() {
    }

    reverseKGroupListNode(int val) {
        this.val = val;
    }

    reverseKGroupListNode(int val, reverseKGroupListNode next) {
        this.val = val;
        this.next = next;
    }
}

class reverseKGroup {
    public reverseKGroupListNode reverseKGroup(reverseKGroupListNode head, int k) {

    }
}