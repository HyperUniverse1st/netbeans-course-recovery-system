/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package courserecoverysystem.model;

/**
 *
 * @author User
 */
public class Lecturer {
    private String id;
    private String name;
    private String email;
    private String phone;   
    private String major;
    
    public Lecturer (String id, String name, String email, String phone, String major){
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.major = major;
    }
    
    public Lecturer(String line){
        String[] p = line.split("\\|");
        this.id = p[0];
        this.name = p[1];
        this.email = p[2];
        this.phone = p[3];
        this.major = p[4];
    }
    
    public String getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public String getMajor() { return major; }
    
    @Override
    public String toString(){
        return name;
    }
}
