package com.data_structures.lists;

public class List<P> implements LinkedList<IntegerNode> {
    protected IntegerNode head = null;
    protected IntegerNode tail = null;

    @Override
    public void insertAtFront(IntegerNode node) {
        if (isEmpty()) {
            tail = node;
            head = node;

        } else {
            // here we don't store the node that is first in the list,
            // so we are setting as next the head(which is atm the first node)
            node.setNxt(head);
            head = node;
        }

    }

    @Override
    public void insertAtBack(IntegerNode node) {
        if (isEmpty()) {
            tail = node;
            head = node;
        } else {
            tail.setNxt(node);
            tail = node;
        }

    }

    @Override
    public IntegerNode removeFromFront() throws Exception {
        IntegerNode node = head;
        if (isEmpty())
            throw new Exception("the list is empty!");

        if (head == tail)
            head = tail = null;

        else
            head = head.getNxt();
        return node;
    }

    @Override
    public IntegerNode removeFromBack() throws Exception {
        IntegerNode node = head;
        int dataInsideNode = tail.getDataInsideNode();

        if (isEmpty())
            throw new Exception("the list is empty!");

        if (head == tail)
            head = tail = null;

        else {
            IntegerNode iterator = head;
            while (iterator.getNxt() != tail)
                iterator = iterator.getNxt();

            iterator.setNxt(null);
            tail = iterator;
        }

        return node;

    }

    @Override
    public boolean isEmpty() {
        return head == null && tail == null;
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "com.data_structures.lists.List is empty :(";
        }

        IntegerNode current = head;

        StringBuilder ret = new StringBuilder();

        // while not at end of list, output current node's data
//        ret.append(" HEAD -> ");

        while (current != null) {
            ret.append(current.getDataInsideNode());

            if (current.getNxt() != null)
                ret.append(" ");

            current = current.getNxt();
        }

        ret.append(" ");

        return ret.toString();
    }

    @Override
    public int traverse() {
        int sum = 0;

        IntegerNode current = head;

        IntegerNode previous = null;

        while (current != null) {

            sum += current.getDataInsideNode();

            previous = current;

            current = current.getNxt();

        }

        return sum;

    }

}
