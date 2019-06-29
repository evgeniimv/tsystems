package com.tsystems.javaschool.tasks.pyramid;

import java.util.Collections;
import java.util.List;

public class PyramidBuilder {

    /**
     * Builds a pyramid with sorted values (with minumum value at the top line and maximum at the bottom,
     * from left to right). All vacant positions in the array are zeros.
     *
     * @param inputNumbers to be used in the pyramid
     * @return 2d array with pyramid inside
     * @throws {@link CannotBuildPyramidException} if the pyramid cannot be build with given input
     */
    public int[][] buildPyramid(List<Integer> inputNumbers) {
        checkPyramidInputData(inputNumbers);
        try {
            Collections.sort(inputNumbers);
        } catch (OutOfMemoryError e) {
            throw new CannotBuildPyramidException();
        }
        int lineNumbers = calcPyramidLines(inputNumbers);
        int columnNumbers = 2 * lineNumbers - 1;
        int[][] pyramidOfNumbers = new int[lineNumbers][columnNumbers];
        int position = 0;
        for (int i = 0; i < lineNumbers; i++) {
            int intend = (columnNumbers + 1) / 2 - (i + 1);
            for (int j = 1; j <= i + 1; j++) {
                pyramidOfNumbers[i][intend] = inputNumbers.get(position);
                intend += 2;
                position++;
            }
        }
        return pyramidOfNumbers;
    }

    public static int calcPyramidLines(List<Integer> inputNumbers) {
        int n = 0;
        int lineNumbers = 0;
        int remainder = inputNumbers.size();
        while (remainder > 0) {
            remainder -= n + 1;
            lineNumbers++;
            n++;
        }
        if (remainder != 0) {
            throw new CannotBuildPyramidException();
        } else {
            return lineNumbers;
        }

    }

    public static void checkPyramidInputData(List<Integer> inputNumbers) {

        for (Integer inputNumber : inputNumbers) {
            if (inputNumber == null) {
                throw new CannotBuildPyramidException();
            } else {
                continue;
            }
        }
    }


}
