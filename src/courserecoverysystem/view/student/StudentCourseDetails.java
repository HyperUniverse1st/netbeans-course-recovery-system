package courserecoverysystem.view.student;

import courserecoverysystem.data.FileData;
import courserecoverysystem.model.Course;
import courserecoverysystem.model.StudentGrade;
import courserecoverysystem.service.FileService;
import courserecoverysystem.service.CourseService;
import courserecoverysystem.service.StudentGradeService;
import java.awt.CardLayout;
import java.awt.Container;
import java.util.List;
import javax.swing.JPanel;
import java.text.DecimalFormat;

public class StudentCourseDetails extends javax.swing.JPanel {    
    private final FileData fileData = new FileData();
    private final FileService fileService = new FileService();
    private final CourseService courseService = new CourseService();
    private final StudentGradeService studentGradeService = new StudentGradeService();
    private final DecimalFormat DF = new DecimalFormat("0.00");
    
    public StudentCourseDetails() {
        initComponents();
        TFCourseName.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TFGrade.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TFGPA.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TFGivenMilestone.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TFGivenRecommendation.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        // lblStudentGPA
        // lblCourseName
        // lblStudentMilestone - given milestone by academic officer
        // lblStudentRec - given recommendation by lecturer
        // lblStudentGrade - grade for the specific subject
        
        // all of this is retrieved from the txt file and input into the labels as GUI        
    }
    
    
    public void loadCourseDetails(String studentId, String courseId) {
        // 1) Course name from course.txt
        Course c = courseService.getCourseById(courseId);
        String courseName = (c != null) ? c.getCourseName() : "N/A";
        TFCourseName.setText(courseName);

        // 2) Grade + GPA (use your existing grade logic)
        StudentGrade sg = studentGradeService.getStudentGrade(studentId, courseId);

        if (sg != null && c != null) {
            double finalScore = studentGradeService.computeFinalScore(sg, c);
            String letter = studentGradeService.computeLetterGrade(finalScore);

            TFGrade.setText(letter);

            // If you don’t have real GPA calculation yet, show finalScore as GPA temporarily
            // (or replace with your GradeService method if you already have it)
            TFGPA.setText(DF.format(finalScore));

        } else {
            TFGrade.setText("N/A");
            TFGPA.setText("N/A");
        }

        // 3) Milestone + Recommendation from recovery files
        loadRecoveryInfo(studentId, courseId);
    }

    private void loadRecoveryInfo (String studentId, String courseId){
        TFGivenMilestone.setText("N/A");
        TFGivenRecommendation.setText("N/A");

        List<String> enrollments = fileService.retrieveAllMatchLine("recoveryEnrollment", "student_id", studentId);

        String enrollmentId = null;
        String type = null;
        String status = null;

        for(String line : enrollments){
            List<String> cols = fileData.parseLine(line);
            if(cols.size() >= 5 && courseId.equals(cols.get(2))){
                enrollmentId = cols.get(0);
                type = cols.get(3);
                status = cols.get(4);
                break;
            }
        }

        if(enrollmentId == null){
            TFGivenMilestone.setText("No recovery enrolled");
            return;
        }

        List<String> phases = fileData.fileRead("recoveryPhase");
        StringBuilder milestoneText = new StringBuilder();
        String phaseKey = enrollmentId.replace("EN", "E");
        for(String line : phases){
            List<String> cols = fileData.parseLine(line);
            if(cols.size() >= 4 && phaseKey.equals(cols.get(1))){
                milestoneText.append(cols.get(2)).append(" - ").append(cols.get(3)).append("; ");
            }
        }
        if(milestoneText.length() > 0){
            TFGivenMilestone.setText(milestoneText.toString());
        }

        TFGivenRecommendation.setText("Type: " + type + "| Status: " + status);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        roundedPanel1 = new courserecoverysystem.uiElements.RoundedPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        btnBack = new javax.swing.JButton();
        TFCourseName = new javax.swing.JTextField();
        TFGrade = new javax.swing.JTextField();
        TFGPA = new javax.swing.JTextField();
        TFGivenMilestone = new javax.swing.JTextField();
        TFGivenRecommendation = new javax.swing.JTextField();

        setBackground(new java.awt.Color(78, 118, 163));
        setDoubleBuffered(false);
        setMaximumSize(new java.awt.Dimension(1420, 820));
        setMinimumSize(new java.awt.Dimension(1420, 820));
        setName(""); // NOI18N
        setPreferredSize(new java.awt.Dimension(1420, 820));

        jLabel1.setFont(new java.awt.Font("Century Gothic", 1, 48)); // NOI18N
        jLabel1.setText("COURSE INFO");

        jLabel2.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Grade");

        jLabel3.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Given Milestone");

        jLabel4.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("GPA");

        jLabel5.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Given Recommendation");

        jLabel6.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Course Name");

        btnBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/pfp/back.png"))); // NOI18N
        btnBack.setBorder(null);
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        TFCourseName.setEditable(false);

        TFGrade.setEditable(false);

        TFGPA.setEditable(false);

        TFGivenMilestone.setEditable(false);

        TFGivenRecommendation.setEditable(false);
        TFGivenRecommendation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TFGivenRecommendationActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout roundedPanel1Layout = new javax.swing.GroupLayout(roundedPanel1);
        roundedPanel1.setLayout(roundedPanel1Layout);
        roundedPanel1Layout.setHorizontalGroup(
            roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel1Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(btnBack)
                .addGap(50, 50, 50)
                .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundedPanel1Layout.createSequentialGroup()
                        .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(TFCourseName, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(123, 123, 123)
                        .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(TFGrade, javax.swing.GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(108, 108, 108)
                        .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
                            .addComponent(TFGPA))
                        .addGap(246, 252, Short.MAX_VALUE))
                    .addGroup(roundedPanel1Layout.createSequentialGroup()
                        .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)
                            .addComponent(TFGivenMilestone))
                        .addGap(123, 123, 123)
                        .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(TFGivenRecommendation)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 277, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        roundedPanel1Layout.setVerticalGroup(
            roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel1Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnBack))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(TFCourseName, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                    .addComponent(TFGrade)
                    .addComponent(TFGPA))
                .addGap(62, 62, 62)
                .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TFGivenRecommendation, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TFGivenMilestone, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(246, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(398, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 448, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(roundedPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(64, 64, 64))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(100, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(roundedPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void SwapToCourses() { 
        Container parent = this.getParent();
        if (parent instanceof JPanel) {
            CardLayout layout = (CardLayout) parent.getLayout();
            layout.show(parent, "sviewcourses");
        }
    }
    
    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        SwapToCourses();
    }//GEN-LAST:event_btnBackActionPerformed

    private void TFGivenRecommendationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TFGivenRecommendationActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TFGivenRecommendationActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField TFCourseName;
    private javax.swing.JTextField TFGPA;
    private javax.swing.JTextField TFGivenMilestone;
    private javax.swing.JTextField TFGivenRecommendation;
    private javax.swing.JTextField TFGrade;
    private javax.swing.JButton btnBack;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private courserecoverysystem.uiElements.RoundedPanel roundedPanel1;
    // End of variables declaration//GEN-END:variables
}
