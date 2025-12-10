/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package courserecoverysystem.model;

/**
 *
 * @author User
 */
public class Course {
    private String courseID;
    private String lecturerID;
    private String courseName;
    private String credit;
    private String semester;
    private String examWeight;
    private String assignmentWeight;
    
    public Course(String courseID, String lecturerID, String courseName, String credit, String semester, String examWeight, String assignmentWeight){
        this.courseID = courseID;
        this.lecturerID = lecturerID;
        this.courseName = courseName;
        this.credit = credit;
        this.semester = semester;
        this.examWeight = examWeight;
        this.assignmentWeight = assignmentWeight;
    }
    
    public String getCourseID() { return courseID; }
    public void setCourseID(String courseID) { this.courseID = courseID; }

    public String getLecturerID() { return lecturerID; }
    public void setLecturerID(String lecturerID) { this.lecturerID = lecturerID; }

    public String getCourseName() { return courseName; }
    public void setCourseName(String courseName) { this.courseName = courseName; }

    public String getCredit() { return credit; }
    public void setCredit(String credit) { this.credit = credit; }

    public String getSemester() { return semester; }
    public void setSemester(String semester) { this.semester = semester; }

    public String getExamWeight() { return examWeight; }
    public void setExamWeight(String examWeight) { this.examWeight = examWeight; }

    public String getAssignmentWeight() { return assignmentWeight; }
    public void setAssignmentWeight(String assignmentWeight) { this.assignmentWeight = assignmentWeight; }
}
