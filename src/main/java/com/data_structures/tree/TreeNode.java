package com.data_structures.tree;
import com.data_structures.shapes.Point;

public class TreeNode {
    Point point; // an object of the class com.data_structures.shapes.Point
    TreeNode l; // pointer to left subtree
    TreeNode r;// pointer to right subtree
    int position = 0;
    protected TreeNode nxt;

    public TreeNode getL() {
        return l;
    }

    public TreeNode getR() {
        return r;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getPosition() {
        return position;
    }

    public TreeNode() {
        this.point = null;
        this.l = null;
        this.r = null;
    }

    public void incrementPosition(){
        this.position++;
    }

    public TreeNode(Point point) {
        this.point = point;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public void setL(TreeNode l) {
        this.l = l;
    }

    public void setR(TreeNode r) {
        this.r = r;
    }

    public TreeNode getNxt() {
        return this.nxt;
    }

    public void setNxt(TreeNode treeNode) {
        this.nxt = treeNode;
    }

}