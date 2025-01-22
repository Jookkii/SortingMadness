package com.example.sorting.service;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class BubbleSortTest {

    private BubbleSort bubbleSort;

    @Before
    public void setUp() {
        bubbleSort = new BubbleSort();
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
        String result = bubbleSort.sort(jsonObject);

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
        String result = bubbleSort.sort(jsonObject);

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
        String result = bubbleSort.sort(jsonObject);

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
        String result = bubbleSort.sort(jsonObject);

        JsonObject resultObj = JsonParser.parseString(result).getAsJsonObject();
        JsonObject expected = JsonParser.parseString("""
        {"sortedArray": [5, 3, 1, 8, 10]}
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
                "n": 5,
                "isReverse": false,
                "key": null
            }
            """;
        JsonObject jsonObject = JsonParser.parseString(jsonInput).getAsJsonObject();
        String result = bubbleSort.sort(jsonObject);

        JsonObject resultObj = JsonParser.parseString(result).getAsJsonObject();
        JsonObject expected = JsonParser.parseString("""
        {"sortedArray": [2, 0, 5, -3, 6, 10, 18, 25]}
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
        String result = bubbleSort.sort(jsonObject);

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

    @Test
    public void ascendingObjectListByKey() {
        String jsonInput = """
                {
                    "list": [
                        { "name": "John", "age": 30, "gender": "Male" },
                        { "name": "Emily", "age": 27, "gender": "Female" },
                        { "name": "Michael", "age": 35, "gender": "Male" },
                        { "name": "Sophia", "age": 29, "gender": "Female" }
                    ],
                    "n": 5,
                    "isReverse": false,
                    "key": "age"
                }
            """;
        JsonObject jsonObject = JsonParser.parseString(jsonInput).getAsJsonObject();
        String result = bubbleSort.sort(jsonObject);

        JsonObject resultObj = JsonParser.parseString(result).getAsJsonObject();
        JsonObject expected = JsonParser.parseString("""
        {"sortedArray": [
                        { "name": "Emily", "age": 27, "gender": "Female" },
                        { "name": "Sophia", "age": 29, "gender": "Female" },
                        { "name": "John", "age": 30, "gender": "Male" },
                        { "name": "Michael", "age": 35, "gender": "Male" }
                    ]}
        """).getAsJsonObject();

        assertEquals(
                expected.get("sortedArray").toString(),
                resultObj.get("sortedArray").toString()
        );
        assertTrue(resultObj.has("executionTime"));
    }

    @Test
    public void descendingObjectListByKey() {
        String jsonInput = """
                {
                    "list": [
                        { "name": "John", "age": 30, "gender": "Male" },
                        { "name": "Emily", "age": 27, "gender": "Female" },
                        { "name": "Michael", "age": 35, "gender": "Male" },
                        { "name": "Sophia", "age": 29, "gender": "Female" }
                    ],
                    "n": 5,
                    "isReverse": true,
                    "key": "age"
                }
            """;
        JsonObject jsonObject = JsonParser.parseString(jsonInput).getAsJsonObject();
        String result = bubbleSort.sort(jsonObject);

        JsonObject resultObj = JsonParser.parseString(result).getAsJsonObject();
        JsonObject expected = JsonParser.parseString("""
        {"sortedArray": [
                        { "name": "Michael", "age": 35, "gender": "Male" },
                        { "name": "John", "age": 30, "gender": "Male" },
                        { "name": "Sophia", "age": 29, "gender": "Female" },
                        { "name": "Emily", "age": 27, "gender": "Female" }
                    ]}
        """).getAsJsonObject();

        assertEquals(
                expected.get("sortedArray").toString(),
                resultObj.get("sortedArray").toString()
        );
        assertTrue(resultObj.has("executionTime"));
    }

    @Test
    public void ObjectListByKeyIterationLimit() {
        String jsonInput = """
                {
                    "list": [
                        { "name": "John", "age": 30, "gender": "Male" },
                        { "name": "Emily", "age": 27, "gender": "Female" },
                        { "name": "Michael", "age": 35, "gender": "Male" },
                        { "name": "Sophia", "age": 29, "gender": "Female" }
                    ],
                    "n": 2,
                    "isReverse": false,
                    "key": "age"
                }
            """;
        JsonObject jsonObject = JsonParser.parseString(jsonInput).getAsJsonObject();
        String result = bubbleSort.sort(jsonObject);

        JsonObject resultObj = JsonParser.parseString(result).getAsJsonObject();
        JsonObject expected = JsonParser.parseString("""
        {"sortedArray": [
                        { "name": "Emily", "age": 27, "gender": "Female" },
                        { "name": "John", "age": 30, "gender": "Male" },
                        { "name": "Sophia", "age": 29, "gender": "Female" },
                        { "name": "Michael", "age": 35, "gender": "Male" }
                    ]}
        """).getAsJsonObject();

        assertEquals(
                expected.get("sortedArray").toString(),
                resultObj.get("sortedArray").toString()
        );
        assertTrue(resultObj.has("executionTime"));
    }

    @Test
    public void WrongKey() {
        String jsonInput = """
                {
                    "list": [
                        { "name": "John", "age": 30, "gender": "Male" },
                        { "name": "Emily", "age": 27, "gender": "Female" },
                        { "name": "Michael", "age": 35, "gender": "Male" },
                        { "name": "Sophia", "age": 29, "gender": "Female" }
                    ],
                    "n": 2,
                    "isReverse": false,
                    "key": "xd"
                }
            """;
        JsonObject jsonObject = JsonParser.parseString(jsonInput).getAsJsonObject();
        String result = bubbleSort.sort(jsonObject);

        JsonObject resultObj = JsonParser.parseString(result).getAsJsonObject();
        JsonObject expected = JsonParser.parseString("""
        {"sortedArray": [
                        { "name": "John", "age": 30, "gender": "Male" },
                        { "name": "Emily", "age": 27, "gender": "Female" },
                        { "name": "Michael", "age": 35, "gender": "Male" },
                        { "name": "Sophia", "age": 29, "gender": "Female" }
                    ]}
        """).getAsJsonObject();

        assertEquals(
                expected.get("sortedArray").toString(),
                resultObj.get("sortedArray").toString()
        );
        assertTrue(resultObj.has("executionTime"));
    }

}