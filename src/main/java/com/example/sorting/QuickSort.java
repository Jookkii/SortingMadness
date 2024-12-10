import com.google.gson.Gson;
import java.util.*;
import java.util.Stack;

public class QuickSort {

    private static final Gson gson = new Gson();

    public static <T> String quickSort(T[] array, Comparator<T> comparator) {
        long startTime = System.nanoTime();

        T[] result = array.clone();
        Stack<int[]> stack = new Stack<>();
        stack.push(new int[]{0, result.length - 1});

        while (!stack.isEmpty()) {
            int[] range = stack.pop();
            int low = range[0];
            int high = range[1];

            if (low < high) {
                int partitionIndex = partition(result, low, high, comparator);
                stack.push(new int[]{low, partitionIndex - 1});
                stack.push(new int[]{partitionIndex + 1, high});
            }
        }

        long endTime = System.nanoTime();
        long executionTime = endTime - startTime;

        Map<String, Object> response = new HashMap<>();
        response.put("sorted", result);
        response.put("executionTime", executionTime);

        return gson.toJson(response);
    }

    public static String quickSort(int[] array, boolean reverse) {
        Integer[] boxedArray = Arrays.stream(array).boxed().toArray(Integer[]::new);
        Comparator<Integer> comparator = reverse ? Comparator.reverseOrder() : Comparator.naturalOrder();
        return quickSort(boxedArray, comparator);
    }

    public static String quickSort(String[] array, boolean reverse) {
        Comparator<String> comparator = reverse ? Comparator.reverseOrder() : Comparator.naturalOrder();
        return quickSort(array, comparator);
    }

    private static <T> int partition(T[] array, int low, int high, Comparator<T> comparator) {
        T pivot = array[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (comparator.compare(array[j], pivot) < 0) {
                i++;
                T temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }

        T temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;

        return i + 1;
    }
}