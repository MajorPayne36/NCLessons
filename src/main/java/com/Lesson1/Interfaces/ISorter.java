package com.Lesson1.Interfaces;

import com.Lesson1.Contracts.BasicContract;
import com.Lesson1.Contracts.Contracts;

import java.util.Comparator;

public interface ISorter {
    public void sort(Comparator<BasicContract> a, Contracts contracts);
}
