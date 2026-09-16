package com.algorithms.kdtree;

import com.data_structures.lists.ListTreeNode;
import com.data_structures.shapes.Point;
import com.data_structures.shapes.Rectangle;
import com.data_structures.tuples.Tuple2;

import com.data_structures.tree.TreeNode;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static java.lang.System.exit;

public class TwoDTRee {
    // tree node class
    Rectangle initialRectangle = new Rectangle(0, 0, 100, 100);

    // function to create a new tree from data given in a text file
    // takes as file path as an argument
    public static TwoDTRee newFromFile(String path) {

        BufferedReader reader;
        TwoDTRee twoDTRee = new TwoDTRee();

        try {
            reader = new BufferedReader(new FileReader(path));

            // Getting number of nodes on the first line
            String line = reader.readLine();
            int numberOfNodes = Integer.parseInt(line);

            // Creating an array of Points
            Point[] points = new Point[numberOfNodes];

            line = reader.readLine();
            for (int arrayPointer = 0; arrayPointer < numberOfNodes; arrayPointer++) {

                // Getting the coordinates
                int x = Integer.parseInt(line.split(" ")[0]);
                int y = Integer.parseInt(line.split(" ")[1]);

                checkingIfRightCoordinates(x, y);

                points[arrayPointer] = new Point(x, y);

                // read next line
                line = reader.readLine();
            }
            reader.close();

            for (Point point : points) {
                twoDTRee.insert(point);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return twoDTRee;

    }

    // function to check whether the coordinates given in the text file are within the limit
    private static void checkingIfRightCoordinates(int x, int y) {
        if (x > 100 || x < 0 || y > 100 || y < 0) {
            System.out.println("Bad coordinates in the file. Terminating program");
            exit(1);
        }
    }

    private TreeNode head; //root of the tree

    public void TwoDTree() {
        head = new TreeNode();
    } // construct an empty tree

    public boolean isEmpty() {
        return head == null;
    }

    public int size() {
        return size(head);
    }

    public int size(TreeNode node) {
        //Preorder Traversal
        //recursively count the nodes in the right subtree

        // result = left + right
        if (node == null) return 0;
        //recursively count the nodes in the left subtree
        return (size(node.getL()) + size(node.getR()) + 1);
    }

    // wrapper method for insert method, using only a point as a parameter
    public void insert(Point p) {
        insert(p, head);
    }

    // insert method implementation
    public void insert(Point p, TreeNode node) {

        // check if the point already exists in the tree
        if (!search(p)) {
            TreeNode node1 = new TreeNode(p);

            if (isEmpty()) {
                head = node1;
                return;
            }
            TreeNode currentNode = node;

            // getting values to compare from both nodes( can be x or y) based on the depth of the tree
            // position denotes the depth
            int curValToCompare = chooseXorYForCurrent(currentNode);
            int nodeValToCompare = chooseXorYForNode(node1, currentNode.getPosition());

            // recursively traverse both subtrees and check in which position the new node should be inserted
            if (nodeValToCompare < curValToCompare) {
                if (currentNode.getL() == null) {
                    node1.setPosition(currentNode.getPosition() + 1);
                    currentNode.setL(node1);

                } else {
                    currentNode = currentNode.getL();
                    insert(p, currentNode);
                }
            } else {
                if (currentNode.getR() == null) {
                    node1.setPosition(currentNode.getPosition() + 1);
                    currentNode.setR(node1);
                } else {
                    currentNode = currentNode.getR();
                    insert(p, currentNode);
                }
            }
        }
    }

    // methods to get x or y value of a node, depending on the depth at which the node exists
    int chooseXorYForCurrent(TreeNode node) {
        if (evenOdd(node.getPosition())) {
            return node.getPoint().x();
        } else return node.getPoint().y();

    }// checking for current node ( node that already exists in the tree)

    int chooseXorYForNode(TreeNode node, int position) {
        if (evenOdd(position)) {
            return node.getPoint().x();
        } else return node.getPoint().y();

    }// checking for node used for comparison ( the one that isnt in the tree)

    boolean evenOdd(int depth) {
        // checking whether the position/ depth is odd/ even

        return depth % 2 == 0;
    }

    // wrapper method for search method, using only a point as a parameter
    public boolean search(Point p) {
        return search(p, head);
    }

    // implementation of search algorithm
    public boolean search(Point p, TreeNode node) {
        TreeNode node1 = new TreeNode(p);

        if (isEmpty()) {
            return false;
        }
        int curValToCompare = chooseXorYForCurrent(node);
        int nodeValToCompare = chooseXorYForNode(node1, node.getPosition());

        // recursively traverses 2 subtrees to find whether the point exists in the tree
        try {
            if (comparePoints(node.getPoint(), p)) {
                return true;
            }
            if (nodeValToCompare < curValToCompare) {
                node = node.getL();

            } else {
                node = node.getR();
            }
            node1.incrementPosition();
            return search(p, node);
        } catch (NullPointerException e) {
            return false;
        }

    }



    // wrapper method for nearest method, using only a point as a parameter
    public Point nearestNeighbor(Point p) {
        return nearest(head, p, null, initialRectangle);
    }

    // implementation of nearestNeighbor search
    private Point nearest(TreeNode node, Point p, Point best, Rectangle rect) {
        // starting at initial rectangle
        Rectangle bestRectangle;
        Rectangle worstRectangle;
        TreeNode bestSide;
        TreeNode worstSide;

        if (node == null) return best;
        if (best == null || p.squareDistanceTo(node.getPoint()) <= p.squareDistanceTo(best)) best = node.getPoint();

        double distanceRectangle = rect.squareDistanceTo(p);

        // determining on which side the query point is
        Tuple2<Rectangle, Rectangle> tuple = splitBasedOnDepth(rect, node);

        if (tuple.getFirst().contains(p)) {
            bestRectangle = tuple.getFirst();
            bestSide = node.getL();
            worstRectangle = tuple.getSecond();
            worstSide = node.getR();

        } else {
            bestRectangle = tuple.getSecond();
            bestSide = node.getR();
            worstRectangle = tuple.getFirst();
            worstSide = node.getL();
        }

        if (bestRectangle.squareDistanceTo(p) <= distanceRectangle) {
            // traverse first the best side and afterwards the worst side
            best = nearest(bestSide, p, best, bestRectangle);
            best = nearest(worstSide, p, best, worstRectangle);
        }

        // if the condition above doesnt apply, traverse the opposite subtree
        best = nearest(worstSide, p, best, worstRectangle);

        return best;
    }


    // method for splitting the rectangle according to x or y coordinate
    public Tuple2<Rectangle, Rectangle> splitBasedOnDepth(Rectangle rect, TreeNode n) {
        // determines how the rectangle should be splitted based on the depth of the node to which it corresponds
        if (evenOdd(n.getPosition())) {
            return Rectangle.splitX(rect, n.getPoint());
        } else {
            return Rectangle.splitY(rect, n.getPoint());
        }
    }

    // points comparator
    public boolean comparePoints(Point a, Point b) {
        return a.x() == b.x() && a.y() == b.y();
    }

    // method for printing the tree
    static void printPreorder(TreeNode node) {
        if (node == null)
            return;
        System.out.print(node.getPoint().toString() + " ");

        printPreorder(node.getL());

        printPreorder(node.getR());
    }

    public ListTreeNode rangeSearch(Rectangle queryRect) {
        ListTreeNode list = new ListTreeNode();

        return rangeSearch(
                head,
                queryRect,
                initialRectangle,
                list
        );
    }

    // Implementation of the range-search algorithm
    private ListTreeNode rangeSearch(
            TreeNode node,
            Rectangle queryRect,
            Rectangle rect,
            ListTreeNode list
    ) {
        // Stop when traversal reaches an empty subtree
        if (node == null) {
            return list;
        }

        // Only search nodes whose regions intersect the query rectangle
        if (queryRect.intersects(rect)) {
            if (queryRect.contains(node.getPoint())) {
                list.insertAtBack(node);
            }

            Tuple2<Rectangle, Rectangle> rectangles =
                    splitBasedOnDepth(rect, node);

            rangeSearch(
                    node.getL(),
                    queryRect,
                    rectangles.getFirst(),
                    list
            );

            rangeSearch(
                    node.getR(),
                    queryRect,
                    rectangles.getSecond(),
                    list
            );
        }

        return list;
    }


    public static void main(String[] args) {
        // this main uses methods from helper class Menu.java
//        String path = "src/main/resources/kdtree/input_file.txt";
        String path = args[0];
        // Check if there are any arguments
        if (args.length > 0) {
            path = args[0];
        }

        TwoDTRee tree = TwoDTRee.newFromFile(path);
        Menu.menu(tree);
    }

}
