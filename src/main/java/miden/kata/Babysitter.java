package miden.kata;

public class Babysitter {

    private static final int CHARGE_PER_HOUR_AFTER_MIDNIGHT = 16;
    private static final int CHARGE_PER_HOUR_BEFORE_MIDNIGHT_BEFORE_BEDTIME = 12;
    private static final int CHARGE_PER_HOUR_BEFORE_MIDNIGHT_AFTER_BEDTIME = 8;


    public int getCharge(BabysitterSchedule schedule) {

        return getChargeAfterMidnight(schedule) + getChargeBeforeMidnightBeforeBedtime(schedule);
    }

    private int getChargeAfterMidnight(BabysitterSchedule schedule) {
        return CHARGE_PER_HOUR_AFTER_MIDNIGHT * schedule.getHoursWorkedAfterMidnight();
    }

    private int getChargeBeforeMidnightBeforeBedtime(BabysitterSchedule schedule) {
        return CHARGE_PER_HOUR_BEFORE_MIDNIGHT_BEFORE_BEDTIME * schedule.getHoursWorkedBeforeMidnightBeforeBedtime();
    }

}
