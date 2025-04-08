package com.example.demo.algorithm.demo24.demo2412;


import java.util.*;

public class Demo1206 {

    public static void main(String[] args) {
            /*
    [0,0,0,0,null,null,0,null,null,null,0]
    [0,#,0,0,#,0,0,#,0,#]

    [0,#,0,0,#,0,0,0,#,0,#]
                    1
            2               3
        4     null    null       5
   null  null                null   6
     */
        Node node1 = new Node(1);
        Node nextNode2 = new Node(2);
        Node nextNode3 = new Node(3);
        Node nextNode4 = new Node(4);
        Node nextNode5 = new Node(5);
        Node nextNode6 = new Node(6);
        node1.left = nextNode2;
        node1.right = nextNode3;

        nextNode2.left = nextNode4;

        nextNode3.right = nextNode5;

        nextNode5.right = nextNode6;

        Node connect = new connect().connect(node1);
        System.out.println();
    }


}

//105
/*
给定两个整数数组 preorder 和 inorder ，其中 preorder 是二叉树的先序遍历， inorder 是同一棵树的中序遍历，请构造二叉树并返回其根节点

输入: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
输出: [3,9,20,null,null,15,7]

输入: preorder = [-1], inorder = [-1]
输出: [-1]

1 <= preorder.length <= 3000
inorder.length == preorder.length
-3000 <= preorder[i], inorder[i] <= 3000
preorder 和 inorder 均 无重复 元素
inorder 均出现在 preorder
preorder 保证 为二叉树的前序遍历序列
inorder 保证 为二叉树的中序遍历序列

 */


class TreeNode {
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

class buildTree {
    /*
     根据中序遍历确定根结点
     到先序遍历中找出左右分支

     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int length = preorder.length;
        if (length == 0 || inorder.length != length) {
            return null;
        }
        if (length == 1) {
            return new TreeNode(preorder[0]);
        }


        int root = preorder[0];
        int rootIndex = 0;
        for (int index = 0; index < length; index++) {
            if (inorder[index] == root) {
                rootIndex = index;
            }
        }

        //输入: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
        int[] newLeftInOrder = Arrays.copyOfRange(inorder, 0, rootIndex);//1
        int[] newLeftPreOrder = Arrays.copyOfRange(preorder, 1, newLeftInOrder.length + 1);//1
        TreeNode leftNode = buildTree(newLeftPreOrder, newLeftInOrder);

        int[] newRightInOrder = Arrays.copyOfRange(inorder, rootIndex + 1, inorder.length);
        int[] newRightPreorder = Arrays.copyOfRange(preorder, 1 + newLeftPreOrder.length, preorder.length);
        TreeNode rightNode = buildTree(newRightPreorder, newRightInOrder);

        return new TreeNode(root, leftNode, rightNode);
    }
}


//106
/*
给定两个整数数组 inorder 和 postorder ，其中 inorder 是二叉树的中序遍历， postorder 是同一棵树的后序遍历，请你构造并返回这颗 二叉树 。

inorder = [9,3,15,20,7], postorder = [9,15,7,20,3]
输出：[3,9,20,null,null,15,7]

输入：inorder = [-1], postorder = [-1]
输出：[-1]

 */

class buildTreeMid {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        int length = postorder.length;
        if (length == 0 || inorder.length != length) {
            return null;
        }
        if (length == 1) {
            return new TreeNode(postorder[0]);
        }

        int root = postorder[length - 1];
        int rootIndex = 0;
        for (int i = 0; i < length; i++) {
            if (inorder[i] == root) {
                rootIndex = i;
            }
        }

        //rootIndex = 1, root = 3
        //inorder = [9,3,15,20,7], postorder = [9,15,7,20,3]
        int[] newLeftInOrder = Arrays.copyOfRange(inorder, 0, rootIndex);
        int[] newLeftPostOrder = Arrays.copyOfRange(postorder, 0, newLeftInOrder.length);
        TreeNode leftNode = buildTree(newLeftInOrder, newLeftPostOrder);

        int[] newRightInOrder = Arrays.copyOfRange(inorder, rootIndex + 1, inorder.length);
        int[] newRightPostOrder = Arrays.copyOfRange(postorder, newLeftInOrder.length, postorder.length - 1);
        TreeNode rightNode = buildTree(newRightInOrder, newRightPostOrder);


        return new TreeNode(root, leftNode, rightNode);
    }
}

// 117
/*
给定一个二叉树：

struct Node {
  int val;
  Node *left;
  Node *right;
  Node *next;
}
填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL 。

初始状态下，所有 next 指针都被设置为 NULL 。

输入：root = [1,2,3,4,5,null,7]
输出：[1,#,2,3,#,4,5,7,#]
解释：给定二叉树如图 A 所示，你的函数应该填充它的每个 next 指针，以指向其下一个右侧节点，如图 B 所示
。序列化输出按层序遍历顺序（由 next 指针连接），'#' 表示每层的末尾。

输入：root = []
输出：[]


 */

// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {
    }

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};

class connect {

    /*
    [0,0,0,0,null,null,0,null,null,null,0]
    [0,#,0,0,#,0,0,#,0,#]
    
    [0,#,0,0,#,0,0,0,#,0,#]
                    0
            0               0
        0     null    null       0
   null  null                null   0
     */
    public Node connect(Node root) {
        if (root == null) {
            return null;
        }
        List<Node> connectResultNodes = new ArrayList<>();
        connectResultNodes.add(root);
        connectResultNodes.add(null);

        LinkedList<Node> currentRound = new LinkedList<>();
        LinkedList<Node> nextRound = new LinkedList<>();
        currentRound.add(root);
        while (!currentRound.isEmpty()) {
            Node first = currentRound.pop();
            boolean addNull = currentRound.isEmpty();
            if (first.left != null) {
                connectResultNodes.add(first.left);
                nextRound.addLast(first.left);
            }
            if (first.right != null) {
                connectResultNodes.add(first.right);
                nextRound.addLast(first.right);
            }
            if (addNull) {
                connectResultNodes.add(null);
                currentRound = nextRound;
                nextRound = new LinkedList<>();
            }
        }

        for (int i = 0; i < connectResultNodes.size(); i++) {
            if (connectResultNodes.get(i) == null) {
                continue;
            }
            connectResultNodes.get(i).next = i + 1 < connectResultNodes.size() ? connectResultNodes.get(i + 1) : null;
        }

        return root;
    }
}