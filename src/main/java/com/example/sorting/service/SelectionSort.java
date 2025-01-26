package com.example.sorting.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import org.springframework.stereotype.Component;

import java.util.Random;

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

    private static class SortRequest<T> {
        JsonElement list;
        int n;
        boolean isReverse;
        String key;
        long time;
    }

    /**
     * Metoda "rozpakowująca" strukturę JSON i używająca jednej z dwóch metod w sortL w zależności od tego czy dana lista ma dane typu String czy int
     *
     * @param jsonInput JSON zawierający listę do posortowania, liczbę iteracji oraz informację o tym, w którą stronę idzie sortowanie.
     * @return Posortowana lista i czas wykonania sortowania.
     */
    public String sort(JsonObject jsonInput) {
        try {
            SelectionSort.SortRequest request = gson.fromJson(jsonInput, SelectionSort.SortRequest.class);

            JsonElement listElement = request.list;
            int n = request.n;
            boolean isReverse = request.isReverse;
            String key = request.key;
            long time = request.time;

            if (request.list == null) {
                return sortL(new int[0], request.n, request.isReverse, request.time);
            } else {
            JsonArray jsonArray = listElement.getAsJsonArray();

            if (jsonArray.size() == 0) {
                return gson.toJson(new int[0]);
            }

            JsonElement firstElement = jsonArray.get(0);

            if (firstElement.isJsonPrimitive()) {
                if (firstElement.getAsJsonPrimitive().isNumber()) {
                    int[] inputArray = gson.fromJson(jsonArray, int[].class);
                    return sortL(inputArray, n, isReverse, time);
                }

                if (firstElement.getAsJsonPrimitive().isString()) {
                    String[] inputArray = gson.fromJson(jsonArray, String[].class);
                    return sortL(inputArray, n, isReverse, time);
                }
            }

            throw new IllegalArgumentException("Unsupported data type in the list. Only numbers or strings are supported.");
        }} catch (Exception e) {
            return createErrorResponse(e.getMessage());
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
     * Metoda sortująca listy integerów
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
        long startTime = System.nanoTime();
        SortResult<int[]> sortResult;

        if (time != 0) {
            int k = 0;
            while (k < n && System.nanoTime() - startTime < time) {
                for (int i = 0; i < n - 1; i++) {
                    int index = i;
                    for (int j = i + 1; j < n; j++) {
                        if (isReverse ? result[j] > result[index] : result[j] < result[index]) {
                            index = j;
                        }
                    }
                    if (index != i) {
                        int temp = result[index];
                        result[index] = result[i];
                        result[i] = temp;
                    }
                }
            }
        } else {
            for (int i = 0; i < n - 1; i++) {
                int index = i;
                for (int j = i + 1; j < n; j++) {
                    if (isReverse ? result[j] > result[index] : result[j] < result[index]) {
                        index = j;
                    }
                }
                if (index != i) {
                    int temp = result[index];
                    result[index] = result[i];
                    result[i] = temp;
                }
            }

        }
        elapsedTime = System.nanoTime() - startTime;


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
        long startTime = System.nanoTime();

        if (time != 0) {
            int k = 0;
            while (k < n && System.nanoTime() - startTime < time) {
                for (int i = 0; i < n - 1; i++) {
                    int index = i;
                    for (int j = i + 1; j < n; j++) {
                        if (isReverse ? result[j].compareTo(result[index]) > 0 : result[j].compareTo(result[index]) < 0) {
                            index = j;
                        }
                    }
                    if (index != i) {
                        String temp = result[index];
                        result[index] = result[i];
                        result[i] = temp;
                    }
                }
            }
        } else {
            for (int i = 0; i < n - 1; i++) {
                int index = i;
                for (int j = i + 1; j < n; j++) {
                    if (isReverse ? result[j].compareTo(result[index]) > 0 : result[j].compareTo(result[index]) < 0) {
                        index = j;
                    }
                }
                if (index != i) {
                    String temp = result[index];
                    result[index] = result[i];
                    result[i] = temp;
                }
            }
        }
        long elapsedTime = System.nanoTime() - startTime;
        sortResult = new SortResult<>(null, elapsedTime, result);
        return gson.toJson(sortResult);
    }
}
