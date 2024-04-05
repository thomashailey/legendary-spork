/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package msystem;

import java.sql.*;
import java.util.Vector;
import javax.swing.DefaultListModel;
import javax.swing.JList;

/**
 *
 * @author thoma
 */
public class Employee {
    
    // Global level varriables
    Connection con = null;
    PreparedStatement stmt;
    ResultSet result;
    
    
    public void ManageEmployee() {
        
    }
    
    public void PullEmployees() throws SQLException, ClassNotFoundException {
        DBConnect db = new DBConnect();
        con = db.OpenConnection();
        Vector<String> elements = new Vector<String>();
        MainPage main = new MainPage();
        
        DefaultListModel model = new DefaultListModel();
        try {
            String sql = "SELECT * FROM user_authoization";
            stmt = con.prepareStatement(sql);
            
            result = stmt.executeQuery();
            /*if (result != null) {
                System.out.println("Successfully Accessed DataBase");
            }*/
            while (result.next()) {
                elements.add(result.getString("Username"));
            }
    

        }
        catch(Exception e) {
            
        }
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
        // streatch goal - if we have time to add
        
    }
    
    public void PrintRecords() {
        
    }
    
}
