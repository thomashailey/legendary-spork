package msystem;

/**
 *
 * @author thoma
 */
import java.util.ArrayList;
import java.util.List;

public class Equipment {
    private int ID;
    private String name;
    private String type;
    private String status;
    private String location;
    private static final List<Equipment> inventory = new ArrayList<>();

    // Constructor
    public Equipment(int ID, String name, String type, String status, String location) {
        this.ID = ID;
        this.name = name;
        this.type = type;
        this.status = status;
        this.location = location;
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
    }

    // Check out method
    public void checkOut() {
        setStatus("Checked Out");
    }

    // Report loss method
    public void reportLoss() {
        setStatus("Lost");
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

