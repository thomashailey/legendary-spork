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

public class Equipment {
    
    // Global level Variables
    DBConnect db = new DBConnect();
    Connection con = null;
    PreparedStatement stmt;
    ResultSet result;
    
    public ArrayList<String> CheckIn() throws SQLException, ClassNotFoundException {
        // Access database to return equipment
             con = db.OpenConnection();
        ArrayList<String> elements = new ArrayList<>();
        String itemToAdd = null;
        try {
                 String sql = "SELECT * FROM equipment";
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
            String sql = "SELECT * FROM equipment WHERE Status = ? AND EquipmentName = ? AND Description = ? LIMIT 1;";
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

    public void ReportLoss(String equipName, String equipDescrip) throws SQLException, ClassNotFoundException {
              // Report loss of equipment
        con = db.OpenConnection();
        try {
            // Update the status of the equipment to "Lost" in the database
            String sql = "UPDATE equipment SET Status = ? WHERE EquipmentName = ? AND Description = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, "Lost");
            stmt.setString(2, equipName);
            stmt.setString(3, equipDescrip);
            
            int rowsAffected = stmt.executeUpdate();
            
            if (rowsAffected > 0) {
                System.out.println("Equipment reported as lost successfully.");
            } else {
                System.out.println("Failed to report equipment as lost.");
            }
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            if (con != null) {
                con.close();
            }
        }
  
    }
    
    /**
     *
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public ArrayList ViewEquipment() throws SQLException, ClassNotFoundException {
        /*  Set connection to DBConnect OpenConnection() method,
            Create ArrayList to store DB elements
        */
        //need to find a way to ensure that dupe items are not added to the list, then pull both when the request is bigger than the primary
        con = db.OpenConnection();
        ArrayList<String> elements = new ArrayList<>();
        HashSet<String> uniqueItems = new HashSet<>(); // HashSet to store unique items 
        String itemToAdd = null;
        try {
            String sql = "SELECT * FROM equipment";
            stmt = con.prepareStatement(sql);
            
            result = stmt.executeQuery();
            
            if (result != null) {
                System.out.println("Successfully Accessed Equipment DataBase");
            }
            while (result.next()) {
                String itemName = result.getString("EquipmentName");
                String itemDescription = result.getString("Description");
                itemToAdd = String.format("%s -- %s", itemName, itemDescription);
                //only need one of these to add a single item into the list, don't want duplicates since we will be pulling singles using sql
//                if(!uniqueItems.contains(itemToAdd)) {
//                    uniqueItems.add(itemToAdd);
//                    elements.add(itemToAdd);
//                } else{
//                    // If the item is a duplicate, add it again to ensure both primay
//                    // and duplicate items ae retieved
//                    elements.add(itemToAdd);
//                }
                if(!elements.contains(itemToAdd)){
                    elements.add(itemToAdd);
                }
            }
        }
        catch(Exception e) {
            System.out.println(e);
            System.out.println("Equipment.ViewEquipment");
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
    
    public ArrayList ViewEquipmentDetails(String equipName, String equipDescrip) throws SQLException, ClassNotFoundException {
        /*  Set connection to DBConnect OpenConnection() method,
            Create ArrayList to store DB elements
        */
        
        con = db.OpenConnection();
        ArrayList<String> elements = new ArrayList<>();
        String itemToAdd = null;
        String sql;
        String warehouseOne = "Primary";
        String warehouseTwo = "Secondary";
        int priCounter = 0;
        int secCounter = 0;
        
        
        try {
            // Search for entry based on name, description, and location.
            ResultSet primary, secondary;
            
            
            // This one searches for primary location
            sql = String.format("SELECT * FROM equipment WHERE EquipmentName = \'%s\' AND Description = \'%s\' AND Location = \'%s\'",
                    equipName, equipDescrip, warehouseOne);
            stmt = con.prepareStatement(sql);
            
            primary = stmt.executeQuery();
            
            // Increment priCounter in order to determine the number of entries matching criteria
            while (primary.next()) {
                priCounter += 1;
            }
            
            
            // This one searches for entries at the secondary location
            sql = String.format("SELECT * FROM equipment WHERE EquipmentName = \'%s\' AND Description = \'%s\' AND Location = \'%s\'",
                    equipName, equipDescrip, warehouseTwo);
            stmt = con.prepareStatement(sql);
            
            secondary = stmt.executeQuery();
            
            // Increment secCounter in order to determine the number of entries matching criteria
            while (secondary.next()) {
                secCounter +=1;
            }
            
            // Search for item using just name and description to narrow down 
            // results again, now that the other queries have determined the number of 
            // items stored at each different location.
            sql = String.format("SELECT * FROM equipment WHERE EquipmentName = \'%s\' AND Description = \'%s\'", equipName, equipDescrip);
            stmt = con.prepareStatement(sql);
            
            result = stmt.executeQuery();
            
            while (result.next()) {
                itemToAdd = String.format("%s: %s \n\n --- Available --- \n Primary:\t%s \n Secondary:\t%s",
                        result.getString("EquipmentName"), result.getString("Description"), 
                        priCounter, secCounter);
                if(!elements.contains(itemToAdd)){
                    elements.add(itemToAdd);
                }
            }
        }
        catch(Exception e) {
            System.out.println(e);
            System.out.println("Equipment.ViewEquipment");
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
            String sql = "SELECT * FROM inventory WHERE ItemName = ?";
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
        con = db.OpenConnection();
        ArrayList<String> elements = new ArrayList<>();
        try {
            String sql = "SELECT * FROM inventory";
            stmt = con.prepareStatement(sql);
            
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
    

 public ArrayList ViewEquipment(String item) throws SQLException, ClassNotFoundException {
        // Search inventory for specific item
        /*  Set connection to DBConnect OpenConnection() method,
            Create ArrayList to store DB elements
        */
        con = db.OpenConnection();
        ArrayList<String> elements = new ArrayList<>();
        try {
            String sql = "SELECT * FROM equipment WHERE ItemName = ?";
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

