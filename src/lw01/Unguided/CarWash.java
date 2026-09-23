package lw01.unguided;

public class CarWash extends WashService {
    private static final int FIRST_DAYS_CHARGE = 35000;
    private static final int ADDITIONAL_DAYS_CHARGE = 25000;
    private static final int SETUP_CHARGE = 15000;

    public CarWash(String id, int days) {
        super(id, days);
    }

    @Override
    public int calculateCharge() {
        int firstDays = Math.min(getDays(), 3);
        int additionalDays = Math.max(getDays() - 3, 0);
        return firstDays * FIRST_DAYS_CHARGE
            + additionalDays * ADDITIONAL_DAYS_CHARGE
            + SETUP_CHARGE;
    }

    @Override
    public String label() {
        return "Car";
    }
    
}
