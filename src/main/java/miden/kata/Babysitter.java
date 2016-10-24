package miden.kata;

import lombok.Data;
import lombok.Getter;

import java.time.LocalTime;

@Getter
public class Babysitter {

    //5:00 PM
    private static final LocalTime EARLIEST_START_TIME = LocalTime.of(17, 0);

    //4:00 AM
    private static final LocalTime LATEST_END_TIME = LocalTime.of(4, 0);

    private LocalTime startTime;
    private LocalTime endTime;
    private LocalTime bedTime;

    public Babysitter(LocalTime startTime, LocalTime endTime, LocalTime bedTime) throws StartsTooEarlyException, EndsTooLateException {
        if(startTime.isBefore(EARLIEST_START_TIME)) {
            throw new StartsTooEarlyException();
        } else if (endTime.isAfter(LATEST_END_TIME)) {
            throw new EndsTooLateException();
        }
    }

    //Returns -1 if constraints around the charge are not met
    public int getCharge(LocalTime startTime, LocalTime endTime, LocalTime bedTime) {

        return 2222;
    }



}
