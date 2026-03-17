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
        bookingQueue.addRequest(new Reservation("Luna", "Single Room"));

        // Booking service
        BookingService bookingService = new BookingService(inventory);

        // Process queue
        while (!bookingQueue.isEmpty()) {
            Reservation res = bookingQueue.getNextRequest();
            bookingService.confirmReservation(res);
        }

        // Show allocations
        bookingService.displayAllocations();

        // Show updated inventory
        inventory.displayInventory();
    }
}