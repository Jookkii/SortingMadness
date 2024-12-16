package com.example.sorting.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import org.springframework.stereotype.Component;

/**
 * Klasa zawierająca metody sortujące algorytmem shell sort
 *
 * @author ML
 * @version 1.0
 */
@Component("shellsort")
public class ShellSort implements SortJsonInterface {

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
        try {
            ShellSort.SortRequest request = gson.fromJson(jsonInput, ShellSort.SortRequest.class);

            JsonElement listElement = request.list;
            int n = request.n;
            boolean isReverse = request.isReverse;
            String key = request.key;

            if (listElement == null || !listElement.isJsonArray()) {
                throw new IllegalArgumentException("The 'list' field must be a non-null JSON array.");
            }

            JsonArray jsonArray = listElement.getAsJsonArray();

            if (jsonArray.size() == 0) {
                return gson.toJson(new int[0]);
            }

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
        } catch (Exception e) {
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
     * @return Posortowana lista i czas sortowania
     */
    public static String sortL(int[] l, int n, boolean isReverse) {
        if (l == null || l.length == 0) {
            return gson.toJson(new SortResult<>(0L, l));
        }
        if (n > l.length) n = l.length;
        int[] result = l.clone();

        long startTime = System.nanoTime();

        for (int gap = n / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < n; i++) {
                int temp = result[i];
                int j;
                for (j = i; j >= gap && (isReverse ? result[j - gap] < temp : result[j - gap] > temp); j -= gap) {
                    result[j] = result[j - gap];
                }
                result[j] = temp;
            }
        }

        long endTime = System.nanoTime();
        long elapsedTime = endTime - startTime;

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
        if (l == null || l.length == 0) {
            return gson.toJson(new SortResult<>(0L, l));
        }
        if (n > l.length) n = l.length;
        String[] result = l.clone();

        long startTime = System.nanoTime();

        for (int gap = n / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < n; i++) {
                String temp = result[i];
                int j;
                for (j = i; j >= gap && (isReverse ? result[j - gap].compareTo(temp) < 0 : result[j - gap].compareTo(temp) > 0); j -= gap) {
                    result[j] = result[j - gap];
                }
                result[j] = temp;
            }
        }

        long endTime = System.nanoTime();
        long elapsedTime = endTime - startTime;

        SortResult<String[]> sortResult = new SortResult<>(elapsedTime, result);
        return gson.toJson(sortResult);
    }
}
