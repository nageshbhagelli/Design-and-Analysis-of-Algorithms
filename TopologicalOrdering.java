/*
 * Suppose You are developing a task scheduler for a project management tool.
 * The project consists of several tasks, and each task may depend on other
 * tasks to be completed before it can start. The tasks and their dependencies
 * are represented as a directed acyclic graph (DAG). Your task is to write a
 * program that schedules the tasks in such a way that each task starts only
 * when all of its dependencies have been completed.
 */

// To compute a topological ordering of G:
// Find a node v with no incoming edges and order it first
// Delete v from G
// Recursively compute a topological ordering of G−{v} and append this order
// after v

import java.util.*;

public class TaskScheduler {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input number of tasks
        System.out.print("Enter number of tasks: ");
        int numTasks = sc.nextInt();
        sc.nextLine();

        String[] tasks = new String[numTasks];
        System.out.println("Enter task names:");
        for (int i = 0; i < numTasks; i++) tasks[i] = sc.nextLine().trim();

        // Initialize adjacency list (ArrayList of ArrayLists) and in-degree array
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>(numTasks);
        int[] inDegree = new int[numTasks];
        for (int i = 0; i < numTasks; i++) adj.add(new ArrayList<>());

        // Input dependencies
        System.out.print("Enter number of dependencies: ");
        int numDeps = sc.nextInt();
        sc.nextLine();

        System.out.println("Enter dependencies in format: TaskA TaskB");
        for (int i = 0; i < numDeps; i++) {
            String[] dep = sc.nextLine().split("\\s+");
            int from = getIndex(tasks, dep[0]), to = getIndex(tasks, dep[1]);
            adj.get(from).add(to);
            inDegree[to]++;
        }

        // Topological sort using Kahn's algorithm
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numTasks; i++) if (inDegree[i] == 0) queue.add(i);

        ArrayList<String> result = new ArrayList<>();
        while (!queue.isEmpty()) {
            int current = queue.poll();
            result.add(tasks[current]);
            for (int neighbor : adj.get(current)) {
                if (--inDegree[neighbor] == 0) queue.add(neighbor);
            }
        }

        // Output result or cycle detection
        if (result.size() != numTasks) System.out.println("Error: Cycle detected. No valid schedule.");
        else result.forEach(System.out::println);
    }
