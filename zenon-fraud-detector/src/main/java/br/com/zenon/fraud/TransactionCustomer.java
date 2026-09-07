package br.com.zenon.fraud;

import java.util.Locale;

public record TransactionCustomer(String name, double oldBalance, double newBalance) {

    public TransactionCustomer {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("name should not be empty");
        }
        if (oldBalance < 0) {
            throw new IllegalArgumentException("oldBalance should be positive: " + money(oldBalance));
        }
        if (newBalance < 0) {
            throw new IllegalArgumentException("newBalance should be positive: " + money(newBalance));
        }
    }

    static String money(double value) {
        return String.format(Locale.US, "%.2f", value);
    }

    @Override
    public String toString() {
        return "TransactionCustomer[name=" + name
                + ", oldBalance=" + money(oldBalance)
                + ", newBalance=" + money(newBalance) + "]";
    }
}
