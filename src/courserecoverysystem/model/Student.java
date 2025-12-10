/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package courserecoverysystem.model;

/**
 *
 * @author User
 */
public class Student extends User {
    private String studentID;
    private String userID;
    private String firstName;
    private String lastName;
    private String major;
    private String year;
    
    public Student(
            String studentID,
            String userID,
            String firstName, 
            String lastName,
            String major,
            String year) {
        super(userID, firstName + " " + lastName, null, "student", null); 
        this.studentID = studentID;
        this.userID = userID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.major = major;
        this.year = year;
    }
    
    public String getStudentID() { return studentID; }
    public void setStudentID(String studentID) { this.studentID = studentID; }

    public String getUserID(){ return userID;}
    public void setUserID(String userID){this.userID = userID;}
    
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getMajor() { return major; }
    public void setMajor(String major) { this.major = major; }

    public String getYear() { return year; }
    public void setYear(String year) { this.year = year; }
    
}
