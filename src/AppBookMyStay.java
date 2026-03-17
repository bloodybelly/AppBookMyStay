public class AppBookMyStay {

    public static void main(String[] args) {

        System.out.println("===== Welcome to BookMyStay =====");

        // Room objects
        Room single = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suite = new SuiteRoom();

        // Inventory (NEW)
        RoomInventory inventory = new RoomInventory();

        System.out.println("\n--- Room Details ---");

        single.displayDetails();
        System.out.println("Available: " + inventory.getAvailability("Single Room"));

        System.out.println();

        doubleRoom.displayDetails();
        System.out.println("Available: " + inventory.getAvailability("Double Room"));

        System.out.println();

        suite.displayDetails();
        System.out.println("Available: " + inventory.getAvailability("Suite Room"));

        // Show full inventory
        inventory.displayInventory();
    }
}