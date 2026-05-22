import algorithms.DepthFirstSearch;
import examples.GraphExamples;
import graphstruture.Graph;

// TestRun.java

public class TestRun {
    public static void main(String[] args) throws InterruptedException {
        Graph graph1 = GraphExamples.buildGraph1();
        Graph graph2 = GraphExamples.buildGraph2();
        DepthFirstSearch dfs = new DepthFirstSearch();

        System.out.println("DFS no grafo 1:");
        dfs.dfsRecursive(graph1, graph1.getNodeByValue("0"), graph1.getNodeByValue("14"));

        System.out.println();

        System.out.println("DFS no grafo 2:");
        dfs.dfsIterative(graph2, graph2.getNodeByValue("0"), graph2.getNodeByValue("17"));
    }
}