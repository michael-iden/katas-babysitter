package miden.kata;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalTime;

import static org.junit.Assert.assertEquals;

public class BabysitterTest {

    private Babysitter babysitter;

    @Before
    public void setUp() {
        babysitter = new Babysitter();
    }

    @Test
    public void cannotStartBefore5PM() {
        assertEquals(-1, babysitter.getCharge(LocalTime.parse("16:59:59"), LocalTime.MAX, LocalTime.MAX));
    }

    @Test
    public void cannotEndAfter4AM() {
        assertEquals(1, 0);
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
    public void bedTimeRoundsDownToBeginningOfHour() {
        assertEquals(1, 0);
    }

}
