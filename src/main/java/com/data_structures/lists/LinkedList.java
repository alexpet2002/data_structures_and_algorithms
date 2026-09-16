package com.data_structures.lists;

public interface LinkedList<T> {

    // Inserts one element at the beginning of the list
    void insertAtFront(T value);

    // Inserts one element at the end of the list
    void insertAtBack(T value);

    // Removes and returns the first element
    T removeFromFront() throws Exception;

    // Removes and returns the last element
    T removeFromBack() throws Exception;

    // Returns true when the list contains no elements
    boolean isEmpty();

    // Traverses the list and returns the required integer result
    int traverse();
}
