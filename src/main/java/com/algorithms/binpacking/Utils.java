package com.algorithms.binpacking;

import com.data_structures.hashmap.Hashmap;
import com.data_structures.lists.IntegerNode;
import com.data_structures.lists.Node2;
import com.data_structures.queues.MaxPQ;
import com.data_structures.tuples.Tuple2;

import java.util.stream.IntStream;

public class Utils {
    public static void storeFolder(Disk disk, int folderSize) {
        // adding a folder to folders
        IntegerNode disknode = new IntegerNode(folderSize);
        disk.getFolders().insertAtBack(disknode);
        disk.decreaseSpace();
    }

    // method returns the peek of the priority queue
    // peek -> contains the greatest capacity
    public static int getGreatestCapacity(MaxPQ priorityQueue) {
        return (Integer) priorityQueue.peek();
    }

    //method modifies the com.data_structures.lists.List by removing the invalid com.data_structures.lists.Node and adding a new one
    public static void updateList(Hashmap<Integer, Tuple2<Disk, Integer>> list, Disk disk) throws Exception {
        Tuple2<Disk, Integer> invalidValue = list.get(disk.getUniqueId());
        list.remove(disk.getUniqueId());
        list.put(disk.getUniqueId(), new Tuple2<>(disk, disk.getFreeSpace()));

    }

    //method modifies the priority queue by removing the invalid com.data_structures.lists.Node and adding a new one
    public static void updateQueue(MaxPQ pq, int folderSize) {
        pq.getMax();
        pq.add(folderSize);
    }

    // removes the element at index 0 in array of incoming folders
    public static int[] updateFolders(int[] folders) {
        return IntStream.range(0, folders.length).filter(i -> i != 0).map(i -> folders[i]).toArray();
    }

    public static void addToList(Disk disk, Hashmap<Integer, Tuple2<Disk, Integer>> list) throws Exception {
        list.put(disk.getUniqueId(), new Tuple2<>(disk, disk.getFreeSpace()));
    }

    public static void removeFromList(Disk disk, Hashmap<Integer, Tuple2<Disk, Integer>> list) {
        list.remove(disk.getUniqueId());
    }

    // method to check if the array of incoming folders has more elements
    public static boolean areThereMoreFolders(int[] folders) {
        return folders.length != 0;
    }

    // method that checks if there are more disks
    public static boolean isThereAFreeDisk(MaxPQ queue) {
        return queue.peek() != null;
    }

    // methods that traverses the priority queue to get a suitable free space for a folder
    public static int getNextFreeSpace(int folderSize, MaxPQ queue) {
        //com.data_structures.queues.MaxPQ copyQueue = new com.data_structures.queues.MaxPQ(queue);
        Object[] copyQueueArray = queue.getHeap().clone();
        MaxPQ copyQueue = new MaxPQ(copyQueueArray, queue.getSize(), new IntegerComparator());
        int firstValue = getGreatestCapacity(queue);
        if (isThereAFreeDisk(copyQueue)) {
            if (folderSize <= firstValue) {
                return firstValue;
            } else {
                copyQueue.getMax();
                return getNextFreeSpace(folderSize, copyQueue);
            }
        }
        return firstValue;
    }

    // method creates and adds a disk to list and priority queue
    public static Disk createAndStoreNewDisk(Hashmap<Integer, Tuple2<Disk, Integer>> list, MaxPQ pq) throws Exception {
        Disk d = new Disk();
        int space = d.getFreeSpace();
        list.put(d.getUniqueId(), new Tuple2<>(d, space));
        pq.add(space);
        return d;
    }

    // method returns a disk that corresponds to a given available space from com.data_structures.lists.List
    public static Disk getDiskFromList(Hashmap<Integer, Tuple2<Disk, Integer>> list, int availableSpace) {
        for (int i = 0; i < list.getNodeList().length; i++) {
            if (list.getNodeList()[i] != null) {
                Node2<Integer, Tuple2<Disk, Integer>> currentNode = list.getNodeList()[i];
                if (currentNode != null) {
                    if (currentNode.getValue().getSecond() == availableSpace) {
                        return currentNode.getValue().getFirst();
                    }
                }
                currentNode = currentNode.getNext();
            }
        }
        return null;
    }

    // method that stores folder
    // if there is an available disk -> folder is stored in it
    // otherwise -> creates a new disk and stores it there
    public static void findASuitableDisk(MaxPQ queue, int folderSize, Hashmap<Integer, Tuple2<Disk, Integer>> list) throws Exception {
        try {
            int nxtFreeSpace = getNextFreeSpace(folderSize, queue);
            Disk diskFromList = getDiskFromList(list, nxtFreeSpace);
            storeFolder(diskFromList, folderSize);

            updateQueue(queue, diskFromList.getFreeSpace());
            updateList(list, diskFromList);

        } catch (NullPointerException e) {
            Disk newDisk = createAndStoreNewDisk(list, queue);
            storeFolder(newDisk, folderSize);

            updateQueue(queue, newDisk.getFreeSpace());
            updateList(list, newDisk);

        }

    }

    // method returns sum of disks from a lists
    public static int countDisksInList(Hashmap<Integer, Tuple2<Disk, Integer>> list) {
        int count = 0;
        for (Node2<Integer, Tuple2<Disk, Integer>> node : list.getNodeList()) {
            if (node != null) count++;
        }
        return count;
    }

    // method returns sum of folders or disks
    public static int sumOfAllElements(int[] a) {
        return IntStream.of(a).sum();
    }

    // method prints the list
    public static void printList(Hashmap<Integer, Tuple2<Disk, Integer>> list) {
        for (Node2<Integer, Tuple2<Disk, Integer>> name : list.getNodeList()) {
            try {
                String key = String.valueOf(name.getKey());
                String valueDisk = name.getValue().getFirst().toString();
                String valueFreeSpace = name.getValue().getSecond().toString();
                System.out.println(("id " + key + " " + valueFreeSpace + " : " + valueDisk));
            } catch (Exception ignore) {
            }

        }

    }

    public static float convertToTB(int megabytes) {
        return (float) (Math.pow(1000, -2) * megabytes);
    }

}




