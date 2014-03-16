package pathfinding;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Controls extends JPanel {
    private JButton runBtn = new JButton("Run");
    private JButton resetBtn = new JButton("Reset");
    private JButton setStartBtn = new JButton("Set Start Node");
    private JButton setGoalBtn = new JButton("Set Goal Node");

    @SuppressWarnings("unused")
    private final Board board;

    public Controls(final Board board) {
        this.setLayout(new FlowLayout());
        this.add(setStartBtn);
        this.add(setGoalBtn);
        this.add(runBtn);
        this.add(resetBtn);

        this.board = board;

        final Controls that = this;
        runBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Map map = Map.getMap();
                // Start and Goal nodes must exist
                if (map.getStartNode() == null || map.getGoalNode() == null) {
                    JOptionPane.showMessageDialog(that,
                            "Must have a Start and Goal node set",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Path p = new Path();
                p.findPath();
                board.repaint();
            }
        });

        resetBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Map.getMap().clearMap();
                board.repaint();
            }
        });

        setStartBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                board.toggleSetStartNode();
            }
        });

        setGoalBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                board.toggleSetGoalNode();
            }
        });
    }
}
