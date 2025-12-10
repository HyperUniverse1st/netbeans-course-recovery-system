/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package courserecoverysystem.service;

import courserecoverysystem.data.FileData;
import courserecoverysystem.model.RecoveryEnrollment;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/**
 *
 * @author User
 */
public class RecoveryEnrollmentService {
    private static final String FILE = "recoveryEnrollment";
    private final FileData fileData = new FileData();
    private final FileService fileService = new FileService();
    
    public List<RecoveryEnrollment> getAllEnrollments(){
        List<RecoveryEnrollment> list = new ArrayList<>();
        List<String> lines = fileData.fileRead(FILE);
        
        for(String line : lines) {
            if (line == null || line.trim().isEmpty()) continue;
            
            List<String> values = fileService.parseLine(line);
            Map<String, String> map = fileService.assignHeaderLine(FILE, values);
            
            String enrollmentID = map.get("enrollmentID");
            String studentID = map.get("studentID");
            String courseID = map.get("courseID");
            String type = map.get("type");
            String status = map.get("status");
            
            list.add(new RecoveryEnrollment(enrollmentID, studentID, courseID, type, status));
        }
        return list;
    }
    
    public boolean hasAnyEnrollment(String studentID) {
        List<String> lines = fileData.fileRead(FILE);
        for (String line : lines) {
            if (line == null || line.trim().isEmpty()) continue;

            List<String> values = fileService.parseLine(line);
            Map<String, String> map = fileService.assignHeaderLine(FILE, values);

            if (studentID.equals(map.get("studentID"))) {
                return true; // found at least one record
            }
        }
        return false;
    }
    
    public List<RecoveryEnrollment> getEnrollmentsByStudent(String studentId) {
        List<String> rows = fileService.retrieveAllMatchLine("recoveryEnrollment", "studentID", studentId);
        List<RecoveryEnrollment> results = new ArrayList<>();

        for (String row : rows) {
            List<String> cols = FileData.parseLine(row);

            results.add(new RecoveryEnrollment(
                    cols.get(0), // enrollment ID
                    cols.get(1), // student ID
                    cols.get(2), // course ID
                    cols.get(3), // recovery type
                    cols.get(4)  // status
            ));
        }
        return results;
    }

    public int checkAttemptNum(String studentID, String courseID){
        int count = 0;
        
        List<String> lines = fileData.fileRead(FILE);
        for(String line : lines){
            if(line == null || line.trim().isEmpty()) continue;
            
            List<String> values = fileService.parseLine(line);
            Map<String, String> map = fileService.assignHeaderLine(FILE, values);
            
            String sId = map.get("studentID");
            String cId = map.get("courseID");
            
            if(studentID.equals(sId) && courseID.equals(cId)){
                count++;
            }
        }
        return count;
    }
}
