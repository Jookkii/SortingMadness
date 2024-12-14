package com.example.sorting.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.stereotype.Component;


@Component("mergesort")
public class MergeSort implements SortJsonInterface {


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

    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    private static class SortRequest<T> {
        JsonElement list;
        int n;
        boolean isReverse;
        String key;
    }

    public static String sort(String jsonInput) {
        MergeSort.SortRequest request = gson.fromJson(jsonInput, MergeSort.SortRequest.class);
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
        if (n > l.length) n = l.length;
        int[] result = l.clone();
        long startTime = System.nanoTime();

        for (int currentSize = 1; currentSize < n; currentSize *= 2) {
            for (int leftStart = 0; leftStart < n - 1; leftStart += 2 * currentSize) {
                int mid = Math.min(leftStart + currentSize - 1, n - 1);
                int rightEnd = Math.min(leftStart + 2 * currentSize - 1, n - 1);

                if (isReverse) {
                    merge(result, leftStart, mid, rightEnd, false);
                } else {
                    merge(result, leftStart, mid, rightEnd, true);
                }
            }
        }

        long endTime = System.nanoTime();
        long elapsedTime = endTime - startTime;

        SortResult<int[]> sortResult = new SortResult<>(elapsedTime, result);
        return gson.toJson(sortResult);
    }

    public static String sortL(String[] l, int n, boolean isReverse) {
        if (n > l.length) n = l.length;
        String[] result = l.clone();
        long startTime = System.nanoTime();

        for (int currentSize = 1; currentSize < n; currentSize *= 2) {
            for (int leftStart = 0; leftStart < n - 1; leftStart += 2 * currentSize) {
                int mid = Math.min(leftStart + currentSize - 1, n - 1);
                int rightEnd = Math.min(leftStart + 2 * currentSize - 1, n - 1);

                if (isReverse) {
                    merge(result, leftStart, mid, rightEnd, false);
                } else {
                    merge(result, leftStart, mid, rightEnd, true);
                }
            }
        }

        long endTime = System.nanoTime();
        long elapsedTime = endTime - startTime;

        SortResult<String[]> sortResult = new SortResult<>(elapsedTime, result);
        return gson.toJson(sortResult);
    }


    private static void merge(int[] l, int left, int mid, int right, boolean ascending) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] leftArr = new int[n1];
        int[] rightArr = new int[n2];

        System.arraycopy(l, left, leftArr, 0, n1);
        System.arraycopy(l, mid + 1, rightArr, 0, n2);

        int i = 0, j = 0, k = left;

        while (i < n1 && j < n2) {
            if ((ascending && leftArr[i] <= rightArr[j]) || (!ascending && leftArr[i] > rightArr[j])) {
                l[k++] = leftArr[i++];
            } else {
                l[k++] = rightArr[j++];
            }
        }

        while (i < n1) {
            l[k++] = leftArr[i++];
        }

        while (j < n2) {
            l[k++] = rightArr[j++];
        }
    }

    private static void merge(String[] l, int left, int mid, int right, boolean ascending) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        String[] leftArr = new String[n1];
        String[] rightArr = new String[n2];

        System.arraycopy(l, left, leftArr, 0, n1);
        System.arraycopy(l, mid + 1, rightArr, 0, n2);

        int i = 0, j = 0, k = left;

        while (i < n1 && j < n2) {
            if ((ascending && leftArr[i].compareTo(rightArr[j]) <= 0) ||
                    (!ascending && leftArr[i].compareTo(rightArr[j]) > 0)) {
                l[k++] = leftArr[i++];
            } else {
                l[k++] = rightArr[j++];
            }
        }

        while (i < n1) {
            l[k++] = leftArr[i++];
        }

        while (j < n2) {
            l[k++] = rightArr[j++];
        }
    }
}
