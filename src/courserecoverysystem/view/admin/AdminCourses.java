/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package courserecoverysystem.view.admin;

import courserecoverysystem.service.ClassService;
import courserecoverysystem.uiElements.TableUtils;
import java.util.List;
import java.awt.CardLayout;
import java.awt.Container;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;



public class AdminCourses extends javax.swing.JPanel {
    private ClassService classService = new ClassService();
    
    public AdminCourses() {
        initComponents();
        loadClassesToTable();
        TableUtils.centerAllColumns(CourseTable);
        // ClassesTable for the jtable
        // btnRefresh
    }
    
    private void loadClassesToTable(){
        DefaultTableModel model = (DefaultTableModel) CourseTable.getModel();
        model.setRowCount(0);
        List<String> lines = classService.getAllClassLines();
        
        for(String line : lines){
            if(line == null || line.trim().isEmpty()) continue;
            String[] parts = line.split("\\|");
            
            if(parts.length < 6) continue;
            
            model.addRow(new Object[]{
                false,           //Checkbox column for admin to select class
                parts[0].trim(), //Class id
                parts[1].trim(), //Subject 
                parts[2].trim(), //Lecturer
                parts[3].trim(), //Date
                parts[4].trim(), //Time
                parts[5].trim() //Student ID
            });
        }
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        roundedPanel1 = new courserecoverysystem.uiElements.RoundedPanel();
        btnRefresh = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        CourseTable = new javax.swing.JTable();

        setBackground(new java.awt.Color(78, 118, 163));
        setDoubleBuffered(false);
        setMaximumSize(new java.awt.Dimension(1420, 820));
        setMinimumSize(new java.awt.Dimension(1420, 820));
        setName(""); // NOI18N
        setPreferredSize(new java.awt.Dimension(1420, 820));

        jLabel1.setFont(new java.awt.Font("Century Gothic", 1, 48)); // NOI18N
        jLabel1.setText("Courses");

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

            }
        ));
        jScrollPane2.setViewportView(CourseTable);

        javax.swing.GroupLayout roundedPanel1Layout = new javax.swing.GroupLayout(roundedPanel1);
        roundedPanel1.setLayout(roundedPanel1Layout);
        roundedPanel1Layout.setHorizontalGroup(
            roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel1Layout.createSequentialGroup()
                .addGap(82, 82, 82)
                .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 795, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(89, Short.MAX_VALUE))
        );
        roundedPanel1Layout.setVerticalGroup(
            roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 473, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(56, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(396, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(roundedPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(58, 58, 58))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(91, 91, 91)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(roundedPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(55, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
   
    private void SwapToCreateClass() { 
        Container parent = this.getParent();
        if (parent instanceof JPanel) {
            CardLayout layout = (CardLayout) parent.getLayout();
            layout.show(parent, "acreateclass");
        }
    } 

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        loadClassesToTable();
        TableUtils.centerAllColumns(CourseTable);
    }//GEN-LAST:event_btnRefreshActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable CourseTable;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane2;
    private courserecoverysystem.uiElements.RoundedPanel roundedPanel1;
    // End of variables declaration//GEN-END:variables
}
