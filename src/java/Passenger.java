import java.util.ArrayList;

public class Passenger {
    public String name;
    private double balance;
    private int frequentFlyerPoints;

    private final ArrayList<TransitPass> transitPasses;
    private final ArrayList<CreditCard> creditCards;

    public Passenger(String name, double balance) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Passenger name cannot be empty.");
        }
        if (balance < 0) {
            throw new IllegalArgumentException("Passenger balance cannot be negative.");
        }

        this.name = name;
        this.balance = balance;
        this.frequentFlyerPoints = 0;
        this.transitPasses = new ArrayList<>();
        this.creditCards = new ArrayList<>();
    }

    public double getBalance() {
        return balance;
    }

    public int getFrequentFlyerPoints() {
        return frequentFlyerPoints;
    }

    public void addTransitPass(TransitPass pass) {
        if (pass == null) {
            throw new IllegalArgumentException("Transit pass cannot be null.");
        }
        transitPasses.add(pass);
    }

    public void addCreditCard(CreditCard card) {
        if (card == null) {
            throw new IllegalArgumentException("Credit card cannot be null.");
        }
        creditCards.add(card);
    }

    public ArrayList<TransitPass> getTransitPasses() {
        return transitPasses;
    }

    public ArrayList<CreditCard> getCreditCards() {
        return creditCards;
    }

    public boolean topUpTransitPass(int passChoice, int cardChoice, double amount) {
        TransitPass pass = getPassByChoice(passChoice);
        CreditCard card = getCardByChoice(cardChoice);

        if (pass == null) {
            System.out.println(name + " does not have transit pass number " + passChoice + ".");
            return false;
        }
        if (card == null) {
            System.out.println(name + " does not have credit card number " + cardChoice + ".");
            return false;
        }
        if (!card.charge(amount)) {
            System.out.println("Top up failed: not enough credit card balance.");
            return false;
        }

        pass.addBalance(amount);
        System.out.println(name + " topped up " + amount + " S.R. to pass " + pass.getPassID() + ".");
        return true;
    }

    public boolean board(Vehicle vehicle) {
        return board(vehicle, 1, 1);
    }

    public boolean board(Vehicle vehicle, int passChoice, int distance) {
        if (vehicle == null) {
            throw new IllegalArgumentException("Vehicle cannot be null.");
        }
        if (distance <= 0) {
            throw new IllegalArgumentException("Distance must be greater than zero.");
        }

        TransitPass selectedPass = getPassByChoice(passChoice);
        if (selectedPass == null) {
            System.out.println(name + " cannot board: selected pass was not found.");
            return false;
        }
        if (selectedPass.isExpired()) {
            System.out.println(name + " cannot board: selected pass is expired.");
            return false;
        }
        if (!vehicle.hasAvailableSeat()) {
            System.out.println(name + " cannot board " + vehicle.getId() + ": vehicle is full.");
            return false;
        }

        double travelCost = vehicle.calculateTravelCost(distance);
        if (!selectedPass.deduct(travelCost)) {
            System.out.println(name + " cannot board: pass balance is not enough. Required: "
                    + String.format("%.2f", travelCost) + " S.R.");
            return false;
        }

        vehicle.addPassenger();
        addTravelPoints(distance);

        System.out.println(name + " boarded " + vehicle.getId()
                + " for " + String.format("%.2f", travelCost) + " S.R."
                + " | Points: " + frequentFlyerPoints);
        return true;
    }

    public boolean redeemPointsToPass(int passChoice, int pointsToRedeem) {
        TransitPass pass = getPassByChoice(passChoice);
        if (pass == null) {
            System.out.println(name + " cannot redeem points: selected pass was not found.");
            return false;
        }
        if (pointsToRedeem < 50) {
            System.out.println("At least 50 points are needed to redeem rewards.");
            return false;
        }
        if (pointsToRedeem > frequentFlyerPoints) {
            System.out.println(name + " does not have enough points.");
            return false;
        }

        int usedPoints = (pointsToRedeem / 50) * 50;
        double rewardAmount = (usedPoints / 50) * 10.0;

        frequentFlyerPoints -= usedPoints;
        pass.addBalance(rewardAmount);

        System.out.println(name + " redeemed " + usedPoints + " points for "
                + String.format("%.2f", rewardAmount) + " S.R.");
        return true;
    }

    private void addTravelPoints(int distance) {
        frequentFlyerPoints += (distance / 10) * 5;
    }

    private TransitPass getPassByChoice(int passChoice) {
        int index = passChoice - 1;
        if (index < 0 || index >= transitPasses.size()) {
            return null;
        }
        return transitPasses.get(index);
    }

    private CreditCard getCardByChoice(int cardChoice) {
        int index = cardChoice - 1;
        if (index < 0 || index >= creditCards.size()) {
            return null;
        }
        return creditCards.get(index);
    }

    @Override
    public String toString() {
        return "Passenger {name='" + name + '\''
                + ", balance=" + String.format("%.2f", balance)
                + ", frequentFlyerPoints=" + frequentFlyerPoints
                + ", transitPasses=" + transitPasses.size()
                + ", creditCards=" + creditCards.size()
                + '}';
    }
}
