package org.poo.Implementation.BankThings;

import org.poo.Implementation.AccountsType.Account;
import org.poo.Implementation.CardTipe.Card;
import org.poo.Implementation.Exchange;
import org.poo.Implementation.User;

import java.util.ArrayList;
import java.util.HashMap;

public class Bank {
    private ArrayList<Account> accountsList;
    private HashMap<String, Account> accountsMap; // IBAN -> cont
    private ArrayList<User> usersList;
    private HashMap<String, User> usersMap; // email -> user
    private ArrayList<Card> cardsList;
    private HashMap<String, Card> cardsMap; // numar card -> card
    private Exchange exchange;

    public Exchange getExchange() {
        return exchange;
    }

    public void setExchange(Exchange exchange) {
        this.exchange = exchange;
    }

    public Bank() {
        accountsMap = new HashMap<String, Account>();
        accountsList = new ArrayList<>();
        usersMap = new HashMap<String, User>();
        usersList = new ArrayList<>();
        cardsMap = new HashMap<String, Card>();
        cardsList = new ArrayList<>();
    }

    public void addAccount(Account account) {
        this.accountsMap.put(account.getAccountIBAN(), account);
        this.accountsList.add(account);
    }

    public void addUser(User user) {
        this.usersMap.put(user.getEmail(), user);
        this.usersList.add(user);
    }

    public void addCard(Card card) {
        this.cardsMap.put(card.getNumber(), card);
        this.cardsList.add(card);
    }

    public ArrayList<Account> getAccountsList() {
        return accountsList;
    }

    public void setAccountsList(ArrayList<Account> accountsList) {
        this.accountsList = accountsList;
    }

    public HashMap<String, Account> getAccountsMap() {
        return accountsMap;
    }

    public void setAccountsMap(HashMap<String, Account> accountsMap) {
        this.accountsMap = accountsMap;
    }

    public ArrayList<User> getUsersList() {
        return usersList;
    }

    public void setUsersList(ArrayList<User> usersList) {
        this.usersList = usersList;
    }

    public HashMap<String, User> getUsersMap() {
        return usersMap;
    }

    public void setUsersMap(HashMap<String, User> usersMap) {
        this.usersMap = usersMap;
    }

    public ArrayList<Card> getCardsList() {
        return cardsList;
    }

    public void setCardsList(ArrayList<Card> cardsList) {
        this.cardsList = cardsList;
    }

    public HashMap<String, Card> getCardsMap() {
        return cardsMap;
    }

    public void setCardsMap(HashMap<String, Card> cardsMap) {
        this.cardsMap = cardsMap;
    }
}
