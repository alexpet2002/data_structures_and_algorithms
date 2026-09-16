package com.data_structures.hashmap;

import com.data_structures.lists.Node2;

public class Hashmap<K, V> {
    // class that implements a custom array which contains Nodes with parameters <K,V>
    // K -> (key) ID of the disk
    // V -> (value) a Tuple that contains a disk object and the value of free space

    private static final int DEFAULT_ARRAY_SIZE = 10000;
    // creating an array that contains nodes
    private final Node2<K, V>[] nodeList = new Node2[DEFAULT_ARRAY_SIZE];

    // method to add a node to the com.data_structures.lists.List
    public void put(K key, V value) throws Exception {

        long hashCode = this.getHashCode(key);
        int index = this.getIndex(hashCode);
        // exception in case the amount of elements exceeds 1000
        if (index > DEFAULT_ARRAY_SIZE) {
            throw new Exception("Invalid key");
        }

        if (this.nodeList[index] != null) {
            // get the node at a given index
            Node2<K, V> node2 = this.nodeList[index];
            // increment until you get to the last node
            // now node2 represents the last node
            while (node2.getNext() != null) {
                node2 = node2.getNext();
            }

            // We're creating the new node.
            Node2<K, V> newNode = new Node2<>();
            newNode.setKey(key);
            newNode.setValue(value);
            newNode.setHashCode(hashCode);
            // setting the new node as the next node of node2
            node2.setNext(newNode);

        } else {
            // We're creating the new node.
            Node2<K, V> newNode = new Node2<>();
            newNode.setKey(key);
            newNode.setValue(value);
            newNode.setHashCode(hashCode);
            // adding the node directly since the array at given index is null
            this.nodeList[index] = newNode;
        }
    }

    // method returns a value that corresponds to a given key
    public V get(K key) {
        // traverse the array
        V value = null;
        int index = getIndex(getHashCode(key));
        Node2<K, V> node = nodeList[index];
        while (node != null) {
            if (node.getKey().equals(key)) {
                value = node.getValue();
                break;
            }
            node = node.getNext();
        }
        return value;
    }

    // method to remove a com.data_structures.lists.Node
    public void remove(K key) {
        int index = getIndex(getHashCode(key));
        Node2<K, V> previous = null;
        Node2<K, V> node = nodeList[index];
        while (node != null) {
            if (node.getKey().equals(key)) {
                if (previous == null) {
                    node = node.getNext();
                    nodeList[index] = node;
                    return;
                } else {
                    previous.setNext(node.getNext());
                    return;
                }
            }
            previous = node;
            node = node.getNext();
        }
    }

    // getters
    private long getHashCode(K key) {
        // gets a unique hashcode for a string
        String keyString = key.toString();
        return keyString.hashCode();
    }

    private int getIndex(long hashCode) {

        return Math.toIntExact(hashCode % DEFAULT_ARRAY_SIZE);
    }

    public Node2<K, V>[] getNodeList() {
        return nodeList;
    }
}