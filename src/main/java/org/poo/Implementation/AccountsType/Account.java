package org.poo.Implementation.AccountsType;

import lombok.Data;
import org.poo.Implementation.CardType.Card;
import org.poo.Implementation.TransactionThings.Transaction;
import org.poo.utils.Utils;

import java.util.ArrayList;
@Data
public class Account {
    private String accountIBAN;
    private double balance; // this variable is practically the amount of money stored in to account
    private double minBalance;
    private final String email;
    private final String currency;
    private final int timestamp;
    private double interestRate;
    private ArrayList<Card> cards;
    private ArrayList<Transaction>  transactions;

    public Account(final String email, final String currency, final int timestamp) {
        this.accountIBAN = Utils.generateIBAN();
        this.balance = 0;
        this.minBalance = 0;
        this.email = email;
        this.currency = currency;
        this.timestamp = timestamp;
        this.interestRate = -1;
        this.cards = new ArrayList<>();
        this.transactions = new ArrayList<>();
    }

    /**
     *  This function modify the interest if the account is
     *  a savings account otherwise it put the interest == -1.
     * @param interestRateModifier
     */
    public void modifyInterest(final double interestRateModifier) {
        setInterestRate(-1);
    }

    /**
     * This function add interest if the account is a
     * savings account.
     */
    public void addInterest() {
        return;
    }

    /**
     * This function add the "amount" to the current
     * balance.
     * @param amount
     */
    public void increaseBalance(final double amount) {
        this.balance += amount;
    }

    /**
     * This function return the type of the account.
     * @return
     */
    public String accountType() {
        return "classic";
    }
}
