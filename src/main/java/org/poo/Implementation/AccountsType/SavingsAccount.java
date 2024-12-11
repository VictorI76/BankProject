package org.poo.Implementation.AccountsType;

public class SavingsAccount extends Account {
    private double interestRate;

    public SavingsAccount(double interestRate, String email, String currency, int timestamp) {
        super(email, currency, timestamp);
        this.interestRate = interestRate;
    }

    public String accountType() {
        return "one time card"; // aflam ce messaj punem mai incolo!
    }
}
