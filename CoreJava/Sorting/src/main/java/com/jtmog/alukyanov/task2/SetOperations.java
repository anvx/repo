package com.jtmog.alukyanov.task2;

import java.util.HashSet;
import java.util.Set;

public class SetOperations extends HashSet {
    public static <T> Set<T> union(Set<T> set1, Set<T> set2) {
        Set<T> result = new HashSet<>(set1);
        result.addAll(set2);
        return result;
    }

    public static <T> Set<T> intersection(Set<T> set1, Set<T> set2) {
        Set<T> result = new HashSet<>(set1);
        result.retainAll(set2);
        return result;
    }

    public static <T> Set<T> minus(Set<T> set1, Set<T> set2) {
        Set<T> result = new HashSet<>(set1);
        result.removeAll(set2);
        return result;
    }

    public static <T> Set<T> complement(Set<T> set1, Set<T> set2) {
        return minus(union(set1, set2), intersection(set1, set2));
    }
}
