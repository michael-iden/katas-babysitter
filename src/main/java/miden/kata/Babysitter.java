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

        setStartTime(startTime);
        this.endTime = endTime;
        setBedTime(bedTime);
    }

    public int getCharge() {
        return -1;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = roundDownToCurrentHour(startTime);
    }

    public void setBedTime(LocalTime bedTime) {
        this.bedTime = roundUpToNextHour(bedTime);
    }

    private LocalTime roundUpToNextHour(LocalTime time) {
        return time.truncatedTo(ChronoUnit.HOURS).plusHours(1);
    }

    private LocalTime roundDownToCurrentHour(LocalTime time) {
        return time.truncatedTo(ChronoUnit.HOURS);
    }



}
