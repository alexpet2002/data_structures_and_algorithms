package com.data_structures.shapes;

public class Point implements PointInt {
    int x, y;
    // pointer to the next point for list implementation
    Point next;

    public Point() {

    }

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int x() {
        return x;
    }

    @Override
    public int y() {
        return y;
    }

    @Override
    public double distanceTo(Point z) {
        int ycoord = Math.abs(this.y() - z.y());
        int xcoord = Math.abs(this.x() - z.x());
        return Math.sqrt((ycoord) * (ycoord) + (xcoord) * (xcoord));
    }

    @Override
    public int squareDistanceTo(Point z) {
        int ycoord = Math.abs(this.y() - z.y());
        int xcoord = Math.abs(this.x() - z.x());
        return ((ycoord) * (ycoord) + (xcoord) * (xcoord));
    }

    public String toString() {
        return x() + "," + y();
    }

    public void setNxt(Point head) {
    }

    public Point getNxt() {
        return next;
    }
}
