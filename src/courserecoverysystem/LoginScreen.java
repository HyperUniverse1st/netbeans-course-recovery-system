package courserecoverysystem;

import courserecoverysystem.controller.SidebarHandler;
import courserecoverysystem.model.User;
import courserecoverysystem.uiElements.SidebarPanel;
import courserecoverysystem.view.admin.AdminClasses;
import courserecoverysystem.view.admin.AdminCreateClass;
import courserecoverysystem.view.admin.AdminLecturers;
import courserecoverysystem.view.admin.AdminMain;
import courserecoverysystem.view.admin.AdminStudents;
import courserecoverysystem.view.login.LoginForm;
import courserecoverysystem.view.lecturer.LecturerMain;
import courserecoverysystem.view.lecturer.LecturerSchedule;
import courserecoverysystem.view.lecturer.LecturerStudentDetails;
import courserecoverysystem.view.lecturer.LecturerStudents;
import courserecoverysystem.view.officer.OfficerAnalytics;
import courserecoverysystem.view.officer.OfficerCourseDetails;
import courserecoverysystem.view.officer.OfficerCourses;
import courserecoverysystem.view.officer.OfficerMain;
import courserecoverysystem.view.officer.OfficerStudentDetails;
import courserecoverysystem.view.officer.OfficerStudents;
import courserecoverysystem.view.officer.OfficerViewReport;
import courserecoverysystem.view.student.StudentCourseDetails;
import courserecoverysystem.view.student.StudentCourses;
import courserecoverysystem.view.student.StudentMain;
import courserecoverysystem.view.student.StudentSchedule;
import courserecoverysystem.viewpublic.UserProfile;
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
        CardLayout currentpage = (CardLayout) mainPanel.getLayout();
        currentpage.show(mainPanel, cardName);
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
        mainPanel.add(new LecturerMain(), "lhome"); // here, every page is considered a "card". we add all lecturer cards on to your hand.
        mainPanel.add(new LecturerStudents(), "lviewstudents");
        mainPanel.add(new LecturerStudentDetails(), "lviewdetails");
        mainPanel.add(new LecturerSchedule(), "lviewschedule");
        mainPanel.add(new UserProfile(), "userprofile");
        
        if (sidebar != null) { // the sidebar is also a separate card. remove the old sidebar card if needed and add the new updated one.
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
    
    public static void SwapToOfficer() {
        // same thing but for other roles
        mainPanel.add(new OfficerMain(), "ohome");
        mainPanel.add(new OfficerStudents(), "oviewstudents");
        mainPanel.add(new OfficerStudentDetails(), "oviewstudentdetails");
        mainPanel.add(new OfficerCourses(), "oviewcourses");
        mainPanel.add(new OfficerCourseDetails(), "oviewcoursedetails");
        mainPanel.add(new OfficerAnalytics(), "oanalytics");
        mainPanel.add(new OfficerViewReport(), "oreports");
        mainPanel.add(new UserProfile(), "userprofile");
        
        if (sidebar != null) { 
            mainFrame.getContentPane().remove(sidebar); 
        }

        SidebarHandler handler = new SidebarHandler(mainPanel);
        String buttonConfig = """
            Home|12|#4E76A3|#ffffff|null|ohome,
            View Students|12|#4E76A3|#ffffff|null|oviewstudents,
            View Analytics|12|#4E76A3|#ffffff|null|oanalytics,
            Logout|12|#E05252|#ffffff|null|logout
            """;
        sidebar = new SidebarPanel(buttonConfig, 300, handler::handle);
        sidebar.setPreferredSize(new Dimension(250, 0));
        
        mainFrame.getContentPane().add(sidebar, BorderLayout.WEST);
        mainFrame.revalidate(); 
        mainFrame.repaint();
        mainFrame.setVisible(true); 
    }
    
    public static void SwapToAdmin() {
        mainPanel.add(new AdminMain(), "ahome");
        mainPanel.add(new AdminStudents(), "aviewstudents");
        mainPanel.add(new AdminLecturers(), "aviewlecturers");
        mainPanel.add(new AdminClasses(), "aviewclasses");
        mainPanel.add(new AdminCreateClass(), "acreateclass");
        mainPanel.add(new UserProfile(), "userprofile");
        
        if (sidebar != null) { 
            mainFrame.getContentPane().remove(sidebar); 
        }

        SidebarHandler handler = new SidebarHandler(mainPanel);
        String buttonConfig = """
            Home|12|#4E76A3|#ffffff|null|ahome,
            View Students|12|#4E76A3|#ffffff|null|aviewstudents,
            View Classes|12|#4E76A3|#ffffff|null|aviewclasses,
            View Lecturers|12|#4E76A3|#ffffff|null|aviewlecturers,
            Logout|12|#E05252|#ffffff|null|logout
            """;
        sidebar = new SidebarPanel(buttonConfig, 300, handler::handle);
        sidebar.setPreferredSize(new Dimension(250, 0));
        
        mainFrame.getContentPane().add(sidebar, BorderLayout.WEST); //this places the sidebar card onto the table, making it visible
        mainFrame.revalidate(); 
        mainFrame.repaint();
        mainFrame.setVisible(true); 
    }
    
    public static void SwapToStudent() {
        mainPanel.add(new StudentMain(), "shome");
        mainPanel.add(new StudentCourses(), "sviewcourses");
        mainPanel.add(new StudentCourseDetails(), "sviewcoursedetails");
        mainPanel.add(new StudentSchedule(), "sviewschedule");
        mainPanel.add(new UserProfile(), "userprofile");
        
        if (sidebar != null) { 
            mainFrame.getContentPane().remove(sidebar); 
        }

        SidebarHandler handler = new SidebarHandler(mainPanel);
        String buttonConfig = """
            Home|12|#4E76A3|#ffffff|null|shome,
            View Courses|12|#4E76A3|#ffffff|null|sviewcourses,
            View Schedule|12|#4E76A3|#ffffff|null|sviewschedule,
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