// DFS(u):
// Mark u as "Visited" and add u to R.
// For each edge (u,v) incident to u
// If v is not marked "Visited" then
// Add v to R.
// Recursively invoke DFS(v).
// Endif
// Endfor

import java.util.*;

public class DFS {
    private Map<Integer, List<Integer>> adjList = new HashMap<>();

    // Add edge to the graph
    public void addEdge(int u, int v) {
        adjList.putIfAbsent(u, new ArrayList<>());
        adjList.putIfAbsent(v, new ArrayList<>()); // for undirected graph
        adjList.get(u).add(v);
        // Uncomment the next line for undirected graph
        // adjList.get(v).add(u);
    }

    // DFS traversal
    public void dfs(int start) {
        Set<Integer> visited = new HashSet<>();
        System.out.print("DFS traversal starting from node " + start + ": ");
        dfsUtil(start, visited);
    }

    // Recursive DFS helper
    private void dfsUtil(int node, Set<Integer> visited) {
        visited.add(node);
        System.out.print(node + " ");

        for (int neighbor : adjList.getOrDefault(node, new ArrayList<>())) {
            if (!visited.contains(neighbor)) {
                dfsUtil(neighbor, visited);
            }
        }
    }

    // Main method
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        DFSGraph graph = new DFSGraph();

        System.out.print("Enter number of edges: ");
        int edges = sc.nextInt();

        System.out.println("Enter edges (u v) one per line:");
        for (int i = 0; i < edges; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            graph.addEdge(u, v);
        }

        System.out.print("Enter starting node for DFS: ");
        int start = sc.nextInt();

        graph.dfs(start);
        sc.close();
    }
}
