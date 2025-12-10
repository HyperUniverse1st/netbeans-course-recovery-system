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
import javax.swing.JOptionPane;
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

        LabelStudent = new javax.swing.JLabel();
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

        LabelStudent.setFont(new java.awt.Font("Century Gothic", 1, 48)); // NOI18N
        LabelStudent.setText("STUDENTS");

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
                .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundedPanel1Layout.createSequentialGroup()
                        .addGap(168, 168, 168)
                        .addComponent(btnRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(622, 622, 622)
                        .addComponent(btnStudentDetails, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(roundedPanel1Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1012, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18))
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
                .addContainerGap(334, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(LabelStudent, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(roundedPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(97, 97, 97)
                .addComponent(LabelStudent, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(roundedPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(57, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    
    //initialise the table model (columns, non-editable)
    private void setupTableModel(){
        tableModel = new DefaultTableModel(
                new Object[]{"Select","Student ID","User ID", "First Name", "Last Name", "Major", "Year", "Assigned to Recovery?"}, 0
        ){
            @Override
            public boolean isCellEditable(int row, int column){
                return column == 0; //user cannot edit cells
            }
            
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 0) {
                    return Boolean.class; // Checkbox
                }
                return String.class;
            }
        };
        
        StudentTable.setModel(tableModel);   
        
        //Auto-uncheck all other rows when one is selected
        tableModel.addTableModelListener(e -> {
            int row = e.getFirstRow();
            int col = e.getColumn();
            
            if(col == 0){ //Only respond to checkbox changes
                Boolean value = (Boolean) tableModel.getValueAt(row, 0);
                if(value != null && value){
                    //uncheck all other rows
                    for(int i = 0; i < tableModel.getRowCount(); i++){
                        if(i != row){
                            tableModel.setValueAt(false, i, 0);
                        }
                    }
                }
            }
        });
    }
    
    //load data from StudentService into the table
    private void loadStudentTable(){
        tableModel.setRowCount(0); //clear old rows
        
        List<Student> students = studentService.getAllStudents();
        System.out.println("Debug: students returned from service = " + students.size());
        
        for (Student s : students) {
            boolean assigned = recoveryService.hasAnyEnrollment(s.getStudentID());
            System.out.println("Student: " + s.getStudentID() + " assigned?: " + assigned);
            tableModel.addRow(new Object[]{
                false, //checkbox
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
            System.out.println("No student found.");
        }
    }
    
    private void SwapToStudentDetails() { 
        Container parent = this.getParent();
        if (parent instanceof JPanel) {
            CardLayout layout = (CardLayout) parent.getLayout();
            layout.show(parent, "aviewstudentdetails");
        }
    }
    
    private JPanel findCardParent(){
        Container c = this.getParent();
        
        while(c != null) {
            if(c instanceof JPanel && c.getLayout() instanceof CardLayout){
                return (JPanel) c; //found the card panel
            }
            c = c.getParent();
        }
        return null;
    }
    
    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        loadStudentTable();
    }//GEN-LAST:event_btnRefreshActionPerformed

    private void btnStudentDetailsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStudentDetailsActionPerformed
        int selectedRow = -1;
        int selectedCount = 0;
        
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            Boolean checked = (Boolean) tableModel.getValueAt(i, 0);
            if (checked != null && checked){
                selectedCount++;
                selectedRow = i;
            }
        }
        
        if(selectedCount == 0){
            JOptionPane.showMessageDialog(this, "Please select a student first.");
            return;
        }
        
        if (selectedCount > 1){
            JOptionPane.showMessageDialog(this,"You only can select ONE student.");
            return;
        }
        
        String studentID = (String) tableModel.getValueAt(selectedRow, 1);
        System.out.println("Selected student ID = " + studentID);
        
        
        JPanel cardPanel = findCardParent();
        if (cardPanel == null) {
            System.out.println("Error: Card Layout parent not found.");
            return;
        }
        
        AdminStudentDetails detailsPanel = null;
        for(java.awt.Component comp : cardPanel.getComponents()){
            if(comp instanceof AdminStudentDetails){
                detailsPanel = (AdminStudentDetails) comp;
                break;
            }
        }
       
        if (detailsPanel == null) {
        System.out.println("ERROR: AdminStudentDetails panel NOT found inside mainPanel.");
        return;
    }

    // Load student info
    detailsPanel.loadStudent(studentID);

    // Show details screen
    CardLayout layout = (CardLayout) cardPanel.getLayout();
    layout.show(cardPanel, "aviewstudentdetails");
    }//GEN-LAST:event_btnStudentDetailsActionPerformed
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel LabelStudent;
    private javax.swing.JTable StudentTable;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JButton btnStudentDetails;
    private javax.swing.JScrollPane jScrollPane1;
    private courserecoverysystem.uiElements.RoundedPanel roundedPanel1;
    // End of variables declaration//GEN-END:variables
}
