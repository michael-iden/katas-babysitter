package miden.kata;

public class EndsTooLateException extends InvalidBabysitterConstraintsException {

    public EndsTooLateException() {
        this("Babysitting gig ends after allowed time");
    }

    public EndsTooLateException(String message) {
        super(message);
    }
}
