package miden.kata;

import org.junit.Before;
import org.junit.Test;

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

}
