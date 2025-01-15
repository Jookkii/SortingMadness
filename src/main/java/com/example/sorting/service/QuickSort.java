package com.example.sorting.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Klasa zawierająca metody sortujące algorytmem quick sort
 *
 * @author ML
 * @version 1.0
 */
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

        quickSortObjects(objectList, 0, objectList.size() - 1, key, isReverse);

        long elapsedTime = System.nanoTime() - startTime;
        return gson.toJson(new SortResult<>(elapsedTime, objectList));
    }

    private void quickSortObjects(List<JsonObject> list, int low, int high, String key, boolean isReverse) {
        if (low < high) {
            int partitionIndex = partitionObjects(list, low, high, key, isReverse);
            quickSortObjects(list, low, partitionIndex - 1, key, isReverse);
            quickSortObjects(list, partitionIndex + 1, high, key, isReverse);
        }
    }

    private int partitionObjects(List<JsonObject> list, int low, int high, String key, boolean isReverse) {
        JsonObject pivot = list.get(high);
        String pivotValue = pivot.get(key).getAsString();
        int i = low - 1;

        for (int j = low; j < high; j++) {
            String currentValue = list.get(j).get(key).getAsString();
            if ((isReverse && currentValue.compareTo(pivotValue) > 0) || (!isReverse && currentValue.compareTo(pivotValue) <= 0)) {
                i++;
                JsonObject temp = list.get(i);
                list.set(i, list.get(j));
                list.set(j, temp);
            }
        }

        JsonObject temp = list.get(i + 1);
        list.set(i + 1, list.get(high));
        list.set(high, temp);

        return i + 1;
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

        long startTime = System.nanoTime();
        quickSortInt(l, 0, l.length - 1, isReverse);
        long elapsedTime = System.nanoTime() - startTime;

        return gson.toJson(new SortResult<>(elapsedTime, l));
    }

    private void quickSortInt(int[] array, int low, int high, boolean isReverse) {
        if (low < high) {
            int partitionIndex = partitionInt(array, low, high, isReverse);
            quickSortInt(array, low, partitionIndex - 1, isReverse);
            quickSortInt(array, partitionIndex + 1, high, isReverse);
        }
    }

    private int partitionInt(int[] array, int low, int high, boolean isReverse) {
        int pivot = array[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if ((isReverse && array[j] > pivot) || (!isReverse && array[j] <= pivot)) {
                i++;
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }

        int temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;

        return i + 1;
    }

    public String sortL(String[] l, boolean isReverse) {
        if (l == null || l.length == 0) {
            return gson.toJson(new SortResult<>(0L, l));
        }

        long startTime = System.nanoTime();
        quickSortString(l, 0, l.length - 1, isReverse);
        long elapsedTime = System.nanoTime() - startTime;

        return gson.toJson(new SortResult<>(elapsedTime, l));
    }

    private void quickSortString(String[] array, int low, int high, boolean isReverse) {
        if (low < high) {
            int partitionIndex = partitionString(array, low, high, isReverse);
            quickSortString(array, low, partitionIndex - 1, isReverse);
            quickSortString(array, partitionIndex + 1, high, isReverse);
        }
    }

    private int partitionString(String[] array, int low, int high, boolean isReverse) {
        String pivot = array[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if ((isReverse && array[j].compareTo(pivot) > 0) || (!isReverse && array[j].compareTo(pivot) <= 0)) {
                i++;
                String temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }

        String temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;

        return i + 1;
    }
}
