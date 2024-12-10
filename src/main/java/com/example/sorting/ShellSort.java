import com.google.gson.Gson;
import java.util.*;

public class ShellSort {

    private static final Gson gson = new Gson();

    public static <T> String shellSort(T[] array, Comparator<T> comparator) {
        long startTime = System.nanoTime();

        T[] result = array.clone();
        int n = result.length;
        for (int gap = n / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < n; i++) {
                T temp = result[i];
                int j;
                for (j = i; j >= gap && comparator.compare(result[j - gap], temp) > 0; j -= gap) {
                    result[j] = result[j - gap];
                }
                result[j] = temp;
            }
        }

        long endTime = System.nanoTime();
        long executionTime = endTime - startTime;

        Map<String, Object> response = new HashMap<>();
        response.put("sorted", result);
        response.put("executionTime", executionTime);

        return gson.toJson(response);
    }

    public static String shellSort(int[] array, boolean reverse) {
        Integer[] boxedArray = Arrays.stream(array).boxed().toArray(Integer[]::new);
        Comparator<Integer> comparator = reverse ? Comparator.reverseOrder() : Comparator.naturalOrder();
        return shellSort(boxedArray, comparator);
    }

    public static String shellSort(String[] array, boolean reverse) {
        Comparator<String> comparator = reverse ? Comparator.reverseOrder() : Comparator.naturalOrder();
        return shellSort(array, comparator);
    }
}
