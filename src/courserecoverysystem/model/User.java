/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package courserecoverysystem.model;

/**
 *
 * @author seany
 */


import courserecoverysystem.service.FileService;
import courserecoverysystem.service.ExceptionService;
import courserecoverysystem.service.NotificationService;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;



/*
there should be only one user and as such you should use currentUser to get values
*/

public class User { //TODO please we really need the values for the user
    private static final FileService data = new FileService();
    private static final ExceptionService ex = new ExceptionService();
    private static final NotificationService notification = new NotificationService();
    private String uid;
    private String name;
    private String email;
    private String role;
    private String password;
    

    
    private static User currentUser;
    public User() {
    }

    public User(String uid, String name, String email, String role, String password) {
        this.uid = uid;
        this.name = name;
        this.email = email;
        this.role = role;
        this.password = password;
    }
    
    public String getUID() { //TODO these are the getters
        return uid; 
    }
    public void setUID(String name) { 
        this.uid = uid; 
    }
    
    public String getName() { //TODO these are the getters
        return name; 
    }
    public void setName(String name) { 
        this.name = name; 
    }

    public String getEmail() { 
        return email; 
    }
    public void setEmail(String email) {
        this.email = email; 
    }

    public String getRole() { 
        return role; 
    }
    public void setRole(String role) { 
        this.role = role; 
    }

    public String getPassword() { 
        return password; 
    }
    public void setPassword(String password) { 
        this.password = password; 
    }

    public static User getCurrentUser() { 
        return currentUser; 
    }
    public static void setCurrentUser(User user) { 
        currentUser = user; 
    }
    


/*
    How to use retrieveUser(String filename):
    
    If you ask again I will know did read this 

    1. Call retrieveUser() to get all lines from a file:
        List<String> lines = retrieveUser("user.txt");

    2. Each line is a raw string separated by "|"

    3. To parse each line into a clean map of header -> value, use FileService.assignHeaderLine():
        FileService fileService = new FileService();
        for (String line : lines) {
            List<String> values = fileService.parseLine(line); // splits line by "|"
            Map<String, String> userMap = fileService.assignHeaderLine("user", values);
            
            // Example usage:
            String uid = userMap.get("uid");
            String username = userMap.get("username");
            String password = userMap.get("password");
            String role = userMap.get("role");

            // Now you have all fields of this user as a Map
        }

    4. If you only need one user by a specific field (uid), you can use:
        List<String> matchedLine = fileService.retrieveOneMatchLine("user", "uid", "001");
        Map<String, String> userMap = fileService.assignHeaderLine("user", matchedLine);
*/    

    public List<String> retrieveUser(String filename) {
        try {
            List<String> matchedLine = data.retrieveAllLine(filename);

            if (matchedLine == null || matchedLine.isEmpty()) {
                ex.emptyFile(filename);
            }

            return matchedLine;

        } catch (RuntimeException e) { //[TODO]: i prob will this something different in the future
            System.err.println("Error retrieving: " + e.getMessage());
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public Map<String, String> retrieveUser(String filename, String searchColumn, String value) {
        try {
            List<String> matchedLine = data.retrieveOneMatchLine(filename, searchColumn, value);

            if (matchedLine == null || matchedLine.isEmpty()) {
                ex.emptyFile(filename);
            }

            Map<String, String> userMap = data.assignHeaderLine(filename, matchedLine);
            return userMap;

        } catch (RuntimeException e) {
            System.err.println("Error retrieving: " + e.getMessage());
            e.printStackTrace();
            return new HashMap<>();
        }
    }
    
    
    
    public boolean editDetail(String filename, String searchColumn, String searchValue, List<String> newValues) {
        try {
            if (newValues == null || newValues.isEmpty()) {
                throw new IllegalArgumentException("New values cannot be null or empty");
            }

            data.editWholeRow(filename, searchColumn, searchValue, newValues);
            return true;
        } catch (RuntimeException e) {//me is lazy to do string format
            System.err.println("Error editing row in " + filename + " where " + searchColumn + "=" + searchValue + ": " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

//  public List<String> viewRecoveryPlan() {}
    
    public boolean sendEmail(String subject, String content) {
        if (this.email == null || this.email.isEmpty()) {
            System.err.println("User email is not set."); //TODO ya error handling again umm maybe later
            return false;
        }
        return notification.sendEmail(this.email, subject, content);
    }
}
    
