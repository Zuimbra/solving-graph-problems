import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Node {
    private String value;
    private List<Node> adjacentes;

    public Node(String value) {
        this.value = value;
        this.adjacentes = new ArrayList<>();
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public List<Node> getAdjacentes() {
        return Collections.unmodifiableList(adjacentes);
    }

    public void addAdjacente(Node node) {
        if (node == null) {
            throw new IllegalArgumentException("O nó adjacente não pode ser null.");
        }

        if (node == this) {
            throw new IllegalArgumentException("Um nó não pode ser adjacente a ele mesmo.");
        }

        if (!adjacentes.contains(node)) {
            adjacentes.add(node);
        }
    }

    public void removeAdjacente(Node node) {
        adjacentes.remove(node);
    }

    public boolean isAdjacente(Node node) {
        return adjacentes.contains(node);
    }

    public int degree() {
        return adjacentes.size();
    }

    public Node get(int index) {
        return adjacentes.get(index);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Node)) return false;
        Node node = (Node) o;
        return Objects.equals(value, node.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}