package com.data_structures.shapes;

import com.data_structures.tuples.Tuple2;

public class Rectangle implements RectangleInt {

    int x1, x2, y1, y2;
    public Rectangle(int xmin, int ymin, int xmax, int ymax) {
        this.y1 = ymin;
        this.y2 = ymax;
        this.x1 = xmin;
        this.x2 = xmax;
    }

    @Override
    public int xmin() {
        return x1;
    }

    @Override
    public int ymin() {
        return y1;
    }

    @Override
    public int xmax() {
        return x2;
    }

    @Override
    public int ymax() {
        return y2;
    }

    @Override
    public boolean contains(Point p) {
        return p.x() >= xmin() && p.x() <= xmax() && p.y() <= ymax() && p.y() >= ymin();
    }


    @Override
    public boolean intersects(Rectangle that) {

        int xmin1 = this.xmin();
        int ymin1 = this.ymin();
        int xmax1 = this.xmax();
        int ymax1 = this.ymax();
        int xmin2 = that.xmin();
        int ymin2 = that.ymin();
        int xmax2 = that.xmax();
        int ymax2 = that.ymax();

        int xmin_intersect = Math.max(xmin1, xmin2);
        int ymin_intersect = Math.max(ymin1, ymin2);
        int xmax_intersect = Math.min(xmax1, xmax2);
        int ymax_intersect = Math.min(ymax1, ymax2);

        if (xmax_intersect <= xmin_intersect || ymax_intersect <= ymin_intersect) {
            // rectangles aren't intersecting
            return false;
        } else {
            // rectangles intersecting
            return true;
        }

    }


    @Override
    public double distanceTo(Point p) {
        double closeX = Math.max(Math.min(p.x(), this.xmax()), this.xmin());
        double closeY = Math.max(Math.min(p.y(), this.ymax()), this.ymin());
        return Math.sqrt((p.x() - closeX) * (p.x() - closeX) + (p.y() - closeY) * (p.y() - closeY));
    }

    @Override
    public int squareDistanceTo(Point p) {
        // Determine the closest point on the rectangle to the given point
        double dx = Math.max(Math.max(this.xmin() - p.x(), 0), p.x() - this.xmax());
        double dy = Math.max(Math.max(this.ymin() - p.y(), 0), p.y() - this.ymax());
        return (int) (dx * dx + dy * dy);
    }

    public String toString() {
        return "[" + xmin() + "," + ymin() + "] * [" + xmax() + "," + ymax() + "]";
    }
    public static Tuple2<Rectangle , Rectangle > splitX(Rectangle initial, Point p){
        Rectangle rl = new Rectangle(initial.xmin(), initial.ymin(), p.x(), initial.ymax() );
        Rectangle rr = new Rectangle(p.x(), initial.ymin(), initial.xmax(), initial.ymax());
        return new Tuple2<>(rl,rr);

    }
    public static Tuple2<Rectangle , Rectangle > splitY(Rectangle initial, Point p){
        Rectangle ru = new Rectangle(initial.xmin(), p.y(), initial.xmax(), initial.ymax());
        Rectangle rd = new Rectangle(initial.xmin(), initial.ymin(), initial.xmax(), p.y());
        return new Tuple2<>(rd, ru);
    }


}

