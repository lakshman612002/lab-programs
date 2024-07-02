import java.util.Arrays;

public class HierarchicalRouting {

    public static void main(String[] args) {
        int[][] networkGraph = {
            {0, 2, 0, 0, 0, 0, 0},
            {2, 0, 1, 0, 0, 0, 0},
            {0, 1, 0, 1, 0, 0, 0},
            {0, 0, 1, 0, 3, 0, 0},
            {0, 0, 0, 3, 0, 1, 0},
            {0, 0, 0, 0, 1, 0, 2},
            {0, 0, 0, 0, 0, 2, 0}
        };

        int[] domainDivision = {3, 7}; // Assuming nodes 1 to 3 are in Domain 1, 4 to 7 are in Domain 2

        hierarchicalRouting(networkGraph, domainDivision);
    }

    private static void hierarchicalRouting(int[][] networkGraph, int[] domainDivision) {
        int numNodes = networkGraph.length;

        // Initialize routing tables for each domain
        int[] routingTableDomain1 = new int[domainDivision[0]];
        int[] routingTableDomain2 = new int[domainDivision[1]];

        // Run intra-domain routing algorithms
        distanceVectorRouting(Arrays.copyOfRange(networkGraph, 0, domainDivision[0]), routingTableDomain1);
        distanceVectorRouting(Arrays.copyOfRange(networkGraph, domainDivision[0], numNodes), routingTableDomain2);

        // Display routing tables
        System.out.println("Routing Table for Domain 1:");
        displayRoutingTable(routingTableDomain1);

        System.out.println("\nRouting Table for Domain 2:");
        displayRoutingTable(routingTableDomain2);
    }

    private static void distanceVectorRouting(int[][] graph, int[] routingTable) {
        int numNodes = graph.length;

        // Initialize routing table to store distances to other nodes within the domain
        int[][] distanceTable = new int[numNodes][numNodes];
        for (int i = 0; i < numNodes; i++) {
            System.arraycopy(graph[i], 0, distanceTable[i], 0, numNodes);
        }

        // Apply distance vector routing algorithm (similar to the previous example)
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

        // Populate the routing table with the shortest distances
        for (int i = 0; i < numNodes; i++) {
            int minDistance = Integer.MAX_VALUE;
            for (int j = 0; j < numNodes; j++) {
                if (i != j && distanceTable[i][j] < minDistance) {
                    minDistance = distanceTable[i][j];
                    routingTable[i] = j; // Next hop for node i
                }
            }
        }
    }

    private static void displayRoutingTable(int[] routingTable) {
        System.out.println("Destination\tNext Hop");
        for (int i = 0; i < routingTable.length; i++) {
            System.out.println(i + "\t\t\t" + routingTable[i]);
        }
    }
}
