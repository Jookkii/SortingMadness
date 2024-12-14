package com.example.sorting.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.Stack;

import com.google.gson.JsonObject;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.stereotype.Component;

@Component("quicksort")
public class QuickSort implements SortJsonInterface {

    private static class SortResult<T> {
        private long executionTime;
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

    public JsonObject sort(JsonObject obj){
        return obj;
    };

    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static String sort(int[] l, int n) {
        if (l == null || l.length == 0) return gson.toJson(new SortResult<>(0L, l));
        if (n > l.length) n = l.length;
        int[] result = l.clone();
        Stack<int[]> stack = new Stack<>();
        stack.push(new int[]{0, result.length - 1});

        long startTime = System.nanoTime();

        while (!stack.isEmpty() && n-- > 0) {
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
        long elapsedTime = endTime - startTime;

        SortResult<int[]> sortResult = new SortResult<>(elapsedTime, result);
        return gson.toJson(sortResult);
    }

    public static String sort(String[] l, int n) {
        if (l == null || l.length == 0) return gson.toJson(new SortResult<>(0L, l));
        if (n > l.length) n = l.length;
        String[] result = l.clone();
        Stack<int[]> stack = new Stack<>();
        stack.push(new int[]{0, result.length - 1});

        long startTime = System.nanoTime();

        while (!stack.isEmpty() && n-- > 0) {
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
        long elapsedTime = endTime - startTime;

        SortResult<String[]> sortResult = new SortResult<>(elapsedTime, result);
        return gson.toJson(sortResult);
    }

    public static String sortInReverse(int[] l, int n) {
        if (l == null || l.length == 0) return gson.toJson(new SortResult<>(0L, l));
        if (n > l.length) n = l.length;
        int[] result = l.clone();
        Stack<int[]> stack = new Stack<>();
        stack.push(new int[]{0, result.length - 1});

        long startTime = System.nanoTime();

        while (!stack.isEmpty() && n-- > 0) {
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
        long elapsedTime = endTime - startTime;

        SortResult<int[]> sortResult = new SortResult<>(elapsedTime, result);
        return gson.toJson(sortResult);
    }

    public static String sortInReverse(String[] l, int n) {
        if (l == null || l.length == 0) return gson.toJson(new SortResult<>(0L, l));
        if (n > l.length) n = l.length;
        String[] result = l.clone();
        Stack<int[]> stack = new Stack<>();
        stack.push(new int[]{0, result.length - 1});

        long startTime = System.nanoTime();

        while (!stack.isEmpty() && n-- > 0) {
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
        long elapsedTime = endTime - startTime;

        SortResult<String[]> sortResult = new SortResult<>(elapsedTime, result);
        return gson.toJson(sortResult);
    }

    private static int partition(int[] arr, int low, int high, boolean ascending) {
        int pivot = arr[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if ((ascending && arr[j] < pivot) || (!ascending && arr[j] >= pivot)) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }

    private static int partition(String[] arr, int low, int high, boolean ascending) {
        String pivot = arr[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if ((ascending && arr[j].compareTo(pivot) < 0) || (!ascending && arr[j].compareTo(pivot) >= 0)) {
                i++;
                String temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        String temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }
}
