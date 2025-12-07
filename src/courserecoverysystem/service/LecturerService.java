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
    // This should match your lecturer file in txtDB (lecturer.txt)
    private final String LECTURER_FILE = "lecturer";

    /**
     * Read all lecturers from lecturer file and convert to Lecturer objects
     */
    public List<Lecturer> getAllLecturers() {
        List<Lecturer> result = new ArrayList<>();

        // 1. Read all lines from the file
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

            // Match the order with lecturer.txt: ID|Name|Email|Phone|Subject(Major)
            Lecturer lec = new Lecturer(
                    values.get(0).trim(), // id
                    values.get(1).trim(), // name
                    values.get(2).trim(), // email
                    values.get(3).trim(), // phone
                    values.get(4).trim()  // major / subject
            );

            result.add(lec);
        }

        return result;
    }

    /**
     * Get all unique subjects/majors from lecturers
     * Used to populate Subject combo box
     */
    public List<String> getAllSubjects() {
        List<String> subjects = new ArrayList<>();

        for (Lecturer lec : getAllLecturers()) {
            String subject = lec.getMajor();
            if (subject == null || subject.trim().isEmpty()) {
                continue;
            }
            // avoid duplicates
            if (!subjects.contains(subject)) {
                subjects.add(subject);
            }
        }

        return subjects;
    }

    /**
     * Get all lecturers who teach a specific major/subject
     */
    public List<Lecturer> getLecturersByMajor(String major) {
        List<Lecturer> result = new ArrayList<>();
        if (major == null) {
            return result;
        }

        for (Lecturer lec : getAllLecturers()) {
            if (major.equalsIgnoreCase(lec.getMajor())) {
                result.add(lec);
            }
        }

        return result;
    }
}
