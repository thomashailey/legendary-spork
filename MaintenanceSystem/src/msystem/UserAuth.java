/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package msystem;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JOptionPane;

/**
 *
 * @author thoma
 */
public class UserAuth extends javax.swing.JFrame {
    DBConnect db = new DBConnect();
    Connection con = null;
    PreparedStatement stmt;
    ResultSet result;
    String sql = null;
    boolean passUser = false;
    
    /**
     * Creates new form UserAuth
     */
    public UserAuth() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tempNextPage = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        lblUsercode = new javax.swing.JLabel();
        lblPassword = new javax.swing.JLabel();
        txtUsercode = new javax.swing.JTextField();
        jpfPassword = new javax.swing.JPasswordField();
        userAuthenByassBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tempNextPage.setText("Log in");
        tempNextPage.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tempNextPageMouseClicked(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setText("User Authentication");

        lblUsercode.setText("User Code");

        lblPassword.setText("Password");

        txtUsercode.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtUsercodeFocusLost(evt);
            }
        });

        userAuthenByassBtn.setText("Bypass");
        userAuthenByassBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                userAuthenByassBtnMouseClicked(evt);
            }
        });
        userAuthenByassBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userAuthenByassBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(106, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(83, 83, 83))
            .addGroup(layout.createSequentialGroup()
                .addGap(126, 126, 126)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jpfPassword)
                            .addComponent(txtUsercode, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(47, 47, 47)
                                .addComponent(lblPassword))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(44, 44, 44)
                                .addComponent(lblUsercode)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(tempNextPage)
                        .addGap(18, 18, 18)
                        .addComponent(userAuthenByassBtn)
                        .addGap(70, 70, 70))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(28, 28, 28)
                .addComponent(lblUsercode)
                .addGap(11, 11, 11)
                .addComponent(txtUsercode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblPassword)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jpfPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tempNextPage)
                    .addComponent(userAuthenByassBtn))
                .addContainerGap(76, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tempNextPageMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tempNextPageMouseClicked
        // TODO add your handling code here:
        String userIdToPull = txtUsercode.getText();
        String userPassToPull = jpfPassword.getText();
        if(checkPrimaryKey(txtUsercode.getText())){
//            JOptionPane.showMessageDialog(null, "Correct!");
            sql = String.format("SELECT Password FROM user_authoization WHERE UserID = %s",
                    userIdToPull);
            try{
                con = db.OpenConnection();
                stmt = con.prepareStatement(sql);
                result = stmt.executeQuery(sql);
                while(result.next()){
                    if(userPassToPull.equals(result.getString("Password"))){
                        System.out.println("password accepted");
                        this.setVisible(false);
                        new MainPage().setVisible(true);
                    }
                
                
                    else{
                        System.out.println("passwords do not match");
                        System.out.println(stmt.executeQuery(sql));
                    }
                }
                con.close();
            }
            catch(Exception e){
                System.out.println(e);
            }
        }
        
    }//GEN-LAST:event_tempNextPageMouseClicked

    private void txtUsercodeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtUsercodeFocusLost
        // TODO add your handling code here:
        // Write userId field value to file
        try {
            File userIdFile = new File("currentuser.txt");
            if (userIdFile.createNewFile()) {
                System.out.println("File created: " + userIdFile.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        
        try {
            FileWriter myWriter = new FileWriter("currentuser.txt");
            myWriter.write(txtUsercode.getText());
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }//GEN-LAST:event_txtUsercodeFocusLost

    private void userAuthenByassBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userAuthenByassBtnActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        new MainPage().setVisible(true);
    }//GEN-LAST:event_userAuthenByassBtnActionPerformed

    private void userAuthenByassBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_userAuthenByassBtnMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_userAuthenByassBtnMouseClicked
    private boolean checkPrimaryKey(String userCode){
        sql = String.format("SELECT * FROM user_authoization WHERE UserID = %s", 
                userCode);
        
        try{
            con = db.OpenConnection();
            stmt = con.prepareStatement(sql);
            passUser = stmt.execute(sql);
            con.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
        System.out.println(passUser);
        return passUser;
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(UserAuth.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UserAuth.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UserAuth.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UserAuth.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UserAuth().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPasswordField jpfPassword;
    private javax.swing.JLabel lblPassword;
    private javax.swing.JLabel lblUsercode;
    private javax.swing.JButton tempNextPage;
    private javax.swing.JTextField txtUsercode;
    private javax.swing.JButton userAuthenByassBtn;
    // End of variables declaration//GEN-END:variables
}
