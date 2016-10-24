package miden.kata;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalTime;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BabysitterTest {

    private Babysitter babysitter;
    private BabysitterSchedule mockBabysitterSchedule;

    @Before
    public void setUp() {
        babysitter = new Babysitter();
        mockBabysitterSchedule = mock(BabysitterSchedule.class);
    }

    @Test
    public void chargeOfOneHourBeforeMidnightBeforeBedtimeEquals12() throws InvalidBabysitterConstraintsException {

        when(mockBabysitterSchedule.getHoursWorkedBeforeMidnightBeforeBedtime()).thenReturn(1);
        when(mockBabysitterSchedule.getHoursWorkedAfterMidnight()).thenReturn(0);

        assertEquals(12, babysitter.getCharge(mockBabysitterSchedule));
    }

    @Test
    public void chargeFor2HoursBeforeMidnightBeforeBedtimeEquals24() throws InvalidBabysitterConstraintsException {

        when(mockBabysitterSchedule.getHoursWorkedBeforeMidnightBeforeBedtime()).thenReturn(2);
        when(mockBabysitterSchedule.getHoursWorkedAfterMidnight()).thenReturn(0);

        assertEquals(24, babysitter.getCharge(mockBabysitterSchedule));
    }

    @Test
    public void chargeFor4HoursBeforeMidnightBeforeBedtimeEquals48() throws InvalidBabysitterConstraintsException {

        when(mockBabysitterSchedule.getHoursWorkedBeforeMidnightBeforeBedtime()).thenReturn(4);
        when(mockBabysitterSchedule.getHoursWorkedAfterMidnight()).thenReturn(0);

        assertEquals(48, babysitter.getCharge(mockBabysitterSchedule));
    }

    @Test
    public void chargeFor2HoursBeforeMidnightAnd2HoursAfterMidnightBeforeBedtimeEquals56() throws InvalidBabysitterConstraintsException {

        when(mockBabysitterSchedule.getHoursWorkedBeforeMidnightBeforeBedtime()).thenReturn(2);
        when(mockBabysitterSchedule.getHoursWorkedAfterMidnight()).thenReturn(2);

        assertEquals(56, babysitter.getCharge(mockBabysitterSchedule));
    }

    @Test
    public void chargeFor2HoursBeforeMidnightBeforeBedtimeAnd1HourBeforeMidnightAfterBedtimeAnd2HoursAfterMidnightBeforeBedtimeEquals64() throws InvalidBabysitterConstraintsException {

        when(mockBabysitterSchedule.getHoursWorkedBeforeMidnightBeforeBedtime()).thenReturn(2);
        when(mockBabysitterSchedule.getHoursWorkedBeforeMidnightAfterBedtime()).thenReturn(1);
        when(mockBabysitterSchedule.getHoursWorkedAfterMidnight()).thenReturn(2);

        assertEquals(64, babysitter.getCharge(mockBabysitterSchedule));
    }

    @Test
    public void chargeForStartingAt5PMLeavingAt1AMWithBedtimeAt10PMIs92() throws InvalidBabysitterConstraintsException {
        BabysitterSchedule babysitterSchedule = new BabysitterSchedule(LocalTime.parse("17:00:00"), LocalTime.parse("01:00:00"), LocalTime.parse("22:00:00"));
        assertEquals(92, babysitter.getCharge(babysitterSchedule));
    }

    @Test
    public void chargeForStartingAtFiveThirtyPMLeavingAtTwelveFiftyFiveAMWithBedtimeAtNineOhOnePMIs92() throws InvalidBabysitterConstraintsException {
        BabysitterSchedule babysitterSchedule = new BabysitterSchedule(LocalTime.parse("17:30:00"), LocalTime.parse("00:55:00"), LocalTime.parse("21:01:00"));
        assertEquals(92, babysitter.getCharge(babysitterSchedule));
    }

    @Test
    public void chargeForStartingAt7PMLeavingAt10PMWithBedtimeAt11PMIs36() throws InvalidBabysitterConstraintsException {
        BabysitterSchedule babysitterSchedule = new BabysitterSchedule(LocalTime.parse("19:00:00"), LocalTime.parse("22:00:00"), LocalTime.parse("23:00:00"));
        assertEquals(36, babysitter.getCharge(babysitterSchedule));
    }

    @Test
    public void chargeForStartingAt1AMLeavingAt3AMWithBedtimeAt9PMIs32() throws InvalidBabysitterConstraintsException {
        BabysitterSchedule babysitterSchedule = new BabysitterSchedule(LocalTime.parse("01:00:00"), LocalTime.parse("03:00:00"), LocalTime.parse("21:00:00"));
        assertEquals(32, babysitter.getCharge(babysitterSchedule));
    }

    @Test
    public void chargeForStartingAt10PMLeavingAt3AMWithBedtimeAt9PMIs64() throws InvalidBabysitterConstraintsException {
        BabysitterSchedule babysitterSchedule = new BabysitterSchedule(LocalTime.parse("22:00:00"), LocalTime.parse("03:00:00"), LocalTime.parse("21:00:00"));
        assertEquals(64, babysitter.getCharge(babysitterSchedule));
    }

    @Test
    public void chargeForStartingAt10PMLeavingAtMidnightWithBedtimeAt9PMIs16() throws InvalidBabysitterConstraintsException {
        BabysitterSchedule babysitterSchedule = new BabysitterSchedule(LocalTime.parse("22:00:00"), LocalTime.MIDNIGHT, LocalTime.parse("21:00:00"));
        assertEquals(16, babysitter.getCharge(babysitterSchedule));
    }

    @Test
    public void chargeForStartingAt9PMLeavingAt11PMWithBedtimeAt10PMIs20() throws InvalidBabysitterConstraintsException {
        BabysitterSchedule babysitterSchedule = new BabysitterSchedule(LocalTime.parse("21:00:00"), LocalTime.parse("23:00:00"), LocalTime.parse("22:00:00"));
        assertEquals(20, babysitter.getCharge(babysitterSchedule));
    }

}
