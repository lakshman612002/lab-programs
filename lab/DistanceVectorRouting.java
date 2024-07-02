import java.util.Arrays;

public class DistanceVectorRouting {

    public static void main(String[] args) {
        int[][] graph = {
            {0, 2, Integer.MAX_VALUE, 3, Integer.MAX_VALUE},
            {2, 0, 1, Integer.MAX_VALUE, 1},
            {Integer.MAX_VALUE, 1, 0, Integer.MAX_VALUE, Integer.MAX_VALUE},
            {3, Integer.MAX_VALUE, Integer.MAX_VALUE, 0, Integer.MAX_VALUE},
            {Integer.MAX_VALUE, 1, Integer.MAX_VALUE, Integer.MAX_VALUE, 0}
        };

        distanceVectorRouting(graph);
    }

    private static void distanceVectorRouting(int[][] graph) {
        int numNodes = graph.length;

        int[][] distanceTable = new int[numNodes][numNodes];
        for (int i = 0; i < numNodes; i++) {
            System.arraycopy(graph[i], 0, distanceTable[i], 0, numNodes);
        }

        boolean updated;

        do {
            updated = false;

            for (int i = 0; i < numNodes; i++) {
                for (int j = 0; j < numNodes; j++) {
                    if (i != j) {
                        for (int k = 0; k < numNodes; k++) {
                            if (distanceTable[i][k] != Integer.MAX_VALUE &&
                                distanceTable[k][j] != Integer.MAX_VALUE) {
                                int newDistance = distanceTable[i][k] + distanceTable[k][j];
                                if (newDistance < distanceTable[i][j]) {
                                    distanceTable[i][j] = newDistance;
                                    updated = true;
                                }
                            }
                        }
                    }
                }
            }
        } while (updated);

        // Display the routing tables
        for (int i = 0; i < numNodes; i++) {
            System.out.println("Routing Table for Node " + i + ":");
            System.out.println("Destination\tNext Hop\tDistance");
            for (int j = 0; j < numNodes; j++) {
                if (i != j && distanceTable[i][j] != Integer.MAX_VALUE) {
                    System.out.println(j + "\t\t\t" + nextHop(distanceTable, i, j) + "\t\t\t" + distanceTable[i][j]);
                }
            }
            System.out.println();
        }
    }

    private static int nextHop(int[][] distanceTable, int source, int destination) {
        int minDistance = distanceTable[source][destination];
        int nextHop = destination;

        for (int i = 0; i < distanceTable.length; i++) {
            if (i != destination && distanceTable[source][i] != Integer.MAX_VALUE) {
                int totalDistance = distanceTable[i][destination] + distanceTable[source][i];
                if (totalDistance == minDistance) {
                    // If there are multiple paths with the same cost, choose the shortest hop count
                    int hopsToI = distanceTable[source][i];
                    int hopsToNextHop = distanceTable[i][destination];
                    if (hopsToI + hopsToNextHop < distanceTable[source][nextHop] + distanceTable[nextHop][destination]) {
                        nextHop = i;
                    }
                }
            }
        }

        return nextHop;
    }
}
