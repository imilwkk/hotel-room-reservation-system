import java.io.Serializable;

public class Room implements Serializable { // Make Room class serializable
    private static final long serialVersionUID = 1L; // Optional but recommended for serialization
    private int roomNumber;
    private String roomType;
    private double price;
    private boolean available;

    public Room(int roomNumber, String roomType, double price, boolean available) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.price = price;
        this.available = available;
    }

    // Getters and Setters

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    // Method to display availability as "Available" or "Booked"
    public String availabilityStatus() {
        return available ? "Available" : "Booked";
    }

    // Override toString to display room details with price in "som"
    @Override
    public String toString() {
        return "Room " + roomNumber + " (" + roomType + "), Price: " + (int) price + " som per night, Status: " + availabilityStatus();
    }
}

