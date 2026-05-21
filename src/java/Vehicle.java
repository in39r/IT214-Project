public abstract class Vehicle {
    private final String id;

    public int capacity;
    public double currentFuel;
    public final double baseFare;

    private int passengersOnBoard;

    public Vehicle(String id, int capacity, double currentFuel, double baseFare) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("Vehicle id cannot be empty.");
        }
        if (capacity <= 0) {
            throw new IllegalArgumentException("Vehicle capacity must be greater than zero.");
        }
        if (currentFuel < 0 || baseFare < 0) {
            throw new IllegalArgumentException("Fuel and base fare cannot be negative.");
        }

        this.id = id;
        this.capacity = capacity;
        this.currentFuel = currentFuel;
        this.baseFare = baseFare;
        this.passengersOnBoard = 0;
    }

    public String getId() {
        return id;
    }

    public int getPassengersOnBoard() {
        return passengersOnBoard;
    }

    public int getAvailableSeats() {
        return capacity - passengersOnBoard;
    }

    public boolean hasAvailableSeat() {
        return passengersOnBoard < capacity;
    }

    public boolean addPassenger() {
        if (!hasAvailableSeat()) {
            return false;
        }

        passengersOnBoard++;
        return true;
    }

    public abstract double calculateTravelCost(int distance);

    @Override
    public String toString() {
        return getClass().getSimpleName()
                + " {id='" + id + '\''
                + ", capacity=" + capacity
                + ", passengersOnBoard=" + passengersOnBoard
                + ", baseFare=" + baseFare
                + '}';
    }
}
