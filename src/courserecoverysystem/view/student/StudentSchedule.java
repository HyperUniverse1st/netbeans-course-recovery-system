/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package courserecoverysystem.view.student;
import courserecoverysystem.service.FileService;
import courserecoverysystem.model.User;
import courserecoverysystem.uiElements.TableUtils;
import java.util.List;
import javax.swing.table.DefaultTableModel;


public class StudentSchedule extends javax.swing.JPanel {
    private final FileService fileService = new FileService();
    private String studentId;
    private final DefaultTableModel tableModel;
    
    public StudentSchedule() {
        initComponents();        
        tableModel = (DefaultTableModel) scheduleTable.getModel();
        
        this.addComponentListener(new java.awt.event.ComponentAdapter(){
            @Override
            public void componentShown(java.awt.event.ComponentEvent e){
                loadSchedule();
            }
        });
    }
    
    private void loadSchedule(){
        tableModel.setRowCount(0);
        
        User currentUser = User.getCurrentUser();
        if (currentUser == null){
            System.out.println("No current user set.");
            return;
        }
        
        List<String> lines = fileService.retrieveAllLine("class");
        
        for (String line : lines) {
            if (line.trim().isEmpty()) continue;

            List<String> values = fileService.parseLine(line);
            if (values.size() < 6) continue;
            
            String classId = values.get(0).trim();
            String courseName = values.get(1).trim();
            String lecturerName = values.get(2).trim();
            String date = values.get(3).trim();
            String time = values.get(4).trim();
            String studentList = values.get(5).trim();
            
            studentId = studentList;
            boolean belongsToStudent = false;
            for(String id : studentList.split(",")){
                if(id.trim().equals(studentId)){
                    belongsToStudent = true;
                    break;
                };
            }
            if(!belongsToStudent){
                continue;
            }
            
            // get courseId by searching courseName in course.txt
            String courseId = getCourseIdByCourseName(courseName);
            
            tableModel.addRow(new Object[]{classId, courseId, courseName, lecturerName, date, time});
        }
        TableUtils.centerAllColumns(scheduleTable);
    }
    
    private String getCourseIdByCourseName(String courseName){
        List<String> courseLines = fileService.retrieveAllLine("course");
        
        for(String line : courseLines){
            if (line == null || line.trim().isEmpty()) continue;
            
            List<String> values = fileService.parseLine(line);
            if(values.size() < 3) continue;
             
            String courseId = values.get(0).trim();
            String courseNameFromFile = values.get(2).trim();
            
            if(courseNameFromFile.equalsIgnoreCase(courseName) || courseNameFromFile.toLowerCase().contains(courseName.toLowerCase()) || courseName.toLowerCase().contains(courseNameFromFile.toLowerCase())){
                return courseId;
            }
        }
        return "-"; //if not found
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        roundedPanel1 = new courserecoverysystem.uiElements.RoundedPanel();
        btnRefresh = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        scheduleTable = new javax.swing.JTable();

        setBackground(new java.awt.Color(78, 118, 163));
        setDoubleBuffered(false);
        setMaximumSize(new java.awt.Dimension(1420, 820));
        setMinimumSize(new java.awt.Dimension(1420, 820));
        setName(""); // NOI18N
        setPreferredSize(new java.awt.Dimension(1420, 820));

        jLabel1.setFont(new java.awt.Font("Century Gothic", 1, 48)); // NOI18N
        jLabel1.setText("SCHEDULE");

        btnRefresh.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        btnRefresh.setText("Refresh");
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });

        scheduleTable.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(scheduleTable);

        javax.swing.GroupLayout roundedPanel1Layout = new javax.swing.GroupLayout(roundedPanel1);
        roundedPanel1.setLayout(roundedPanel1Layout);
        roundedPanel1Layout.setHorizontalGroup(
            roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 929, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        roundedPanel1Layout.setVerticalGroup(
            roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 451, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 66, Short.MAX_VALUE)
                .addComponent(btnRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(406, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(roundedPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(67, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(roundedPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(56, 56, 56))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        loadSchedule();
    }//GEN-LAST:event_btnRefreshActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRefresh;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private courserecoverysystem.uiElements.RoundedPanel roundedPanel1;
    private javax.swing.JTable scheduleTable;
    // End of variables declaration//GEN-END:variables
}
