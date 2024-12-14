package com.example.sorting.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.stereotype.Component;

@Component("bubblesort")
public class BubbleSort implements SortJsonInterface {

    @Override
    public JsonObject sort(JsonObject input){
        return input;
    };

    private static class SortResult<T> {
        private long executionTime; // w milisekundach
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

    public static String sort(int[] l, int n) {
        boolean swap;
        if (n > l.length) n = l.length;
        int[] result = l.clone();
        long startTime = System.nanoTime();

        for (int i = 0; i < n - 1; i++) {
            swap = false;
            for (int j = 0; j < n - 1 - i; j++) {
                if (result[j] > result[j + 1]) {
                    int temp = result[j];
                    result[j] = result[j + 1];
                    result[j + 1] = temp;
                    swap = true;
                }
            }
            if (!swap) {
                break;
            }
        }

        long endTime = System.nanoTime();
        long elapsedTime = (endTime - startTime);

        SortResult<int[]> sortResult = new SortResult<>(elapsedTime, result);
        return gson.toJson(sortResult);
    }

    public static String sort(String[] l, int n) {
        boolean swap;
        String[] result = l.clone();
        if (n > l.length) n = l.length;
        long startTime = System.nanoTime();

        for (int i = 0; i < n - 1; i++) {
            swap = false;
            for (int j = 0; j < n - 1 - i; j++) {
                if (result[j].compareTo(result[j + 1]) > 0) {
                    String temp = result[j];
                    result[j] = result[j + 1];
                    result[j + 1] = temp;
                    swap = true;
                }
            }
            if (!swap) {
                break;
            }
        }

        long endTime = System.nanoTime();
        long elapsedTime = (endTime - startTime);

        SortResult<String[]> sortResult = new SortResult<>(elapsedTime, result);
        return gson.toJson(sortResult);
    }

    public static String sortInReverse(int[] l, int n) {
        boolean swap;
        int[] result = l.clone();
        if (n > l.length) n = l.length;
        long startTime = System.nanoTime();

        for (int i = 0; i < n - 1; i++) {
            swap = false;
            for (int j = 0; j < n - 1 - i; j++) {
                if (result[j] < result[j + 1]) {
                    int temp = result[j];
                    result[j] = result[j + 1];
                    result[j + 1] = temp;
                    swap = true;
                }
            }
            if (!swap) {
                break;
            }
        }

        long endTime = System.nanoTime();
        long elapsedTime = (endTime - startTime);

        SortResult<int[]> sortResult = new SortResult<>(elapsedTime, result);
        return gson.toJson(sortResult);
    }

    public static String sortInReverse(String[] l, int n) {
        boolean swap;
        String[] result = l.clone();
        if (n > l.length) n = l.length;
        long startTime = System.nanoTime();

        for (int i = 0; i < n - 1; i++) {
            swap = false;
            for (int j = 0; j < n - 1 - i; j++) {
                if (result[j + 1].compareTo(result[j]) > 0) {
                    String temp = result[j];
                    result[j] = result[j + 1];
                    result[j + 1] = temp;
                    swap = true;
                }
            }
            if (!swap) {
                break;
            }
        }

        long endTime = System.nanoTime();
        long elapsedTime = (endTime - startTime);

        SortResult<String[]> sortResult = new SortResult<>(elapsedTime, result);
        return gson.toJson(sortResult);
    }

}