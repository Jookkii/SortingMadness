package com.example.sorting.service;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class MergeSortTest{

    private MergeSort mergeSort;

    @Before
    public void setUp() {
        mergeSort = new MergeSort();
    }

    @Test
    public void ascendingIntiger() {
        String jsonInput = """
            {
                "list": [5, 3, 8, 1, 2],
                "n": 5,
                "isReverse": false,
                "key": null
            }
            """;
        JsonObject jsonObject = JsonParser.parseString(jsonInput).getAsJsonObject();
        String result = mergeSort.sort(jsonObject);

        JsonObject resultObj = JsonParser.parseString(result).getAsJsonObject();
        JsonObject expected = JsonParser.parseString("""
        {"sortedArray": [1, 2, 3, 5, 8]}
        """).getAsJsonObject();

        assertEquals(
                expected.get("sortedArray").toString(),
                resultObj.get("sortedArray").toString()
        );
        assertTrue(resultObj.has("executionTime"));
    }

    @Test
    public void descendingIntiger() {
        String jsonInput = """
            {
                "list": [5, 3, 8, 1, 10],
                "n": 5,
                "isReverse": true,
                "key": null
            }
            """;
        JsonObject jsonObject = JsonParser.parseString(jsonInput).getAsJsonObject();
        String result = mergeSort.sort(jsonObject);

        JsonObject resultObj = JsonParser.parseString(result).getAsJsonObject();
        JsonObject expected = JsonParser.parseString("""
        {"sortedArray": [10, 8, 5, 3, 1]}
        """).getAsJsonObject();

        assertEquals(
                expected.get("sortedArray").toString(),
                resultObj.get("sortedArray").toString()
        );
        assertTrue(resultObj.has("executionTime"));
    }

    @Test
    public void tooManyIteration() {
        String jsonInput = """
            {
                "list": [5, 3, 8, 1, 10],
                "n": 100,
                "isReverse": true,
                "key": null
            }
            """;
        JsonObject jsonObject = JsonParser.parseString(jsonInput).getAsJsonObject();
        String result = mergeSort.sort(jsonObject);

        JsonObject resultObj = JsonParser.parseString(result).getAsJsonObject();
        JsonObject expected = JsonParser.parseString("""
        {"sortedArray": [10, 8, 5, 3, 1]}
        """).getAsJsonObject();

        assertEquals(
                expected.get("sortedArray").toString(),
                resultObj.get("sortedArray").toString()
        );
        assertTrue(resultObj.has("executionTime"));
    }

    @Test
    public void IterationLimit() {
        String jsonInput = """
            {
                "list": [10, 8, 5, 3, 1],
                "n": 2,
                "isReverse": false,
                "key": null
            }
            """;
        JsonObject jsonObject = JsonParser.parseString(jsonInput).getAsJsonObject();
        String result = mergeSort.sort(jsonObject);

        JsonObject resultObj = JsonParser.parseString(result).getAsJsonObject();
        JsonObject expected = JsonParser.parseString("""
        {"sortedArray": [8, 10, 5, 3, 1]}
        """).getAsJsonObject();

        assertEquals(
                expected.get("sortedArray").toString(),
                resultObj.get("sortedArray").toString()
        );
        assertTrue(resultObj.has("executionTime"));
    }

    @Test
    public void IterationLimit1() {
        String jsonInput = """
            {
                "list": [5, 10, 2, 18, 0, 6, -3, 25],
                "n": 3,
                "isReverse": false,
                "key": null
            }
            """;
        JsonObject jsonObject = JsonParser.parseString(jsonInput).getAsJsonObject();
        String result = mergeSort.sort(jsonObject);

        JsonObject resultObj = JsonParser.parseString(result).getAsJsonObject();
        JsonObject expected = JsonParser.parseString("""
        {"sortedArray": [2, 5, 10, 18, 0, 6, -3, 25]}
        """).getAsJsonObject();

        assertEquals(
                expected.get("sortedArray").toString(),
                resultObj.get("sortedArray").toString()
        );
        assertTrue(resultObj.has("executionTime"));
    }

    @Test
    public void ascendingString() {
        String jsonInput = """
            {
                "list": ["d", "b", "e", "c", "a"],
                "n": 5,
                "isReverse": false,
                "key": null
            }
            """;
        JsonObject jsonObject = JsonParser.parseString(jsonInput).getAsJsonObject();
        String result = mergeSort.sort(jsonObject);

        JsonObject resultObj = JsonParser.parseString(result).getAsJsonObject();
        JsonObject expected = JsonParser.parseString("""
        {"sortedArray": ["a", "b", "c", "d", "e"]}
        """).getAsJsonObject();

        assertEquals(
                expected.get("sortedArray").toString(),
                resultObj.get("sortedArray").toString()
        );
        assertTrue(resultObj.has("executionTime"));
    }

}