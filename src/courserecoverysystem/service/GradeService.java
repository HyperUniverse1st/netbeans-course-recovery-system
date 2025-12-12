/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package courserecoverysystem.service;

/**
 *
 * @author seany
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

public class GradeService {
    private static final FileService file = new FileService();


    
    public double getCGPA(String studentId) {
        return getCGPA(studentId, null);
    }
    
    public double getCGPA(String studentId, String semester) {
        List<List<String>> grades = getGradesBySemester(studentId, semester);
        if (grades.isEmpty()) return 0.0;

        double totalQualityPoints = 0;
        double totalCredits = 0;

        int courseIdIndex = file.getHeaderIndex("studentgrade", "course_id");
        int finalScoreIndex = file.getHeaderIndex("studentgrade", "final_score");
        int creditIndex = file.getHeaderIndex("course", "credit");

        for (List<String> gradeCols : grades) {
            String courseId = gradeCols.get(courseIdIndex);
            int finalScore = Integer.parseInt(gradeCols.get(finalScoreIndex));

            List<String> courseRow = file.retrieveOneMatchLine("course", "course_id", courseId);
            if (courseRow.isEmpty()) continue;

            int courseCredit = Integer.parseInt(courseRow.get(creditIndex));
            double gradePoint = convertToGradePoint(finalScore);

            totalQualityPoints += gradePoint * courseCredit;
            totalCredits += courseCredit;
        }

        return totalCredits == 0 ? 0.0 : totalQualityPoints / totalCredits;
    }

    
    
    public List<List<String>> getGradesBySemester(String studentId, String semester) {
        List<String> allGrades = file.retrieveAllMatchLine("studentgrade", "student_id", studentId);
        List<List<String>> filteredGrades = new ArrayList<>();
        if (allGrades.isEmpty()) return filteredGrades;

        int courseIdIndex = file.getHeaderIndex("studentgrade", "course_id");
        int semesterIndex = file.getHeaderIndex("course", "semester");

        for (String record : allGrades) {
            List<String> gradeCols = file.parseLine(record);
            String courseId = gradeCols.get(courseIdIndex);

            List<String> courseRow = file.retrieveOneMatchLine("course", "course_id", courseId);
            if (courseRow.isEmpty()) continue;

            String courseSemester = courseRow.get(semesterIndex);
            if (semester == null || courseSemester.equals(semester)) {
                filteredGrades.add(gradeCols);
            }
        }
        return filteredGrades;
    }

    
    
    public Set<String> getAllStudentSemester(String studentId) {
        List<String> gradeRecords = file.retrieveAllMatchLine("studentgrade", "student_id", studentId);
        Set<String> semesters = new HashSet<>();

        if (gradeRecords.isEmpty()) return semesters;

        int courseIdIndex = file.getHeaderIndex("studentgrade", "course_id");
        int semesterIndex = file.getHeaderIndex("course", "semester");

        for (String record : gradeRecords) {
            List<String> gradeCols = file.parseLine(record);
            String courseId = gradeCols.get(courseIdIndex);

            List<String> courseRow = file.retrieveOneMatchLine("course", "course_id", courseId);
            if (courseRow.isEmpty()) continue;

            String courseSemester = courseRow.get(semesterIndex);
            semesters.add(courseSemester);
        }
        return semesters;
    }

    
    
    private double convertToGradePoint(int score) {
        if (score >= 85) return 4.0;
        if (score >= 70) return 3.0;
        if (score >= 55) return 2.0;
        if (score >= 40) return 1.0;
        return 0.0;
    }

    
    
    public int getCourseCredit(String courseId) {
        List<String> courseRow = file.retrieveOneMatchLine("course", "course_id", courseId);
        if (courseRow.isEmpty()) return 0;

        int creditIndex = file.getHeaderIndex("course", "credit");
        return Integer.parseInt(courseRow.get(creditIndex));
    }
    
    
    
    public List<String> getFailedCourses(String studentId) {
        List<List<String>> allGrades = getGradesBySemester(studentId, null);
        List<String> failedCourses = new ArrayList<>();

        int courseIdIndex = file.getHeaderIndex("studentgrade", "course_id");
        int finalScoreIndex = file.getHeaderIndex("studentgrade", "final_score");

        for (List<String> gradeCols : allGrades) {
            int finalScore = Integer.parseInt(gradeCols.get(finalScoreIndex));
            if (finalScore < 40) {
                String courseId = gradeCols.get(courseIdIndex);
                failedCourses.add(courseId);
            }
        }

        return failedCourses;
    }



    public List<String> getIneligibleStudents() {
        List<String> allStudents = file.retrieveAllLine("student");
        List<String> ineligible = new ArrayList<>();
        int idIndex = file.getHeaderIndex("student", "student_id");

        for (String line : allStudents) {
            List<String> cols = file.parseLine(line);
            String studentId = cols.get(idIndex);
            double cgpa = getCGPA(studentId);
            List<String> failedCourses = getFailedCourses(studentId);

            if (cgpa < 2.0 || failedCourses.size() > 3) {
                ineligible.add(studentId);
            }
        }

        return ineligible;
    }
    
        public int countRecoveryAttempts(String studentId, String courseId) {
        List<String> attempts = file.retrieveAllMatchLine("recoveryenrollment", "student_id", studentId);
        long attemptCount = attempts.stream()
                                   .filter(line -> line.contains(courseId))
                                   .count();
        return (int) attemptCount;
    }

    public List<String> getFailedComponents(String studentId, String courseId, int passingScore) {
        List<String> failedComponents = new ArrayList<>();    

        List<String> courseCols = file.retrieveOneMatchLine("course", "course_id", courseId);
        if (courseCols.isEmpty()) {
            System.out.println("Course not found: " + courseId);
            return failedComponents;
        }

        int examWeightIndex = file.getHeaderIndex("course", "exam_weight");
        int assignmentWeightIndex = file.getHeaderIndex("course", "assignment_weight");
        double examWeight = Double.parseDouble(courseCols.get(examWeightIndex));
        double assignmentWeight = Double.parseDouble(courseCols.get(assignmentWeightIndex));

        List<String> studentGrades = file.retrieveAllMatchLine("studentgrade", "student_id", studentId);
        if (studentGrades.isEmpty()) return failedComponents;

        int courseIndex = file.getHeaderIndex("studentgrade", "course_id");
        int examScoreIndex = file.getHeaderIndex("studentgrade", "exam_score");
        int assignmentScoreIndex = file.getHeaderIndex("studentgrade", "assignment_score");

        for (String gradeLine : studentGrades) {
            List<String> gradeCols = file.parseLine(gradeLine);

            if (!gradeCols.get(courseIndex).equals(courseId)) continue;

            int examScore = Integer.parseInt(gradeCols.get(examScoreIndex));
            int assignmentScore = Integer.parseInt(gradeCols.get(assignmentScoreIndex));

            double finalScore = (examScore * examWeight / 100) + (assignmentScore * assignmentWeight / 100);

            if (finalScore < passingScore) {
                if (examScore < passingScore) failedComponents.add("Exam");
                if (assignmentScore < passingScore) failedComponents.add("Assignment");
            }
        }

        return failedComponents;
    }
}