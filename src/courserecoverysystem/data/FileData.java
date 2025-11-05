/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package courserecoverysystem.data;

/**
 *
 * @author seany
 */
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class FileData {
    
    public List<String> fileRead(String filename) {
        List<String> fileData = new ArrayList<>();
        String line;
        
        try {
            FileReader fileReader = new FileReader(filename);
            BufferedReader reader = new BufferedReader(fileReader);
            
            while((line = reader.readLine()) != null) {
                fileData.add(line);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileData;
    }
    
    
    
    public void fileAppendWrite(String filename, String content) {
        try {
            FileWriter fileWriter = new FileWriter(filename, true);
            BufferedWriter writer = new BufferedWriter(fileWriter);
            writer.newLine(); 
            writer.write(content);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    
    public void fileOverrrideWrite(String filename, String content) {
        try {
            FileWriter fileWriter = new FileWriter(filename, false);
            BufferedWriter writer = new BufferedWriter(fileWriter);
            writer.write(content);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    
    public void fileCreate(String filename) {
        try {
            FileWriter fileWriter = new FileWriter(filename, true);
            BufferedWriter writer = new BufferedWriter(fileWriter);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
