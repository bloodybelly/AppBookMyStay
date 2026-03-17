import java.util.HashMap;
import java.util.HashSet;

public class AppBookMyStay {

    public static void main(String[] args) {

        System.out.println("===== Welcome to BookMyStay Concurrent Booking Demo =====");

        RoomInventory inventory = new RoomInventory();
        inventory.addRoomType("Single Room", 2);  // 2 available for demo
        inventory.addRoomType("Double Room", 1);

        HashMap<String, HashSet<String>> allocatedRooms = new HashMap<>();
        ConcurrentBookingService bookingService = new ConcurrentBookingService(inventory, allocatedRooms);

        // Multiple guests submitting requests at the same time
        Reservation r1 = new Reservation("Isai", "Single Room");
        Reservation r2 = new Reservation("Alex", "Single Room");
        Reservation r3 = new Reservation("Chris", "Single Room"); // will test no availability
        Reservation r4 = new Reservation("Luna", "Double Room");

        bookingService.submitBookingRequest(r1);
        bookingService.submitBookingRequest(r2);
        bookingService.submitBookingRequest(r3);
        bookingService.submitBookingRequest(r4);

        bookingService.processBookings(); // process all requests concurrently
    }
}