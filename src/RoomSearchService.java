import java.util.ArrayList;
import java.util.List;

class RoomSearchService {

    private RoomInventory inventory;

    // Constructor
    public RoomSearchService(RoomInventory inventory) {
        this.inventory = inventory;
    }

    // Search available rooms
    public List<Room> getAvailableRooms(List<Room> rooms) {
        List<Room> availableRooms = new ArrayList<>();

        for (Room room : rooms) {
            int count = inventory.getAvailability(room.roomType);
            if (count > 0) {
                availableRooms.add(room);
            }
        }

        return availableRooms;
    }

    // Display rooms
    public void displayAvailableRooms(List<Room> rooms) {
        System.out.println("\n--- Available Rooms ---");

        for (Room room : rooms) {
            room.displayDetails();
            System.out.println("Available: " + inventory.getAvailability(room.roomType));
            System.out.println();
        }
    }
}