package com.example.sorting.controller;

import com.example.sorting.dto.*;
import com.example.sorting.service.*;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ApiController {

    @PostMapping("/bubblesort")
    public String bubbleSort(@RequestBody SortRequest request) {
        int n = request.getN();
        List<Object> inputArray = request.getArray();

        if (inputArray.isEmpty()) {
            throw new IllegalArgumentException("Array cannot be empty");
        }

        if (inputArray.get(0) instanceof Integer) {
            int[] intArray = inputArray.stream().mapToInt(obj -> (Integer) obj).toArray();
            return BubbleSort.sort(intArray, n);
        } else if (inputArray.get(0) instanceof String) {
            String[] stringArray = inputArray.stream().map(obj -> (String) obj).toArray(String[]::new);
            return BubbleSort.sort(stringArray, n);
        } else {
            throw new IllegalArgumentException("Unsupported array type");
        }
    }

    @PostMapping("/insertionsort")
    public String insertionSort(@RequestBody SortRequest request) {
        int n = request.getN();
        List<Object> inputArray = request.getArray();

        if (inputArray.isEmpty()) {
            throw new IllegalArgumentException("Array cannot be empty");
        }

        if (inputArray.get(0) instanceof Integer) {
            int[] intArray = inputArray.stream().mapToInt(obj -> (Integer) obj).toArray();
            return InsertionSort.sort(intArray, n);
        } else if (inputArray.get(0) instanceof String) {
            String[] stringArray = inputArray.stream().map(obj -> (String) obj).toArray(String[]::new);
            return InsertionSort.sort(stringArray, n);
        } else {
            throw new IllegalArgumentException("Unsupported array type");
        }
    }


    @GetMapping("/api")
    public String getData2() {
        return "Smile:)";
    }

}
