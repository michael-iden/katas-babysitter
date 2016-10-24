package miden.kata;

import java.time.LocalTime;

public class Babysitter {

    //5:00 PM
    private static final LocalTime EARLIEST_START_TIME = LocalTime.of(17, 0);

    //4:00 AM
    private static final LocalTime LATEST_END_TIME = LocalTime.of(4, 0);

    //Returns -1 if constraints around the charge are not met
    public int getCharge(LocalTime startTime, LocalTime endTime, LocalTime bedTime) {

        if(startTime.isBefore(EARLIEST_START_TIME)) {
            return -1;
        } else if (endTime.isAfter(LATEST_END_TIME)) {
            return -1;
        }

        return 2222;
    }



}
