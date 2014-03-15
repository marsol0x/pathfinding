package pathfinding;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public class Map {
    private int width, height;

    public Map(int width, int height) {
        this.width = width;
        this.height = height;
    }
    
    public void render(Graphics2D g) {
        Rectangle2D rect = new Rectangle2D.Double();
        BasicStroke stroke = new BasicStroke(2.0f, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER);

        for (int y = 0; y < height; y += 20) {
            for (int x = 0; x < width; x += 20) {
                rect.setRect(x, y, 20, 20);
                g.setPaint(Color.WHITE);
                g.fill(rect);
                g.setStroke(stroke);
                g.setPaint(Color.BLACK);
                g.draw(rect);
            }
        }
    }
}
