/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package courserecoverysystem.uiElements;

/**
 *
 * @author seany
 */
import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class SidebarProfile extends JPanel {
    private JLabel iconLabel;
    private JLabel nameLabel;
    private JLabel subLabel;

    public SidebarProfile(String resourcePath, String name, String subText, Color bg, Color fg) {
        setBackground(bg);
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS)); // icon left, text right

        // Profile icon
        if (resourcePath != null) {
            URL iconUrl = getClass().getResource(resourcePath);
            if (iconUrl != null) {
                ImageIcon originalIcon = new ImageIcon(iconUrl);
                Image scaledIcon = originalIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                iconLabel = new JLabel(new ImageIcon(scaledIcon));
                iconLabel.setAlignmentY(Component.TOP_ALIGNMENT); // top-align icon
                add(iconLabel);
            } else {
                System.out.println("Image not found: " + resourcePath);
            }
        }

        add(Box.createRigidArea(new Dimension(10, 0))); // horizontal spacing

        // Text panel (vertical layout for name + sub-label)
        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        textPanel.setOpaque(false);
        textPanel.setAlignmentY(Component.TOP_ALIGNMENT); // align with top of icon

        // Name label
        nameLabel = new JLabel(name);
        nameLabel.setForeground(fg);
        nameLabel.setFont(new Font("Segoe UI", Font.BOLD, 25));
        nameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        textPanel.add(nameLabel);

        // Sub-label below name
        if (subText != null && !subText.isEmpty()) {
            subLabel = new JLabel(subText);
            subLabel.setForeground(fg.darker());
            subLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
            subLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
            textPanel.add(subLabel);
        }

        add(textPanel);
        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
    }

    @Override
    public Dimension getPreferredSize() {
        int width = 0;
        int height = 0;

        if (iconLabel != null) {
            width += iconLabel.getPreferredSize().width;
            height = Math.max(height, iconLabel.getPreferredSize().height);
        }
        if (nameLabel != null) {
            int textWidth = nameLabel.getPreferredSize().width;
            int textHeight = nameLabel.getPreferredSize().height;
            if (subLabel != null) {
                textWidth = Math.max(textWidth, subLabel.getPreferredSize().width);
                textHeight += subLabel.getPreferredSize().height;
            }
            width += 10 + textWidth; // spacing
            height = Math.max(height, textHeight);
        }

        Insets insets = getInsets();
        width += insets.left + insets.right;
        height += insets.top + insets.bottom;

        return new Dimension(width, height);
    }
}