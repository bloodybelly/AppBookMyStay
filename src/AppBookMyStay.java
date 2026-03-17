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

        // Get available rooms
        List<Room> availableRooms = searchService.getAvailableRooms(rooms);

        // Display
        searchService.displayAvailableRooms(availableRooms);
    }
}