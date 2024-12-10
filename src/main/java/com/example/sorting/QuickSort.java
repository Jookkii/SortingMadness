import java.util.Stack;

public class QuickSort {

    public static int[] quickSort(int[] l) {
        if (l == null || l.length == 0) return l;
        int[] result = l.clone();
        Stack<int[]> stack = new Stack<>();
        stack.push(new int[]{0, result.length - 1});

        while (!stack.isEmpty()) {
            int[] range = stack.pop();
            int low = range[0];
            int high = range[1];

            if (low < high) {
                int partitionIndex = partition(result, low, high, true);
                stack.push(new int[]{low, partitionIndex - 1});
                stack.push(new int[]{partitionIndex + 1, high});
            }
        }
        return result;
    }

    public static String[] quickSort(String[] l) {
        if (l == null || l.length == 0) return l;
        String[] result = l.clone();
        Stack<int[]> stack = new Stack<>();
        stack.push(new int[]{0, result.length - 1});

        while (!stack.isEmpty()) {
            int[] range = stack.pop();
            int low = range[0];
            int high = range[1];

            if (low < high) {
                int partitionIndex = partition(result, low, high, true);
                stack.push(new int[]{low, partitionIndex - 1});
                stack.push(new int[]{partitionIndex + 1, high});
            }
        }
        return result;
    }

    public static int[] quickSortReverse(int[] l) {
        if (l == null || l.length == 0) return l;
        int[] result = l.clone();
        Stack<int[]> stack = new Stack<>();
        stack.push(new int[]{0, result.length - 1});

        while (!stack.isEmpty()) {
            int[] range = stack.pop();
            int low = range[0];
            int high = range[1];

            if (low < high) {
                int partitionIndex = partition(result, low, high, false);
                stack.push(new int[]{low, partitionIndex - 1});
                stack.push(new int[]{partitionIndex + 1, high});
            }
        }
        return result;
    }

    public static String[] quickSortReverse(String[] l) {
        if (l == null || l.length == 0) return l;
        String[] result = l.clone();
        Stack<int[]> stack = new Stack<>();
        stack.push(new int[]{0, result.length - 1});

        while (!stack.isEmpty()) {
            int[] range = stack.pop();
            int low = range[0];
            int high = range[1];

            if (low < high) {
                int partitionIndex = partition(result, low, high, false);
                stack.push(new int[]{low, partitionIndex - 1});
                stack.push(new int[]{partitionIndex + 1, high});
            }
        }
        return result;
    }

    private static int partition(int[] arr, int low, int high, boolean ascending) {
        int pivot = arr[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if ((ascending && arr[j] < pivot) || (!ascending && arr[j] >= pivot)) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }


    private static int partition(String[] arr, int low, int high, boolean ascending) {
        String pivot = arr[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if ((ascending && arr[j].compareTo(pivot)<0) || (!ascending && arr[j].compareTo(pivot)>=0)) {
                i++;
                String temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        String temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }

}
