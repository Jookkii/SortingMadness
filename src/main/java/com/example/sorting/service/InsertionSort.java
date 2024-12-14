package com.example.sorting.service;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.JsonObject;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.stereotype.Component;

@Component("insertionsort")
public class InsertionSort implements SortJsonInterface {


    public JsonObject sort(JsonObject obj){
        return obj;
    };

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

    private static class SortResultPrimitive {
        private long executionTime;
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
        if (n > l.length) n = l.length;
        int[] result = l.clone();
        long startTime = System.nanoTime();

        for (int i = 1; i < n; i++) {
            int key = result[i];
            int j = i - 1;
            while (j >= 0 && result[j] > key) {
                result[j + 1] = result[j];
                j--;
            }
            result[j + 1] = key;
        }

        long endTime = System.nanoTime();
        long executionTime = endTime - startTime;

        SortResultPrimitive sortResult = new SortResultPrimitive(executionTime, result);
        return gson.toJson(sortResult);
    }


    public static String sort(String[] l, int n) {
        if (l == null || l.length == 0) {
            return gson.toJson(new SortResult<>(0L, l));
        }
        if (n > l.length) n = l.length;
        String[] result = l.clone();
        long startTime = System.nanoTime();

        for (int i = 1; i < n; i++) {
            String key = result[i];
            int j = i - 1;
            while (j >= 0 && result[j].compareTo(key) > 0) {
                result[j + 1] = result[j];
                j--;
            }
            result[j + 1] = key;
        }

        long endTime = System.nanoTime();
        long executionTime = endTime - startTime;

        SortResult<String[]> sortResult = new SortResult<>(executionTime, result);
        return gson.toJson(sortResult);
    }

    public static String sortInReverse(int[] l, int n) {
        if (l == null || l.length == 0) {
            return gson.toJson(new SortResultPrimitive(0L, l));
        }
        if (n > l.length) n = l.length;
        int[] result = l.clone();
        long startTime = System.nanoTime();

        for (int i = 1; i < n; i++) {
            int key = result[i];
            int j = i - 1;
            while (j >= 0 && result[j] < key) {
                result[j + 1] = result[j];
                j--;
            }
            result[j + 1] = key;
        }

        long endTime = System.nanoTime();
        long executionTime = endTime - startTime;

        SortResultPrimitive sortResult = new SortResultPrimitive(executionTime, result);
        return gson.toJson(sortResult);
    }


    public static String sortInReverse(String[] l, int n) {
        if (l == null || l.length == 0) {
            return gson.toJson(new SortResult<>(0L, l));
        }
        if (n > l.length) n = l.length;
        String[] result = l.clone();
        long startTime = System.nanoTime();

        for (int i = 1; i < n; i++) {
            String key = result[i];
            int j = i - 1;
            while (j >= 0 && result[j].compareTo(key) < 0) {
                result[j + 1] = result[j];
                j--;
            }
            result[j + 1] = key;
        }

        long endTime = System.nanoTime();
        long executionTime = endTime - startTime;

        SortResult<String[]> sortResult = new SortResult<>(executionTime, result);
        return gson.toJson(sortResult);
    }
}
