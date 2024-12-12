package org.poo.Implementation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.poo.Implementation.AccountsType.Account;
import org.poo.Implementation.CardTipe.Card;
import org.poo.Implementation.TranzactionThings.Transaction;

import java.util.ArrayList;

public class OutputCreator {

    public ObjectNode cardShow(ArrayNode output, Card card) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode objectNode = mapper.createObjectNode();
        objectNode.put("cardNumber", card.getNumber());
        objectNode.put("status", card.getStatus());

        return objectNode;
    }

    public void cardDoseNotExist(ArrayNode output, int timestamp) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode objectNode = mapper.createObjectNode();
        ObjectNode cardFormat = mapper.createObjectNode();
        cardFormat.put("timestamp", timestamp);
        cardFormat.put("description", "Card not found");
        objectNode.put("command", "payOnline");
        objectNode.set("output", cardFormat);
        objectNode.put("timestamp", timestamp);

        output.add(objectNode);
    }

    public ObjectNode accountShow(ArrayNode output, Account account) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode objectNode = mapper.createObjectNode();
        objectNode.put("IBAN", account.getAccountIBAN());
        objectNode.put("balance", account.getBalance());
        objectNode.put("currency", account.getCurrency());
        objectNode.put("type", account.accountType());
        ArrayNode arrayNode = mapper.createArrayNode();
        for (int i = 0; i < account.getCards().size(); i++) {
            arrayNode.add(cardShow(output, account.getCards().get(i)));
        }
        objectNode.set("cards", arrayNode);
        return objectNode;
    }

    public ObjectNode usersShow(ArrayNode output, User user) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode objectNode = mapper.createObjectNode();
        objectNode.put("firstName", user.getFirstName());
        objectNode.put("lastName", user.getLastName());
        objectNode.put("email", user.getEmail());
        ArrayNode arrayNode = mapper.createArrayNode();
        for (int i = 0; i < user.getAccounts().size(); i++) {
            arrayNode.add(accountShow(output, user.getAccounts().get(i)));
        }
        objectNode.set("accounts", arrayNode);

        return objectNode;
    }

    public void usersList(ArrayNode output, ArrayList<User> users, int timestamp) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode objectNode = mapper.createObjectNode();
        objectNode.put("command", "printUsers");
        ArrayNode arrayNode = mapper.createArrayNode();
        for (int i = 0; i < users.size(); i++) {
            arrayNode.add(usersShow(output, users.get(i)));
        }
        objectNode.set("output", arrayNode);
        objectNode.put("timestamp", timestamp);

        output.add(objectNode);
    }

    public void deleteAccount(ArrayNode output, int timestamp) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode objectNode = mapper.createObjectNode();
        objectNode.put("command", "deleteAccount");
        ObjectNode accountNode = mapper.createObjectNode();
        accountNode.put("success", "Account deleted");
        accountNode.put("timestamp", timestamp);
        objectNode.set("output", accountNode);
        objectNode.put("timestamp", timestamp);
        output.add(objectNode);
    }

    public void notDeleteAccount(ArrayNode output, int timestamp) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode objectNode = mapper.createObjectNode();
        objectNode.put("command", "deleteAccount");
        ObjectNode accountNode = mapper.createObjectNode();
        accountNode.put("error", "Account couldn't be deleted - see org.poo.transactions for details");
        accountNode.put("timestamp", timestamp);
        objectNode.set("output", accountNode);
        objectNode.put("timestamp", timestamp);
        output.add(objectNode);
    }

    public void cardNotFound(ArrayNode output, String command, int timestamp) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode objectNode = mapper.createObjectNode();
        objectNode.put("command", command);
        ObjectNode cardFormat = mapper.createObjectNode();
        cardFormat.put("timestamp", timestamp);
        cardFormat.put("description", "Card not found");
        objectNode.set("output", cardFormat);
        objectNode.put("timestamp", timestamp);

        output.add(objectNode);
    }


    public ObjectNode transactionNode(Transaction transaction) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode objectNode = mapper.createObjectNode();
        objectNode.put("timestamp", transaction.getTimestamp());
        objectNode.put("description", transaction.getDescription());
        if (transaction.getSenderIBAN() != null) {
            objectNode.put("senderIBAN", transaction.getSenderIBAN());
        }
        if (transaction.getReceiverIBAN() != null) {
            objectNode.put("receiverIBAN", transaction.getReceiverIBAN());
        }
        if (transaction.getFunds() != 0) {
            objectNode.put("amount", transaction.getFunds());
        }
        if (transaction.getFundsString() != null) {
            objectNode.put("amount", transaction.getFundsString());
        }
        if (transaction.getTransferType() != null) {
            objectNode.put("transferType", transaction.getTransferType());
        }
        if (transaction.getCard() != null) {
            objectNode.put("card", transaction.getCard());
        }
        if (transaction.getCardHolder() != null) {
            objectNode.put("cardHolder", transaction.getCardHolder());
        }
        if (transaction.getAccountIBAN() != null) {
            objectNode.put("account", transaction.getAccountIBAN());
        }
        if (transaction.getCommerciant() != null) {
            objectNode.put("commerciant", transaction.getCommerciant());
        }

        return objectNode;
    }

    public void transactionsList(ArrayNode output, ArrayList<Transaction> transactions, int timestamp) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode objectNode = mapper.createObjectNode();
        objectNode.put("command", "printTransactions");
        ArrayNode arrayNode = mapper.createArrayNode();
        for (Transaction transaction : transactions) {
            arrayNode.add(transactionNode(transaction));
        }
        objectNode.set("output", arrayNode);
        objectNode.put("timestamp", timestamp);

        output.add(objectNode);
    }
}
