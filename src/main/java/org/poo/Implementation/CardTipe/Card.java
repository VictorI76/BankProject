package org.poo.Implementation.CardTipe;

import lombok.Data;
import org.poo.utils.Utils;

@Data
public class Card {
    private String accountIBAN;
    private String email;
    private String number;
    private int timestamp;
    private String status;

    public Card(final String accountIBAN, final String email, final int timestamp) {
        this.accountIBAN = accountIBAN;
        this.email = email;
        this.number = Utils.generateCardNumber();
        this.timestamp = timestamp;
        this.status = "active";
    }

    /**
     * If the card is one time, regenerate it.
     */
    public void refactorCard() {
        return;
    }
}
