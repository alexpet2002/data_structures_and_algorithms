package com.data_structures.lists;

public class NodeString {

    private NodeString nxt;
    private String[] dataInsideNode;

    public NodeString(String[] dataInsideNode) {
        this.dataInsideNode = dataInsideNode;
    }

    public NodeString getNxt() {
        return nxt;
    }

    public void setNxt(NodeString nxt) {
        this.nxt = nxt;
    }

    public String[] getDataInsideNode() {
        return dataInsideNode;
    }

    public void setDataInsideNode(String[] dataInsideNode) {
        this.dataInsideNode = dataInsideNode;
    }
}