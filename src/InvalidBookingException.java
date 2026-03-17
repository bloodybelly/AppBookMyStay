// InvalidBookingException.java
public class InvalidBookingException extends Exception {

    // Constructor that takes a message
    public InvalidBookingException(String message) {
        super(message); // Pass the message to the base Exception class
    }

    // Optional: Constructor that takes a message and a cause (another Throwable)
    public InvalidBookingException(String message, Throwable cause) {
        super(message, cause);
    }
}