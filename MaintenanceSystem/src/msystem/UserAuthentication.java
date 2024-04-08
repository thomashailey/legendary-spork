/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package msystem;

import java.sql.*;
/**
 *
 * @author thoma
 */
public class UserAuthentication {
    DBConnect db = new DBConnect();
    Connection con = null;
    PreparedStatement stmt;
    ResultSet result;
    String sql = null;
    
    
    
    public boolean authenticateUser(String userCode, String password) {
        try {
            if (checkPrimaryKey(userCode, password)) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    private boolean checkPrimaryKey(String userCode, String password) {
        try {
            // Get database connection
            con = db.OpenConnection();

            // Prepare SQL statement
            String sql = "SELECT * FROM user_authoization WHERE UserID = ? AND Password = ?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, userCode);
            statement.setString(2, password);

            // Execute query
            ResultSet resultSet = statement.executeQuery();
            boolean result = resultSet.next();

            // Close database connection
            con.close();

            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    


}
