package com.example.sorting.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Klasa zawierająca metody sortujące algorytmem selection sort
 *
 * @author ML
 * @version 1.0
 */
@Component("selectionsort")
public class SelectionSort implements SortJsonInterface {

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

        for (int i = 0; i < objectList.size() - 1; i++) {
            int index = i;
            for (int j = i + 1; j < objectList.size(); j++) {
                String value1 = objectList.get(j).get(key).getAsString();
                String value2 = objectList.get(index).get(key).getAsString();
                if ((isReverse && value1.compareTo(value2) > 0) || (!isReverse && value1.compareTo(value2) < 0)) {
                    index = j;
                }
            }
            if (index != i) {
                JsonObject temp = objectList.get(index);
                objectList.set(index, objectList.get(i));
                objectList.set(i, temp);
            }
        }

        long elapsedTime = System.nanoTime() - startTime;
        return gson.toJson(new SortResult<>(elapsedTime, objectList));
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
        for (int i = 0; i < l.length - 1; i++) {
            int index = i;
            for (int j = i + 1; j < l.length; j++) {
                if ((isReverse && l[j] > l[index]) || (!isReverse && l[j] < l[index])) {
                    index = j;
                }
            }
            int temp = l[index];
            l[index] = l[i];
            l[i] = temp;
        }

        long elapsedTime = System.nanoTime() - startTime;
        return gson.toJson(new SortResult<>(elapsedTime, l));
    }

    public String sortL(String[] l, boolean isReverse) {
        if (l == null || l.length == 0) {
            return gson.toJson(new SortResult<>(0L, l));
        }

        long startTime = System.nanoTime();
        for (int i = 0; i < l.length - 1; i++) {
            int index = i;
            for (int j = i + 1; j < l.length; j++) {
                if ((isReverse && l[j].compareTo(l[index]) > 0) || (!isReverse && l[j].compareTo(l[index]) < 0)) {
                    index = j;
                }
            }
            String temp = l[index];
            l[index] = l[i];
            l[i] = temp;
        }

        long elapsedTime = System.nanoTime() - startTime;
        return gson.toJson(new SortResult<>(elapsedTime, l));
    }
}
