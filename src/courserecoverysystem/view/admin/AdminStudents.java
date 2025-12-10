/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package courserecoverysystem.view.admin;
import courserecoverysystem.model.Student;
import courserecoverysystem.service.StudentService;
import courserecoverysystem.uiElements.TableUtils;
import courserecoverysystem.service.RecoveryEnrollmentService;
import java.awt.CardLayout;
import java.awt.Container;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import javax.swing.JPanel;

public class AdminStudents extends javax.swing.JPanel {
    private final StudentService studentService = new StudentService();
    private final RecoveryEnrollmentService recoveryService = new RecoveryEnrollmentService();
    private DefaultTableModel tableModel;
    
    public AdminStudents() {
        initComponents();
        setupTableModel();   // set columns for table
        loadStudentTable();
        TableUtils.centerAllColumns(StudentTable); //center everything
        TableUtils.startAutoRefresh(10_000, this::loadStudentTable); // real-time refresh every 10s
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        roundedPanel1 = new courserecoverysystem.uiElements.RoundedPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        StudentTable = new javax.swing.JTable();
        btnRefresh = new javax.swing.JButton();
        btnStudentDetails = new javax.swing.JButton();

        setBackground(new java.awt.Color(78, 118, 163));
        setDoubleBuffered(false);
        setMaximumSize(new java.awt.Dimension(1420, 820));
        setMinimumSize(new java.awt.Dimension(1420, 820));
        setName(""); // NOI18N
        setPreferredSize(new java.awt.Dimension(1420, 820));

        jLabel1.setFont(new java.awt.Font("Century Gothic", 1, 48)); // NOI18N
        jLabel1.setText("STUDENTS");

        StudentTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(StudentTable);

        btnRefresh.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        btnRefresh.setText("Refresh");
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });

        btnStudentDetails.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        btnStudentDetails.setText("View Student");
        btnStudentDetails.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStudentDetailsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout roundedPanel1Layout = new javax.swing.GroupLayout(roundedPanel1);
        roundedPanel1.setLayout(roundedPanel1Layout);
        roundedPanel1Layout.setHorizontalGroup(
            roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel1Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 898, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(roundedPanel1Layout.createSequentialGroup()
                        .addComponent(btnRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(622, 622, 622)
                        .addComponent(btnStudentDetails, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(26, 26, 26))
        );
        roundedPanel1Layout.setVerticalGroup(
            roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 506, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnStudentDetails, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(420, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(roundedPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(97, 97, 97)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(roundedPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(57, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    
    //initialise the table model (columns, non-editable)
    private void setupTableModel(){
        tableModel = new DefaultTableModel(
                new Object[]{"Student ID","User ID", "First Name", "Last Name", "Major", "Year", "Assigned to Recovery?"}, 0
        ){
            @Override
            public boolean isCellEditable(int row, int column){
                return false; //user cannot edit cells
            }
        };
        
        StudentTable.setModel(tableModel);    
    }
    
    //load data from StudentService into the table
    private void loadStudentTable(){
        tableModel.setRowCount(0); //clear old rows
        
        List<Student> students = studentService.getAllStudents();
        System.out.println("Debug: students returned from service = " + students.size());
        for (Student s : students) {
            boolean assigned = recoveryService.hasAnyEnrollment(s.getStudentID());
            tableModel.addRow(new Object[]{
                s.getStudentID(),
                s.getUserID(),
                s.getFirstName(),
                s.getLastName(),
                s.getMajor(),
                s.getYear(),
                assigned ? "Yes" : "No"
            });
        }
        
        if (students.isEmpty()){
            System.out.println("No student found in student.txt file.");
        }
    }
    
    private void SwapToStudentDetails() { 
        Container parent = this.getParent();
        if (parent instanceof JPanel) {
            CardLayout layout = (CardLayout) parent.getLayout();
            layout.show(parent, "aviewstudentdetails");
        }
    }
    
    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        loadStudentTable();
    }//GEN-LAST:event_btnRefreshActionPerformed

    private void btnStudentDetailsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStudentDetailsActionPerformed
        SwapToStudentDetails();
    }//GEN-LAST:event_btnStudentDetailsActionPerformed
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable StudentTable;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JButton btnStudentDetails;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private courserecoverysystem.uiElements.RoundedPanel roundedPanel1;
    // End of variables declaration//GEN-END:variables
}
