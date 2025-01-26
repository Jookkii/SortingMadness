package com.example.sorting.controller;

import com.example.sorting.Main;
import com.google.gson.JsonArray;
import com.example.sorting.dto.SortResult;
import com.example.sorting.service.*;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.ArrayList;

@RestController
public class ApiController {
    private final SortContext sortContext;
    private static final Logger logger = LoggerFactory.getLogger(ApiController.class);

    @Autowired
    public ApiController(SortContext sortContext) {
        this.sortContext = sortContext;
        logger.debug("Przypisano kontekst sortowań");
        logger.info("Zakończono inicjalizację kontrolera");
    }

    @PostMapping("/sort")
    public List<SortResult> sort(
            @RequestParam String algorithms,
            @RequestBody String requestBody
    ) {
        String[] algorithmList = algorithms.split(",");
        logger.debug("Odebrano i odczytano listę algorytmów");
        List<SortResult> results = new ArrayList<>();
        JsonObject jsonObject = JsonParser.parseString(requestBody).getAsJsonObject();
        logger.debug("Przetworzono request body na obiekt typu Json");
        logger.info("Odebrano przekazane parametry");

        for (String algorithm : algorithmList) {
            if (algorithm == null || algorithm.trim().isEmpty()) {
                algorithm = chooseDefaultAlgorithm(jsonObject);
            }
            String sortedArrayJson = sortContext.sort(algorithm, jsonObject);
            logger.debug("Wykorzystano strategię: " + algorithm);
            JsonObject sortedResult = JsonParser.parseString(sortedArrayJson).getAsJsonObject();
            List<String> generatedArray = new ArrayList<>();
            if (sortedResult.has("generatedArray")) {
                JsonArray generatedJsonArray = sortedResult.getAsJsonArray("generatedArray");
                generatedJsonArray.forEach(element -> generatedArray.add(element.getAsString()));
            }
            List<String> sortedArray = new ArrayList<>();
            sortedResult.getAsJsonArray("sortedArray").forEach(element -> sortedArray.add(element.getAsString()));
            logger.debug("Utworzono JSON z wynikiem działania strategii: " + algorithm);

            long executionTime = sortedResult.get("executionTime").getAsLong();

            results.add(new SortResult(algorithm, executionTime, sortedArray, generatedArray));
        }
        logger.info("Zakończono wykonywanie algorytmów");
        return results;
    }

    @GetMapping("/api")
    public String getData2() {
        return "Smile:)";
    }

    /**
     * Wybiera domyślny algorytm sortowania na podstawie danych wejściowych.
     */
    private String chooseDefaultAlgorithm(JsonObject jsonObject) {
        JsonArray array = jsonObject.getAsJsonArray("list");
        int n;
        double disorderRatio = 1;
        try {
            n = array.size();
            int disorderCount = 0;
            for (int i = 0; i < n - 1; i++) {
                if (array.get(i).getAsInt() > array.get(i + 1).getAsInt()) {
                    disorderCount++;
                }
            }
            disorderRatio = (double) disorderCount / n;
        }
        catch (NullPointerException e) {
            n = 0;
        }
        if (n <= 10) {
            return "bubblesort"; // mały zbiór
        } else if (disorderRatio <= 0.1) {
            return "insertionsort"; // prawie posortowane
        } else if (n <= 1000) {
            return "quickSort"; // średni zbiór
        } else {
            return "mergesort"; // duży zbiór
        }
    }
}

