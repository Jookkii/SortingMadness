public class InsertionSort {

    public static int[] sort(int[] l, int n) {
//        int n = l.length;
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


    public static String[] sort(String[] l, int n) {
//        int n = l.length;
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

    public static int[] sortInReverse(int[] l, int n) {
//        int n = l.length;
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


    public static String[] sortInReverse(String[] l, int n) {
//        int n = l.length;
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
