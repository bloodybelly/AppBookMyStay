import java.util.HashMap;

class RoomInventory {

    private HashMap<String, Integer> availability;

    public RoomInventory() {
        availability = new HashMap<>();
        availability.put("Single Room", 5);
        availability.put("Double Room", 3);
        availability.put("Suite Room", 2);
    }

    public boolean isValidRoomType(String roomType) {
        return availability.containsKey(roomType);
    }

    public int getAvailability(String roomType) {
        return availability.getOrDefault(roomType, 0);
    }

    public void decrementAvailability(String roomType) throws InvalidBookingException {
        int avail = getAvailability(roomType);
        if (avail <= 0) {
            throw new InvalidBookingException("Cannot decrement availability. No rooms left for: " + roomType);
        }
        availability.put(roomType, avail - 1);
    }
}