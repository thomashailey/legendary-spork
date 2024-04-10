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
        catch(Exception e) {
            System.out.println(e);
            System.out.println("Equipment.Checkout");
        }
        return elements;
        
    }
    
    public void AdjustEquipment(){
        // 
    }

    public void ReportLoss() {
        // Report loss of equipment
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
            System.out.println("Equipment.ViewEquipment");
        }
        return elements;
        
    }
    
    public ArrayList ViewEquipmentDetails(String equipName, String equipDescrip) throws SQLException, ClassNotFoundException {
        /*  Set connection to DBConnect OpenConnection() method,
            Create ArrayList to store DB elements
        */
        //need to find a way to ensure that dupe items are not added to the list, then pull both when the request is bigger than the primary
        con = db.OpenConnection();
        ArrayList<String> elements = new ArrayList<>();
        String itemToAdd = null;
        try {
            String sql = "SELECT * FROM equipment";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, "Available");
            stmt.setString(2, equipName);
            stmt.setString(3, equipDescrip);
            
            result = stmt.executeQuery();
            
            if (result != null) {
                System.out.println("Successfully Accessed Equipment DataBase -- Details");
            }
            while (result.next()) {
                itemToAdd = String.format("%s -- %s", result.getString("EquipmentName"), result.getString("Description"));
                if(!elements.contains(itemToAdd)){
                    elements.add(itemToAdd);
                    elements.add(result.getInt("EquipmentIDChar"), result.getString("EquipmentIDNum"));
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
    
    public void AdjustInventory() {
        // Add code to add or remove inventory from an employee's account
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
                elements.add(result.getString("ItemName"));
            }
        }
        catch(Exception e) {
            
        }
        return elements;
    }    
    

}

