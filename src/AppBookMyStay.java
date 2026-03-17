public class AppBookMyStay {

    public static void main(String[] args) {

        System.out.println("===== Welcome to BookMyStay =====");

        // Inventory & Booking setup
        RoomInventory inventory = new RoomInventory();
        BookingRequestQueue bookingQueue = new BookingRequestQueue();
        BookingService bookingService = new BookingService(inventory);

        // Sample bookings
        bookingQueue.addRequest(new Reservation("Isai", "Single Room"));
        bookingQueue.addRequest(new Reservation("Chris", "Suite Room"));

        // Process reservations
        while (!bookingQueue.isEmpty()) {
            Reservation res = bookingQueue.getNextRequest();
            bookingService.confirmReservation(res);
        }

        // Add-On Service Manager
        AddOnServiceManager addOnManager = new AddOnServiceManager();

        // Sample services
        AddOnService breakfast = new AddOnService("Breakfast", 500);
        AddOnService spa = new AddOnService("Spa Session", 1500);
        AddOnService airport = new AddOnService("Airport Pickup", 700);

        // Map services to reservations
        addOnManager.addService("SI-5", breakfast);
        addOnManager.addService("SI-5", spa);
        addOnManager.addService("SU-2", airport);

        // Display add-ons
        addOnManager.displayAddOns("SI-5");
        addOnManager.displayAddOns("SU-2");
    }
}