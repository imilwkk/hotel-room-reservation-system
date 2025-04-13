import java.util.*;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static List<User> users = FileManager.loadUsers();
    private static List<Room> rooms = FileManager.loadRooms();
    private static List<Reservation> reservations = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("Welcome to Hotel Room Reservation System!");

        while (true) {
            System.out.println("\nMain Menu:");
            System.out.println("1. Register New User");
            System.out.println("2. View All Users");
            System.out.println("3. View All Rooms");
            System.out.println("4. Book Room");
            System.out.println("5. Add Room");
            System.out.println("6. Cancel Reservation");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    registerUser();
                    break;
                case "2":
                    viewUsers();
                    break;
                case "3":
                    viewRooms();
                    break;
                case "4":
                    bookRoom();
                    break;
                case "5":
                    addRoom();
                    break;
                case "6":
                    cancelReservation();
                    break;
                case "7":
                    FileManager.saveUsers(users);
                    FileManager.saveRooms(rooms);
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    // Register a new user
    private static void registerUser() {
        System.out.print("Enter User Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter User Email: ");
        String email = scanner.nextLine();
        System.out.print("Enter User Phone: ");
        String phone = scanner.nextLine();

        User user = new User(name, email, phone);
        users.add(user);
        FileManager.saveUsers(users); // Save after adding
        System.out.println("User registered successfully!");
    }

    // View all users
    private static void viewUsers() {
        System.out.println("\nUsers List:");
        for (User user : users) {
            System.out.println(user);
        }
    }

    // View all rooms
    private static void viewRooms() {
        System.out.println("\nRooms List:");
        for (Room room : rooms) {
            System.out.println(room);
        }
    }

    // Add a new room
    private static void addRoom() {
        System.out.print("Enter Room Number: ");
        int roomNumber = Integer.parseInt(scanner.nextLine());

        System.out.println("Select Room Type: ");
        System.out.println("1. Single Room");
        System.out.println("2. Double Room");
        System.out.println("3. Suite");
        System.out.print("Choose room type (1-3): ");
        int roomTypeChoice = Integer.parseInt(scanner.nextLine());

        String roomType = "";
        switch (roomTypeChoice) {
            case 1:
                roomType = "Single Room";
                break;
            case 2:
                roomType = "Double Room";
                break;
            case 3:
                roomType = "Suite";
                break;
            default:
                System.out.println("Invalid choice. Defaulting to Single Room.");
                roomType = "Single Room";
        }

        System.out.print("Enter Room Price per Night: ");
        double price = Double.parseDouble(scanner.nextLine());

        System.out.print("Is the room available? (true/false): ");
        boolean isAvailable = Boolean.parseBoolean(scanner.nextLine());

        Room room = new Room(roomNumber, roomType, price, isAvailable);
        rooms.add(room);
        FileManager.saveRooms(rooms); // Save after adding
        System.out.println("Room added successfully!");
    }

    // Book a room
    private static void bookRoom() {
        System.out.println("\nAvailable Rooms:");
        for (Room room : rooms) {
            if (room.isAvailable()) {
                System.out.println(room);
            }
        }

        System.out.print("Enter Room Number to book: ");
        int roomNumber = Integer.parseInt(scanner.nextLine());

        Room room = findRoomByNumber(roomNumber);
        if (room == null || !room.isAvailable()) {
            System.out.println("Room is not available.");
            return;
        }

        System.out.print("Enter User Name for booking: ");
        String userName = scanner.nextLine();
        User user = findUserByName(userName);
        if (user == null) {
            System.out.println("User not found.");
            return;
        }

        System.out.print("Enter Reservation Date (yyyy-mm-dd): ");
        String reservationDate = scanner.nextLine();

        Reservation reservation = new Reservation(room, user, reservationDate);
        reservations.add(reservation);

        // Mark room as booked
        room.setAvailable(false);
        FileManager.saveRooms(rooms); // Save after booking

        System.out.println("Room booked successfully!");
    }

    // Cancel a reservation by User Name
    private static void cancelReservation() {
        System.out.print("Enter User Name to cancel reservation: ");
        String userName = scanner.nextLine();

        User user = findUserByName(userName);
        if (user == null) {
            System.out.println("User not found.");
            return;
        }

        List<Reservation> userReservations = findReservationsByUser(user);
        if (userReservations.isEmpty()) {
            System.out.println("No reservations found for this user.");
            return;
        }

        System.out.println("Found the following reservations:");
        for (Reservation reservation : userReservations) {
            System.out.println(reservation);
        }

        System.out.print("Enter Room Number to cancel booking: ");
        int roomNumberToCancel = Integer.parseInt(scanner.nextLine());

        Reservation reservationToCancel = null;
        for (Reservation reservation : userReservations) {
            if (reservation.getRoom().getRoomNumber() == roomNumberToCancel) {
                reservationToCancel = reservation;
                break;
            }
        }

        if (reservationToCancel == null) {
            System.out.println("Reservation for this room not found.");
            return;
        }

        System.out.print("Are you sure you want to cancel this reservation? (yes/no): ");
        String confirm = scanner.nextLine();

        if (confirm.equalsIgnoreCase("yes")) {
            // Mark the room as available again
            Room room = reservationToCancel.getRoom();
            room.setAvailable(true);

            // Remove the reservation
            reservations.remove(reservationToCancel);

            // Save the changes
            FileManager.saveRooms(rooms);
            System.out.println("Reservation canceled successfully.");
        } else {
            System.out.println("Cancellation aborted.");
        }
    }

    private static Room findRoomByNumber(int roomNumber) {
        for (Room room : rooms) {
            if (room.getRoomNumber() == roomNumber) {
                return room;
            }
        }
        return null;
    }

    private static User findUserByName(String name) {
        for (User user : users) {
            if (user.getName().equalsIgnoreCase(name)) {
                return user;
            }
        }
        return null;
    }

    private static List<Reservation> findReservationsByUser(User user) {
        List<Reservation> userReservations = new ArrayList<>();
        for (Reservation reservation : reservations) {
            if (reservation.getUser().equals(user)) {
                userReservations.add(reservation);
            }
        }
        return userReservations;
    }
}
