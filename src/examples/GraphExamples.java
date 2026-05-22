package examples;

import graphstruture.Graph;
import graphstruture.Node;

public class GraphExamples {
    private GraphExamples() {
    }

    public static Graph buildCitiesGraph() {
        Graph graph = new Graph();

        addCities(graph);
        addCityConnections(graph);

        return graph;
    }

    public static Graph buildGraph1() {
        int[][] adjacencyList = {
            {1, 2, 3},
            {0, 4, 5},
            {0, 5, 6},
            {0, 6, 7},
            {1, 8},
            {1, 2, 9},
            {2, 3, 9, 10},
            {3, 10},
            {4, 11},
            {5, 6, 11, 12},
            {6, 7, 12, 13},
            {8, 9, 14},
            {9, 10, 14},
            {10, 14},
            {11, 12, 13}
        };

        return buildGraphFromAdjacencyList(adjacencyList);
    }

    public static Graph buildGraph2() {
        int[][] adjacencyList = {
            {1, 2},
            {0, 3, 4},
            {0, 4, 5},
            {1, 6},
            {1, 2, 7, 8},
            {2, 8, 9},
            {3, 10},
            {4, 10, 11},
            {4, 5, 11, 12},
            {5, 12},
            {6, 7, 13},
            {7, 8, 13, 14},
            {8, 9, 14, 15},
            {10, 11, 16},
            {11, 12, 16, 17},
            {12, 17},
            {13, 14, 17},
            {14, 15, 16}
        };

        return buildGraphFromAdjacencyList(adjacencyList);
    }

    public static Graph buildGraphFromAdjacencyList(int[][] adjacencyList) {
        Graph graph = new Graph();
        Node[] nodes = new Node[adjacencyList.length];

        for (int i = 0; i < adjacencyList.length; i++) {
            nodes[i] = new Node(String.valueOf(i));
            graph.addNode(nodes[i]);
        }

        for (int i = 0; i < adjacencyList.length; i++) {
            for (int destination : adjacencyList[i]) {
                if (i < destination) {
                    graph.addEdge(nodes[i], nodes[destination]);
                }
            }
        }

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

    private static void addCityConnections(Graph graph) {
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
}
