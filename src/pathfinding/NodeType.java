package pathfinding;

import java.awt.Color;

public enum NodeType {
    EMPTY(Color.WHITE),
    START(Color.GREEN),
    GOAL(Color.RED),
    SEEN(Color.LIGHT_GRAY),
    PATH(Color.CYAN),
    WALL(Color.GRAY);

    public final Color color;

    private NodeType(Color color) {
        this.color = color;
    }
}
