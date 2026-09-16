package com.data_structures.lists;

import com.data_structures.lists.LinkedList;
import com.data_structures.shapes.Point;
public class ListPoint<P> implements LinkedList<Point> {
    protected Point head = null;
    protected Point tail = null;

    @Override
    public void insertAtFront(Point point) {
        if (isEmpty()) {
            tail = point;
            head = point;

        } else {
            // here we don't store the node that is first in the list,
            // so we are setting as next the head(which is atm the first node)
            point.setNxt(head);
            head = point;
        }

    }

    @Override
    public void insertAtBack(Point point) {
        if (isEmpty()) {
            tail = point;
            head = point;
        } else {
            tail.setNxt(point);
            tail = point;
        }

    }

    @Override
    public Point removeFromFront() throws Exception {
        Point pointData = head;
        if (isEmpty())
            throw new Exception("the list is empty!");

        if (head == tail)
            head = tail = null;

        else
            head = head.getNxt();
        return pointData;
    }

    @Override
    public Point removeFromBack() throws Exception {
        Point pointData = head;
        if (isEmpty())
            throw new Exception("the list is empty!");

        if (head == tail)
            head = tail = null;

        else {
            Point iterator = head;
            while (iterator.getNxt() != tail)
                iterator = iterator.getNxt();

            iterator.setNxt(null);
            tail = iterator;
        }

        return pointData;

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
        if (isEmpty()) {
            return "No elements found! ";
        }

        Point current = head;

        StringBuilder ret = new StringBuilder();

        while (current != null) {
            ret.append("(");
            ret.append(current.x());
            ret.append(",");
            ret.append(current.y());
            ret.append(")");

            if (current.getNxt() != null)
                ret.append(" ");

            current = current.getNxt();
        }

        ret.append(" ");

        return ret.toString();
    }

}
