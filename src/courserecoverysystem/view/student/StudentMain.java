package courserecoverysystem.view.student;

import courserecoverysystem.LoginScreen;
import courserecoverysystem.model.User;
import courserecoverysystem.service.FileService;
import courserecoverysystem.service.StudentService;
import courserecoverysystem.uiElements.TableUtils;
import java.awt.CardLayout;
import java.awt.Container;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

public class StudentMain extends javax.swing.JPanel {
    private final FileService fileService = new FileService();
    private final StudentService studentService = new StudentService();
    private final DefaultTableModel tableModel;
    private String studentId;
    
    public StudentMain() {
        // lblName for the welcome !! DONT FORGET THIS
        initComponents();
        tableModel = (DefaultTableModel) MainTable.getModel();
        System.out.println("StudentMain constructor say hi");
        loadUpcomingClasses();
    }
    
    private void loadUpcomingClasses(){
        String studentId = studentService.getCurrentStudentId();
        tableModel.setRowCount(0);
        
        User currentUser = User.getCurrentUser();
        if(currentUser == null){
            System.out.println("No current user set.");
            TableUtils.centerAllColumns(MainTable);
            return;
        }
        
        String userId = currentUser.getUID();
        System.out.println("Home Debug userId = " + userId);
        
        if(userId == null || userId.isBlank()){
            System.out.println("Student ID is blank. Cannot load classes.");
            TableUtils.centerAllColumns(MainTable);
            return;
        }
        List<String> classLines = fileService.retrieveAllLine("class");
        if(classLines == null || classLines.isEmpty()){
            TableUtils.centerAllColumns(MainTable);
            return;
        }
        
        int added = 0;
        
        for(String line : classLines){
            if(line == null || line.trim().isEmpty()) continue;
            
            List<String> values = fileService.parseLine(line);
            if(values.size() < 6) continue;
            
            String classId = values.get(0).trim();
            String courseName = values.get(1).trim();
            String lecturerName = values.get(2).trim();
            String date = values.get(3).trim();
            String time = values.get(4).trim();
            String studentList = values.get(5).trim();
            
            if(!containsStudent(studentList, studentId)) {
                System.out.println(studentList);
                System.out.println(studentId);
                continue;
            }
            
            String courseId = getCourseIdByCourseName(courseName);
            
            tableModel.addRow(new Object[]{
                classId,
                courseId,
                courseName,
                lecturerName,
                date,
                time
            });
            added++;
        }

        TableUtils.centerAllColumns(MainTable);
        }

        private boolean containsStudent(String studentList, String targetId) {
            if (studentList == null || studentList.isBlank()) return false;
            if(targetId == null || targetId.isBlank())return false;
            String[] ids = studentList.split(",");
            for (String token : ids) {
                if (token != null && token.trim().equalsIgnoreCase(targetId.trim())) {
                    return true;
                }
            }
            return false;
        }

        private String getCourseIdByCourseName(String courseName) {
        if (courseName == null || courseName.isBlank()) return "-";

        List<String> courseLines = fileService.retrieveAllLine("course");
        if (courseLines == null || courseLines.isEmpty()) return "-";

        for (String line : courseLines) {
            if (line == null || line.trim().isEmpty()) continue;

            List<String> values = fileService.parseLine(line);
            if (values.size() < 3) continue;

            // Common course format (based on your earlier method):
            // [0]=courseId, [2]=courseName
            String courseId = values.get(0).trim();
            String nameFromFile = values.get(2).trim();

            if (nameFromFile.trim().equalsIgnoreCase(courseName)) {
                return courseId;
            }
        }
        return "-";
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        roundedPanel1 = new courserecoverysystem.uiElements.RoundedPanel();
        label1 = new java.awt.Label();
        jScrollPane1 = new javax.swing.JScrollPane();
        MainTable = new javax.swing.JTable();
        btnRefresh = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        lblName = new java.awt.Label();
        jLabel1 = new javax.swing.JLabel();
        label5 = new java.awt.Label();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0));
        btnViewCourse = new javax.swing.JButton();
        btnProfile = new javax.swing.JButton();
        btnReport = new javax.swing.JButton();

        setBackground(new java.awt.Color(78, 118, 163));
        setDoubleBuffered(false);
        setMaximumSize(new java.awt.Dimension(1420, 820));
        setMinimumSize(new java.awt.Dimension(1420, 820));
        setName(""); // NOI18N
        setPreferredSize(new java.awt.Dimension(1420, 820));

        label1.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        label1.setText("Upcoming Classes");

        MainTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Class ID", "Course ID", "Course Name", "Lecturer Name", "Date", "Time"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(MainTable);

        btnRefresh.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        btnRefresh.setText("Refresh");
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout roundedPanel1Layout = new javax.swing.GroupLayout(roundedPanel1);
        roundedPanel1.setLayout(roundedPanel1Layout);
        roundedPanel1Layout.setHorizontalGroup(
            roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel1Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(roundedPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 888, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(33, Short.MAX_VALUE))
        );
        roundedPanel1Layout.setVerticalGroup(
            roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        jButton1.setBackground(new java.awt.Color(30, 62, 107));
        jButton1.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Log Out");
        jButton1.setActionCommand("btnLogOut");
        jButton1.setOpaque(true);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        lblName.setFont(new java.awt.Font("Segoe UI", 0, 28)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 65)); // NOI18N
        jLabel1.setText("HOME");

        label5.setFont(new java.awt.Font("Segoe UI", 0, 28)); // NOI18N
        label5.setText("Welcome,");

        btnViewCourse.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        btnViewCourse.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/pfp/CourseLogo.png"))); // NOI18N
        btnViewCourse.setText("View Course");
        btnViewCourse.setAutoscrolls(true);
        btnViewCourse.setContentAreaFilled(true);
        btnViewCourse.setFocusPainted(false);
        btnViewCourse.setBorderPainted(false);
        btnViewCourse.setHideActionText(true);
        btnViewCourse.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnViewCourse.setIconTextGap(20);
        btnViewCourse.setMargin(new java.awt.Insets(2, 35, 3, 14));
        btnViewCourse.setName(""); // NOI18N
        btnViewCourse.setOpaque(true);
        btnViewCourse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewCourseActionPerformed(evt);
            }
        });

        btnProfile.setBackground(new java.awt.Color(78, 118, 163));
        btnProfile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/pfp/ProfileLogo.png"))); // NOI18N
        btnProfile.setBorder(null);
        btnProfile.setOpaque(true);
        btnProfile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProfileActionPerformed(evt);
            }
        });

        btnReport.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        btnReport.setText("Report");
        btnReport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReportActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(514, 514, 514)
                .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 371, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnProfile, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(label5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblName, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnViewCourse, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(55, 55, 55)
                        .addComponent(btnReport, javax.swing.GroupLayout.PREFERRED_SIZE, 329, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(roundedPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(72, 72, 72))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnProfile, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblName, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(label5, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnViewCourse, javax.swing.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
                            .addComponent(btnReport, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(roundedPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(49, 49, 49))
        );
    }// </editor-fold>//GEN-END:initComponents
    private void SwapToCourses() { 
        Container parent = this.getParent();
        if (parent instanceof JPanel) {
            CardLayout layout = (CardLayout) parent.getLayout();
            layout.show(parent, "sviewcourses");
        }
    }
    private void SwapToReport(){
        Container parent = this.getParent();
        if(parent instanceof JPanel) {
            CardLayout layout = (CardLayout) parent.getLayout();
            layout.show(parent, "sreport");
        }
    }
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        LoginScreen.logout();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnViewCourseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewCourseActionPerformed
        SwapToCourses();
    }//GEN-LAST:event_btnViewCourseActionPerformed

    private void btnProfileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProfileActionPerformed
        Container parent = this.getParent();
        if (parent instanceof JPanel) {
            CardLayout layout = (CardLayout) parent.getLayout();
            layout.show(parent, "userprofile");
        }
    }//GEN-LAST:event_btnProfileActionPerformed

    private void btnReportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReportActionPerformed
        SwapToReport();
    }//GEN-LAST:event_btnReportActionPerformed

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        loadUpcomingClasses();
    }//GEN-LAST:event_btnRefreshActionPerformed
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable MainTable;
    private javax.swing.JButton btnProfile;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JButton btnReport;
    private javax.swing.JButton btnViewCourse;
    private javax.swing.Box.Filler filler1;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private java.awt.Label label1;
    private java.awt.Label label5;
    private java.awt.Label lblName;
    private courserecoverysystem.uiElements.RoundedPanel roundedPanel1;
    // End of variables declaration//GEN-END:variables
}
