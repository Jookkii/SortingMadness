package com.example.sorting.service;

import com.google.gson.JsonObject;
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

    public JsonObject sort(String algorithm, JsonObject json) {
        SortJsonInterface strategy = strategies.get(algorithm);
        if (strategy != null) {
            return strategy.sort(json);
        } else {
            throw new IllegalArgumentException("Unknown sorting algorithm: " + algorithm);
        }
    }
}
