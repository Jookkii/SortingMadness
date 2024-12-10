import com.google.gson.Gson;
import java.util.*;

public class InsertionSort {

    private static final Gson gson = new Gson();

    public static <T> String insertionSort(T[] array, Comparator<T> comparator) {
        long startTime = System.nanoTime();

        T[] result = array.clone();
        int n = result.length;
        for (int i = 0; i < n; i++) {
            T key = result[i];
            int j = i - 1;
            while (j >= 0 && comparator.compare(result[j], key) > 0) {
                result[j + 1] = result[j];
                j--;
            }
            result[j + 1] = key;
        }

        long endTime = System.nanoTime();
        long executionTime = endTime - startTime;

        Map<String, Object> response = new HashMap<>();
        response.put("sorted", result);
        response.put("executionTime", executionTime);

        return gson.toJson(response);
    }

    public static String insertionSort(int[] array, boolean reverse) {
        Integer[] boxedArray = Arrays.stream(array).boxed().toArray(Integer[]::new);
        Comparator<Integer> comparator = reverse ? Comparator.reverseOrder() : Comparator.naturalOrder();
        return insertionSort(boxedArray, comparator);
    }

    public static String insertionSort(String[] array, boolean reverse) {
        Comparator<String> comparator = reverse ? Comparator.reverseOrder() : Comparator.naturalOrder();
        return insertionSort(array, comparator);
    }
}
