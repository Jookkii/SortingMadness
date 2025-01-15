package com.example.sorting.controller;

import com.example.sorting.dto.SortRequest;
import com.example.sorting.dto.SortResult;
import com.example.sorting.service.SortContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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
            @RequestBody SortRequest sortRequest
    ) {
        String[] algorithmList = algorithms.split(",");
        logger.debug("Odebrano i odczytano listę algorytmów");
        List<SortResult> results = new ArrayList<>();

        logger.debug("Array in sortRequest: {}", sortRequest.getArray());
        logger.debug("Key in sortRequest: {}", sortRequest.getKey());
        logger.debug("N in sortRequest: {}", sortRequest.getN());


        // Konwersja sortRequest na JsonObject
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("list", new com.google.gson.Gson().toJsonTree(sortRequest.getArray()));
        jsonObject.addProperty("key", sortRequest.getKey());
        jsonObject.addProperty("n", sortRequest.getN());

        logger.debug("Przetworzono request body na obiekt typu Json");
        logger.debug("Lista: {}", jsonObject.get("list").toString()); // Wyświetla zawartość listy
        logger.debug("N: {}", jsonObject.get("n").toString()); // Wyświetla zawartość listy
        logger.info("Odebrano przekazane parametry");

        for (String algorithm : algorithmList) {
            try {
                String sortedArrayJson = sortContext.sort(algorithm, jsonObject);
                logger.debug("Wykorzystano strategię: " + algorithm);
                JsonObject sortedResult = JsonParser.parseString(sortedArrayJson).getAsJsonObject();
                List<String> sortedArray = new ArrayList<>();

                sortedResult.getAsJsonArray("sortedArray").forEach(element -> sortedArray.add(element.getAsString()));

                long executionTime = sortedResult.get("executionTime").getAsLong();
                results.add(new SortResult(algorithm, executionTime, sortedArray));

            } catch (Exception e) {
                logger.error("Błąd podczas sortowania przy użyciu algorytmu: " + algorithm, e);
                results.add(new SortResult(algorithm, -1, List.of("Error: " + e.getMessage())));
            }
        }

        logger.info("Zakończono wykonywanie algorytmów");
        return results;
    }

    @GetMapping("/api")
    public String getData2() {
        return "Smile:)";
    }
}
