package org.poo.Implementation.CardTipe;

import org.poo.Implementation.AccountsType.Account;
import org.poo.utils.Utils;

public class OneTimeCard extends Card {

    public OneTimeCard(String accountIBAN, String email, int timestamp) {
        super(accountIBAN, email, timestamp);
    }

    public void refactorCard() {
        this.setNumber(Utils.generateCardNumber());
    }
}
