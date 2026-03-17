import java.util.*;

public class AppBookMyStay {
    public static void main(String[] args) {

        // Initialize system state
        List<Reservation> bookingHistory = new ArrayList<>();
        Map<String, Integer> inventory = new HashMap<>();

        // Load persisted state
        PersistenceService.loadState(bookingHistory, inventory);

        // Application logic goes here
        System.out.println("Welcome to BookMyStay! System ready.");

        // ... your booking, search, and allocation logic ...

        // Save state on shutdown
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            PersistenceService.saveState(bookingHistory, inventory);
        }));
    }
}