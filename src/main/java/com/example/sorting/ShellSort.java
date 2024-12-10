public class ShellSort {

    public static int[] sort(int[] l, int n) {
//        int n = l.length;
        int[] result = l.clone();
        for (int gap = n / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < n; i++) {
                int temp = result[i];
                int j;
                for (j = i; j >= gap && result[j - gap] > temp; j -= gap) {
                    result[j] = result[j - gap];
                }
                result[j] = temp;
            }
        }
        return result;
    }

    public static String[] sort(String[] l, int n) {
//        int n = l.length;
        String[] result = l.clone();
        for (int gap = n / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < n; i++) {
                String temp = result[i];
                int j;
                for (j = i; j >= gap && (result[j - gap].compareTo(temp))>0; j -= gap) {
                    result[j] = result[j - gap];
                }
                result[j] = temp;
            }
        }
        return result;
    }


    public static int[] sortInReverse(int[] l, int n) {
//        int n = l.length;
        int[] result = l.clone();
        for (int gap = n / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < n; i++) {
                int temp = result[i];
                int j;
                for (j = i; j >= gap && result[j - gap] < temp; j -= gap) {
                    result[j] = result[j - gap];
                }
                result[j] = temp;
            }
        }
        return result;
    }

    public static String[] sortInReverse(String[] l, int n) {
//        int n = l.length;
        String[] result = l.clone();
        for (int gap = n / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < n; i++) {
                String temp = result[i];
                int j;
                for (j = i; j >= gap && (result[j - gap].compareTo(temp))<0; j -= gap) {
                    result[j] = result[j - gap];
                }
                result[j] = temp;
            }
        }
        return result;
    }

}
