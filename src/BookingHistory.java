import java.util.ArrayList;
import java.util.List;

class BookingHistory {

    private List<Reservation> confirmedReservations;

    public BookingHistory() {
        confirmedReservations = new ArrayList<>();
    }

    // Add confirmed reservation to history
    public void addReservation(Reservation reservation) {
        confirmedReservations.add(reservation);
        System.out.println("Added to booking history: " + reservation.getGuestName() +
                " | " + reservation.getRoomType() + " | " + reservation.getRoomID());
    }

    // Retrieve all bookings
    public List<Reservation> getAllReservations() {
        return new ArrayList<>(confirmedReservations); // return a copy to avoid external modification
    }
}