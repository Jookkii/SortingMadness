package com.example.sorting.service;

import com.example.sorting.controller.ApiController;
import com.google.gson.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Klasa zawierająca metody sortujące algorytmem bubble sort
 *
 * @author ML
 * @version 1.0
 */
@Component("bubblesort")
public class BubbleSort implements SortJsonInterface {
    private static final Logger logger = LoggerFactory.getLogger(ApiController.class);
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

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

    private static class SortRequest {
        JsonElement list;
        int n;
        boolean isReverse;
        String key;
    }

    @Override
    public String sort(JsonObject jsonInput) {
        try {
            if (jsonInput == null || jsonInput.size() == 0) {
                throw new IllegalArgumentException("Input JSON cannot be null or empty.");
            }

            SortRequest request = gson.fromJson(jsonInput, SortRequest.class);

            if (request.list == null || !request.list.isJsonArray()) {
                throw new IllegalArgumentException("The 'list' field must be a non-null JSON array.");
            }

            JsonArray jsonArray = request.list.getAsJsonArray();
            int n = Math.max(1, request.n); // Ensure n is at least 1
            boolean isReverse = request.isReverse;
            String key = request.key;

            logger.info("Input JSON validated successfully.");

            if (jsonArray.isEmpty()) {
                logger.warn("Empty list provided, returning an empty result.");
                return gson.toJson(new int[0]);
            }

            JsonElement firstElement = jsonArray.get(0);

            if (firstElement.isJsonObject()) {
                List<JsonObject> objectList = gson.fromJson(jsonArray, List.class);
                return sortObjects(objectList, key, n, isReverse);
            } else if (firstElement.isJsonPrimitive()) {
                if (firstElement.getAsJsonPrimitive().isNumber()) {
                    int[] inputArray = gson.fromJson(jsonArray, int[].class);
                    return sortL(inputArray, n, isReverse);
                } else if (firstElement.getAsJsonPrimitive().isString()) {
                    String[] inputArray = gson.fromJson(jsonArray, String[].class);
                    return sortL(inputArray, n, isReverse);
                }
            }

            throw new IllegalArgumentException("Unsupported data type in the list.");
        } catch (Exception e) {
            logger.error("Error during sorting: {}", e.getMessage());
            return createErrorResponse(e.getMessage());
        }
    }

    private String sortObjects(List<JsonObject> objectList, String key, int n, boolean isReverse) {
        if (key == null || key.isEmpty()) {
            throw new IllegalArgumentException("Key cannot be null or empty for object sorting.");
        }

        long startTime = System.nanoTime();

        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < objectList.size() - 1 - i; j++) {
                JsonObject obj1 = objectList.get(j);
                JsonObject obj2 = objectList.get(j + 1);

                String value1 = obj1.get(key).getAsString();
                String value2 = obj2.get(key).getAsString();

                if (isReverse ? value1.compareTo(value2) < 0 : value1.compareTo(value2) > 0) {
                    objectList.set(j, obj2);
                    objectList.set(j + 1, obj1);
                    swapped = true;
                }
            }
            if (!swapped) break;
        }

        long elapsedTime = System.nanoTime() - startTime;

        return gson.toJson(new SortResult<>(elapsedTime, objectList));
    }

    private String createErrorResponse(String message) {
        JsonObject errorResponse = new JsonObject();
        errorResponse.addProperty("error", message);
        return gson.toJson(errorResponse);
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
            if (!swap) break;
        }

        long elapsedTime = System.nanoTime() - startTime;
        SortResult<int[]> sortResult = new SortResult<>(elapsedTime, result);
        return gson.toJson(sortResult);
    }

    public static String sortL(String[] l, int n, boolean isReverse) {
        boolean swap;
        if (n > l.length) n = l.length;
        String[] result = l.clone();
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
            if (!swap) break;
        }

        long elapsedTime = System.nanoTime() - startTime;
        SortResult<String[]> sortResult = new SortResult<>(elapsedTime, result);
        return gson.toJson(sortResult);
    }
}
