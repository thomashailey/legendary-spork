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
