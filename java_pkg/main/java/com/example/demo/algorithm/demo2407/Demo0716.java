package com.example.demo.algorithm.demo2407;

import java.util.ArrayList;
import java.util.Collections;

public class Demo0716 {

    public static void main(String[] args) {

        //4,2,1,3
        ListNode listNode4 = new ListNode(3);
        ListNode listNode3 = new ListNode(1, listNode4);
        ListNode listNode2 = new ListNode(2, listNode3);
        ListNode listNode1 = new ListNode(4, listNode2);
        SortList sortList = new SortList();
        ListNode listNode = sortList.sortList(listNode1);
        System.out.println();
    }

}

//class ListNode {
//    int val;
//    ListNode next;
//
//    ListNode() {
//    }
//
//    ListNode(int val) {
//        this.val = val;
//    }
//
//    ListNode(int val, ListNode next) {
//        this.val = val;
//        this.next = next;
//    }
//}

class SortList {
    //链表中节点的数目在范围 [0, 5 * 104] 内
    //-105 <= Node.val <= 105
    public ListNode sortList(ListNode head) {
        if (head == null) {
            return null;
        }

        ArrayList<Integer> integerArrayList = new ArrayList<>();
        ListNode current = head;
        do {
            integerArrayList.add(current.val);
            current = current.next;
        } while (current != null);
        Collections.sort(integerArrayList);

        ListNode newCurrent = head;
        for (Integer i : integerArrayList) {

            if (newCurrent == null) {
                break;
            }

            newCurrent.val = i;
            newCurrent = newCurrent.next;
        }


        return head;
    }

    // todo 归并排序
}


