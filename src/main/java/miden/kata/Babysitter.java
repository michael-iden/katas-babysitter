package miden.kata;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class Babysitter {

    //5:00 PM
    private static final LocalTime EARLIEST_START_TIME = LocalTime.of(17, 0);

    //4:00 AM
    private static final LocalTime LATEST_END_TIME = LocalTime.of(4, 0);

    private LocalTime startTime;
    private LocalTime endTime;
    private LocalTime bedTime;

    public Babysitter(LocalTime startTime, LocalTime endTime, LocalTime bedTime) throws StartsTooEarlyException, EndsTooLateException {

        setStartTime(startTime);
        setEndTime(endTime);
        setBedTime(bedTime);
    }

    public int getCharge() {
        return -1;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public LocalTime getBedTime() {
        return bedTime;
    }

    public void setStartTime(LocalTime startTime) throws StartsTooEarlyException {
        if(startTime.isBefore(EARLIEST_START_TIME) && startTime.isAfter(LATEST_END_TIME)) {
            throw new StartsTooEarlyException();
        }

        this.startTime = roundDownToCurrentHour(startTime);
    }

    public void setEndTime(LocalTime endTime) throws EndsTooLateException {
        if (endTime.isAfter(LATEST_END_TIME) && endTime.isBefore(EARLIEST_START_TIME)) {
            throw new EndsTooLateException();
        }

        this.endTime = roundUpToNextHour(endTime);
    }

    public void setBedTime(LocalTime bedTime) {
        this.bedTime = roundUpToNextHour(bedTime);
    }

    private LocalTime roundUpToNextHour(LocalTime time) {

        LocalTime roundedUpTime;
        LocalTime roundedDownTime = roundDownToCurrentHour(time);
        if(roundedDownTime.equals(time)) {
            roundedUpTime = roundedDownTime;
        } else {
            roundedUpTime = roundedDownTime.plusHours(1);
        }

        return roundedUpTime;
    }

    private LocalTime roundDownToCurrentHour(LocalTime time) {
        return time.truncatedTo(ChronoUnit.HOURS);
    }



}
