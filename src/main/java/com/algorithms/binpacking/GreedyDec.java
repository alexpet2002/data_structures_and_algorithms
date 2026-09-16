package com.algorithms.binpacking;

import com.data_structures.hashmap.Hashmap;
import com.data_structures.queues.MaxPQ;
import com.data_structures.tuples.Tuple2;
import com.loaders.fileloaders.LoadFromFile;

import java.io.File;

public class GreedyDec {
    // algorithm to save folders where the priority is according to their size
    // saves the folder into the disk with the greatest capacity
    public static Hashmap<Integer, Tuple2<Disk, Integer>> greedyDecMain(String pathToFile) throws Exception {
        File filename = new File(pathToFile);
        int[] unsortedFolders = LoadFromFile.readFile(filename);
        int[] sortedFolders = Sort.mergeSort(unsortedFolders);
        System.out.println(" % java Greedy " + filename);
        System.out.println("Sum of all folders:" + Utils.sumOfAllElements(sortedFolders));


        Hashmap<Integer, Tuple2<Disk, Integer>> availableDisks = new Hashmap<>();
        MaxPQ priorityQueue = new MaxPQ(new IntegerComparator());

        while (Utils.areThereMoreFolders(sortedFolders)) {
            Utils.findASuitableDisk(priorityQueue, sortedFolders[0], availableDisks);
            sortedFolders = Utils.updateFolders(sortedFolders);
        }
        Utils.printList(availableDisks);
        return availableDisks;
    }

    public static void main(String[] args) throws Exception {
        greedyDecMain(args[0]);
    }
}
