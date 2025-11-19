/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package courserecoverysystem.service;

/**
 *
 * @author seany
 */
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Arrays;
import courserecoverysystem.data.FileData;

/*
Here you need to know is 
    int getHeaderIndex(String filename, String column) - this will just return the index of the header

    String createLineString(List<String> values) - this just make array into a string with | inbetween

    String createContentString(List<String> lines) - this will make things in an Arraylist to have /n inbetween each other

    List<String> parseLine (String line) - does the opposite of createLineString(List<String> values)

    Map<String, String> assignHeaderLine(String filename, List<String> values) - this is should be used during your retrieve so you can get the
    value cleanly


    THIS IS THE IMPORTANT ONES
    List<String> retrieveOneMatchLine(String filename, String searchColumn, String value)
    List<String> retrieveAllMatchLine(String filename, String searchColumn, String value)
    List<String> retrieveAllLine(String filename)
    void deleteOneMatchLine(String filename, String searchColumn, String value)
    void deleteAllMatchLine(String filename, String searchColumn, String value)
    void editOneMatchLine(String filename, String searchColumn, String searchValue, String editColumn, String editValue)
    void editAllMatchLine(String filename, String searchColumn, String searchValue, String editColumn, String editValue)
    void writeAppend(String filename, String content)
    void writeOverride(String filename, String content)
    
    ykw just call me if you got any question - Yap
*/

public class FileService {
    String separator = "//|";
    final private String[] user = {"column1", "column2"}; 
    //need to add all the headers for the file
    
    public List<String> dbHeaderSearch(String filename) { 
        if (filename.endsWith(".txt")) {
            filename = filename.substring(0, filename.length() - 4);
        }

        switch (filename) {
            case "user":
                return Arrays.asList(user);
            default:
                return new ArrayList<>();
        }
    }
    
    public int getHeaderIndex(String filename, String column) {
        List<String> headers = dbHeaderSearch(filename);
        return headers.indexOf(column);
    }
    
    
    
    public List<String> trim(List<String> values) {
        List<String> trimmedValues = new ArrayList<>();
        
        for (String value : values) {
            if (value == null || value.isEmpty()) {
                continue;
            }
            value = value.strip();
            trimmedValues.add(value);
        }
        return trimmedValues;   
    }
    
    
    
    public boolean lengthCheck(List<String> header, List<String> line) {
        return header.size() == line.size();
    }    
    

    public String createLineString(List<String> values) {
        if (values == null || values.isEmpty()) {
            return "";
        }
        
        return String.join(separator, trim(values));
    }
    
    
    
    public String createContentString(List<String> lines) {
        StringBuilder content = new StringBuilder();
        lines = trim(lines);
        
        for (String line : lines) {
            content.append(line).append("\n");
        }    
        return content.toString();
    }    
    
    
    
    public List<String> parseLine (String line) {
        String[] parts = line.split(separator);
        return Arrays.asList(parts);
    }
    
    
    
    public Map<String, String> assignHeaderLine(String filename, List<String> values) {
        List<String> header = dbHeaderSearch(filename);
        Map<String, String> mappedValues = new HashMap<>();
        
        if (!lengthCheck(header, values)) {
            return mappedValues; //TODO add error handling, like this one really needs one
        }
        
        for (int i = 0; i < values.size(); i++) {
            mappedValues.put(header.get(i), values.get(i));
        }
        return mappedValues;
    }
            
    
    
    //[TODO] Need to add error handling here 
    public List<String> retrieveOneMatchLine(String filename, String searchColumn, String value) {

        List<String> header = dbHeaderSearch(filename);
        int columnIndex = header.indexOf(searchColumn);
        if (columnIndex == -1) {
            return new ArrayList<>();
        }

        FileData data = new FileData();
        List<String> lines = data.fileRead(filename);

        for (String line : lines) {
            List<String> values = parseLine(line);
            if (lengthCheck(header, values) && values.get(columnIndex).equals(value)) {
                return values;
            }
        }
        return new ArrayList<>();
    }
         
    public List<String> retrieveAllMatchLine(String filename, String searchColumn, String value) {

        List<String> header = dbHeaderSearch(filename);
        int columnIndex = header.indexOf(searchColumn);
        if (columnIndex == -1) {
            return new ArrayList<>();
        }

        FileData data = new FileData();
        List<String> lines = data.fileRead(filename);
        List<String> matchedLines = new ArrayList<>();

        for (String line : lines) {
            List<String> values = parseLine(line);
            if (lengthCheck(header, values) && values.get(columnIndex).equals(value)) {
                matchedLines.add(line);
            }
        }
        return matchedLines;
    }
    
    public List<String> retrieveAllLine(String filename) {
        FileData data = new FileData();
        return data.fileRead(filename);
    } 


    
    public void deleteOneMatchLine(String filename, String searchColumn, String value) {

        List<String> header = dbHeaderSearch(filename);
        int columnIndex = header.indexOf(searchColumn);
        boolean deleted = false;
        
        if (columnIndex == -1) {
            return;
        }

        FileData data = new FileData();
        List<String> lines = data.fileRead(filename);
        List<String> unmatchedLines = new ArrayList<>();

        for (String line : lines) {
            List<String> values = parseLine(line);
            if (deleted) {
                unmatchedLines.add(line);
                continue;
            }
            if (lengthCheck(header, values) && values.get(columnIndex).equals(value)) {
                deleted = true;
                continue;
            }
            unmatchedLines.add(line);
        }
        data.fileOverrrideWrite(filename, createContentString(unmatchedLines));
    }
    

    
    public void deleteAllMatchLine(String filename, String searchColumn, String value) {

        List<String> header = dbHeaderSearch(filename);
        int columnIndex = header.indexOf(searchColumn);
        if (columnIndex == -1) {
            return;
        }

        FileData data = new FileData();
        List<String> lines = data.fileRead(filename);
        List<String> unmatchedLines = new ArrayList<>();

        for (String line : lines) {
            List<String> values = parseLine(line);
            if (lengthCheck(header, values) && values.get(columnIndex).equals(value)) {
                continue;
            }
            unmatchedLines.add(line);
        }
        data.fileOverrrideWrite(filename, createContentString(unmatchedLines));
    }
    

    
    public void editOneMatchLine(String filename, String searchColumn, String searchValue, String editColumn, String editValue) {
        List<String> header = dbHeaderSearch(filename);
        int searchColumnIndex = header.indexOf(searchColumn);
        int editColumnIndex = header.indexOf(editColumn);
        boolean edited = false;

        if (searchColumnIndex == -1 || editColumnIndex == -1) {
            return;
        }
        
        FileData data = new FileData();
        List<String> lines = data.fileRead(filename);
        List<String> editedLines = new ArrayList<>();

        for (String line : lines) {
            List<String> values = parseLine(line);
            if (lengthCheck(header, values) && values.get(searchColumnIndex).equals(searchValue) && !edited) {
                values.set(editColumnIndex, editValue);
                edited = true;
            }
            editedLines.add(line);
        }
        data.fileOverrrideWrite(filename, createContentString(editedLines));
    }
    
    public void editAllMatchLine(String filename, String searchColumn, String searchValue, String editColumn, String editValue) {
        List<String> header = dbHeaderSearch(filename);
        int searchColumnIndex = header.indexOf(searchColumn);
        int editColumnIndex = header.indexOf(editColumn);

        if (searchColumnIndex == -1 || editColumnIndex == -1) {
            return;
        }
        
        FileData data = new FileData();
        List<String> lines = data.fileRead(filename);
        List<String> editedLines = new ArrayList<>();

        for (String line : lines) {
            List<String> values = parseLine(line);
            if (lengthCheck(header, values) && values.get(searchColumnIndex).equals(searchValue)) {
                values.set(editColumnIndex, editValue);
            }
            editedLines.add(line);
        }
        data.fileOverrrideWrite(filename, createContentString(editedLines));
    }
    
    
    
    public void writeAppend(String filename, String content) {
        FileData data = new FileData();
        data.fileAppendWrite(filename, content);
    }
    
    public void writeOverride(String filename, String content) {
        FileData data = new FileData();
        data.fileOverrrideWrite(filename, content);
    }
    
    //TODO add string match
}