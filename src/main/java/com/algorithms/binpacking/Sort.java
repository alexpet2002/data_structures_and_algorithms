package com.algorithms.binpacking;

import java.util.Arrays;

public class Sort {
    // method that will recursively split an array
    public static int[] mergeSort(int[] elements) {
        if (elements == null || elements.length < 2)
            return elements;
        // splitting the array based on median
        int m = elements.length / 2;
        int[] left = Arrays.copyOfRange(elements, 0, m);
        int[] right = Arrays.copyOfRange(elements, m, elements.length);
        // 2 recursive calls
        left = mergeSort(left);
        right = mergeSort(right);
        // merging parts
        return merge(left, right);
    }
    // method that will merge an array in descending order
    public static int[] merge(int[] left, int[] right) {
        // merging 2 parts of an array
        int[] ret = new int[left.length + right.length];

        for (int i = 0, j = 0, z = 0; z != ret.length; z++) {
            if (i == left.length)
                ret[z] = right[j++];
            else if (j == right.length)
                ret[z] = left[i++];
            else {
                if (left[i] > right[j])
                    ret[z] = left[i++];
                else
                    ret[z] = right[j++];
            }
        }

        return ret;
    }
}
