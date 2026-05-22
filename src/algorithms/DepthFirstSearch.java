package algorithms;

import util.ConsoleColors;

// DepthFirstSearch.java

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import graphstruture.Graph;
import graphstruture.Node;

public class DepthFirstSearch {
    private final Map<Node, Boolean> visited;
    private final Map<Node, NodeState> states;

    private enum NodeState {
        NOT_VISITED,
        VISITED,
        EXPLORED
    }

    public DepthFirstSearch() {
        this.visited = new HashMap<>();
        this.states = new HashMap<>();
    }

    public void dfsRecursive(Graph graph, Node start, Node end) throws InterruptedException {
        initializeSearchState(graph);

        Stack<Node> path = new Stack<>();
        boolean found = searchRecursive(start, end, 0, path);

        if (!found) {
            printNoPathMessage();
        }
    }

    public void dfsIterative(Graph graph, Node start, Node end) throws InterruptedException {
        initializeSearchState(graph);
        searchIterative(start, end);
    }

    private void initializeSearchState(Graph graph) {
        visited.clear();
        states.clear();

        for (Node node : graph.getNodes()) {
            visited.put(node, false);
            states.put(node, NodeState.NOT_VISITED);
        }
    }

    private boolean searchRecursive(Node current, Node end, int depth, Stack<Node> path) throws InterruptedException {
        visited.put(current, true);
        path.push(current);

        waitStep();
        printVisitedNode(current, depth);

        if (current == end) {
            printPath(path);
            return true;
        }

        for (Node neighbor : current.getAdjacentNodes()) {
            if (!visited.get(neighbor)) {
                boolean found = searchRecursive(neighbor, end, depth + 1, path);

                if (found) {
                    return true;
                }
            }
        }

        path.pop();
        return false;
    }

    private void searchIterative(Node start, Node end) throws InterruptedException {
        Stack<Node> stack = new Stack<>();
        Map<Node, Node> parentMap = new HashMap<>();
        Map<Node, Integer> depthMap = new HashMap<>();

        stack.push(start);
        depthMap.put(start, 0);
        parentMap.put(start, null);

        while (!stack.isEmpty()) {
            Node current = stack.peek();

            if (states.get(current) == NodeState.NOT_VISITED) {
                visitIterativeNode(current, end, stack, parentMap, depthMap);

                if (current == end) {
                    return;
                }
            } else if (states.get(current) == NodeState.VISITED) {
                states.put(current, NodeState.EXPLORED);
                stack.pop();
            } else {
                stack.pop();
            }
        }

        printNoPathMessage();
    }

    private void visitIterativeNode(
        Node current,
        Node end,
        Stack<Node> stack,
        Map<Node, Node> parentMap,
        Map<Node, Integer> depthMap
    ) throws InterruptedException {
        states.put(current, NodeState.VISITED);

        int depth = depthMap.get(current);

        waitStep();
        printVisitedNode(current, depth);

        if (current == end) {
            printPath(parentMap, end);
            return;
        }

        addUnvisitedNeighborsToStack(current, stack, parentMap, depthMap, depth);
    }

    private void addUnvisitedNeighborsToStack(
        Node current,
        Stack<Node> stack,
        Map<Node, Node> parentMap,
        Map<Node, Integer> depthMap,
        int depth
    ) {
        List<Node> adjacentNodes = current.getAdjacentNodes();

        for (int i = adjacentNodes.size() - 1; i >= 0; i--) {
            Node neighbor = adjacentNodes.get(i);

            if (states.get(neighbor) == NodeState.NOT_VISITED && !parentMap.containsKey(neighbor)) {
                stack.push(neighbor);
                parentMap.put(neighbor, current);
                depthMap.put(neighbor, depth + 1);
            }
        }
    }

    private void waitStep() throws InterruptedException {
        Thread.sleep(100);
    }

    private void printVisitedNode(Node node, int depth) {
        System.out.println("-> Visitando nó: " + ConsoleColors.GREEN + node.getValue() + ConsoleColors.RESET);
        System.out.println(ConsoleColors.GREY + " \\-Distância no caminho: " + depth + ConsoleColors.RESET);
    }

    private void printNoPathMessage() {
        System.out.println("\n" + ConsoleColors.GREY + "SEM CAMINHO" + ConsoleColors.RESET);
    }

    private void printPath(Stack<Node> path) {
        System.out.print("Path: ");

        for (int i = 0; i < path.size(); i++) {
            Node node = path.get(i);
            System.out.print(ConsoleColors.GREEN + node.getValue() + ConsoleColors.RESET);

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
            System.out.print(ConsoleColors.GREEN + node.getValue() + ConsoleColors.RESET);

            if (!path.isEmpty()) {
                System.out.print(" > ");
            }
        }

        System.out.println();
    }
}
