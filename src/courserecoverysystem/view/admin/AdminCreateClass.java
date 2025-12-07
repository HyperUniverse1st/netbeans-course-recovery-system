/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package courserecoverysystem.view.admin;

import courserecoverysystem.model.Lecturer;
import courserecoverysystem.model.Student;
import courserecoverysystem.service.LecturerService;
import courserecoverysystem.service.ClassService;
import courserecoverysystem.service.StudentService;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.util.List;
import java.awt.CardLayout;
import java.awt.Container;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


public class AdminCreateClass extends javax.swing.JPanel {
    private LecturerService lecturerService = new LecturerService();
    private StudentService studentService = new StudentService();
    private ClassService classService = new ClassService();
    
    public AdminCreateClass() {
        initComponents();
        loadSubjectsToComboBox();
        setupSubjectListener();
        loadTimeSlots();
        loadDateChoices();
        // listStudents for the student list
        // btnRefresh
        // comboLecturer
        // comboSubject
        // comboTime
        // comboDate
        // btnCreate
    }
    private void loadSubjectsToComboBox(){
        comboSubject.removeAllItems();
        comboSubject.addItem("--Select Subject--");
        
        List<String> subjects = lecturerService.getAllSubjects();
        
        for(String subject : subjects){
            comboSubject.addItem(subject);
        }
    }
    
    private void setupSubjectListener(){
        comboSubject.addActionListener(e -> {
            String selected = (String) comboSubject.getSelectedItem();
            if(selected == null || selected.startsWith("--")){
                comboLecturer.removeAllItems();
                clearStudentsTable();
                return;
            }
            loadLecturersForSubject(selected);
            loadStudentsForSubject(selected);
        });
    }
    
    private void loadLecturersForSubject(String subject){
        comboLecturer.removeAllItems();
        
        List<Lecturer> lecturers = lecturerService.getLecturersByMajor(subject);
        
        for(Lecturer lec : lecturers){
            comboLecturer.addItem(lec.getName());
        }
    }
    
    private void clearStudentsTable(){
        DefaultTableModel model = (DefaultTableModel) StudentTable.getModel();
        model.setRowCount(0); //remove all rows
    }
    
    private void loadStudentsForSubject(String subject){
        DefaultTableModel model = (DefaultTableModel) StudentTable.getModel();
        model.setRowCount(0); //clear old rows
        
        List<Student> students = studentService.getFailedStudentsByMajor(subject);
        
        for(Student s : students){
            String fullName = s.getFirstName() + " " + s.getLastName();
            //column 0 = checkbox (Boolean), 1 = Student ID, 2 = Name
            model.addRow(new Object[] {false, s.getStudentID(), fullName });
        }
    }
    
    private void loadDateChoices(){
        comboDate.removeAllItems();
        comboDate.addItem("--Select Date--");
        
        LocalDate today = LocalDate.now();
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd MMM yyyy");
        
        //only can choose date after today
        for (int i = 1; i <= 30; i++){ //next 30 days
            LocalDate d = today.plusDays(i);
            String label = d.format(fmt); // 13 Dec 2025
            comboDate.addItem(label);
        }
    }
    
    private void loadTimeSlots() {
        comboTime.removeAllItems();
        comboTime.addItem("-- Select Time Slot --");
        comboTime.addItem("08:30 - 10:30");
        comboTime.addItem("10:45 - 12:45");
        comboTime.addItem("13:30 - 15:30");
        comboTime.addItem("15:45 - 17:45");
    }

    private void resetForm(){
        comboSubject.setSelectedIndex(0);//clear subject
        comboLecturer.removeAllItems();//clear lecturer
        comboLecturer.addItem("");
        comboDate.setSelectedIndex(0);//clear date
        comboTime.setSelectedIndex(0); //clear time
        clearStudentsTable();//clear student table
    }
    
    private void SwapToClasses() { 
    Container parent = this.getParent();
    if (parent instanceof JPanel) {
        CardLayout layout = (CardLayout) parent.getLayout();
        layout.show(parent, "aviewclasses");
    }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        roundedPanel1 = new courserecoverysystem.uiElements.RoundedPanel();
        comboSubject = new javax.swing.JComboBox<>();
        comboTime = new javax.swing.JComboBox<>();
        comboLecturer = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        btnCreate = new javax.swing.JButton();
        btnRefresh = new javax.swing.JButton();
        btnBack2 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        comboDate = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        StudentTable = new javax.swing.JTable();

        setBackground(new java.awt.Color(78, 118, 163));
        setDoubleBuffered(false);
        setMaximumSize(new java.awt.Dimension(1420, 820));
        setMinimumSize(new java.awt.Dimension(1420, 820));
        setName(""); // NOI18N
        setPreferredSize(new java.awt.Dimension(1420, 820));

        jLabel1.setFont(new java.awt.Font("Century Gothic", 1, 48)); // NOI18N
        jLabel1.setText("CREATE CLASS");

        jLabel2.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel2.setText("Subject");

        jLabel4.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel4.setText("Time");

        jLabel5.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel5.setText("Students To Add");

        jLabel6.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel6.setText("Lecturer");

        btnCreate.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        btnCreate.setText("Create");
        btnCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateActionPerformed(evt);
            }
        });

        btnRefresh.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        btnRefresh.setText("Refresh Fields");
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });

        btnBack2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/pfp/back.png"))); // NOI18N
        btnBack2.setBorder(null);
        btnBack2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBack2ActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel7.setText("Date");

        comboDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboDateActionPerformed(evt);
            }
        });

        StudentTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Select", "Student ID", "Name"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(StudentTable);

        javax.swing.GroupLayout roundedPanel1Layout = new javax.swing.GroupLayout(roundedPanel1);
        roundedPanel1.setLayout(roundedPanel1Layout);
        roundedPanel1Layout.setHorizontalGroup(
            roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundedPanel1Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(btnBack2, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 81, Short.MAX_VALUE)
                .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(roundedPanel1Layout.createSequentialGroup()
                        .addComponent(btnRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnCreate, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 565, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundedPanel1Layout.createSequentialGroup()
                        .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(comboSubject, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboLecturer, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(comboTime, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboDate, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(241, 241, 241))
        );
        roundedPanel1Layout.setVerticalGroup(
            roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel1Layout.createSequentialGroup()
                .addContainerGap(48, Short.MAX_VALUE)
                .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundedPanel1Layout.createSequentialGroup()
                        .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(comboSubject, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboDate, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(btnBack2))
                .addGap(18, 18, 18)
                .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboLecturer, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboTime, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49)
                .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCreate)
                    .addComponent(btnRefresh))
                .addGap(62, 62, 62))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(383, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 464, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(roundedPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(58, 58, 58))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(91, 91, 91)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(roundedPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(13, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateActionPerformed
        String subject = (String) comboSubject.getSelectedItem();
        String lecturerName = (String) comboLecturer.getSelectedItem();
        String date = (String) comboDate.getSelectedItem();
        String time = (String) comboTime.getSelectedItem();

        // 1. Basic validation
        if (subject == null || subject.startsWith("--")) {
            JOptionPane.showMessageDialog(this, "Please select a subject.");
            return;
        }
        if (lecturerName == null || lecturerName.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please select a lecturer.");
            return;
        }
        if (date == null || date.startsWith("--") || date.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please select a date.");
            return;
        }
        if (time == null || time.startsWith("--") || time.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please select a time slot.");
            return;
        }

        // 2. Get selected students from the table
        DefaultTableModel model = (DefaultTableModel) StudentTable.getModel();
        java.util.List<String> selectedStudentIds = new java.util.ArrayList<>();

        for (int i = 0; i < model.getRowCount(); i++) {
            Boolean selected = (Boolean) model.getValueAt(i, 0); // column 0 = checkbox
            if (Boolean.TRUE.equals(selected)) {
                String studentId = (String) model.getValueAt(i, 1); // column 1 = Student ID
                selectedStudentIds.add(studentId);
            }
        }

        if (selectedStudentIds.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please select at least one student.");
            return;   // <-- important
        }

        // 3. Check student schedule clashes for this date + time
        java.util.List<String> clashStudents =
                classService.findStudentConflicts(date, time, selectedStudentIds);

        if (!clashStudents.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Cannot create class. These students already have a class at this time:\n"
                            + String.join(", ", clashStudents),
                    "Schedule Conflict",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        // (Optional) check lecturer clash as well, if you implemented it:
        /*
        if (classService.isLecturerConflict(lecturerName, date, time)) {
            JOptionPane.showMessageDialog(this,
                    "This lecturer already has a class at this time.",
                    "Lecturer Conflict",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }
        */

        // 4. Generate new class ID and save
        String classId = classService.getNextClassId();
        classService.saveClass(classId, subject, lecturerName, date, time, selectedStudentIds);

        // 5. Mark these students as assigned in student.txt
        studentService.markStudentsAssigned(selectedStudentIds);

        // 6. Show success message
        JOptionPane.showMessageDialog(this, "Class " + classId + " created successfully.");

        // 7. Reset the form back to default (subject/date/time to 'Select', table empty)
        resetForm();
    }//GEN-LAST:event_btnCreateActionPerformed

    private void btnBack2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBack2ActionPerformed
        SwapToClasses();
    }//GEN-LAST:event_btnBack2ActionPerformed

    private void comboDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboDateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboDateActionPerformed

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        resetForm();
    }//GEN-LAST:event_btnRefreshActionPerformed



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable StudentTable;
    private javax.swing.JButton btnBack2;
    private javax.swing.JButton btnCreate;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JComboBox<String> comboDate;
    private javax.swing.JComboBox<String> comboLecturer;
    private javax.swing.JComboBox<String> comboSubject;
    private javax.swing.JComboBox<String> comboTime;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane2;
    private courserecoverysystem.uiElements.RoundedPanel roundedPanel1;
    // End of variables declaration//GEN-END:variables
}
