package pathfinding;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class Pathfinder {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Pathfinding");
        Controls controls = new Controls();
        Board board = new Board();
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        
        frame.add(controls, BorderLayout.PAGE_START);
        frame.add(board, BorderLayout.CENTER);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}
