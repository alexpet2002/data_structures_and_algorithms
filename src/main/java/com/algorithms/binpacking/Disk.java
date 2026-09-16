package com.algorithms.binpacking;

import com.data_structures.lists.List;

public class Disk implements Comparable<Disk> {
    // each object of type Disk contains a unique ID number
    // contains a singly-linked list to store folders
    // keeps track of free space left
    int uniqueId;
    private final List folders;
    public int freeSpace;
    static int nextDiskNum = 0;
    private final int capacity = 1000000;

    public Disk() {
        this.freeSpace = capacity;
        this.uniqueId = nextDiskNum;
        nextDiskNum++;
        this.folders = new List();
    }

    public int getUniqueId() {
        return uniqueId;
    }

    public List getFolders() {
        return folders;
    }

    public int getFreeSpace() {
        return freeSpace;
    }

    // comparing the free space in 2 disks
    // If A == B -> returns 1
    // If A < B -> returns -1
    // If A > B -> returns 0
    @Override
    public int compareTo(Disk disk) {
        int spaceDiff = this.freeSpace - disk.getFreeSpace();
        if (spaceDiff == 0) {
            return 1;
        }
        if (spaceDiff < 0) {
            return -1;
        } else {
            return 0;
        }
    }

    public void decreaseSpace() {
        int occupiedSpace = this.getFolders().traverse();
        this.freeSpace = capacity - occupiedSpace;

    }

    public String toString() {
        return this.getFolders().toString();
    }

}
