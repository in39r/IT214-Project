import java.time.LocalDate;

public class TestPart1 {
    public static void main(String[] args) {

        // --- Setup Hubs ---
        TransitHub northHub = new TransitHub("North Jeddah Hub", "Jeddah");
        TransitHub coastHub = new TransitHub("Corniche Hub", "Jeddah");

        // --- Setup Vehicles ---
        Bus electricBus = new Bus("BUS-771", 3, 60, 3.5, true);
        Train expressTrain = new Train("TRN-220", 300, 800, 4);
        Bus dieselBus = new Bus("BUS-992", 50, 100, 2.0, false);

        coastHub.addVehicle(electricBus);
        coastHub.addVehicle(expressTrain);
        northHub.addVehicle(dieselBus);

        // --- Setup Routes ---
        coastHub.addRoute(new Route("Jeddah Islamic Port", 10));
        coastHub.addRoute(new Route("Red Sea Mall", 8));

        northHub.addRoute(new Route("King Abdulaziz University", 12));
        northHub.addRoute(new Route("Obhur Beach", 22));
        northHub.addRoute(new Route("KAEC", 60));

        // --- Setup Passengers ---
        Passenger khalid = new Passenger("Khalid", 200);
        khalid.addCreditCard(new CreditCard(1000, "4444333322221111", "03/30", 321, "Khalid Al-Ghamdi"));
        khalid.addTransitPass(new TransitPass("TP-101", 10, LocalDate.now().plusMonths(6)));

        Passenger nora = new Passenger("Nora", 50);
        nora.addTransitPass(new TransitPass("TP-202", 80, LocalDate.now().plusMonths(4)));
        nora.addCreditCard(new CreditCard(300, "7777888899990000", "11/26", 654, "Nora Al-Otaibi"));

        Passenger faisal = new Passenger("Faisal", 120);
        faisal.addTransitPass(new TransitPass("TP-303", 5, LocalDate.now().plusMonths(2)));
        faisal.addCreditCard(new CreditCard(150, "1212343456567878", "07/28", 789, "Faisal Al-Dosari"));

        // --- Hub Info ---
        System.out.println("===== Hub Overview =====");
        System.out.println("Registered hubs: " + northHub.getHubNames());
        System.out.println("Corniche Hub avg route distance: "
                + String.format("%.2f", coastHub.getAverageRouteDistance()) + " km");
        System.out.println("North Hub avg route distance: "
                + String.format("%.2f", northHub.getAverageRouteDistance()) + " km");

        // --- Vehicle Info ---
        System.out.println("\n===== Fleet Details =====");
        System.out.println(dieselBus);
        System.out.println(electricBus);
        System.out.println(expressTrain);

        // --- Top-up before boarding (Khalid's balance is low) ---
        System.out.println("\n===== Top-Up =====");
        khalid.topUpTransitPass(1, 1, 100);
        faisal.topUpTransitPass(1, 1, 50);

        // --- Boarding ---
        System.out.println("\n===== Boarding =====");
        nora.board(electricBus, 1, 8);
        khalid.board(electricBus, 1, 8);
        faisal.board(electricBus, 1, 8);      // bus is now full (capacity 3)
        nora.board(electricBus, 1, 5);        // should fail: bus full

        // --- Long train trip to earn points ---
        System.out.println("\n===== Train Journey =====");
        nora.board(expressTrain, 1, 60);
        khalid.board(expressTrain, 1, 60);

        // --- Redeem points ---
        System.out.println("\n===== Redeem Points =====");
        nora.redeemPointsToPass(1, 50);
        khalid.redeemPointsToPass(1, 50);
        faisal.redeemPointsToPass(1, 50);     // should fail: not enough points

        // --- Final State ---
        System.out.println("\n===== Final Passenger Summary =====");
        System.out.println(khalid);
        System.out.println(khalid.getTransitPasses().get(0));
        System.out.println(khalid.getCreditCards().get(0));
        System.out.println();
        System.out.println(nora);
        System.out.println(nora.getTransitPasses().get(0));
        System.out.println();
        System.out.println(faisal);
        System.out.println(faisal.getTransitPasses().get(0));
    }
}
