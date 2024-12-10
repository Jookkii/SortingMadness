public class BubbleSort {

    public static int[] bubbleSort(int[] l) {
        int n = l.length;
        boolean swap;
        int[] result = l.clone();
        for (int i = 0; i < n-1; i++) {
            swap = false;
            for (int j = 0; j < n-1-i; j++) {
                if (result[j] > result[j+1]) {
                    int temp = result[j];
                    result[j] = result[j+1];
                    result[j+1] = temp;
                    swap = true;
                }
            }
            if (!swap) {
                break;
            }
        }
        return result;
    }


    public static String[] bubbleSort(String[] l) {
        int n = l.length;
        boolean swap;
        String[] result = l.clone();
        for (int i = 0; i < n-1; i++) {
            swap = false;
            for (int j = 0; j < n-1-i; j++) {
                if (result[j].compareTo(result[j+1]) > 0) {
                    String temp = result[j];
                    result[j] = result[j+1];
                    result[j+1] = temp;
                    swap = true;
                }
            }
            if (!swap) {
                break;
            }
        }
        return result;
    }


    public static int[] bubbleSortReverse(int[] l) {
        int n = l.length;
        boolean swap;
        int[] result = l.clone();
        for (int i = 0; i < n-1; i++) {
            swap = false;
            for (int j = 0; j < n-1-i; j++) {
                if (result[j] < result[j+1]) {
                    int temp = result[j];
                    result[j] = result[j+1];
                    result[j+1] = temp;
                    swap = true;
                }
            }
            if (!swap) {
                break;
            }
        }
        return result;
    }


    public static String[] bubbleSortReverse(String[] l) {
        int n = l.length;
        boolean swap;
        String[] result = l.clone();
        for (int i = 0; i < n-1; i++) {
            swap = false;
            for (int j = 0; j < n-1-i; j++) {
                if (result[j+1].compareTo(result[j]) > 0) {
                    String temp = result[j];
                    result[j] = result[j+1];
                    result[j+1] = temp;
                    swap = true;
                }
            }
            if (!swap) {
                break;
            }
        }
        return result;
    }
}
