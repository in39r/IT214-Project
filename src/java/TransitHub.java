import java.util.ArrayList;

public class TransitHub {
    private static final ArrayList<TransitHub> allHubs = new ArrayList<>();

    private String hubName;
    private String city;
    private ArrayList<Vehicle> vehicleArrayList;
    private ArrayList<Route> routes;

    public TransitHub(String hubName, String city) {
        if (hubName == null || hubName.trim().isEmpty()) {
            throw new IllegalArgumentException("Hub name cannot be empty.");
        }
        if (city == null || city.trim().isEmpty()) {
            throw new IllegalArgumentException("City cannot be empty.");
        }

        this.hubName = hubName;
        this.city = city;
        this.vehicleArrayList = new ArrayList<>();
        this.routes = new ArrayList<>();

        allHubs.add(this);
    }

    public String getHubName() {
        return hubName;
    }

    public String getCity() {
        return city;
    }

    public ArrayList<Vehicle> getVehicleArrayList() {
        return vehicleArrayList;
    }

    public ArrayList<Route> getRoutes() {
        return routes;
    }

    public void addVehicle(Vehicle vehicle) {
        if (vehicle == null) {
            throw new IllegalArgumentException("Vehicle cannot be null.");
        }
        vehicleArrayList.add(vehicle);
    }

    public void addRoute(Route route) {
        if (route == null) {
            throw new IllegalArgumentException("Route cannot be null.");
        }
        routes.add(route);
    }

    public double getAverageRouteDistance() {
        if (routes.isEmpty()) {
            return 0;
        }

        int totalDistance = 0;
        for (Route route : routes) {
            totalDistance += route.getDistance();
        }

        return (double) totalDistance / routes.size();
    }

    public String getHubNames() {
        if (allHubs.isEmpty()) {
            return "No hubs available";
        }

        StringBuilder names = new StringBuilder();
        for (TransitHub hub : allHubs) {
            if (names.length() > 0) {
                names.append(", ");
            }
            names.append(hub.getHubName());
        }

        return names.toString();
    }

    @Override
    public String toString() {
        return "TransitHub {hubName='" + hubName + '\''
                + ", city='" + city + '\''
                + ", vehicles=" + vehicleArrayList.size()
                + ", routes=" + routes.size()
                + '}';
    }
}
