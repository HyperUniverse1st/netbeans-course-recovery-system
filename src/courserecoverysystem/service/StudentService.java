/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package courserecoverysystem.service;

import courserecoverysystem.model.Student;
import courserecoverysystem.data.FileData;
import java.util.*;
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
            Map<String, String> map = fileService.assignHeaderLine("student.txt", values);
            
            System.out.println("Debug map = " + map);
            String id = map.get("studentID");
            String fn = map.get("firstName");
            String ln = map.get("lastName");
            String major = map.get("major");
            String year = map.get("year");
            String email = map.get("email");
            boolean needRec = "1".equals(map.get("needRecovery"));
            boolean assigned = "1".equals(map.get("assigned"));
            
            list.add(new Student(id, fn, ln, major, year, email, needRec, assigned));
        }
        System.out.println("Debug all students = " + list.size());
        return list;
    }
    
    public List<Student> getFailedStudentsByMajor(String major){
        List<Student> result = new ArrayList<>();
        
        for(Student s : getAllStudents()){
            if(s.getMajor().equalsIgnoreCase(major)
                    && s.isNeedRecovery()
                    && !s.isAssigned()){
                    result.add(s);
                }
            }
        return result;
    }
    
    public List<Student> getUnassignedStudentsNeedingRecovery(){
        List<Student> all = getAllStudents();
        List<Student> result = new ArrayList<>();
        
        for (Student s : all) {
            if (s.isNeedRecovery() && !s.isAssigned()){
                result.add(s);
            }
        }
        System.out.println("Debug unassigned need recovery = " + result.size());
        return result;
    }
}
