package miden.kata;

import org.junit.Test;

import java.time.LocalTime;

import static org.junit.Assert.assertEquals;

public class BabysitterTest {

    @Test(expected = StartsTooEarlyException.class)
    public void cannotStartBefore5PM() throws StartsTooEarlyException, EndsTooLateException {
        new Babysitter(LocalTime.parse("16:59:59"), LocalTime.MAX, LocalTime.MAX);
    }

    @Test(expected = EndsTooLateException.class)
    public void cannotEndAfter4AM() throws StartsTooEarlyException, EndsTooLateException {
        new Babysitter(LocalTime.parse("17:00:00"), LocalTime.parse("04:00:01"), LocalTime.MAX);
    }

    @Test
    public void bedtimeAfterMidnightGetsHigherRate() {
        assertEquals(1, 0);
    }

    @Test
    public void startTimeRoundsDownToStartOfHour() {
        assertEquals(1, 0);
    }

    @Test
    public void endTimeRoundsUpToEndOfHour() {
        assertEquals(1, 0);
    }

    @Test
    public void bedTimeOfTenThirtyPMRoundsUpToEleven() throws StartsTooEarlyException, EndsTooLateException {
        Babysitter babysitter = new Babysitter(LocalTime.parse("17:00:00"), LocalTime.parse("03:00:00"), LocalTime.parse("22:30:00"));
        assertEquals(LocalTime.parse("23:00:00"), babysitter.getRoundedBedTime());
    }

    @Test
    public void bedTimeOfElevenThirtyPMRoundsToMidnight() throws StartsTooEarlyException, EndsTooLateException {
        Babysitter babysitter = new Babysitter(LocalTime.parse("17:00:00"), LocalTime.parse("03:00:00"), LocalTime.parse("23:30:00"));
        assertEquals(LocalTime.MIDNIGHT, babysitter.getRoundedBedTime());
    }

}
