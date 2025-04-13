import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileManager {

    // Method to save rooms to a file
    public static void saveRooms(List<Room> rooms) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("rooms.dat"))) {
            out.writeObject(rooms); // Serializing rooms list to file
        } catch (IOException e) {
            System.out.println("Error saving rooms: " + e.getMessage());
        }
    }

    // Method to load rooms from file
    public static List<Room> loadRooms() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("rooms.dat"))) {
            return (List<Room>) in.readObject(); // Deserializing rooms list from file
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading rooms: " + e.getMessage());
            return new ArrayList<>(); // Return empty list in case of error
        }
    }

    // Save users to a file
    public static void saveUsers(List<User> users) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("users.dat"))) {
            out.writeObject(users);
        } catch (IOException e) {
            System.out.println("Error saving users: " + e.getMessage());
        }
    }

    // Load users from file
    public static List<User> loadUsers() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("users.dat"))) {
            return (List<User>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading users: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}






