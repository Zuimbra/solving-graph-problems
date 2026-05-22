import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class DepthFirstSearch {

    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String GREY = "\u001B[90m";

    private Map<Node, Boolean> visited;
    private Map<Node, EstadoNo> estados;

    private enum EstadoNo {
        NAO_VISITADO,
        VISITADO,
        EXPLORADO
    }

    public DepthFirstSearch() {
        visited = new HashMap<>();
        estados = new HashMap<>();
    }

    public void dfs(Graph graph, Node start, Node end, String type) throws InterruptedException {
        visited.clear();
        estados.clear();

        for (Node node : graph.getNodes()) {
            visited.put(node, false);
            estados.put(node, EstadoNo.NAO_VISITADO);
        }

        if ("recursive".equalsIgnoreCase(type)) {
            Stack<Node> path = new Stack<>();

            boolean found = dfsRecursive(start, end, 0, path);

            if (!found) {
                System.out.println("\n" + GREY + "SEM CAMINHO" + RESET);
            }

        } else if ("iterative".equalsIgnoreCase(type)) {
            dfsIterative(graph, start, end);

        } else {
            System.out.println(RED + "Tipo inválido. Use 'recursive' ou 'iterative'." + RESET);
        }
    }

    private boolean dfsRecursive(Node current, Node end, int depth, Stack<Node> path) throws InterruptedException {
        visited.put(current, true);
        path.push(current);

        Thread.sleep(100);
        System.out.println("-> Visitando nó: " + GREEN + current.getValue() + RESET);
        System.out.println(GREY + "   \\-Distância no caminho: " + depth + RESET);

        if (current == end) {
            printPath(path);
            return true;
        }

        for (Node v : current.getAdjacentes()) {
            if (!visited.get(v)) {
                boolean found = dfsRecursive(v, end, depth + 1, path);

                if (found) {
                    return true;
                }
            }
        }

        path.pop();
        return false;
    }

    private void dfsIterative(Graph graph, Node start, Node end) throws InterruptedException {
        Stack<Node> stack = new Stack<>();

        Map<Node, Node> parentMap = new HashMap<>();
        Map<Node, Integer> depthMap = new HashMap<>();

        stack.push(start);
        depthMap.put(start, 0);
        parentMap.put(start, null);

        while (!stack.isEmpty()) {
            Node current = stack.peek();

            if (estados.get(current) == EstadoNo.NAO_VISITADO) {
                estados.put(current, EstadoNo.VISITADO);

                int depth = depthMap.get(current);

                Thread.sleep(100);
                System.out.println("-> Visitando nó: " + GREEN + current.getValue() + RESET);
                System.out.println(GREY + "   \\-Distância no caminho: " + depth + RESET);

                if (current == end) {
                    printPath(parentMap, end);
                    return;
                }

                List<Node> adjacentes = current.getAdjacentes();

                for (int i = adjacentes.size() - 1; i >= 0; i--) {
                    Node v = adjacentes.get(i);

                    if (estados.get(v) == EstadoNo.NAO_VISITADO && !parentMap.containsKey(v)) {
                        stack.push(v);
                        parentMap.put(v, current);
                        depthMap.put(v, depth + 1);
                    }
                }

            } else if (estados.get(current) == EstadoNo.VISITADO) {
                estados.put(current, EstadoNo.EXPLORADO);
                stack.pop();

            } else {
                stack.pop();
            }
        }

        System.out.println("\n" + GREY + "SEM CAMINHO" + RESET);
    }

    private void printPath(Stack<Node> path) {
        System.out.print("Path: ");

        for (int i = 0; i < path.size(); i++) {
            Node node = path.get(i);

            System.out.print(GREEN + node.getValue() + RESET);

            if (i < path.size() - 1) {
                System.out.print(" > ");
            }
        }

        System.out.println();
    }

    private void printPath(Map<Node, Node> parentMap, Node end) {
        Stack<Node> path = new Stack<>();

        Node current = end;

        while (current != null) {
            path.push(current);
            current = parentMap.get(current);
        }

        System.out.print("Path: ");

        while (!path.isEmpty()) {
            Node node = path.pop();

            System.out.print(GREEN + node.getValue() + RESET);

            if (!path.isEmpty()) {
                System.out.print(" > ");
            }
        }

        System.out.println();
    }
}