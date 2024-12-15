package com.example.sorting.service;

import com.example.sorting.controller.ApiController;
import com.google.gson.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.stereotype.Component;


/**
 * Klasa zawierająca metody sortujące algorytmem bubble sort
 *
 * @author ML
 * @version 1.0
 */

@Component("bubblesort")
public class BubbleSort implements SortJsonInterface {
    private static final Logger logger = LoggerFactory.getLogger(ApiController.class);

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

    /**
     * Metoda "rozpakowująca" strukturę JSON i używająca jednej z dwóch metod w sortL w zależności od tego czy dana lista ma dane typu String czy int
     *
     * @param jsonInput JSON zawierający listę do posortowania, liczbę iteracji oraz informację o tym, w którą stronę idzie sortowanie.
     * @return Posortowana lista i czas wykonania sortowania.
     */
    public String sort(JsonObject jsonInput) {
        BubbleSort.SortRequest request = gson.fromJson(jsonInput, BubbleSort.SortRequest.class);

        JsonElement listElement = request.list;
        int n = request.n;
        boolean isReverse = request.isReverse;
        String key = request.key;
        logger.info("wypakowano jsona");
        if (listElement == null || !listElement.isJsonArray()) {
            throw new IllegalArgumentException("The 'list' field must be a non-null JSON array.");
        }
        logger.debug("Json był kompletny");
        JsonArray jsonArray = listElement.getAsJsonArray();
        logger.debug("Stworzono json array");
        if (jsonArray.size() == 0) {
            return gson.toJson(new int[0]);
        }
        logger.info("rozpoczęto sortowanie");
        JsonElement firstElement = jsonArray.get(0);

        if (firstElement.isJsonPrimitive()) {
            if (firstElement.getAsJsonPrimitive().isNumber()) {
                int[] inputArray = gson.fromJson(jsonArray, int[].class);
                return sortL(inputArray, n, isReverse);
            }

            if (firstElement.getAsJsonPrimitive().isString()) {
                String[] inputArray = gson.fromJson(jsonArray, String[].class);
                return sortL(inputArray, n, isReverse);
            }
        }

        throw new IllegalArgumentException("Unsupported data type in the list. Only numbers or strings are supported.");
    }

    /**
     * Metoda sortująca listy integerów
     *
     * @param l Nieposortowana lista integerów
     * @param n Liczba iteracji, które program ma wykonać przed zwróceniem listy
     * @param isReverse Znacznik określający, czy lista ma być sortowana malejąco
     * @return Posortowana lista i czas sortowania
     */
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

    /**
     * Metoda sortująca listy Stringów
     *
     * @param l Nieposortowana lista Stringów
     * @param n Liczba iteracji, które program ma wykonać przed zwróceniem listy
     * @param isReverse Znacznik określający, czy lista ma być sortowana malejąco
     * @return Posortowana lista i czas sortowania jako
     */
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