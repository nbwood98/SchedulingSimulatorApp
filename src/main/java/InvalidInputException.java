public class InvalidInputException extends Exception {

    public InvalidInputException(String errorMessage) {
        super(errorMessage);
    }

    public InvalidInputException(String errorMessage, Throwable cause) {
        super(errorMessage, cause);
    }
}
