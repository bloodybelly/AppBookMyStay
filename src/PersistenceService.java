import java.io.*;
import java.util.List;
import java.util.Map;

public class PersistenceService {

    private static final String FILE_NAME = "system_state.dat";

    // Save booking history and inventory state
    public static void saveState(List<Reservation> bookingHistory, Map<String, Integer> inventory) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(bookingHistory);
            oos.writeObject(inventory);
            System.out.println("System state saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving system state: " + e.getMessage());
        }
    }

    // Load booking history and inventory state
    @SuppressWarnings("unchecked")
    public static void loadState(List<Reservation> bookingHistory, Map<String, Integer> inventory) {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            System.out.println("No previous state found, starting fresh.");
            return;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            List<Reservation> loadedHistory = (List<Reservation>) ois.readObject();
            Map<String, Integer> loadedInventory = (Map<String, Integer>) ois.readObject();

            bookingHistory.clear();
            bookingHistory.addAll(loadedHistory);

            inventory.clear();
            inventory.putAll(loadedInventory);

            System.out.println("System state loaded successfully.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading system state: " + e.getMessage());
        }
    }
}