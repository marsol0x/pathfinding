package pathfinding;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Controls extends JPanel {
    private JButton runBtn = new JButton("Run");
    private JButton resetBtn = new JButton("Reset");

    public Controls() {
        this.setLayout(new FlowLayout());
        this.add(runBtn);
        this.add(resetBtn);
    }
}
