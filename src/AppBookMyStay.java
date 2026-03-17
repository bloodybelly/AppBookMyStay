public class AppBookMyStay {

    public static void main(String[] args) {

        System.out.println("===== Welcome to BookMyStay =====");

        // Create room objects
        Room single = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suite = new SuiteRoom();

        // Static availability
        int singleAvailable = 5;
        int doubleAvailable = 3;
        int suiteAvailable = 2;

        // Display details
        System.out.println("\n--- Room Details ---");

        single.displayDetails();
        System.out.println("Available: " + singleAvailable);

        System.out.println();

        doubleRoom.displayDetails();
        System.out.println("Available: " + doubleAvailable);

        System.out.println();

        suite.displayDetails();
        System.out.println("Available: " + suiteAvailable);
    }
}