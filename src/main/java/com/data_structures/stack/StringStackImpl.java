package com.data_structures.stack;

import com.data_structures.lists.Node;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.NoSuchElementException;

public class StringStackImpl implements StackInt<String[]> {

    /*
     * N = String[] because each node stores a String array.
     * K = StackNode because getNxt() returns the next stack node.
     */
    private static final class StackNode
            extends Node<String[], StackNode> {

        private StackNode(String[] data) {
            super(data);
        }
    }

    private StackNode head;
    private StackNode tail;
    private int numOfNodes;

    public StringStackImpl() {
        head = null;
        tail = null;
        numOfNodes = 0;
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public void push(String[] item) {
        StackNode newNode = new StackNode(item);

        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.setNxt(head);
            head = newNode;
        }

        numOfNodes++;
    }

    @Override
    public String[] pop() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException(
                    "The stack is empty."
            );
        }

        StackNode poppedNode = head;
        head = head.getNxt();

        poppedNode.setNxt(null);

        if (head == null) {
            tail = null;
        }

        numOfNodes--;

        return poppedNode.getDataInsideNode();
    }

    @Override
    public String[] peek() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException(
                    "The stack is empty."
            );
        }

        return head.getDataInsideNode();
    }

    @Override
    public void printStack(PrintStream stream) {
        StackNode current = head;

        while (current != null) {
            stream.print(
                    Arrays.toString(current.getDataInsideNode())
            );

            if (current.getNxt() != null) {
                stream.print(" ");
            }

            current = current.getNxt();
        }

        stream.println();
    }

    @Override
    public int size() {
        return numOfNodes;
    }
}
