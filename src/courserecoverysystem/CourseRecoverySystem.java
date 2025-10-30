/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package courserecoverysystem;

/**
 *
 * @author seany
 */
import java.awt.*;
import javax.swing.*;
import uiElements.SidebarPanel;
import view.Test;

public class CourseRecoverySystem {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Course Recovery System");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1000, 600);
            frame.setLocationRelativeTo(null);
            frame.setLayout(new BorderLayout());

            SidebarPanel sidebar = new SidebarPanel();
            sidebar.setPreferredSize(new Dimension(250, 0)); // fixed width
            frame.add(sidebar, BorderLayout.WEST);

            Test test = new Test();
            frame.add(test, BorderLayout.CENTER);

            frame.setVisible(true);
        });
    }
}

