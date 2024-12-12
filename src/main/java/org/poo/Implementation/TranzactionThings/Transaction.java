package org.poo.Implementation.TranzactionThings;

import lombok.Data;

@Data
public class Transaction {
    private int timestamp;
    private String description;
    private String senderIBAN;
    private String receiverIBAN;
    private String accountIBAN;
    private double funds;
    private String fundsString;
    private String transferType;
    private String card;
    private String cardHolder;
    private String commerciant;

    Transaction (TransactionBuilder transactionBuilder) {
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
    }
}
