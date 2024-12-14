package org.poo.Implementation.AccountsType;

public class SavingsAccount extends Account {

    public SavingsAccount(double interestRate, String email, String currency, int timestamp) {
        super(email, currency, timestamp);
        setInterestRate(interestRate);
    }

    public void modifyInterest(double interestRate) {
        setInterestRate(interestRate);
    }

    public void addInterest() {
        setBalance(getBalance() + getInterestRate() * getBalance());
    }

    public String accountType() {
        return "savings";
    }
}
