/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package courserecoverysystem.service;

import courserecoverysystem.data.FileData;
import courserecoverysystem.model.Course;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/**
 *
 * @author User
 */
public class CourseService {
    private static final String FILE = "course";
    private final FileData fileData = new FileData();
    private final FileService fileService = new FileService();
    
    public List<Course> getAllCourses(){
        List<Course> list = new ArrayList<>();
        
        List<String> lines = fileData.fileRead(FILE);
        System.out.println("Debug course lines from file: " + lines.size());
        
        for(String line : lines){
            if (line == null || line.trim().isEmpty()) continue;
            
            List<String> values = fileService.parseLine(line);
            Map<String, String> map = fileService.assignHeaderLine(FILE, values);
            System.out.println("Debug course map: " + map);
            
            String courseID = map.get("courseID");
            String lecturerID       = map.get("lecturerID");
            String courseName       = map.get("courseName");
            String credit           = map.get("credit");
            String semester         = map.get("semester");
            String examWeight       = map.get("examWeight");
            String assignmentWeight = map.get("assignmentWeight");
            
            Course course = new Course(courseID, lecturerID, courseName, credit, semester, examWeight, assignmentWeight);
            list.add(course);
        }
        System.out.println("Debug all course: " + list.size());
        return list;
    }
    //small helper: exam + assignment must be 100
    private boolean validateWeights(String examWeight, String assignmentWeight){
        try{
            int e = Integer.parseInt(examWeight);
            int a = Integer.parseInt(assignmentWeight);
            return (e + a) == 100;
        } catch (NumberFormatException ex){
            return false;
        }
    }
    
    //CREATE: add new course
        public boolean addCourse(Course course){
            if(!validateWeights(course.getExamWeight(),course.getAssignmentWeight())){
                System.out.println("Invalid weights: exam + assignment must = 100.");
                return false;
            }
            
            String line = String.join("|", 
                    course.getCourseID(),
                    course.getLecturerID(),
                    course.getCourseName(),
                    course.getCredit(),
                    course.getSemester(),
                    course.getExamWeight(),
                    course.getAssignmentWeight()
            );
            
            fileData.fileAppendWrite(FILE, line); //FILE = "course"
            return true;
        }
}
