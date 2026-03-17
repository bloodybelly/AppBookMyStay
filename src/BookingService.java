import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

class BookingService {

    private RoomInventory inventory;

    // Map: Room Type -> Assigned Room IDs
    private HashMap<String, Set<String>> allocatedRooms;

    public BookingService(RoomInventory inventory) {
        this.inventory = inventory;
        allocatedRooms = new HashMap<>();
    }

    // Confirm reservation
    public void confirmReservation(Reservation reservation) {
        String roomType = reservation.getRoomType();

        int available = inventory.getAvailability(roomType);
        if (available <= 0) {
            System.out.println("Sorry " + reservation.getGuestName() + ", no " + roomType + " available.");
            return;
        }

        // Generate unique room ID
        String roomID = roomType.substring(0,2).toUpperCase() + "-" + (available);

        // Add to allocated set
        allocatedRooms.putIfAbsent(roomType, new HashSet<>());
        Set<String> roomSet = allocatedRooms.get(roomType);

        if (roomSet.contains(roomID)) {
            System.out.println("Error: Room ID already allocated!"); // unlikely but safe check
            return;
        }

        roomSet.add(roomID);

        // Update inventory
        inventory.updateAvailability(roomType, available - 1);

        System.out.println("Reservation confirmed for " + reservation.getGuestName() +
                " | Room Type: " + roomType +
                " | Room ID: " + roomID);
    }

    // Display all allocations
    public void displayAllocations() {
        System.out.println("\n--- Allocated Rooms ---");
        for (String roomType : allocatedRooms.keySet()) {
            System.out.println(roomType + ": " + allocatedRooms.get(roomType));
        }
    }
}