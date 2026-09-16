package com.data_structures.lists;

public class Node2<K, V> {

    private K key;
    private V value;
    private Node2<K, V> next;
    private long hashCode;

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public Node2<K, V> getNext() {
        return next;
    }

    public void setNext(Node2<K, V> next) {
        this.next = next;
    }

    public long getHashCode() {
        return hashCode;
    }

    public void setHashCode(long hashCode) {
        this.hashCode = hashCode;
    }
}

