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
}
