package com.example.sorting.dto;

import java.util.List;

public class SortResult {
    private String algorithm;
    private long executionTime;
    private List<String> sortedArray;

    // Konstruktor
    public SortResult(String algorithm, long executionTime, List<String> sortedArray) {
        this.algorithm = algorithm;
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
}
