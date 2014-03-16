package pathfinding;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public class Map {
    private static Map mapInstance = null;

    public static final int NODE_SIZE = 20;
    private int width, height;
    private Node[] nodes;
    private Node startNode = null;
    private Node goalNode = null;

    public static final Map getMap() {
        return mapInstance;
    }

    public static Map setMap(int width, int height) {
        if (mapInstance != null) return null;

        mapInstance = new Map(width, height);
        return mapInstance;
    }

    private Map(int width, int height) {
        this.width = width;
        this.height = height;
        this.nodes = new Node[width * height];
        initNodes(); // Set nodes to empty
    }

    // Initialize the nodes, by making them empty
    private void initNodes() {
        startNode = null;
        goalNode = null;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                nodes[getNodeIndex(x, y)] = new Node(x, y, NodeType.EMPTY);
            }
        }
    }

    // Clear the map
    public void clearMap() {
        initNodes();
    }

    private final int getNodeIndex(final int x, final int y) {
        return x * height + y;
    }

    public void setNodeType(int x, int y, NodeType type) {
        nodes[getNodeIndex(x, y)].setType(type);

        if (startNode != null
            && startNode.getX() == x
            && startNode.getY() == y
            && type != NodeType.START) {

            startNode = null;
        }

        if (goalNode != null
            && goalNode.getX() == x
            && goalNode.getY() == y
            && type != NodeType.GOAL) {

            goalNode = null;
        }
    }

    public void render(final Graphics2D g) {
        Rectangle2D rect = new Rectangle2D.Double();
        BasicStroke stroke = new BasicStroke(2.0f, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                rect.setRect(x * NODE_SIZE, y * NODE_SIZE, NODE_SIZE, NODE_SIZE);
                g.setPaint(nodes[getNodeIndex(x, y)].getType().color);
                g.fill(rect);
                g.setStroke(stroke);
                g.setPaint(Color.BLACK);
                g.draw(rect);
            }
        }
    }

    public final int getWidth() {
        return width;
    }

    public final int getHeight() {
        return height;
    }

    public final Node getNode(int x, int y) {
        if (getNodeIndex(x, y) >= nodes.length) return null;
        if (x < 0 || y < 0) return null;

        return nodes[getNodeIndex(x, y)];
    }

    public final Node getStartNode() {
        return startNode;
    }

    public final Node getGoalNode() {
        return goalNode;
    }

    public void setStartNode(final int x, final int y) {
        if (startNode != null) {
            setNodeType(startNode.getX(), startNode.getY(), NodeType.EMPTY);
        }
        setNodeType(x, y, NodeType.START);
        startNode = nodes[getNodeIndex(x, y)];
    }

    public void setGoalNode(final int x, final int y) {
        if (goalNode != null) {
            setNodeType(goalNode.getX(), goalNode.getY(), NodeType.EMPTY);
        }
        setNodeType(x, y, NodeType.GOAL);
        goalNode = nodes[getNodeIndex(x, y)];
    }
}
