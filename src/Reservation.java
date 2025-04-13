import java.util.UUID;

public class Reservation {
    private String reservationId;
    private Room room;
    private User user;
    private String reservationDate;

    // constructor
    public Reservation(Room room, User user, String reservationDate) {
        this.reservationId = UUID.randomUUID().toString();  // Generate a unique ID
        this.room = room;
        this.user = user;
        this.reservationDate = reservationDate;
    }

    // getters and setters
    public String getReservationId() { return reservationId; }
    public void setReservationId(String reservationId) { this.reservationId = reservationId; }

    public Room getRoom() { return room; }
    public void setRoom(Room room) { this.room = room; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public String getReservationDate() { return reservationDate; }
    public void setReservationDate(String reservationDate) { this.reservationDate = reservationDate; }

    @Override
    public String toString() {
        return "Reservation ID: " + reservationId + ", Room: " + room.getRoomNumber() + ", User: " + user.getName() + ", Date: " + reservationDate;
    }
}

