public class MergeSort {

    public static int[] sort(int[] l, int n) {
//        int n = l.length;
        int[] result = l.clone();
        for (int currentSize = 1; currentSize < result.length && n-- > 0; currentSize *= 2) {
            for (int leftStart = 0; leftStart < n - 1; leftStart += 2 * currentSize) {
                int mid = Math.min(leftStart + currentSize - 1, n - 1);
                int rightEnd = Math.min(leftStart + 2 * currentSize - 1, n - 1);

                merge(result, leftStart, mid, rightEnd, true);
            }
        }
        return result;
    }

    public static String[] sort(String[] l, int n) {
//        int n = l.length;
        String[] result = l.clone();
        for (int currentSize = 1; currentSize < result.length && n-- > 0; currentSize *= 2) {
            for (int leftStart = 0; leftStart < n - 1; leftStart += 2 * currentSize) {
                int mid = Math.min(leftStart + currentSize - 1, n - 1);
                int rightEnd = Math.min(leftStart + 2 * currentSize - 1, n - 1);

                merge(result, leftStart, mid, rightEnd, true);
            }
        }
        return result;
    }

    public static int[] sortInReverse(int[] l, int n) {
//        int n = l.length;
        int[] result = l.clone();
        for (int currentSize = 1; currentSize < result.length && n-- > 0; currentSize *= 2) {
            for (int leftStart = 0; leftStart < n - 1; leftStart += 2 * currentSize) {
                int mid = Math.min(leftStart + currentSize - 1, n - 1);
                int rightEnd = Math.min(leftStart + 2 * currentSize - 1, n - 1);

                merge(result, leftStart, mid, rightEnd, false);
            }
        }
        return result;
    }

    public static String[] sortInReverse(String[] l, int n) {
//        int n = l.length;
        String[] result = l.clone();
        for (int currentSize = 1; currentSize < result.length && n-- > 0; currentSize *= 2) {
            for (int leftStart = 0; leftStart < n - 1; leftStart += 2 * currentSize) {
                int mid = Math.min(leftStart + currentSize - 1, n - 1);
                int rightEnd = Math.min(leftStart + 2 * currentSize - 1, n - 1);

                merge(result, leftStart, mid, rightEnd, false);
            }
        }
        return result;
    }

    private static void merge(int[] l, int left, int mid, int right, boolean ascending) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] leftArr = new int[n1];
        int[] rightArr = new int[n2];

        for (int i = 0; i < n1; i++) {
            leftArr[i] = l[left + i];
        }
        for (int j = 0; j < n2; j++) {
            rightArr[j] = l[mid + 1 + j];
        }

        int i = 0, j = 0, k = left;

        while (i < n1 && j < n2) {
            if ((ascending && leftArr[i] <= rightArr[j]) || (!ascending && leftArr[i] > rightArr[j])) {
                l[k++] = leftArr[i++];
            } else {
                l[k++] = rightArr[j++];
            }
        }

        while (i < n1) {
            l[k++] = leftArr[i++];
        }

        while (j < n2) {
            l[k++] = rightArr[j++];
        }
    }

    private static void merge(String[] l, int left, int mid, int right, boolean ascending) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        String[] leftArr = new String[n1];
        String[] rightArr = new String[n2];

        for (int i = 0; i < n1; i++) {
            leftArr[i] = l[left + i];
        }
        for (int j = 0; j < n2; j++) {
            rightArr[j] = l[mid + 1 + j];
        }

        int i = 0, j = 0, k = left;

        while (i < n1 && j < n2) {
            if ((ascending && leftArr[i].compareTo(rightArr[j]) <= 0) ||
                    (!ascending && leftArr[i].compareTo(rightArr[j]) > 0)) {
                l[k++] = leftArr[i++];
            } else {
                l[k++] = rightArr[j++];
            }
        }

        while (i < n1) {
            l[k++] = leftArr[i++];
        }

        while (j < n2) {
            l[k++] = rightArr[j++];
        }
    }
}
