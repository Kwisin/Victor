package com.example.demo.algorithm.year25.month12;

import java.util.*;

public class Day25 {
    public static void main(String[] args) {
        Day25 day25 = new Day25();

        // 1. 反转链表
        ListNode head1 = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        ListNode reversed = day25.reverseList(head1);
        System.out.println("Reversed List Head: " + (reversed != null ? reversed.val : "null"));

        // 2. 环形链表 II
        ListNode head2 = new ListNode(3);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(0);
        ListNode node4 = new ListNode(-4);
        head2.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node2; // 环指向 2
        ListNode cycleNode = day25.detectCycle(head2);
        System.out.println("Cycle Node Val: " + (cycleNode != null ? cycleNode.val : "null")); // 2

        // 3. 翻转二叉树
        TreeNode root3 = new TreeNode(4,
                new TreeNode(2, new TreeNode(1), new TreeNode(3)),
                new TreeNode(7, new TreeNode(6), new TreeNode(9)));
        day25.invertTree(root3);
        System.out.println("Root val: " + root3.val); // 简单的打印验证

        // 4. 二叉树的层序遍历
        System.out.println(day25.levelOrder(root3));
    }

    // 链表节点定义
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    // 二叉树节点定义
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    /*
    206. 反转链表 (Reverse Linked List)
    简单
    给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。

    示例 1：输入：head = [1,2,3,4,5] -> 输出：[5,4,3,2,1]
    */
    /*
    206. 反转链表 (Reverse Linked List)
    使用标准三指针法 (prev, curr, next)
    */
    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode next = curr.next; // 暂存下一步
            curr.next = prev;          // 反转指向
            prev = curr;               // prev 前移
            curr = next;               // curr 前移
        }
        return prev;
    }

    /*
    142. 环形链表 II (Linked List Cycle II)
    推荐使用快慢指针法 (O(1) 空间复杂度)
    */
    public ListNode detectCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        // 1. 判断是否有环
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            // 相遇说明有环
            if (slow == fast) {
                // 2. 找入口：slow 回到起点，fast 留在相遇点，同步走
                ListNode ptr = head;
                while (ptr != slow) {
                    ptr = ptr.next;
                    slow = slow.next;
                }
                return ptr;
            }
        }
        return null;
    }

    /*
    226. 翻转二叉树 (Invert Binary Tree)
    */
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }

        TreeNode left = root.left;
        root.left = root.right;
        root.right = left;

        invertTree(root.left);
        invertTree(root.right);
        return root;
    }

    /*
    102. 二叉树的层序遍历 (Binary Tree Level Order Traversal)
    使用 Queue + size 控制层级
    */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) return ans;

        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size(); // 关键：锁定当前层节点数
            List<Integer> currentLevel = new ArrayList<>();

            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                currentLevel.add(node.val);

                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
            ans.add(currentLevel);
        }
        return ans;
    }

    /*
    104. 二叉树的最大深度 (Maximum Depth of Binary Tree)
    简单
    给定一个二叉树，找出其最大深度。
    二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。

    示例：root = [3,9,20,null,null,15,7] -> 输出：3
    */
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        ArrayList<TreeNode> row = new ArrayList<>();
        row.add(root);
        int depth = 0;
        while (!row.isEmpty()) {
            depth++;

            ArrayList<TreeNode> temp = new ArrayList<>();
            for (TreeNode node : row) {
                if (node.left != null) {
                    temp.add(node.left);
                }
                if (node.right != null) {
                    temp.add(node.right);
                }
            }

            row = temp;

        }

        return depth;
    }

    /*
    101. 对称二叉树 (Symmetric Tree)
    简单
    给你一个二叉树的根节点 root ， 检查它是否轴对称。

    示例 1：root = [1,2,2,3,4,4,3] -> true
    示例 2：root = [1,2,2,null,3,null,3] -> false
    */
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }

        ArrayList<TreeNode> row = new ArrayList<>();
        row.add(root.left);
        row.add(root.right);
        while (!row.isEmpty()) {

            int left = 0;
            int right = row.size() - 1;
            while (left <= right) {
                if ((row.get(left) == null || row.get(right) != null) ||
                        (row.get(left) != null || row.get(right) == null)) {
                    return false;
                }
                if (row.get(left) != null && row.get(right) != null && row.get(left).val != row.get(right).val) {
                    return false;
                }
                left++;
                right--;
            }

            ArrayList<TreeNode> temp = new ArrayList<>();
            for (TreeNode node : row) {
                temp.add(node.left);
                temp.add(node.right);
            }

            row = temp;
        }

        return true;
    }

    /*
    98. 验证二叉搜索树 (Validate Binary Search Tree)
    中等
    给你一个二叉树的根节点 root ，判断其是否是一个有效的二叉搜索树。
    有效 BST 定义：
    - 节点的左子树只包含 小于 当前节点的数。
    - 节点的右子树只包含 大于 当前节点的数。
    - 所有左子树和右子树自身必须也是二叉搜索树。

    示例 1：root = [2,1,3] -> true
    */
    public boolean isValidBST(TreeNode root) {
        return checkBST(root, new Stack<TreeNode>());
    }

    public boolean checkBST(TreeNode root, Stack<TreeNode> stack) {
        if (root == null) {
            return true;
        }

        if (!checkBST(root.left, stack)) {
            return false;
        }

        if (!stack.isEmpty() && root.val <= stack.peek().val) {
            return false;
        }

        stack.push(root);

        return checkBST(root.right, stack);
    }

    /*
    21. 合并两个有序链表 (Merge Two Sorted Lists)
    简单
    将两个升序链表合并为一个新的 升序 链表并返回。
    新链表是通过拼接给定的两个链表的所有节点组成的。

    示例 1：l1 = [1,2,4], l2 = [1,3,4] -> [1,1,2,3,4,4]
    */
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null) return list2;
        if (list2 == null) return list1;

        ListNode curr = list1;
        ListNode pre = null;
        ListNode next = curr.next;

        ListNode curr2 = list2;
        ListNode head = new ListNode(0, list1);
        while (curr2 != null) {

            ListNode next1 = curr2.next;

            if (curr2.val < curr.val) {
                curr2.next = curr;
                if (pre == null) {
                    pre = curr2;
                } else {
                    pre.next = curr2;
                    pre = curr2;
                }
            } else if (curr2.val > curr.val && (next == null || curr2.val < next.val)) {
                curr.next = curr2;
                curr2.next = next;
                pre = curr;
                curr = curr2;
            } else {
                pre = curr;
                curr = next;
                next = next.next;
            }

            curr2 = next1;

        }

        return head.next;
    }
}
