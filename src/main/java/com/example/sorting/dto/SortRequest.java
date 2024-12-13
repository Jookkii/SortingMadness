package com.example.sorting.dto;

import java.util.List;

public class SortRequest {
    private int n;
    private List<Object> array; // Obsługa zarówno Integer, jak i String

    // Gettery i settery
    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public List<Object> getArray() {
        return array;
    }

    public void setArray(List<Object> array) {
        this.array = array;
    }
}
