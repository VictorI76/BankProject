package org.poo.Implementation.CardTipe;

import org.poo.utils.Utils;

public final class OneTimeCard extends Card {

    public OneTimeCard(final String accountIBAN, final String email, final int timestamp) {
        super(accountIBAN, email, timestamp);
    }

    /**
     * This function regenerate the card.
     */
    public void refactorCard() {
        this.setNumber(Utils.generateCardNumber());
    }
}
