/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package msystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

/**
 *
 * @author thoma
 */
public class CheckInScreen extends javax.swing.JFrame {

    /**
     * Creates new form CheckOutScreen
     */
    String sql = null;
    DBConnect db = new DBConnect();
    Connection con = null;
    PreparedStatement stmt;
    ResultSet result;
    
    
    
    public CheckInScreen() {
        initComponents();
        
        // setup dropdown for equipment options (empty until search by ID)
        ArrayList<String> list = new ArrayList();
        DefaultListModel model = new DefaultListModel();
        model.addAll(list);
        checkinEquipList.setModel(model);
        
        // setup dropdown for location
        ArrayList<String> locationOptions = new ArrayList<String>();
        Collections.addAll(locationOptions, "Primary","Secondary");
        DefaultComboBoxModel locationModel = new DefaultComboBoxModel();
        locationModel.addAll(locationOptions);
        checkinLocationDropdown.setModel(locationModel);
        checkinLocationDropdown.setSelectedIndex(0);
    }
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        checkinUserIDField = new javax.swing.JTextField();
        checkinCheckInBtn = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        checkinCancelBtn = new javax.swing.JButton();
        checkinSearchBtn = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        checkinLocationDropdown = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        checkinEquipList = new javax.swing.JList<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel2.setText("User ID:");

        checkinCheckInBtn.setText("Check In");
        checkinCheckInBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                checkinCheckInBtnMouseClicked(evt);
            }
        });

        jLabel5.setText("Checked Out Equipment:");

        checkinCancelBtn.setText("Cancel");
        checkinCancelBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                checkinCancelBtnMouseClicked(evt);
            }
        });

        checkinSearchBtn.setText("Search");
        checkinSearchBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                checkinSearchBtnMouseClicked(evt);
            }
        });

        jLabel1.setText("Warehouse Location:");

        checkinLocationDropdown.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        checkinEquipList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(checkinEquipList);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(checkinLocationDropdown, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(checkinUserIDField, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(checkinSearchBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(checkinCancelBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(checkinCheckInBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(checkinUserIDField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(checkinSearchBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(checkinLocationDropdown, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(checkinCheckInBtn)
                    .addComponent(checkinCancelBtn))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void checkinSearchBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_checkinSearchBtnMouseClicked
        // TODO add your handling code here:
        // Search database for all of an employee's checked out equipment
        // Display them in checkinEquipDropdown
        // Format: Name - Descrip - Char - Num
        if (checkinUserIDField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please provide a User ID.");
        }
        else {
            try {
                con = db.OpenConnection();
                ArrayList<String> elements = new ArrayList<>();
                ArrayList<String> populateList = new ArrayList<>();
                String user = checkinUserIDField.getText();
                String itemToAdd = null;
                String id = null;
                String name = null;
                String equipChar = null;
                String equipNum = null;
                String descrip = null;
                
                // Pull info for checked out items
                sql = String.format("SELECT equipment_checkout.CheckoutID, equipment.EquipmentIDChar, equipment.EquipmentIDNum, "
                        + "equipment.EquipmentName, equipment.Description FROM equipment_checkout INNER JOIN "
                        + "equipment ON equipment_checkout.EquipmentIDChar=equipment.EquipmentIDChar AND "
                        + "equipment_checkout.EquipmentIDNum=equipment.EquipmentIDNum WHERE equipment_checkout.UserID=\'%s\' "
                        + "AND equipment_checkout.Status=\'Checked out\'", user);
                stmt = con.prepareStatement(sql);
                result = stmt.executeQuery();
                
                while (result.next()) {
                    Collections.addAll(elements, result.getString("CheckoutID"),result.getString("EquipmentName"), result.getString("Description") ,result.getString("EquipmentIDChar"), result.getString("EquipmentIDNum"));
                    id = result.getString("CheckoutID");
                    name = result.getString("EquipmentName");
                    descrip = result.getString("Description");
                    equipChar = result.getString("EquipmentIDChar");
                    equipNum = result.getString("EquipmentIDNum");
                    
                    populateList.add(id + " - " + name + " - " + descrip + " - " + equipChar + " - " + equipNum);
                    
                }

                
                DefaultListModel model = new DefaultListModel();
                model.addAll(populateList);
                checkinEquipList.setModel(model);
                
                
            } catch (SQLException ex) {
                Logger.getLogger(CheckOutScreen.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(CheckOutScreen.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }//GEN-LAST:event_checkinSearchBtnMouseClicked

    private void checkinCancelBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_checkinCancelBtnMouseClicked
        // TODO add your handling code here:
        this.setVisible(false);
        MainPage.editEmployee = false;
    }//GEN-LAST:event_checkinCancelBtnMouseClicked

    private void checkinCheckInBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_checkinCheckInBtnMouseClicked
        // TODO add your handling code here:
        // Runs sql statement that changes necessary fields in database
        // Should update the equipment_checkout table based on
        // criteria: Char, Num, UserID, Status(Checkedout) [add ReturnDate
        // and change status to Returned],
        // and change equipment table to reflect the availability of the
        // returned item. This should be a status change and a location change
        // location will be taken from the checkinLocationDropdown combobox
        
        if (checkinUserIDField.getText().isEmpty() || !checkinEquipList.hasFocus()) {
            JOptionPane.showMessageDialog(null, "Please fill out all fields.");
        }
        else {
            try {
                con = db.OpenConnection();
                ArrayList<String> elements = new ArrayList<String>();
                String charID = null;
                String numID = null;
                String name = null;
                String descrip = null;
                String userID = null;
                
                String pattern = "yyyy-MM-dd";
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
                
                String date = simpleDateFormat.format(new Date());
                System.out.println(date);
                
                sql = String.format("UPDATE equipment SET Status = \'Checked out\', Location = \'Out\' WHERE EquipmentIDChar = \'%s\' AND EquipmentIDNum = \'%s\'", charID, numID);
                stmt = con.prepareStatement(sql);

                stmt.executeUpdate();
                JOptionPane.showMessageDialog(null, "Successfully checked out equipment.");
                
                sql = String.format("INSERT INTO equipment_checkout (UserID, EquipmentIDChar, EquipmentIDNum, CheckoutDate, Status) VALUES (%s, \'%s\', \'%s\', \'%s\', \'Checked out\')",
                        userID, charID, numID, date);
                
                stmt = con.prepareStatement(sql);
                stmt.execute();
                System.out.println("Success");
                
                this.setVisible(false);
                
            } catch (SQLException ex) {
                Logger.getLogger(CheckOutScreen.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(CheckOutScreen.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
    }//GEN-LAST:event_checkinCheckInBtnMouseClicked

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
            java.util.logging.Logger.getLogger(CheckInScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CheckInScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CheckInScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CheckInScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CheckInScreen().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton checkinCancelBtn;
    private javax.swing.JButton checkinCheckInBtn;
    private javax.swing.JList<String> checkinEquipList;
    private javax.swing.JComboBox<String> checkinLocationDropdown;
    private javax.swing.JButton checkinSearchBtn;
    private javax.swing.JTextField checkinUserIDField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
