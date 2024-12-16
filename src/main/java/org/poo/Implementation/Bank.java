package org.poo.Implementation;

import lombok.Data;
import org.poo.Implementation.AccountsType.Account;
import org.poo.Implementation.CardTipe.Card;

import java.util.ArrayList;
import java.util.HashMap;

@Data
public final class Bank {
    private ArrayList<Account> accountsList;
    private HashMap<String, Account> accountsMap; // IBAN -> account
    private HashMap<String, String> alisaMap; // alias -> IBAN
    private ArrayList<User> usersList;
    private HashMap<String, User> usersMap; // email -> user
    private ArrayList<Card> cardsList;
    private HashMap<String, Card> cardsMap; // card number -> card
    private Exchange exchange;

    public Bank() {
        accountsMap = new HashMap<String, Account>();
        accountsList = new ArrayList<>();
        alisaMap = new HashMap<String, String>();
        usersMap = new HashMap<String, User>();
        usersList = new ArrayList<>();
        cardsMap = new HashMap<String, Card>();
        cardsList = new ArrayList<>();
    }

    /**
     * This function add an account in the account list
     * and in the hashMap.
     * @param account
     */
    public void addAccount(final Account account) {
        this.accountsMap.put(account.getAccountIBAN(), account);
        this.accountsList.add(account);
    }

    /**
     * This function add a user in the account list
     * and in the hashMap.
     * @param user
     */
    public void addUser(final User user) {
        this.usersMap.put(user.getEmail(), user);
        this.usersList.add(user);
    }

    /**
     * This function add a card in the account list
     * and in the hashMap.
     * @param card
     */
    public void addCard(final Card card) {
        this.cardsMap.put(card.getNumber(), card);
        this.cardsList.add(card);
    }

}
