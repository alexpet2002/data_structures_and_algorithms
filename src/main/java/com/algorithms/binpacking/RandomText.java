package com.algorithms.binpacking;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

public class RandomText {
    // class that generates random text
    public static int NUMBER = 100;
    public static String NAME = "test1.txt";
    private int num;

    public RandomText(int num) {
        this.num = NUMBER;
    }

    public static void main(String[] args) {
        generateFile(NAME, 100);
    }

    public static String generateFileName(int a){
        return "test" + a +".txt";
    }
    public static void generateFile(String name, int number) {
        Random ran = new Random();
        int n;
        try (PrintWriter file = new PrintWriter(
                new BufferedWriter(
                        new FileWriter(name)));
        ) {

            for (int i = 0; i < number; i++) {
                n = ran.nextInt(1000000);
                file.println(n);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("File " + name + " has been created!");
    }

    public static int getNUMBER() {
        return NUMBER;
    }

    public static void setNUMBER(int NUMBER) {
        RandomText.NUMBER = NUMBER;
    }
}

