package com.example.sorting;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ShellSort {

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


    public static String shellSort(int[] l) {
        if (l == null || l.length == 0) {
            return gson.toJson(new SortResult<>(0L, l));
        }

        int n = l.length;
        int[] result = l.clone();

        long startTime = System.nanoTime();

        for (int gap = n / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < n; i++) {
                int temp = result[i];
                int j;
                for (j = i; j >= gap && result[j - gap] > temp; j -= gap) {
                    result[j] = result[j - gap];
                }
                result[j] = temp;
            }
        }

        long endTime = System.nanoTime();
        long elapsedTime = endTime - startTime;

        SortResult<int[]> sortResult = new SortResult<>(elapsedTime, result);
        return gson.toJson(sortResult);
    }


    public static String shellSort(String[] l) {
        if (l == null || l.length == 0) {
            return gson.toJson(new SortResult<>(0L, l));
        }

        int n = l.length;
        String[] result = l.clone();

        long startTime = System.nanoTime();

        for (int gap = n / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < n; i++) {
                String temp = result[i];
                int j;
                for (j = i; j >= gap && result[j - gap].compareTo(temp) > 0; j -= gap) {
                    result[j] = result[j - gap];
                }
                result[j] = temp;
            }
        }

        long endTime = System.nanoTime();
        long elapsedTime = endTime - startTime;

        SortResult<String[]> sortResult = new SortResult<>(elapsedTime, result);
        return gson.toJson(sortResult);
    }


    public static String shellSortReverse(int[] l) {
        if (l == null || l.length == 0) {
            return gson.toJson(new SortResult<>(0L, l));
        }

        int n = l.length;
        int[] result = l.clone();

        long startTime = System.nanoTime();

        for (int gap = n / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < n; i++) {
                int temp = result[i];
                int j;
                for (j = i; j >= gap && result[j - gap] < temp; j -= gap) {
                    result[j] = result[j - gap];
                }
                result[j] = temp;
            }
        }

        long endTime = System.nanoTime();
        long elapsedTime = endTime - startTime;

        SortResult<int[]> sortResult = new SortResult<>(elapsedTime, result);
        return gson.toJson(sortResult);
    }


    public static String shellSortReverse(String[] l) {
        if (l == null || l.length == 0) {
            return gson.toJson(new SortResult<>(0L, l));
        }

        int n = l.length;
        String[] result = l.clone();

        long startTime = System.nanoTime();

        for (int gap = n / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < n; i++) {
                String temp = result[i];
                int j;
                for (j = i; j >= gap && result[j - gap].compareTo(temp) < 0; j -= gap) {
                    result[j] = result[j - gap];
                }
                result[j] = temp;
            }
        }

        long endTime = System.nanoTime();
        long elapsedTime = endTime - startTime;

        SortResult<String[]> sortResult = new SortResult<>(elapsedTime, result);
        return gson.toJson(sortResult);
    }

}
