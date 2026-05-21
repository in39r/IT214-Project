public class CreditCard {
    private double balance;
    private final String cardNumber;
    private final String expiryDate;
    private final int pin;
    private final String cardHolderName;

    public CreditCard(double balance, String cardNumber, String expiryDate, int pin, String cardHolderName) {
        if (balance < 0) {
            throw new IllegalArgumentException("Credit card balance cannot be negative.");
        }
        if (cardNumber == null || !cardNumber.matches("\\d{16}")) {
            throw new IllegalArgumentException("Card number must contain exactly 16 digits.");
        }
        if (pin < 100 || pin > 999) {
            throw new IllegalArgumentException("PIN must contain exactly 3 digits.");
        }
        if (expiryDate == null || expiryDate.trim().isEmpty()) {
            throw new IllegalArgumentException("Expiry date cannot be empty.");
        }
        if (cardHolderName == null || cardHolderName.trim().isEmpty()) {
            throw new IllegalArgumentException("Card holder name cannot be empty.");
        }

        this.balance = balance;
        this.cardNumber = cardNumber;
        this.expiryDate = expiryDate;
        this.pin = pin;
        this.cardHolderName = cardHolderName;
    }

    public double getBalance() {
        return balance;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public String getCardHolderName() {
        return cardHolderName;
    }

    public String getMaskedCardNumber() {
        return "**** **** **** " + cardNumber.substring(12);
    }

    public boolean charge(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Charge amount must be greater than zero.");
        }
        if (balance < amount) {
            return false;
        }

        balance -= amount;
        return true;
    }

    @Override
    public String toString() {
        return "CreditCard {holder='" + cardHolderName + '\''
                + ", card=" + getMaskedCardNumber()
                + ", balance=" + String.format("%.2f", balance)
                + ", expiryDate='" + expiryDate + '\''
                + '}';
    }
}
