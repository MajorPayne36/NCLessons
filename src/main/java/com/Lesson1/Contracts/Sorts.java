package com.Lesson1.Contracts;

import java.util.Comparator;

/**
 * This method sort the array by contract worked period
 */
class SortByPeriod implements Comparator<BasicContract> {
    @Override
    public int compare(BasicContract o1, BasicContract o2) {
        return o1.getStartDateTime().compareTo(o2.getStartDateTime());
    }
}

/**
 * This method sort the array by contract start date
 */
class SortByStartDate implements Comparator<BasicContract> {
    @Override
    public int compare(BasicContract o1, BasicContract o2) {
        return o1.getStartDateTime().compareTo(o2.getStartDateTime());
    }
}

/**
 * This method sort the array by contract type
 */
class SortByContractType implements Comparator<BasicContract> {
    @Override
    public int compare(BasicContract o1, BasicContract o2) {
        return o1.getClass().getSimpleName().compareTo(o2.getClass().getSimpleName());
    }
}