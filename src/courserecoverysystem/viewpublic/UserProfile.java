package courserecoverysystem.viewpublic;

import courserecoverysystem.service.FileService;
import courserecoverysystem.model.User;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.List;

public class UserProfile extends javax.swing.JPanel {
    private final FileService fileService = new FileService();
    
    public UserProfile() {
        initComponents();
        this.addComponentListener(new ComponentAdapter(){
            @Override
            public void componentShown(ComponentEvent e){
                loadProfile();
            }
        });
    }
    
    private void loadProfile() {
        User current = User.getCurrentUser();
        if (current == null) return;

        // Existing info
        txtName.setText(current.getName());
        lblEmail.setText(current.getEmail());
        lblPassword.setText(current.getPassword());

        String userId = current.getUID(); // e.g. U00001
        String role = current.getRole();     // e.g. student / lecturer / admin / officer

        // Default: Admin/Officer show User ID directly from user.txt
        String idTitle = "User ID:";
        String idValue = userId;

        // Student: find student_id from student.txt using user_id
        if (role != null && role.equalsIgnoreCase("student")) {
            idTitle = "Student ID:";
            List<String> row = fileService.retrieveOneMatchLine("student", "user_id", userId);
            if (!row.isEmpty()) {
                idValue = row.get(0); // col0 = student_id
                String firstName = (row.size() > 2) ? row.get(2) : "";
                String lastName = (row.size() > 3) ? row.get(3) : "";
                String fullName = (firstName + " " + lastName).trim();
                
                if(!fullName.isBlank()){
                    txtName.setText(fullName);
                }else {
                    txtName.setText("N/A");
                }
            }
            else {
                idValue = "N/A"; 
                txtName.setText("N/A");
            }
        }

        // Lecturer: find lecturer_id from lecturer.txt using user_id
        if (role != null && role.equalsIgnoreCase("lecturer")) {
            idTitle = "Lecturer ID:";
            List<String> row = fileService.retrieveOneMatchLine("lecturer", "user_id", userId);
            if (!row.isEmpty()) idValue = row.get(0); // col0 = lecturer_id
            else idValue = "N/A";
        }

        // Set to UI
        lblID.setText(idTitle);
        txtID.setText(idValue);
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        roundedPanel1 = new courserecoverysystem.uiElements.RoundedPanel();
        lblName = new javax.swing.JLabel();
        lblID = new javax.swing.JLabel();
        txtName = new javax.swing.JLabel();
        txtID = new javax.swing.JLabel();
        roundedPanel2 = new courserecoverysystem.uiElements.RoundedPanel();
        jLabel5 = new javax.swing.JLabel();
        lblEmail = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lblPassword = new javax.swing.JLabel();

        setBackground(new java.awt.Color(78, 118, 163));
        setDoubleBuffered(false);
        setMaximumSize(new java.awt.Dimension(1420, 820));
        setMinimumSize(new java.awt.Dimension(1420, 820));
        setName(""); // NOI18N
        setPreferredSize(new java.awt.Dimension(1420, 820));

        jLabel1.setFont(new java.awt.Font("Century Gothic", 1, 48)); // NOI18N
        jLabel1.setText("USER INFO");

        lblName.setFont(new java.awt.Font("Century Gothic", 0, 24)); // NOI18N
        lblName.setText("Name:");

        lblID.setFont(new java.awt.Font("Century Gothic", 0, 24)); // NOI18N
        lblID.setText("ID       :");

        txtName.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        txtName.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        txtID.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        txtID.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        roundedPanel2.setBackground(new java.awt.Color(143, 169, 199));

        jLabel5.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel5.setText("Email: ");

        lblEmail.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        jLabel7.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel7.setText("Password:");

        lblPassword.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        javax.swing.GroupLayout roundedPanel2Layout = new javax.swing.GroupLayout(roundedPanel2);
        roundedPanel2.setLayout(roundedPanel2Layout);
        roundedPanel2Layout.setHorizontalGroup(
            roundedPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel2Layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addGroup(roundedPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblEmail, javax.swing.GroupLayout.DEFAULT_SIZE, 480, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPassword, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(57, Short.MAX_VALUE))
        );
        roundedPanel2Layout.setVerticalGroup(
            roundedPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout roundedPanel1Layout = new javax.swing.GroupLayout(roundedPanel1);
        roundedPanel1.setLayout(roundedPanel1Layout);
        roundedPanel1Layout.setHorizontalGroup(
            roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel1Layout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(roundedPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(roundedPanel1Layout.createSequentialGroup()
                        .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblName, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
                            .addComponent(lblID, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtName, javax.swing.GroupLayout.DEFAULT_SIZE, 442, Short.MAX_VALUE)
                            .addComponent(txtID, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(319, Short.MAX_VALUE))
        );
        roundedPanel1Layout.setVerticalGroup(
            roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel1Layout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblName, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblID, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(47, 47, 47)
                .addComponent(roundedPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(103, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(323, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(roundedPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 448, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(118, 118, 118))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(147, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(roundedPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblID;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblPassword;
    private courserecoverysystem.uiElements.RoundedPanel roundedPanel1;
    private courserecoverysystem.uiElements.RoundedPanel roundedPanel2;
    private javax.swing.JLabel txtID;
    private javax.swing.JLabel txtName;
    // End of variables declaration//GEN-END:variables
}
