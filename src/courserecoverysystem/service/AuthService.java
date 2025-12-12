/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package courserecoverysystem.service;

/**
 *
 * @author seany
 */

import courserecoverysystem.model.User;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/*
Okay so here the method you need to know is
    validateCredential if true then the user model will be init
    resetpassword just reset password... ya that is it
*/
//TODO what is the file name btw?
public class AuthService {
    //TODO we really need to figure out the columns
    private Map<String, String> userMap = new HashMap<>();
    private String userFile = "user";
    final private String emailColumn = "email";
    final private String passwordColumn = "password";
    private User currentUser;  //TODO you think we need this?

    public boolean validateCredentials(String email, String password) {
        FileService file = new FileService();
        
        List<String> userData = file.retrieveOneMatchLine(userFile, emailColumn, email);
        //TODO add whatever if username not found
        if (userData.isEmpty()) {
            return false;
        }

        int passwordColumnIndex = file.getHeaderIndex(userFile, passwordColumn);

        if (userData.get(passwordColumnIndex).equals(password)) {

            mapUserData(userFile, userData);
            System.out.println("usermap debug: " + userMap);

            currentUser = new User(); //TODO add all user values 
//            currentUser.setName(userMap.get("name")); TODO you need to add this some time evenutally      
            currentUser.setUID(userMap.get("user_id"));  
            currentUser.setEmail(userMap.get("email"));  
            currentUser.setRole(userMap.get("role"));       
            currentUser.setPassword(userMap.get("password"));              

            User.setCurrentUser(currentUser); //TODO you think we need this?
            System.out.println("debug use: " + currentUser.getUID());
            return true;
        }
        //TODO if password is not a match
        return false;
    }

    public void mapUserData(String filename, List<String> values) {
        FileService file = new FileService();
        userMap = file.assignHeaderLine(filename, values);
    }

    public Map<String, String> getUserMap() {
        return userMap;
    }
    
    public boolean resetPassword(String email, String password) {
        FileService file = new FileService();
        file.editOneMatchLine(userFile, emailColumn, email, passwordColumn, password);
        return true; //TODO it is temp will always give true i will prob fix this later
    }
}
