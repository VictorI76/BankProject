package org.poo.Implementation;

import lombok.Data;
import org.poo.Implementation.AccountsType.Account;
import org.poo.Implementation.TransactionThings.Transaction;
import org.poo.fileio.UserInput;

import java.util.ArrayList;

@Data
public final class User {
    private final String firstName;
    private final String lastName;
    private final String email;
    private final ArrayList<Account> accounts;
    private final ArrayList<Transaction> transactions;

    public User(final UserInput userInput) {
        this.firstName = userInput.getFirstName();
        this.lastName = userInput.getLastName();
        this.email = userInput.getEmail();
        this.accounts = new ArrayList<>();
        this.transactions = new ArrayList<>();
    }
}
