import java.util.*;
public class DijkstraAlgo {
    public DijkstraAlgo(){ }

    public String dijkstra(Node start) {
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(Node::getId));
        Map<Node, Integer> dist = new HashMap<>();
        Map<Node, Node> prev = new HashMap<>();
        String ans = "";

        for (Node node : getAllNodes(start)) {
            if (node == start) {
                dist.put(node, 0);
            } else {
                dist.put(node, Integer.MAX_VALUE);
            }
            pq.add(node);
        }

        while (!pq.isEmpty()) {
            Node node = pq.poll();
            if (dist.get(node) == Integer.MAX_VALUE) {
                break;
            }
            for (Edge edge : node.getEdges()) {
                Node neighbor = edge.getTo();
                int alt = dist.get(node) + edge.getWeight();
                if (alt < dist.get(neighbor)) {
                    pq.remove(neighbor);
                    dist.put(neighbor, alt);
                    prev.put(neighbor, node);
                    pq.add(neighbor);
                }
            }
        }

        for (Node node : getAllNodes(start)) {
            // System.out.println("Distance from " + start.getId() + " to " + node.getId() + ": " + dist.get(node));
            ans += "Distance from " + Character.toString(start.getId())  + " to " + Character.toString(node.getId()) + ": " + dist.get(node) + "\n";
        }

        return ans;
    }

    private static Set<Node> getAllNodes(Node start) {
        Set<Node> visited = new HashSet<>();
        Queue<Node> queue = new LinkedList<>();
        queue.add(start);
        visited.add(start);
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            for (Edge edge : node.getEdges()) {
                Node neighbor = edge.getTo();
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }
        return visited;
    }
}
