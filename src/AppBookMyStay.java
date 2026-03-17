import java.util.Arrays;
import java.util.List;

public class AppBookMyStay {

    public static void main(String[] args) {

        System.out.println("===== Welcome to BookMyStay =====");

        // Room objects
        Room single = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suite = new SuiteRoom();
        List<Room> rooms = Arrays.asList(single, doubleRoom, suite);

        // Inventory
        RoomInventory inventory = new RoomInventory();

        // Room search service
        RoomSearchService searchService = new RoomSearchService(inventory);

        // Booking requests
        BookingRequestQueue bookingQueue = new BookingRequestQueue();

        // Sample requests
        bookingQueue.addRequest(new Reservation("Isai", "Single Room"));
        bookingQueue.addRequest(new Reservation("Chris", "Suite Room"));
        bookingQueue.addRequest(new Reservation("Alex", "Double Room"));

        // Display booking queue
        bookingQueue.displayQueue();

        // Display available rooms (read-only)
        List<Room> availableRooms = searchService.getAvailableRooms(rooms);
        searchService.displayAvailableRooms(availableRooms);
    }
}