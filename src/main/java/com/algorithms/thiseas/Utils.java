package com.algorithms.thiseas;

import com.data_structures.stack.StringStackImpl;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Objects;

public class Utils {
    // class with all the methods needed for Thiseas.java

    // checking if the current coordinates correspond to an exit
    public static boolean foundExit(String[][] labyrinth, String[] dimensions, String[] currentPosition) {

        int n = Integer.parseInt(dimensions[0]);
        int m = Integer.parseInt(dimensions[1]);
        int coord_i = Integer.parseInt(currentPosition[0]);
        int coord_j = Integer.parseInt(currentPosition[1]);

        if (labyrinth[coord_i][coord_j].equals("E")) return false;

        if (coord_i == 0) return true;
        if (coord_j == 0) return true;
        if (coord_i == n - 1) return true;
        if (coord_j == m - 1) return true;

        return false;
    }

    // checking if the current coordinates correspond to an entrance
    public static boolean foundEntrance(String[][] labyrinth, String[] dimensions, String[] currentPosition) {
        int n = Integer.parseInt(dimensions[0]);
        int m = Integer.parseInt(dimensions[1]);
        int coord_i = Integer.parseInt(currentPosition[0]);
        int coord_j = Integer.parseInt(currentPosition[1]);

        return labyrinth[coord_i][coord_j].equals("E");
    }

    // checking if there is an exit for current coordinates
    public static boolean isThereAnExit(String[][] labyrinth, String[] dimensions, StringStackImpl stack) throws Exception {

        // Current Position
        String[] currentPosition = (String[]) stack.peek();

        System.out.println();
        System.out.println("Current Position: " + currentPosition[0] + ", " + currentPosition[1]);
        System.out.println();

        if (foundExit(labyrinth, dimensions, currentPosition)) {
            System.out.println("Found the Exit");
            return true;
        }

        if (foundEntrance(labyrinth, dimensions, currentPosition)) {
            System.out.println("Found the entrance");
            String[] nextPosition = checkSurroundings(labyrinth, currentPosition, stack);
            if (nextPosition != null) {
                stack.push(nextPosition);

                System.out.println("the stack is: ");
                stack.printStack(System.out);
                return isThereAnExit(labyrinth, dimensions, stack);
            } else {
                System.out.println("There is no exit");
                return false;
            }
        }

        // Impossible case
        if (surroundedByOnes(labyrinth, currentPosition)) {
            System.out.println("Surrounded by ones");
            return false;
        }

        String[] nextPosition = checkSurroundings(labyrinth, currentPosition, stack);
        if (nextPosition != null) {
            stack.push(nextPosition);

            System.out.println("the stack is: ");
            stack.printStack(System.out);
        } else {
            currentPosition = (String[]) stack.pop();
            System.out.println("Going back to previous position: " + stack.peek());
            int currentPosition_i = Integer.parseInt(currentPosition[0]);
            int currentPosition_j = Integer.parseInt(currentPosition[1]);
            labyrinth[currentPosition_i][currentPosition_j] = "1";
            System.out.println("the stack is: ");
            stack.printStack(System.out);
        }

        return isThereAnExit(labyrinth, dimensions, stack);
    }

    // returns the next position as string coordinates
    public static String[] checkSurroundings(String[][] labyrinth, String[] coordinates, StringStackImpl stack) throws Exception {
        int currentPositionI = Integer.parseInt(coordinates[0]);
        int currentPositionJ = Integer.parseInt(coordinates[1]);
        String[] currentCoordinatesInNode = null;

        try {
            String[] futurePosition = Arrays.stream(new int[]{currentPositionI + 1, currentPositionJ}).mapToObj(String::valueOf).toArray(String[]::new);
            if (Objects.equals(labyrinth[currentPositionI + 1][currentPositionJ], "0") && !isPreviousPositionOnStack(futurePosition, stack)) {
                currentCoordinatesInNode = futurePosition;
            }
        } catch (IndexOutOfBoundsException ignore) {
        }
        try {
            String[] futurePosition = Arrays.stream(new int[]{currentPositionI - 1, currentPositionJ}).mapToObj(String::valueOf).toArray(String[]::new);
            if (Objects.equals(labyrinth[currentPositionI - 1][currentPositionJ], "0") && !isPreviousPositionOnStack(futurePosition, stack)) {
                currentCoordinatesInNode = futurePosition;
            }
        } catch (IndexOutOfBoundsException ignore) {
        }
        try {
            String[] futurePosition = Arrays.stream(new int[]{currentPositionI, currentPositionJ + 1}).mapToObj(String::valueOf).toArray(String[]::new);
            if (Objects.equals(labyrinth[currentPositionI][currentPositionJ + 1], "0") && !isPreviousPositionOnStack(futurePosition, stack)) {
                currentCoordinatesInNode = futurePosition;
            }
        } catch (IndexOutOfBoundsException ignore) {
        }
        try {
            String[] futurePosition = Arrays.stream(new int[]{currentPositionI, currentPositionJ - 1}).mapToObj(String::valueOf).toArray(String[]::new);
            if (Objects.equals(labyrinth[currentPositionI][currentPositionJ - 1], "0") && !isPreviousPositionOnStack(futurePosition, stack)) {
                currentCoordinatesInNode = futurePosition;
            }
        } catch (IndexOutOfBoundsException ignore) {
        }

        System.out.println("The next coordinates are: " + Arrays.toString(currentCoordinatesInNode));
        return currentCoordinatesInNode;
    }

    // returns the previous position as string coordinates
    private static String[] getThePreviousPosition(StringStackImpl stack) {
        String[] currentPosition = (String[]) stack.pop();
        try {
            String[] previousPosition = (String[]) stack.peek();
            stack.push(currentPosition);
            return previousPosition;
        } catch (NoSuchElementException e) {
            stack.push(currentPosition);
            throw e;
        }

    }

    // checking if the previous position is already stored in stack
    public static boolean isPreviousPositionOnStack(String[] futurePosition, StringStackImpl stack) {
        if (stack.isEmpty()) {
            return false;
        } else {
            try {
                String[] previousPosition = getThePreviousPosition(stack);
                return (previousPosition[0].equals(futurePosition[0])) && (previousPosition[1].equals(futurePosition[1]));
            } catch (NoSuchElementException ignore) {
            }
            return false;
        }
    }

    // checking if values around current position are 1's
    public static boolean surroundedByOnes(String[][] labyrinth, String[] currentPosition) {
        int i = Integer.parseInt(currentPosition[0]);
        int j = Integer.parseInt(currentPosition[1]);
        try {
            return labyrinth[i + 1][j].equals("1") &&
                    labyrinth[i - 1][j].equals("1") &&
                    labyrinth[i][j + 1].equals("1") &&
                    labyrinth[i][j - 1].equals("1");
        } catch (Exception exception) {
            return false;
        }
    }


}
