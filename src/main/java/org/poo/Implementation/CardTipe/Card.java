package org.poo.Implementation.CardTipe;

import org.poo.utils.Utils;

public class Card {
    private String accountIBAN;
    private String email;
    private String number;
    private int timestamp;
    private String status;

    public Card(String accountIBAN, String email, int timestamp) {
        this.accountIBAN = accountIBAN;
        this.email = email;
        this.number = Utils.generateCardNumber();
        this.timestamp = timestamp;
        this.status = "active";
    }

    public void refactorCard() {
        return;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getAccount() {
        return accountIBAN;
    }

    public void setAccount(String accountIBAN) {
        this.accountIBAN = accountIBAN;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
