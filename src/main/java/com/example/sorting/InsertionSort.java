package com.example.sorting;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class InsertionSort {

    // Pomocnicza klasa do strukturyzacji wyniku sortowania dla obiektów
    private static class SortResult<T> {
        private long executionTime; // w nanosekundach
        private T sortedArray;

        public SortResult(long executionTime, T sortedArray) {
            this.executionTime = executionTime;
            this.sortedArray = sortedArray;
        }

        public long getExecutionTime() {
            return executionTime;
        }

        public T getSortedArray() {
            return sortedArray;
        }
    }

    // Pomocnicza klasa do strukturyzacji wyniku sortowania dla prymitywnych typów
    private static class SortResultPrimitive {
        private long executionTime; // w nanosekundach
        private int[] sortedArray;

        public SortResultPrimitive(long executionTime, int[] sortedArray) {
            this.executionTime = executionTime;
            this.sortedArray = sortedArray;
        }

        public long getExecutionTime() {
            return executionTime;
        }

        public int[] getSortedArray() {
            return sortedArray;
        }
    }

    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static String sort(int[] l, int n) {
        if (l == null || l.length == 0) {
            return gson.toJson(new SortResultPrimitive(0L, l));
        }

        int[] result = l.clone();
        long startTime = System.nanoTime();

        for (int i = 1; i < n; i++) { // Rozpoczynamy od 1, ponieważ element 0 jest już posortowany
            int key = result[i];
            int j = i - 1;
            while (j >= 0 && result[j] > key) {
                result[j + 1] = result[j];
                j--;
            }
            result[j + 1] = key;
        }

        long endTime = System.nanoTime();
        long executionTime = endTime - startTime; // Czas w nanosekundach

        SortResultPrimitive sortResult = new SortResultPrimitive(executionTime, result);
        return gson.toJson(sortResult);
    }


    public static String sort(String[] l, int n) {
        if (l == null || l.length == 0) {
            return gson.toJson(new SortResult<>(0L, l));
        }

        String[] result = l.clone();
        long startTime = System.nanoTime();

        for (int i = 1; i < n; i++) { // Rozpoczynamy od 1, ponieważ element 0 jest już posortowany
            String key = result[i];
            int j = i - 1;
            while (j >= 0 && result[j].compareTo(key) > 0) {
                result[j + 1] = result[j];
                j--;
            }
            result[j + 1] = key;
        }

        long endTime = System.nanoTime();
        long executionTime = endTime - startTime; // Czas w nanosekundach

        SortResult<String[]> sortResult = new SortResult<>(executionTime, result);
        return gson.toJson(sortResult);
    }

    public static String sortInReverse(int[] l, int n) {
        if (l == null || l.length == 0) {
            return gson.toJson(new SortResultPrimitive(0L, l));
        }

        int[] result = l.clone();
        long startTime = System.nanoTime();

        for (int i = 1; i < n; i++) { // Rozpoczynamy od 1, ponieważ element 0 jest już posortowany
            int key = result[i];
            int j = i - 1;
            while (j >= 0 && result[j] < key) {
                result[j + 1] = result[j];
                j--;
            }
            result[j + 1] = key;
        }

        long endTime = System.nanoTime();
        long executionTime = endTime - startTime; // Czas w nanosekundach

        SortResultPrimitive sortResult = new SortResultPrimitive(executionTime, result);
        return gson.toJson(sortResult);
    }


    public static String sortInReverse(String[] l, int n) {
        if (l == null || l.length == 0) {
            return gson.toJson(new SortResult<>(0L, l));
        }

        String[] result = l.clone();
        long startTime = System.nanoTime();

        for (int i = 1; i < n; i++) { // Rozpoczynamy od 1, ponieważ element 0 jest już posortowany
            String key = result[i];
            int j = i - 1;
            while (j >= 0 && result[j].compareTo(key) < 0) {
                result[j + 1] = result[j];
                j--;
            }
            result[j + 1] = key;
        }

        long endTime = System.nanoTime();
        long executionTime = endTime - startTime; // Czas w nanosekundach

        SortResult<String[]> sortResult = new SortResult<>(executionTime, result);
        return gson.toJson(sortResult);
    }
}
