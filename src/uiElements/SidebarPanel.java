package uiElements;

import javax.swing.*;
import java.awt.*;

public class SidebarPanel extends JPanel {

    public SidebarPanel() {
//        String[] button;
//        for (String buttonValue : buttonList) {
//            button = buttonValue.split("\\|");
//            
//            addButton(button[0], button[1]); // will need to sort this out
//        }
//        
        
        
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); 
        setBackground(new Color(44, 62, 80)); 
        setPreferredSize(new Dimension(200, 600));
        
        
        
        add(Box.createRigidArea(new Dimension(0, 20))); 

        
        addButton("Home", new Color(52, 152, 219));
        addButton("Profile", new Color(46, 204, 113));
        addButton("Settings", new Color(241, 196, 15));
        addButton("Logout", new Color(231, 76, 60));
    }

    private void addButton(String text, Color color) {
        RoundedJButton btn = new RoundedJButton(text, 20, color, Color.WHITE);
        btn.setMaximumSize(new Dimension(180, 40));
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.setFocusPainted(false);

        add(btn);
        add(Box.createRigidArea(new Dimension(0, 15)));
    }
}