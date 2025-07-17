/*
 Initialize_Single_Source(G, s):
	For each vertex v∈G(V)
		vd = ∞
		vπ = nil
	Sd = 0

Relax(u,v,w):
	If vd > ud + w(u,v)
		vd = ud + w(u,v)
		vπ = u

BellmanFord(G, w, s):
	Initialize_Single_Source(G, s)
	For i=1 to |G(v)|-1
		For each edge (u,v) ∈G(E)
			Relax(u,v,w)
	For each edge(u,v) ∈ G(E)
		If vd > ud + w(u,v)
			Return FALSE
		Return True


 */

import java.util.*;

public class BellmanFord {

    static class Edge {
        int source, destination, weight;

        Edge(int source, int destination, int weight) {
            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }
    }

    public static void bellmanFord(List<Edge> edges, int V, int source) {
        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[source] = 0;

        // Relax all edges V - 1 times
        for (int i = 1; i < V; i++) {
            for (Edge edge : edges) {
                if (dist[edge.source] != Integer.MAX_VALUE &&
                        dist[edge.source] + edge.weight < dist[edge.destination]) {
                    dist[edge.destination] = dist[edge.source] + edge.weight;
                }
            }
        }

        // Check for negative-weight cycles
        for (Edge edge : edges) {
            if (dist[edge.source] != Integer.MAX_VALUE &&
                    dist[edge.source] + edge.weight < dist[edge.destination]) {
                System.out.println("Graph contains a negative-weight cycle");
                return;
            }
        }

        // Print distances
        System.out.println("Vertex\tDistance from Source");
        for (int i = 0; i < V; i++) {
            System.out.println(i + "\t" + (dist[i] == Integer.MAX_VALUE ? "INF" : dist[i]));
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter number of vertices: ");
        int V = scanner.nextInt();

        System.out.print("Enter number of edges: ");
        int E = scanner.nextInt();

        List<Edge> edges = new ArrayList<>();

        System.out.println("Enter each edge in format: source destination weight");
        for (int i = 0; i < E; i++) {
            int u = scanner.nextInt();
            int v = scanner.nextInt();
            int w = scanner.nextInt();
            edges.add(new Edge(u, v, w));
        }

        System.out.print("Enter source vertex: ");
        int source = scanner.nextInt();

        bellmanFord(edges, V, source);
        scanner.close();
    }
}
