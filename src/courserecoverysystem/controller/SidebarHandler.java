/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package courserecoverysystem.controller;

/**
 *
 * @author seany
 */
public class SidebarHandler {

    public void handle(String functionName) {
        System.out.println("Sidebar clicked: " + functionName);

        switch (functionName) {
            case "Fun1" -> btnFunction1();
            default -> System.out.println("Unknown sidebar action: " + functionName); //maybe i might make this into error handling but i dont wanna T-T can someone else do it?
        }
    }

    private void btnFunction1() { // here add it here as in all button functions for like all buttons
        System.out.println("Fun1");
        
    }
}
