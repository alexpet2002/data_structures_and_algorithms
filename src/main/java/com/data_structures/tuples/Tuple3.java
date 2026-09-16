package com.data_structures.tuples;

public class Tuple3<K, V, M> {

    private K first;
    private V second;
    private M third;

    public Tuple3(K first, V second, M third) {
        this.first = first;
        this.second = second;
        this.third = third;
    }

    public K getFirst() {
        return first;
    }

    public void setFirst(K first) {
        this.first = first;
    }

    public V getSecond() {
        return second;
    }

    public void setSecond(V second) {
        this.second = second;
    }

    public M getThird() {
        return third;
    }

    public void setThird(M third) {
        this.third = third;
    }

}