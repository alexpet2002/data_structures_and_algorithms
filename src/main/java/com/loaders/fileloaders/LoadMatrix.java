package com.loaders.fileloaders;

import com.data_structures.tuples.Tuple3;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class LoadMatrix {
    // class for reading the txt file
    public static Tuple3<String[][], String[], String[]> readMatrix(File file) {
        // reading the dimensions of array
        int m = 0;
        int n = 0;
        int corX;
        int corY;

        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            System.out.println("File " + file + " was not Found");
            System.exit(1);
        }

        Scanner scanFile = new Scanner(fis);
        String line = scanFile.nextLine();
        // storing the dimensions
        String[] dimensions = line.split(" ");

        try {
            n = Integer.parseInt(dimensions[0]);
            m = Integer.parseInt(dimensions[1]);
        } catch (ArrayIndexOutOfBoundsException ignore) {
            System.out.println("There are no dimensions or written in a wrong format");
            System.exit(1);
        }
        String line2 = scanFile.nextLine();
        // storing the coordinates
        String[] coordinates = line2.split(" ");

        try {
            Integer.parseInt(coordinates[0]);
            Integer.parseInt(coordinates[1]);
        } catch (ArrayIndexOutOfBoundsException ignore) {
            System.out.println("There are no coordinates of E or written in a wrong format");
            System.exit(1);
        }// 2d array
        String[][] a = new String[n][m];
        // storing each element in the array above
        do {
            try {
                for (int i = 0; i < n; i++) {
                    line = scanFile.nextLine();
                    String[] elementsInTheLine = line.split(" ");
                    for (int j = 0; j < m; j++) {
                        a[i][j] = elementsInTheLine[j];
                    }
                }
            } catch (Exception e) {
                System.out.println("Incorrect Dimensions! ");
                System.exit(1);
            }
        } while (scanFile.hasNextLine());

        scanFile.close();
        return new Tuple3<String[][], String[], String[]>(a, dimensions, coordinates);
    }
    // displaying the array of strings
    public static void displayArray(String[][] array) {
        for (String[] ints : array) {
            System.out.println();
            for (int j = 0; j < ints.length; j++) {
                System.out.print(ints[j]);
                System.out.print(" ");
            }
        }
        System.out.println();
    }

}

