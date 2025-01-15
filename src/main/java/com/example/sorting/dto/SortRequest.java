package com.example.sorting.dto;

import java.util.List;

public class SortRequest {
    private int n;
    private List<Object> array; // Obsługa zarówno Integer, String, jak i obiektów
    private String key; // Pole do sortowania

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

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
