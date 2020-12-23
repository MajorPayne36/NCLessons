package com.Lesson1.ArrayUtil;

import com.Lesson1.Contracts.BasicContract;
import com.Lesson1.Contracts.Contracts;
import com.Lesson1.Interfaces.ISorter;

import java.util.Comparator;

public class ShellSorter implements ISorter {
    /**
     * Do sort using Shell's method
     * @param a Sort comparator extends BasicContract
     * @param contracts What we need to sort
     */
    @Override
    public void sort(Comparator<BasicContract> a, Contracts contracts) {
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
