package com.example.sorting.dto;

import java.util.List;
import java.util.ArrayList;

/**
 * Klasa reprezentująca wynik działania algorytmu sortowania.
 * Przechowuje informacje o użytym algorytmie, czas wykonania,
 * posortowaną tablicę oraz opcjonalnie wygenerowaną tablicę wejściową.
 *
 * @author sb
 * @version 1.0
 */
public class SortResult {
    private String algorithm;
    private List<String> generatedArray;
    private long executionTime;
    private List<String> sortedArray;

    // Konstruktor

    /**
     * Tworzy obiekt SortResult z podanymi danymi.
     *
     * @param algorithm      nazwa algorytmu sortowania
     * @param executionTime  czas wykonania algorytmu w milisekundach
     * @param sortedArray    lista posortowanych wartości
     * @param generatedArray opcjonalna lista wygenerowanych wartości (może być null)
     */
    public SortResult(String algorithm, long executionTime, List<String> sortedArray, List<String> generatedArray) {
        this.algorithm = algorithm;
        if (generatedArray != null) {
            this.generatedArray = new ArrayList<>(generatedArray);
        } else {
            this.generatedArray = null;
        }
        this.executionTime = executionTime;
        this.sortedArray = sortedArray;
    }

    // Gettery i Settery

    /**
     * Zwraca nazwę algorytmu sortowania.
     *
     * @return nazwa algorytmu
     */
    public String getAlgorithm() {
        return algorithm;
    }

    /**
     * Ustawia nazwę algorytmu sortowania.
     *
     * @param algorithm nazwa algorytmu
     */
    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

    /**
     * Zwraca czas wykonania algorytmu w milisekundach.
     *
     * @return czas wykonania algorytmu
     */
    public long getExecutionTime() {
        return executionTime;
    }

    /**
     * Ustawia czas wykonania algorytmu w milisekundach.
     *
     * @param executionTime czas wykonania algorytmu
     */
    public void setExecutionTime(long executionTime) {
        this.executionTime = executionTime;
    }

    /**
     * Zwraca posortowaną tablicę wynikową.
     *
     * @return lista posortowanych wartości
     */
    public List<String> getSortedArray() {
        return sortedArray;
    }

    /**
     * Ustawia posortowaną tablicę wynikową.
     *
     * @param sortedArray lista posortowanych wartości
     */
    public void setSortedArray(List<String> sortedArray) {
        this.sortedArray = sortedArray;
    }

    /**
     * Zwraca wygenerowaną tablicę wejściową (jeśli istnieje).
     *
     * @return lista wygenerowanych wartości lub null, jeśli nie ustawiono
     */
    public List<String> getGeneratedArray() {
        return generatedArray;
    }

    /**
     * Ustawia wygenerowaną tablicę wejściową.
     *
     * @param generatedArray lista wygenerowanych wartości
     */
    public void setGeneratedArray(List<String> generatedArray) {
        this.generatedArray = generatedArray;
    }
}
