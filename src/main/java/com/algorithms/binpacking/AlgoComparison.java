package com.algorithms.binpacking;

import com.data_structures.hashmap.Hashmap;
import com.data_structures.tuples.Tuple2;

public class AlgoComparison {
    public static int average(int a) {
        return a / 10;
    }

    public static void main(String[] args) throws Exception {
        // arrays to store sum of disks used for each file
        int[] arrayForGreedy = new int[30];
        int[] arrayForGreedyDec = new int[30];

        files();
        //for loop to process all files with 2 algorithms
        for (int i = 0; i < 30; i++) {
            String filename2 = RandomText.generateFileName(i);

            System.out.println("Greedy version: ");
            Hashmap<Integer, Tuple2<Disk, Integer>> availableDiskOfGreedy = Greedy.greedyMain(filename2);
            int diskCount1 = Utils.countDisksInList(availableDiskOfGreedy);
            arrayForGreedy[i] = diskCount1;
            System.out.println();

            System.out.println("Greedy Decreasing version: ");
            Hashmap<Integer, Tuple2<Disk, Integer>> availableDiskOfGreedyDec = GreedyDec.greedyDecMain(filename2);
            int diskCount2 = Utils.countDisksInList(availableDiskOfGreedyDec);
            arrayForGreedyDec[i] = diskCount2;
            System.out.println();

        }
        // calculating average for each value of N
        int sum100Greedy = 0, sum500Greedy = 0, sum1000Greedy = 0;
        int sum100GreedyDec = 0, sum500GreedyDec = 0, sum1000GreedyDec = 0;

        for (int i = 0; i < 30; i++) {
            if (i < 10) {
                sum100Greedy += arrayForGreedy[i];
                sum100GreedyDec += arrayForGreedyDec[i];

            }
            if (i >= 10 && i < 20) {
                sum500Greedy += arrayForGreedy[i];
                sum500GreedyDec += arrayForGreedyDec[i];

            }
            if (i >= 20) {
                sum1000Greedy += arrayForGreedy[i];
                sum1000GreedyDec += arrayForGreedyDec[i];
            }

        }

        // Greedy average values
        System.out.println("Greedy results:");
        int avg100 = average(sum100Greedy);
        System.out.println("The average num of Disks for N = 100 using Greedy is : " + avg100 + "\n");
        int avg500 = average(sum500Greedy);
        System.out.println("The average num of Disks for N = 500 using Greedy is : " + avg500 + "\n");
        int avg1000 = average(sum1000Greedy);
        System.out.println("The average num of Disks for N = 1000 using Greedy is : " + avg1000 + "\n");

        // GreedyDec average values
        System.out.println("Greedy Decreasing results:");
        int avg100_2 = average(sum100GreedyDec);
        System.out.println("The average num of Disks for N = 100 using Greedy Decreasing is : " + avg100_2 + "\n");
        int avg500_2 = average(sum500GreedyDec);
        System.out.println("The average num of Disks for N = 500 using Greedy Decreasing is : " + avg500_2 + "\n");
        int avg1000_2 = average(sum1000GreedyDec);
        System.out.println("The average num of Disks for N = 1000 using Greedy Decreasing is : " + avg1000_2 + "\n");

        // sums of all disks for all N
        int sumGreedy = Utils.sumOfAllElements(arrayForGreedy);
        int sumGreedyDec = Utils.sumOfAllElements(arrayForGreedyDec);

        // printing the results
        System.out.println();
        System.out.println("Total amount of disks required using Greedy algorithm is: " + sumGreedy);
        System.out.println("Total amount of disks required using Greedy Decreasing algorithm is: " + sumGreedyDec);

        if (sumGreedy < sumGreedyDec) {
            System.out.println(" Greedy algorithm is more optimal in this case");
        } else System.out.println(" Greedy Decreasing algorithm is more optimal in this case");
    }

    // method to generate 30 files
    private static void files() {
        // for loop to generate 30 files with random numbers
        for (int i = 0; i < 30; i++) {
            String filename = RandomText.generateFileName(i);
            if (i < 10) {
                RandomText.generateFile(filename, 100);
            }
            if (i >= 10 && i < 20) {
                RandomText.generateFile(filename, 500);
            }
            if (i >= 20) {
                RandomText.generateFile(filename, 1000);
            }
        }
    }
}
