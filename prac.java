import Graphs.GraphUsingMap;

public class prac {
    public static void main(String[] args) {

        GraphUsingMap g = new GraphUsingMap();
        // g.addVertex("A");
        // g.addVertex("B");
        // g.addVertex("C");
        // g.addVertex("D");
        // g.addVertex("E");
        // g.addVertex("F");

        // g.addEdge("A", "B", 1);
        // g.addEdge("A", "D", 1);
        // g.addEdge("B", "C", 1);
        // g.addEdge("C", "D", 1);
        // g.addEdge("D", "E", 1);
        // g.addEdge("E", "F", 1);
        // g.addEdge("B", "D", 1);
        // g.removeEdge("B", "D");
        // g.removeEdge("D", "E");

        // System.out.println(g.isConnected());
        g.addVertex("A");
        g.addVertex("B");
        g.addVertex("C");
        g.addVertex("D");
        g.addVertex("E");
        

        g.addEdge("A", "B", 2);
        g.addEdge("C", "D", 1);
        System.out.println(g.getCC());
    }
}