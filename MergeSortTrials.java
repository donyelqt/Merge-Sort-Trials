import java.util.Arrays;

public class MergeSortTrials {

    public static void main(String[] args) {
        int[] array1To1000 = generateAscendingArray(1000);
        int[] array1000To1 = generateDescendingArray(1000);
        int[] arrayRandom = generateRandomArray(1000);

        System.out.println("Trial 1: Input 1 up to 1000");
        runAndMeasureSorting(array1To1000);

        System.out.println("\nTrial 2: Input 1000 down to 1");
        runAndMeasureSorting(array1000To1);

        System.out.println("\nTrial 3: Input 1 to 1000 random");
        runAndMeasureSorting(arrayRandom);
    }

    // MergeSort algorithm with a counter variable
    public static void mergeSort(int[] arr) {
        int counter = 0;
        mergeSortHelper(arr, 0, arr.length - 1, counter);
    }

    private static void mergeSortHelper(int[] arr, int low, int high, int counter) {
        if (low < high) {
            int mid = low + (high - low) / 2;

            mergeSortHelper(arr, low, mid, counter);
            mergeSortHelper(arr, mid + 1, high, counter);

            merge(arr, low, mid, high, counter);
        }
    }

    private static void merge(int[] arr, int low, int mid, int high, int counter) {
        int n1 = mid - low + 1;
        int n2 = high - mid;

        int[] left = new int[n1];
        int[] right = new int[n2];

        for (int i = 0; i < n1; i++) {
            left[i] = arr[low + i];
        }
        for (int j = 0; j < n2; j++) {
            right[j] = arr[mid + 1 + j];
        }

        int i = 0, j = 0, k = low;

        while (i < n1 && j < n2) {
            counter++; // Increment the counter for each comparison

            if (left[i] <= right[j]) {
                arr[k] = left[i];
                i++;
            } else {
                arr[k] = right[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            arr[k] = left[i];
            i++;
            k++;
        }

        while (j < n2) {
            arr[k] = right[j];
            j++;
            k++;
        }
    }

    // Utility method to run the sorting algorithm and measure execution time
    public static void runAndMeasureSorting(int[] arr) {
        long startTime = System.currentTimeMillis();
        mergeSort(arr);
        long endTime = System.currentTimeMillis();

        System.out.println("Sorted Array: " + Arrays.toString(arr));
        System.out.println("Number of comparisons in MergeSort: " + arr[arr.length - 1]); // Counter variable is stored in the last element
        System.out.println("Execution time: " + (endTime - startTime) + " milliseconds");
    }

    // Utility methods to generate input arrays for the three trials
    public static int[] generateAscendingArray(int size) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = i + 1;
        }
        return array;
    }

    public static int[] generateDescendingArray(int size) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = size - i;
        }
        return array;
    }

    public static int[] generateRandomArray(int size) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = (int) (Math.random() * size) + 1;
        }
        return array;
    }
}

