package courserecoverysystem.view.officer;
import courserecoverysystem.LoginScreen;
import java.awt.CardLayout;
import java.awt.Container;
import javax.swing.JPanel;


public class OfficerMain extends javax.swing.JPanel {
    public OfficerMain() {
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
        btnViewAnalytics = new javax.swing.JButton();
        btnViewStudents = new javax.swing.JButton();
        btnViewCourses = new javax.swing.JButton();
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

        btnViewAnalytics.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        btnViewAnalytics.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/pfp/AnalyticsLogo.png"))); // NOI18N
        btnViewAnalytics.setText("View Analytics");
        btnViewAnalytics.setAutoscrolls(true);
        btnViewAnalytics.setContentAreaFilled(true);
        btnViewAnalytics.setFocusPainted(false);
        btnViewAnalytics.setBorderPainted(false);
        btnViewAnalytics.setHideActionText(true);
        btnViewAnalytics.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnViewAnalytics.setIconTextGap(20);
        btnViewAnalytics.setMargin(new java.awt.Insets(2, 35, 3, 14));
        btnViewAnalytics.setName(""); // NOI18N
        btnViewAnalytics.setOpaque(true);
        btnViewAnalytics.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewAnalyticsActionPerformed(evt);
            }
        });

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

        btnViewCourses.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        btnViewCourses.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/pfp/CourseLogo.png"))); // NOI18N
        btnViewCourses.setText("View Courses");
        btnViewCourses.setAutoscrolls(true);
        btnViewCourses.setFocusPainted(false);
        btnViewCourses.setHideActionText(true);
        btnViewCourses.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnViewCourses.setIconTextGap(20);
        btnViewCourses.setMargin(new java.awt.Insets(2, 35, 3, 14));
        btnViewCourses.setName(""); // NOI18N
        btnViewCourses.setOpaque(true);
        btnViewCourses.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewCoursesActionPerformed(evt);
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnProfile, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(label5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(lblName, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(btnViewStudents, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnViewCourses, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnViewAnalytics, javax.swing.GroupLayout.PREFERRED_SIZE, 679, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(138, 138, 138))
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
                .addComponent(btnViewCourses, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(btnViewAnalytics, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(143, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    private void SwapToStudents() { 
    Container parent = this.getParent();
    if (parent instanceof JPanel) {
        CardLayout layout = (CardLayout) parent.getLayout();
        layout.show(parent, "oviewstudents");
    }
    }
    
    private void SwapToCourses() { 
    Container parent = this.getParent();
    if (parent instanceof JPanel) {
        CardLayout layout = (CardLayout) parent.getLayout();
        layout.show(parent, "oviewcourses");
    }
    }
    
    private void SwapToAnalytics() { 
    Container parent = this.getParent();
    if (parent instanceof JPanel) {
        CardLayout layout = (CardLayout) parent.getLayout();
        layout.show(parent, "oanalytics");
    }
    }
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        LoginScreen.logout();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnViewAnalyticsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewAnalyticsActionPerformed
        SwapToAnalytics();
    }//GEN-LAST:event_btnViewAnalyticsActionPerformed

    private void btnViewStudentsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewStudentsActionPerformed
        SwapToStudents();
    }//GEN-LAST:event_btnViewStudentsActionPerformed

    private void btnViewCoursesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewCoursesActionPerformed
        SwapToCourses();
    }//GEN-LAST:event_btnViewCoursesActionPerformed

    private void btnProfileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProfileActionPerformed
        Container parent = this.getParent();
        if (parent instanceof JPanel) {
            CardLayout layout = (CardLayout) parent.getLayout();
            layout.show(parent, "userprofile");
        }
    }//GEN-LAST:event_btnProfileActionPerformed

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnProfile;
    private javax.swing.JButton btnViewAnalytics;
    private javax.swing.JButton btnViewCourses;
    private javax.swing.JButton btnViewStudents;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private java.awt.Label label5;
    private java.awt.Label lblName;
    // End of variables declaration//GEN-END:variables
}
