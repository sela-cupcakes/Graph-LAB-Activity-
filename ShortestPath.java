import java.util.*;

public class ShortestPath {

    public static void main(String[] args) {

        double[][] graph = {
                {0, 4, 0, 0, 0, 0, 0, 8, 0},

                {4, 0, 8, 0, 0, 0, 0, 11, 0},

                {0, 8, 0, 7, 0, 4, 0, 0, 2},

                {0, 0, 7, 0, 9, 14, 0, 0, 0},

                {0, 0, 0, 9, 0, 10, 0, 0, 0},

                {0, 0, 4, 14, 10, 0, 2, 0, 0},

                {0, 0, 0, 0, 0, 2, 0, 1, 6},

                {8, 11, 0, 0, 0, 0, 1, 0, 7},

                {0, 0, 2, 0, 0, 0, 6, 7, 0}
        };

        int source = 0;

        int destination = 4;

        dijkstra(graph, source, destination);
    }

    public static void dijkstra(double[][] graph, int source, int destination) {

        int n = graph.length;

        double[] dist = new double[n];

        Arrays.fill(dist, Double.MAX_VALUE);

        dist[source] = 0;

        boolean[] visited = new boolean[n];

        int[] prev = new int[n]; //for path reconstruction

        for (int count = 0; count < n - 1; count++) {

            int u = minDistance(dist, visited);

            visited[u] = true;

            for (int v = 0; v < n; v++) {

                if (!visited[v] && graph[u][v] != 0 && dist[u] != Double.MAX_VALUE && dist[u] + graph[u][v] < dist[v]) {

                    dist[v] = dist[u] + graph[u][v];

                    prev[v] = u;
                }
            }
        }

        printPath(prev, source, destination);

        System.out.println("Shortest distance from " + source + " to " + destination + ": " + dist[destination]);
    }


    private static int minDistance(double[] dist, boolean[] visited) {

        double min = Double.MAX_VALUE;

        int minIndex = -1;

        for (int v = 0; v < dist.length; v++) {

            if (!visited[v] && dist[v] <= min) {

                min = dist[v];

                minIndex = v;
            }
        }
        return minIndex;
    }


    private static void printPath(int[] prev, int source, int dest){

        List<Integer> path = new ArrayList<>();

        int curr = dest;

        while(curr != source){

            path.add(0, curr);

            curr = prev[curr];
        }
        path.add(0, source);

        System.out.println("Shortest Path: " + path);
    }
}