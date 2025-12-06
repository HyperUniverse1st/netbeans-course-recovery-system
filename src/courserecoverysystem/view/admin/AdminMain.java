package courserecoverysystem.view.admin;

import courserecoverysystem.view.officer.*;
import courserecoverysystem.view.lecturer.*;
import courserecoverysystem.LoginScreen;
import java.awt.CardLayout;
import java.awt.Container;
import javax.swing.JPanel;

public class AdminMain extends javax.swing.JPanel {
    public AdminMain() {
        // lblName for the welcome !! DONT FORGET THIS
        
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        lblName = new java.awt.Label();
        jLabel1 = new javax.swing.JLabel();
        label5 = new java.awt.Label();
        btnViewStudents = new javax.swing.JButton();
        btnViewClasses = new javax.swing.JButton();
        btnProfile = new javax.swing.JButton();

        setBackground(new java.awt.Color(78, 118, 163));
        setDoubleBuffered(false);
        setMaximumSize(new java.awt.Dimension(1420, 820));
        setMinimumSize(new java.awt.Dimension(1420, 820));
        setName(""); // NOI18N
        setPreferredSize(new java.awt.Dimension(1420, 820));

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

        btnViewStudents.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        btnViewStudents.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/pfp/studentlogo.png"))); // NOI18N
        btnViewStudents.setText("View Students");
        btnViewStudents.setAutoscrolls(true);
        btnViewStudents.setFocusPainted(false);
        btnViewStudents.setHideActionText(true);
        btnViewStudents.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnViewStudents.setIconTextGap(20);
        btnViewStudents.setMargin(new java.awt.Insets(2, 35, 3, 14));
        btnViewStudents.setName(""); // NOI18N
        btnViewStudents.setOpaque(true);
        btnViewStudents.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewStudentsActionPerformed(evt);
            }
        });

        btnViewClasses.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        btnViewClasses.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/pfp/CourseLogo.png"))); // NOI18N
        btnViewClasses.setText("View Classes");
        btnViewClasses.setAutoscrolls(true);
        btnViewClasses.setFocusPainted(false);
        btnViewClasses.setHideActionText(true);
        btnViewClasses.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnViewClasses.setIconTextGap(20);
        btnViewClasses.setMargin(new java.awt.Insets(2, 35, 3, 14));
        btnViewClasses.setName(""); // NOI18N
        btnViewClasses.setOpaque(true);
        btnViewClasses.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewClassesActionPerformed(evt);
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(454, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(label5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblName, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnViewStudents, javax.swing.GroupLayout.DEFAULT_SIZE, 679, Short.MAX_VALUE)
                            .addComponent(btnViewClasses, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(287, 287, 287))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnProfile, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(138, 138, 138))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnProfile, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(44, 44, 44)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblName, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(label5, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addComponent(btnViewStudents, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(btnViewClasses, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(290, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    private void SwapToStudents() { 
    Container parent = this.getParent();
    if (parent instanceof JPanel) {
        CardLayout layout = (CardLayout) parent.getLayout();
        layout.show(parent, "aviewstudents");
    }
    }

    private void SwapToClasses() { 
    Container parent = this.getParent();
    if (parent instanceof JPanel) {
        CardLayout layout = (CardLayout) parent.getLayout();
        layout.show(parent, "aviewclasses");
    }
    }
    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        LoginScreen.logout();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnViewStudentsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewStudentsActionPerformed
        SwapToStudents();
    }//GEN-LAST:event_btnViewStudentsActionPerformed

    private void btnViewClassesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewClassesActionPerformed
        SwapToClasses();
    }//GEN-LAST:event_btnViewClassesActionPerformed

    private void btnProfileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProfileActionPerformed
        Container parent = this.getParent();
        if (parent instanceof JPanel) {
            CardLayout layout = (CardLayout) parent.getLayout();
            layout.show(parent, "userprofile");
        }
    }//GEN-LAST:event_btnProfileActionPerformed

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnProfile;
    private javax.swing.JButton btnViewClasses;
    private javax.swing.JButton btnViewStudents;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private java.awt.Label label5;
    private java.awt.Label lblName;
    // End of variables declaration//GEN-END:variables
}
