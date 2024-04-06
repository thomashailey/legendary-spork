/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package msystem;

import java.sql.*;
import java.util.ArrayList;


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
    
    
    public void ManageEmployee() {
        
    }
    

    public ArrayList PullEmployees() throws SQLException, ClassNotFoundException {
        
        /*  Set connection to DBConnect OpenConnection() method,
            Create ArrayList to store DB elements
        */
        con = db.OpenConnection();
        ArrayList<String> elements = new ArrayList<>();
        try {
            String sql = "SELECT * FROM user_authoization";
            stmt = con.prepareStatement(sql);
            
            result = stmt.executeQuery();

            if (result != null) {
                System.out.println("Successfully Accessed DataBase");
            }
            while (result.next()) {
                elements.add(result.getString("Username"));
            }
        }
        catch(Exception e) {
            
        }
        return elements;
    }

    
    public void SearchEmployees() {
        //use pull employees to search for specific names
        // can use str.charAt(-1) in order to pull off last initial
        // and str.substring(0, -2) to pull only the first name like so
        //String fName = userID.substring(0,-2);
        //String lInit = userIS.charAt(-1);
        
    }
    
    public void PullEmpInfo() {
        //pull employee role and endorsements
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
                System.out.println("Successfully Accessed DataBase");
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
            
        }
        return elements;
    }
    
    public void AccessLogs() {
        // stretch goal - if we have time to add

        
    }
    
    public void PrintRecords() {
        
    }
    
}
