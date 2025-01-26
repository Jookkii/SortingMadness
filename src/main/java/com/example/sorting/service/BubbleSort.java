package com.example.sorting.service;

import com.example.sorting.controller.ApiController;
import com.google.gson.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import java.util.Random;

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
        private T generatedArray;

        public SortResult(T generatedArray, long executionTime, T sortedArray) {
            this.generatedArray = generatedArray;
            this.executionTime = executionTime;
            this.sortedArray = sortedArray;
        }

        public long getExecutionTime() {
            return executionTime;
        }

        public T getSortedArray() {
            return sortedArray;
        }
        public T getGeneratedArray() { return generatedArray; }
    }

    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    private static class SortRequest {
        JsonElement list;
        int n;
        boolean isReverse;
        long time;
        String key;
    }

    /**
     * Metoda "rozpakowująca" strukturę JSON i używająca jednej z dwóch metod w sortL w zależności od tego czy dana lista ma dane typu String czy int
     *
     * @param jsonInput JSON zawierający listę do posortowania, liczbę iteracji oraz informację o tym, w którą stronę idzie sortowanie.
     * @return Posortowana lista i czas wykonania sortowania.
     */
    public String sort(JsonObject jsonInput) {
        try {
            if (jsonInput == null || jsonInput.size() == 0) {
                throw new IllegalArgumentException("Input JSON cannot be null or empty.");
            }

            BubbleSort.SortRequest request = gson.fromJson(jsonInput, BubbleSort.SortRequest.class);

            if (request.list == null) {
                return sortL(new int[0], request.n, request.isReverse, request.time);
            } else {
            JsonArray jsonArray = request.list.getAsJsonArray();
            int n = request.n;
            boolean isReverse = request.isReverse;
            long time = request.time;

            logger.info("Input JSON validated successfully.");

            JsonElement firstElement = jsonArray.get(0);
            if (firstElement.isJsonPrimitive()) {
                if (firstElement.getAsJsonPrimitive().isNumber()) {
                    int[] inputArray = gson.fromJson(jsonArray, int[].class);
                    return sortL(inputArray, n, isReverse, time);
                } else if (firstElement.getAsJsonPrimitive().isString()) {
                    String[] inputArray = gson.fromJson(jsonArray, String[].class);
                    return sortL(inputArray, n, isReverse, time);
                } else {
                    throw new IllegalArgumentException("Unsupported data type in the list. Only numbers or strings are supported.");
                }
            } else {
                throw new IllegalArgumentException("List elements must be primitive types (numbers or strings).");
            }}
        } catch (JsonSyntaxException e) {
            logger.error("JSON parsing error: {}", e.getMessage());
            return createErrorResponse("Invalid JSON format.");
        } catch (IllegalArgumentException e) {
            logger.error("Validation error: {}", e.getMessage());
            return createErrorResponse(e.getMessage());
        } catch (Exception e) {
            logger.error("Unexpected error: {}", e.getMessage());
            return createErrorResponse("An unexpected error occurred.");
        }
    }

    /**
     * Funkcja tworząca odpowiedź błędu w formacie JSON
     *
     * @param message Wiadomość o błędzie
     * @return JSON zawierający informacje o błędzie
     */
    private String createErrorResponse(String message) {
        JsonObject errorResponse = new JsonObject();
        errorResponse.addProperty("error", message);
        return gson.toJson(errorResponse);
    }

    /**
     * Metoda sortująca listy integerów; w przypadku braku listy na wejściu jest generowana ona losowo o długości n bądź 10 jeżeli n też jest puste
     *
     * @param l Nieposortowana lista integerów
     * @param n Liczba iteracji, które program ma wykonać przed zwróceniem listy
     * @param isReverse Znacznik określający, czy lista ma być sortowana malejąco
     * @param time Czas, po którym algorytm ma zakończyć pracę i zwrócić aktualny stan listy; jeżeli równy zero to całość wykonuje się normalnie
     * @return Posortowana lista i czas sortowania
     */
    public static String sortL(int[] l, int n, boolean isReverse, long time) {
        boolean isDataGenerated = false;
        long elapsedTime;

        if (l.length == 0) {
            Random random = new Random();
            n = (n <= 0) ? 10 : n;
            l = new int[n];
            for (int i = 0; i < n; i++) {
                l[i] = random.nextInt(100);
            }
            isDataGenerated = true;
        }

        if (n <= 0 || n > l.length) {
            n = l.length;
        }

        int[] result = l.clone();
        SortResult<int[]> sortResult;
        boolean swap;
        long startTime = System.nanoTime();

        if (time != 0) {
            int i = 0;
            while (i < n && System.nanoTime() - startTime < time) {
                swap = false;
                for (int j = 0; j < l.length - n - 1; j++) {
                    if (isReverse ? result[j] < result[j + 1] : result[j] > result[j + 1]) {
                        int temp = result[j];
                        result[j] = result[j + 1];
                        result[j + 1] = temp;
                        swap = true;
                    }
                }
                if (!swap) break;
                i++;
            }
            elapsedTime = System.nanoTime() - startTime;
        } else {
            for (int i = 0; i < n; i++) {
                swap = false;
                for (int j = 0; j < l.length - i - 1; j++) {
                    if (isReverse ? result[j] < result[j + 1] : result[j] > result[j + 1]) {
                        int temp = result[j];
                        result[j] = result[j + 1];
                        result[j + 1] = temp;
                        swap = true;
                    }
                }
                if (!swap) break;
            }
            elapsedTime = System.nanoTime() - startTime;

        }

        if (isDataGenerated) {
            sortResult = new SortResult<>(l, elapsedTime, result);
        }
        else {
            sortResult = new SortResult<>(null, elapsedTime, result);
        }
        return gson.toJson(sortResult);
    }


    /**
     * Metoda sortująca listy Stringów
     *
     * @param l Nieposortowana lista Stringów
     * @param n Liczba iteracji, które program ma wykonać przed zwróceniem listy
     * @param isReverse Znacznik określający, czy lista ma być sortowana malejąco
     * @param time Czas, po którym algorytm ma zakończyć pracę i zwrócić aktualny stan listy; jeżeli równy zero to całość wykonuje się normalnie
     * @return Posortowana lista i czas sortowania jako
     */
    public static String sortL(String[] l, int n, boolean isReverse, long time) {
        n = (n <= 0 || n > l.length) ? l.length : n;
        String[] result = l.clone();
        SortResult<String[]> sortResult;
        boolean swap;
        long startTime = System.nanoTime();

        if (time != 0) {
            int i = 0;
            while (i < n - 1 && System.nanoTime() - startTime < time) {
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
                i++;
            }
            long elapsedTime = System.nanoTime() - startTime;
            sortResult = new SortResult<>(null, elapsedTime, result);
        } else {
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
            sortResult = new SortResult<>(null, elapsedTime, result);
        }
        return gson.toJson(sortResult);
    }
}