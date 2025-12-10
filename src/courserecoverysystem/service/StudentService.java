/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package courserecoverysystem.service;

import courserecoverysystem.model.Student;
import courserecoverysystem.data.FileData;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/**
 *
 * @author User
 */
public class StudentService {
    private static final String FILE = "student";
    private final FileService fileService = new FileService();
    private final FileData fileData = new FileData();
    
    public List<Student> getAllStudents(){
        List<Student> list = new ArrayList<>();
        
        List<String> lines = fileData.fileRead(FILE);
        System.out.println("Debug lines from file: " + lines.size());
        
        for (String line : lines){
            if (line.trim().isEmpty()) continue;
            
            List<String> values = fileService.parseLine(line);
            Map<String, String> map = fileService.assignHeaderLine("student", values);
            
            System.out.println("Debug map = " + map);
            String studentId = map.get("studentID");
            String userId = map.get("userID");
            String firstName = map.get("firstName");
            String lastName = map.get("lastName");
            String major = map.get("major");
            String year = map.get("year");
            
            list.add(new Student(studentId, userId, firstName, lastName, major, year));
        }
        System.out.println("Debug all students = " + list.size());
        return list;
    }

    public List<Student> getStudentsNeedRecovery(){
        StudentGradeService gradeService = new StudentGradeService();
        RecoveryEnrollmentService recService = new RecoveryEnrollmentService();
        
        List<Student> allStudents = getAllStudents();
        List<Student> result = new ArrayList<>();
        
        for (Student s : allStudents){
            System.out.println("Checking Students: " + s.getStudentID() + " {" + s.getFirstName() + " " + s.getLastName() +")");
            boolean hasFailed = gradeService.hasFailedCourse(s.getStudentID());
            boolean hasRecovery = recService.hasAnyEnrollment(s.getStudentID());
            
            if(hasFailed && !hasRecovery){
                result.add(s);
            }
        }
        return result;
    }
}
