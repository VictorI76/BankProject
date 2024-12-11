package org.poo.Implementation.TranzactionThings;

import lombok.Data;

@Data
public class Transaction {
    private int timestamp;
    private String description;
    private String senderIBAN;
    private String receiverIBAN;
    private int funds;
    private String transferType;

    Transaction (TransactionBuilder transactionBuilder) {
        timestamp = transactionBuilder.getTimestamp();
        description = transactionBuilder.getDescription();
        senderIBAN = transactionBuilder.getSenderIBAN();
        receiverIBAN = transactionBuilder.getReceiverIBAN();
        funds = transactionBuilder.getFunds();
        transferType = transactionBuilder.getTransferType();
    }
}
