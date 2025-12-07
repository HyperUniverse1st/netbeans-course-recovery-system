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
        if(major == null){
            return result;
        }
        
        for (Student s : getAllStudents()){
            if(major.equalsIgnoreCase(s.getMajor())
                    && s.isNeedRecovery()
                    && !s.isAssigned()){
                result.add(s);
            }
        }
        return result;
    }
    //get all student who need recovery and are not assign yet
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
    
    public void markStudentsAssigned(List<String> studentIds) {
        if (studentIds == null || studentIds.isEmpty()) {
            return;
        }

        List<String> lines = fileData.fileRead(FILE);   // FILE = "student"
        List<String> updatedLines = new ArrayList<>();

        for (String line : lines) {
            if (line == null || line.trim().isEmpty()) {
                updatedLines.add(line);
                continue;
            }

            // student.txt format:
            // studentID|firstName|lastName|major|year|email|needRecovery|assigned
            List<String> values = fileService.parseLine(line);
            if (values.size() < 8) {
                // bad line, just keep it as is
                updatedLines.add(line);
                continue;
            }

            String id = values.get(0); // S001, S002, ...

            // if this student was selected, set assigned = "1"
            if (studentIds.contains(id)) {
                values.set(7, "1");    // index 7 = assigned column
            }

            // rebuild the line and add to updated list
            String newLine = fileService.createLineString(values);
            updatedLines.add(newLine);
        }

        // write everything back to student file
        String content = fileService.createContentString(updatedLines);
        fileData.fileOverrrideWrite(FILE, content);
    }

}
