public class InsertionSort {

    public static int[] insertionSort(int[] l) {
        int n = l.length;
        int[] result = l.clone();
        for (int i = 0; i < n; i++) {
            int k = result[i];
            int j = i-1;
            while (j >= 0 && result[j] > k) {
                result[j+1] = result[j];
                j--;
            }
            result[j+1] = k;
        }
        return result;
    }


    public static String[] insertionSort(String[] l) {
        int n = l.length;
        String[] result = l.clone();
        for (int i = 0; i < n; i++) {
            String k = result[i];
            int j = i-1;
            while (j >= 0 && result[j].compareTo(k) > 0) {
                result[j+1] = result[j];
                j--;
            }
            result[j+1] = k;
        }
        return result;
    }

    public static int[] insertionSortReverse(int[] l) {
        int n = l.length;
        int[] result = l.clone();
        for (int i = 0; i < n; i++) {
            int k = result[i];
            int j = i-1;
            while (j >= 0 && result[j] < k) {
                result[j+1] = result[j];
                j--;
            }
            result[j+1] = k;
        }
        return result;
    }


    public static String[] insertionSortReverse(String[] l) {
        int n = l.length;
        String[] result = l.clone();
        for (int i = 0; i < n; i++) {
            String k = result[i];
            int j = i-1;
            while (j >= 0 && result[j].compareTo(k) < 0) {
                result[j+1] = result[j];
                j--;
            }
            result[j+1] = k;
        }
        return result;
    }

}
