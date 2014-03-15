package pathfinding;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Board extends JPanel {
    private final int width = 500;
    private final int height = 500;
    private Map map;

    public Board() {
        this.setPreferredSize(new Dimension(width, height));
        
        map = new Map(width, height);
    }
    
    @Override
    public void paint(Graphics g) {
       super.paint(g); 
       Graphics2D g2 = (Graphics2D) g;
       map.render(g2);
    }
}
