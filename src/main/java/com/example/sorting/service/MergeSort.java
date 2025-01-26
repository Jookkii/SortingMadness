package com.example.sorting.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.springframework.stereotype.Component;

import java.util.Random;

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
            MergeSort.SortRequest request = gson.fromJson(jsonInput, MergeSort.SortRequest.class);

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
        long startTime = System.nanoTime();
        SortResult<int[]> sortResult;

        if (time != 0) {
            int k = 0;
            while (k < n && System.nanoTime() - startTime < time) {
                for (int currentSize = 1; currentSize < n; currentSize *= 2) {
                    for (int leftStart = 0; leftStart < n - 1; leftStart += 2 * currentSize) {
                        int mid = Math.min(leftStart + currentSize - 1, n - 1);
                        int rightEnd = Math.min(leftStart + 2 * currentSize - 1, n - 1);

                        merge(result, leftStart, mid, rightEnd, !isReverse);
                    }
                }
            }
        } else {
            for (int currentSize = 1; currentSize < n; currentSize *= 2) {
                for (int leftStart = 0; leftStart < n - 1; leftStart += 2 * currentSize) {
                    int mid = Math.min(leftStart + currentSize - 1, n - 1);
                    int rightEnd = Math.min(leftStart + 2 * currentSize - 1, n - 1);

                    merge(result, leftStart, mid, rightEnd, !isReverse);
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
            }
        } else {
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
        }
        long elapsedTime = System.nanoTime() - startTime;
        sortResult = new SortResult<>(null, elapsedTime, result);
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
