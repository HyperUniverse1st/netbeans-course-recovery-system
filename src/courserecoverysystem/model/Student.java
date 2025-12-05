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
    private String firstName;
    private String lastName;
    private String major;
    private String year;
    private String email;
    private boolean needRecovery; // true = failed, need course recovery
    private boolean assigned; // true = already assigned to a recover class
    
    public Student(
            String studentID, 
            String firstName, 
            String lastName,
            String major,
            String year,
            String email,
            boolean needRecovery, boolean assigned) {
        super(studentID, firstName + " " + lastName, email, "student", ""); 
        this.studentID = studentID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.major = major;
        this.year = year;
        this.email = email;
        this.needRecovery = needRecovery;
        this.assigned = assigned;
    }
    
    public String getStudentID() { return studentID; }
    public void setStudentID(String studentID) { this.studentID = studentID; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getMajor() { return major; }
    public void setMajor(String major) { this.major = major; }

    public String getYear() { return year; }
    public void setYear(String year) { this.year = year; }
    
    public String getEmail() { return email; } 
    public void setEmail(String email) { this.email = email; }

    public boolean isNeedRecovery() { return needRecovery; }
    public void setNeedRecovery(boolean needRecovery) { this.needRecovery = needRecovery; }

    public boolean isAssigned() { return assigned; }
    public void setAssigned(boolean assigned) { this.assigned = assigned; }
}
