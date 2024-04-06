/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package msystem;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
/**
 *
 * @author thoma
 */
public class UserAuthentication {
    
    DBConnect db = new DBConnect();
    Connection con = null;
    PreparedStatement stmt;
    ResultSet result;
    
    
    private void CheckAccess() {
        
    }
    
    private ArrayList<String> AuthenticateUser(String userInput) throws SQLException, ClassNotFoundException{
        MainPage mp = new MainPage();
        ArrayList<String> elements = new ArrayList<>();
        try {
            String sql = "SELECT * FROM user_authoization WHERE Username = ? " + "SELECT * FROM user_authoization WHERE Password = ? ";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, userInput);
            
            result = stmt.executeQuery();

            if (result != null) {
                System.out.println("Successfully Accessed DataBase");
            }
            while (result.next()) {
                elements.add(result.getString("Username + Password"));
            }
        }
        catch(Exception e) {
            
        }
        return elements;
    }
    
}
