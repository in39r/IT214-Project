public class Train extends Vehicle {
    public static final double TRAIN_BASE_FARE = 12.0;

    private int numberOfCarriages;

    public Train(String id, int capacity, double currentFuel, int numberOfCarriages) {
        super(id, capacity, currentFuel, TRAIN_BASE_FARE);

        if (numberOfCarriages <= 0) {
            throw new IllegalArgumentException("Number of carriages must be greater than zero.");
        }

        this.numberOfCarriages = numberOfCarriages;
    }

    public int getNumberOfCarriages() {
        return numberOfCarriages;
    }

    public void setNumberOfCarriages(int numberOfCarriages) {
        if (numberOfCarriages <= 0) {
            throw new IllegalArgumentException("Number of carriages must be greater than zero.");
        }
        this.numberOfCarriages = numberOfCarriages;
    }

    @Override
    public double calculateTravelCost(int distance) {
        if (distance <= 0) {
            throw new IllegalArgumentException("Distance must be greater than zero.");
        }

        return baseFare + (distance * 1.50) + (numberOfCarriages * 0.25);
    }

    @Override
    public String toString() {
        return super.toString() + " Carriages=" + numberOfCarriages;
    }
}
