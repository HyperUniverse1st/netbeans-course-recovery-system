/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package courserecoverysystem.service;

/**
 *
 * @author seany
 */
import java.util.HashMap;
import java.util.List;
import java.util.Map;


//TODO what is the file name btw?
public class AuthService {
    //TODO we really need to figure out the columns
    private Map<String, String> userMap = new HashMap<>();
    private String userFile = "user.txt";
    private String usernameColumn = "username";
    private String passwordColumn = "password";

    public boolean validateCredentials(String username, String password) {
        FileService file = new FileService();

        List<String> userData = file.retrieveOneMatchLine(userFile, usernameColumn, username);
        //TODO add whatever if username not found
        if (userData.isEmpty()) {
            return false;
        }

        int passwordColumnIndex = file.getHeaderIndex(userFile, passwordColumn);

        if (userData.get(passwordColumnIndex).equals(password)) {
            mapUserData(userFile, userData);
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
    //TODO
    //public resetPassword
}
