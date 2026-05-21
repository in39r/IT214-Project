import java.time.LocalDate;

public class TestPart1 {
    public static void main(String[] args) {
        TransitHub mainHub = new TransitHub("Riyadh Metro Hub", "Riyadh");
        TransitHub southHub = new TransitHub("Abha Transit Hub", "Abha");

        Bus electricBus = new Bus("BUS-301", 3, 75, 4.5, true);
        Bus dieselBus = new Bus("BUS-402", 45, 90, 3.0, false);
        Train cityTrain = new Train("TRN-500", 250, 600, 6);

        mainHub.addVehicle(electricBus);
        mainHub.addVehicle(cityTrain);
        southHub.addVehicle(dieselBus);

        mainHub.addRoute(new Route("King Fahd Road", 20));
        mainHub.addRoute(new Route("Diplomatic Quarter", 14));
        mainHub.addRoute(new Route("King Khalid Airport", 40));

        southHub.addRoute(new Route("Al Soudah Park", 6));
        southHub.addRoute(new Route("Abha Mall", 3));

        Passenger ahmed = new Passenger("Ahmed", 180);
        ahmed.addTransitPass(new TransitPass("TP-011", 30, LocalDate.now().plusMonths(3)));
        ahmed.addCreditCard(new CreditCard(600, "9876543298765432", "10/29", 741, "Ahmed Al-Qahtani"));

        Passenger maha = new Passenger("Maha", 100);
        maha.addTransitPass(new TransitPass("TP-022", 40, LocalDate.now().plusMonths(2)));
        maha.addCreditCard(new CreditCard(200, "1111222233334444", "06/27", 852, "Maha Al-Zahrani"));

        Passenger tariq = new Passenger("Tariq", 60);
        tariq.addTransitPass(new TransitPass("TP-033", 55, LocalDate.now().plusMonths(1)));

        System.out.println("===== TransitHub Part 1 Demo =====");
        System.out.println("All hub names: " + mainHub.getHubNames());
        System.out.println("Main hub average route distance: "
                + String.format("%.2f", mainHub.getAverageRouteDistance()) + " km");
        System.out.println("South hub average route distance: "
                + String.format("%.2f", southHub.getAverageRouteDistance()) + " km");

        System.out.println("\n===== Vehicles =====");
        System.out.println(electricBus);
        System.out.println(cityTrain);
        System.out.println(dieselBus);

        System.out.println("\n===== Payment and Boarding =====");
        ahmed.topUpTransitPass(1, 1, 200);
        ahmed.board(electricBus, 1, 15);
        maha.board(electricBus, 1, 12);
        tariq.board(electricBus, 1, 10);

        System.out.println("\n===== Train Trip and Reward Points =====");
        ahmed.board(cityTrain, 1, 100);
        ahmed.redeemPointsToPass(1, 50);

        System.out.println("\n===== Final Passenger Details =====");
        System.out.println(ahmed);
        System.out.println(ahmed.getTransitPasses().get(0));
        System.out.println(ahmed.getCreditCards().get(0));
        System.out.println(maha);
        System.out.println(tariq);
    }
}
