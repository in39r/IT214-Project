import java.time.LocalDate;

public class TransitPass {
    private final String passID;
    private double balance;
    private LocalDate expiryDate;

    public TransitPass(String passID, double balance, LocalDate expiryDate) {
        if (passID == null || passID.trim().isEmpty()) {
            throw new IllegalArgumentException("Pass ID cannot be empty.");
        }
        if (balance < 0) {
            throw new IllegalArgumentException("Transit pass balance cannot be negative.");
        }
        if (expiryDate == null) {
            throw new IllegalArgumentException("Expiry date cannot be null.");
        }

        this.passID = passID;
        this.balance = balance;
        this.expiryDate = expiryDate;
    }

    public String getPassID() {
        return passID;
    }

    public double getBalance() {
        return balance;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public boolean isExpired() {
        return LocalDate.now().isAfter(expiryDate);
    }

    public void addBalance(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Top-up amount must be greater than zero.");
        }
        balance += amount;
    }

    public boolean deduct(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deduction amount must be greater than zero.");
        }
        if (isExpired() || balance < amount) {
            return false;
        }

        balance -= amount;
        return true;
    }

    @Override
    public String toString() {
        return "TransitPass {passID='" + passID + '\''
                + ", balance=" + String.format("%.2f", balance)
                + ", expiryDate=" + expiryDate
                + '}';
    }
}
