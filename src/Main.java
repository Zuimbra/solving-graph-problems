import graphStructure.Graph;
import graphStructure.Node;
import algorithms.DepthFirstSearch;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Graph graph = buildGraph();
        runTests(graph);
    }

    private static Graph buildGraph() {
        Graph graph = new Graph();

        addCities(graph);
        addConnections(graph);

        return graph;
    }

    private static void addCities(Graph graph) {
        graph.addNode(new Node("Manaus"));
        graph.addNode(new Node("Belém"));
        graph.addNode(new Node("Fortaleza"));
        graph.addNode(new Node("Recife"));
        graph.addNode(new Node("Salvador"));
        graph.addNode(new Node("BH"));
        graph.addNode(new Node("Brasília"));
        graph.addNode(new Node("RJ"));
        graph.addNode(new Node("SP"));
        graph.addNode(new Node("Goiânia"));
        graph.addNode(new Node("Curitiba"));
        graph.addNode(new Node("Porto Alegre"));
    }

    private static void addConnections(Graph graph) {
        connect(graph, "Manaus", "Belém");
        connect(graph, "Manaus", "Fortaleza");

        connect(graph, "Belém", "Recife");
        connect(graph, "Belém", "Fortaleza");

        connect(graph, "Fortaleza", "Recife");
        connect(graph, "Fortaleza", "Salvador");

        connect(graph, "Recife", "Salvador");
        connect(graph, "Recife", "BH");

        connect(graph, "Salvador", "BH");
        connect(graph, "Salvador", "Brasília");

        connect(graph, "BH", "RJ");
        connect(graph, "BH", "Brasília");
        connect(graph, "BH", "SP");

        connect(graph, "Brasília", "SP");
        connect(graph, "Brasília", "Goiânia");

        connect(graph, "RJ", "SP");

        connect(graph, "SP", "Curitiba");
        connect(graph, "SP", "Goiânia");

        connect(graph, "Goiânia", "Curitiba");

        connect(graph, "Curitiba", "Porto Alegre");
    }

    private static void connect(Graph graph, String city1, String city2) {
        Node node1 = graph.getNodeByValue(city1);
        Node node2 = graph.getNodeByValue(city2);
        graph.addEdge(node1, node2);
    }

    private static void runTests(Graph graph) {
        System.out.println("=========");;
        testRoute(graph, "Manaus", "Porto Alegre");
        testRoute(graph, "Belém", "Goiânia");
        testRoute(graph, "Fortaleza", "RJ");
    }

    private static void testRoute(Graph graph, String origin, String destination) {

        String PURPLE = "\u001B[35m";
        String PINK = "\u001B[36m";
        String RESET = "\u001B[0m";

        List<Node> path = bfs(graph, origin, destination);

        System.out.println("Origem: " + PURPLE + origin + RESET);
        System.out.println("Destino: " + PINK + destination + RESET);

        if (path == null) {
            System.out.println("Não existe rota.");
        } else {
            printPath(path);
            System.out.println("Número de paradas: " + (path.size() - 1));
        }

        System.out.println("=========");
    }

    private static List<Node> bfs(Graph graph, String origin, String destination) {
    Node start = graph.getNodeByValue(origin);
    Node end = graph.getNodeByValue(destination);

    if (start == null || end == null) {
        return null;
    }

    Queue<Node> queue = new LinkedList<>();
    List<Node> visited = new ArrayList<>();
    Map<Node, Node> previous = new HashMap<>();

    queue.add(start);
    visited.add(start);

    while (!queue.isEmpty()) {
        Node current = queue.poll();

        if (current.equals(end)) {
            return buildPath(previous, end);
        }

        for (Node neighbor : current.getAdjacentes()) {
            if (!visited.contains(neighbor)) {
                visited.add(neighbor);
                previous.put(neighbor, current);
                queue.add(neighbor);
            }
        }
    }

    return null;
}

private static List<Node> buildPath(Map<Node, Node> previous, Node end) {
    List<Node> path = new ArrayList<>();

    while (end != null) {
        path.add(end);
        end = previous.get(end);
    }

    Collections.reverse(path);
    return path;
}
    private static void printPath(List<Node> path) {
        System.out.print("Caminho: ");

        for (int i = 0; i < path.size(); i++) {
            System.out.print(path.get(i).getValue());

            if (i < path.size() - 1) {
                System.out.print(" -> ");
            }
        }

        System.out.println();
    }
}
