package graphstruture;

// GRAPH //

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Graph {
    private final List<Node> nodes;

    public Graph() {
        this.nodes = new ArrayList<>();
    }

    public List<Node> getNodes() {
        return Collections.unmodifiableList(nodes);
    }

    public void addNode(Node node) {
        if (node == null) {
            throw new IllegalArgumentException("O nó não pode ser null.");
        }

        if (!nodes.contains(node)) {
            nodes.add(node);
        }
    }

    public void removeNode(Node node) {
        if (node == null) {
            return;
        }

        nodes.remove(node);

        for (Node current : nodes) {
            current.removeAdjacentNode(node);
        }
    }

    public boolean containsNode(Node node) {
        return nodes.contains(node);
    }

    public int size() {
        return nodes.size();
    }

    public Node get(int index) {
        return nodes.get(index);
    }

    public Node getNodeByValue(String value) {
        for (Node node : nodes) {
            if (node.getValue().equals(value)) {
                return node;
            }
        }

        return null;
    }

    public void addEdge(Node a, Node b) {
        if (a == null || b == null) {
            throw new IllegalArgumentException("Os nós não podem ser null.");
        }

        if (!nodes.contains(a) || !nodes.contains(b)) {
            throw new IllegalArgumentException("Os dois nós precisam estar no grafo.");
        }

        a.addAdjacentNode(b);
        b.addAdjacentNode(a);
    }

    public void removeEdge(Node a, Node b) {
        if (a == null || b == null) {
            return;
        }

        a.removeAdjacentNode(b);
        b.removeAdjacentNode(a);
    }

    public boolean isAdjacent(Node a, Node b) {
        if (a == null || b == null) {
            return false;
        }

        return a.isAdjacentTo(b);
    }

    public boolean isAdjacente(Node a, Node b) {
        return isAdjacent(a, b);
    }

    public int degree(Node node) {
        if (node == null || !nodes.contains(node)) {
            throw new IllegalArgumentException("O nó não pertence ao grafo.");
        }

        return node.degree();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (Node node : nodes) {
            sb.append(node.getValue()).append(" -> ");

            for (Node adjacentNode : node.getAdjacentNodes()) {
                sb.append(adjacentNode.getValue()).append(" ");
            }

            sb.append("\n");
        }

        return sb.toString();
    }
}