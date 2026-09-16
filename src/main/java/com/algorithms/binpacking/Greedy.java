package com.algorithms.binpacking;

import com.data_structures.hashmap.Hashmap;
import com.data_structures.queues.MaxPQ;
import com.data_structures.tuples.Tuple2;
import com.loaders.fileloaders.LoadFromFile;

import java.io.File;

public class Greedy {
    // algorithm to save folders where the priority is according to their order
    // saves the folder into the disk with the greatest capacity
    public static Hashmap<Integer, Tuple2<Disk, Integer>> greedyMain(String pathToFile) throws Exception {

        File filename = new File(pathToFile);
        int[] folders = LoadFromFile.readFile(filename);
        System.out.println(" % java Greedy " + filename);
        System.out.println("Sum of all folders:" + Utils.convertToTB(Utils.sumOfAllElements(folders)) + "TB");

        Hashmap<Integer, Tuple2<Disk, Integer>> availableDisks = new Hashmap<>();
        MaxPQ priorityQueue = new MaxPQ(new IntegerComparator());

        while (Utils.areThereMoreFolders(folders)) {
            Utils.findASuitableDisk(priorityQueue, folders[0], availableDisks);
            folders = Utils.updateFolders(folders);
        }
        Utils.printList(availableDisks);
        return availableDisks;
    }

    public static void main(String[] args) throws Exception {
        greedyMain(args[0]);
    }
}

