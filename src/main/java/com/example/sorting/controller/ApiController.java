package com.example.sorting.controller;

import com.example.sorting.dto.*;
import com.example.sorting.service.*;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class ApiController {
    private final SortContext sortContext;

    @Autowired
    public ApiController(SortContext sortContext) {
        this.sortContext = sortContext;
    }
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

    @PostMapping("/sort")
    public Map<String, JsonObject> sort(
            @RequestParam String algorithms,
            @RequestBody JsonObject requestBody
    ) {

        String[] algorithmList = algorithms.split(",");

        Map<String, JsonObject> sortedResults = new LinkedHashMap<>();

        for (String algorithm : algorithmList) {
            sortedResults.put(algorithm, sortContext.sort(algorithm, requestBody));
        }

        return sortedResults;
    }


    @GetMapping("/api")
    public String getData2() {
        return "Smile:)";
    }

}
