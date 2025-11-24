/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package courserecoverysystem.model;

/**
 *
 * @author seany
 */


/*
there should be only one user and as such you should use currentUser to get values
*/

public class User { //TODO please we really need the values for the user
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
}
    
