/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package courserecoverysystem.service;

import courserecoverysystem.data.FileData;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author User
 */
public class ClassService {
    private static final String FILE = "class";
    
    private final FileData fileData = new FileData();
    private final FileService fileService = new FileService();
    
    public List<String> getAllClassLines(){
        return fileData.fileRead(FILE);
    }
    
    public String getNextClassId(){
        List<String> lines = fileData.fileRead(FILE);
        
        if(lines.isEmpty()){
            return "C001";
        }
        
        String lastLine = null;
        for (int i = lines.size() - 1; i >= 0; i--){
            String line = lines.get(i).trim();
            if(!line.isEmpty()){
                lastLine = line;
                break;
            }
        }
        
        if(lastLine == null){
            return "C001";
        }
        
        List<String> values = fileService.parseLine(lastLine);
        if(values.isEmpty()){
            return "C001";
        }
        
        String lastId = values.get(0); //example C005
        int num = 0;
        try{
            num = Integer.parseInt(lastId.substring(1)); //remove C
        } catch (NumberFormatException e){
            return "C001";
        }
        
        num++;
        return String.format("C%03d", num); //C006,C007...
        
    }
    
    public void saveClass(String classId,
                          String subject,
                          String lecturerName,
                          String date,
                          String timeSlot,
                          List<String> studentIds) {

        String studentJoined = String.join(",", studentIds);

        List<String> values = new ArrayList<>();
        values.add(classId);
        values.add(subject);
        values.add(lecturerName);
        values.add(date);
        values.add(timeSlot);
        values.add(studentJoined);

        String line = fileService.createLineString(values);

        List<String> lines = fileData.fileRead(FILE);
        lines.add(line);

        String content = fileService.createContentString(lines);
        fileData.fileOverrrideWrite(FILE, content);
    }
    
    public List<String> findStudentConflicts(String date, String time, List<String> studentIds){
        List<String> conflictStudents = new ArrayList<>();
        List<String> lines = fileData.fileRead("class");
        
        for (String line : lines){
            if (line.trim().isEmpty()) continue;
            
            List<String> values = fileService.parseLine(line);
            String existingDate = values.get(3);
            String existingTime = values.get(4);
            
            if(!existingDate.equals(date)) continue;
            if(!existingTime.equals(time)) continue;
            
            String[] assigned = values.get(5).split(",");
            
            for(String sid : studentIds){
                for(String a : assigned){
                    if(sid.trim().equals(a.trim())){
                        conflictStudents.add(sid);
                    }
                }
            }
        }
        return conflictStudents;            
    }
    
    public void deleteClasses(List<String> classIds){
        if(classIds == null || classIds.isEmpty()) return;
        
        List<String> lines = fileData.fileRead(FILE);
        List<String> newLines = new ArrayList<>();
        
        for(String line : lines){
            if(line == null || line.trim().isEmpty()){
                continue;
            }
            
            //class format: 
            List<String> values = fileService.parseLine(line);
            if(values.isEmpty()){
                continue;
            }
            
            String classId = values.get(0).trim();
            
            //if this class is not in delete list then keep it
            if(!classIds.contains(classId)){
                newLines.add(line);
            }
        }
        String content = fileService.createContentString(newLines);
        fileData.fileOverrrideWrite(FILE, content);
    }
}
