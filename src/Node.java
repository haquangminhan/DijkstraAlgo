import java.util.*;
public class Node{
        private char id;
        private List<Edge> edges;

        public Node(char id) {
            this.id = id;
            this.edges = new ArrayList<>();
        }

        public char getId() {
            return id;
        }

        public List<Edge> getEdges() {
            return edges;
        }

        public void addEdge(Edge edge) {
            edges.add(edge);
        }
}
