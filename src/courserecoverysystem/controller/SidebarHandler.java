/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package courserecoverysystem.controller;

/**
 *
 * @author seany
 */
import courserecoverysystem.LoginScreen;
import courserecoverysystem.view.lecturer.LecturerMain;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import javax.swing.*;

/*
only j should be touching this - seanyap
*/

public class SidebarHandler {
    private final JPanel mainPanel;
    
    public SidebarHandler(JPanel mainPanel){
        this.mainPanel = mainPanel;
    }
    
    public void handle(String functionName) {
        System.out.println("Sidebar clicked: " + functionName);

        switch (functionName) {
            case "Fun1" -> btnFunction1();
            case "logout"-> btnLogOut();
            default -> JOptionPane.showMessageDialog(mainPanel, "Error! Button not formatted.");
            
            // LECTURER HANDLER
            case "LectTest" -> btnLect(); // i forgot what this was for but im not going to touch it
            case "lhome"-> btnLectHome();
            case "lviewstudents"-> btnLectStudents();
            case "lviewschedule"-> btnLectSchedule();
            
            // OFFICER HANDLER
            case "oviewstudents"-> btnOfficerStudents();
            case "ohome"-> btnOfficerHome();
            case "oanalytics"-> btnOfficerAnalytics();
            
            // ADMIN HANDLER
            case "ahome"-> btnAdminHome();
            case "aviewstudents"-> btnAdminStudents();
            case "aviewclasses"-> btnAdminClasses();
            case "alecturers" -> btnAdminLecturers();
            
            // STUDENT HANDLER
            case "shome"-> btnStudentHome();
            case "sviewcourses"-> btnStudentCourse();
            case "sviewschedule"-> btnStudentSchedule();
        }
    }
    private void btnLogOut() { 
        LoginScreen.logout();
    }
    
    private void btnStudentHome() { 
        CardLayout layout = (CardLayout) mainPanel.getLayout();
        layout.show(mainPanel,"shome");
    }
    
    private void btnStudentCourse() { 
        CardLayout layout = (CardLayout) mainPanel.getLayout();
        layout.show(mainPanel,"sviewcourses");
    }
    
    private void btnStudentSchedule() { 
        CardLayout layout = (CardLayout) mainPanel.getLayout();
        layout.show(mainPanel,"sviewschedule");
    }
    
    // ADMIN STUFF
    private void btnAdminHome() { 
        CardLayout layout = (CardLayout) mainPanel.getLayout();
        layout.show(mainPanel,"ahome");
    }
    
    private void btnAdminStudents() { 
        CardLayout layout = (CardLayout) mainPanel.getLayout();
        layout.show(mainPanel,"aviewstudents");
    }
    
    private void btnAdminClasses() { 
        CardLayout layout = (CardLayout) mainPanel.getLayout();
        layout.show(mainPanel,"aviewclasses");
    }
    
    private void btnAdminLecturers() { 
        CardLayout layout = (CardLayout) mainPanel.getLayout();
        layout.show(mainPanel,"alecturers");
    }
    
    // OFFICER STUFF
    private void btnOfficerStudents() { 
        CardLayout layout = (CardLayout) mainPanel.getLayout();
        layout.show(mainPanel,"oviewstudents");
    }
    
    private void btnOfficerHome() { 
        CardLayout layout = (CardLayout) mainPanel.getLayout();
        layout.show(mainPanel,"ohome");
    }
    
    private void btnOfficerAnalytics() { 
        CardLayout layout = (CardLayout) mainPanel.getLayout();
        layout.show(mainPanel,"oanalytics");
    }
    
    private void btnFunction1() { 
        CardLayout layout = (CardLayout) mainPanel.getLayout();
        layout.show(mainPanel,"Test");
    }
    
    
    // LECTURER STUFF
    private void btnLect() { 
        System.out.println("Navigating to Lecturer Page...");
        CardLayout layout = (CardLayout) mainPanel.getLayout();
        layout.show(mainPanel,"LectTest");
    }
    
    private void btnLectHome(){
        System.out.println("Navigating to Lecturer Home Page...");
        CardLayout layout = (CardLayout) mainPanel.getLayout();
        layout.show(mainPanel,"lhome");
    }
    
    private void btnLectStudents(){
        System.out.println("Navigating to Student Page...");
        CardLayout layout = (CardLayout) mainPanel.getLayout();
        layout.show(mainPanel,"lviewstudents");
        
    }
    
    private void btnLectSchedule(){
        System.out.println("Navigating to Schedule Page...");
        CardLayout layout = (CardLayout) mainPanel.getLayout();
        layout.show(mainPanel,"lviewschedule");
        
    }
    
}