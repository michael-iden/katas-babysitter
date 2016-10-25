package miden.kata;

import org.junit.Test;

import java.time.LocalTime;

import static org.junit.Assert.assertEquals;

public class BabysitterScheduleTest {

    @Test(expected = EndBeforeStartException.class)
    public void startingAtOneAMAndEndingAtNinePMThrowsEndBeforeStartException() throws InvalidBabysitterConstraintsException {
        new BabysitterSchedule(LocalTime.parse("01:00:00"), LocalTime.parse("21:00:00"), LocalTime.MAX);
    }

    @Test(expected = EndBeforeStartException.class)
    public void startingAtOneAMAndEndingAtLocalTimeMaxThrowsEndBeforeStartException() throws InvalidBabysitterConstraintsException {
        new BabysitterSchedule(LocalTime.parse("01:00:00"), LocalTime.MAX, LocalTime.MAX);
    }

    @Test(expected = EndBeforeStartException.class)
    public void startingAtTwoAMAndEndingAtOneAMThrowsEndBeforeStartException() throws InvalidBabysitterConstraintsException {
        new BabysitterSchedule(LocalTime.parse("02:00:00"), LocalTime.parse("01:00:00"), LocalTime.MAX);
    }

    @Test(expected = EndBeforeStartException.class)
    public void startingAtTenPMAndEndingAtNinePMThrowsEndBeforeStartException() throws InvalidBabysitterConstraintsException {
        new BabysitterSchedule(LocalTime.parse("22:00:00"), LocalTime.parse("21:00:00"), LocalTime.MAX);
    }

    @Test(expected = StartsTooEarlyException.class)
    public void startingAtFourFiftyNineThrowsStartsTooEarlyException() throws InvalidBabysitterConstraintsException {
        new BabysitterSchedule(LocalTime.parse("16:59:59"), LocalTime.MAX, LocalTime.MAX);
    }

    @Test(expected = EndsTooLateException.class)
    public void endingAfterFourAMThrowsEndsTooLateException() throws InvalidBabysitterConstraintsException {
        new BabysitterSchedule(LocalTime.parse("17:00:00"), LocalTime.parse("04:00:01"), LocalTime.MAX);
    }

    @Test
    public void babysittingCanEndBeforeMidnight() throws InvalidBabysitterConstraintsException {
        new BabysitterSchedule(LocalTime.parse("17:00:00"), LocalTime.parse("23:15:00"), LocalTime.parse("23:00:00"));
    }

    @Test
    public void babysittingCanStartAfterMidnight() throws InvalidBabysitterConstraintsException {
        new BabysitterSchedule(LocalTime.parse("01:00:00"), LocalTime.parse("02:00:00"), LocalTime.parse("23:00:00"));
    }

    @Test
    public void startTimeOfSixFiftyNinePMRoundsUpToSixPM() throws InvalidBabysitterConstraintsException {
        BabysitterSchedule babysitterSchedule = new BabysitterSchedule(LocalTime.parse("18:59:00"), LocalTime.parse("03:00:00"), LocalTime.parse("22:30:00"));
        assertEquals(LocalTime.parse("18:00:00"), babysitterSchedule.getStartTime());
    }

    @Test
    public void startTimeOfEightElevenPMRoundsToEightPM() throws InvalidBabysitterConstraintsException {
        BabysitterSchedule babysitterSchedule = new BabysitterSchedule(LocalTime.parse("19:11:00"), LocalTime.parse("03:00:00"), LocalTime.parse("23:30:00"));
        assertEquals(LocalTime.parse("19:00:00"), babysitterSchedule.getStartTime());
    }

    @Test
    public void endTimeOfElevenFifteenPMRoundsUpToMidnight() throws InvalidBabysitterConstraintsException {
        BabysitterSchedule babysitterSchedule = new BabysitterSchedule(LocalTime.parse("18:59:00"), LocalTime.parse("23:15:00"), LocalTime.parse("22:30:00"));
        assertEquals(LocalTime.MIDNIGHT, babysitterSchedule.getEndTime());
    }

    @Test
    public void endTimeOfOneAMStaysAtOneAM() throws InvalidBabysitterConstraintsException {
        BabysitterSchedule babysitterSchedule = new BabysitterSchedule(LocalTime.parse("19:59:00"), LocalTime.parse("01:00:00"), LocalTime.parse("23:30:00"));
        assertEquals(LocalTime.parse("01:00:00"), babysitterSchedule.getEndTime());
    }

    @Test
    public void bedTimeOfTenThirtyPMRoundsUpToEleven() throws InvalidBabysitterConstraintsException {
        BabysitterSchedule babysitterSchedule = new BabysitterSchedule(LocalTime.parse("17:00:00"), LocalTime.parse("03:00:00"), LocalTime.parse("22:30:00"));
        assertEquals(LocalTime.parse("23:00:00"), babysitterSchedule.getBedTime());
    }

    @Test
    public void bedTimeOfElevenThirtyPMRoundsToMidnight() throws InvalidBabysitterConstraintsException {
        BabysitterSchedule babysitterSchedule = new BabysitterSchedule(LocalTime.parse("17:00:00"), LocalTime.parse("03:00:00"), LocalTime.parse("23:30:00"));
        assertEquals(LocalTime.MIDNIGHT, babysitterSchedule.getBedTime());
    }

    @Test
    public void getHoursWorkedBeforeMidnightBeforeBedTimeStartingAt9SleepAt11AndLeavingAtMidnight() throws InvalidBabysitterConstraintsException {
        BabysitterSchedule babysitterSchedule = new BabysitterSchedule(LocalTime.parse("21:00:00"), LocalTime.MIDNIGHT, LocalTime.parse("23:00:00"));
        assertEquals(2, babysitterSchedule.getHoursWorkedBeforeMidnightBeforeBedtime());
    }

    @Test
    public void getHoursWorkedBeforeMidnightBeforeBedTimeStartingAt7SleepAt10AndLeavingAt1AM() throws InvalidBabysitterConstraintsException {
        BabysitterSchedule babysitterSchedule = new BabysitterSchedule(LocalTime.parse("19:00:00"), LocalTime.parse("01:00:00"), LocalTime.parse("22:00:00"));
        assertEquals(3, babysitterSchedule.getHoursWorkedBeforeMidnightBeforeBedtime());
    }

    @Test
    public void getHoursWorkedBeforeMidnightBeforeBedTimeStartingAt7SleepAt10AndLeavingAt11PM() throws InvalidBabysitterConstraintsException {
        BabysitterSchedule babysitterSchedule = new BabysitterSchedule(LocalTime.parse("19:00:00"), LocalTime.parse("23:00:00"), LocalTime.parse("22:00:00"));
        assertEquals(3, babysitterSchedule.getHoursWorkedBeforeMidnightBeforeBedtime());
    }

    @Test
    public void getHoursWorkedBeforeMidnightBeforeBedTimeStartingAt7SleepAt11AndLeavingAt10PM() throws InvalidBabysitterConstraintsException {
        BabysitterSchedule babysitterSchedule = new BabysitterSchedule(LocalTime.parse("19:00:00"), LocalTime.parse("22:00:00"), LocalTime.parse("23:00:00"));
        assertEquals(3, babysitterSchedule.getHoursWorkedBeforeMidnightBeforeBedtime());
    }

    @Test
    public void getHoursWorkedBeforeMidnightBeforeBedTimeStartingAt1AMSleepAt2AMAndLeavingAt3AM() throws InvalidBabysitterConstraintsException {
        BabysitterSchedule babysitterSchedule = new BabysitterSchedule(LocalTime.parse("01:00:00"), LocalTime.parse("03:00:00"), LocalTime.parse("02:00:00"));
        assertEquals(0, babysitterSchedule.getHoursWorkedBeforeMidnightBeforeBedtime());
    }

    @Test
    public void getHoursWorkedBeforeMidnightBeforeBedTimeStartingAt10PMSleepAt9PMAndLeavingAt3AM() throws InvalidBabysitterConstraintsException {
        BabysitterSchedule babysitterSchedule = new BabysitterSchedule(LocalTime.parse("22:00:00"), LocalTime.parse("03:00:00"), LocalTime.parse("21:00:00"));
        assertEquals(0, babysitterSchedule.getHoursWorkedBeforeMidnightBeforeBedtime());
    }

    @Test
    public void getHoursWorkedBeforeMidnightAfterBedTimeStartingAt9SleepAt11AndLeavingAtMidnight() throws InvalidBabysitterConstraintsException {
        BabysitterSchedule babysitterSchedule = new BabysitterSchedule(LocalTime.parse("21:00:00"), LocalTime.MIDNIGHT, LocalTime.parse("23:00:00"));
        assertEquals(1, babysitterSchedule.getHoursWorkedBeforeMidnightAfterBedtime());
    }

    @Test
    public void getHoursWorkedBeforeMidnightAfterBedTimeStartingAt7SleepAt10AndLeavingAt1AM() throws InvalidBabysitterConstraintsException {
        BabysitterSchedule babysitterSchedule = new BabysitterSchedule(LocalTime.parse("19:00:00"), LocalTime.parse("01:00:00"), LocalTime.parse("22:00:00"));
        assertEquals(2, babysitterSchedule.getHoursWorkedBeforeMidnightAfterBedtime());
    }

    @Test
    public void getHoursWorkedBeforeMidnightAfterBedTimeStartingAt7SleepAt10AndLeavingAt11PM() throws InvalidBabysitterConstraintsException {
        BabysitterSchedule babysitterSchedule = new BabysitterSchedule(LocalTime.parse("19:00:00"), LocalTime.parse("23:00:00"), LocalTime.parse("22:00:00"));
        assertEquals(1, babysitterSchedule.getHoursWorkedBeforeMidnightAfterBedtime());
    }

    @Test
    public void getHoursWorkedBeforeMidnightAfterBedTimeStartingAt7SleepAt11AndLeavingAt10PM() throws InvalidBabysitterConstraintsException {
        BabysitterSchedule babysitterSchedule = new BabysitterSchedule(LocalTime.parse("19:00:00"), LocalTime.parse("22:00:00"), LocalTime.parse("23:00:00"));
        assertEquals(0, babysitterSchedule.getHoursWorkedBeforeMidnightAfterBedtime());
    }

    @Test
    public void getHoursWorkedBeforeMidnightAfterBedTimeStartingAt1AMSleepAt2AMAndLeavingAt3AM() throws InvalidBabysitterConstraintsException {
        BabysitterSchedule babysitterSchedule = new BabysitterSchedule(LocalTime.parse("01:00:00"), LocalTime.parse("03:00:00"), LocalTime.parse("02:00:00"));
        assertEquals(0, babysitterSchedule.getHoursWorkedBeforeMidnightAfterBedtime());
    }

    @Test
    public void getHoursWorkedBeforeMidnightAfterBedTimeStartingAt10PMSleepAt9PMAndLeavingAt3AM() throws InvalidBabysitterConstraintsException {
        BabysitterSchedule babysitterSchedule = new BabysitterSchedule(LocalTime.parse("22:00:00"), LocalTime.parse("03:00:00"), LocalTime.parse("21:00:00"));
        assertEquals(2, babysitterSchedule.getHoursWorkedBeforeMidnightAfterBedtime());
    }

    @Test
    public void getHoursWorkedBeforeMidnightAfterBedTimeStartingAt10PMSleepAt9PMAndLeavingAt11PM() throws InvalidBabysitterConstraintsException {
        BabysitterSchedule babysitterSchedule = new BabysitterSchedule(LocalTime.parse("22:00:00"), LocalTime.parse("23:00:00"), LocalTime.parse("21:00:00"));
        assertEquals(1, babysitterSchedule.getHoursWorkedBeforeMidnightAfterBedtime());
    }

    @Test
    public void getHoursWorkedAfterMidnightStartingAt1AMSleepAt2AMAndLeavingAt3AM() throws InvalidBabysitterConstraintsException {
        BabysitterSchedule babysitterSchedule = new BabysitterSchedule(LocalTime.parse("01:00:00"), LocalTime.parse("03:00:00"), LocalTime.parse("02:00:00"));
        assertEquals(2, babysitterSchedule.getHoursWorkedAfterMidnight());
    }

    @Test
    public void getHoursWorkedAfterMidnightStartingAt5PMSleepAt7PMAndLeavingAt4AM() throws InvalidBabysitterConstraintsException {
        BabysitterSchedule babysitterSchedule = new BabysitterSchedule(LocalTime.parse("17:00:00"), LocalTime.parse("04:00:00"), LocalTime.parse("19:00:00"));
        assertEquals(4, babysitterSchedule.getHoursWorkedAfterMidnight());
    }

    @Test
    public void getHoursWorkedAfterMidnightStartingAt5PMSleepAt7PMAndLeavingAt9PM() throws InvalidBabysitterConstraintsException {
        BabysitterSchedule babysitterSchedule = new BabysitterSchedule(LocalTime.parse("17:00:00"), LocalTime.parse("21:00:00"), LocalTime.parse("19:00:00"));
        assertEquals(0, babysitterSchedule.getHoursWorkedAfterMidnight());
    }

    @Test(expected = NullTimeException.class)
    public void passingInNullStartTimeExplodesGracefully() throws InvalidBabysitterConstraintsException {
        new BabysitterSchedule(null, LocalTime.parse("21:00:00"), LocalTime.parse("19:00:00"));
    }

    @Test(expected = NullTimeException.class)
    public void passingInNullEndTimeExplodesGracefully() throws InvalidBabysitterConstraintsException {
        new BabysitterSchedule(LocalTime.parse("21:00:00"), null, LocalTime.parse("19:00:00"));
    }

    @Test(expected = NullTimeException.class)
    public void passingInNullBedTimeExplodesGracefully() throws InvalidBabysitterConstraintsException {
        new BabysitterSchedule(LocalTime.parse("21:00:00"), LocalTime.parse("21:00:00"), null);
    }
}
