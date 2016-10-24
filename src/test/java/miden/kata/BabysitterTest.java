package miden.kata;

import org.junit.Test;

import java.time.LocalTime;

import static org.junit.Assert.assertEquals;

public class BabysitterTest {

    @Test(expected = StartsTooEarlyException.class)
    public void babySittingcannotStartBefore5PM() throws StartsTooEarlyException, EndsTooLateException {
        new Babysitter(LocalTime.parse("16:59:59"), LocalTime.MAX, LocalTime.MAX);
    }

    @Test(expected = EndsTooLateException.class)
    public void babySittingCannotEndAfter4AM() throws StartsTooEarlyException, EndsTooLateException {
        new Babysitter(LocalTime.parse("17:00:00"), LocalTime.parse("04:00:01"), LocalTime.MAX);
    }

    @Test
    public void babysittingCanEndBeforeMidnight() throws StartsTooEarlyException, EndsTooLateException {
        new Babysitter(LocalTime.parse("17:00:00"), LocalTime.parse("23:15:00"), LocalTime.parse("23:00:00"));
    }

    @Test
    public void babysittingCanStartAfterMidnight() throws StartsTooEarlyException, EndsTooLateException {
        new Babysitter(LocalTime.parse("01:00:00"), LocalTime.parse("02:00:00"), LocalTime.parse("23:00:00"));
    }

    @Test
    public void bedtimeAfterMidnightGetsHigherRate() {
        assertEquals(1, 0);
    }

    @Test
    public void startTimeOfSixFiftyNinePMRoundsUpToSixPM() throws StartsTooEarlyException, EndsTooLateException {
        Babysitter babysitter = new Babysitter(LocalTime.parse("18:59:00"), LocalTime.parse("03:00:00"), LocalTime.parse("22:30:00"));
        assertEquals(LocalTime.parse("18:00:00"), babysitter.getStartTime());
    }

    @Test
    public void startTimeOfEightElevenPMRoundsToEightPM() throws StartsTooEarlyException, EndsTooLateException {
        Babysitter babysitter = new Babysitter(LocalTime.parse("19:11:00"), LocalTime.parse("03:00:00"), LocalTime.parse("23:30:00"));
        assertEquals(LocalTime.parse("19:00:00"), babysitter.getStartTime());
    }

    @Test
    public void endTimeOfElevenFifteenPMRoundsUpToMidnight() throws StartsTooEarlyException, EndsTooLateException {
        Babysitter babysitter = new Babysitter(LocalTime.parse("18:59:00"), LocalTime.parse("23:15:00"), LocalTime.parse("22:30:00"));
        assertEquals(LocalTime.MIDNIGHT, babysitter.getEndTime());
    }

    @Test
    public void endTimeOfOneAMStaysAtOneAM() throws StartsTooEarlyException, EndsTooLateException {
        Babysitter babysitter = new Babysitter(LocalTime.parse("19:59:00"), LocalTime.parse("01:00:00"), LocalTime.parse("23:30:00"));
        assertEquals(LocalTime.parse("01:00:00"), babysitter.getEndTime());
    }

    @Test
    public void bedTimeOfTenThirtyPMRoundsUpToEleven() throws StartsTooEarlyException, EndsTooLateException {
        Babysitter babysitter = new Babysitter(LocalTime.parse("17:00:00"), LocalTime.parse("03:00:00"), LocalTime.parse("22:30:00"));
        assertEquals(LocalTime.parse("23:00:00"), babysitter.getBedTime());
    }

    @Test
    public void bedTimeOfElevenThirtyPMRoundsToMidnight() throws StartsTooEarlyException, EndsTooLateException {
        Babysitter babysitter = new Babysitter(LocalTime.parse("17:00:00"), LocalTime.parse("03:00:00"), LocalTime.parse("23:30:00"));
        assertEquals(LocalTime.MIDNIGHT, babysitter.getBedTime());
    }

}
