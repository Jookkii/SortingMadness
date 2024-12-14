package com.example.sorting.service;

import com.google.gson.*;
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

    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    private static class SortRequest<T> {
        JsonElement list;
        int n;
        boolean isReverse;
        String key;
    }

    public static String sort(String jsonInput) {
        SortRequest request = gson.fromJson(jsonInput, SortRequest.class);
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
        boolean swap;
        if (n > l.length) n = l.length;
        int[] result = l.clone();
        long startTime = System.nanoTime();

        for (int i = 0; i < n - 1; i++) {
            swap = false;
            for (int j = 0; j < n - 1 - i; j++) {
                if (isReverse ? result[j] < result[j + 1] : result[j] > result[j + 1]) {
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

    public static String sortL(String[] l, int n, boolean isReverse) {
        boolean swap;
        String[] result = l.clone();
        if (n > l.length) n = l.length;
        long startTime = System.nanoTime();

        for (int i = 0; i < n - 1; i++) {
            swap = false;
            for (int j = 0; j < n - 1 - i; j++) {
                if (isReverse ? result[j + 1].compareTo(result[j]) > 0 : result[j].compareTo(result[j + 1]) > 0) {
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