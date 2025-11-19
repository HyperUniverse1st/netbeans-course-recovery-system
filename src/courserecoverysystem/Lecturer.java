package courserecoverysystem;
import courserecoverysystem.controller.SidebarHandler;
import java.awt.*;
import javax.swing.*;
import courserecoverysystem.uiElements.SidebarPanel;
import courserecoverysystem.view.lecturer.LecturerMain;
import courserecoverysystem.view.lecturer.LecturerSchedule;
import courserecoverysystem.view.lecturer.LecturerStudents;
// Tang Shi Yee - TP074886
// Lecturer Role + Profile
// Can Do: UI - Buttons, Nagivation
// Can't Do: Main Functions due to lack of data - View Students, View Schedule, View Profile

public class Lecturer {
    private static JPanel mainPanel;
    private static SidebarPanel sidebar;
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Lecturer Dashboard");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1420, 820);
            frame.setLayout(new BorderLayout());
            
            LecturerInitialise();
            
            frame.add(sidebar, BorderLayout.WEST);
            frame.add(mainPanel, BorderLayout.CENTER);
            
            
            frame.setVisible(true);
        });
    }
    
    public static void LecturerInitialise(){
        mainPanel = new JPanel(new CardLayout());
        LecturerMain lectPage = new LecturerMain(); // setting up pages for page swapping
        LecturerStudents lectStudentsPage = new LecturerStudents();
        LecturerSchedule lectSchedulePage = new LecturerSchedule();
        
        mainPanel.add(lectPage, "lhome");
        mainPanel.add(lectStudentsPage, "lviewstudents");
        mainPanel.add(lectSchedulePage, "lviewschedule");
            
        SidebarHandler handler = new SidebarHandler(mainPanel); // adding the side panel with the below buttons
        String buttonConfig = """
            Home|12|#4E76A3|#ffffff|null|lhome,
            View Students|12|#4E76A3|#ffffff|null|lviewstudents,
            View Schedule|12|#4E76A3|#ffffff|null|lviewschedule,
            """; // button text, font size, button color, text color, idk ask sean yap, calling card

        sidebar = new SidebarPanel(buttonConfig, 300, handler::handle); 
        sidebar.setPreferredSize(new Dimension(250, 0));            
        
        CardLayout layout = (CardLayout) mainPanel.getLayout();
        layout.show(mainPanel, "lhome");
    }
    
}