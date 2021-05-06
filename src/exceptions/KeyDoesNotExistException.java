package exceptions;

public class KeyDoesNotExistException extends Exception {
    String message;

    public KeyDoesNotExistException(String message) {
        super(message);
    }

}
