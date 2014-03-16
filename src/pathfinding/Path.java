package pathfinding;

import java.util.Collections;
import java.util.Iterator;
import java.util.Vector;

public class Path {
    private final Map map;

    public Path() {
        map = Map.getMap();
    }

    public boolean findPath() {
        Vector<Node> closedSet = new Vector<Node>();
        Vector<Node> openSet = new Vector<Node>();

        Node startNode = map.getStartNode();
        Node goalNode = map.getGoalNode();

        startNode.setGScore(0);
        startNode.setFScore(startNode.getGScore() + costEstimate(startNode, goalNode));
        openSet.add(startNode);

        while (!openSet.isEmpty()) {
            Node current = getLowestScore(openSet);

            // Found the goal, done
            if (current.equals(goalNode)) {
                for (Node n : closedSet) {
                    if (!n.equals(startNode)) {
                        n.setType(NodeType.SEEN);
                    }
                }

                Node cameFrom = current.getCameFrom();
                while (cameFrom != null) {
                    if (!cameFrom.equals(startNode)) {
                        cameFrom.setType(NodeType.PATH);
                    }
                    cameFrom = cameFrom.getCameFrom();
                }
                return true;
            }

            // Remove current from open set and add to closed set
            // We shouldn't need it anymore
            openSet.remove(current);
            closedSet.add(current);

            // Check neighbors
            for (Node neighbor : getNeighbors(current)) {
                // If the neighbor already exists in closedSet, skip it
                if (closedSet.contains(neighbor)) {
                    continue;
                }

                int tentativeCost = current.getGScore() + costEstimate(current, neighbor);
                if (!openSet.contains(neighbor) || tentativeCost < neighbor.getGScore()) {
                    neighbor.setCameFrom(current);
                    neighbor.setGScore(tentativeCost);
                    neighbor.setFScore(neighbor.getGScore() + costEstimate(neighbor, goalNode));
                    if (!openSet.contains(neighbor)) {
                        openSet.add(neighbor);
                    }
                }
            }
        }
        return false;
    }

    private Vector<Node> getNeighbors(Node node) {
        Vector<Node> neighbors = new Vector<Node>();
        int x = node.getX();
        int y = node.getY();

        neighbors.add(map.getNode(x + 1, y));
        neighbors.add(map.getNode(x - 1, y));
        neighbors.add(map.getNode(x, y + 1));
        neighbors.add(map.getNode(x, y - 1));

        // Remove null objects from the neighbors collection
        neighbors.removeAll(Collections.singleton(null));
        // Remove wall nodes from neighbors and add gscore
        for (Iterator<Node> it = neighbors.iterator(); it.hasNext(); ) {
            Node n = it.next();
            if (n.getType() == NodeType.WALL) {
                it.remove();
            } else {
                n.setGScore(node.getGScore() + 10);
            }
        }

        return neighbors;
    }

    private int costEstimate(Node start, Node goal) {
        return Math.abs(goal.getX() - start.getX()) + Math.abs(goal.getY() - start.getY());
    }

    private Node getLowestScore(Vector<Node> nodes) {
        Node lowest = nodes.firstElement();
        for (Node n : nodes) {
            if (n.getFScore() < lowest.getFScore()) {
                lowest = n;
            }
        }

        return lowest;
    }
}
