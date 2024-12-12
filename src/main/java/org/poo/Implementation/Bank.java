package org.poo.Implementation;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.poo.Implementation.AccountsType.Account;
import org.poo.Implementation.CardTipe.Card;

import java.util.ArrayList;
import java.util.HashMap;

@Data
public class Bank {
    private ArrayList<Account> accountsList;
    private HashMap<String, Account> accountsMap; // IBAN -> cont
    private HashMap<String, String> alisaMap;// alias -> IBAN
    private ArrayList<User> usersList;
    private HashMap<String, User> usersMap; // email -> user
    private ArrayList<Card> cardsList;
    private HashMap<String, Card> cardsMap;// numar card -> card
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

}
