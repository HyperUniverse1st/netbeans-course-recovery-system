/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package courserecoverysystem.model;

/**
 *
 * @author User
 */
public class RecoveryEnrollment {
    private String enrollmentID;
    private String studentID;
    private String courseID;
    private String type;
    private String status;
    
    public RecoveryEnrollment(String enrollmentID, String studentID, String courseID, String type, String status ){
        this.enrollmentID = enrollmentID;
        this.studentID = studentID;
        this.courseID = courseID;
        this.type = type;
        this.status = status;
        }
        
    // get method
    public String getEnrollmentID(){return enrollmentID;}
    public String getStudentID(){return studentID;}
    public String getCourseID(){return courseID;}
    public String getType(){return type;} 
    public String getStatus(){return status;}
}
