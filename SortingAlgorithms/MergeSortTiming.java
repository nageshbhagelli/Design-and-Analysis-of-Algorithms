// 3.Design and implement merge sort algorithm that takes random number input and displays 
//the execution time required. Complete the program for the given values.
/*
 * ALGORITHM Mergesort(A[0..n − 1])
// Sorts array A[0..n − 1] by recursive mergesort
// Input: An array A[0..n − 1] of orderable elements
// Output: Array A[0..n − 1] sorted in nondecreasing order

if n > 1  
    copy A[0..⌊n/2⌋ − 1] to B[0..⌊n/2⌋ − 1]  
    copy A[⌊n/2⌋..n − 1] to C[0..⌈n/2⌉ − 1]  

    Mergesort(B[0..⌊n/2⌋ − 1])  
    Mergesort(C[0..⌈n/2⌉ − 1])  

    Merge(B, C, A)
 Merge two sorted arrays:

ALGORITHM Merge(B[0..p − 1], C[0..q − 1], A[0..p + q − 1])
// Merges two sorted arrays into one sorted array
// Input: Arrays B[0..p − 1] and C[0..q − 1] both sorted
// Output: Sorted array A[0..p + q − 1] of the elements of B and C
i ← 0;  j ← 0;  k ← 0  
while i < p and j < q do  
    if B[i] ≤ C[j]  
        A[k] ← B[i];  i ← i + 1  
    else  
        A[k] ← C[j];  j ← j + 1  
    k ← k + 1  

if i = p  
    copy C[j..q − 1] to A[k..p + q − 1]  
else  
    copy B[i..p − 1] to A[k..p + q − 1]

 */

import java.util.*;

public class MergeSortTiming {

    // Merge Sort function
    public static void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }

    // Merge two halves
    public static void merge(int[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] L = new int[n1];
        int[] R = new int[n2];

        System.arraycopy(arr, left, L, 0, n1);
        System.arraycopy(arr, mid + 1, R, 0, n2);

        int i = 0, j = 0, k = left;

        while (i < n1 && j < n2)
            arr[k++] = (L[i] <= R[j]) ? L[i++] : R[j++];
        while (i < n1)
            arr[k++] = L[i++];
        while (j < n2)
            arr[k++] = R[j++];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of elements to sort: ");
        int n = sc.nextInt();
        int[] arr = new int[n];

        Random rand = new Random();
        System.out.println("Generating random numbers...");
        for (int i = 0; i < n; i++)
            arr[i] = rand.nextInt(100000); // random numbers up to 100000

        // Start timing
        long startTime = System.nanoTime();

        mergeSort(arr, 0, n - 1);

        // End timing
        long endTime = System.nanoTime();
        double elapsedTimeMillis = (endTime - startTime) / 1_000_000.0;

        System.out.println("Merge Sort completed.");
        System.out.printf("Execution time: %.3f ms\n", elapsedTimeMillis);

        // Optional: print sorted array
        System.out.println("Sorted array: " + Arrays.toString(arr));
    }
}
