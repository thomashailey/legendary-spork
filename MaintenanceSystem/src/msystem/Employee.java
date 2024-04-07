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
        }
        return elements;
    }

    public ArrayList SearchEmployees(boolean fName, boolean lName, String firstNameString, String lastInitString) throws SQLException, ClassNotFoundException {
        //use pull employees to search for specific names
        // can use str.charAt(-1) in order to pull off last initial
        // and str.substring(0, -2) to pull only the first name like so
        //String fName = userID.substring(0,-2);
        //String lInit = userIS.charAt(-1);
        
        /*  Set connection to DBConnect OpenConnection() method,
            Create ArrayList to store DB elements
        
            SELECT Username
            FROM ceis400_groupc_maintsys.user_authoization
            where Username sounds like "Emma"
            and substr(Username, -1) ='A'
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
        }
        return elements;
        
    }
    
    public ArrayList PullEmpInfo(String userID) throws SQLException, ClassNotFoundException {
        //pull employee role and endorsements
        //TODO -- move pull employee information for edit employee here
        
        /*  Set connection to DBConnect OpenConnection() method,
            Create ArrayList to store DB elements
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
        }
        return elements;
    }
    
    public void RemoveEmpInfo(String userID){
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
        }
    }
    
    public ArrayList PullReports () throws SQLException, ClassNotFoundException {
        //pull reports tied to the employee's User ID
        /*  Set connection to DBConnect OpenConnection() method,
            Create ArrayList to store DB elements
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
        }
        catch(Exception e) {
            
        }
        return elements;
    }
    
    public ArrayList SearchReports(int userInput) throws SQLException, ClassNotFoundException {
        con = db.OpenConnection();
        MainPage mp = new MainPage();
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
        }
        catch(Exception e) {
            JOptionPane.showMessageDialog(null, "Incorrect");
        }
        return elements;
    }
    
    public String ReportDetails(String selectedItem) throws SQLException, ClassNotFoundException {
        con = db.OpenConnection();
        ArrayList<String> elements = new ArrayList<>();
        String results = "";
        try {
            String sql = "SELECT * FROM reports WHERE ReportID = ? ";
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, Integer.parseInt(selectedItem));
            
            result = stmt.executeQuery();

            if (result != null) {
                System.out.println("Entry not empty");
            }
            results = elements.toString();
        }
        catch(Exception e) {
            
        }
        return results;
    }
    
    public void AccessLogs() {
        // stretch goal - if we have time to add

        
    }
    
    public void PrintRecords() {
        
    }
    
}
