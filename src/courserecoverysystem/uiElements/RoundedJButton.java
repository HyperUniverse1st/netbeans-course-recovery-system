package courserecoverysystem.uiElements;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class RoundedJButton extends JButton {
    private int cornerRadius;

    public RoundedJButton(String label, int radius, Color background, Color foreground, Icon icon) {
        super(label, icon);
        this.cornerRadius = radius;

        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);
        setOpaque(false);
        initButton(background, foreground);
        setBackground(background);
        setForeground(foreground);

        setHorizontalAlignment(SwingConstants.LEFT);
        setIconTextGap(10);
    }

    public RoundedJButton(String label, int radius, Color background, Color foreground) {
        this(label, radius, background, foreground, null);
    }
    
    public RoundedJButton(String label, int radius, Color background, Color foreground, Icon icon, int width, int height) {
        super(label, icon);
        this.cornerRadius = radius;
        initButton(background, foreground);
        setPreferredSize(new Dimension(width, height));
        setMaximumSize(new Dimension(width, height));
        setMinimumSize(new Dimension(width, height));
    }

    private void initButton(Color background, Color foreground) {
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);
        setOpaque(false);
        setBackground(background);
        setForeground(foreground);
        setHorizontalAlignment(SwingConstants.LEFT);
        setIconTextGap(10);
        
        if (getIcon() == null) {
            setHorizontalAlignment(SwingConstants.CENTER);
        } else {
            setHorizontalAlignment(SwingConstants.LEFT);
        }
    setVerticalAlignment(SwingConstants.CENTER);
    }
    
    public void setCornerRadius(int radius) {
        this.cornerRadius = radius;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        Color fillColor = getBackground();
        if (getModel().isPressed()) {
            fillColor = fillColor.darker();
        } else if (getModel().isRollover()) {
            fillColor = fillColor.brighter();
        }

        g2.setColor(fillColor);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius);

        super.paintComponent(g2);
        g2.dispose();
    }

    @Override
    protected void paintBorder(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(getBackground());
        g2.setStroke(new BasicStroke(1.5f));
        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, cornerRadius, cornerRadius);

        g2.dispose();
    }
}
