import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

class BookingService {

    private RoomInventory inventory;
    private HashMap<String, Set<String>> allocatedRooms; // roomType -> Set of Room IDs

    public BookingService(RoomInventory inventory) {
        this.inventory = inventory;
        allocatedRooms = new HashMap<>();
    }

    // Confirm a reservation with validation
    public void confirmReservation(Reservation reservation) {
        try {
            validateReservation(reservation);

            // Generate unique room ID
            String roomID = generateRoomID(reservation.getRoomType());

            // Assign room ID
            reservation.setRoomID(roomID);

            // Update allocated rooms map
            allocatedRooms.putIfAbsent(reservation.getRoomType(), new HashSet<>());
            allocatedRooms.get(reservation.getRoomType()).add(roomID);

            // Decrement inventory
            inventory.decrementAvailability(reservation.getRoomType());

            System.out.println("Reservation confirmed for " + reservation.getGuestName() +
                    " | Room Type: " + reservation.getRoomType() +
                    " | Room ID: " + reservation.getRoomID());

        } catch (InvalidBookingException e) {
            System.out.println("Booking failed for " + reservation.getGuestName() + ": " + e.getMessage());
        }
    }

    // Validate reservation before processing
    private void validateReservation(Reservation reservation) throws InvalidBookingException {
        String roomType = reservation.getRoomType();

        if (!inventory.isValidRoomType(roomType)) {
            throw new InvalidBookingException("Invalid room type: " + roomType);
        }

        if (inventory.getAvailability(roomType) <= 0) {
            throw new InvalidBookingException("No availability for room type: " + roomType);
        }

        if (reservation.getGuestName() == null || reservation.getGuestName().isEmpty()) {
            throw new InvalidBookingException("Guest name cannot be empty");
        }
    }

    // Simple unique room ID generator
    private String generateRoomID(String roomType) {
        char prefix = roomType.charAt(0); // e.g., Single Room -> 'S'
        int id = allocatedRooms.containsKey(roomType) ? allocatedRooms.get(roomType).size() + 1 : 1;
        return prefix + "I-" + id; // e.g., "SI-1"
    }
}