import java.util.*;

public class SubsetSum {
    static Boolean[][] memo;

    // Memoized subset sum check
    static boolean isSubsetSum(int[] transactions, int n, int target) {
        if (target == 0)
            return true;
        if (n == 0)
            return false;
        if (memo[n][target] != null)
            return memo[n][target];
        if (transactions[n - 1] > target)
            memo[n][target] = isSubsetSum(transactions, n - 1, target);
        else
            memo[n][target] = isSubsetSum(transactions, n - 1, target) ||
                    isSubsetSum(transactions, n - 1, target - transactions[n - 1]);

        return memo[n][target];
    }

    // Function to print the subset that sums to the target
    static List<Integer> getSubset(int[] transactions, int n, int target) {
        List<Integer> subset = new ArrayList<>();
        while (n > 0 && target > 0) {
            if (memo[n][target] != null && memo[n][target]
                    && (memo[n - 1][target] == null || !memo[n - 1][target])) {
                subset.add(transactions[n - 1]);
                target -= transactions[n - 1];
            }
            n--;
        }
        Collections.reverse(subset);
        return subset;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // Input transactions
        System.out.print("Enter number of transactions: ");
        int n = scanner.nextInt();
        int[] transactions = new int[n];
        System.out.println("Enter transaction amounts:");
        for (int i = 0; i < n; i++) {
            transactions[i] = scanner.nextInt();
        }
        // Input suspicious target
        System.out.print("Enter suspicious target amount: ");
        int target = scanner.nextInt();
        // Initialize memo table
        memo = new Boolean[n + 1][target + 1];
        boolean found = isSubsetSum(transactions, n, target);
        if (found) {
            System.out.println("Yes, suspicious subset found.");
            List<Integer> subset = getSubset(transactions, n, target);
            System.out.println("Transactions that sum to suspicious amount:");
            System.out.println(subset);
        } else {
            System.out.println("No suspicious subset found.");
        }

        scanner.close();
    }
}
