/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package msystem;

import java.awt.List;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import static msystem.MainPage.selectingThisEndorsement;
import static msystem.MainPage.selectingThisPassword;
import static msystem.MainPage.selectingThisRole;
import static msystem.MainPage.selectingThisUserID;
import static msystem.MainPage.selectingThisUsername;

/**
 *
 * @author never
 */
public class EditEmployee extends javax.swing.JFrame {
    static boolean passwordEdit = false;
    static boolean passAsk = false;
    String sql = null;
    DBConnect db = new DBConnect();
    Connection con = null;
    PreparedStatement stmt;
    ResultSet result;
    
    /**
     * Creates new form EditEmployee
     */
    public EditEmployee() {
        initComponents();
        passwordEdit = false;
        
        System.out.println(MainPage.editEmployee);
        if(MainPage.editEmployee){
            editEmpUserIDtxt.setText(MainPage.selectingThisUserID);
            editEmpUsernametxt.setText(MainPage.selectingThisUsername);
            editEmpRoleComboBox.setSelectedItem(MainPage.selectingThisRole);
            editEmpEndorseComboBox.setSelectedItem(MainPage.selectingThisEndorsement);
            editEmpUserIDtxt.setFocusable(false);
        }
        /*
        System.out.println("This is the edit employee accessing the previous pull");
        System.out.println(String.format("%12s -- %s", "UserID",selectingThisUserID));
        System.out.println(String.format("%12s -- %s", "Username", selectingThisUsername));
        System.out.println(String.format("%12s -- %s", "Password", selectingThisPassword));
        System.out.println(String.format("%12s -- %s", "Role", selectingThisRole));
        System.out.println(String.format("%12s -- %s", "Endorsement", selectingThisEndorsement));
        */
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        editEmpUserIDtxt = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        editEmpUsernametxt = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        editEmpPasswordtxt = new javax.swing.JTextArea();
        editEmpEndorseComboBox = new javax.swing.JComboBox<>();
        editEmpRoleComboBox = new javax.swing.JComboBox<>();
        editEmpConfirmButton = new javax.swing.JButton();
        editEmpCancelButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        editEmpUserIDtxt.setColumns(20);
        editEmpUserIDtxt.setFont(new java.awt.Font("Cascadia Code", 0, 12)); // NOI18N
        editEmpUserIDtxt.setRows(1);
        jScrollPane1.setViewportView(editEmpUserIDtxt);

        editEmpUsernametxt.setColumns(20);
        editEmpUsernametxt.setFont(new java.awt.Font("Cascadia Code", 0, 12)); // NOI18N
        editEmpUsernametxt.setRows(1);
        jScrollPane2.setViewportView(editEmpUsernametxt);

        editEmpPasswordtxt.setColumns(20);
        editEmpPasswordtxt.setFont(new java.awt.Font("Cascadia Code", 0, 12)); // NOI18N
        editEmpPasswordtxt.setRows(1);
        editEmpPasswordtxt.setText("***************");
        editEmpPasswordtxt.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                editEmpPasswordtxtFocusGained(evt);
            }
        });
        jScrollPane3.setViewportView(editEmpPasswordtxt);

        editEmpEndorseComboBox.setFont(new java.awt.Font("Cascadia Code", 0, 12)); // NOI18N
        editEmpEndorseComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "None" }));

        editEmpRoleComboBox.setFont(new java.awt.Font("Cascadia Code", 0, 12)); // NOI18N
        editEmpRoleComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Admin", "Management", "Maintenance", "Warehouse", "Technician" }));
        editEmpRoleComboBox.setSelectedItem("Technician");
        editEmpRoleComboBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                editEmpRoleComboBoxItemStateChanged(evt);
            }
        });
        editEmpRoleComboBox.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                editEmpRoleComboBoxPropertyChange(evt);
            }
        });

        editEmpConfirmButton.setText("Confirm");
        editEmpConfirmButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                editEmpConfirmButtonMouseClicked(evt);
            }
        });

        editEmpCancelButton.setText("Cancel");
        editEmpCancelButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                editEmpCancelButtonMouseClicked(evt);
            }
        });

        jLabel1.setText("User ID");

        jLabel2.setText("User Name");

        jLabel3.setText("Password");

        jLabel4.setText("Role");

        jLabel5.setText("Endorsement");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel5)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel2)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(editEmpConfirmButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(editEmpCancelButton))))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(editEmpRoleComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(editEmpEndorseComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(editEmpRoleComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(editEmpEndorseComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(editEmpConfirmButton)
                    .addComponent(editEmpCancelButton))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void editEmpCancelButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editEmpCancelButtonMouseClicked
        // TODO add your handling code here:
        this.setVisible(false);
        MainPage.editEmployee = false;
    }//GEN-LAST:event_editEmpCancelButtonMouseClicked

    private void editEmpConfirmButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editEmpConfirmButtonMouseClicked
        // TODO add your handling code here:
        if(MainPage.editEmployee){
            if(editEmpPasswordtxt.getText().equals("***************")){
                sql = String.format("UPDATE user_authoization SET Username = \"%s\", Role = \"%s\", Endorsement = \"%s\" WHERE UserID = %s", 
                editEmpUsernametxt.getText(), editEmpRoleComboBox.getSelectedItem(), editEmpEndorseComboBox.getSelectedItem(),
                editEmpUserIDtxt.getText());
            }
            else{
                sql = String.format("UPDATE user_authoization SET Username = \"%s\", Role = \"%s\", Endorsement = \"%s\", Password = \"%s\" WHERE UserID = %s ", 
                editEmpUsernametxt.getText(), editEmpRoleComboBox.getSelectedItem(), editEmpEndorseComboBox.getSelectedItem(),
                editEmpPasswordtxt.getText(), editEmpUserIDtxt.getText());
            }
        }
        else{
            if(editEmpPasswordtxt.getText().equals("***************")){
                //password is a placeholder here and is intended to be used as the initial password given to all employees
                sql = String.format("INSERT INTO user_authoization VALUES(\"%s\", \"%s\", 'password123', \"%s\", \"%s\")", 
                editEmpUserIDtxt.getText(), editEmpUsernametxt.getText(),
                editEmpRoleComboBox.getSelectedItem(), editEmpEndorseComboBox.getSelectedItem());
            }
            else{
                //password defined as the specific password the employee has chosen
                sql = String.format("INSERT INTO user_authoization VALUES(\"%s\", \"%s\", \"%s\", \"%s\", \"%s\")", 
                editEmpUserIDtxt.getText(), editEmpUsernametxt.getText(), editEmpPasswordtxt.getText(),
                editEmpRoleComboBox.getSelectedItem(), editEmpEndorseComboBox.getSelectedItem());
            }
        }
        
        
        System.out.println(sql);
        // JOptionPane.showMessageDialog(null, sql);
        // add the edit sql query here
        // and a confirm popup/toast
        
        
        try {
            con = db.OpenConnection();
            stmt = con.prepareStatement(sql);
            stmt.execute();
            
            if (result != null) {
                if(MainPage.editEmployee){
                    System.out.println(String.format("Successfully Accessed DataBase to edit user %s", editEmpUserIDtxt.getText()));
                }
                else{
                    System.out.println(String.format("Successfully Accessed DataBase to add user %s", editEmpUserIDtxt.getText()));
                }
            }
            /*
            while (result.next()) {
                System.out.print(result.getString("Username") + ", " + result.getString("UserID"));
            }*/
            con.close();
            System.out.println("Database closed");
        }
        catch(Exception e) {
            System.out.println(e);
        }
        MainPage.editEmployee = false;
        this.setVisible(false);

    }//GEN-LAST:event_editEmpConfirmButtonMouseClicked

    private void editEmpRoleComboBoxPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_editEmpRoleComboBoxPropertyChange
        // TODO add your handling code here:
        //only need if below method does not work
    }//GEN-LAST:event_editEmpRoleComboBoxPropertyChange

    private void editEmpRoleComboBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_editEmpRoleComboBoxItemStateChanged
        // TODO add your handling code here:
        
        ArrayList<String> techEndos = new ArrayList<String>();
        ArrayList<String> manageEndos = new ArrayList<String>();
        ArrayList<String> maintEndos = new ArrayList<String>();
        ArrayList<String> wareEndos = new ArrayList<String>();
        
        Collections.addAll(techEndos, "None");
        Collections.addAll(manageEndos, "Staff","Maintenance","Warehouse","Technician");
        Collections.addAll(maintEndos, "Electrician","Machine","Painter","Welder","Plumber","Carpenter","None");
        Collections.addAll(wareEndos, "Inventory","Equipment");
      
        DefaultComboBoxModel tModel = new DefaultComboBoxModel();
        DefaultComboBoxModel bModel = new DefaultComboBoxModel();
        DefaultComboBoxModel mModel = new DefaultComboBoxModel();
        DefaultComboBoxModel wModel = new DefaultComboBoxModel();

        tModel.addAll(techEndos);
        bModel.addAll(manageEndos);
        mModel.addAll(maintEndos);
        wModel.addAll(wareEndos);
        
        //Admin employees have the same endorcements as technicians
        if(editEmpRoleComboBox.getSelectedItem().equals("Admin")){
            editEmpEndorseComboBox.setModel(tModel);
            editEmpEndorseComboBox.setSelectedIndex(0);
            System.out.println("Admin detected");
        }
        else if(editEmpRoleComboBox.getSelectedItem().equals("Management")){
            editEmpEndorseComboBox.setModel(bModel);
            editEmpEndorseComboBox.setSelectedIndex(0);
            System.out.println("boss detected");

        }
        else if(editEmpRoleComboBox.getSelectedItem().equals("Maintenance")){
            editEmpEndorseComboBox.setModel(mModel);
            editEmpEndorseComboBox.setSelectedIndex(0);
            System.out.println("maint detected");

        }
        else if(editEmpRoleComboBox.getSelectedItem().equals("Warehouse")){
            editEmpEndorseComboBox.setModel(wModel);
            editEmpEndorseComboBox.setSelectedIndex(0);
            System.out.println("ware detected");

        }
        else if(editEmpRoleComboBox.getSelectedItem().equals("Technician")){
            editEmpEndorseComboBox.setModel(tModel);
            editEmpEndorseComboBox.setSelectedIndex(0);
            System.out.println("tech detected");

        }
    }//GEN-LAST:event_editEmpRoleComboBoxItemStateChanged

    private void editEmpPasswordtxtFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_editEmpPasswordtxtFocusGained
        // TODO add your handling code here:
        // toast?
        if(!passwordEdit){
            jScrollPane1.requestFocus();
            new EditEmployeePasswordConfirm().setVisible(true);
            passAsk = true;
            
        }
    }//GEN-LAST:event_editEmpPasswordtxtFocusGained

    /**
     * @param args the command line arguments
     */
    public void main(String args[]) {

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
            java.util.logging.Logger.getLogger(EditEmployee.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EditEmployee.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EditEmployee.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EditEmployee.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */

        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                
                new EditEmployee().setVisible(true);
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton editEmpCancelButton;
    private javax.swing.JButton editEmpConfirmButton;
    public static javax.swing.JComboBox<String> editEmpEndorseComboBox;
    private javax.swing.JTextArea editEmpPasswordtxt;
    public static javax.swing.JComboBox<String> editEmpRoleComboBox;
    public static javax.swing.JTextArea editEmpUserIDtxt;
    public static javax.swing.JTextArea editEmpUsernametxt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    public javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    // End of variables declaration//GEN-END:variables
}
