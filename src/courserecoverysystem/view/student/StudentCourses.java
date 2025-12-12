/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package courserecoverysystem.view.student;
import courserecoverysystem.model.Course;
import courserecoverysystem.model.StudentGrade;
import courserecoverysystem.service.CourseService;
import courserecoverysystem.service.StudentGradeService;
import courserecoverysystem.service.StudentService;
import courserecoverysystem.uiElements.TableUtils;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.List;
import java.text.DecimalFormat;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;


public class StudentCourses extends javax.swing.JPanel {
    private final StudentGradeService studentGradeService = new StudentGradeService();
    private final StudentService studentService = new StudentService();
    private final CourseService courseService = new CourseService();
    private final DefaultTableModel tableModel;
    private static final DecimalFormat DF = new DecimalFormat("0.00");
    private String studentId;
    
    public StudentCourses() {
        initComponents();
        tableModel = (DefaultTableModel) CourseTable.getModel();
        
        this.addComponentListener(new ComponentAdapter(){
            @Override
            public void componentShown(ComponentEvent e){
                loadCourseTable();
            }
        });
    }
    
    private void loadCourseTable() {
        // Clear table
        tableModel.setRowCount(0);

        // 1. Get current student ID from logged-in user
        studentId = studentService.getCurrentStudentId();
        System.out.println("Debug studentId: " + studentId);

        if (studentId == null || studentId.isBlank()) {
            TableUtils.centerAllColumns(CourseTable);
            return;
        }

        // 2. Get all grades for this student
        List<StudentGrade> grades = studentGradeService.getGradesByStudent(studentId);
        System.out.println("Debug grades size = " + (grades == null ? 0 : grades.size()));

        if (grades == null || grades.isEmpty()) {
            TableUtils.centerAllColumns(CourseTable);
            return;
        }

        // 3. Prepare course.txt column indexes (adjust column names if yours are different)
        for (StudentGrade g : grades){
            String courseId = g.getCourseID();
            
            Course c = courseService.getCourseById(courseId);
            String courseName = "";
            Integer credit = null;
            String semester = "";
            
            if(c != null){
                courseName = c.getCourseName();
                try{
                    credit = Integer.valueOf(c.getCredit());
                    semester = c.getSemester();
                }catch (NumberFormatException ex){
                    System.out.println("Course credit or semester not numeric for " + courseId);
                }
            }
            
            double finalScore = 0.0;
            String letter = "";
            String status = "";
            
            if(c != null){
                double rawFinalScore = studentGradeService.computeFinalScore(g, c); // weight 0-100

                // UPDATED: format to 2 decimal places to avoid 71.0500000...
                finalScore = Double.parseDouble(DF.format(rawFinalScore)); // UPDATED

                letter = studentGradeService.computeLetterGrade(finalScore); // UPDATED
                status = letter.equals("F") ? "Failed" : "Passed";
            }
            
            tableModel.addRow(new Object[]{
                false,                    // Select
                courseId,                 // Course ID
                courseName,               // Course Name
                credit,                   // Credit
                semester,                 // Semester
                g.getExamScore(),         // Exam Score
                g.getAssignmentScore(),   // Assignment Score
                finalScore,               // Final Score (0–100)
                letter,                   // Grade (A/B/C/D/F)
                status                    // Status (Passed / Failed)
            });
        }

        TableUtils.centerAllColumns(CourseTable);
    }
    
    private void openCourseDetails(String studentId, String courseId) {
        Container parent = this.getParent();
        if (!(parent instanceof JPanel parentPanel)) return;

        for (java.awt.Component comp : parentPanel.getComponents()) {
            if (comp instanceof StudentCourseDetails detailsPanel) {
                detailsPanel.loadCourseDetails(studentId, courseId); // now matches (String,String)
                break;
            }
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        roundedPanel1 = new courserecoverysystem.uiElements.RoundedPanel();
        btnCourseDetails = new javax.swing.JButton();
        btnRefresh = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        CourseTable = new javax.swing.JTable();

        setBackground(new java.awt.Color(78, 118, 163));
        setDoubleBuffered(false);
        setMaximumSize(new java.awt.Dimension(1450, 820));
        setMinimumSize(new java.awt.Dimension(1450, 820));
        setName(""); // NOI18N
        setPreferredSize(new java.awt.Dimension(1450, 820));

        jLabel1.setFont(new java.awt.Font("Century Gothic", 1, 48)); // NOI18N
        jLabel1.setText("COURSES");

        btnCourseDetails.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        btnCourseDetails.setText("View More Details");
        btnCourseDetails.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCourseDetailsActionPerformed(evt);
            }
        });

        btnRefresh.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        btnRefresh.setText("Refresh");
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });

        CourseTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Select", "Course ID", "Course Name", "Credit", "Semester", "Exam Score", "Assigment Score", "Final Score", "Grade", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(CourseTable);

        javax.swing.GroupLayout roundedPanel1Layout = new javax.swing.GroupLayout(roundedPanel1);
        roundedPanel1.setLayout(roundedPanel1Layout);
        roundedPanel1Layout.setHorizontalGroup(
            roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel1Layout.createSequentialGroup()
                .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundedPanel1Layout.createSequentialGroup()
                        .addGap(82, 82, 82)
                        .addComponent(btnRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(580, 580, 580)
                        .addComponent(btnCourseDetails, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(roundedPanel1Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1073, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        roundedPanel1Layout.setVerticalGroup(
            roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 508, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCourseDetails, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(336, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(roundedPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(71, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(roundedPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(82, 82, 82))
        );
    }// </editor-fold>//GEN-END:initComponents
    
    private void SwapToCourseDetails() { 
        Container parent = this.getParent();
        if (parent instanceof JPanel) {
            CardLayout layout = (CardLayout) parent.getLayout();
            layout.show(parent, "sviewcoursedetails");
        }
    }
    
    private void btnCourseDetailsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCourseDetailsActionPerformed
        int selectedCount = 0;
        int selectedRow = -1;

        for (int i = 0; i < tableModel.getRowCount(); i++) {
            Boolean checked = (Boolean) tableModel.getValueAt(i, 0); // col 0 = Select
            if (checked != null && checked) {
                selectedCount++;
                selectedRow = i;
            }
        }

        if (selectedCount == 0) {
            JOptionPane.showMessageDialog(this,
                    "Please select ONE course to view details.",
                    "No Selection",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (selectedCount > 1) {
            JOptionPane.showMessageDialog(this,
                    "You can select ONE course at a time.",
                    "Multiple Selection",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        String selectedCourseId = (String) tableModel.getValueAt(selectedRow, 1);
        System.out.println("Selected Course ID: " + selectedCourseId);

        openCourseDetails(studentId, selectedCourseId);
        SwapToCourseDetails();
    }//GEN-LAST:event_btnCourseDetailsActionPerformed

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        loadCourseTable();
    }//GEN-LAST:event_btnRefreshActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable CourseTable;
    private javax.swing.JButton btnCourseDetails;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane2;
    private courserecoverysystem.uiElements.RoundedPanel roundedPanel1;
    // End of variables declaration//GEN-END:variables
}
