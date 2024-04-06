/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package msystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author thoma
 */
public class Equipment {
    
    DBConnect db = new DBConnect();
    Connection con = null;
    PreparedStatement stmt;
    ResultSet result;
    public void CheckIn() {
        
    }
    
    public void CheckOut() {
        
    }
    
    public void ReportLoss() {
        
    }
    
<<<<<<< Updated upstream
    public void CheckStatus() {
        
    }
    
    public void SearchInventory() {
        
=======
    public ArrayList<String> CheckStatus() throws SQLException, ClassNotFoundException{
        // Check status of equipment by 
        con = db.OpenConnection();
        ArrayList<String> elements = new ArrayList<>();
        try {
            String sql = "SELECT * FROM equipment WHERE EquipmentIDNum = ?";
            stmt = con.prepareStatement(sql);
            
            result = stmt.executeQuery();

            if (result != null) {
                System.out.println("Successfully Accessed DataBase");
            }
            while (result.next()) {
                elements.add(result.getString("Equipment Status"));
            }
        }
        catch(Exception e) {
            
        }
        return elements;
    }
    
    public ArrayList<String> SearchInventory(String userInput) throws SQLException, ClassNotFoundException{
        // Search inventory for specific item
        MainPage mp = new MainPage();
        ArrayList<String> elements = new ArrayList<>();
        try {
            String sql = "SELECT * FROM equipment WHERE EquipmentName = ? ";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, userInput);
            
            result = stmt.executeQuery();

            if (result != null) {
                System.out.println("Successfully Accessed DataBase");
            }
            while (result.next()) {
                elements.add(result.getString("EquipmentInventory"));
            }
        }
        catch(Exception e) {
            
        }
        return elements;
>>>>>>> Stashed changes
    }
    
    public void AdjustInventory() {
        
    }
    
    public void ViewInventory() {
        
    }
    
}
