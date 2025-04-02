package com.thefivebros.shared;

import java.util.Arrays;

public class Functions {
    public static boolean contains(String[] array, int item) {
        if(array == null) {
            return false;
        }
        return Arrays.asList(array).contains(String.valueOf(item));
    }
}