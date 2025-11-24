package courserecoverysystem;

import courserecoverysystem.controller.SidebarHandler;
import courserecoverysystem.model.User;
import courserecoverysystem.uiElements.SidebarPanel;
import courserecoverysystem.view.credential.LoginForm;
import courserecoverysystem.view.lecturer.LecturerMain;
import courserecoverysystem.view.lecturer.LecturerSchedule;
import courserecoverysystem.view.lecturer.LecturerStudents;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

// jo here in charge of page swapping, ill be describing the next few sections like a set of cards.
public class LoginScreen { 
    public static JFrame mainFrame; // think of this as your "table"
    public static JPanel mainPanel; // think of this as your "hand"
    public static SidebarPanel sidebar;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            mainFrame = new JFrame("Course Recovery System");
            mainFrame.setSize(1420, 820); 
            mainFrame.setLayout(new BorderLayout()); 
            mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            mainPanel = new JPanel(new CardLayout());
            mainFrame.add(mainPanel, BorderLayout.CENTER);
            
            LoginForm loginPage = new LoginForm(); 
            loginPage.setVisible(true);

        });
    }

    public static void showContent(String cardName) {
        CardLayout cl = (CardLayout) mainPanel.getLayout();
        cl.show(mainPanel, cardName);
    }
    
    public static void logout() {
        User.setCurrentUser(null);
        if (sidebar != null) {
            mainFrame.getContentPane().remove(sidebar);
            sidebar = null;
        }
        
        mainPanel.removeAll(); 
        mainFrame.setVisible(false);
        
        LoginForm loginPage = new LoginForm();
        loginPage.setVisible(true);
        
    }
    
    public static void SwapToLecturer() {
        // here, every page is considered a "card". we add all lecturer cards on to your hand.
        mainPanel.add(new LecturerMain(), "lhome");
        mainPanel.add(new LecturerStudents(), "lviewstudents");
        mainPanel.add(new LecturerSchedule(), "lviewschedule");
        
        // the sidebar is also a separate card. remove the old sidebar card if needed and add the new updated one.
        if (sidebar != null) { 
            mainFrame.getContentPane().remove(sidebar); 
        }

        SidebarHandler handler = new SidebarHandler(mainPanel);
        String buttonConfig = """
            Home|12|#4E76A3|#ffffff|null|lhome,
            View Students|12|#4E76A3|#ffffff|null|lviewstudents,
            View Schedule|12|#4E76A3|#ffffff|null|lviewschedule,
            Logout|12|#E05252|#ffffff|null|logout
            """;
        sidebar = new SidebarPanel(buttonConfig, 300, handler::handle);
        sidebar.setPreferredSize(new Dimension(250, 0));
        
        mainFrame.getContentPane().add(sidebar, BorderLayout.WEST); //this places the sidebar card onto the table, making it visible
        mainFrame.revalidate(); 
        mainFrame.repaint();
        mainFrame.setVisible(true); 
    }
}