import com.google.gson.Gson;
import java.util.*;
import java.util.function.BiFunction;

public class BubbleSort {

    private static final Gson gson = new Gson();

    public static <T> String bubbleSort(T[] array, Comparator<T> comparator) {
        long startTime = System.nanoTime();

        T[] result = array.clone();
        int n = result.length;
        boolean swap;
        for (int i = 0; i < n - 1; i++) {
            swap = false;
            for (int j = 0; j < n - 1 - i; j++) {
                if (comparator.compare(result[j], result[j + 1]) > 0) {
                    T temp = result[j];
                    result[j] = result[j + 1];
                    result[j + 1] = temp;
                    swap = true;
                }
            }
            if (!swap) {
                break;
            }
        }

        long endTime = System.nanoTime();
        long executionTime = endTime - startTime;

        Map<String, Object> response = new HashMap<>();
        response.put("sorted", result);
        response.put("executionTime", executionTime);

        return gson.toJson(response);
    }

    public static String bubbleSort(int[] array, boolean reverse) {
        Integer[] boxedArray = Arrays.stream(array).boxed().toArray(Integer[]::new);
        Comparator<Integer> comparator = reverse ? Comparator.reverseOrder() : Comparator.naturalOrder();
        return bubbleSort(boxedArray, comparator);
    }

    public static String bubbleSort(String[] array, boolean reverse) {
        Comparator<String> comparator = reverse ? Comparator.reverseOrder() : Comparator.naturalOrder();
        return bubbleSort(array, comparator);
    }
}
