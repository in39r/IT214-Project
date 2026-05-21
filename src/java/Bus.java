public class Bus extends Vehicle {
    private boolean isElectric;

    public Bus(String id, int capacity, double currentFuel, double baseFare, boolean isElectric) {
        super(id, capacity, currentFuel, baseFare);
        this.isElectric = isElectric;
    }

    public boolean isElectric() {
        return isElectric;
    }

    public void setElectric(boolean electric) {
        isElectric = electric;
    }

    @Override
    public double calculateTravelCost(int distance) {
        if (distance <= 0) {
            throw new IllegalArgumentException("Distance must be greater than zero.");
        }

        double costPerKm = isElectric ? 0.80 : 1.00;
        return baseFare + (distance * costPerKm);
    }

    @Override
    public String toString() {
        return super.toString() + " Electric=" + isElectric;
    }
}
