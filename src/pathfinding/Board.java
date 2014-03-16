package pathfinding;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Board extends JPanel implements MouseListener {
    private final int width = 25;
    private final int height = 25;
    private Map map;

    private boolean setStartNode = false;
    private boolean setGoalNode = false;

    public Board() {
        this.setPreferredSize(new Dimension(width * Map.NODE_SIZE, height * Map.NODE_SIZE));
        this.addMouseListener(this);

        Map.setMap(width, height);
        map = Map.getMap();
    }

    public void toggleSetStartNode() { setStartNode = !setStartNode; }
    public void toggleSetGoalNode() { setGoalNode = !setGoalNode; }

    @Override
    public void paint(Graphics g) {
       super.paint(g);
       Graphics2D g2 = (Graphics2D) g;
       map.render(g2);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int x = (int) (e.getX() / (float) Map.NODE_SIZE);
        int y = (int) (e.getY() / (float) Map.NODE_SIZE);

        if (!setStartNode && !setGoalNode) {
            switch (e.getModifiers()) {
            case InputEvent.BUTTON1_MASK:
                map.setNodeType(x, y, NodeType.WALL);
                break;
            case InputEvent.BUTTON1_MASK | InputEvent.SHIFT_MASK:
                map.setNodeType(x, y, NodeType.EMPTY);
                break;
            }
        } else {
            if (setStartNode && !setGoalNode) {
                map.setStartNode(x, y);
                toggleSetStartNode();
            }
            if (setGoalNode && !setStartNode) {
                map.setGoalNode(x, y);
                toggleSetGoalNode();
            }
        }

        repaint();
    }

    /*
     *
     * Below are unnecessary, but required, listening methods
     *
     */
    @Override
    public void mouseReleased(MouseEvent e) { }

    @Override
    public void mouseClicked(MouseEvent e) { }

    @Override
    public void mouseEntered(MouseEvent e) { }

    @Override
    public void mouseExited(MouseEvent e) { }
}