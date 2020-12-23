package com.Lesson1.ArrayUtil;

import com.Lesson1.Contracts.BasicContract;
import com.Lesson1.Contracts.Contracts;
import com.Lesson1.Interfaces.ISorter;

import java.util.Comparator;

public class BubbleSorter implements ISorter {
    /**
     * Do sort using Bubble method
     * @param a Sort comparator extends BasicContract
     * @param contracts What we need to sort
     */
    @Override
    public void sort(Comparator<BasicContract> a, Contracts contracts) {
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
