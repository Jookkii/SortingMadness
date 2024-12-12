public class SelectionSort {

    public static int[] sort(int[] l, int n) {
//        int n = l.length;
        int[] result = l.clone();
        for (int i=0; i < n-1; i++) {
            int minIndex = i;
            for (int j=i+1; j < n; j++) {
                if (result[j]<result[minIndex]) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                int temp = result[minIndex];
                result[minIndex] = result[i];
                result[i] = temp;
            }
        }
        return result;
    }

    public static String[] sort(String[] l, int n) {
//        int n = l.length;
        String[] result = l.clone();
        for (int i=0; i < n-1; i++) {
            int minIndex = i;
            for (int j=i+1; j < n; j++) {
                if (result[j].compareTo(result[minIndex])<0) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                String temp = result[minIndex];
                result[minIndex] = result[i];
                result[i] = temp;
            }
        }
        return result;
    }


    public static int[] sortInReverse(int[] l, int n) {
//        int n = l.length;
        int[] result = l.clone();
        for (int i=0; i < n-1; i++) {
            int minIndex = i;
            for (int j=i+1; j < n; j++) {
                if (result[j]>result[minIndex]) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                int temp = result[minIndex];
                result[minIndex] = result[i];
                result[i] = temp;
            }
        }
        return result;
    }

    public static String[] sortInReverse(String[] l, int n) {
//        int n = l.length;
        String[] result = l.clone();
        for (int i=0; i < n-1; i++) {
            int minIndex = i;
            for (int j=i+1; j < n; j++) {
                if (result[j].compareTo(result[minIndex])>0) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                String temp = result[minIndex];
                result[minIndex] = result[i];
                result[i] = temp;
            }
        }
        return result;
    }

}
