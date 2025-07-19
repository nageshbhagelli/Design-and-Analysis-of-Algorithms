// BFS(s):
// Mark s as "Visited".
// Initialize R={s}.
// Define layer L0={s}.
// While Li is not empty
// For each node u∈Li
// Consider each edge (u,v) incident to u
// If v is not marked "Visited" then
// Mark v "Visited"
// Add v to the set R and to layer Li+1
// Endif
// Endfor
// Endwhile

import java.util.*;

public class BFSAlgorithm {
    private Map<Integer, List<Integer>> adjList = new HashMap<>();

    public void addEdge(int u, int v) {
        adjList.putIfAbsent(u, new ArrayList<>());
        adjList.putIfAbsent(v, new ArrayList<>()); // for undirected graph
        adjList.get(u).add(v);
        // If it's undirected, uncomment the line below
        // adjList.get(v).add(u);
    }

    // BFS traversal
    public void bfs(int start) {
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();

        queue.add(start);
        visited.add(start);

        System.out.print("BFS traversal starting from node " + start + ": ");

        while (!queue.isEmpty()) {
            int node = queue.poll();
            System.out.print(node + " ");

            for (int neighbor : adjList.getOrDefault(node, new ArrayList<>())) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BFSAlgorithm graph = new BFSAlgorithm();

        System.out.print("Enter number of edges: ");
        int edges = sc.nextInt();

        System.out.println("Enter edges (u v) one per line:");
        for (int i = 0; i < edges; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            graph.addEdge(u, v);
        }

        System.out.print("Enter starting node for BFS: ");
        int start = sc.nextInt();

        graph.bfs(start);
        sc.close();
    }
}
