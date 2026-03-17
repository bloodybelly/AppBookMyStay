import java.util.HashMap;
import java.util.HashSet;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ConcurrentBookingService {

    private RoomInventory inventory;
    private HashMap<String, HashSet<String>> allocatedRooms; // roomType -> allocated room IDs
    private Queue<Reservation> bookingQueue; // thread-safe queue

    public ConcurrentBookingService(RoomInventory inventory, HashMap<String, HashSet<String>> allocatedRooms) {
        this.inventory = inventory;
        this.allocatedRooms = allocatedRooms;
        this.bookingQueue = new ConcurrentLinkedQueue<>();
    }

    // Add a booking request to the queue
    public void submitBookingRequest(Reservation reservation) {
        bookingQueue.offer(reservation);
        System.out.println("Booking request submitted for " + reservation.getGuestName());
    }

    // Process booking requests concurrently
    public void processBookings() {
        Thread worker = new Thread(() -> {
            while (!bookingQueue.isEmpty()) {
                Reservation res = bookingQueue.poll();
                if (res != null) {
                    allocateRoom(res);
                }
            }
        });
        worker.start();
        try {
            worker.join(); // wait for processing to complete
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Synchronized allocation to prevent race conditions
    private synchronized void allocateRoom(Reservation reservation) {
        String roomType = reservation.getRoomType();
        int available = inventory.getAvailability(roomType);

        if (available > 0) {
            // Generate unique Room ID
            String roomID = roomType.substring(0, 2).toUpperCase() + "-" + (available);
            allocatedRooms.putIfAbsent(roomType, new HashSet<>());
            allocatedRooms.get(roomType).add(roomID);
            reservation.setRoomID(roomID);

            // Update inventory
            inventory.decrementAvailability(roomType);

            System.out.println("Reservation confirmed for " + reservation.getGuestName() +
                    " | Room Type: " + roomType +
                    " | Room ID: " + roomID);
        } else {
            System.out.println("No availability for " + roomType + " for " + reservation.getGuestName());
        }
    }
}