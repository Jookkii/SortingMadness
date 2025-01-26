package com.example.sorting.dto;

import java.util.List;
import java.util.ArrayList;

public class SortResult {
    private String algorithm;
    private List<String> generatedArray;
    private long executionTime;
    private List<String> sortedArray;


    // Konstruktor
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
    public String getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

    public long getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(long executionTime) {
        this.executionTime = executionTime;
    }

    public List<String> getSortedArray() {
        return sortedArray;
    }

    public void setSortedArray(List<String> sortedArray) {
        this.sortedArray = sortedArray;
    }

    public List<String> getGeneratedArray() {
        return generatedArray;
    }

    public void setGeneratedArray(List<String> generatedArray) {
        this.generatedArray = generatedArray;
    }
}
