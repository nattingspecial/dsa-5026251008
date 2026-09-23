package lw01.unguided;

public class MotorcycleWash extends WashService {
    private static final int DAILY_CHARGE = 15000;
    private static final int SETUP_CHARGE = 5000;

    public MotorcycleWash(String id, int days) {
        super(id, days);
    }

    @Override
    public int calculateCharge() {
        return getDays() * DAILY_CHARGE + SETUP_CHARGE;
    }

    @Override
    public String label() {
        return "Motorcycle";
    }
    
}
