package com.example.sorting.service;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DifferentSortTest {

    @Mock
    private Gson gson;

    @InjectMocks
    private BubbleSort bubbleSort;

    @BeforeEach
    void setUp() {
        // Initialization if needed
    }

    @Test
    void testSortWithValidJsonArray() {
        // Prepare test data
        JsonObject input = createTestInput(new int[]{3, 1, 2}, 3, false);
        String expectedOutput = "{\"sortedArray\":[1,2,3],\"executionTime\":100}";

        // Configure mocks
        when(gson.fromJson(input, int[].class)).thenReturn(new int[]{3, 1, 2});
        when(gson.toJson(any())).thenReturn(expectedOutput);

        // Execute the sort method
        String result = bubbleSort.sort(input);

        // Verify results
        assertEquals(expectedOutput, result);
        // Additional verification can be done based on the internal logic.
    }

    @Test
    void testSortWithEmptyArray() {
        JsonObject input = createTestInput(new int[]{}, 0, false);
        String expectedOutput = "{\"sortedArray\":[],\"executionTime\":0}";

        // Mock Gson's behavior
        when(gson.fromJson(input, int[].class)).thenReturn(new int[]{});
        when(gson.toJson(any())).thenReturn(expectedOutput);

        String result = bubbleSort.sort(input);

        assertEquals(expectedOutput, result);
    }

    @Test
    void testSortWithUnsupportedType() {
        JsonObject input = new JsonObject(); // Invalid input
        String expectedErrorResponse = "{\"error\":\"The 'list' field must be a non-null JSON array.\"}";

        // Mock Gson's behavior for unsupported type
        when(gson.fromJson(input, int[].class))
                .thenThrow(new IllegalArgumentException("The 'list' field must be a non-null JSON array."));

        String result = bubbleSort.sort(input);

        assertEquals(expectedErrorResponse, result);
    }

    private JsonObject createTestInput(int[] list, int n, boolean isReverse) {
        JsonObject input = new JsonObject();
        input.add("list", gson.toJsonTree(list));
        input.addProperty("n", n);
        input.addProperty("isReverse", isReverse);
        return input;
    }
}