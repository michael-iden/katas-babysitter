package miden.kata;

import org.junit.Test;

import java.time.LocalTime;

import static org.junit.Assert.assertEquals;

public class BabysitterTest {

    @Test(expected = EndBeforeStartException.class)
    public void startingAtOneAMAndEndingAtNinePMThrowsEndBeforeStartException() throws InvalidBabysitterConstraintsException {
        new Babysitter(LocalTime.parse("01:00:00"), LocalTime.parse("21:00:00"), LocalTime.MAX);
    }

    @Test(expected = EndBeforeStartException.class)
    public void startingAtOneAMAndEndingAtLocalTimeMaxThrowsEndBeforeStartException() throws InvalidBabysitterConstraintsException {
        new Babysitter(LocalTime.parse("01:00:00"), LocalTime.MAX, LocalTime.MAX);
    }

    @Test(expected = EndBeforeStartException.class)
    public void startingAtTwoAMAndEndingAtOneAMThrowsEndBeforeStartException() throws InvalidBabysitterConstraintsException {
        new Babysitter(LocalTime.parse("02:00:00"), LocalTime.parse("01:00:00"), LocalTime.MAX);
    }

    @Test(expected = EndBeforeStartException.class)
    public void startingAtTenPMAndEndingAtNinePMThrowsEndBeforeStartException() throws InvalidBabysitterConstraintsException {
        new Babysitter(LocalTime.parse("22:00:00"), LocalTime.parse("21:00:00"), LocalTime.MAX);
    }

    @Test(expected = StartsTooEarlyException.class)
    public void startingAtFourFiftyNineThrowsStartsTooEarlyException() throws InvalidBabysitterConstraintsException {
        new Babysitter(LocalTime.parse("16:59:59"), LocalTime.MAX, LocalTime.MAX);
    }

    @Test(expected = EndsTooLateException.class)
    public void endingAfterFourAMThrowsEndsTooLateException() throws InvalidBabysitterConstraintsException {
        new Babysitter(LocalTime.parse("17:00:00"), LocalTime.parse("04:00:01"), LocalTime.MAX);
    }

    @Test
    public void babysittingCanEndBeforeMidnight() throws InvalidBabysitterConstraintsException {
        new Babysitter(LocalTime.parse("17:00:00"), LocalTime.parse("23:15:00"), LocalTime.parse("23:00:00"));
    }

    @Test
    public void babysittingCanStartAfterMidnight() throws InvalidBabysitterConstraintsException {
        new Babysitter(LocalTime.parse("01:00:00"), LocalTime.parse("02:00:00"), LocalTime.parse("23:00:00"));
    }

    @Test
    public void startTimeOfSixFiftyNinePMRoundsUpToSixPM() throws InvalidBabysitterConstraintsException {
        Babysitter babysitter = new Babysitter(LocalTime.parse("18:59:00"), LocalTime.parse("03:00:00"), LocalTime.parse("22:30:00"));
        assertEquals(LocalTime.parse("18:00:00"), babysitter.getStartTime());
    }

    @Test
    public void startTimeOfEightElevenPMRoundsToEightPM() throws InvalidBabysitterConstraintsException {
        Babysitter babysitter = new Babysitter(LocalTime.parse("19:11:00"), LocalTime.parse("03:00:00"), LocalTime.parse("23:30:00"));
        assertEquals(LocalTime.parse("19:00:00"), babysitter.getStartTime());
    }

    @Test
    public void endTimeOfElevenFifteenPMRoundsUpToMidnight() throws InvalidBabysitterConstraintsException {
        Babysitter babysitter = new Babysitter(LocalTime.parse("18:59:00"), LocalTime.parse("23:15:00"), LocalTime.parse("22:30:00"));
        assertEquals(LocalTime.MIDNIGHT, babysitter.getEndTime());
    }

    @Test
    public void endTimeOfOneAMStaysAtOneAM() throws InvalidBabysitterConstraintsException {
        Babysitter babysitter = new Babysitter(LocalTime.parse("19:59:00"), LocalTime.parse("01:00:00"), LocalTime.parse("23:30:00"));
        assertEquals(LocalTime.parse("01:00:00"), babysitter.getEndTime());
    }

    @Test
    public void bedTimeOfTenThirtyPMRoundsUpToEleven() throws InvalidBabysitterConstraintsException {
        Babysitter babysitter = new Babysitter(LocalTime.parse("17:00:00"), LocalTime.parse("03:00:00"), LocalTime.parse("22:30:00"));
        assertEquals(LocalTime.parse("23:00:00"), babysitter.getBedTime());
    }

    @Test
    public void bedTimeOfElevenThirtyPMRoundsToMidnight() throws InvalidBabysitterConstraintsException {
        Babysitter babysitter = new Babysitter(LocalTime.parse("17:00:00"), LocalTime.parse("03:00:00"), LocalTime.parse("23:30:00"));
        assertEquals(LocalTime.MIDNIGHT, babysitter.getBedTime());
    }

    @Test
    public void getChargeOfOneHourBeforeMidnightBeforeTheKidGoesToBed() throws InvalidBabysitterConstraintsException {
        Babysitter babysitter = new Babysitter(LocalTime.parse("17:00:00"), LocalTime.parse("18:00:00"), LocalTime.parse("23:30:00"));
        assertEquals(12, babysitter.getCharge());
    }

    @Test
    public void getChargeFor2HoursBeforeMidnightBeforeTheKidGoesToBed() throws InvalidBabysitterConstraintsException {
        Babysitter babysitter = new Babysitter(LocalTime.parse("17:00:00"), LocalTime.parse("19:00:00"), LocalTime.parse("23:30:00"));
        assertEquals(24, babysitter.getCharge());
    }

}
