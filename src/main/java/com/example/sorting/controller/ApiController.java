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
            String sortedArrayJson = sortContext.sort(algorithm, jsonObject);
            logger.debug("wykorzystano strategie:"+algorithm);
            JsonObject sortedResult = JsonParser.parseString(sortedArrayJson).getAsJsonObject();
            List<String> generatedArray = new ArrayList<>();
            if (sortedResult.has("generatedArray")) {
                JsonArray generatedJsonArray = sortedResult.getAsJsonArray("generatedArray");
                generatedJsonArray.forEach(element -> generatedArray.add(element.getAsString()));
            }
            List<String> sortedArray = new ArrayList<>();
            sortedResult.getAsJsonArray("sortedArray").forEach(element -> sortedArray.add(element.getAsString()));
            logger.debug("utworzono json z wynikiem działania strategii:"+algorithm);

            long executionTime = sortedResult.get("executionTime").getAsLong();

            results.add(new SortResult(algorithm, executionTime, sortedArray, generatedArray));
        }
        logger.info("zakończono wykonywanie algorytmów");
        return results;
    }


    @GetMapping("/api")
    public String getData2() {
        return "Smile:)";
    }

}
