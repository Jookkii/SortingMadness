package com.example.sorting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Map;

@Service
public class SortContext {

    private final Map<String, SortJsonInterface> strategies;

    @Autowired
    public SortContext(Map<String, SortJsonInterface> strategies) {
        this.strategies = strategies;
    }

    public Gson sort(String algorithm, Gson gson) {
        SortJsonInterface strategy = strategies.get(algorithm);
        if (strategy != null) {
            return strategy.sort(gson);
        } else {
            throw new IllegalArgumentException("Unknown sorting algorithm: " + algorithm);
        }
    }
}
