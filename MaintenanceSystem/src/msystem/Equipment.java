package msystem;

/**
 *
 * @author thoma / vaughnr
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Equipment {
    private int ID;
    private String name;
    private String type;
    private String status;
    private String location;
    private static final List<Equipment> inventory = new ArrayList<>();
    private static Connection connection = null;

    // Constructor
    public Equipment(int ID, String name, String type, String status, String location) {
        this.ID = ID;
        this.name = name;
        this.type = type;
        this.status = status;
        this.location = location;
    }
    
    // Establish database connection
    private static void connect() {
        String url = "jdbc:mysql://localhost:3306/ceis400_groupc_maintsys";
        String username = "root";
        String password = "devry123";
        
        try {
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connected to the database.");
        } catch (SQLException e) {
            System.out.println("Failed to connect to the database.");
        }
    }
    
    // Close database connection
    private static void disconnect(){
        if (connection != null){
            try {
                connection.close();
                System.out.println("Disconnected from the database.");
            } catch (SQLException e) {
            }
        }
        
    }
    
     // Getters and Setters
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    // Check in method
    public void checkIn() {
        setStatus("Available");
        updateStatusInDatabase();
    }

    // Check out method
    public void checkOut() {
        setStatus("Checked Out");
        updateStatusInDatabase();
    }

    // Report loss method
    public void reportLoss() {
        setStatus("Lost");
        updateStatusInDatabase();
    }

    // Check status method
    public void checkStatus() {
        System.out.println("Equipment ID: " + getID() + ", Status: " + getStatus());
    }

    // Search inventory method
    public static Equipment searchInventory(int ID) {
        for (Equipment item : inventory) {
            if (item.getID() == ID) {
                return item;
            }
        }
        return null;
    }
    
    // Update equipment status in the database
    private void updateStatusInDatabase() {
        connect();
        try {
            String sql = "UPDATE equipment SET status = ? WhERE ID = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, getStatus());
            statement.setInt(2, getID());
            statement.executeUpdate();
            System.out.println("Equipment status updated in the database.");
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
            disconnect();
        }
    }

    // Adjust inventory method
    public static void adjustInventory(Equipment item) {
        if (item != null) {
            inventory.remove(item);
        }
    }

    // View inventory method
    public static void viewInventory() {
        System.out.println("Inventory:");
        for (Equipment item : inventory) {
            System.out.println(item);
        }
    }

    // Add equipment to inventory
    public void addToInventory() {
        inventory.add(this);
    }

    // toString method to represent the object as a string
    @Override
    public String toString() {
        return "Equipment{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", status='" + status + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}

