package courserecoverysystem.uiElements;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

// j already know this so if you got any question just ask me - seanyap

public class SidebarPanel extends JPanel {
    private Map<String, Runnable> functionMap = new HashMap<>();
    private int buttonWidth;
    private int buttonHeight = 100;
    private Consumer<String> buttonClickListener;

    public SidebarPanel(String buttonConfig, int width, Consumer<String> listener) {
        this.buttonWidth = width;
        this.buttonClickListener = listener;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(new Color(78,118,163));

    SidebarProfile profile = new SidebarProfile(
        "/resources/pfp/Logo.png", // added a logo for you guys! - jo
        "Course Recovery System",
        "Asia Pacific University",
        new Color(78,118,163),
        Color.WHITE
    );
    
    profile.setAlignmentX(Component.CENTER_ALIGNMENT);
    add(Box.createRigidArea(new Dimension(0, 40)));
    add(profile);
    add(Box.createRigidArea(new Dimension(0, 20)));
        parseAndAddButtons(buttonConfig);
    }

    private void parseAndAddButtons(String config) {
        String[] buttonEntries = config.split(",");

        for (String entry : buttonEntries) {
            String[] parts = entry.trim().split("\\|");
            if (parts.length < 6) {
            System.out.println("Button has invalid call" + parts[0]);
            continue;
            } // istg if you didnt look through here you should note that the calling has fail // what does this sentence even mean?? - jo
            // i said what i said
            String label = parts[0].trim();
            int radius = Integer.parseInt(parts[1].trim());
            Color bg = Color.decode(parts[2].trim());
            Color fg = Color.decode(parts[3].trim());
            String iconPath = parts[4].trim();
            String functionName = parts[5].trim();

            Icon icon = null;
            if (!iconPath.equalsIgnoreCase("null") && !iconPath.isEmpty()) {
                icon = new ImageIcon(iconPath);
            }
            addButton(label, radius, bg, fg, icon, functionName);
        }
        revalidate();
    }

    private void addButton(String text, int radius, Color bg, Color fg, Icon icon, String functionName) {
        RoundedJButton btn = new RoundedJButton(text, radius, bg, fg, icon, buttonWidth, buttonHeight);
        btn.setMaximumSize(new Dimension(buttonWidth, buttonHeight));
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.setFont(new Font("Segoe UI", Font.BOLD, 25));

        btn.addActionListener(e -> {
            Runnable action = functionMap.get(functionName);
            if (action != null) action.run();

            if (buttonClickListener != null) buttonClickListener.accept(functionName);
        });

        add(btn);
        add(Box.createRigidArea(new Dimension(0, 15)));
    }

    public void registerFunction(String name, Runnable function) {
        functionMap.put(name, function);
    }

    public void setButtonClickListener(Consumer<String> listener) {
        this.buttonClickListener = listener;
    }

    @Override
    public Dimension getPreferredSize() {
        int height = super.getPreferredSize().height;
        return new Dimension(buttonWidth + 40, height);
    }
}