// Add this method inside RoomInventory class
public void incrementAvailability(String roomType) {
    int avail = getAvailability(roomType);
    availability.put(roomType, avail + 1);
}