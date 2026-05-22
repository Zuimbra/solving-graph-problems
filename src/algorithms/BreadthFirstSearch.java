package algorithms;

// BreadthFirstSearch.java

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import graphstruture.Graph;
import graphstruture.Node;

public class BreadthFirstSearch {
    public List<Node> findPath(Graph graph, String origin, String destination) {
        Node start = graph.getNodeByValue(origin);
        Node end = graph.getNodeByValue(destination);

        if (start == null || end == null) {
            return null;
        }

        return findPath(start, end);
    }

    public List<Node> findPath(Node start, Node end) {
        Queue<Node> queue = new LinkedList<>();
        Set<Node> visited = new HashSet<>();
        Map<Node, Node> previous = new HashMap<>();

        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            Node current = queue.poll();

            if (current.equals(end)) {
                return buildPath(previous, end);
            }

            for (Node neighbor : current.getAdjacentNodes()) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    previous.put(neighbor, current);
                    queue.add(neighbor);
                }
            }
        }

        return null;
    }

    private List<Node> buildPath(Map<Node, Node> previous, Node end) {
        List<Node> path = new ArrayList<>();
        Node current = end;

        while (current != null) {
            path.add(current);
            current = previous.get(current);
        }

        Collections.reverse(path);
        return path;
    }
}
