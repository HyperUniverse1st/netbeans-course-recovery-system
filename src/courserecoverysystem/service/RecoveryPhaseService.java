/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package courserecoverysystem.service;

import courserecoverysystem.data.FileData;
import courserecoverysystem.model.RecoveryPhase;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
/**
 *
 * @author User
 */
public class RecoveryPhaseService {
    private static final String FILE = "recoveryPhase";
    private final FileData fileData = new FileData();
    private final FileService fileService = new FileService();
    
    //1. get all phases for one enrollment
    public List<RecoveryPhase> getPhaseByEnrollment (String enrollmentID){
        List<RecoveryPhase> list = new ArrayList<>();
        
        List<String> lines = fileService.retrieveAllMatchLine(FILE, enrollmentID, enrollmentID);
        
        for(String line : lines){
            if (line == null || line.trim().isEmpty()) continue;
            
            List<String> values = fileService.parseLine(line);
            Map<String, String> map = fileService.assignHeaderLine(FILE, values);
            
            String phaseID = map.get("phaseID");
            String phase = map.get("phase");
            String task = map.get("task");
            
            list.add(new RecoveryPhase(phaseID, enrollmentID, phase, task));
        }
        return list;
    }
    
    // 2. add new phase
    public void addPhase(RecoveryPhase phase){
        List<String> values = Arrays.asList(phase.getPhaseID(), phase.getEnrollmentID(), phase.getPhase(), phase.getTask());
        String line = fileService.createLineString(values);
        fileService.writeAppend(FILE, line + "/n");
    }
    
    //3. delete all the phase for an enrollment example if cancel or recreate the plan
    public void deletePhaseForEnrollment(String enrollmentID){
        fileService.deleteAllMatchLine(FILE, "enrollmentID", enrollmentID);
    }
    
    // 4. simple generator
    public String getNextPhaseID(){
        List<String> lines = fileData.fileRead(FILE);
        int max = 0;
        
        for(String line : lines){
            if (line == null || line.trim().isEmpty())continue;
            
            List<String> values = fileService.parseLine(line);
            String phaseID = values.get(0); //first column
            
            if(phaseID != null && phaseID.startsWith("p")){
                try{
                    int num = Integer.parseInt(phaseID.substring(1));
                    if(num > max) max = num;
                } catch (NumberFormatException ignored){}
            }
            
        }
        int next = max + 1;
        return String.format("P%03d", next); //P001, P002.....
    }
}
