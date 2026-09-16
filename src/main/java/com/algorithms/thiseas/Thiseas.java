package com.algorithms.thiseas;

import com.data_structures.tuples.Tuple3;
import com.data_structures.stack.StringStackImpl;
import com.loaders.fileloaders.LoadMatrix;
import java.util.Arrays;
import java.io.File;

public class Thiseas {
    // backtracking algorithm implementation

    public static void main(String[] args) throws Exception {
        //Program to find whether there is an exit in a given labyrinth
        // instantiating a new stack object
        StringStackImpl stack = new StringStackImpl();
        // load the file
        String path = args[0];
        File filename = new File(path);
        Tuple3<String[][], String[], String[]> file_information = LoadMatrix.readMatrix(filename);

        // load elements form file to a 2d array (labyrinth)
        String[][] labyrinth = file_information.getFirst();
        // load dimensions form file to an array
        String[] dimensions = file_information.getSecond();
        // load entrance coordinates form file to an array
        String[] entrance = file_information.getThird();

        // printing the given information
        System.out.println(Arrays.deepToString(labyrinth));
        System.out.println(Arrays.toString(dimensions));
        System.out.println(Arrays.toString(entrance));

        // display labyrinth
        LoadMatrix.displayArray(labyrinth);
        // pushing the entrance coordinates onto the stack
        stack.push(entrance);
        // printing true if there is an exit, false if not
        System.out.println("Is there an exit: " + Utils.isThereAnExit(labyrinth, dimensions, stack));

    }

}
