package org.poo.Implementation.TranzactionThings;

import lombok.Data;

import java.util.List;

@Data
public final class Transaction {
    private final int timestamp;
    private final String description;
    private final String senderIBAN;
    private final String receiverIBAN;
    private final String accountIBAN;
    private final double funds;
    private final String fundsString;
    private final String transferType;
    private final String card;
    private final String cardHolder;
    private final String commerciant;
    private final String currency;
    private final String error;
    private final List<String> involvedAccounts;

    Transaction(final TransactionBuilder transactionBuilder) {
        timestamp = transactionBuilder.getTimestamp();
        description = transactionBuilder.getDescription();
        senderIBAN = transactionBuilder.getSenderIBAN();
        receiverIBAN = transactionBuilder.getReceiverIBAN();
        accountIBAN = transactionBuilder.getAccountIBAN();
        funds = transactionBuilder.getFunds();
        fundsString = transactionBuilder.getFundsString();
        transferType = transactionBuilder.getTransferType();
        card = transactionBuilder.getCard();
        cardHolder = transactionBuilder.getCardHolder();
        commerciant = transactionBuilder.getCommerciant();
        currency = transactionBuilder.getCurrency();
        error = transactionBuilder.getError();
        involvedAccounts = transactionBuilder.getInvolvedAccounts();
    }
}
