public class collatz {
    
    // Function to calculate the Collatz length for a given number
    public static int collatzLength(long n) {
        int length = 1;
        while (n != 1) {
            if (n % 2 == 0) {
                n = n / 2;
            } else {
                n = 3 * n + 1;
            }
            length++;
        }
        return length;
    }
    
    // Merge Sort implementation
    public static void mergeSort(long[] arr, int[] collatzLengths, int low, int high) {
        if (low < high) {
            int mid = (low + high) / 2;
            mergeSort(arr, collatzLengths, low, mid);
            mergeSort(arr, collatzLengths, mid + 1, high);
            merge(arr, collatzLengths, low, mid, high);
        }
    }
    
    // Merge function for Merge Sort
    public static void merge(long[] arr, int[] collatzLengths, int low, int mid, int high) {
        int n1 = mid - low + 1;
        int n2 = high - mid;
        
        long[] leftArr = new long[n1];
        long[] rightArr = new long[n2];
        int[] leftCollatzLengths = new int[n1];
        int[] rightCollatzLengths = new int[n2];
        
        // Copy data to temporary arrays
        for (int i = 0; i < n1; ++i) {
            leftArr[i] = arr[low + i];
            leftCollatzLengths[i] = collatzLengths[low + i];
        }
        for (int j = 0; j < n2; ++j) {
            rightArr[j] = arr[mid + 1 + j];
            rightCollatzLengths[j] = collatzLengths[mid + 1 + j];
        }
        
        // Merge the temporary arrays
        int i = 0, j = 0;
        int k = low;
        while (i < n1 && j < n2) {
            if (leftCollatzLengths[i] < rightCollatzLengths[j] || 
                (leftCollatzLengths[i] == rightCollatzLengths[j] && leftArr[i] < rightArr[j])) {
                arr[k] = leftArr[i];
                collatzLengths[k] = leftCollatzLengths[i];
                i++;
            } else {
                arr[k] = rightArr[j];
                collatzLengths[k] = rightCollatzLengths[j];
                j++;
            }
            k++;
        }
        
        // Copy remaining elements of leftArr[] if any
        while (i < n1) {
            arr[k] = leftArr[i];
            collatzLengths[k] = leftCollatzLengths[i];
            i++;
            k++;
        }
        
        // Copy remaining elements of rightArr[] if any
        while (j < n2) {
            arr[k] = rightArr[j];
            collatzLengths[k] = rightCollatzLengths[j];
            j++;
            k++;
        }
    }
    
    // Function to find the number and steps at nth place in the sorted list of numbers by Collatz length
    public static void numberAndStepsAtNthPlace(int n) {
        long[] numbers = new long[n];
        int[] collatzLengths = new int[n];
        
        // Calculate Collatz length for each number
        for (int i = 0; i < n; i++) {
            numbers[i] = i + 1;
            collatzLengths[i] = collatzLength(numbers[i]);
        }
        
        // Sort numbers by Collatz lengths using Merge Sort
        mergeSort(numbers, collatzLengths, 0, n - 1);
        
        // Output the number and steps at nth place
        System.out.println("Number at " + n + "th place: " + numbers[n - 1]);
        System.out.println("Steps: " + collatzLengths[n - 1]);
    }
    
    public static void main(String[] args) {
        int n = 10000; // Adjust the value of n as needed
        numberAndStepsAtNthPlace(n);
    }
}