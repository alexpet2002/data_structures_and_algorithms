package com.data_structures.queues;

import com.data_structures.lists.NodeString;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.NoSuchElementException;

public class StringQueueImpl implements StringQueue {
    public NodeString head = null;
    public NodeString tail = null;
    private int numOfNodes = 0;

    @Override
    public boolean isEmpty() {
        return  head == null && tail == null;
    }

    @Override
    public void put(String[] item) {
        // inserts a node to the end of the queue
        NodeString node = new NodeString(item);
        if (isEmpty()) {
            tail = node;
            head = node;
        } else {
            tail.setNxt(node);
            tail = node;
        }
        numOfNodes++;
    }

    @Override
    public String[] get() throws NoSuchElementException {
        // removes the 1st node and returns the data inside
        if (isEmpty()) throw new NoSuchElementException("the list is empty!");
        String[] dataInsideNode = head.getDataInsideNode();
        if (head == tail) {
            head = tail = null;
        } else {
            head = head.getNxt();
        }
        numOfNodes--;
        return dataInsideNode;
    }

    @Override
    public String[] peek() throws NoSuchElementException {
        if (isEmpty()) throw new NoSuchElementException("the list is empty!");
        return this.head.getDataInsideNode();
    }

    @Override
    public void printQueue(PrintStream stream) {
        NodeString node;
        node = head;
        for (int i = 0; i < numOfNodes; i++) {
            stream.print(Arrays.toString(node.getDataInsideNode()) + " ");
            node = node.getNxt();
        }
    }

    @Override
    public int size() {
        return numOfNodes;
    }

    // TODO: test queue
    public static void main(String[] args) {
        StringQueueImpl stringQueueImp = new StringQueueImpl();
        stringQueueImp.put(new String[]{"0", "0"});
        stringQueueImp.put(new String[]{"1", "1"});
        stringQueueImp.put(new String[]{"2", "2"});

        stringQueueImp.printQueue(System.out);

    }
}
