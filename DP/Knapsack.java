import java.util.*;

public class Knapsack {

    static int[][] dp;

    // Memoized function to solve 0/1 Knapsack
    static int knapsack(int[] weights, int[] values, int n, int capacity) {
        if (n == 0 || capacity == 0)
            return 0;

        if (dp[n][capacity] != -1)
            return dp[n][capacity];

        if (weights[n - 1] <= capacity) {
            dp[n][capacity] = Math.max(
                    values[n - 1] + knapsack(weights, values, n - 1, capacity - weights[n - 1]),
                    knapsack(weights, values, n - 1, capacity));
        } else {
            dp[n][capacity] = knapsack(weights, values, n - 1, capacity);
        }

        return dp[n][capacity];
    }

    static void findSelectedItems(int[] weights, int[] values, int n, int capacity) {
        List<Integer> selectedItems = new ArrayList<>();
        int totalValue = dp[n][capacity];

        for (int i = n; i > 0 && totalValue > 0; i--) {
            if (dp[i][capacity] != dp[i - 1][capacity]) {
                selectedItems.add(i - 1); // index of item
                totalValue -= values[i - 1];
                capacity -= weights[i - 1];
            }
        }

        Collections.reverse(selectedItems);
        System.out.println("Selected item indices (0-based): " + selectedItems);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter backpack capacity (kg): ");
        int capacity = scanner.nextInt();

        System.out.print("Enter number of items: ");
        int n = scanner.nextInt();

        int[] weights = new int[n];
        int[] values = new int[n];

        System.out.println("Enter weight and value for each item:");
        for (int i = 0; i < n; i++) {
            System.out.print("Item " + i + " - Weight: ");
            weights[i] = scanner.nextInt();
            System.out.print("Item " + i + " - Value: ");
            values[i] = scanner.nextInt();
        }

        // Initialize dp array with -1
        dp = new int[n + 1][capacity + 1];
        for (int[] row : dp)
            Arrays.fill(row, -1);

        int maxValue = knapsack(weights, values, n, capacity);
        System.out.println("Maximum value Ravi can carry: " + maxValue);

        findSelectedItems(weights, values, n, capacity);

        scanner.close();
    }
}