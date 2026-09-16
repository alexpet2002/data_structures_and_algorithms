package com.data_structures.lists;

public class Node<N, K> {

    private K nxt;
    private N dataInsideNode;

    public Node(N dataInsideNode) {
        this.dataInsideNode = dataInsideNode;
    }

    public K getNxt() {
        return nxt;
    }

    public void setNxt(K nxt) {
        this.nxt = nxt;
    }

    public N getDataInsideNode() {
        return dataInsideNode;
    }

    public void setDataInsideNode(N dataInsideNode) {
        this.dataInsideNode = dataInsideNode;
    }
}