/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package courserecoverysystem.service;
import courserecoverysystem.data.FileData;
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
}
