package msystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserAuthentication {
    
    private Connection con;
    private PreparedStatement stmt;
    private ResultSet result;
    
    public UserAuthentication() throws SQLException, ClassNotFoundException {
        // Initialize database connection
        DBConnect db = new DBConnect();
        con = db.getConnection();
    }
    
    public boolean authenticateUser(String username, String password) throws SQLException {
        String sql = "SELECT * FROM user_authoization WHERE Username = ? AND Password = ?";
        stmt = con.prepareStatement(sql);
        stmt.setString(1, username);
        stmt.setString(2, password);
        result = stmt.executeQuery();

        // Check if any rows are returned
        return result.next();
    }
}
