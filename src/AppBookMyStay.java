public class AppBookMyStay {

    public static void main(String[] args) {

        System.out.println("===== Welcome to BookMyStay =====");

        RoomInventory inventory = new RoomInventory();
        BookingService bookingService = new BookingService(inventory);

        // Test bookings
        Reservation r1 = new Reservation("Isai", "Single Room"); // valid
        Reservation r2 = new Reservation("", "Double Room"); // invalid guest
        Reservation r3 = new Reservation("Chris", "Penthouse"); // invalid room type
        Reservation r4 = new Reservation("Alex", "Suite Room"); // valid

        bookingService.confirmReservation(r1);
        bookingService.confirmReservation(r2);
        bookingService.confirmReservation(r3);
        bookingService.confirmReservation(r4);
    }
}