/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package msystem;

import com.sun.jdi.connect.spi.Connection;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author thoma
 */
public class DBConnect {
    public static void OpenConnection() throws SQLException, ClassNotFoundException {
        // Load the driver
        Class.forName("com.mysql.cj.jdbc.Driver");
        
        // Create connection
        java.sql.Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ceis400_groupc_maintsys","root","devry123");
    }
    
    public static void CloseConnection() throws SQLException {
        java.sql.Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ceis400_groupc_maintsys","root","devry123");
        connection.close();
    }
}
