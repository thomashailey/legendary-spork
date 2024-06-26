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
import java.util.Collections;
import java.util.HashSet;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.swing.JOptionPane;

public class Equipment {
    
    // Global level Variables
    DBConnect db = new DBConnect();
    Connection con = null;
    PreparedStatement stmt;
    ResultSet result;
    String sql = null;

    CheckInScreen checkin = new CheckInScreen();
    CheckOutScreen checkout = new CheckOutScreen();
    
    public ArrayList<String> CheckIn() throws SQLException, ClassNotFoundException {
        // Access database to return equipment
        con = db.OpenConnection();
        
        // Instantiate variables
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
            con.close();
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
            String sql = "SELECT * FROM equipment WHERE Status = \'?\' AND EquipmentName = \'?\' AND Description = \'?\' LIMIT 1;";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, "Available");
            stmt.setString(2, equipName);
            stmt.setString(3, equipDescrip);
            
            result = stmt.executeQuery();
            
            
            
//            
//            Use item fields to change equipment_checkout table to reflect 
//            state of checked equipment. Above code will use selected item
//            from list to find first available equipment in the database and 
//            allow user to check it out under their account
            
            
            if (result != null) {
                System.out.println("Successfully Accessed Equipment DataBase - CheckOut");
            }
            while (result.next()) {
                itemToAdd = String.format("%s -- %s", result.getString("EquipmentName"), result.getString("Description"));
                if(!elements.contains(itemToAdd)){
                    elements.add(itemToAdd);
                }
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Equipment.CheckOut");
        }
        return elements;
        
    }
    
    public ArrayList PullEquipInfo(String equipName, String equipDescrip) throws SQLException, ClassNotFoundException {
        //pull employee role and endorsements
    
        /*  Set connection to DBConnect OpenConnection() method,
            Create ArrayList to store DB elements
        */
        con = db.OpenConnection();
        ArrayList<String> elements = new ArrayList<String>();
        try {
            String sql = String.format("SELECT * FROM equipment WHERE EquipmentName = \'%s\' AND Description = \'%s\' AND Status = \'Available\' LIMIT 1", equipName, equipDescrip);
            stmt = con.prepareStatement(sql);
            
            result = stmt.executeQuery();

            if (result != null) {
                System.out.println(String.format("Successfully Accessed DataBase to pull equipment: %s - %s", equipName, equipDescrip));
            }
            while (result.next()) {
                Collections.addAll(elements, result.getString("EquipmentIDChar"), result.getString("EquipmentIDNum"), result.getString("EquipmentName"), result.getString("Description"));
            }
            con.close();
            System.out.println("Database closed");
        }   
        catch(Exception e) {
            System.out.println(e);
        }
        return elements;
    }
    
    
  
    public ArrayList ViewEquipment() throws SQLException, ClassNotFoundException {
        /*  Set connection to DBConnect OpenConnection() method,
            Create ArrayList to store DB elements
        */
        con = db.OpenConnection();
        ArrayList<String> elements = new ArrayList<>();
        HashSet<String> uniqueItems = new HashSet<>(); // HashSet to store unique items 
        String itemToAdd = null;
        try {
            sql = "SELECT * FROM equipment";
            stmt = con.prepareStatement(sql);
            
            result = stmt.executeQuery();
            
            if (result != null) {
                System.out.println("Successfully Accessed Equipment DataBase");
            }
            while (result.next()) {
                String itemName = result.getString("EquipmentName");
                String itemDescription = result.getString("Description");
                itemToAdd = String.format("%s -- %s", itemName, itemDescription);
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
            con.close();
        }
        catch(Exception e) {
            System.out.println(e);
            System.out.println("Equipment.ViewEquipment");
        }
        return elements;
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
            con.close();
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
            con.close();
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
            con.close();
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
    
    public ArrayList searchInventoryRecordForUnfulfilled(String itemNameRequested, String itemDescriptionRequested, String convertThisToInt){
        int amountAdding = Integer.parseInt(convertThisToInt);
        String sql2 = "";
        Connection con2 = null;
        PreparedStatement stmt2;
        ResultSet result2;

        sql = String.format("SELECT * FROM inventory_request WHERE Fulfilled = 0 and ItemName = \"%s\" and Description = \"%s\" LIMIT 1", 
                itemNameRequested, itemDescriptionRequested);
        System.out.println(sql);
        ArrayList<String> elements = new ArrayList<>();
        int amountNeeded = 0;
        try{
            con = db.OpenConnection();
            stmt = con.prepareStatement(sql);
            result = stmt.executeQuery();
            System.out.println(result);
            if(result != null){
                while(result.next()){
                    System.out.println(sql);
                    ///elements.add("None");
                    System.out.println(elements);
                    //Collections.addAll(elements, result.getString("InvRequestID"), result.getString("Quantity"), result.getString("UserIDRequested"));
                    amountNeeded = Integer.parseInt(result.getString("Quantity"));
                    //TODO add all sql querys to adjust the inventory records
                    if(amountAdding > amountNeeded){
                        System.out.println("amountAdding > amountNeeded");
                        String amountRemainingToAdd = String.format("%d", (amountAdding-amountNeeded));
                        sql2 = String.format("UPDATE inventory_request SET Fulfilled = 1 where InvRequestID = %s",
                                result.getString("InvRequestID"));
                        con2 = db.OpenConnection();
                        stmt2 = con2.prepareStatement(sql2);
                        stmt2.execute();
                        Collections.addAll(elements, "fn", result.getString("UserIDRequested"), amountRemainingToAdd);
                        //This request is fulfilled, and there is more remaining to go through
                        
                    }
                    else if(amountAdding == amountNeeded){
                        System.out.println("amountAdding == amountNeeded");
                        sql2 = String.format("UPDATE inventory_request SET Fulfilled = 1 where InvRequestID = %s",
                                result.getString("InvRequestID"));
                        con2 = db.OpenConnection();
                        stmt2 = con2.prepareStatement(sql2);
                        stmt2.execute();
                        Collections.addAll(elements, "fc", result.getString("UserIDRequested"));
                        // this request is fulfilled, and the amount is fully covered
                    }
                    else if(amountAdding < amountNeeded){
                        System.out.println("amountAdding < amountNeeded");
                        int amountRemainingRequested = amountNeeded-amountAdding;
                        sql2 = String.format("UPDATE inventory_request SET Quantity = %s, Fulfilled = 1 where InvRequestID = %s",
                                amountAdding, result.getString("InvRequestID"));
                        con2 = db.OpenConnection();
                        stmt2 = con2.prepareStatement(sql2);
                        stmt2.execute();
                        ArrayList<String> sendToNewInvRequest = new ArrayList<>();
                        //0=IDCHAR, 1=IDNUM, 2=ITEMNAME, 3=ITEMDESC, 4=USERidRequesting, 5=REQUESTamt, 6=LOCATION
                        Collections.addAll(sendToNewInvRequest, 
                                result.getString("ItemIDChar"), result.getString("ItemIDNum"), 
                                result.getString("ItemName"), result.getString("Description"),
                                result.getString("UserIDRequested"), // need to put userID here(4) and transfer that to the requestNewInventory as well
                                "this entry doesn't matter", 
                                result.getString("Destination"));
                        requestNewInventory(sendToNewInvRequest, amountRemainingRequested);
                        Collections.addAll(elements, "uc", result.getString("UserIDRequested"));
                        //This request is unfulfilled, and the amount os fully covered
                    }
                    else{
                        System.out.println("else statement");
                        elements.add("None");
                    }
                }
                con.close();
                if(con2 != null){
                    con2.close();
                }
            }
        }
        catch(Exception e){
            System.out.println(e);
            System.out.println("Equipment.searchInventoryRecordForUnfulfilled");
        }
        elements.add("None");
        System.out.println(elements);
        return elements;
    }
    
    public void addToInventory(ArrayList inventoryToAdd) {
        // Add code to add inventory to employee's account
        //expecting 3 items here, ItemIDChar, ItemIDNum, and toAddAmt
        //yeah strike that as a just in case, full item line expected
        //0=IDCHAR, 1=IDNUM, 2=ITEMNAME, 3=ITEMDESC, 4=ADDamt, 5=LOCATION
        String startOfNotifStatement = "Please Inform User ";
        String completeFulfillment = " that their request was completely added to the inventory";
        String partialFulfillment = " that their request was partially added to the inventory";
        String sendThisInANotification = "Requests found";
        
        String amountNeeded = inventoryToAdd.get(4).toString();
        ArrayList<String> list = new ArrayList<>();
        while(true){
            list = searchInventoryRecordForUnfulfilled(inventoryToAdd.get(2).toString(), inventoryToAdd.get(3).toString(), amountNeeded);
            if(list.get(0).equals("None")){
                //no match,no need to re-search
                break;
            }
            else if(list.get(0).toString().equals("uc")){
                //match, but amount is completely covered and the request is not fully covered
                sendThisInANotification = String.format("%s\n%s%s%s",
                        sendThisInANotification, startOfNotifStatement, 
                        list.get(1), partialFulfillment);
                break;
            }
            else if(list.get(0).toString().equals("fc")){
                //match, the amount is fully covered and the request is fully covered
                sendThisInANotification = String.format("%s\n%s%s%s",
                        sendThisInANotification, startOfNotifStatement, 
                        list.get(1), completeFulfillment);
                break;
            }
            else if(list.get(0).toString().equals("fn")){
                //match, the request is fully covered but the amount added still has room to go so search will run again
                sendThisInANotification = String.format("%s\n%s%s%s",
                        sendThisInANotification, startOfNotifStatement, 
                        list.get(1), completeFulfillment);
                amountNeeded = list.get(2).toString();
            }
        }
        if(!sendThisInANotification.equals("Requests found")){
            JOptionPane.showMessageDialog(null, sendThisInANotification);
        }
        
        //add search into inventory request where unfulfilled, then popup a notification to notify user requested
        //if found and the amount added is more than the request, mark fulfilled
        //if found and the amount added is less, update that line to fulfilled with the amount added
        //and the remainder add to a new line
        
        //below is to add to current inventory
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
        //0=IDCHAR, 1=IDNUM, 2=ITEMNAME, 3=ITEMDESC, 4=USERidRequesting, 5=REQUESTamt, 6=LOCATION
        sql = String.format("INSERT INTO inventory_request(ItemIDChar, ItemIDNum, UserIDRequested,"
                + " ItemName, Description, Quantity, Destination, Fulfilled) VALUES(\"%s\", %s, %s, \"%s\", \"%s\", %s, \"%s\", %s)",
                    requestedNewInv.get(0), requestedNewInv.get(1), 
                    requestedNewInv.get(4), requestedNewInv.get(2), 
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
        Connection con2 = null;
        PreparedStatement stmt2;
        // adding a request against current inventory, will pull values from inventory table
        //requested vs number in inventory
        //0=IDCHAR, 1=IDNUM, 2=ITEMNAME, 3=ITEMDESC, 4=REQUESTamt, 5=AMTinINVENTORY, 6=LOCATION - old for reference
        //0=IDCHAR, 1=IDNUM, 2=ITEMNAME, 3=ITEMDESC, 4=userIDrequested 5=REQUESTamt, 6=LOCATION, 7=AMTinINVENTORY
        if(Integer.parseInt(requestedCurrentInventory.get(5).toString()) > Integer.parseInt(requestedCurrentInventory.get(7).toString())){
            //requested amount larger than the amount currently in inventory, adding an unfulfilled request to inventory_requests
            int remainingInventoryRequired = Integer.parseInt(requestedCurrentInventory.get(5).toString()) - Integer.parseInt(requestedCurrentInventory.get(7).toString());
            System.out.println(remainingInventoryRequired);
            requestNewInventory(requestedCurrentInventory, remainingInventoryRequired);

            sql = String.format("UPDATE inventory SET Quantity = 0 WHERE ItemIDChar = \"%s\" and ItemIDNum = %s", 
                     requestedCurrentInventory.get(0), requestedCurrentInventory.get(1));
            
            sql2 = String.format("INSERT INTO inventory_request(ItemIDChar, ItemIDNum, UserIDRequested,"
                    + " ItemName, Description, Quantity, Destination, Fulfilled) VALUES(\"%s\", %s, %s, \"%s\", \"%s\", %s, \"%s\", %s)",
                    requestedCurrentInventory.get(0), requestedCurrentInventory.get(1), 
                    requestedCurrentInventory.get(4), requestedCurrentInventory.get(2), 
                    requestedCurrentInventory.get(3), requestedCurrentInventory.get(7), 
                    requestedCurrentInventory.get(6), 1); // 1 here means the request has been fulfilled(true)
            
            System.out.println("request more");
            //System.out.println(String.format("%s - is something foing on here?", sql));
            //System.out.println(sql2);
        }
        else{
            //requested amt equal to or lesser than the amt currently in inventory, adding a fulfilled report to inventory_requests
            //0=IDCHAR, 1=IDNUM, 2=ITEMNAME, 3=ITEMDESC, 4=userIDrequested 5=REQUESTamt, 6=LOCATION, 7=AMTinINVENTORY
            sql = String.format("UPDATE inventory SET Quantity = (Quantity-%s) WHERE ItemIDChar = \"%s\" and ItemIDNum = %s", 
                    requestedCurrentInventory.get(5), requestedCurrentInventory.get(0), requestedCurrentInventory.get(1));
            sql2 = String.format("INSERT INTO inventory_request(ItemIDChar, ItemIDNum, UserIDRequested, "
                    + "ItemName, Description, Quantity, Destination, Fulfilled) VALUES(\"%s\", %s, %s, \"%s\", \"%s\", %s, \"%s\", %s)",
                    requestedCurrentInventory.get(0), requestedCurrentInventory.get(1), 
                    requestedCurrentInventory.get(4), requestedCurrentInventory.get(2), 
                    requestedCurrentInventory.get(3), requestedCurrentInventory.get(5), 
                    requestedCurrentInventory.get(6), 1); // 1 here means the request has been fulfilled
            System.out.println("current request equals or lower");
            System.out.println(sql);
            System.out.println(sql2);
            
        }
        //System.out.println(sql);
        try{
            con = db.OpenConnection();
            stmt = con.prepareStatement(sql);
            stmt.execute();
            con2 = db.OpenConnection();
            stmt2 = con2.prepareStatement(sql2);
            stmt2.execute();
            
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
            con.close();
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
            con.close();
        }
        catch(Exception e) {
            System.out.println(e);
            System.out.println("Equipment.ViewInventoryRequests");
        }
        return elements;
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
    }

    public ArrayList checkForUserCheckOut(String equipIDChar, String equipIDNum){
        sql = String.format("SELECT * FROM equipment_checkout WHERE EquipmentIDChar = \"%s\" and EquipmentIDNum = %s and Status = \"Checked out\"", equipIDChar, equipIDNum);
        ArrayList userInformation = new ArrayList<>();
        
        try{
            con = db.OpenConnection();
            stmt = con.prepareStatement(sql);
            result = stmt.executeQuery();
            while(result.next()){
                userInformation.add(result.getString("UserID"));
            }
            con.close();
        }
        catch(Exception e){
            System.out.println(e);
            System.out.println("Equipment.checkForUserCheckOut");
        }
        if(userInformation.isEmpty()){
            userInformation.addLast("not checked out by a user");
        }
        return userInformation;
    }
    
    public ArrayList checkIfCheckedIn(String equipIDChar, String equipIDNum){
        sql = String.format("SELECT * FROM equipment WHERE EquipmentIDChar = \"%s\" and EquipmentIDNum = %s", equipIDChar, equipIDNum);
        ArrayList checkedIn = new ArrayList<>();
        
        try{
            con = db.OpenConnection();
            stmt = con.prepareStatement(sql);
            result = stmt.executeQuery();
            while(result.next()){
                checkedIn.add(result.getString("Status"));
            }
            con.close();
        }
        catch(Exception e){
            System.out.println(e);
            System.out.println("Equipment.checkIfCheckedIn");
        }
        if(checkedIn.isEmpty()){
            checkedIn.add("not an item");
        }
        return checkedIn;
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
            con.close();
        }
        catch(Exception e) {
            
        }
        return elements;
    }    
    

}

