/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package msystem;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JOptionPane;



/**
 *
 * @author thoma
 */
public class Employee {
    
    // Global level varriables

    DBConnect db = new DBConnect();
    Connection con = null;
    PreparedStatement stmt;
    ResultSet result;
    String sql = null;
    
    public ArrayList PullEmployees() throws SQLException, ClassNotFoundException {
        
        /*  Set connection to DBConnect OpenConnection() method,
            Create ArrayList to store DB elements
        */
        con = db.OpenConnection();
        ArrayList<String> elements = new ArrayList<>();
        try {
            sql = "SELECT * FROM user_authoization";
            stmt = con.prepareStatement(sql);
            
            result = stmt.executeQuery();

            if (result != null) {
                System.out.println("Successfully Accessed DataBase to pull all users");
            }
            while (result.next()) {
                elements.add(String.format("%s,%s,   %s", String.format("%10s",result.getString("UserID")), String.format("%12s", result.getString("Username")), result.getString("Role")));
            }
            /*
            while (result.next()) {
                System.out.print(result.getString("Username") + ", " + result.getString("UserID"));
            }*/
            con.close();
            System.out.println("Database closed");
         


        }
        
        catch(Exception e) {
            System.out.println(e);
            System.out.println("Employee.PullEmployees");
        }
        return elements;
    }

    public ArrayList SearchEmployees(boolean fName, boolean lName, String firstNameString, String lastInitString) throws SQLException, ClassNotFoundException {
        //use pull employees to search for specific names
      
        /*  Set connection to DBConnect OpenConnection() method,
            Create ArrayList to store DB elements
        
            SELECT Username
            FROM ceis400_groupc_maintsys.user_authoization
            where Username sounds like "Emma"
            and substr(Username, -1) ='A'
        
        pulls the entered first name and last initial, is also able to only use one or ther other
        will then run the search through the db and display the results
        */
        
        con = db.OpenConnection();
        ArrayList<String> elements = new ArrayList<>();
        try {
            if(fName && lName){
                sql = String.format("SELECT * FROM user_authoization WHERE Username SOUNDS LIKE '%s' and SUBSTR(Username, -1) = '%s'", firstNameString, lastInitString);
                System.out.println(sql);
                stmt = con.prepareStatement(sql);
                result = stmt.executeQuery();
            }
            else if(fName && !lName){
                sql = String.format("SELECT * FROM user_authoization WHERE Username SOUNDS LIKE '%s' ", firstNameString);
                stmt = con.prepareStatement(sql);
                result = stmt.executeQuery();
            }
            else if(!fName && lName){
                sql = String.format("SELECT * FROM user_authoization WHERE SUBSTR(Username, -1) = '%s'", lastInitString);
                stmt = con.prepareStatement(sql);
                result = stmt.executeQuery();
            }
            if (result != null) {
                System.out.println("Successfully Accessed DataBase to pull searched users");
            }
            while (result.next()) {
                elements.add(String.format("%s,%s,   %s", 
                        String.format("%10s",result.getString("UserID")), 
                        String.format("%12s", result.getString("Username")),
                        result.getString("Role")));
            }
            /*
            while (result.next()) {
                System.out.print(result.getString("Username") + ", " + result.getString("UserID"));
            }*/
            con.close();
            System.out.println("Database closed");
         
        }
        
        catch(Exception e) {
            System.out.println(e);
            System.out.println("Employee.SearchEmployees");
        }
        return elements;
        
    }
    
    public ArrayList PullEmpInfo(String userID) throws SQLException, ClassNotFoundException {
        //pull employee role and endorsements   
        /*  Set connection to DBConnect OpenConnection() method,
            Create ArrayList to store DB elements
        pulls all employee information for user information edits, returns an arraylist with that information
        */
        con = db.OpenConnection();
        ArrayList<String> elements = new ArrayList<String>();
        try {
            sql = String.format("SELECT * FROM user_authoization WHERE UserID = %s", userID);
            stmt = con.prepareStatement(sql);
            
            result = stmt.executeQuery();

            if (result != null) {
                System.out.println(String.format("Successfully Accessed DataBase to pull user %s", userID));
            }
            while (result.next()) {
                Collections.addAll(elements, result.getString("UserID"), result.getString("Username"), result.getString("Role"), result.getString("Endorsement"));
            }
            /*
            while (result.next()) {
                System.out.print(result.getString("Username") + ", " + result.getString("UserID"));
            }*/
            con.close();
            System.out.println("Database closed");
         

        }
        
        catch(Exception e) {
            System.out.println(e);
            System.out.println("Employee.PullEmpInfo");
        }
        return elements;
    }
    
    public void RemoveEmpInfo(String userID){
        /*
        Removes the selected employee entirely from the DB user_authoization
        */
        sql = String.format("DELETE FROM user_authoization WHERE UserID = %s", userID);
        try {
            con = db.OpenConnection();
            stmt = con.prepareStatement(sql);
            stmt.execute();
            
            if (result != null) {
                System.out.println(String.format("Successfully Accessed DataBase to remove user %s", userID));
            }
            /*
            while (result.next()) {
                System.out.print(result.getString("Username") + ", " + result.getString("UserID"));
            }*/
            con.close();
            System.out.println("Database closed");
        }
        catch(Exception e) {
            System.out.println(e);
            System.out.println("Employee.RemoveEmployee");
        }
    }
    
    public ArrayList PullReports () throws SQLException, ClassNotFoundException {
        /*  Set connection to DBConnect OpenConnection() method,
            Create ArrayList to store DB elements
        pulls all reports then returns an ArrayList to diaply them
        */
        con = db.OpenConnection();
        ArrayList<String> elements = new ArrayList<>();
        try {
            String sql = "SELECT * FROM reports";
            stmt = con.prepareStatement(sql);
            
            result = stmt.executeQuery();

            if (result != null) {
                System.out.println("Successfully Accessed Reports DataBase");
            }
            while (result.next()) {
                elements.add(result.getString("ReportID"));
            }
            con.close();
        }
        catch(Exception e) {
            System.out.println(e);
            System.out.println("Employee.PullReports");
        }
        return elements;
    }
    
    public ArrayList SearchReports(int userInput) throws SQLException, ClassNotFoundException {
        /*
        Pulls all reports associated with input userID, then returns an arraylist to display them
        */
        con = db.OpenConnection();
        //MainPage mp = new MainPage();
        ArrayList<String> elements = new ArrayList<>();
        try {
            String sql = "SELECT * FROM reports WHERE UserID = ? ";
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, userInput);
            
            result = stmt.executeQuery();

            if (result != null) {
                System.out.println("Successfully Accessed DataBase");
            }
            while (result.next()) {
                elements.add(result.getString("ReportID"));
            }
            con.close();
        }
        catch(Exception e) {
            //JOptionPane.showMessageDialog(null, "Incorrect");
            System.out.println(e);
            System.out.println("Employee.SearchReports");
        }
        return elements;
    }
    
    public String ReportDetails(String selectedItem) throws SQLException, ClassNotFoundException {
        /*
        Pulls report detals using the report ID, then formats them for display and returns that in a string
        */
        con = db.OpenConnection();
        //ArrayList<String> elements = new ArrayList<>();
        String results = "";
        try {
            String sql = String.format("SELECT * FROM reports WHERE ReportID = %s", selectedItem);
            stmt = con.prepareStatement(sql);
            //stmt.setInt(1, Integer.parseInt(selectedItem));
            
            result = stmt.executeQuery();
            
            if (result != null) {
                System.out.println("Entry not empty");
                while(result.next()){
                    System.out.println(String.format("%s, %s, %s, %s", 
                        result.getString("ReportID"), result.getString("UserID"), 
                        result.getString("ReportDate"), result.getString("ReportType")));
                    results = String.format("Report ID   - %s\nUserID      - %s\nReport Date - %s\nType/ Description\n%s", 
                        result.getString("ReportID"), result.getString("UserID"), 
                        result.getString("ReportDate"), result.getString("ReportType"));
                    
                }
                
            }
            con.close();
            //results = elements.toString();
            //System.out.println(results);
            //System.out.println(elements);
        }
        catch(Exception e) {
            System.out.println(e);
            System.out.println("Employee.ReportDetails");
        }
        return results;
    }
    
    public String PrintReportLogs(String selectedReport){
        /*
        pulls all information in a single report and returns a formatted final report to print out
        */
        String reportToPrint = null;
        sql = String.format("SELECT * FROM reports WHERE ReportID = %s", selectedReport);
        try{
            con = db.OpenConnection();
            stmt = con.prepareStatement(sql);
            result = stmt.executeQuery();
            while(result.next()){
                reportToPrint = String.format("Report ID   - %s\nUserID      - %s\nReport Date - %s\nType/ Description\n%s\n\nReport Body\n%s", 
                        result.getString("ReportID"), result.getString("UserID"), 
                        result.getString("ReportDate"), result.getString("ReportType"),
                        result.getString("data"));
            }
            con.close();
        }
        catch(Exception e){
            System.out.println(e);
            System.out.println("Employee.PrintReportLogs");
        }
        return reportToPrint;
    }
    
    public String pullAllLostForReport(){
        /*
        Pulls all lost equipment and sends the information within a string to generate a report with that information
        */
        String allLostEquipment = "";
        sql = "SELECT equipment.EquipmentIDChar, equipment.EquipmentIDNum, equipment.Location, equipment_checkout.UserID " +
            "FROM equipment " +
            "LEFT JOIN equipment_checkout ON equipment.EquipmentIDChar = equipment_checkout.EquipmentIDChar " +
            "AND equipment.EquipmentIDNum = equipment_checkout.EquipmentIDNum " +
            "WHERE equipment.Location = \"Lost\"";
        
        try{
            con = db.OpenConnection();
            stmt = con.prepareStatement(sql);
            result = stmt.executeQuery();
            while(result.next()){
                if(result.getString("UserID") == null){
                    allLostEquipment = String.format("%s\nEquipment %s%s lost without a UserID associated", 
                            allLostEquipment, result.getString("EquipmentIDChar"),
                            result.getString("EquipmentIDNum"));
                }
                else{
                    allLostEquipment = String.format("%s\nEquipment %s%s lost by Employee %s", 
                            allLostEquipment, result.getString("EquipmentIDChar"),
                            result.getString("EquipmentIDNum"), result.getString("UserID"));
                }
                
            }
            con.close();
        }
        catch(Exception e){
            System.out.println(e);
            System.out.println("Employee.pullAllLostForReport");
        }

        return allLostEquipment;
    }
    
    public void addNewReport(ArrayList reportToAdd){
        /*
        Generated a new report based on the arraylist sent through the method
        */
        //order UID, Date, report type, data
        sql = String.format("INSERT INTO reports(UserID, ReportDate, ReportType, Data) VALUES(%s, '%s', \"%s\", \"%s\")", 
                reportToAdd.get(0), reportToAdd.get(1), reportToAdd.get(2), reportToAdd.get(3));
        //JOptionPane.showMessageDialog(null, sql);
        try {
            con = db.OpenConnection();
            stmt = con.prepareStatement(sql);
            stmt.execute();
            
            if (result != null) {
                System.out.println("Successfully Accessed DataBase to add report");
            }
            con.close();
            System.out.println("Database closed");
        }
        catch(Exception e) {
            System.out.println(e);
            System.out.println("Employee.addNewReport");
        }
    }
    
    public ArrayList PullMaintenanceActivities(String selectedItem) throws SQLException, ClassNotFoundException {
        /*
        Pulls all maint logs based on the provided UserID, then returns an arraylist with the sql information
        */

        con = db.OpenConnection();
        ArrayList<String> elements = new ArrayList<>();

        try {  sql = "SELECT * FROM maintenance_activities WHERE UserID = ?";
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, Integer.parseInt(selectedItem));
            result = stmt.executeQuery();

            if (result != null) {
                System.out.println("Successfully Accessed DataBase to pull maintenance activities");
            }
            while (result.next()) {
            elements.add(String.format("%s,%s,   %s", 
                        String.format("%10s",result.getString("ActivityID")), 
                        String.format("%12s", result.getString("ActivityDate")),
                        result.getString("Description")));
            }

            con.close();
            System.out.println("Database closed");
         
        }
        
        catch(Exception e) {
            System.out.println(e);
            System.out.println("Employee.PullMaintenanceActivities");
        }
        return elements;
    }
    
    public ArrayList PullAllMaintenanceActivities() throws SQLException, ClassNotFoundException {
        /*
        Pulls all maint logs and returns a formatted arraylist<string> to adjust the display
        */

       con = db.OpenConnection();
        ArrayList<String> elements = new ArrayList<>();
        try {
            String sql = "SELECT * FROM maintenance_activities";
            stmt = con.prepareStatement(sql);
            
            result = stmt.executeQuery();

            if (result != null) {
                System.out.println("Successfully Accessed Maintenance DataBase");
            }
            while (result.next()) {
                elements.add(String.format("%s,%s,   %s, %s",
                        String.format("%10s",result.getString("ActivityID")),
                        String.format("%12s", result.getString("UserID")),
                        String.format("%12s", result.getString("ActivityDate")),
                        result.getString("Description")));
            }
            con.close();
        
           }catch(Exception e) {
            System.out.println(e);
            System.out.println("Employee.PullMaintenanceActivities");
        }
        return elements;
    }
    
    public ArrayList PullMaintenanceActivitiesDate(String selectedItem) throws SQLException, ClassNotFoundException {
        /*
        Pulls all maint logs based on the provided date, then returns an arraylist with the sql information
        */
        con = db.OpenConnection();
        ArrayList<String> elements = new ArrayList<>();

        try {  sql = "SELECT * FROM maintenance_activities WHERE ActivityDate = ?";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, selectedItem);
            result = stmt.executeQuery();

            if (result != null) {
                System.out.println("Successfully Accessed DataBase to pull maintenance activities");
            }
            while (result.next()) {
            elements.add(String.format("%s,%s,   %s", 
                        String.format("%10s",result.getString("ActivityID")), 
                        String.format("%12s", result.getString("UserID")),
                        result.getString("Description")));
            }

            con.close();
            System.out.println("Database closed");
         
        }
        catch(Exception e) {
            System.out.println(e);
            System.out.println("Employee.PullMaintenanceActivities");
        }
        return elements;
    }
    
//    public ArrayList ConfirmNewLog(int userInput) throws SQLException, ClassNotFoundException {
//        /*
//        does this get pulled by anything?
//        can't actually see what it goes to
//        based on the sql statement it looks like this was an original version of PullMaintenanceActivitiesDate, commenting out method
//        */
//        
//
//        con = db.OpenConnection();
//        ArrayList<String> elements = new ArrayList<>();
//
//        try {  sql = "SELECT * FROM maintenance_activities WHERE ActivityDate = ?";
//            stmt = con.prepareStatement(sql);
//            stmt.setInt(1, userInput);
//            result = stmt.executeQuery();
//
//            if (result != null) {
//                System.out.println("Successfully Accessed DataBase to pull maintenance activities");
//            }
//            while (result.next()) {
//            elements.add(String.format("%s,%s,   %s", 
//                        String.format("%10s",result.getString("ActivityID")), 
//                        String.format("%12s", result.getString("UserID")),
//                        result.getString("Description")));
//            }
//
//            con.close();
//            System.out.println("Database closed");
//         
//        }
//        catch(Exception e) {
//            System.out.println(e);
//            System.out.println("Employee.PullMaintenanceActivities");
//        }
//        return elements;
//    }
    
    public void AccessLogs() {
        // stretch goal - if we have time to add
        //moved to report and maint page
    }
}
