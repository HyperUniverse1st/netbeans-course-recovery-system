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
import java.nio.file.Paths;
import java.nio.file.Path;

public class FileData {
    
    private String basePath;
    
    public FileData() {
        this.basePath = "src/textDB/";
    }
    
    public FileData(String basePath) {
        this.basePath = basePath;
    }

    private String getFilePath(String filename) {
        Path path = Paths.get(basePath, filename + ".txt");
        return path.toString();
    }
    
    public List<String> fileRead(String filename) {
        List<String> fileData = new ArrayList<>();
        String line;
        
        try {
            FileReader fileReader = new FileReader(getFilePath(filename));
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
            FileWriter fileWriter = new FileWriter(getFilePath(filename), true);
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
            FileWriter fileWriter = new FileWriter(getFilePath(filename), false);
            BufferedWriter writer = new BufferedWriter(fileWriter);
            writer.write(content);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    
    public void fileCreate(String filename) {
        try {
            FileWriter fileWriter = new FileWriter(getFilePath(filename), true);
            BufferedWriter writer = new BufferedWriter(fileWriter);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
