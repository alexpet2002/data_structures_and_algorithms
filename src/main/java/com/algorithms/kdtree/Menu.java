package com.algorithms.kdtree;

import com.algorithms.kdtree.TwoDTRee;
import com.data_structures.shapes.Point;
import com.data_structures.shapes.Rectangle;

import java.util.Arrays;
import java.util.Scanner;

import static java.lang.System.exit;
import static java.lang.System.in;

public class Menu {


    public static void menu(TwoDTRee tree) {
        Scanner scanner = new Scanner(in);

        String choice = "";
        String[] possibleValues = {"1", "2", "3", "4", "5"};

        while (true) {

            System.out.println();
            System.out.println("Please select the following choice:");
            System.out.println("1. Compute the size of the tree");
            System.out.println("2. Insert a new point");
            System.out.println("3. Search if a given point exists in the tree");
            System.out.println("4. Provide a query rectangle");
            System.out.println("5. Provide a query point");
            System.out.println();

            while (!Arrays.asList(possibleValues).contains(choice)) {
                choice = scanner.nextLine();
            }

            switch (choice) {
                case "1":
                    System.out.println("Your choice is 1");
                    computeTheSizeOfTheTree(tree);
                    break;
                case "2":
                    System.out.println("Your choice is 2");
                    insertANewPoint(tree);
                    break;
                case "3":
                    System.out.println("Your choice is 3");
                    searchIfAGivenPointExistsInTheTree(tree);
                    break;
                case "4":
                    System.out.println("Your choice is 4");
                    provideAQueryRectangle(tree);
                    break;
                case "5":
                    System.out.println("Your choice is 5");
                    provideAQueryPoint(tree);
                    break;
                default:
                    System.out.println("Wrong input: Exiting the program");
                    exit(1);
                    break;
            }

            choice = "";
        }

    }

    public static void computeTheSizeOfTheTree(TwoDTRee tree) {
        System.out.println("The size of the tree is: " + tree.size());
    }

    public static void insertANewPoint(TwoDTRee tree) {
        Point point = getPointCoordinates();
        System.out.println("Inserting new point (" + point + ") into tree");
        tree.insert(point);
    }

    public static Point getPointCoordinates() {
        Scanner scanner = new Scanner(in);

        System.out.println("Please give x coordinate");
        int x = Integer.parseInt(scanner.nextLine());

        System.out.println("Please give y coordinate");
        int y = Integer.parseInt(scanner.nextLine());

        return new Point(x, y);
    }

    public static void searchIfAGivenPointExistsInTheTree(TwoDTRee tree) {
        Point point = getPointCoordinates();
        System.out.println("Searching for the point (" + point + ") in the tree");
        printIfPointFoundInTheTree(tree, point);
    }

    public static void printIfPointFoundInTheTree(TwoDTRee tree, Point point) {
        if (tree.search(point)) {
            System.out.println("The point is in the tree");
        } else {
            System.out.println("The point is not in the tree");
        }
    }

    public static void provideAQueryRectangle(TwoDTRee tree) {
        Rectangle rectangle = getRectangleCoordinates();
        System.out.println("Searching for the points in the given rectangle (" + rectangle + ")");
        System.out.println("The points inside the given rectangle (" + rectangle + ") are: (" + tree.rangeSearch(rectangle) + ")");
    }

    public static Rectangle getRectangleCoordinates() {
        Scanner scanner = new Scanner(in);

        System.out.println("Please give xmin coordinate");
        int xmin = Integer.parseInt(scanner.nextLine());

        System.out.println("Please give ymin coordinate");
        int ymin = Integer.parseInt(scanner.nextLine());

        System.out.println("Please give xmax coordinate");
        int xmax = Integer.parseInt(scanner.nextLine());

        System.out.println("Please give ymax coordinate");
        int ymax = Integer.parseInt(scanner.nextLine());

        return new Rectangle(xmin, ymin, xmax, ymax);
    }

    public static void provideAQueryPoint(TwoDTRee tree) {
        Point point = getPointCoordinates();
        System.out.println("Searching for the nearest Neighbour for the point (" + point + ")");
        System.out.println("The nearest point to query point (" + point + ") is: (" + tree.nearestNeighbor(point) + ")");
    }


}