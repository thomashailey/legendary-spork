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
                elements.add(String.format("%s,%s,   %s", String.format("%10s",result.getString("UserID")), String.format("%12s", result.getString("Username")), result.getString("Role")));
            }
            /*
            while (result.next()) {
                System.out.print(result.getString("Username") + ", " + result.getString("UserID"));
            }*/
    


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
    
    public void PullReports () {
        //pull reports tied to the employee's User ID
    }
    
    public void SearchReports() {
        
    }
    
    public void AccessLogs() {
        // stretch goal - if we have time to add

        
    }
    
    public void PrintRecords() {
        
    }
    
}
