/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package courserecoverysystem.service;

import courserecoverysystem.model.Student;
import courserecoverysystem.data.FileData;
import courserecoverysystem.model.Course;
import courserecoverysystem.model.StudentGrade;
import courserecoverysystem.model.User;
import courserecoverysystem.service.FileService;
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
    private final StudentGradeService gradeService = new StudentGradeService();
    
    public List<Student> getAllStudents(){
        List<Student> list = new ArrayList<>();
        
        List<String> lines = fileData.fileRead(FILE);
        System.out.println("Debug lines from file: " + lines.size());
        
        for (String line : lines){
            if (line.trim().isEmpty()) continue;
            
            List<String> values = fileService.parseLine(line);
            Map<String, String> map = fileService.assignHeaderLine("student", values);
            
            System.out.println("Debug map = " + map);
            String studentId = map.get("student_id");
            String userId = map.get("userID_id");
            String firstName = map.get("first_Name");
            String lastName = map.get("last_Name");
            String major = map.get("major");
            String year = map.get("year");
            
            list.add(new Student(studentId, userId, firstName, lastName, major, year));
        }
        System.out.println("Debug all students = " + list.size());
        return list;
    }
    
    //find studentid for a given userid
    public String getStudentIdByUserId (String userId){
        if(userId == null || userId.isEmpty()) return null;
        
        List<String> lines = fileService.retrieveAllLine("student");
        if(lines == null || lines.isEmpty()) return null;
        
        int studentIdIdx = fileService.getHeaderIndex("student", "studentID");
        int userIdIdx = fileService.getHeaderIndex("student", "userID");

        for(String line : lines){
            if(line == null || line.trim().isEmpty()) continue;
            
            List<String> cols = fileService.parseLine(line);
            if(cols.size() <= Math.max(studentIdIdx, userIdIdx)) continue;
            
            String userIdFromFile = cols.get(userIdIdx).trim();
            String studentIdFromFile = cols.get(studentIdIdx).trim();
            
            if(userId.equals(userIdFromFile)){
                return studentIdFromFile;
            }
        }
        return null; // not found
    }
    
    public String getCurrentStudentId(){
        User currentUser = User.getCurrentUser();
        System.out.println("Debug use, current user: " + currentUser);
        if(currentUser == null){
            System.out.println("no current user yet.");
            return null;
        }
        String currentUserId = currentUser.getUID();
        return getStudentIdByUserId(currentUserId);
    }
    
    public Student getStudentById(String studentId){
        for (Student s : getAllStudents()){
            if(studentId.equals(s.getStudentID())){
                return s; //found it
            }
        }
        return null;
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
    
    public StudentGrade getStudentGrade(String studentId, String courseId){
        List<StudentGrade> grades = gradeService.getGradesByStudent(studentId);
        for(StudentGrade g : grades){
            if(g.getCourseID().equals(courseId)){
                return g;
            }
        }
        return null;
    }
    
    // Calculate CGPA for one student based on studentGrade.txt + course.txt
    public double computeCGPA(String studentId) {
        CourseService courseService = new CourseService();

        List<StudentGrade> grades = gradeService.getGradesByStudent(studentId);
        if (grades.isEmpty()) return 0.0;

        double totalQualityPoints = 0.0;
        double totalCredits = 0.0;

        for (StudentGrade sg : grades) {
            Course c = courseService.getCourseById(sg.getCourseID());
            if (c == null) continue;

            int credit;
            try {
                credit = Integer.parseInt(c.getCredit());  // credit stored as String
            } catch (NumberFormatException ex) {
                continue;
            }

            double finalScore = gradeService.computeFinalScore(sg, c);
            double gradePoint = convertToGradePoint(finalScore);

            totalQualityPoints += gradePoint * credit;
            totalCredits += credit;
        }

        if (totalCredits == 0.0) return 0.0;
        return totalQualityPoints / totalCredits;
    }

    // helper: numeric score → grade point
    private double convertToGradePoint(double score) {
        if (score >= 85) return 4.0;
        if (score >= 70) return 3.0;
        if (score >= 55) return 2.0;
        if (score >= 40) return 1.0;
        return 0.0;
    }

    
}
