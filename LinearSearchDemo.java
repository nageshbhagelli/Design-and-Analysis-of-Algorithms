import java.util.Scanner;

public class LinearSearchDemo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input array size
        System.out.print("Enter the number of elements in the array: ");
        int n = scanner.nextInt();
        int[] arr = new int[n];

        // Input array elements
        System.out.println("Enter " + n + " integers (array elements):");
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }

        // Input the value to search for
        System.out.print("Enter the number to search for: ");
        int target = scanner.nextInt();

        // Perform linear search
        int result = linearSearch(arr, target);

        // Output the result
        if (result == -1) {
            System.out.println(target + " not found in the array.");
        } else {
            System.out.println(target + " found at index: " + result);
        }

        scanner.close();
    }

    // Linear search method
    public static int linearSearch(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                return i; // Return index if found
            }
        }
        return -1;
    }
}
