package com.example.sorting.controller;

import com.example.sorting.dto.SortResult;
import com.example.sorting.service.*;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.ArrayList;

@RestController
public class ApiController {
    private final SortContext sortContext;

    @Autowired
    public ApiController(SortContext sortContext) {
        this.sortContext = sortContext;
    }


    @PostMapping("/sort")
    public List<SortResult> sort(
            @RequestParam String algorithms,
            @RequestBody String requestBody
    ) {
        String[] algorithmList = algorithms.split(",");
        List<SortResult> results = new ArrayList<>();
        JsonObject jsonObject = JsonParser.parseString(requestBody).getAsJsonObject();

        for (String algorithm : algorithmList) {
            String sortedArrayJson = sortContext.sort(algorithm, jsonObject);

            JsonObject sortedResult = JsonParser.parseString(sortedArrayJson).getAsJsonObject();
            List<String> sortedArray = new ArrayList<>();
            sortedResult.getAsJsonArray("sortedArray").forEach(element -> sortedArray.add(element.getAsString()));

            long executionTime = sortedResult.get("executionTime").getAsLong();

            results.add(new SortResult(algorithm, executionTime, sortedArray));
        }

        return results;
    }


    @GetMapping("/api")
    public String getData2() {
        return "Smile:)";
    }

}
