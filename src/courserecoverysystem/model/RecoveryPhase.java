/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package courserecoverysystem.model;

/**
 *
 * @author User
 */
public class RecoveryPhase {
    private String phaseID;
    private String enrollmentID;
    private String phase; //example Phase1, Phase2, Final
    private String task; //description what to do
    
    public RecoveryPhase(String phaseID, String enrollmentID, String phase, String task){
        this.phaseID = phaseID;
        this.enrollmentID = enrollmentID;
        this.phase = phase;
        this.task = task;
    }
    
    //get method
    public String getPhaseID(){return phaseID;}
    public String getEnrollmentID(){return enrollmentID;}
    public String getPhase(){return phase;}
    public String getTask(){return task;}
    
    public void setPhaseID(String phaseID){this.phaseID = phaseID;}
    public void setEnrollmentID(String enrollmentID){this.enrollmentID = enrollmentID;}
    public void setPhase(String phase){this.phase = phase;}
    public void setTask(String task){this.task = task;}
}
