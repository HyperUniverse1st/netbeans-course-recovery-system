/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package courserecoverysystem.controller;

/**
 *
 * @author seany
 */
import java.awt.*;
import javax.swing.*;

public class SidebarHandler {

    private final JPanel mainPanel;

    public SidebarHandler(JPanel mainPanel) {
        this.mainPanel = mainPanel;
    }

    public void handle(String functionName) {
        System.out.println("Sidebar clicked: " + functionName);

        switch (functionName) {
            case "Fun1" -> btnFunction1();
            default -> System.out.println("Unknown sidebar action: " + functionName);
        }
    }

    private void btnFunction1() {
        CardLayout layout = (CardLayout) mainPanel.getLayout();
        layout.show(mainPanel, "Test2");
    }
}
