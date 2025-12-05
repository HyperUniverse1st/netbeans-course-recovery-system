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
    
    //Generate class ID example C001
    public String getNextClassId(){
        List<String> lines = fileData.fileRead(FILE);
        if(lines.isEmpty()){
            return "C001";
        }
        String lastLine = lines.get(lines.size() - 1).trim();
        if (lastLine.isEmpty()){
            return "C001";
        }
        
        List<String> values = fileService.parseLine(lastLine); // split |
        String lastId = values.get(0); //C00"x"
        int num = Integer.parseInt(lastId.substring(1)); //remove "C"
        num ++;
        return String.format("C%03d",num);
    }
    
    public void saveClass(
            String classId,
            String subject,
            String lecturerName,
            String date,
            String Time,
            List<String> studentIds
            ){
    String studentJoined = String.join(",", studentIds);
    
    List<String> values = new ArrayList<>();
    values.add(classId);
    values.add(subject);
    values.add(lecturerName);
    values.add(date);
    values.add(Time);
    values.add(studentJoined);
    
    String line = fileService.createLineString(values);
    
    //read existing lines, add new and overwrite the file
    List<String> lines = fileData.fileRead(FILE);
    lines.add(line);
    fileData.fileOverrrideWrite(FILE, fileService.createContentString(lines));
    }
}
