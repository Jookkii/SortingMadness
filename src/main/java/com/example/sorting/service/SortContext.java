//package com.example.sorting.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.Map;
//
//@Service
//public class SortContext {
//
//    private final Map<String, SortInterface> strategies;
//
//    @Autowired
//    public SortContext(Map<String, SortInterface> strategies) {
//        this.strategies = strategies;
//    }
//
//    public int[] sort(String algorithm, int[] numbers) {
//        SortInterface strategy = strategies.get(algorithm);
//        if (strategy != null) {
//            return strategy.sort(numbers);
//        } else {
//            throw new IllegalArgumentException("Unknown sorting algorithm: " + algorithm);
//        }
//    }
//}
