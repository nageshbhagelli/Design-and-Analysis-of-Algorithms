import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class TravellingSalesmanProblem {
    static final int INF = 1000000000;
    static int n;
    static int[][] dist;
    static int[][] dp;
    static int[][] parent;

    public static int tsp(int mask, int pos) {
        if (mask == (1 << n) - 1) {
            return dist[pos][0]; // Return to start
        }

        if (dp[mask][pos] != -1) {
            return dp[mask][pos];
        }

        int minCost = INF;
        int bestCity = -1;

        for (int city = 0; city < n; city++) {
            if ((mask & (1 << city)) == 0) {
                int newCost = dist[pos][city] + tsp(mask | (1 << city), city);
                if (newCost < minCost) {
                    minCost = newCost;
                    bestCity = city;
                }
            }
        }

        parent[mask][pos] = bestCity;
        dp[mask][pos] = minCost;
        return minCost;
    }

    public static List<Integer> getPath() {
        List<Integer> path = new ArrayList<>();
        int mask = 1;
        int pos = 0;
        path.add(0);

        while (true) {
            int next = parent[mask][pos];
            if (next == -1)
                break;
            path.add(next);
            mask |= (1 << next);
            pos = next;
        }

        path.add(0); // Return to starting city
        return path;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of cities: ");
        n = sc.nextInt();

        dist = new int[n][n];
        System.out.println("Enter the distance matrix:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dist[i][j] = sc.nextInt();
            }
        }

        dp = new int[1 << n][n];
        parent = new int[1 << n][n];

        for (int[] row : dp)
            Arrays.fill(row, -1);
        for (int[] row : parent)
            Arrays.fill(row, -1);

        int minCost = tsp(1, 0); // Starting from city 0
        List<Integer> path = getPath();

        System.out.println("\nMinimum cost: " + minCost);
        System.out.print("Path: ");
        for (int city : path) {
            System.out.print(city + " ");
        }
        System.out.println();
    }

}
