/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package courserecoverysystem.view.student;
import courserecoverysystem.data.FileData;
import courserecoverysystem.model.Course;
import courserecoverysystem.model.StudentGrade;
import courserecoverysystem.service.AcademicReportService;
import courserecoverysystem.service.CourseService;
import courserecoverysystem.service.FileService;
import courserecoverysystem.service.StudentGradeService;
import courserecoverysystem.service.StudentService;
import courserecoverysystem.uiElements.TableUtils;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.text.DecimalFormat;
import java.util.List;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author User
 */
public class StudentReport extends javax.swing.JPanel {
    private final StudentService studentService = new StudentService();
    private final StudentGradeService studentGradeService = new StudentGradeService();
    private final CourseService courseService = new CourseService();
    private final FileData fileData = new FileData();
    private final FileService fileService = new FileService();
    private DefaultTableModel tableModel;
    private static final DecimalFormat DF = new DecimalFormat("0.00");
    private String studentId;


    /**
     * Creates new form StudentReport
     */
    public StudentReport() {
        initComponents();

        tableModel = (DefaultTableModel) ReportTable.getModel();
        // Auto-load whenever this panel becomes visible
        this.addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentShown(java.awt.event.ComponentEvent e) {
                loadReportPreview(); // you implement this to fill the table
            }
        });
    }
    
    public void loadReportPreview(){
        loadReportTable(); // to run in StudentMain.java
        System.out.println("Report table loading..");
    }
    
    private void loadReportTable() {
        tableModel.setRowCount(0);
        studentId = studentService.getCurrentStudentId(); // same as your StudentCourses
        System.out.println("Report Debug studentId = " + studentId);

        if (studentId == null || studentId.isBlank()) {
            TableUtils.centerAllColumns(ReportTable);
            return;
        }

        List<StudentGrade> grades = studentGradeService.getGradesByStudent(studentId);
        if (grades == null || grades.isEmpty()) {
            TableUtils.centerAllColumns(ReportTable);
            return;
        }

        for (StudentGrade sg : grades) {
            String courseId = sg.getCourseID();
            Course c = courseService.getCourseById(courseId);

            String courseName = (c != null) ? c.getCourseName() : "N/A";
            Integer credit = null;
            String semester = "N/A";

            if (c != null) {
                try {
                    credit = Integer.valueOf(c.getCredit());
                } catch (Exception ex) {
                    credit = null;
                }
                semester = c.getSemester();
            }

            double exam = sg.getExamScore();
            double assignment = sg.getAssignmentScore();

            double finalScore = 0.0;
            String gradeLetter = "N/A";
            String status = "N/A";

            if (c != null) {
                double raw = studentGradeService.computeFinalScore(sg, c);
                finalScore = Double.parseDouble(DF.format(raw));
                gradeLetter = studentGradeService.computeLetterGrade(finalScore);
                status = gradeLetter.equalsIgnoreCase("F") ? "Failed" : "Passed";
            }

            RecoveryInfo rec = getRecoveryInfo(studentId, courseId);

            tableModel.addRow(new Object[]{
                courseId,
                courseName,
                credit,
                semester,
                exam,
                assignment,
                finalScore,
                gradeLetter,
                status,
                rec.type,
                rec.status,
                rec.milestones
            });
        }

        TableUtils.centerAllColumns(ReportTable);
    }

    private static class RecoveryInfo {
        String type = "N/A";
        String status = "N/A";
        String milestones = "N/A";
    }

    private RecoveryInfo getRecoveryInfo(String studentId, String courseId) {
        RecoveryInfo info = new RecoveryInfo();

        // recoveryEnrollment format (example): EN001|S001|C202|Resit Exam|Completed
        List<String> enrollLines = fileData.fileRead("recoveryEnrollment");
        if (enrollLines == null || enrollLines.isEmpty()) return info;

        String enrollmentId = null;

        for (String line : enrollLines) {
            if (line == null || line.isBlank()) continue;
            List<String> cols = fileService.parseLine(line);

            // [0]=enrollmentId, [1]=studentId, [2]=courseId, [3]=type, [4]=status
            if (cols.size() >= 5
                    && studentId.equals(cols.get(1))
                    && courseId.equals(cols.get(2))) {
                enrollmentId = cols.get(0);
                info.type = cols.get(3);
                info.status = cols.get(4);
                break;
            }
        }

        if (enrollmentId == null) {
            info.type = "No recovery";
            info.status = "No recovery";
            info.milestones = "No recovery enrolled";
            return info;
        }

        // recoveryPhase format (example): P001|E001|Week1-3|Study Material
        // Your earlier logic converts EN001 -> E001
        String phaseKey = enrollmentId.replace("EN", "E");

        List<String> phaseLines = fileData.fileRead("recoveryPhase");
        if (phaseLines == null || phaseLines.isEmpty()) return info;

        StringBuilder sb = new StringBuilder();
        for (String line : phaseLines) {
            if (line == null || line.isBlank()) continue;
            List<String> cols = fileService.parseLine(line);

            // [1] should be E001, [2]=Week..., [3]=desc
            if (cols.size() >= 4 && phaseKey.equals(cols.get(1))) {
                if (sb.length() > 0) sb.append(" | ");
                sb.append(cols.get(2)).append(": ").append(cols.get(3));
            }
        }

        if (sb.length() > 0) info.milestones = sb.toString();
        return info;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        ReportTable = new javax.swing.JTable();
        btnRefresh = new javax.swing.JButton();
        btnGenerateReport = new javax.swing.JButton();

        setBackground(new java.awt.Color(78, 118, 163));
        setToolTipText("");
        setMaximumSize(new java.awt.Dimension(1450, 820));
        setMinimumSize(new java.awt.Dimension(1450, 820));

        jLabel1.setFont(new java.awt.Font("Century Gothic", 1, 48)); // NOI18N
        jLabel1.setText("REPORT");

        jPanel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel1.setPreferredSize(new java.awt.Dimension(111, 593));

        ReportTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Course ID", "Course Name", "Credit", "Semester", "Exam", "Assignment", "Final Score", "Grade", "Status", "Type", "Status", "Milestones"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(ReportTable);

        btnRefresh.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        btnRefresh.setText("Refresh");
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });

        btnGenerateReport.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        btnGenerateReport.setText("Generate Report");
        btnGenerateReport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerateReportActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(btnRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnGenerateReport)
                .addGap(54, 54, 54))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 928, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 362, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGenerateReport, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(70, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(513, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 961, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(67, 67, 67))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 515, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(281, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        loadReportTable();
    }//GEN-LAST:event_btnRefreshActionPerformed

    private void btnGenerateReportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerateReportActionPerformed
        AcademicReportService reportService = new AcademicReportService();
        reportService.exportReport(studentId);
    }//GEN-LAST:event_btnGenerateReportActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable ReportTable;
    private javax.swing.JButton btnGenerateReport;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
