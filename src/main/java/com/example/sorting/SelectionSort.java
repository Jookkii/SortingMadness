package com.example.sorting;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class SelectionSort {

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

    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static String selectionSort(int[] l) {
        if (l == null || l.length == 0) {
            SortResult<int[]> sortResult = new SortResult<>(0L, l);
            return gson.toJson(sortResult);
        }

        int n = l.length;
        int[] result = l.clone();

        long startTime = System.nanoTime();

        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (result[j] < result[minIndex]) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                int temp = result[minIndex];
                result[minIndex] = result[i];
                result[i] = temp;
            }
        }

        long endTime = System.nanoTime();
        long elapsedTime = endTime - startTime;

        SortResult<int[]> sortResult = new SortResult<>(elapsedTime, result);
        return gson.toJson(sortResult);
    }

    public static String selectionSort(String[] l) {
        if (l == null || l.length == 0) {
            SortResult<String[]> sortResult = new SortResult<>(0L, l);
            return gson.toJson(sortResult);
        }

        int n = l.length;
        String[] result = l.clone();

        long startTime = System.nanoTime();

        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (result[j].compareTo(result[minIndex]) < 0) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                String temp = result[minIndex];
                result[minIndex] = result[i];
                result[i] = temp;
            }
        }

        long endTime = System.nanoTime();
        long elapsedTime = endTime - startTime;

        SortResult<String[]> sortResult = new SortResult<>(elapsedTime, result);
        return gson.toJson(sortResult);
    }

    public static String selectionSortReverse(int[] l) {
        if (l == null || l.length == 0) {
            SortResult<int[]> sortResult = new SortResult<>(0L, l);
            return gson.toJson(sortResult);
        }

        int n = l.length;
        int[] result = l.clone();

        long startTime = System.nanoTime();

        for (int i = 0; i < n - 1; i++) {
            int maxIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (result[j] > result[maxIndex]) {
                    maxIndex = j;
                }
            }
            if (maxIndex != i) {
                int temp = result[maxIndex];
                result[maxIndex] = result[i];
                result[i] = temp;
            }
        }

        long endTime = System.nanoTime();
        long elapsedTime = endTime - startTime;

        SortResult<int[]> sortResult = new SortResult<>(elapsedTime, result);
        return gson.toJson(sortResult);
    }

    public static String selectionSortReverse(String[] l) {
        if (l == null || l.length == 0) {
            SortResult<String[]> sortResult = new SortResult<>(0L, l);
            return gson.toJson(sortResult);
        }

        int n = l.length;
        String[] result = l.clone();

        long startTime = System.nanoTime();

        for (int i = 0; i < n - 1; i++) {
            int maxIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (result[j].compareTo(result[maxIndex]) > 0) {
                    maxIndex = j;
                }
            }
            if (maxIndex != i) {
                String temp = result[maxIndex];
                result[maxIndex] = result[i];
                result[i] = temp;
            }
        }

        long endTime = System.nanoTime();
        long elapsedTime = endTime - startTime; // Czas w nanosekundach

        SortResult<String[]> sortResult = new SortResult<>(elapsedTime, result);
        return gson.toJson(sortResult);
    }

}
