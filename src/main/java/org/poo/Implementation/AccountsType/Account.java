package org.poo.Implementation.AccountsType;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.poo.Implementation.CardTipe.Card;
import org.poo.Implementation.TranzactionThings.Transaction;
import org.poo.utils.Utils;

import java.util.ArrayList;
@Data
public class Account {
    private String accountIBAN;
    private double balance; // (practic) aici tin cati bani sunt in cont
    private double minBalance;
    private String email;
    private String currency;
    private int timestamp;
    private double interestRate;
    private ArrayList<Card> cards;
    private ArrayList<Transaction>  transactions;

    public Account(String email, String currency, int timestamp) {
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

    public void modifyInterest(double interestRate) {
        setInterestRate(-1);
    }

    public void addInterest() {
        return;
    }

    public void increaseBalance(double amount) {
        this.balance += amount;
    }
    public String accountType() {
        return "classic";
    }
}
