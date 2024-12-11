package org.poo.Implementation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.poo.Implementation.AccountsType.Account;
import org.poo.Implementation.CardTipe.Card;

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
}
