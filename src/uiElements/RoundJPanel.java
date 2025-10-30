package uiElements;

import javax.swing.*;
import java.awt.*;

public class RoundJPanel extends JFrame {

    public RoundJPanel() {
        setTitle("Rounded Panel Example");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null); 

        Container pane = getContentPane();
        pane.setBackground(Color.LIGHT_GRAY);
        pane.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 20)); 

        RoundedPanel panel1 = new RoundedPanel(15, Color.CYAN);
        panel1.setPreferredSize(new Dimension(100, 60));

        RoundedPanel panel2 = new RoundedPanel(20, Color.RED);
        panel2.setPreferredSize(new Dimension(100, 60));

        RoundedPanel panel3 = new RoundedPanel(30, Color.GREEN);
        panel3.setPreferredSize(new Dimension(120, 100));

        RoundedPanel panel4 = new RoundedPanel(50, Color.BLUE);
        panel4.setPreferredSize(new Dimension(150, 150));

        pane.add(panel1);
        pane.add(panel2);
        pane.add(panel3);
        pane.add(panel4);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new RoundJPanel().setVisible(true);
        });
    }
}
