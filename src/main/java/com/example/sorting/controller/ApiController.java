package com.example.sorting.controller;

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
    public List<String> sort(
            @RequestParam String algorithms,
            @RequestBody String requestBody
    ) {

        String[] algorithmList = algorithms.split(",");

        List<String> sortedResults = new ArrayList<>();

        JsonObject jsonObject = JsonParser.parseString(requestBody).getAsJsonObject();

        for (String algorithm : algorithmList) {
            sortedResults.add(sortContext.sort(algorithm, jsonObject));
        }

        return sortedResults;
    }


    @GetMapping("/api")
    public String getData2() {
        return "Smile:)";
    }

}
