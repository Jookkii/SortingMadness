package com.example.sorting;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.example.sorting.service.*;

@SpringBootApplication
public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        logger.info("Sorting Application Started!");
        SpringApplication.run(Main.class, args); // Uruchamia Spring Boot
        System.out.println("Welcome to Sorting Application!");

        int[] numbers = {5, 3, 8, 4, 2};
        String[] strings = {"banana", "apple", "cherry"};

        // Sortowanie za pomocą InsertionSort
        String insertionSortedNumbersJson = InsertionSort.sort(numbers, numbers.length);
        String insertionSortedStringsJson = InsertionSort.sort(strings, strings.length);

        System.out.println("InsertionSorted Numbers JSON:\n" + insertionSortedNumbersJson);
        System.out.println("InsertionSorted Strings JSON:\n" + insertionSortedStringsJson);

        // Sortowanie w odwrotnej kolejności za pomocą InsertionSort
        String insertionSortedNumbersReverseJson = InsertionSort.sortInReverse(numbers, numbers.length);
        String insertionSortedStringsReverseJson = InsertionSort.sortInReverse(strings, strings.length);

        System.out.println("InsertionSorted Numbers in Reverse JSON:\n" + insertionSortedNumbersReverseJson);
        System.out.println("InsertionSorted Strings in Reverse JSON:\n" + insertionSortedStringsReverseJson);
    }
}
