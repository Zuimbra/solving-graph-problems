import util.ConsoleColors;
import algorithms.BreadthFirstSearch;
import examples.GraphExamples;
import graphstruture.Graph;
import graphstruture.Node;

// Main.java

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Graph graph = GraphExamples.buildCitiesGraph();
        runTests(graph);
    }

    private static void runTests(Graph graph) {
        System.out.println("=========");
        testRoute(graph, "Manaus", "Porto Alegre");
        testRoute(graph, "Belém", "Goiânia");
        testRoute(graph, "Fortaleza", "RJ");
    }

    private static void testRoute(Graph graph, String origin, String destination) {
        BreadthFirstSearch bfs = new BreadthFirstSearch();
        List<Node> path = bfs.findPath(graph, origin, destination);

        System.out.println("Origem: " + ConsoleColors.PURPLE + origin + ConsoleColors.RESET);
        System.out.println("Destino: " + ConsoleColors.CYAN + destination + ConsoleColors.RESET);

        if (path == null) {
            System.out.println("Não existe rota.");
        } else {
            printPath(path);
            System.out.println("Número de paradas: " + (path.size() - 1));
        }

        System.out.println("=========");
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