package org.poo.Implementation.AccountsType;

import org.poo.Implementation.CardTipe.Card;
import org.poo.Implementation.TranzactionThings.Transaction;
import org.poo.utils.Utils;

import java.util.ArrayList;

public class Account {
    private String accountIBAN;
    private double balance; // (practic) aici tin cati bani sunt in cont
    private double minBalance;
    private String email;
    private String currency;
    private int timestamp;
    private ArrayList<Card> cards;
    private ArrayList<Transaction>  transactions;



    public Account(String email, String currency, int timestamp) {
        this.accountIBAN = Utils.generateIBAN();
        System.out.println("Account IBAN: " + accountIBAN + "account timestamp: " + timestamp);
        this.balance = 0;
        this.minBalance = 0;
        this.email = email;
        this.currency = currency;
        this.timestamp = timestamp;
        this.cards = new ArrayList<>();
        this.transactions = new ArrayList<>();
    }

    public String getAccountIBAN() {
        return accountIBAN;
    }

    public void setAccountIBAN(String accountIBAN) {
        this.accountIBAN = accountIBAN;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getMinBalance() {
        return minBalance;
    }

    public void setMinBalance(double minBalance) {
        this.minBalance = minBalance;
    }

    public void increaseBalance(double amount) {
        this.balance += amount;
    }

    public void decreaseBalance(double amount) {
        this.balance -= amount;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public void setCards(ArrayList<Card> cards) {
        this.cards = cards;
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(ArrayList<Transaction> transactions) {
        this.transactions = transactions;
    }

    public String accountType() {
        return "classic";
    }
}
