/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package msystem;

import java.sql.*;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.DefaultListModel;
import javax.swing.JList;

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
        con = db.OpenConnection();
        ArrayList<String> elements = new ArrayList<String>();
        MainPage main = new MainPage();
        
        DefaultListModel model = new DefaultListModel();
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
        
    }
    
    public void PullEmpInfo() {
        
    }
    
    public void PullReports () {
        
    }
    
    public void SearchReports() {
        
    }
    
    public void AccessLogs() {
        
    }
    
    public void PrintRecords() {
        
    }
    
}
