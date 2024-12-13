package com.example.sorting;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Arrays;
import java.util.List;

@RestController
public class ApiController {


    @PostMapping("/bubblesort-numbers")
    public List<Integer> bubbleSortNumbers(@RequestBody List<Integer> numbers) {
        int[] inputArray = numbers.stream().mapToInt(Integer::intValue).toArray();
        int[] sortedArray = BubbleSort.bubbleSort(inputArray);
        return Arrays.stream(sortedArray).boxed().toList();
    }

    @PostMapping("/bubblesort-numbers/reverse")
    public List<Integer> bubbleSortNumbersReverse(@RequestBody List<Integer> numbers) {
        int[] inputArray = numbers.stream().mapToInt(Integer::intValue).toArray();
        int[] sortedArray = BubbleSort.bubbleSortReverse(inputArray);
        return Arrays.stream(sortedArray).boxed().toList();
    }

    @PostMapping("/bubblesort-strings")
    public List<String> bubbleSortStrings(@RequestBody List<String> strings) {
        String[] inputArray = strings.toArray(new String[0]);
        String[] sortedArray = BubbleSort.bubbleSort(inputArray);
        return Arrays.asList(sortedArray);
    }

    @PostMapping("/bubblesort-strings/reverse")
    public List<String> bubbleSortStringsReverse(@RequestBody List<String> strings) {
        String[] inputArray = strings.toArray(new String[0]);
        String[] sortedArray = BubbleSort.bubbleSortReverse(inputArray);
        return Arrays.asList(sortedArray);
    }

    @PostMapping("/insertionsort-numbers")
    public List<Integer> insertionSortNumbers(@RequestBody List<Integer> numbers) {
        int[] inputArray = numbers.stream().mapToInt(Integer::intValue).toArray();
        int[] sortedArray = InsertionSort.insertionSort(inputArray);
        return Arrays.stream(sortedArray).boxed().toList();
    }

    @PostMapping("/insertionsort-numbers/reverse")
    public List<Integer> insertionSortNumbersReverse(@RequestBody List<Integer> numbers) {
        int[] inputArray = numbers.stream().mapToInt(Integer::intValue).toArray();
        int[] sortedArray = InsertionSort.insertionSortReverse(inputArray);
        return Arrays.stream(sortedArray).boxed().toList();
    }

    @PostMapping("/insertionsort-strings")
    public List<String> insertionSortStrings(@RequestBody List<String> strings) {
        String[] inputArray = strings.toArray(new String[0]);
        String[] sortedArray = BubbleSort.bubbleSort(inputArray);
        return Arrays.asList(sortedArray);
    }

    @PostMapping("/insertionsort-strings/reverse")
    public List<String> insertionSortStringsReverse(@RequestBody List<String> strings) {
        String[] inputArray = strings.toArray(new String[0]);
        String[] sortedArray = BubbleSort.bubbleSortReverse(inputArray);
        return Arrays.asList(sortedArray);
    }

    @PostMapping("/mergesort-numbers")
    public List<Integer> mergeSortNumbers(@RequestBody List<Integer> numbers) {
        int[] inputArray = numbers.stream().mapToInt(Integer::intValue).toArray();
        int[] sortedArray = MergeSort.mergeSort(inputArray);
        return Arrays.stream(sortedArray).boxed().toList();
    }

    @PostMapping("/mergesort-numbers/reverse")
    public List<Integer> mergeSortNumbersReverse(@RequestBody List<Integer> numbers) {
        int[] inputArray = numbers.stream().mapToInt(Integer::intValue).toArray();
        int[] sortedArray = MergeSort.mergeSortReverse(inputArray);
        return Arrays.stream(sortedArray).boxed().toList();
    }

    @PostMapping("/mergesort-strings")
    public List<String> mergeSortStrings(@RequestBody List<String> strings) {
        String[] inputArray = strings.toArray(new String[0]);
        String[] sortedArray = MergeSort.mergeSort(inputArray);
        return Arrays.asList(sortedArray);
    }

    @PostMapping("/mergesort-strings/reverse")
    public List<String> mergeSortStringsReverse(@RequestBody List<String> strings) {
        String[] inputArray = strings.toArray(new String[0]);
        String[] sortedArray = MergeSort.mergeSortReverse(inputArray);
        return Arrays.asList(sortedArray);
    }

    @PostMapping("/quicksort-numbers")
    public List<Integer> quickSortNumbers(@RequestBody List<Integer> numbers) {
        int[] inputArray = numbers.stream().mapToInt(Integer::intValue).toArray();
        int[] sortedArray = QuickSort.quickSort(inputArray);
        return Arrays.stream(sortedArray).boxed().toList();
    }

    @PostMapping("/quicksort-numbers/reverse")
    public List<Integer> quickSortNumbersReverse(@RequestBody List<Integer> numbers) {
        int[] inputArray = numbers.stream().mapToInt(Integer::intValue).toArray();
        int[] sortedArray = QuickSort.quickSortReverse(inputArray);
        return Arrays.stream(sortedArray).boxed().toList();
    }

    @PostMapping("/quicksort-strings")
    public List<String> quickSortStrings(@RequestBody List<String> strings) {
        String[] inputArray = strings.toArray(new String[0]);
        String[] sortedArray = QuickSort.quickSort(inputArray);
        return Arrays.asList(sortedArray);
    }

    @PostMapping("/quicksort-strings/reverse")
    public List<String> quickSortStringsReverse(@RequestBody List<String> strings) {
        String[] inputArray = strings.toArray(new String[0]);
        String[] sortedArray = QuickSort.quickSortReverse(inputArray);
        return Arrays.asList(sortedArray);
    }

    @PostMapping("/selectionsort-numbers")
    public List<Integer> selectionSortNumbers(@RequestBody List<Integer> numbers) {
        int[] inputArray = numbers.stream().mapToInt(Integer::intValue).toArray();
        int[] sortedArray = SelectionSort.selectionSort(inputArray);
        return Arrays.stream(sortedArray).boxed().toList();
    }

    @PostMapping("/selectionsort-numbers/reverse")
    public List<Integer> selectionSortNumbersReverse(@RequestBody List<Integer> numbers) {
        int[] inputArray = numbers.stream().mapToInt(Integer::intValue).toArray();
        int[] sortedArray = SelectionSort.selectionSortReverse(inputArray);
        return Arrays.stream(sortedArray).boxed().toList();
    }

    @PostMapping("/selectionsort-strings")
    public List<String> selectionSortStrings(@RequestBody List<String> strings) {
        String[] inputArray = strings.toArray(new String[0]);
        String[] sortedArray = SelectionSort.selectionSort(inputArray);
        return Arrays.asList(sortedArray);
    }

    @PostMapping("/selectionsort-strings/reverce")
    public List<String> selectionSortStringsReverce(@RequestBody List<String> strings) {
        String[] inputArray = strings.toArray(new String[0]);
        String[] sortedArray = SelectionSort.selectionSortReverse(inputArray);
        return Arrays.asList(sortedArray);
    }

    @PostMapping("/shellsort-numbers")
    public List<Integer> shellSortNumbers(@RequestBody List<Integer> numbers) {
        int[] inputArray = numbers.stream().mapToInt(Integer::intValue).toArray();
        int[] sortedArray = ShellSort.shellSort(inputArray);
        return Arrays.stream(sortedArray).boxed().toList();
    }

    @PostMapping("/shellsort-numbers/reverse")
    public List<Integer> shellSortNumbersReverse(@RequestBody List<Integer> numbers) {
        int[] inputArray = numbers.stream().mapToInt(Integer::intValue).toArray();
        int[] sortedArray = ShellSort.shellSortReverse(inputArray);
        return Arrays.stream(sortedArray).boxed().toList();
    }

    @PostMapping("/shellsort-strings")
    public List<String> shellSortStrings(@RequestBody List<String> strings) {
        String[] inputArray = strings.toArray(new String[0]);
        String[] sortedArray = ShellSort.shellSort(inputArray);
        return Arrays.asList(sortedArray);
    }

    @PostMapping("/shellsort-strings/reverse")
    public List<String> shellSortStringsReverse(@RequestBody List<String> strings) {
        String[] inputArray = strings.toArray(new String[0]);
        String[] sortedArray = ShellSort.shellSortReverse(inputArray);
        return Arrays.asList(sortedArray);
    }

    @GetMapping("/api")
    public String getData2() {
        return "Smile:)";
    }

}
