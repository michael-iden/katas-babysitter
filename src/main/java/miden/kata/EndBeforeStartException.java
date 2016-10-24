package miden.kata;

public class EndBeforeStartException extends InvalidBabysitterConstraintsException {

    public EndBeforeStartException() {
        this("End time is before start time");
    }


    public EndBeforeStartException(String message) {
        super(message);
    }
}
