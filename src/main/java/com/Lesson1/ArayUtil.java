package com.Lesson1;

import com.Lesson1.Contracts.BasicContract;
import com.Lesson1.Contracts.Contracts;

import java.util.Arrays;
import java.util.Comparator;

public class ArayUtil {

    /**
     * Return array values count. <b>NOT LENGTH</b>
     *
     * @param arr
     * @return Return founded count
     */
    public static int getArrayValuesCount(Object[] arr) {
        int count = 0;
        for (Object o : arr) {
            if (o != null) ++count;
            else break;
        }
        return count;
    }

    /**
     * Find the value in the array
     *
     * @param arr   Array where we need to search value
     * @param value The value, which we searched
     * @return Return true, if the value founded, and false otherwise
     */
    public static boolean hasValueInArray(Object[] arr, Object value) {
        for (Object obj : arr) if (obj == value) return true;
        return false;
    }

    /**
     * Check out is the array full
     *
     * @param arr
     * @return Return true, if the array is full and false, if the array empty
     */
    public static boolean arrayIsFull(Object[] arr) {
        return getArrayValuesCount(arr) == arr.length;
    }

    /**
     * Increase size of array
     *
     * @param arr array, wich need to increase
     * @return return increased array
     */
    public static Object[] increaseArray(Object[] arr) {
        return Arrays.copyOf(arr, arr.length * 2, arr.getClass());
    }

    /**
     * Use Comparable to do bubble sort by Contract Number
     */
    public void sortBubble(BasicContract[] contracts) {
        for (int i = 0; i < contracts.length - 1; i++) {
            // внутренний цикл прохода
            for (int j = contracts.length - 1; j > i; j--) {
                if (contracts[j - 1].compareTo(contracts[j]) > 0) {
                    swap(j - 1, j, contracts);
//                    BasicContract tmp = contracts[j - 1];
//                    contracts[j - 1] = contracts[j];
//                    contracts[j] = tmp;
                }
            }
        }
    }

    /**
     * Swap the elements in array
     *
     * @param i
     * @param j
     */
    private void swap(int i, int j, BasicContract[] contracts) {
        BasicContract tmp = contracts[i];
        contracts[i] = contracts[j];
        contracts[j] = tmp;
    }

    /**
     * This method do Shell's sort by entered type of Comparator
     *
     * @param a it's type of sort parameter
     */
    public void sortShell(Comparator<BasicContract> a, BasicContract[] contracts) {
        int h = 1;
        int arrLength = ArayUtil.getArrayValuesCount(contracts);
        while (h * 3 < arrLength)
            h = h * 3 + 1;

        while (h >= 1) {
            for (int i = h; i < arrLength; i++) {
                for (int j = i; j >= h; j = j - h) {
                    if (a.compare(contracts[j], contracts[j - h]) < 0)
                        swap(j, j - h, contracts);
                    else
                        break;
                }
            }
            h = h / 3;
        }
    }
}