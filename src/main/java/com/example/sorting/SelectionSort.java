
import com.google.gson.Gson;
import java.util.*;

public class SelectionSort {

    private static final Gson gson = new Gson();

    public static <T> String selectionSort(T[] array, Comparator<T> comparator) {
        long startTime = System.nanoTime();

        T[] result = array.clone();
        int n = result.length;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (comparator.compare(result[j], result[minIndex]) < 0) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                T temp = result[minIndex];
                result[minIndex] = result[i];
                result[i] = temp;
            }
        }

        long endTime = System.nanoTime();
        long executionTime = endTime - startTime;

        Map<String, Object> response = new HashMap<>();
        response.put("sorted", result);
        response.put("executionTime", executionTime);

        return gson.toJson(response);
    }

    public static String selectionSort(int[] array, boolean reverse) {
        Integer[] boxedArray = Arrays.stream(array).boxed().toArray(Integer[]::new);
        Comparator<Integer> comparator = reverse ? Comparator.reverseOrder() : Comparator.naturalOrder();
        return selectionSort(boxedArray, comparator);
    }

    public static String selectionSort(String[] array, boolean reverse) {
        Comparator<String> comparator = reverse ? Comparator.reverseOrder() : Comparator.naturalOrder();
        return selectionSort(array, comparator);
    }
}