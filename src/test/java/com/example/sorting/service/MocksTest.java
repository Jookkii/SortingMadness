package com.example.sorting.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Map;

@ExtendWith(MockitoExtension.class)
class SortContextTest {

    @Mock
    private SortJsonInterface quickSortStrategy;

    @Mock
    private SortJsonInterface shellSortStrategy;

    @Mock
    private SortJsonInterface selectionSortStrategy;

    @Mock
    private SortJsonInterface differentStrategy;

    private SortContext sortContext;
    private Gson gson;

    @BeforeEach
    void setUp() {
        Map<String, SortJsonInterface> strategies = new HashMap<>();
        strategies.put("quicksort", quickSortStrategy);
        strategies.put("shellsort", shellSortStrategy);
        strategies.put("selectionsort", selectionSortStrategy);

        sortContext = new SortContext(strategies);
        gson = new Gson();
    }

    @Test
    void whenSortingWithQuickSort_thenCorrectMethodCalled() {
        // Prepare test data
        JsonObject input = createTestInput(new int[]{3, 1, 4}, 3, false);
        String expectedOutput = createExpectedOutput(new int[]{1, 3, 4}, 100);

        // Configure mock
        when(quickSortStrategy.sort(any(JsonObject.class))).thenReturn(expectedOutput);

        // Execute
        String result = sortContext.sort("quicksort", input);

        // Verify
        assertEquals(expectedOutput, result);
        verify(quickSortStrategy).sort(input);
    }


    @Test
    void whenSortingWithShellSort_thenCorrectMethodCalled() {
        JsonObject input = createTestInput(new String[]{"c", "a", "b"}, 3, false);
        String expectedOutput = createExpectedOutput(new String[]{"a", "b", "c"}, 150);

        when(shellSortStrategy.sort(any(JsonObject.class))).thenReturn(expectedOutput);

        String result = sortContext.sort("shellsort", input);

        assertEquals(expectedOutput, result);
        verify(shellSortStrategy).sort(input);
    }

    @Test
    void whenSortingWithSelectionSort_thenCorrectMethodCalled() {
        JsonObject input = createTestInput(new int[]{5, 2, 8}, 3, true);
        String expectedOutput = createExpectedOutput(new int[]{8, 5, 2}, 120);

        when(selectionSortStrategy.sort(any(JsonObject.class))).thenReturn(expectedOutput);

        String result = sortContext.sort("selectionsort", input);

        assertEquals(expectedOutput, result);
        verify(selectionSortStrategy).sort(input);
    }

    @Test
    void whenSortingWithUnknownAlgorithm_thenThrowException() {
        JsonObject input = createTestInput(new int[]{1, 2, 3}, 3, false);

        assertThrows(IllegalArgumentException.class, () -> {
            sortContext.sort("unknownalgorithm", input);
        });
    }

    private JsonObject createTestInput(int[] list, int n, boolean isReverse) {
        JsonObject input = new JsonObject();
        input.add("list", gson.toJsonTree(list));
        input.addProperty("n", n);
        input.addProperty("isReverse", isReverse);
        return input;
    }

    private JsonObject createTestInput(String[] list, int n, boolean isReverse) {
        JsonObject input = new JsonObject();
        input.add("list", gson.toJsonTree(list));
        input.addProperty("n", n);
        input.addProperty("isReverse", isReverse);
        return input;
    }

    private String createExpectedOutput(Object sortedArray, long executionTime) {
        JsonObject result = new JsonObject();
        result.add("sortedArray", gson.toJsonTree(sortedArray));
        result.addProperty("executionTime", executionTime);
        return gson.toJson(result);
    }
}