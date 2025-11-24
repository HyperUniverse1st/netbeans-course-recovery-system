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
            default -> System.out.println("Unknown sidebar action: " + functionName); //maybe i might make this into error handling but i dont wanna T-T can someone else do it?
            case "LectTest" -> btnLect();
            case "lhome"-> btnLectHome();
            case "lviewstudents"-> btnLectStudents();
            case "lviewschedule"-> btnLectSchedule();
            case "logout"-> btnLogOut();
        }
    }
    private void btnLogOut() { 
        LoginScreen.logout();
    }
    
    private void btnFunction1() { // here add it here as in all button functions for like all buttons
        CardLayout layout = (CardLayout) mainPanel.getLayout();
        layout.show(mainPanel,"Test");
    }
    
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