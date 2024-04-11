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
            if(result != null){
                System.out.println("Successfully accessed database to pull inventory from user entry");
                while(result.next()){
                    requestedItemName = result.getString("ItemName");
                    requestedItemDesc = result.getString("Description");            
                }
            }
        }
        catch(Exception e){
            System.out.println(e);
            System.out.println("Equipment.searchInventoryFromEdit");
        }
        ArrayList<String> list = new ArrayList<>();
        list = searchInventoryForEdit(requestedItemName, requestedItemDesc);
        System.out.println(list);
        return list;
    }
    
    public void addToInventory(ArrayList inventoryToAdd) {
        // Add code to add inventory to employee's account
        //expecting 3 items here, ItemIDChar, ItemIDNum, and toAddAmt
        //yeah strike that as a just in case, full item line expected
        
        //add search into inventory request where unfulfilled, then popup a notification to notify user requested
        //if found and the amount added is more than the request, mark fulfilled
        //if found and the amount added is less, update that line to fulfilled with the amount added
        //and the remainder add to a new line
        sql = String.format("UPDATE inventory SET Quantity = (Quantity+%s) WHERE ItemIDChar = \"%s\" AND ItemIDNum = %s",
                inventoryToAdd.get(4) ,inventoryToAdd.get(0), inventoryToAdd.get(1));
        System.out.println(sql);
        try{
            con = db.OpenConnection();
            stmt = con.prepareStatement(sql);
            stmt.execute();
            
            System.out.println("Added to inventory");
            con.close();
        }
        catch(Exception e){
            System.out.println(e);
            System.out.println("Equipment.addToInventory");
        }
    }
    
    public void requestNewInventory(ArrayList requestedNewInv, int requestedAmt){
        // adding a request directly to the inventory request as there is no current inventory for this item, will pass values the user inputs
        //0=IDCHAR, 1=IDNUM, 2=ITEMNAME, 3=ITEMDESC, 4=AMTinINVENTORY, 5=REQUESTamt, 6=LOCATION
        sql = String.format("INSERT INTO inventory_request(ItemIDChar, ItemIDNum, UserIDRequested,"
                + " ItemName, Description, Quantity, Destination, Fulfilled) VALUES(\"%s\", %s, %s, \"%s\", \"%s\", %s, \"%s\", %s)",
                    requestedNewInv.get(0), requestedNewInv.get(1), 
                    123, requestedNewInv.get(2), 
                    requestedNewInv.get(3), requestedAmt, 
                    requestedNewInv.get(6), 0); // 0 here means the request is unfulfilled(false)
        try{
            con = db.OpenConnection();
            stmt = con.prepareStatement(sql);
            stmt.execute();
       

            System.out.println("Added new unfulfilled inventory request entry");
            con.close();
        }
        catch(Exception e){
            System.out.println(e);
            System.out.println("Equipment.requestNewInventory");
        }
    }
    
    public void requestCurrentInventory(ArrayList requestedCurrentInventory){
        String sql2 = null;
        // adding a request against current inventory, will pull values from inventory table
        //requested vs number in inventory
        //0=IDCHAR, 1=IDNUM, 2=ITEMNAME, 3=ITEMDESC, 4=REQUESTamt, 5=AMTinINVENTORY, 6=LOCATION
        if(Integer.parseInt(requestedCurrentInventory.get(4).toString()) > Integer.parseInt(requestedCurrentInventory.get(5).toString())){
            //requested amount larger than the amount currently in inventory, adding an unfulfilled request to inventory_requests
            int remainingInventoryRequired = Integer.parseInt(requestedCurrentInventory.get(4).toString()) - Integer.parseInt(requestedCurrentInventory.get(5).toString());
            System.out.println(remainingInventoryRequired);
            
            sql = String.format("UPDATE inventory SET Quantity = 0 WHERE ItemIDChar = \"%s\" and ItemIDNum = %s", 
                     requestedCurrentInventory.get(0), requestedCurrentInventory.get(1));
            sql2 = String.format("INSERT INTO inventory_request(ItemIDChar, ItemIDNum, UserIDRequested,"
                    + " ItemName, Description, Quantity, Destination, Fulfilled) VALUES(\"%s\", %s, %s, \"%s\", \"%s\", %s, \"%s\", %s)",
                    requestedCurrentInventory.get(0), requestedCurrentInventory.get(1), 
                    123, requestedCurrentInventory.get(2), 
                    requestedCurrentInventory.get(3), requestedCurrentInventory.get(4), 
                    requestedCurrentInventory.get(6), 1); // 1 here means the request has been fulfilled(true)
            requestNewInventory(requestedCurrentInventory, remainingInventoryRequired);
            System.out.print("current request");
            System.out.println(sql);
            System.out.println(sql2);
        }
        else{
            //requested amt equal to or lesser than the amt currently in inventory, adding a fulfilled report to inventory_requests
            //0=IDCHAR, 1=IDNUM, 2=ITEMNAME, 3=ITEMDESC, 4=REQUESTamt, 5=AMTinINVENTORY, 6=LOCATION
            sql = String.format("UPDATE inventory SET Quantity = (Quantity-%s) WHERE ItemIDChar = \"%s\" and ItemIDNum = %s", 
                    requestedCurrentInventory.get(4), requestedCurrentInventory.get(0), requestedCurrentInventory.get(1));
            sql2 = String.format("INSERT INTO inventory_request(ItemIDChar, ItemIDNum, UserIDRequested, "
                    + "ItemName, Description, Quantity, Destination, Fulfilled) VALUES(\"%s\", %s, %s, \"%s\", \"%s\", %s, \"%s\", %s)",
                    requestedCurrentInventory.get(0), requestedCurrentInventory.get(1), 
                    123, requestedCurrentInventory.get(2), 
                    requestedCurrentInventory.get(3), requestedCurrentInventory.get(4), 
                    requestedCurrentInventory.get(6), 1); // 1 here means the request has been fulfilled
            System.out.print("current request");
            //System.out.println(sql);
            //System.out.println(sql2);
            
        }
        //System.out.println(sql);
        try{
            con = db.OpenConnection();
            stmt = con.prepareStatement(sql);
            stmt.execute();
            stmt = con.prepareStatement(sql2);
            stmt.execute();
            
            System.out.println("Removed from inventory\nAdded new inventory request entry");
            con.close();
        }
        catch(Exception e){
            System.out.println(e);
            System.out.println("Equipment.requestCurrentInventory");
        }
        //compare how much is needed va how much we have
        //if more, zero out and request new inventory for remainder
        
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
    
    public ArrayList ViewInventoryRequests(){
            
        ArrayList<String> elements = new ArrayList<>();
        sql = "SELECT * FROM inventory_request ORDER BY InvRequestID DESC LIMIT 20";
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
                elements.add(result.getString("ItemName"));
            }
        }
        catch(Exception e) {
            
        }
        return elements;
    }    
    

}

