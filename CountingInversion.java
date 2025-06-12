/*
 * Counting Inversions Algorithm:
 * // Purpose: To count the inversions for a given list L(a1,a2,…,an)
 * // Input: An unsorted list of distinct numbers L(a1,a2,…,an)
 * // Output: The number of inversions r for the list L(a1,a2,…,an)
 * Sort-and-Count(L)
 * If the list has one element then
 * there are no inversions
 * Else
 * Divide the list into two halves:
 * A contains the first ⌈n/2⌉ elements
 * B contains the remaining ⌊n/2⌋ elements
 * (r_A, A) = Sort-and-Count(A)
 * (r_B, B) = Sort-and-Count(B)
 * (r, L) = Merge-and-Count(A, B)
 * Endif
 * Return r = r_A + r_B + r, and the sorted list L
 * 
 */

import java.util.*;

public class CountingInversion {

    // Count inversions using modified merge sort
    public static int countInversions(int[] arr) {
        return mergeSortAndCount(arr.clone(), 0, arr.length - 1);
    }

    private static int mergeSortAndCount(int[] arr, int left, int right) {
        if (left >= right)
            return 0;
        int mid = (left + right) / 2;
        int count = mergeSortAndCount(arr, left, mid);
        count += mergeSortAndCount(arr, mid + 1, right);
        count += mergeAndCount(arr, left, mid, right);
        return count;
    }

    private static int mergeAndCount(int[] arr, int left, int mid, int right) {
        int[] leftArr = Arrays.copyOfRange(arr, left, mid + 1);
        int[] rightArr = Arrays.copyOfRange(arr, mid + 1, right + 1);

        int i = 0, j = 0, k = left, swaps = 0;
        while (i < leftArr.length && j < rightArr.length) {
            if (leftArr[i] <= rightArr[j])
                arr[k++] = leftArr[i++];
            else {
                arr[k++] = rightArr[j++];
                swaps += (leftArr.length - i); // Inversion
            }
        }
        while (i < leftArr.length)
            arr[k++] = leftArr[i++];
        while (j < rightArr.length)
            arr[k++] = rightArr[j++];
        return swaps;
    }

    // Map playlist1 order to playlist2 indices
    public static int[] mapOrder(int[] base, int[] target) {
        int[] pos = new int[9]; // songs 1 to 8
        for (int i = 0; i < base.length; i++)
            pos[base[i]] = i;
        int[] mapped = new int[target.length];
        for (int i = 0; i < target.length; i++)
            mapped[i] = pos[target[i]];
        return mapped;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[][] playlists = new int[3][8];

        // Input playlists
        for (int i = 0; i < 3; i++) {
            System.out.println("Enter playlist for user " + (i + 1) + ":");
            for (int j = 0; j < 8; j++) {
                playlists[i][j] = sc.nextInt();
            }
        }

        // Compare playlists
        for (int i = 0; i < 3; i++) {
            int minInversions = Integer.MAX_VALUE, recommendUser = -1;
            for (int j = 0; j < 3; j++) {
                if (i == j)
                    continue;
                int[] mapped = mapOrder(playlists[i], playlists[j]);
                int inv = countInversions(mapped);
                if (inv < minInversions) {
                    minInversions = inv;
                    recommendUser = j + 1;
                }
            }
            System.out.println("User " + (i + 1) + " should be recommended playlist of User " + recommendUser);
        }
    }
}
