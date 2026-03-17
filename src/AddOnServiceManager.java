import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class AddOnServiceManager {

    // Map: Reservation ID -> List of Add-On Services
    private Map<String, List<AddOnService>> reservationServices;

    public AddOnServiceManager() {
        reservationServices = new HashMap<>();
    }

    // Attach service to a reservation
    public void addService(String reservationID, AddOnService service) {
        reservationServices.putIfAbsent(reservationID, new ArrayList<>());
        reservationServices.get(reservationID).add(service);
        System.out.println("Added " + service.getServiceName() + " to " + reservationID);
    }

    // Get total cost of add-ons
    public double getTotalAddOnCost(String reservationID) {
        double total = 0;
        List<AddOnService> services = reservationServices.get(reservationID);
        if (services != null) {
            for (AddOnService service : services) {
                total += service.getPrice();
            }
        }
        return total;
    }

    // Display all add-ons for a reservation
    public void displayAddOns(String reservationID) {
        System.out.println("\n--- Add-On Services for " + reservationID + " ---");
        List<AddOnService> services = reservationServices.get(reservationID);
        if (services != null) {
            for (AddOnService service : services) {
                service.displayService();
            }
            System.out.println("Total Add-On Cost: ₹" + getTotalAddOnCost(reservationID));
        } else {
            System.out.println("No add-on services selected.");
        }
    }
}