package com.example.sorting.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.stereotype.Component;


@Component("mergeSort")
public class MergeSort {

    // Pomocnicza klasa do strukturyzacji wyniku sortowania
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

    // Metoda sortująca int[] i zwracająca JSON
    public static String mergeSort(int[] l) {
        int n = l.length;
        int[] result = l.clone();
        long startTime = System.nanoTime();

        for (int currentSize = 1; currentSize < n; currentSize *= 2) {
            for (int leftStart = 0; leftStart < n - 1; leftStart += 2 * currentSize) {
                int mid = Math.min(leftStart + currentSize - 1, n - 1);
                int rightEnd = Math.min(leftStart + 2 * currentSize - 1, n - 1);

                merge(result, leftStart, mid, rightEnd, true);
            }
        }

        long endTime = System.nanoTime();
        long elapsedTime = endTime - startTime; // Czas w nanosekundach

        SortResult<int[]> sortResult = new SortResult<>(elapsedTime, result);
        return gson.toJson(sortResult);
    }

    // Metoda sortująca String[] i zwracająca JSON
    public static String mergeSort(String[] l) {
        int n = l.length;
        String[] result = l.clone();
        long startTime = System.nanoTime();

        for (int currentSize = 1; currentSize < n; currentSize *= 2) {
            for (int leftStart = 0; leftStart < n - 1; leftStart += 2 * currentSize) {
                int mid = Math.min(leftStart + currentSize - 1, n - 1);
                int rightEnd = Math.min(leftStart + 2 * currentSize - 1, n - 1);

                merge(result, leftStart, mid, rightEnd, true);
            }
        }

        long endTime = System.nanoTime();
        long elapsedTime = endTime - startTime; // Czas w nanosekundach

        SortResult<String[]> sortResult = new SortResult<>(elapsedTime, result);
        return gson.toJson(sortResult);
    }

    // Metoda sortująca int[] w odwrotnej kolejności i zwracająca JSON
    public static String mergeSortReverse(int[] l) {
        int n = l.length;
        int[] result = l.clone();
        long startTime = System.nanoTime();

        for (int currentSize = 1; currentSize < n; currentSize *= 2) {
            for (int leftStart = 0; leftStart < n - 1; leftStart += 2 * currentSize) {
                int mid = Math.min(leftStart + currentSize - 1, n - 1);
                int rightEnd = Math.min(leftStart + 2 * currentSize - 1, n - 1);

                merge(result, leftStart, mid, rightEnd, false);
            }
        }

        long endTime = System.nanoTime();
        long elapsedTime = endTime - startTime; // Czas w nanosekundach

        SortResult<int[]> sortResult = new SortResult<>(elapsedTime, result);
        return gson.toJson(sortResult);
    }

    // Metoda sortująca String[] w odwrotnej kolejności i zwracająca JSON
    public static String mergeSortReverse(String[] l) {
        int n = l.length;
        String[] result = l.clone();
        long startTime = System.nanoTime();

        for (int currentSize = 1; currentSize < n; currentSize *= 2) {
            for (int leftStart = 0; leftStart < n - 1; leftStart += 2 * currentSize) {
                int mid = Math.min(leftStart + currentSize - 1, n - 1);
                int rightEnd = Math.min(leftStart + 2 * currentSize - 1, n - 1);

                merge(result, leftStart, mid, rightEnd, false);
            }
        }

        long endTime = System.nanoTime();
        long elapsedTime = endTime - startTime; // Czas w nanosekundach

        SortResult<String[]> sortResult = new SortResult<>(elapsedTime, result);
        return gson.toJson(sortResult);
    }

    // Metoda pomocnicza do scalania int[]
    private static void merge(int[] l, int left, int mid, int right, boolean ascending) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] leftArr = new int[n1];
        int[] rightArr = new int[n2];

        System.arraycopy(l, left, leftArr, 0, n1);
        System.arraycopy(l, mid + 1, rightArr, 0, n2);

        int i = 0, j = 0, k = left;

        while (i < n1 && j < n2) {
            if ((ascending && leftArr[i] <= rightArr[j]) || (!ascending && leftArr[i] > rightArr[j])) {
                l[k++] = leftArr[i++];
            } else {
                l[k++] = rightArr[j++];
            }
        }

        while (i < n1) {
            l[k++] = leftArr[i++];
        }

        while (j < n2) {
            l[k++] = rightArr[j++];
        }
    }

    // Metoda pomocnicza do scalania String[]
    private static void merge(String[] l, int left, int mid, int right, boolean ascending) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        String[] leftArr = new String[n1];
        String[] rightArr = new String[n2];

        System.arraycopy(l, left, leftArr, 0, n1);
        System.arraycopy(l, mid + 1, rightArr, 0, n2);

        int i = 0, j = 0, k = left;

        while (i < n1 && j < n2) {
            if ((ascending && leftArr[i].compareTo(rightArr[j]) <= 0) ||
                    (!ascending && leftArr[i].compareTo(rightArr[j]) > 0)) {
                l[k++] = leftArr[i++];
            } else {
                l[k++] = rightArr[j++];
            }
        }

        while (i < n1) {
            l[k++] = leftArr[i++];
        }

        while (j < n2) {
            l[k++] = rightArr[j++];
        }
    }
}
