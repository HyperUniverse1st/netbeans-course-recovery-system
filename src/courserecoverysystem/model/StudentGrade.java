/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package courserecoverysystem.model;

/**
 *
 * @author User
 */
public class StudentGrade {
    private String studentID;
    private String courseID;
    private int examScore;
    private int assignmentScore;
    
    public StudentGrade(String studentID, String courseID, int examScore, int assignmentScore){
        this.studentID = studentID;
        this.courseID = courseID;
        this.examScore = examScore;
        this.assignmentScore = assignmentScore;
    }
    
    public String getStudentID() {return studentID;}
    public String getCourseID(){return courseID;}
    public int getExamScore(){return examScore;}
    public int getAssignmentScore(){return assignmentScore;}
    
    public int getTotalScore(){
        return examScore + assignmentScore;
    }
    
    public boolean isFailed(){
        return getTotalScore() < 50;
    }
}

