package com.example.sorting.service;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
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

    private static class SortRequest<T> {
        JsonElement list;
        int n;
        boolean isReverse;
        String key;
    }

    public static String sort(String jsonInput) {
        InsertionSort.SortRequest request = gson.fromJson(jsonInput, InsertionSort.SortRequest.class);
        JsonElement listElement = request.list;
        int n = request.n;
        boolean isReverse = request.isReverse;
        String key = request.key;

        if (listElement.isJsonArray()) {
            JsonArray jsonArray = listElement.getAsJsonArray();

            if (jsonArray.size() > 0 && jsonArray.get(0).isJsonPrimitive() && jsonArray.get(0).getAsJsonPrimitive().isNumber()) {
                int[] inputArray = gson.fromJson(jsonArray, int[].class);
                return sortL(inputArray, n, isReverse);
            }
            else if (jsonArray.size() > 0 && jsonArray.get(0).isJsonPrimitive() && jsonArray.get(0).getAsJsonPrimitive().isString()) {
                String[] inputArray = gson.fromJson(jsonArray, String[].class);
                return sortL(inputArray, n, isReverse);
            }
        }
        throw new IllegalArgumentException("Unsupported data type in the list");
    }

    public static String sortL(int[] l, int n, boolean isReverse) {
        if (l == null || l.length == 0) {
            return gson.toJson(new SortResultPrimitive(0L, l));
        }
        if (n > l.length) n = l.length;
        int[] result = l.clone();
        long startTime = System.nanoTime();

        for (int i = 1; i < n; i++) {
            int key = result[i];
            int j = i - 1;
            while (j >= 0 && (isReverse ? result[j] < key : result[j] > key)) {
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


    public static String sortL(String[] l, int n, boolean isReverse) {
        if (l == null || l.length == 0) {
            return gson.toJson(new SortResult<>(0L, l));
        }
        if (n > l.length) n = l.length;
        String[] result = l.clone();
        long startTime = System.nanoTime();

        for (int i = 1; i < n; i++) {
            String key = result[i];
            int j = i - 1;
            while (j >= 0 && (isReverse ? result[j].compareTo(key) < 0 : result[j].compareTo(key) > 0)) {
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
