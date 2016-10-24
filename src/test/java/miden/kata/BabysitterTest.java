package miden.kata;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BabysitterTest {

    @Before
    public void setUp() {

    }

    @Test
    public void cannotStartBefore5PM() {
        assertEquals(1, 0);
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
