/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package courserecoverysystem.service;
import courserecoverysystem.data.FileData;
import courserecoverysystem.model.Course;
import courserecoverysystem.model.StudentGrade;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author User
 */
public class StudentGradeService {
    private final String FILE = "studentGrade";
    private final FileData fileData = new FileData();
    private final FileService fileService = new FileService();
    
    public List<StudentGrade> getAllGrades(){
        List<StudentGrade> grades = new ArrayList<>();
        
        List<String> lines = fileData.fileRead(FILE);
        for(String line : lines){
            if(line.trim().isEmpty()) continue;
            
            List<String> values = fileService.parseLine(line);
            
            String studentID = values.get(0);
            String courseID = values.get(1);
            int exam = Integer.parseInt(values.get(2));
            int assignment = Integer.parseInt(values.get(3));
            
            grades.add(new StudentGrade(studentID, courseID, exam, assignment));
        }
        return grades;
    }
    
    public boolean hasFailedCourse(String studentID){
        for(StudentGrade grade : getAllGrades()){
            if(grade.getStudentID().equals(studentID) && grade.isFailed()){
                return true;
            }
        }
        return false;
        
    }
    
    public List<StudentGrade> getFailedCourses(String studentID){
        List<StudentGrade> failed = new ArrayList<>();

        for(StudentGrade grade : getAllGrades()){
            if(grade.getStudentID().equals(studentID) && grade.isFailed()){
                failed.add(grade);
            }
        }
        return failed;
    }
    
    // Return all grade records for a student
    public List<StudentGrade> getGradesByStudent(String studentId) {
        List<String> rows = fileService.retrieveAllMatchLine("studentGrade", "studentID", studentId);
        List<StudentGrade> results = new ArrayList<>();

        for (String row : rows) {
            
            List<String> cols = FileData.parseLine(row);
            results.add(new StudentGrade(
                    cols.get(0),                  // studentID
                    cols.get(1),                  // courseID
                    Integer.parseInt(cols.get(2)),// exam_score
                    Integer.parseInt(cols.get(3)) // assignment_score
            ));
        }
        return results;
    }

    // Return ONE grade for (student, course)
    public StudentGrade getStudentGrade(String studentId, String courseId) {
        List<StudentGrade> grades = getGradesByStudent(studentId);

        for (StudentGrade g : grades) {
            if (g.getCourseID().equals(courseId)) {
                return g;
            }
        }
        return null;
    }

    // Compute final numeric score
    public double computeFinalScore(StudentGrade sg, Course c) {
        double examWeight = Double.parseDouble(c.getExamWeight()) / 100.0;
        double assignmentWeight = Double.parseDouble(c.getAssignmentWeight()) / 100.0;
        
        return sg.getExamScore() * examWeight
             + sg.getAssignmentScore() * assignmentWeight;
    }

    // Convert final score → Letter grade
    public String computeLetterGrade(double score) {
        if (score >= 85) return "A";
        if (score >= 70) return "B";
        if (score >= 55) return "C";
        if (score >= 40) return "D";
        return "F";
    }

}
