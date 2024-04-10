/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package msystem;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author never
 */
public class EditInventory extends javax.swing.JFrame {
    /**
     * Creates new form EditInventory
     */
    public EditInventory(boolean add, boolean newItem, ArrayList values) {
        initComponents();
        editInventoryNewRequestRadio.setSelected(newItem);
        if(add && !newItem){
            //with adding into inventory we do not need to set against new or current inventory, it will scan current inventory first, then requests to make sure someone isnt waiting for it
            editInventoryNewRequestRadio.setVisible(false);
            editInventoryCurrentInventoryRadio.setVisible(false);
        }
        if(!add){
            
            editInventoryNameTxt.setText(values.get(2).toString());
            editInventoryDescriptionTxt.setText(values.get(3).toString());
            
            if(values.get(11).toString().equals("Primary")){
                //pulling and aadjusting the pull values to match where they should be displayed
                editInventoryPromaryIDChar.setText(values.get(6).toString());
                editInventoryPromaryIDNum.setText(values.get(7).toString());
                editInventoryPromaryQAvailable.setText(values.get(10).toString());
                editInventoryPromaryQRequested.setText("0");
                
                editInventorySecondaryIDChar.setText(values.get(0).toString());
                editInventorySecondaryIDNum.setText(values.get(1).toString());
                editInventorySeccondaryQAvailable.setText(values.get(4).toString());
                editInventorySecondaryQRequested.setText("0");
            }
            else{
                editInventorySecondaryIDChar.setText(values.get(6).toString());
                editInventorySecondaryIDNum.setText(values.get(7).toString());
                editInventorySeccondaryQAvailable.setText(values.get(10).toString());
                editInventorySecondaryQRequested.setText("0");
                
                editInventoryPromaryIDChar.setText(values.get(0).toString());
                editInventoryPromaryIDNum.setText(values.get(1).toString());
                editInventoryPromaryQAvailable.setText(values.get(4).toString());
                editInventoryPromaryQRequested.setText("0");
            }
            
            
            //Test statement below, uncomment to check what values are being passed in
            /*
            for(int x=0;x<values.size();x++){
                System.out.println(values.get(x));
                
            }*/
        }
        //editInventoryNameTxt.setEditable(false);
        //editInventoryDescriptionTxt.setEditable(false);
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        editInventoryNewRequestRadio = new javax.swing.JRadioButton();
        editInventoryCurrentInventoryRadio = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        editInventoryConfirmBtn = new javax.swing.JButton();
        editInventoryCancelBtn = new javax.swing.JButton();
        editInventoryNameTxt = new javax.swing.JTextField();
        editInventoryDescriptionTxt = new javax.swing.JTextField();
        editInventoryPromaryIDChar = new javax.swing.JTextField();
        editInventorySecondaryIDChar = new javax.swing.JTextField();
        editInventoryPromaryIDNum = new javax.swing.JTextField();
        editInventorySecondaryIDNum = new javax.swing.JTextField();
        editInventoryPromaryQAvailable = new javax.swing.JTextField();
        editInventoryPromaryQRequested = new javax.swing.JTextField();
        editInventorySeccondaryQAvailable = new javax.swing.JTextField();
        editInventorySecondaryQRequested = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        buttonGroup1.add(editInventoryNewRequestRadio);
        editInventoryNewRequestRadio.setText("New Request");

        buttonGroup1.add(editInventoryCurrentInventoryRadio);
        editInventoryCurrentInventoryRadio.setSelected(true);
        editInventoryCurrentInventoryRadio.setText("Current Inventory");

        jLabel1.setText("Inventory Name");

        jLabel2.setText("Inventory Description");

        jLabel3.setText("Primary");

        jLabel4.setText("Secondary");

        jLabel5.setText("Inventory ID");

        jLabel6.setText("Available");

        jLabel7.setText("Request");

        editInventoryConfirmBtn.setText("Confirm");
        editInventoryConfirmBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                editInventoryConfirmBtnMouseClicked(evt);
            }
        });

        editInventoryCancelBtn.setText("Cancel");
        editInventoryCancelBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                editInventoryCancelBtnMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(editInventoryPromaryIDChar)
                                    .addComponent(editInventorySecondaryIDChar))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(editInventoryPromaryIDNum)
                                    .addComponent(editInventorySecondaryIDNum)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(93, 93, 93)
                                .addComponent(jLabel5)))
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(editInventoryPromaryQAvailable, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(editInventorySeccondaryQAvailable, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(editInventoryPromaryQRequested)
                            .addComponent(editInventorySecondaryQRequested))
                        .addGap(13, 13, 13))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(editInventoryCurrentInventoryRadio)
                                .addGap(74, 74, 74)
                                .addComponent(editInventoryConfirmBtn)
                                .addGap(45, 45, 45)
                                .addComponent(editInventoryCancelBtn))
                            .addComponent(editInventoryNewRequestRadio, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(editInventoryNameTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(editInventoryDescriptionTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(editInventoryNameTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(editInventoryDescriptionTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(editInventoryPromaryIDChar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(editInventoryPromaryIDNum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(editInventoryPromaryQAvailable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(editInventoryPromaryQRequested, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(editInventorySecondaryIDChar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(editInventorySecondaryIDNum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(editInventorySeccondaryQAvailable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(editInventorySecondaryQRequested, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addComponent(editInventoryNewRequestRadio)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(editInventoryCurrentInventoryRadio)
                    .addComponent(editInventoryConfirmBtn)
                    .addComponent(editInventoryCancelBtn))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void editInventoryConfirmBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editInventoryConfirmBtnMouseClicked
        // TODO add your handling code here:
        this.setVisible(false);
    }//GEN-LAST:event_editInventoryConfirmBtnMouseClicked

    private void editInventoryCancelBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editInventoryCancelBtnMouseClicked
        // TODO add your handling code here:
        this.setVisible(false);
    }//GEN-LAST:event_editInventoryCancelBtnMouseClicked

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
            java.util.logging.Logger.getLogger(EditInventory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EditInventory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EditInventory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EditInventory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EditInventory(false, false, MainPage.nullArray).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton editInventoryCancelBtn;
    private javax.swing.JButton editInventoryConfirmBtn;
    private javax.swing.JRadioButton editInventoryCurrentInventoryRadio;
    private javax.swing.JTextField editInventoryDescriptionTxt;
    private javax.swing.JTextField editInventoryNameTxt;
    private javax.swing.JRadioButton editInventoryNewRequestRadio;
    private javax.swing.JTextField editInventoryPromaryIDChar;
    private javax.swing.JTextField editInventoryPromaryIDNum;
    private javax.swing.JTextField editInventoryPromaryQAvailable;
    private javax.swing.JTextField editInventoryPromaryQRequested;
    private javax.swing.JTextField editInventorySeccondaryQAvailable;
    private javax.swing.JTextField editInventorySecondaryIDChar;
    private javax.swing.JTextField editInventorySecondaryIDNum;
    private javax.swing.JTextField editInventorySecondaryQRequested;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    // End of variables declaration//GEN-END:variables
}
