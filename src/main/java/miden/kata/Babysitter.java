package miden.kata;

import lombok.Getter;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

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

        this.startTime = startTime;
        this.endTime = endTime;
        this.bedTime = bedTime;
    }

    public int getCharge() {
        return -1;
    }

    public LocalTime getRoundedBedTime() {
       return roundUpToNextHour(bedTime);
    }

    private LocalTime roundUpToNextHour(LocalTime time) {
        return time.truncatedTo(ChronoUnit.HOURS).plusHours(1);
    }

    private LocalTime roundDownToCurrentHour(LocalTime time) {
        return time.truncatedTo(ChronoUnit.HOURS);
    }



}
