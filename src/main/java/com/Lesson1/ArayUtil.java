package com.Lesson1;

import com.Lesson1.Contracts.BasicContract;
import com.Lesson1.Contracts.Contracts;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
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
     * Swap the elements in array
     *
     * @param i
     * @param j
     */
    public static void swap(int i, int j, BasicContract[] contracts) {
        BasicContract tmp = contracts[i];
        contracts[i] = contracts[j];
        contracts[j] = tmp;
    }

}


 class TestReflection {
    public static void main(String[] args) {
        try {
            Class<?> c = Class.forName("java.util.ArrayList");
            Method[] methods = c.getMethods();
            Constructor[] ctors = c.getConstructors();
            for(Method method : methods)
                System.out.println(method.toString());
            for(Constructor ctor : ctors)
                System.out.println(ctor.toString());
        }catch(ClassNotFoundException e)
        {System.out.println("No such class: " + e);}
    }
}


