package org.poo.Implementation.BankThings;

import com.fasterxml.jackson.databind.node.ArrayNode;
import org.poo.Implementation.AccountsType.Account;
import org.poo.Implementation.AccountsType.SavingsAccount;
import org.poo.Implementation.CardTipe.Card;
import org.poo.Implementation.CardTipe.OneTimeCard;
import org.poo.Implementation.OutputCreator;

public class BankActions extends Bank{

    public BankActions(){
        super();
    }

    public void addNewAccount(String email, String currency, String accountType, double interestRate, int timestamp){
        Account account;
        if(accountType.equals("classic")) {
            account = new Account(email, currency, timestamp);
        } else {
            account = new SavingsAccount(interestRate, email, currency, timestamp);
        }

        this.addAccount(account);
        this.getUsersMap().get(email).getAccounts().add(account);
    }

    public void createNewCard(String email, String accountIBAN, int timestamp) {
        Card card = new Card(accountIBAN, email, timestamp);
        this.addCard(card);
        this.getAccountsMap().get(accountIBAN).getCards().add(card);
    }

    public void createNewOneTimeCard(String email, String accountIBAN, int timestamp) {
        OneTimeCard oneTimeCard = new OneTimeCard(accountIBAN, email, timestamp);
        this.addCard(oneTimeCard);
        this.getAccountsMap().get(accountIBAN).getCards().add(oneTimeCard);
    }

    public void addFunds(String accountIBAN, double funds) { // nu uita sa adaugi ", int timestamp" inapoi!
        this.getAccountsMap().get(accountIBAN).increaseBalance(funds);
    }

    public void deleteAccount(String accountIBAN, String email, int timestamp, ArrayNode output) {
        OutputCreator outputCreator = new OutputCreator();
        Account account = this.getAccountsMap().get(accountIBAN);

        System.out.println("sunt un delete account asta este IBAN-ul " + accountIBAN + "\nasta este email-ul " + email);
        System.out.println("contul ==> " + account);

        if (account == null || !this.getUsersMap().get(email).getAccounts().contains(account)) {
            return;
        }

        if(account.getBalance() > 0) {
            return;
        }

        for(Card card : account.getCards()) {
            this.getCardsMap().remove(card.getNumber());
            this.getCardsList().remove(card);
        }
        account.getCards().removeAll(account.getCards());

        this.getAccountsMap().remove(accountIBAN);
        this.getAccountsList().remove(account);
        this.getUsersMap().get(email).getAccounts().remove(account);

        outputCreator.deleteAccount(output, timestamp);
    }

    public void deleteCard(String cardNumber, String email, int timestamp) {
        OutputCreator outputCreator = new OutputCreator();
        Card card = this.getCardsMap().get(cardNumber);

        System.out.println("\n\n\n" + this.getCardsMap() + "\n\n\n");

        System.out.println("numarul cardului === " + cardNumber);

        System.out.println("a intrat la stergerea cardului pentru: " + card);

        if (card == null || !card.getEmail().equals(email)) {
            return;
        }

        System.out.println("printez numarul cardului care trebuie sters" + card.getNumber() + cardNumber);

        this.getCardsMap().remove(cardNumber);
        this.getCardsList().remove(card);
        this.getAccountsMap().get(card.getAccount()).getCards().remove(card);
    }

    public void payOnline(String cardNumber, double funds, String currency, int timestamp, String description, String commerciant, ArrayNode output) {
        OutputCreator outputCreator = new OutputCreator();
        Card card = this.getCardsMap().get(cardNumber);

        if(card == null) {
            outputCreator.cardDoseNotExist(output, timestamp);
            return;
        }

        Account account = this.getAccountsMap().get(card.getAccount());

        if(account == null) {
            return;
        }

        if (account.getBalance() > 0) {
            account.setBalance(account.getBalance() - this.getExchange().getRate(currency, account.getCurrency()) * funds);
        }

        card.refactorCard();

        if (!cardNumber.equals(card.getNumber())) {
            this.getCardsMap().remove(cardNumber);
            this.getCardsMap().put(card.getNumber(), card);
        }
    }

}
