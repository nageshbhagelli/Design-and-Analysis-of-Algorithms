import java.util.Scanner;

public class RotatedArraySearch {
    // Modified binary search for rotated sorted array
    public static int search(int[] arr, int left, int right, int key) {
        if (left > right)
            return -1;

        int mid = left + (right - left) / 2;

        // Key found
        if (arr[mid] == key)
            return mid;

        // If left half is sorted
        if (arr[left] <= arr[mid]) {
            // Check if key lies in this half
            if (key >= arr[left] && key < arr[mid])
                return search(arr, left, mid - 1, key);
            else
                return search(arr, mid + 1, right, key);
        }
        // Else, right half must be sorted
        else {
            if (key > arr[mid] && key <= arr[right])
                return search(arr, mid + 1, right, key);
            else
                return search(arr, left, mid - 1, key);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input array size
        System.out.print("Enter the number of elements in the array: ");
        int n = scanner.nextInt();
        int[] arr = new int[n];

        // Input array elements (should be sorted then rotated)
        System.out.println("Enter " + n + " integers (array elements, sorted then rotated):");
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }

        // Input the value to search for
        System.out.print("Enter the number to search for: ");
        int target = scanner.nextInt();

        // Perform search
        int index = search(arr, 0, n - 1, target);

        // Output the result
        if (index != -1) {
            System.out.println(target + " found at index: " + index);
        } else {
            System.out.println(target + " not found in the array.");
        }

        scanner.close();
    }
}
