package pathfinding;

public class Node {
    private NodeType type;
    private int x;
    private int y;
    private int fScore;
    private int gScore;
    private Node cameFrom = null;

    public Node(int x, int y, NodeType type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }

    public final NodeType getType() {
        return type;
    }

    public void setType(NodeType type) {
        this.type = type;
    }

    public final int getX() {
        return x;
    }

    public final int getY() {
        return y;
    }

    public final int getFScore() {
        return fScore;
    }

    public void setFScore(int fScore) {
        this.fScore = fScore;
    }

    public final int getGScore() {
        return gScore;
    }

    public void setGScore(int gScore) {
        this.gScore = gScore;
    }

    public final Node getCameFrom() {
        return cameFrom;
    }

    public void setCameFrom(Node cameFrom) {
        this.cameFrom = cameFrom;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + x;
        result = prime * result + y;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Node other = (Node) obj;
        if (x != other.x)
            return false;
        if (y != other.y)
            return false;
        return true;
    }
}
