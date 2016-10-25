package miden.kata;

public class NullTimeException extends InvalidBabysitterConstraintsException {

    public NullTimeException() {
        this("NullTimeWasReceived");
    }

    public NullTimeException(String message) {
        super(message);
    }
}
