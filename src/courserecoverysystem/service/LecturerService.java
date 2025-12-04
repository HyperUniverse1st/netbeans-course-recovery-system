/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package courserecoverysystem.service;

import courserecoverysystem.model.Lecturer;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author User
 */
public class LecturerService {

    private final FileService fileService = new FileService();
    private final String LECTURER_FILE = "lecturer";   // File in txtDB folder

    public List<Lecturer> getAllLecturers() {
        List<Lecturer> result = new ArrayList<>();

        // 1. Read all lines from the file (FileService will call FileData for you)
        List<String> lines = fileService.retrieveAllLine(LECTURER_FILE);

        // 2. Convert each line into a Lecturer object
        for (String line : lines) {
            if (line == null || line.trim().isEmpty()) {
                continue; // skip empty lines
            }

            List<String> values = fileService.parseLine(line); // split by "|"
            if (values.size() < 5) {
                continue; // not enough data, skip
            }

            // IMPORTANT: match the order with your lecturer.txt file
            Lecturer lec = new Lecturer(
                    values.get(0).trim(), // id
                    values.get(1).trim(), // name
                    values.get(2).trim(), // email
                    values.get(3).trim(), // phone
                    values.get(4).trim()  // major
            );

            result.add(lec);
        }

        return result;
    }
}

