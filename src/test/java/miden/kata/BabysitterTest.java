package miden.kata;

import org.junit.Before;
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
    public void bedTimeRoundsUpToEndOfHour() {
        assertEquals(1, 0);
    }

}
