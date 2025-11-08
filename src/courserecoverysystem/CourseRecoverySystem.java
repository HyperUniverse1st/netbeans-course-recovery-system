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
import courserecoverysystem.uiElements.SidebarPanel;
import view.Test;
import courserecoverysystem.view.Test2;
import courserecoverysystem.controller.SidebarHandler;

public class CourseRecoverySystem {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Course Recovery System");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            frame.setLayout(new BorderLayout());
            
            JPanel mainPanel = new JPanel(new CardLayout());

            
            Test testPage = new Test();
            Test2 test2Page = new Test2();

            mainPanel.add(testPage, "Test");
            mainPanel.add(test2Page, "Test2");
            

            SidebarHandler handler = new SidebarHandler(mainPanel);

            String buttonConfig = """
                Fun1|15|#5c6872|#ffffff|null|Fun1,
                Fun2|15|#5c6872|#ffffff|null|Fun2,
                Fun3|15|#5c6872|#ffffff|null|Fun3,
                Fun4|15|#5c6872|#ffffff|null|Fun4
                """;

            SidebarPanel sidebar = new SidebarPanel(buttonConfig, 300, handler::handle);
            sidebar.setPreferredSize(new Dimension(250, 0));

            frame.add(sidebar, BorderLayout.WEST);
            frame.add(mainPanel, BorderLayout.CENTER);

            frame.setVisible(true);
        });
    }
}