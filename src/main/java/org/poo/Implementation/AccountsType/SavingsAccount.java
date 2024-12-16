package org.poo.Implementation.AccountsType;

public final class SavingsAccount extends Account {

    public SavingsAccount(final double interestRate, final String email,
                          final String currency, final int timestamp) {
        super(email, currency, timestamp);
        setInterestRate(interestRate);
    }

    /**
     * This function modify the current interest.
     * @param interestRateModifier
     */
    public void modifyInterest(final double interestRateModifier) {
        setInterestRate(interestRateModifier);
    }

    /**
     * This function add interest.
     */
    public void addInterest() {
        setBalance(getBalance() + getInterestRate() * getBalance());
    }

    /**
     * This function return the account type.
     * @return
     */
    public String accountType() {
        return "savings";
    }
}
