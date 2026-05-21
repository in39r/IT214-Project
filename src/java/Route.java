public class Route {
    private String destination;
    private int distance;

    public Route(String destination, int distance) {
        if (destination == null || destination.trim().isEmpty()) {
            throw new IllegalArgumentException("Destination cannot be empty.");
        }
        if (distance <= 0) {
            throw new IllegalArgumentException("Distance must be greater than zero.");
        }

        this.destination = destination;
        this.distance = distance;
    }

    public String getDestination() {
        return destination;
    }

    public int getDistance() {
        return distance;
    }

    @Override
    public String toString() {
        return destination + " (" + distance + " km)";
    }
}
