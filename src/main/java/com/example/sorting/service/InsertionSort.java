package com.example.sorting.service;

import com.google.gson.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.ArrayList;

/**
 * Klasa zawierająca metody sortujące algorytmem insertion sort
 *
 * @author ML
 * @version 1.0
 */
@Component("insertionsort")
public class InsertionSort implements SortJsonInterface {

    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    private static class SortResult<T> {
        private long executionTime;
        private T sortedArray;

        public SortResult(long executionTime, T sortedArray) {
            this.executionTime = executionTime;
            this.sortedArray = sortedArray;
        }
    }

    private static class SortRequest {
        JsonElement list;
        int n;
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

        for (int i = 1; i < objectList.size(); i++) {
            JsonObject keyObject = objectList.get(i);
            int j = i - 1;

            while (j >= 0) {
                String value1 = objectList.get(j).get(key).getAsString();
                String value2 = keyObject.get(key).getAsString();

                if (isReverse ? value1.compareTo(value2) < 0 : value1.compareTo(value2) > 0) {
                    objectList.set(j + 1, objectList.get(j));
                    j--;
                } else {
                    break;
                }
            }
            objectList.set(j + 1, keyObject);
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

        int[] result = l.clone();
        long startTime = System.nanoTime();

        for (int i = 1; i < result.length; i++) {
            int key = result[i];
            int j = i - 1;
            while (j >= 0 && (isReverse ? result[j] < key : result[j] > key)) {
                result[j + 1] = result[j];
                j--;
            }
            result[j + 1] = key;
        }

        long elapsedTime = System.nanoTime() - startTime;
        return gson.toJson(new SortResult<>(elapsedTime, result));
    }

    public String sortL(String[] l, boolean isReverse) {
        if (l == null || l.length == 0) {
            return gson.toJson(new SortResult<>(0L, l));
        }

        String[] result = l.clone();
        long startTime = System.nanoTime();

        for (int i = 1; i < result.length; i++) {
            String key = result[i];
            int j = i - 1;
            while (j >= 0 && (isReverse ? result[j].compareTo(key) < 0 : result[j].compareTo(key) > 0)) {
                result[j + 1] = result[j];
                j--;
            }
            result[j + 1] = key;
        }

        long elapsedTime = System.nanoTime() - startTime;
        return gson.toJson(new SortResult<>(elapsedTime, result));
    }
}
