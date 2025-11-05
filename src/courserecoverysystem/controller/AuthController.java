/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.assignment.courserecoverysystem.controller;

import com.assignment.courserecoverysystem.model.User;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


/**
 *
 * @author User
 */
public class AuthController {
    
    private static final String USER_FILE = "src/user.txt";
    
    public static User login(String emailInput,String passwordInput){
        try{
            BufferedReader reader = new BufferedReader(new FileReader("src/user.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if(data.length >= 5){
                    String id = data[0];
                    String name = data[1];
                    String email = data[2];
                    String password = data[3];
                    String role = data[4];

                    if (email.equalsIgnoreCase(emailInput) && password.equals(passwordInput)){
                    reader.close(); //close file
                    return new User(id,name,email,password,role);
                    }
                }
            }
            
            reader.close();
        }catch (IOException e){
            System.out.println("Error reading file: " + e.getMessage());
        }
        
        return null;
    }
    
    //reset password method
    public static boolean resetPassword(String emailInput, String newPassword) {
        String filePath = "src/user.txt";
        StringBuilder fileContent = new StringBuilder();
        boolean found = false;

        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;

            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");

                if (data.length >= 5) {
                    String id = data[0];
                    String name = data[1];
                    String email = data[2];
                    String password = data[3];
                    String role = data[4];

                    // Match email → update password
                    if (email.equalsIgnoreCase(emailInput)) {
                        data[3] = newPassword; // replace password
                        found = true;
                    }

                    // Join back the updated line
                    String updatedLine = String.join(",", data);
                    fileContent.append(updatedLine).append("\n");
                } else {
                    // keep invalid lines as they are
                    fileContent.append(line).append("\n");
                }
            }
            reader.close();

            // If user not found, return false
            if (!found) {
                return false;
            }

            // Write all updated lines back to the file
            FileWriter writer = new FileWriter(filePath);
            writer.write(fileContent.toString());
            writer.close();

            return true;

        } catch (IOException e) {
            System.out.println("Error resetting password: " + e.getMessage());
            return false;
        }
    }

}