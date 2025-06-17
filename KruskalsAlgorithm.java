import java.util.*;

public class KruskalsAlgorithm {

    static class Edge implements Comparable<Edge> {
        int u, v, cost;

        Edge(int u, int v, int cost) {
            this.u = u;
            this.v = v;
            this.cost = cost;
        }

        public int compareTo(Edge other) {
            return this.cost - other.cost;
        }
    }

    static int find(int[] parent, int x) {
        if (parent[x] != x)
            parent[x] = find(parent, parent[x]); // path compression
        return parent[x];
    }

    static void union(int[] parent, int[] rank, int x, int y) {
        int xr = find(parent, x), yr = find(parent, y);
        if (xr == yr)
            return;
        if (rank[xr] < rank[yr])
            parent[xr] = yr;
        else if (rank[xr] > rank[yr])
            parent[yr] = xr;
        else {
            parent[yr] = xr;
            rank[xr]++;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of cities: ");
        int n = sc.nextInt();

        System.out.print("Enter number of connections: ");
        int e = sc.nextInt();

        List<Edge> edges = new ArrayList<>();
        System.out.println("Enter each connection (city1 city2 cost):");
        for (int i = 0; i < e; i++) {
            int u = sc.nextInt(), v = sc.nextInt(), cost = sc.nextInt();
            edges.add(new Edge(u, v, cost));
        }

        Collections.sort(edges); // sort edges by cost

        int[] parent = new int[n];
        int[] rank = new int[n];
        for (int i = 0; i < n; i++)
            parent[i] = i;

        int totalCost = 0;
        List<Edge> mst = new ArrayList<>();

        for (Edge edge : edges) {
            if (find(parent, edge.u) != find(parent, edge.v)) {
                union(parent, rank, edge.u, edge.v);
                totalCost += edge.cost;
                mst.add(edge);
            }
        }

        System.out.println("Edges in the Minimum Spanning Tree:");
        for (Edge edge : mst) {
            System.out.println(edge.u + " - " + edge.v + " : " + edge.cost);
        }

        System.out.println("Total minimum cost: " + totalCost);
    }
}
