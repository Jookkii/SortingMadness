package com.example.sorting.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.Stack;
import org.springframework.stereotype.Component;

@Component("quickSort")
public class QuickSort {

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
    public static String quickSort(int[] l) {
        if (l == null || l.length == 0) return gson.toJson(new SortResult<>(0L, l));

        int[] result = l.clone();
        Stack<int[]> stack = new Stack<>();
        stack.push(new int[]{0, result.length - 1});

        long startTime = System.nanoTime();

        while (!stack.isEmpty()) {
            int[] range = stack.pop();
            int low = range[0];
            int high = range[1];

            if (low < high) {
                int partitionIndex = partition(result, low, high, true);
                stack.push(new int[]{low, partitionIndex - 1});
                stack.push(new int[]{partitionIndex + 1, high});
            }
        }

        long endTime = System.nanoTime();
        long elapsedTime = endTime - startTime; // Czas w nanosekundach

        SortResult<int[]> sortResult = new SortResult<>(elapsedTime, result);
        return gson.toJson(sortResult);
    }

    // Metoda sortująca String[] i zwracająca JSON
    public static String quickSort(String[] l) {
        if (l == null || l.length == 0) return gson.toJson(new SortResult<>(0L, l));

        String[] result = l.clone();
        Stack<int[]> stack = new Stack<>();
        stack.push(new int[]{0, result.length - 1});

        long startTime = System.nanoTime();

        while (!stack.isEmpty()) {
            int[] range = stack.pop();
            int low = range[0];
            int high = range[1];

            if (low < high) {
                int partitionIndex = partition(result, low, high, true);
                stack.push(new int[]{low, partitionIndex - 1});
                stack.push(new int[]{partitionIndex + 1, high});
            }
        }

        long endTime = System.nanoTime();
        long elapsedTime = endTime - startTime; // Czas w nanosekundach

        SortResult<String[]> sortResult = new SortResult<>(elapsedTime, result);
        return gson.toJson(sortResult);
    }

    // Metoda sortująca int[] w odwrotnej kolejności i zwracająca JSON
    public static String quickSortReverse(int[] l) {
        if (l == null || l.length == 0) return gson.toJson(new SortResult<>(0L, l));

        int[] result = l.clone();
        Stack<int[]> stack = new Stack<>();
        stack.push(new int[]{0, result.length - 1});

        long startTime = System.nanoTime();

        while (!stack.isEmpty()) {
            int[] range = stack.pop();
            int low = range[0];
            int high = range[1];

            if (low < high) {
                int partitionIndex = partition(result, low, high, false);
                stack.push(new int[]{low, partitionIndex - 1});
                stack.push(new int[]{partitionIndex + 1, high});
            }
        }

        long endTime = System.nanoTime();
        long elapsedTime = endTime - startTime; // Czas w nanosekundach

        SortResult<int[]> sortResult = new SortResult<>(elapsedTime, result);
        return gson.toJson(sortResult);
    }

    // Metoda sortująca String[] w odwrotnej kolejności i zwracająca JSON
    public static String quickSortReverse(String[] l) {
        if (l == null || l.length == 0) return gson.toJson(new SortResult<>(0L, l));

        String[] result = l.clone();
        Stack<int[]> stack = new Stack<>();
        stack.push(new int[]{0, result.length - 1});

        long startTime = System.nanoTime();

        while (!stack.isEmpty()) {
            int[] range = stack.pop();
            int low = range[0];
            int high = range[1];

            if (low < high) {
                int partitionIndex = partition(result, low, high, false);
                stack.push(new int[]{low, partitionIndex - 1});
                stack.push(new int[]{partitionIndex + 1, high});
            }
        }

        long endTime = System.nanoTime();
        long elapsedTime = endTime - startTime; // Czas w nanosekundach

        SortResult<String[]> sortResult = new SortResult<>(elapsedTime, result);
        return gson.toJson(sortResult);
    }

    // Metoda pomocnicza do partition dla int[]
    private static int partition(int[] arr, int low, int high, boolean ascending) {
        int pivot = arr[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if ((ascending && arr[j] < pivot) || (!ascending && arr[j] >= pivot)) {
                i++;
                // Zamiana arr[i] i arr[j]
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        // Zamiana arr[i + 1] i arr[high] (pivot)
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }

    // Metoda pomocnicza do partition dla String[]
    private static int partition(String[] arr, int low, int high, boolean ascending) {
        String pivot = arr[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if ((ascending && arr[j].compareTo(pivot) < 0) || (!ascending && arr[j].compareTo(pivot) >= 0)) {
                i++;
                // Zamiana arr[i] i arr[j]
                String temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        // Zamiana arr[i + 1] i arr[high] (pivot)
        String temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }
}
