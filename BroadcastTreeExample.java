import java.util.ArrayList;
import java.util.List;

class Host {
    String name;
    List<Host> neighbors;

    public Host(String name) {
        this.name = name;
        this.neighbors = new ArrayList<>();
    }

    public void addNeighbor(Host neighbor) {
        neighbors.add(neighbor);
        neighbor.neighbors.add(this); // Assuming an undirected connection
    }

    public void printBroadcastTree(int depth) {
        StringBuilder indent = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            indent.append("   ");
        }

        System.out.println(indent.toString() + name);

        for (Host neighbor : neighbors) {
            neighbor.printBroadcastTree(depth + 1);
        }
    }
}

public class BroadcastTreeExample {
    public static void main(String[] args) {
        // Create hosts
        Host A = new Host("A");
        Host B = new Host("B");
        Host C = new Host("C");
        Host D = new Host("D");
        Host E = new Host("E");

        // Connect hosts to form the subnet
        A.addNeighbor(B);
        A.addNeighbor(C);
        A.addNeighbor(D);
        B.addNeighbor(E);
        D.addNeighbor(E);

        // Print the broadcast tree
        System.out.println("Broadcast Tree:");
        A.printBroadcastTree(0);
    }
}
