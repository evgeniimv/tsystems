package com.tsystems.javaschool.tasks.subsequence;

import java.util.List;

public class Subsequence {

    /**
     * Checks if it is possible to get a sequence which is equal to the first
     * one by removing some elements from the second one.
     *
     * @param x first sequence
     * @param y second sequence
     * @return <code>true</code> if possible, otherwise <code>false</code>
     */
    @SuppressWarnings("rawtypes")
    public boolean find(List x, List y) {
        if (!checkListsInputData(x, y)) {
            return false;
        }
        int remainderOfX = x.size();
        int yOrder = 0;
        for (int i = 0; i < x.size(); i++) {
            for (int j = yOrder; j < y.size(); j++) {
                if (x.get(i).equals(y.get(j))) {
                    yOrder = y.indexOf(x.get(i));
                    remainderOfX--;
                    break;
                } else {
                    continue;
                }
            }
        }
        if (remainderOfX == 0) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean checkListsInputData(List x, List y) {
        try {
            x.size();
            y.size();
        } catch (NullPointerException e) {
            throw new IllegalArgumentException();
        }
        if (x.size() > y.size()) {
            return false;
        } else {
            return true;
        }
    }
}
