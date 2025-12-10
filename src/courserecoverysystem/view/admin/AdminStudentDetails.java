package courserecoverysystem.view.admin;
import courserecoverysystem.model.Course;
import courserecoverysystem.model.RecoveryEnrollment;
import courserecoverysystem.model.Student;
import courserecoverysystem.model.StudentGrade;
import courserecoverysystem.service.CourseService;
import courserecoverysystem.service.RecoveryEnrollmentService;
import courserecoverysystem.service.StudentGradeService;
import courserecoverysystem.service.StudentService;
import java.awt.CardLayout;
import java.awt.Container;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class AdminStudentDetails extends javax.swing.JPanel {    
    private final StudentService studentService = new StudentService();
    private final StudentGradeService gradeService = new StudentGradeService();
    private final CourseService courseService = new CourseService();
    private final RecoveryEnrollmentService recoveryService = new RecoveryEnrollmentService();

    private List<RecoveryEnrollment> currentEnrollments; // store for combobox switching
    private String currentStudentID;
    public AdminStudentDetails() {
        initComponents();
        // btnEdit - edit recommendation given
        // lblStudentGPA
        // lblStudentName
        // cmbCourse
        // cmbRecovery
        // cmbStatus
        // lblStudentGrade - grade for the specific subject
        // lblStudentID
        // lblYear
        // lblMajor
        // btnCancel
        // btnSave
        // btnRefresh
        
        // all of this is retrieved from the txt file and input into the labels as GUI
    }
    
    public void loadStudent(String studentID){
        this.currentStudentID = studentID;
        
        Student student = studentService .getStudentById(studentID);
        if(student == null){
            System.out.println("Error: student not found: " + studentID);
            return;
        }
        
        //basic info
        txtName.setText(student.getFirstName() + " " + student.getLastName());
        txtStudentID.setText(student.getStudentID());
        txtMajor.setText(student.getMajor());
        txtYear.setText(student.getYear());

        txtName.setEditable(false);
        txtStudentID.setEditable(false);
        txtMajor.setEditable(false);
        txtYear.setEditable(false);
        
        //load CGPA
        double cgpa = studentService.computeCGPA(studentID);
        txtCGPA.setText(String.format("%.2f", cgpa));
        txtCGPA.setEditable(false);
        
        //load recovery enrollments
        currentEnrollments = recoveryService.getEnrollmentsByStudent(studentID);
        
        cmbCourse.removeAllItems();
        cmbRecovery.removeAllItems();
        cmbStatus.removeAllItems();

        if (currentEnrollments.isEmpty()) {
            cmbCourse.addItem("No recovery courses");
            txtGrade.setText("N/A");
            return;
        }
        
        //populate course names
        for(RecoveryEnrollment e : currentEnrollments){
            Course c = courseService.getCourseById(e.getCourseID());
            if(c != null) {
                cmbCourse.addItem(c.getCourseName());
            }else {
                cmbCourse.addItem(e.getCourseID());
            }
        }
        
        //when user select a course then will load details
        cmbCourse.addActionListener(evt -> updateRecoveryDetails());
        
        //load first course by default
        if(cmbCourse.getItemCount() > 0){
            cmbCourse.setSelectedIndex(0);
            updateRecoveryDetails();
        }
    }
    
    private void updateRecoveryDetails(){
        int index = cmbCourse.getSelectedIndex();
        if(index < 0 || currentEnrollments == null || index >= currentEnrollments.size()) return;
        
        RecoveryEnrollment enrollment = currentEnrollments.get(index);
        
        //load Recovery Type
        cmbRecovery.removeAllItems();
        cmbRecovery.addItem(enrollment.getType());
        cmbRecovery.setEnabled(false);
        
        //load Status
        cmbStatus.removeAllItems();
        cmbStatus.addItem(enrollment.getStatus());
        cmbStatus.setEnabled(false);
        
        //load Grade for this course
        StudentGrade sg = gradeService.getStudentGrade(currentStudentID, enrollment.getCourseID());
        Course course = courseService.getCourseById(enrollment.getCourseID());
        
        if(sg == null|| course == null){
            txtGrade.setText("N/A");
            return;
        }
        
        double finalScore = gradeService.computeFinalScore(sg, course);
        String letterGrade = gradeService.computeLetterGrade(finalScore);
        
        txtGrade.setText(letterGrade);
        txtGrade.setEditable(false);
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
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        cmbCourse = new javax.swing.JComboBox<>();
        cmbRecovery = new javax.swing.JComboBox<>();
        cmbStatus = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        btnCancel = new javax.swing.JButton();
        btnRefresh = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        txtName = new javax.swing.JTextField();
        txtStudentID = new javax.swing.JTextField();
        txtGrade = new javax.swing.JTextField();
        txtMajor = new javax.swing.JTextField();
        txtCGPA = new javax.swing.JTextField();
        txtYear = new javax.swing.JTextField();

        setBackground(new java.awt.Color(78, 118, 163));
        setDoubleBuffered(false);
        setMaximumSize(new java.awt.Dimension(1420, 820));
        setMinimumSize(new java.awt.Dimension(1420, 820));
        setName(""); // NOI18N
        setPreferredSize(new java.awt.Dimension(1420, 820));

        jLabel1.setFont(new java.awt.Font("Century Gothic", 1, 48)); // NOI18N
        jLabel1.setText("STUDENT DETAILS");

        jLabel2.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel2.setText("Grade");

        jLabel3.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel3.setText("Course Selection");

        jLabel4.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel4.setText("CGPA");

        jLabel5.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel5.setText("Recovery Type");

        jLabel6.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel6.setText("Name");

        btnBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/pfp/back.png"))); // NOI18N
        btnBack.setBorder(null);
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel7.setText("ID");

        jLabel8.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel8.setText("Major");

        jLabel9.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel9.setText("Year");

        cmbRecovery.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Exam", "Assignment", "Both" }));

        cmbStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Active", "In Progress", "Completed", "Failed" }));
        cmbStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbStatusActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel10.setText("Status");

        btnCancel.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        btnCancel.setText("Cancel");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        btnRefresh.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        btnRefresh.setText("Refresh");
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });

        btnSave.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        btnSave.setText("Save");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        txtName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNameActionPerformed(evt);
            }
        });

        txtCGPA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCGPAActionPerformed(evt);
            }
        });

        txtYear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtYearActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout roundedPanel1Layout = new javax.swing.GroupLayout(roundedPanel1);
        roundedPanel1.setLayout(roundedPanel1Layout);
        roundedPanel1Layout.setHorizontalGroup(
            roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel1Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(btnBack)
                .addGap(122, 122, 122)
                .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbCourse, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(roundedPanel1Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtStudentID, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(89, 89, 89)
                .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtMajor, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
                            .addComponent(txtGrade)))
                    .addComponent(cmbRecovery, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(txtYear, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
                        .addComponent(txtCGPA)))
                .addGap(0, 74, Short.MAX_VALUE))
            .addGroup(roundedPanel1Layout.createSequentialGroup()
                .addGap(91, 91, 91)
                .addComponent(btnRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(110, 110, 110))
        );
        roundedPanel1Layout.setVerticalGroup(
            roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel1Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundedPanel1Layout.createSequentialGroup()
                        .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnBack))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtStudentID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(roundedPanel1Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(roundedPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtGrade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(roundedPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCGPA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(43, 43, 43)
                        .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(roundedPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtMajor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(roundedPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(15, 15, 15)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundedPanel1Layout.createSequentialGroup()
                        .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmbCourse, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbRecovery, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(roundedPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(66, 66, 66)
                .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRefresh)
                    .addComponent(btnSave)
                    .addComponent(btnCancel))
                .addGap(54, 54, 54))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 448, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(roundedPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(382, 382, 382))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(165, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(roundedPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(67, 67, 67))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void SwapToStudents() { 
        Container parent = this.getParent();
        if (parent instanceof JPanel) {
            CardLayout layout = (CardLayout) parent.getLayout();
            layout.show(parent, "aviewstudents");
        }
    }
    
    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        SwapToStudents();
    }//GEN-LAST:event_btnBackActionPerformed

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        // btnRefresh
    }//GEN-LAST:event_btnRefreshActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        String studentID = txtStudentID.getText();
        String courseID = (String) cmbCourse.getSelectedItem();
        
        int attempts = recoveryService.checkAttemptNum(studentID, courseID);
        
        if(attempts >= 3){
            JOptionPane.showMessageDialog(this, "This student has already attempted recovery for this course " + attempts + " times.\nNo more attempts allowed.", "Maximum Attempts Reached", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        // btnCancel
    }//GEN-LAST:event_btnCancelActionPerformed

    private void cmbStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbStatusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbStatusActionPerformed

    private void txtNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNameActionPerformed

    private void txtCGPAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCGPAActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCGPAActionPerformed

    private void txtYearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtYearActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtYearActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JButton btnSave;
    private javax.swing.JComboBox<String> cmbCourse;
    private javax.swing.JComboBox<String> cmbRecovery;
    private javax.swing.JComboBox<String> cmbStatus;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private courserecoverysystem.uiElements.RoundedPanel roundedPanel1;
    private javax.swing.JTextField txtCGPA;
    private javax.swing.JTextField txtGrade;
    private javax.swing.JTextField txtMajor;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtStudentID;
    private javax.swing.JTextField txtYear;
    // End of variables declaration//GEN-END:variables
}
