import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class sortingALgorithms {

    // Insertion Sort
    public static void insertionSort(String[] arr) {
        for (int i = 1; i < arr.length; i++) {
            String key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j].compareTo(key) > 0) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    // Selection Sort
    public static void selectionSort(String[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j].compareTo(arr[minIndex]) < 0) {
                    minIndex = j;
                }
            }
            String temp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = temp;
        }
    }

    // Bubble Sort
    public static void bubbleSort(String[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j].compareTo(arr[j + 1]) > 0) {
                    String temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    // Merge Sort
    public static void mergeSort(String[] arr, int l, int r) {
        if (l < r) {
            int m = (l + r) / 2;
            mergeSort(arr, l, m);
            mergeSort(arr, m + 1, r);
            merge(arr, l, m, r);
        }
    }

    private static void merge(String[] arr, int l, int m, int r) {
        int n1 = m - l + 1;
        int n2 = r - m;
        String[] L = new String[n1];
        String[] R = new String[n2];
        System.arraycopy(arr, l, L, 0, n1);
        for (int j = 0; j < n2; j++) {
            R[j] = arr[m + 1 + j];
        }

        int i = 0, j = 0;
        int k = l;
        while (i < n1 && j < n2) {
            if (L[i].compareTo(R[j]) <= 0) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }

    // Quick Sort
    public static void quickSort(String[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    private static int partition(String[] arr, int low, int high) {
        String pivot = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j].compareTo(pivot) <= 0) {
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

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the size of the array:");
        int n = scanner.nextInt();
        String[] arr = new String[n];
        System.out.println("Enter the elements of the array:");
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.next();
        }
        String[] arrCopy;

        // Insertion Sort
        arrCopy = Arrays.copyOf(arr, arr.length);
        long startTime = System.nanoTime();
        insertionSort(arrCopy);
        long endTime = System.nanoTime();
        System.out.println("Sorted array: " + Arrays.toString(arrCopy));
        System.out.println("Time taken by Insertion Sort: " + (endTime - startTime) + " ns");

        // Selection Sort
        arrCopy = Arrays.copyOf(arr, arr.length);
        startTime = System.nanoTime();
        selectionSort(arrCopy);
        endTime = System.nanoTime();
        System.out.println("Time taken by Selection Sort: " + (endTime - startTime) + " ns");

        // Bubble Sort
        arrCopy = Arrays.copyOf(arr, arr.length);
        startTime = System.nanoTime();
        bubbleSort(arrCopy);
        endTime = System.nanoTime();
        System.out.println("Time taken by Bubble Sort: " + (endTime - startTime) + " ns");

        // Merge Sort
        arrCopy = Arrays.copyOf(arr, arr.length);
        startTime = System.nanoTime();
        mergeSort(arrCopy, 0, arr.length - 1);
        endTime = System.nanoTime();
        System.out.println("Time taken by Merge Sort: " + (endTime - startTime) + " ns");

        // Quick Sort
        arrCopy = Arrays.copyOf(arr, arr.length);
        startTime = System.nanoTime();
        quickSort(arrCopy, 0, arr.length - 1);
        endTime = System.nanoTime();
        System.out.println("Time taken by Quick Sort: " + (endTime - startTime) + " ns");

        Random rand = new Random();
        for (int i = 0; i < 3; i++) {
            String[] randomArr = new String[1000];
            for (int j = 0; j < 1000; j++) {

                int len = rand.nextInt(5) + 1;
                StringBuilder sb = new StringBuilder(len);
                for (int k = 0; k < len; k++) {
                    int type = rand.nextInt(3);
                    if (type == 0) { // digit
                        sb.append(rand.nextInt(10));
                    } else if (type == 1) { // uppercase letter
                        sb.append((char) (rand.nextInt(26) + 'A'));
                    } else { // lowercase letter
                        sb.append((char) (rand.nextInt(26) + 'a'));
                    }
                }
                randomArr[j] = sb.toString();
            }

            arrCopy = Arrays.copyOf(randomArr, randomArr.length);
            startTime = System.nanoTime();
            insertionSort(arrCopy);
            endTime = System.nanoTime();
            System.out.println(
                    "Time taken by Insertion Sort on random array " + (i + 1) + ": " + (endTime - startTime) + " ns");

            arrCopy = Arrays.copyOf(randomArr, randomArr.length);
            startTime = System.nanoTime();
            selectionSort(arrCopy);
            endTime = System.nanoTime();
            System.out.println(
                    "Time taken by Selection Sort on random array " + (i + 1) + ": " + (endTime - startTime) + " ns");

            arrCopy = Arrays.copyOf(randomArr, randomArr.length);
            startTime = System.nanoTime();
            bubbleSort(arrCopy);
            endTime = System.nanoTime();
            System.out.println(
                    "Time taken by Bubble Sort on random array " + (i + 1) + ": " + (endTime - startTime) + " ns");

            arrCopy = Arrays.copyOf(randomArr, randomArr.length);
            startTime = System.nanoTime();
            mergeSort(arrCopy, 0, randomArr.length - 1);
            endTime = System.nanoTime();
            System.out.println(
                    "Time taken by Merge Sort on random array " + (i + 1) + ": " + (endTime - startTime) + " ns");

            arrCopy = Arrays.copyOf(randomArr, randomArr.length);
            startTime = System.nanoTime();
            quickSort(arrCopy, 0, randomArr.length - 1);
            endTime = System.nanoTime();
            System.out.println(
                    "Time taken by Quick Sort on random array " + (i + 1) + ": " + (endTime - startTime) + " ns");
        }
    }
}
