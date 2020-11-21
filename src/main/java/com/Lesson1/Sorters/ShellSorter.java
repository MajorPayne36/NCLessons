package com.Lesson1.Sorters;

import com.Lesson1.ArayUtil;
import com.Lesson1.Contracts.Contracts;
import com.Lesson1.Interfaces.ISorter;

import java.util.Comparator;

public class ShellSorter implements ISorter {

    /**
     * Do sort in repository's array by Shell's method
     *
     * @param a         Comparator to sort
     * @param contracts Repository which we need to sort
     */
    @Override
    public void sort(Comparator a, Contracts contracts) {
        int h = 1;
        int arrLength = ArayUtil.getArrayValuesCount(contracts.getContracts());
        while (h * 3 < arrLength)
            h = h * 3 + 1;

        while (h >= 1) {
            for (int i = h; i < arrLength; i++) {
                for (int j = i; j >= h; j = j - h) {
                    if (a.compare(contracts.getContracts()[j], contracts.getContracts()[j - h]) < 0)
                        ArayUtil.swap(j, j - h, contracts.getContracts());
                    else
                        break;
                }
            }
            h = h / 3;
        }
    }
}
