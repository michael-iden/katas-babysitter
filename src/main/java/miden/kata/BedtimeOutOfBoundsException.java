package miden.kata;

public class BedtimeOutOfBoundsException extends InvalidBabysitterConstraintsException {

    public BedtimeOutOfBoundsException() {
        this("Bedtime not at reasonable hour");
    }

    public BedtimeOutOfBoundsException(String message) {
        super(message);
    }
}
