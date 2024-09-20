package carter;

/**
 * Represents an exception specific to the Carter application.
 */
public class CarterException extends Exception {

    /**
     * Constructs a new CarterException with the specified error message.
     *
     * @param message The detail message explaining the cause of the exception.
     */
    public CarterException(String message) {
        super(message);
    }
}
