package com.loaders.fileloaders;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class LoadFromFile {
    public static int[] readFile(File fileName) {
        int[] incomingFolders = new int[1];
        int count = 0;
        Scanner sc = null;
        try {
            sc = new Scanner(fileName);
        } catch (FileNotFoundException e) {
            System.out.println("Error. No file detected.");
        }
        do {
            assert sc != null;
            try {
                int a = sc.nextInt();
                if (a <= 1000000)
                    try {
                        incomingFolders[count] = a;
                        if (sc.hasNextLine()) {
                            incomingFolders = changingLengthOfTheArray(incomingFolders.length + 1, incomingFolders.length, incomingFolders);
                            count++;
                        }
                    } catch (NoSuchElementException e) {
                        System.out.println(" integer not found");
                    }
                else {
                    System.out.println("Error. The value given exceeds 1TB. ");
                    System.exit(0);
                }
            } catch (NoSuchElementException ignored) {
                System.out.println("Got a blank space or a wrong format. Considering that it's the end of file");
                incomingFolders = changingLengthOfTheArray(incomingFolders.length - 1, incomingFolders.length - 1, incomingFolders);
                break;
            }
        } while (sc.hasNextLine());

        sc.close();
        return incomingFolders;
    }

    private static int[] changingLengthOfTheArray(int tempFileLength, int loopLength, int[] incomingFolders) {
        int[] temp = new int[tempFileLength];
        for (int j = 0; j < loopLength; j++) {
            temp[j] = incomingFolders[j];
        }
        incomingFolders = temp;
        return incomingFolders;
    }

}

