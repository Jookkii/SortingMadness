import com.google.gson.Gson;
import java.util.*;

public class MergeSort {

    private static final Gson gson = new Gson();

    public static <T> String mergeSort(T[] array, Comparator<T> comparator) {
        long startTime = System.nanoTime();

        T[] result = array.clone();
        int n = result.length;
        for (int currentSize = 1; currentSize < n; currentSize *= 2) {
            for (int leftStart = 0; leftStart < n - 1; leftStart += 2 * currentSize) {
                int mid = Math.min(leftStart + currentSize - 1, n - 1);
                int rightEnd = Math.min(leftStart + 2 * currentSize - 1, n - 1);

                merge(result, leftStart, mid, rightEnd, comparator);
            }
        }

        long endTime = System.nanoTime();
        long executionTime = endTime - startTime;

        Map<String, Object> response = new HashMap<>();
        response.put("sorted", result);
        response.put("executionTime", executionTime);

        return gson.toJson(response);
    }

    public static String mergeSort(int[] array, boolean reverse) {
        Integer[] boxedArray = Arrays.stream(array).boxed().toArray(Integer[]::new);
        Comparator<Integer> comparator = reverse ? Comparator.reverseOrder() : Comparator.naturalOrder();
        return mergeSort(boxedArray, comparator);
    }

    public static String mergeSort(String[] array, boolean reverse) {
        Comparator<String> comparator = reverse ? Comparator.reverseOrder() : Comparator.naturalOrder();
        return mergeSort(array, comparator);
    }

    private static <T> void merge(T[] array, int left, int mid, int right, Comparator<T> comparator) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        T[] leftArr = Arrays.copyOfRange(array, left, mid + 1);
        T[] rightArr = Arrays.copyOfRange(array, mid + 1, right + 1);

        int i = 0, j = 0, k = left;

        while (i < n1 && j < n2) {
            if (comparator.compare(leftArr[i], rightArr[j]) <= 0) {
                array[k++] = leftArr[i++];
            } else {
                array[k++] = rightArr[j++];
            }
        }

        while (i < n1) {
            array[k++] = leftArr[i++];
        }

        while (j < n2) {
            array[k++] = rightArr[j++];
        }
    }
}
