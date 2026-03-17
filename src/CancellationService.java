import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;

public class CancellationService {

    private RoomInventory inventory;
    private HashMap<String, HashSet<String>> allocatedRooms; // roomType -> Set of allocated room IDs
    private Stack<String> rollbackStack; // Tracks recently released room IDs

    public CancellationService(RoomInventory inventory, HashMap<String, HashSet<String>> allocatedRooms) {
        this.inventory = inventory;
        this.allocatedRooms = allocatedRooms;
        this.rollbackStack = new Stack<>();
    }

    // Cancel a confirmed reservation
    public void cancelReservation(Reservation reservation) {
        String roomType = reservation.getRoomType();
        String roomID = reservation.getRoomID();

        if (roomID == null || roomID.isEmpty()) {
            System.out.println("Cancellation failed: No room allocated for " + reservation.getGuestName());
            return;
        }

        if (!allocatedRooms.containsKey(roomType) || !allocatedRooms.get(roomType).contains(roomID)) {
            System.out.println("Cancellation failed: Room ID " + roomID + " not found for type " + roomType);
            return;
        }

        // Perform rollback
        allocatedRooms.get(roomType).remove(roomID);
        rollbackStack.push(roomID); // Track released room ID

        // Restore inventory
        inventory.incrementAvailability(roomType);

        System.out.println("Reservation cancelled for " + reservation.getGuestName() +
                " | Room Type: " + roomType +
                " | Room ID: " + roomID);
    }

    // Optionally, view recently released rooms
    public void showRollbackHistory() {
        System.out.println("Recently released Room IDs: " + rollbackStack);
    }
}