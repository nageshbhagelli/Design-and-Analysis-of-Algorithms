import java.util.*;

public class DijkstraAlgorithm {

    static class Edge {
        int to, weight;

        Edge(int t, int w) {
            to = t;
            weight = w;
        }
    }

    public static void dijkstra(List<List<Edge>> graph, int source) {
        int n = graph.size();
        int[] dist = new int[n];
        boolean[] visited = new boolean[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[source] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        pq.add(new int[] { source, 0 });

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int node = curr[0], currentDist = curr[1];
            if (visited[node])
                continue;
            visited[node] = true;

            for (Edge edge : graph.get(node)) {
                int neighbor = edge.to, weight = edge.weight;
                if (dist[neighbor] > currentDist + weight) {
                    dist[neighbor] = currentDist + weight;
                    pq.add(new int[] { neighbor, dist[neighbor] });
                }
            }
        }

        // Output shortest paths from source
        System.out.println("Shortest distances from location " + source + ":");
        for (int i = 0; i < n; i++) {
            System.out.println("To location " + i + " -> " + dist[i] + " units");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input number of locations (nodes) and paths (edges)
        System.out.print("Enter number of locations: ");
        int n = sc.nextInt();
        System.out.print("Enter number of roads (connections): ");
        int e = sc.nextInt();

        List<List<Edge>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++)
            graph.add(new ArrayList<>());

        System.out.println("Enter the roads in format: from to distance");
        for (int i = 0; i < e; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int w = sc.nextInt();
            graph.get(u).add(new Edge(v, w));
            graph.get(v).add(new Edge(u, w)); // if undirected
        }

        System.out.print("Enter the source location: ");
        int source = sc.nextInt();

        dijkstra(graph, source);
    }
}
