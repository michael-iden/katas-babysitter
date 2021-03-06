package miden.kata;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class BabysitterSchedule {

    //5:00 PM
    private static final LocalTime EARLIEST_START_TIME = LocalTime.of(17, 0);

    //4:00 AM
    private static final LocalTime LATEST_END_TIME = LocalTime.of(4, 0);
    private static final int MIDNIGHT_24 = 24;

    private LocalTime startTime;
    private LocalTime endTime;
    private LocalTime bedTime;

    public BabysitterSchedule(LocalTime startTime, LocalTime endTime, LocalTime bedTime) throws InvalidBabysitterConstraintsException {

        setStartTime(startTime);
        setEndTime(endTime);
        setBedTime(bedTime);
    }

    public int getHoursWorkedAfterMidnight() {

        int hoursWorkedAfterMidnight = 0;

        if (isAfterMidnight(endTime)) {
            if (isAfterMidnight(startTime)) {
                hoursWorkedAfterMidnight = Math.toIntExact(ChronoUnit.HOURS.between(startTime, endTime));
            } else {
                hoursWorkedAfterMidnight = Math.toIntExact(ChronoUnit.HOURS.between(LocalTime.MIDNIGHT, endTime));
            }
        }

        return hoursWorkedAfterMidnight;
    }

    public int getHoursWorkedBeforeMidnightBeforeBedtime() {
        int hoursWorkedBeforeMidnightBeforeBedtime = 0;

        if (!isAfterMidnight(startTime)) {
            if (isAfterMidnight(endTime) && isAfterMidnight(bedTime)) {
                hoursWorkedBeforeMidnightBeforeBedtime = MIDNIGHT_24 - startTime.getHour();
            } else if (bedTime.isAfter(startTime)) {
                if (bedTime.isBefore(endTime) || isAfterMidnight(endTime)) {
                    hoursWorkedBeforeMidnightBeforeBedtime = Math.toIntExact(ChronoUnit.HOURS.between(startTime, bedTime));
                } else if (bedTime.isAfter(endTime) && !isAfterMidnight(endTime)) {
                    hoursWorkedBeforeMidnightBeforeBedtime = Math.toIntExact(ChronoUnit.HOURS.between(startTime, endTime));
                }
            }
        }

        return hoursWorkedBeforeMidnightBeforeBedtime;
    }

    public int getHoursWorkedBeforeMidnightAfterBedtime() {
        int hoursWorkedBeforeMidnightAfterBedtime = 0;

        if (!isAfterMidnight(startTime)) {
            if (!isAfterMidnight(bedTime)) {
                if (isAfterMidnight(endTime) && bedTime.isAfter(startTime)) {
                    hoursWorkedBeforeMidnightAfterBedtime = MIDNIGHT_24 - bedTime.getHour();
                } else if (isAfterMidnight(endTime)) {
                    hoursWorkedBeforeMidnightAfterBedtime = MIDNIGHT_24 - startTime.getHour();
                } else if (bedTime.isBefore(endTime) && bedTime.isAfter(startTime)) {
                    hoursWorkedBeforeMidnightAfterBedtime = Math.toIntExact(ChronoUnit.HOURS.between(bedTime, endTime));
                } else if (bedTime.isBefore(endTime)) {
                    hoursWorkedBeforeMidnightAfterBedtime = Math.toIntExact(ChronoUnit.HOURS.between(startTime, endTime));
                }
            }
        }

        return hoursWorkedBeforeMidnightAfterBedtime;
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

    public void setStartTime(LocalTime startTime) throws InvalidBabysitterConstraintsException {
        if(startTime == null) {
            throw new NullTimeException("Cannot set start time to null");
        } else if (startTime.isBefore(EARLIEST_START_TIME) && startTime.isAfter(LATEST_END_TIME)) {
            throw new StartsTooEarlyException();
        }

        this.startTime = roundDownToCurrentHour(startTime);
        verifyStartEndTimeOrdering();
    }

    public void setEndTime(LocalTime endTime) throws InvalidBabysitterConstraintsException {
        if(endTime == null) {
            throw new NullTimeException("Cannot set end time to null");
        } else if (endTime.isAfter(LATEST_END_TIME) && endTime.isBefore(EARLIEST_START_TIME)) {
            throw new EndsTooLateException();
        }

        this.endTime = roundUpToNextHour(endTime);
        verifyStartEndTimeOrdering();
    }

    public void setBedTime(LocalTime bedTime) throws InvalidBabysitterConstraintsException {
        if(bedTime == null) {
            throw new NullTimeException("Cannot set bed time to null");
        } else if (bedTime.isAfter(LATEST_END_TIME) && bedTime.isBefore(EARLIEST_START_TIME)) {
            throw new BedtimeOutOfBoundsException();
        }

        this.bedTime = roundUpToNextHour(bedTime);
    }

    private boolean isAfterMidnight(LocalTime time) {
        if (!time.isAfter(LATEST_END_TIME)) {
            return true;
        }

        return false;
    }


    private LocalTime roundUpToNextHour(LocalTime time) {

        LocalTime roundedUpTime;
        LocalTime roundedDownTime = roundDownToCurrentHour(time);
        if (roundedDownTime.equals(time)) {
            roundedUpTime = roundedDownTime;
        } else {
            roundedUpTime = roundedDownTime.plusHours(1);
        }

        return roundedUpTime;
    }

    private LocalTime roundDownToCurrentHour(LocalTime time) {
        return time.truncatedTo(ChronoUnit.HOURS);
    }

    private void verifyStartEndTimeOrdering() throws EndBeforeStartException {

        if (startTime == null || endTime == null) {
            return;
        }

        if (startIsAfterMidnightAndEndIsBeforeMidnight()
                || startIsAfterEndWithBothBeforeMidnight()
                || startIsAfterEndWithBothAfterMidnight()) {
            throw new EndBeforeStartException();
        }
    }

    private boolean startIsAfterMidnightAndEndIsBeforeMidnight() {
        return startTime.isBefore(LATEST_END_TIME) && endTime.isAfter(EARLIEST_START_TIME) && endTime.isBefore(LocalTime.MAX);
    }

    private boolean startIsAfterEndWithBothBeforeMidnight() {
        return startTime.isAfter(endTime) && startTime.isAfter(EARLIEST_START_TIME) && endTime.isAfter(EARLIEST_START_TIME);
    }

    private boolean startIsAfterEndWithBothAfterMidnight() {
        return startTime.isAfter(endTime) && startTime.isBefore(LATEST_END_TIME) && endTime.isBefore(LATEST_END_TIME);
    }

}
