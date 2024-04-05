/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package msystem;

import java.sql.*;
import javax.swing.DefaultListModel;

/**
 *
 * @author thoma
 */
public class Employee {
    
    // Global level varriables
    Connection con = null;
    PreparedStatement stmt;
    
    
    public void ManageEmployee() {
        
    }
    
    public void PullEmployees() throws SQLException, ClassNotFoundException {
        DBConnect db = new DBConnect();
        con = db.OpenConnection();
        
        DefaultListModel model = new DefaultListModel();
        try {
            String sql = "SELECT * FROM user_authoization";
            stmt = con.prepareStatement(sql);
            
        }
        catch(Exception e) {
            
        }
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
