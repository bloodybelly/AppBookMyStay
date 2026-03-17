class BookingReportService {

    private BookingHistory history;

    public BookingReportService(BookingHistory history) {
        this.history = history;
    }

    // Display all confirmed bookings
    public void generateFullReport() {
        System.out.println("\n--- Booking History Report ---");
        for (Reservation res : history.getAllReservations()) {
            System.out.println(res.getGuestName() + " | " +
                    res.getRoomType() + " | " +
                    res.getRoomID());
        }
    }

    // Summary: number of bookings per room type
    public void generateSummary() {
        System.out.println("\n--- Booking Summary ---");
        var bookings = history.getAllReservations();
        var summary = new java.util.HashMap<String, Integer>();

        for (Reservation res : bookings) {
            summary.put(res.getRoomType(), summary.getOrDefault(res.getRoomType(), 0) + 1);
        }

        for (String roomType : summary.keySet()) {
            System.out.println(roomType + ": " + summary.get(roomType));
        }
    }
}