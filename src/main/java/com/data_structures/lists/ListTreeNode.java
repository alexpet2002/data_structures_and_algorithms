package com.data_structures.lists;

import com.data_structures.shapes.Point;
import com.data_structures.tree.TreeNode;

public class ListTreeNode implements LinkedList<TreeNode> {
    protected TreeNode head = null;
    protected TreeNode tail = null;

    @Override
    public void insertAtFront(TreeNode treeNode) {
        if (isEmpty()) {
            tail = treeNode;
            head = treeNode;

        } else {
            // here we don't store the node that is first in the list,
            // so we are setting as next the head(which is atm the first node)
            treeNode.setNxt(head);
            head = treeNode;
        }

    }

    @Override
    public void insertAtBack(TreeNode treeNode) {
        if (isEmpty()) {
            tail = treeNode;
            head = treeNode;
        } else {
            tail.setNxt(treeNode);
            tail = treeNode;
        }

    }

    @Override
    public TreeNode removeFromFront() throws Exception {
        TreeNode treeNode = head;
        if (isEmpty())
            throw new Exception("the list is empty!");

        if (head == tail)
            head = tail = null;

        else
            head = head.getNxt();
        return treeNode;
    }

    @Override
    public TreeNode removeFromBack() throws Exception {
        TreeNode treeNode = head;
        if (isEmpty())
            throw new Exception("the list is empty!");

        if (head == tail)
            head = tail = null;

        else {
            TreeNode iterator = head;
            while (iterator.getNxt() != tail)
                iterator = iterator.getNxt();

            iterator.setNxt(null);
            tail = iterator;
        }

        return treeNode;

    }

    @Override
    public boolean isEmpty() {
        return head == null && tail == null;
    }

    @Override
    public int traverse() {
        return 0;
    }

    @Override
    public String toString() {
        return "";
    }

}
