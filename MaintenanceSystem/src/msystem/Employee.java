/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package msystem;

import java.sql.SQLException;
import javax.swing.DefaultListModel;

/**
 *
 * @author thoma
 */
public class Employee {
    
    public void ManageEmployee() {
        
    }
    
    public static void PullEmployees() throws SQLException, ClassNotFoundException {
        DBConnect db = new DBConnect();
        db.OpenConnection();
        
        DefaultListModel model = new DefaultListModel();
        try {
            String sql = "SELECT * FROM user_authoization";
            
            
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
