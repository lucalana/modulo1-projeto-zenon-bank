package br.com.zenon.fraud;

import java.util.Locale;
import java.util.Objects;

public record Transaction(
        int step,
        TransactionType type,
        double amount,
        TransactionCustomer origin,
        TransactionCustomer recipient,
        boolean isFraud,
        boolean isFlaggedFraud
) {

    public Transaction {
        Objects.requireNonNull(type, "type should not be null");
        Objects.requireNonNull(origin, "origin should not be null");
        Objects.requireNonNull(recipient, "recipient should not be null");
        if (step < 1) {
            throw new IllegalArgumentException("step should be positive: " + step);
        }
        if (amount < 0) {
            throw new IllegalArgumentException("amount should be positive: " + money(amount));
        }
    }

    private static String money(double value) {
        return String.format(Locale.US, "%.2f", value);
    }

    @Override
    public String toString() {
        return "Transaction[step=" + step
                + ", type=" + type
                + ", amount=" + money(amount)
                + ", origin=" + origin
                + ", recipient=" + recipient
                + ", isFraud=" + isFraud
                + ", isFlaggedFraud=" + isFlaggedFraud + "]";
    }
}
