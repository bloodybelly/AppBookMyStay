public class AppBookMyStay {

    public static void main(String[] args) {

        System.out.println("===== Welcome to BookMyStay =====");

        RoomInventory inventory = new RoomInventory();
        HashMap<String, HashSet<String>> allocatedRooms = new HashMap<>();
        BookingService bookingService = new BookingService(inventory);
        CancellationService cancellationService = new CancellationService(inventory, allocatedRooms);

        // Example bookings
        Reservation r1 = new Reservation("Isai", "Single Room");
        bookingService.confirmReservation(r1);

        Reservation r2 = new Reservation("Alex", "Suite Room");
        bookingService.confirmReservation(r2);

        // Example cancellation
        cancellationService.cancelReservation(r1);

        // Show rollback history
        cancellationService.showRollbackHistory();
    }
}