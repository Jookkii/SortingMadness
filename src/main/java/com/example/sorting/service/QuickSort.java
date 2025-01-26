package com.example.sorting.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Random;
import java.util.Stack;
import com.google.gson.JsonObject;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import org.springframework.stereotype.Component;

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
            QuickSort.SortRequest request = gson.fromJson(jsonInput, QuickSort.SortRequest.class);

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
        Stack<int[]> stack = new Stack<>();
        stack.push(new int[]{0, result.length - 1});
        SortResult<int[]> sortResult;
        long startTime = System.nanoTime();


        if (time != 0) {
            int k = 0;
            while (k < n && System.nanoTime() - startTime < time) {
                while (!stack.isEmpty() && n-- > 0) {
                    int[] range = stack.pop();
                    int low = range[0];
                    int high = range[1];

                    if (low < high) {
                        int partitionIndex;
                        if (isReverse) {
                            partitionIndex = partition(result, low, high, false);
                        }
                        else {
                            partitionIndex = partition(result, low, high, true);
                        }
                        stack.push(new int[]{low, partitionIndex - 1});
                        stack.push(new int[]{partitionIndex + 1, high});
                    }
                }
            }
        } else {
            while (!stack.isEmpty() && n-- > 0) {
                int[] range = stack.pop();
                int low = range[0];
                int high = range[1];

                if (low < high) {
                    int partitionIndex;
                    if (isReverse) {
                        partitionIndex = partition(result, low, high, false);
                    }
                    else {
                        partitionIndex = partition(result, low, high, true);
                    }
                    stack.push(new int[]{low, partitionIndex - 1});
                    stack.push(new int[]{partitionIndex + 1, high});
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
     * @return Posortowana lista i czas sortowania jako json
     */
    public static String sortL(String[] l, int n, boolean isReverse, long time) {
        n = (n <= 0 || n > l.length) ? l.length : n;
        String[] result = l.clone();
        SortResult<String[]> sortResult;
        Stack<int[]> stack = new Stack<>();
        stack.push(new int[]{0, result.length - 1});
        long startTime = System.nanoTime();

        if (time != 0) {
            int k = 0;
            while (k < n && System.nanoTime() - startTime < time) {
                while (!stack.isEmpty() && n-- > 0) {
                    int[] range = stack.pop();
                    int low = range[0];
                    int high = range[1];

                    if (low < high) {
                        int partitionIndex;
                        if (isReverse) {
                            partitionIndex = partition(result, low, high, false);
                        }
                        else {
                            partitionIndex = partition(result, low, high, true);
                        }
                        stack.push(new int[]{low, partitionIndex - 1});
                        stack.push(new int[]{partitionIndex + 1, high});
                    }
                }
            }
        } else {
            while (!stack.isEmpty() && n-- > 0) {
                int[] range = stack.pop();
                int low = range[0];
                int high = range[1];

                if (low < high) {
                    int partitionIndex;
                    if (isReverse) {
                        partitionIndex = partition(result, low, high, false);
                    }
                    else {
                        partitionIndex = partition(result, low, high, true);
                    }
                    stack.push(new int[]{low, partitionIndex - 1});
                    stack.push(new int[]{partitionIndex + 1, high});
                }
            }
        }
        long elapsedTime = System.nanoTime() - startTime;
        sortResult = new SortResult<>(null, elapsedTime, result);
        return gson.toJson(sortResult);
    }

    /**
     *Funkcja bierze podtablicę (zadaną przez indeksy low i high) i wybiera element na pozycji high jako pivot, następnie przesuwa elementy tablicy tak, aby były uporządkowane względem pivota; wersja dla int
     * @param arr Lista, która zostanie częściowo posortowana
     * @param low Indeks początkowy podtablicy, która ma zostać podzielona
     * @param high Indeks końcowy podtablicy, który wskazuje na element wybrany jako pivot
     * @param ascending Znacznik określający porządek sortowania
     * @return  indeks, pod którym pivot został umieszczony po podziale
     */
    private static int partition(int[] arr, int low, int high, boolean ascending) {
        int pivot = arr[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if ((ascending && arr[j] < pivot) || (!ascending && arr[j] >= pivot)) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }

    /**
     *Funkcja bierze podtablicę (zadaną przez indeksy low i high) i wybiera element na pozycji high jako pivot, następnie przesuwa elementy tablicy tak, aby były uporządkowane względem pivota; wersja dla String
     * @param arr Lista, która zostanie częściowo posortowana
     * @param low Indeks początkowy podtablicy, która ma zostać podzielona
     * @param high Indeks końcowy podtablicy, który wskazuje na element wybrany jako pivot
     * @param ascending Znacznik określający porządek sortowania
     * @return  indeks, pod którym pivot został umieszczony po podziale
     */
    private static int partition(String[] arr, int low, int high, boolean ascending) {
        String pivot = arr[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if ((ascending && arr[j].compareTo(pivot) < 0) || (!ascending && arr[j].compareTo(pivot) >= 0)) {
                i++;
                String temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        String temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }
}
