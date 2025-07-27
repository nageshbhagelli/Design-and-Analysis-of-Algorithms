/*
 * Suppose say you are working for a company that is building a new office
 * complex. The company needs to install network cables to connect all the
 * office buildings in the most cost-effective way. The buildings are located in
 * a grid, and there are possible routes between pairs of buildings. Each route
 * has an associated cost based on the distance between buildings. Your task is
 * to help the company minimize the total cost of wiring the buildings together
 * using Prim’s Algorithm to find the Minimum Spanning Tree (MST) of the graph.
 */

import java.util.*;

public class PrimsAlgorithm {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of buildings: ");
        int n = sc.nextInt(); // number of nodes

        System.out.print("Enter number of possible routes: ");
        int e = sc.nextInt(); // number of edges

        List<List<int[]>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++)
            graph.add(new ArrayList<>());

        System.out.println("Enter each route (from to cost):");
        for (int i = 0; i < e; i++) {
            int u = sc.nextInt(), v = sc.nextInt(), w = sc.nextInt();
            graph.get(u).add(new int[] { v, w });
            graph.get(v).add(new int[] { u, w });
        }

        // Prim's Algorithm
        boolean[] visited = new boolean[n];
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        pq.add(new int[] { 0, 0 }); // {node, cost}
        int totalCost = 0;

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int node = cur[0], cost = cur[1];
            if (visited[node])
                continue;
            visited[node] = true;
            totalCost += cost;

            for (int[] neighbor : graph.get(node)) {
                if (!visited[neighbor[0]]) {
                    pq.add(new int[] { neighbor[0], neighbor[1] });
                }
            }
        }

        System.out.println("Minimum total cost to connect all buildings: " + totalCost);
    }
}
