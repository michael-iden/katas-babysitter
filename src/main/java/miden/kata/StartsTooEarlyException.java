package miden.kata;

public class StartsTooEarlyException extends InvalidBabysitterConstraintsException {

    public StartsTooEarlyException() {
        this("Babysitting gig starts before allowed time");
    }

    public StartsTooEarlyException(String message) {
        super(message);
    }
}
