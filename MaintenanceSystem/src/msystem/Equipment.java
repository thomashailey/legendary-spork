package msystem;


/**
 *
 * @author thoma / vaughnr
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.swing.JOptionPane;

public class Equipment {
    
    // Global level Variables
    DBConnect db = new DBConnect();
    Connection con = null;
    String sql = null;
    PreparedStatement stmt;
    ResultSet result;
    String sql = null;
    
    public ArrayList<String> CheckIn() throws SQLException, ClassNotFoundException {
        // Access database to return equipment
             con = db.OpenConnection();
        ArrayList<String> elements = new ArrayList<>();
        String itemToAdd = null;
        try {
                 sql = "SELECT * FROM equipment";
            stmt = con.prepareStatement(sql);
            
            result = stmt.executeQuery();
            
            if (result != null) {
                System.out.println("Successfully Accessed Equipment DataBase");
            }
            while (result.next()) {
                itemToAdd = String.format("%s -- %s", result.getString("EquipmentName"), result.getString("Description"));
                if(!elements.contains(itemToAdd)){
                    elements.add(itemToAdd);
                }
            }
        }
        catch(Exception e) {
            System.out.println(e);
            System.out.println("Equipment.Checkin");
        }
        return elements;   
     }
    

    public ArrayList<String> CheckOut(String equipName, String equipDescrip) throws SQLException, ClassNotFoundException {
        // Access database to assign equipment and check out        
        con = db.OpenConnection();
        ArrayList<String> elements = new ArrayList<>();
        String itemToAdd = null;
        try {
            sql = "SELECT * FROM equipment WHERE Status = ? AND EquipmentName = ? AND Description = ? LIMIT 1;";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, "Available");
            stmt.setString(2, equipName);
            stmt.setString(3, equipDescrip);
            
            result = stmt.executeQuery();
            
            if (result.next()) {
                //Get the ID of the equipment
                int equipmentId = result.getInt("ID");
                
                //Change the status of the equipment to checked out
                String updateSql = "UPDATE equipment SET Status = ? WHERE ID = ?";
                PreparedStatement updateStmt = con.prepareStatement(updateSql);
                updateStmt.setString(1, "CheckedOut");
                updateStmt.setInt(2, equipmentId);
                updateStmt.executeUpdate();
                
                itemToAdd = equipName;
                
            }
        }finally{
            // Close resources
            if (stmt != null) {
                stmt.close();
            }
            if (con != null) {
                con.close();
            }
            
//            TODO: Need to finish checkout process
//            Use item fields to change equipment_checkout table to reflect 
//            state of checked equipment. Above code will use selected item
//            from list to find first available equipment in the database and 
//            allow user to check it out under their account
            
            
            if (result != null) {
                System.out.println("Successfully Accessed Equipment DataBase");
            }
            while (result.next()) {
                itemToAdd = String.format("%s -- %s", result.getString("EquipmentName"), result.getString("Description"));
                if(!elements.contains(itemToAdd)){
                    elements.add(itemToAdd);
                }
            }
        
        }
        elements.add(itemToAdd);
        return elements;
        
    }
    
    public void AdjustEquipment(){
        // 
    }

    public void ReportLoss(String equipIDChar, String equipIDNum) throws SQLException, ClassNotFoundException {
              // Report loss of equipment
        con = db.OpenConnection();
        try {
            // Update the status of the equipment to "Lost" in the database
            sql = "UPDATE equipment SET Location = \"Lost\" WHERE EquipmentIDChar = ? AND EquipmentIDNum = ?";
            System.out.println(sql);
            stmt = con.prepareStatement(sql);
            stmt.setString(1, equipIDChar);
            stmt.setString(2, equipIDNum);
            
            int rowsAffected = stmt.executeUpdate();
            
            if (rowsAffected > 0) {
                System.out.println("Equipment reported as lost successfully.");
            } else {
                System.out.println("Failed to report equipment as lost.");
            }
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
            System.out.println("Equipment.ReportLost");
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            if (con != null) {
                con.close();
            }
        }

    public void CheckOut() {
        // Access database to assign equipment and check out

    }
    public void ReportLoss() {
        // Report loss of equipment
    }
    
    public void CheckStatus() {
        // Check status of equipment by 
    }
    
    public ArrayList SearchInventory(String item) throws SQLException, ClassNotFoundException {
        // Search inventory for specific item
        /*  Set connection to DBConnect OpenConnection() method,
            Create ArrayList to store DB elements
        */
        con = db.OpenConnection();
        ArrayList<String> elements = new ArrayList<>();
        String itemToAdd = null;
        try {
            sql = String.format("SELECT * FROM inventory WHERE ItemName LIKE '%%%s%%' OR Description LIKE '%%%s%%'", item, item);
            stmt = con.prepareStatement(sql);
            //stmt.setString(1, item);
            
            result = stmt.executeQuery();

            if (result != null) {
                System.out.println("Successfully Accessed Inventory DataBase");
            }
            while (result.next()) {
                itemToAdd = String.format("%s -- %s", result.getString("ItemName"), result.getString("Description"));
                if(!elements.contains(itemToAdd)){
                    elements.add(itemToAdd);
                }
            }
        }
        catch(Exception e) {
            System.out.println(e);
            System.out.println("Equipment.SearchInventory");

        }
        return elements;
    }
    public ArrayList searchInventoryForEdit(String selectedItemName, String selectedDescription){
        //use this in order to pull the inventory item from both locations
        //return is passed into EditInventory to show what the 2 locations have
        
        
        sql = String.format("SELECT * FROM inventory WHERE ItemName = '%s' AND Description = '%s'",
                selectedItemName, selectedDescription);
        ArrayList<String> elements = new ArrayList<>();
        
        try{
            con = db.OpenConnection();
            stmt = con.prepareStatement(sql);
            result = stmt.executeQuery();
            if(result != null){
                System.out.println("Successfully accessed database to pull inventory from user selection");
                while(result.next()){
                    Collections.addAll(elements, result.getString("ItemIDChar"), result.getString("ItemIDNum"),
                            result.getString("ItemName"), result.getString("Description"),
                            result.getString("Quantity"), result.getString("Location"));
                    
                }
            }
        }
        catch(Exception e){
            System.out.println(e);
            System.out.println("Equipment.searchInventoryForEdit");
        }
        return elements;
        
    }
    
    public void CheckStatus() {
        // Check status of equipment by 
    }
    
    public ArrayList SearchInventory(String item) throws SQLException, ClassNotFoundException {
        // Search inventory for specific item
        /*  Set connection to DBConnect OpenConnection() method,
            Create ArrayList to store DB elements
        */
        con = db.OpenConnection();
        ArrayList<String> elements = new ArrayList<>();
        try {
            sql = "SELECT * FROM inventory WHERE ItemName = ?";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, item);
            
            result = stmt.executeQuery();

            if (result != null) {
                System.out.println("Successfully Accessed Inventory DataBase");
            }
            while (result.next()) {
                elements.add(result.getString("ItemName"));
            }
        }
        catch(Exception e) {
            
        }
        return elements;
    }
    
    public void AdjustInventory(String itemName, int quantity, boolean add) throws SQLException, ClassNotFoundException {
        // Added code to add or remove inventory from an employee's account
        con = db.OpenConnection();
        try {
            String sqlSelect = "SELECT * FROM inventory WHERE ItemName = ?";
            stmt = con.prepareStatement(sqlSelect);
            stmt.setString(1, itemName);
    }
    
    public ArrayList searchInventoryFromEdit(String selectedItemID, String selectedItemNum){
        //use this in order to pull the inventory item from both locations
        //return is passed into EditInventory to show what the 2 locations have
        
        
        sql = String.format("SELECT * FROM inventory WHERE ItemIdChar = '%s' AND ItemIDNum = '%s'",
                selectedItemID, selectedItemNum);
        String requestedItemName = null;
        String requestedItemDesc = null;
        try{
            con = db.OpenConnection();
            stmt = con.prepareStatement(sql);
            result = stmt.executeQuery();
            
            if (result.next()) {
                int currentQuantity = result.getInt("Quantity");
                int newQuantity = add ? currentQuantity + quantity : currentQuantity - quantity;
                if (newQuantity >= 0) {
                    String sqlUpdate = "UPDATE inventory SET Quantity = ? WHERE ItemName = ?";
                    PreparedStatement updateStmt = con.prepareStatement(sqlUpdate);
                    updateStmt.setInt(1, newQuantity);
                    updateStmt.setString(2, itemName);
                    updateStmt.executeUpdate();
                    System.out.println("Inventory adjusted successfully.");
            } else {
                    System.out.println("Error: Not enough inventory to remove");
            }
        } else {
            System.out.println("Error: Item not found in inventory.");
        }
    } catch (SQLException e) {
        System.out.println("SQL Error: " + e.getMessage());
            } finally {
                if (stmt != null){
                    stmt.close();
                }
                if (con != null) {
                    con.close();
                }
           
        }
    }
    
    public ArrayList ViewInventory() throws SQLException, ClassNotFoundException {
        /*  Set connection to DBConnect OpenConnection() method,
            Create ArrayList to store DB elements
        */
        //need to find a way to ensure that dupe items are not added to the list, then pull both when the request is bigger than the primary
        con = db.OpenConnection();
        ArrayList<String> elements = new ArrayList<>();
        String itemToAdd = null;
        try {
            sql = "SELECT * FROM inventory";
            stmt = con.prepareStatement(sql);
            
            result = stmt.executeQuery();
            
            if (result != null) {
                System.out.println("Successfully Accessed Inventory DataBase");
            }
            while (result.next()) {
                itemToAdd = String.format("%s -- %s", result.getString("ItemName"), result.getString("Description"));
                if(!elements.contains(itemToAdd)){
                    elements.add(itemToAdd);
                }
            }
        }
        catch(Exception e) {

            System.out.println(e);
            System.out.println("Equipment.ViewInventory");
        }
        return elements;
    }
    
    public ArrayList ViewInventoryRequests(boolean unfulfilledOnly){

            
        ArrayList<String> elements = new ArrayList<>();
        if(unfulfilledOnly){
            sql = "SELECT * FROM inventory_request WHERE Fulfilled = 0";
        }
        else {
            sql = "SELECT * FROM inventory_request ORDER BY InvRequestID DESC LIMIT 20";
        }
        try {
            con = db.OpenConnection();
            stmt = con.prepareStatement(sql);
            result = stmt.executeQuery();
            if (result != null) {
                System.out.println("Successfully Accessed Inventory Request DataBase");
            }
            while (result.next()) {
                if(result.getString("Fulfilled").equals("1")){
                    elements.add(String.format("%s -- %s", result.getString("InvRequestID"), "Fulfilled"));
                }
                else{
                    elements.add(String.format("%s -- %s", result.getString("InvRequestID"), "Needed"));
                }
            }
        }
        catch(Exception e) {
            System.out.println(e);
            System.out.println("Equipment.ViewInventoryRequests");
        }
        return elements;
    }


    public ArrayList ViewEquipment(String item) throws SQLException, ClassNotFoundException {

        // Search inventory for specific item
        /*  Set connection to DBConnect OpenConnection() method,
            Create ArrayList to store DB elements
        */
        con = db.OpenConnection();
        ArrayList<String> elements = new ArrayList<>();
        try {
            sql = "SELECT * FROM equipment WHERE ItemName = ?";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, item);
            
            result = stmt.executeQuery();

            if (result != null) {
                System.out.println("Successfully Accessed Equipment DataBase");
            }
            while (result.next()) {
                String itemName = result.getString("ItemName");
                String itemDescription = result.getString("Description");
                String equipmentInfo = String.format("%s -- %s", itemName, itemDescription);
                elements.add(equipmentInfo);
            }
        }
        catch(Exception e) {
            System.out.println("SQL Error: " + e.getMessage());
        }finally {
            if (stmt != null) {
                stmt.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return elements;
    }    
    

}

