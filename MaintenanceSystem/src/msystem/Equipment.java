package msystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author thoma / vaughnr
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Equipment {
    
    // Global level Variables
    DBConnect db = new DBConnect();
    Connection con = null;
    String sql = null;
    PreparedStatement stmt;
    ResultSet result;
    
    public void CheckIn() {
        // Access database to return equipment
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
            System.out.println("Equipment.ViewInventory");
            
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
    
    public void addToInventory() {
        // Add code to add or remove inventory from an employee's account
    }
    
    public void requestNewInventory(){
        // adding a request directly to the inventory request as there is no current inventory for this item, will pass values the user inputs
    }
    
    public void requestCurrentInventory(){
        // adding a request against current inventory, will pull values from inventory table
        
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
                elements.add(result.getString("ItemName"));
            }
        }
        catch(Exception e) {
            
        }
        return elements;
    }    
    

}

