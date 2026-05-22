public class TestRun {

    public static void main(String[] args) throws InterruptedException {
        Graph graph1 = buildGraph1();
        Graph graph2 = buildGraph2();

        DepthFirstSearch dfs = new DepthFirstSearch();

        System.out.println("DFS no grafo 1:");
        dfs.dfs(graph1, graph1.getNodeByValue("0"), graph1.getNodeByValue("14"), "recursive");

        System.out.println();

        System.out.println("DFS no grafo 2:");
        dfs.dfs(graph2, graph2.getNodeByValue("0"), graph2.getNodeByValue("17"), "iterative");
    }

    public static Graph buildGraph1() {
        int[][] listaAdj1 = {
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

        return buildGraphFromAdjacencyList(listaAdj1);
    }

    public static Graph buildGraph2() {
        int[][] listaAdj2 = {
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

        return buildGraphFromAdjacencyList(listaAdj2);
    }

    private static Graph buildGraphFromAdjacencyList(int[][] listaAdj) {
        Graph graph = new Graph();

        Node[] nodes = new Node[listaAdj.length];

        for (int i = 0; i < listaAdj.length; i++) {
            nodes[i] = new Node(String.valueOf(i));
            graph.addNode(nodes[i]);
        }

        for (int i = 0; i < listaAdj.length; i++) {
            for (int destino : listaAdj[i]) {
                if (i < destino) {
                    graph.addEdge(nodes[i], nodes[destino]);
                }
            }
        }

        return graph;
    }
}