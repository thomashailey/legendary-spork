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
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import msystem.Employee;

/**
 *
 * @author mista
 */
public class NewLog extends javax.swing.JFrame {

    String pattern = "yyyy-MM-dd";
    SimpleDateFormat simpleDate = new SimpleDateFormat(pattern);
    String currentDate = simpleDate.format(new Date());
    String sql = null;
    DBConnect db = new DBConnect();
    Connection con = null;
    PreparedStatement stmt;
    ResultSet result;
    int numberOfCharactersInBox = 0;
    Employee emp = new Employee();

    /**
     * Creates new form NewLog
     */
    public NewLog() {
        initComponents();

        System.out.println(currentDate);

        System.out.println(MainPage.NewLog);
        if (MainPage.NewLog) {
            jtfUserId.setText(MainPage.selectingThisUserID);
            jtfActivityDate.setText(MainPage.selectingThisUsername);
            jtfDescription.setText(MainPage.selectingThisUserID);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtUserIDLog = new javax.swing.JLabel();
        jtfUserId = new javax.swing.JTextField();
        txtActivityDateLog = new javax.swing.JLabel();
        jtfActivityDate = new javax.swing.JTextField();
        txtDescriptonLog = new javax.swing.JLabel();
        btnConfirmLog = new javax.swing.JButton();
        btnCancelLog = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtfDescription = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        txtUserIDLog.setText("UserID");

        txtActivityDateLog.setText("ActivityDate");

        txtDescriptonLog.setText("Description");

        btnConfirmLog.setText("Confirm");
        btnConfirmLog.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnConfirmLogMouseClicked(evt);
            }
        });

        btnCancelLog.setText("Cancel");
        btnCancelLog.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCancelLogMouseClicked(evt);
            }
        });

        jtfDescription.setColumns(20);
        jtfDescription.setRows(5);
        jScrollPane1.setViewportView(jtfDescription);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtUserIDLog)
                            .addComponent(txtActivityDateLog)
                            .addComponent(txtDescriptonLog))
                        .addContainerGap(330, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jtfUserId, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jtfActivityDate, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnConfirmLog)
                            .addComponent(btnCancelLog))
                        .addGap(45, 45, 45))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(txtUserIDLog)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtfUserId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnConfirmLog))
                .addGap(18, 18, 18)
                .addComponent(txtActivityDateLog)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtfActivityDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelLog))
                .addGap(18, 18, 18)
                .addComponent(txtDescriptonLog)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(46, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnConfirmLogMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnConfirmLogMouseClicked
        // TODO add your handling code here:
        
        
        /**Other Test
        sql = String.format("INSERT INTO maintenance_activities (UserID, ActivityDate, Description) VALUES (%s, '%s', '%s')", 
                jtfUserId.getText(), jtfActivityDate.getText(), jtfDescription.getText());
        //JOptionPane.showMessageDialog(null, sql);
        try {
            con = db.OpenConnection();
            stmt = con.prepareStatement(sql);
            stmt.execute();
            
            if (result != null) {
                System.out.println("Successfully Accessed DataBase to add log");
            }
            
            con.close();
            System.out.println("Database closed");
        }
        catch(Exception e) {
            System.out.println(e);
            System.out.println("Addlog.ConfrimLogMouseClicked");
        }
        MainPage.editEmployee = false;
        this.setVisible(false);
        * 
        Other Test*/
        
        
        
        //Main Test
        ArrayList<String> list = new ArrayList<String>();
        try {
            // Pull all maintenance activities
            list = emp.PullAllMaintenanceActivities();

            // Construct the SQL INSERT statement
            String sql = String.format("INSERT INTO maintenance_activities (UserID, ActivityDate, Description) VALUES (?, ?, ?)");

            // Establish connection and execute the INSERT statement
            try (Connection con = db.OpenConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {

                // Set values for the parameters in the prepared statement
                stmt.setString(1, jtfUserId.getText());
                stmt.setString(2, jtfActivityDate.getText());
                stmt.setString(3, jtfDescription.getText());

                // Execute the INSERT statement
                stmt.executeUpdate();

                // Log success message
                System.out.println(String.format("Successfully inserted log for user %s", jtfUserId.getText()));
            }
        } catch (SQLException ex) {
            Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
        }
         
        //Main Test*/

        /**
         * Test 1 ArrayList<String> list = new ArrayList<String>(); try { int
         * userInput = 0; // Pull all maintenance activities list =
         * emp.ConfirmNewLog(userInput);
         *
         * // Construct the SQL INSERT statement String sql =
         * String.format("INSERT INTO maintenance_activities (UserID,
         * ActivityDate, Description) VALUES (?, ?, ?)");
         *
         * // Establish connection and execute the INSERT statement try
         * (Connection con = db.OpenConnection(); PreparedStatement stmt =
         * con.prepareStatement(sql)) {
         *
         * // Set values for the parameters in the prepared statement
         * stmt.setString(1, jtfUserId.getText()); stmt.setString(2,
         * jtfActivityDate.getText()); stmt.setString(3,
         * jtfDescription.getText());
         *
         * // Execute the INSERT statement stmt.executeUpdate();
         *
         * // Log success message System.out.println(String.format("Successfully
         * inserted log for user %s", jtfUserId.getText())); } } catch
         * (SQLException ex) {
         * Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null,
         * ex); } catch (ClassNotFoundException ex) {
         * Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null,
         * ex); }
         *
         *
         * Test 1
         */
        /**
         * ?? prob delete sql = String.format("INSERT INTO
         * maintenance_activities(UserID, ActivityDate, Description) VALUES(%s,
         * '%s', '%s')", jtfUserId.getText(), jtfActivityDate.getText(),
         * jtfDescription.getText()); //JOptionPane.showMessageDialog(null,
         * sql); try { con = db.OpenConnection(); stmt =
         * con.prepareStatement(sql); stmt.execute();
         *
         * if (result != null) { System.out.println("Successfully Accessed
         * DataBase to add new log"); }
         *
         * con.close(); System.out.println("Database closed"); } catch(Exception
         * e) { System.out.println(e);
         * System.out.println("AddLog.btnConfirmLogActionPerformed"); }
         * MainPage.editEmployee = false; this.setVisible(false);
         *
         *
         */
    }//GEN-LAST:event_btnConfirmLogMouseClicked

    private void btnCancelLogMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelLogMouseClicked
        // TODO add your handling code here:
        this.setVisible(false);
        MainPage.editEmployee = false;
    }//GEN-LAST:event_btnCancelLogMouseClicked

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
            java.util.logging.Logger.getLogger(NewLog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NewLog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NewLog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NewLog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NewLog().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelLog;
    private javax.swing.JButton btnConfirmLog;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jtfActivityDate;
    private javax.swing.JTextArea jtfDescription;
    private javax.swing.JTextField jtfUserId;
    private javax.swing.JLabel txtActivityDateLog;
    private javax.swing.JLabel txtDescriptonLog;
    private javax.swing.JLabel txtUserIDLog;
    // End of variables declaration//GEN-END:variables
}
