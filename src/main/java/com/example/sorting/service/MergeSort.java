package com.example.sorting.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Klasa zawierająca metody sortujące algorytmem merge sort
 *
 * @author ML
 * @version 1.0
 */
@Component("mergesort")
public class MergeSort implements SortJsonInterface {

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

    private static class SortRequest {
        JsonElement list;
        boolean isReverse;
        String key;
    }

    @Override
    public String sort(JsonObject jsonInput) {
        try {
            SortRequest request = gson.fromJson(jsonInput, SortRequest.class);

            if (request.list == null || !request.list.isJsonArray()) {
                throw new IllegalArgumentException("The 'list' field must be a non-null JSON array.");
            }

            JsonArray jsonArray = request.list.getAsJsonArray();
            String key = request.key;
            boolean isReverse = request.isReverse;

            if (jsonArray.size() == 0) {
                return gson.toJson(new SortResult<>(0L, new ArrayList<>()));
            }

            JsonElement firstElement = jsonArray.get(0);

            if (firstElement.isJsonObject() && key != null && !key.isEmpty()) {
                List<JsonObject> objectList = new ArrayList<>();
                jsonArray.forEach(el -> objectList.add(el.getAsJsonObject()));
                return sortObjects(objectList, key, isReverse);
            } else if (firstElement.isJsonPrimitive()) {
                if (firstElement.getAsJsonPrimitive().isNumber()) {
                    int[] inputArray = gson.fromJson(jsonArray, int[].class);
                    return sortL(inputArray, isReverse);
                } else if (firstElement.getAsJsonPrimitive().isString()) {
                    String[] inputArray = gson.fromJson(jsonArray, String[].class);
                    return sortL(inputArray, isReverse);
                }
            }

            throw new IllegalArgumentException("Unsupported data type or missing key for object sorting.");
        } catch (Exception e) {
            return createErrorResponse(e.getMessage());
        }
    }

    private String sortObjects(List<JsonObject> objectList, String key, boolean isReverse) {
        long startTime = System.nanoTime();

        List<JsonObject> sortedList = mergeSortObjects(objectList, key, isReverse);

        long elapsedTime = System.nanoTime() - startTime;
        return gson.toJson(new SortResult<>(elapsedTime, sortedList));
    }

    private List<JsonObject> mergeSortObjects(List<JsonObject> list, String key, boolean isReverse) {
        if (list.size() <= 1) {
            return list;
        }

        int mid = list.size() / 2;
        List<JsonObject> left = mergeSortObjects(list.subList(0, mid), key, isReverse);
        List<JsonObject> right = mergeSortObjects(list.subList(mid, list.size()), key, isReverse);

        return mergeObjects(left, right, key, isReverse);
    }

    private List<JsonObject> mergeObjects(List<JsonObject> left, List<JsonObject> right, String key, boolean isReverse) {
        List<JsonObject> merged = new ArrayList<>();
        int i = 0, j = 0;

        while (i < left.size() && j < right.size()) {
            String leftValue = left.get(i).get(key).getAsString();
            String rightValue = right.get(j).get(key).getAsString();

            if ((isReverse && leftValue.compareTo(rightValue) > 0) || (!isReverse && leftValue.compareTo(rightValue) <= 0)) {
                merged.add(left.get(i++));
            } else {
                merged.add(right.get(j++));
            }
        }

        while (i < left.size()) {
            merged.add(left.get(i++));
        }

        while (j < right.size()) {
            merged.add(right.get(j++));
        }

        return merged;
    }

    private String createErrorResponse(String message) {
        JsonObject errorResponse = new JsonObject();
        errorResponse.addProperty("error", message);
        return gson.toJson(errorResponse);
    }

    public String sortL(int[] l, boolean isReverse) {
        if (l == null || l.length == 0) {
            return gson.toJson(new SortResult<>(0L, l));
        }

        int[] result = mergeSortInt(l, isReverse);

        long elapsedTime = System.nanoTime();
        return gson.toJson(new SortResult<>(elapsedTime, result));
    }

    private int[] mergeSortInt(int[] array, boolean isReverse) {
        if (array.length <= 1) {
            return array;
        }

        int mid = array.length / 2;
        int[] left = mergeSortInt(Arrays.copyOfRange(array, 0, mid), isReverse);
        int[] right = mergeSortInt(Arrays.copyOfRange(array, mid, array.length), isReverse);

        return mergeInt(left, right, isReverse);
    }

    private int[] mergeInt(int[] left, int[] right, boolean isReverse) {
        int[] merged = new int[left.length + right.length];
        int i = 0, j = 0, k = 0;

        while (i < left.length && j < right.length) {
            if ((isReverse && left[i] > right[j]) || (!isReverse && left[i] <= right[j])) {
                merged[k++] = left[i++];
            } else {
                merged[k++] = right[j++];
            }
        }

        while (i < left.length) {
            merged[k++] = left[i++];
        }

        while (j < right.length) {
            merged[k++] = right[j++];
        }

        return merged;
    }

    public String sortL(String[] l, boolean isReverse) {
        if (l == null || l.length == 0) {
            return gson.toJson(new SortResult<>(0L, l));
        }

        String[] result = mergeSortString(l, isReverse);

        long elapsedTime = System.nanoTime();
        return gson.toJson(new SortResult<>(elapsedTime, result));
    }

    private String[] mergeSortString(String[] array, boolean isReverse) {
        if (array.length <= 1) {
            return array;
        }

        int mid = array.length / 2;
        String[] left = mergeSortString(Arrays.copyOfRange(array, 0, mid), isReverse);
        String[] right = mergeSortString(Arrays.copyOfRange(array, mid, array.length), isReverse);

        return mergeString(left, right, isReverse);
    }

    private String[] mergeString(String[] left, String[] right, boolean isReverse) {
        String[] merged = new String[left.length + right.length];
        int i = 0, j = 0, k = 0;

        while (i < left.length && j < right.length) {
            if ((isReverse && left[i].compareTo(right[j]) > 0) || (!isReverse && left[i].compareTo(right[j]) <= 0)) {
                merged[k++] = left[i++];
            } else {
                merged[k++] = right[j++];
            }
        }

        while (i < left.length) {
            merged[k++] = left[i++];
        }

        while (j < right.length) {
            merged[k++] = right[j++];
        }

        return merged;
    }
}
