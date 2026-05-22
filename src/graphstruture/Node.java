package graphstruture;

// NODE //

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Node {
    private final String value;
    private final List<Node> adjacentNodes;

    public Node(String value) {
        this.value = value;
        this.adjacentNodes = new ArrayList<>();
    }

    public String getValue() {
        return value;
    }

    public List<Node> getAdjacentNodes() {
        return Collections.unmodifiableList(adjacentNodes);
    }

    public List<Node> getAdjacentes() {
        return getAdjacentNodes();
    }

    public void addAdjacentNode(Node node) {
        if (node == null) {
            throw new IllegalArgumentException("O nó adjacente não pode ser null.");
        }

        if (node == this) {
            throw new IllegalArgumentException("Um nó não pode ser adjacente a ele mesmo.");
        }

        if (!adjacentNodes.contains(node)) {
            adjacentNodes.add(node);
        }
    }

    public void addAdjacente(Node node) {
        addAdjacentNode(node);
    }

    public void removeAdjacentNode(Node node) {
        adjacentNodes.remove(node);
    }

    public void removeAdjacente(Node node) {
        removeAdjacentNode(node);
    }

    public boolean isAdjacentTo(Node node) {
        return adjacentNodes.contains(node);
    }

    public boolean isAdjacente(Node node) {
        return isAdjacentTo(node);
    }

    public int degree() {
        return adjacentNodes.size();
    }

    public Node get(int index) {
        return adjacentNodes.get(index);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof Node)) {
            return false;
        }

        Node node = (Node) o;
        return Objects.equals(value, node.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}