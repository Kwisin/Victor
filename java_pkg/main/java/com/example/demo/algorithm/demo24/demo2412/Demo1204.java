package com.example.demo.algorithm.demo24.demo2412;


import java.util.*;

public class Demo1204 {

    public static void main(String[] args) {
        ListNode node0 = new ListNode(1);
        ListNode nextNode1 = new ListNode(2);
        ListNode nextNode2 = new ListNode(3);
        ListNode nextNode3 = new ListNode(4);
        ListNode nextNode4 = new ListNode(5);
        ListNode nextNode5 = new ListNode(6);
        ListNode nextNode6 = new ListNode(7);
        ListNode nextNode7 = new ListNode(8);
        ListNode nextNode8 = new ListNode(9);

        node0.next = nextNode1;
        nextNode1.next = nextNode2;
        nextNode2.next = nextNode3;
        nextNode3.next = nextNode4;
        nextNode4.next = nextNode5;
        nextNode5.next = nextNode6;
        nextNode6.next = nextNode7;
        nextNode7.next = nextNode8;

        ListNode listNode = new reverseKGroup().reverseKGroup(node0, 2);

        System.out.println();
    }


}

//138
/*
给你一个长度为 n 的链表，每个节点包含一个额外增加的随机指针 random ，该指针可以指向链表中的任何节点或空节点。

构造这个链表的 深拷贝。 深拷贝应该正好由 n 个 全新 节点组成，其中每个新节点的值都设为其对应的原节点的值。
新节点的 next 指针和 random 指针也都应指向复制链表中的新节点，并使原链表和复制链表中的这些指针能够表示相同的链表状态。
复制链表中的指针都不应指向原链表中的节点 。

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
// todo 可优化，O(1)空间复杂度完成
class copyRandomListNode {
    int val;
    copyRandomListNode next;
    copyRandomListNode random;

    public copyRandomListNode(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}

class copyRandomList {
    public copyRandomListNode copyRandomList(copyRandomListNode head) {
        if (head == null) {
            return null;
        }
        // 新的node列表
        List<copyRandomListNode> nodeList = new ArrayList<>();
        // 每个node对应的位置，方便后面定位randomNode位置
        Map<copyRandomListNode, Integer> nodeIndexMap = new HashMap<>();
        copyRandomListNode current = head;
        int index = 0;
        while (current != null) {
            copyRandomListNode copyNode = new copyRandomListNode(current.val);
            nodeList.add(copyNode);
            nodeIndexMap.put(current, index);
            index++;
            current = current.next;
        }

        current = head;
        Map<copyRandomListNode, Integer> randomNodeIndexMap = new HashMap<>();
        while (current != null) {
            copyRandomListNode random = current.random;
            if (random != null) {
                randomNodeIndexMap.put(current, nodeIndexMap.get(random));
            }
            current = current.next;
        }

        current = head;
        index = 0;
        while (current != null) {
            copyRandomListNode copyNode = nodeList.get(index);
            copyNode.random = current.random == null ? null : nodeList.get(randomNodeIndexMap.get(current));
            copyNode.next = index + 1 < nodeList.size() ? nodeList.get(index + 1) : null;
            index++;
            current = current.next;
        }

        return nodeList.get(0);

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
// todo 可优化，一次遍历完成反转
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

class reverseBetween {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (head == null || left >= right || left == 0) {
            return head;
        }
        left--;
        right--;

        List<ListNode> nodeList = new ArrayList<>();
        ListNode current = head;
        while (current != null) {
            nodeList.add(current);
            current = current.next;
        }

        while (left < right) {
            ListNode leftNode = nodeList.get(left);
            ListNode rightNode = nodeList.get(right);

            nodeList.set(left, rightNode);
            nodeList.set(right, leftNode);

            left++;
            right--;
        }

        for (int i = 0; i < nodeList.size(); i++) {
            ListNode node = nodeList.get(i);
            node.next = i + 1 < nodeList.size() ? nodeList.get(i + 1) : null;
        }

        return nodeList.get(0);
    }
}

// 25
/*
给你链表的头节点 head ，每 k 个节点一组进行翻转，请你返回修改后的链表。

k 是一个正整数，它的值小于或等于链表的长度。如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。

你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。

输入：head = [1,2,3,4,5], k = 2
输出：[2,1,4,3,5]

1->2->3->4->5
-1 2 1 3
pre = -1   current = 1   next = 2
-1-> 1

输入：head = [1,2,3,4,5], k = 3
输出：[3,2,1,4,5]
pre    gapTail     current
-1
          1,         2,         3,         4,        5

 gap = 2


 */

// 你可以设计一个只用 O(1) 额外内存空间的算法解决此问题吗
class reverseKGroup {
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || k == 0 || k == 1) {
            return head;
        }

        // 统计长度
        int length = 0;
        ListNode current = head;
        while (current != null) {
            length++;
            current = current.next;
        }

        ListNode newHead = new ListNode(-1);
        newHead.next = head;

        ListNode pre = newHead;
        ListNode gapTail = head;
        current = head.next;
        int gap = k - 1;

        while (current != null) {
            ListNode nextCurrent = current.next;

            if (length >= k) {
                ListNode preNext = pre.next;
                pre.next = current;
                current.next = preNext;
            } else {
                pre.next = current;
            }

            current = nextCurrent;
            gap--;
            if (gap == 0) {
                pre = gapTail;
                gapTail = current;
                current = current.next;
                gap = k - 1;
                length -= k;
            }
        }


        return newHead.next;
    }
}