package com.Lesson1.Sorters;

import com.Lesson1.ArayUtil;
import com.Lesson1.Contracts.Contracts;
import com.Lesson1.Interfaces.ISorter;

import java.util.Comparator;

public class BubbleSorter implements ISorter {

    /**
     * Do sort in repository's array by Shell's method
     *
     * @param a         Comparator to sort
     * @param contracts Repository which we need to sort
     */
    @Override
    public void sort(Comparator a, Contracts contracts) {
        for (int i = 0; i < ArayUtil.getArrayValuesCount(contracts.getContracts()) - 1; i++) {
            // внутренний цикл прохода
            for (int j = ArayUtil.getArrayValuesCount(contracts.getContracts()) - 1; j > i; j--) {
                if (contracts.getContracts()[j - 1].compareTo(contracts.getContracts()[j]) > 0) {
                    ArayUtil.swap(j - 1, j, contracts.getContracts());
                }
            }
        }
    }
}
