package org.poo.Implementation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.poo.Implementation.AccountsType.Account;
import org.poo.Implementation.CardTipe.Card;
import org.poo.Implementation.TranzactionThings.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;
import java.util.HashMap;

public final class OutputCreator {

    /**
     * It is a helper method creating the
     * output format for a card.
     * @param output
     * @param card
     * @return
     */
    private ObjectNode cardShow(final ArrayNode output, final Card card) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode objectNode = mapper.createObjectNode();
        objectNode.put("cardNumber", card.getNumber());
        objectNode.put("status", card.getStatus());

        return objectNode;
    }

    /**
     * Is a method that create the error
     * output for a card that is messing.
     * @param output
     * @param timestamp
     */
    public void cardDoseNotExist(final ArrayNode output, final int timestamp) {
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

    /**
     * It is a helper method that create the
     * output format of an account.
     * @param output
     * @param account
     * @return
     */
    private ObjectNode accountShow(final ArrayNode output, final Account account) {
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

    /**
     * It is a helper method that create the
     * output format of a user.
     * @param output
     * @param user
     * @return
     */
    private ObjectNode usersShow(final ArrayNode output, final User user) {
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

    /**
     * This method create the user list for
     * the "printUser" command.
     * @param output
     * @param users
     * @param timestamp
     */
    public void usersList(final ArrayNode output,
                          final ArrayList<User> users, final int timestamp) {
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

    /**
     * This method send to the output a
     * success message if an account was
     * deleted.
     * @param output
     * @param timestamp
     */
    public void deleteAccount(final ArrayNode output, final int timestamp) {
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

    /**
     * This method send to the output an
     * error if an account could not be
     * deleted.
     * @param output
     * @param timestamp
     */
    public void notDeleteAccount(final ArrayNode output, final int timestamp) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode objectNode = mapper.createObjectNode();
        objectNode.put("command", "deleteAccount");
        ObjectNode accountNode = mapper.createObjectNode();
        accountNode.put("error",
                "Account couldn't be deleted - see org.poo.transactions for details");
        accountNode.put("timestamp", timestamp);
        objectNode.set("output", accountNode);
        objectNode.put("timestamp", timestamp);
        output.add(objectNode);
    }

    /**
     * This method send and error message to
     * the output if an account was not found.
     * @param output
     * @param timestamp
     * @param command
     */
    public void notFoundAccount(final ArrayNode output,
                                final int timestamp, final String command) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode objectNode = mapper.createObjectNode();
        objectNode.put("command", command);
        ObjectNode accountNode = mapper.createObjectNode();
        accountNode.put("description", "Account not found");
        accountNode.put("timestamp", timestamp);
        objectNode.set("output", accountNode);
        objectNode.put("timestamp", timestamp);

        output.add(objectNode);
    }

    /**
     * This method send and error message to
     * the output if a card was not found.
     * @param output
     * @param timestamp
     * @param command
     */
    public void cardNotFound(final ArrayNode output, final String command, final int timestamp) {
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

    /**
     * It is a helper method that create an
     * array of all account that participated
     * in split payment.
     * @param accounts
     * @return
     */
    private ArrayNode transactionAccountList(final List<String> accounts) {
        ObjectMapper mapper = new ObjectMapper();
        ArrayNode arrayNode = mapper.createArrayNode();
        for (String account : accounts) {
            arrayNode.add(account);
        }
        return arrayNode;
    }

    /**
     * It is a helper method that create
     * specific format for any transaction.
     * @param transaction
     * @return
     */
    private ObjectNode transactionNode(final Transaction transaction) {
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
        if (transaction.getCurrency() != null) {
            objectNode.put("currency", transaction.getCurrency());
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
        if (transaction.getFunds() != 0) {
            objectNode.put("amount", transaction.getFunds());
        }
        if (transaction.getInvolvedAccounts() != null) {
            objectNode.set("involvedAccounts",
                    transactionAccountList(transaction.getInvolvedAccounts()));
        }
        if (transaction.getError() != null) {
            objectNode.put("error", transaction.getError());
        }

        return objectNode;
    }

    /**
     * This method crate the transaction list
     * of a user.
     * @param output
     * @param transactions
     * @param timestamp
     */
    public void transactionsList(final ArrayNode output,
                                 final ArrayList<Transaction> transactions, final int timestamp) {
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

    /**
     * It is a helper method that create a
     * list of all transaction from a time
     * interval.
     * @param transactions
     * @param startTimestamp
     * @param endTimestamp
     * @return
     */
    private ArrayNode reportTransaction(final ArrayList<Transaction> transactions,
                                        final int startTimestamp, final int endTimestamp) {
        ObjectMapper mapper = new ObjectMapper();
        ArrayNode arrayNode = mapper.createArrayNode();
        for (Transaction transaction : transactions) {
            if (transaction.getTimestamp() >= startTimestamp
                    && transaction.getTimestamp() <= endTimestamp) {
                arrayNode.add(transactionNode(transaction));
            }
        }

        return arrayNode;
    }

    /**
     * This method create a report of all
     * transaction in a time interval for
     * an account.
     * @param output
     * @param account
     * @param startTimestamp
     * @param endTimestamp
     * @param timestamp
     */
    public void reportList(final ArrayNode output, final Account account,
                           final int startTimestamp, final int endTimestamp, final int timestamp) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode objectNode = mapper.createObjectNode();
        objectNode.put("command", "report");
        ObjectNode outputNode = mapper.createObjectNode();
        outputNode.put("IBAN", account.getAccountIBAN());
        outputNode.put("balance", account.getBalance());
        outputNode.put("currency", account.getCurrency());
        outputNode.set("transactions", reportTransaction(account.getTransactions(),
                startTimestamp, endTimestamp));
        objectNode.set("output", outputNode);
        objectNode.put("timestamp", timestamp);

        output.add(objectNode);
    }

    /**
     * It is a helper method that create a
     * list of all transaction from a time
     * interval that ar payments to a
     * commerciant.
     * @param transactions
     * @param startTimestamp
     * @param endTimestamp
     * @return
     */
    private ArrayNode spendingsReportTransaction(final ArrayList<Transaction> transactions,
                                                 final int startTimestamp,
                                                 final int endTimestamp) {
        ObjectMapper mapper = new ObjectMapper();
        ArrayNode arrayNode = mapper.createArrayNode();
        for (Transaction transaction : transactions) {
            if (transaction.getTimestamp() >= startTimestamp && transaction.getTimestamp()
                    <= endTimestamp && transaction.getCommerciant() != null) {
                arrayNode.add(transactionNode(transaction));
            }
        }

        return arrayNode;
    }

    /**
     * It is a helper method that create
     * list with only the payments to the
     * commerciants.
     * @param transactions
     * @param startTimestamp
     * @param endTimestamp
     * @return
     */
    private ArrayNode spendingsReportCommerciants(final ArrayList<Transaction> transactions,
                                                  final int startTimestamp,
                                                  final int endTimestamp) {
        ObjectMapper mapper = new ObjectMapper();
        ArrayNode arrayNode = mapper.createArrayNode();
        TreeSet<String> commerciants = new TreeSet<>();
        HashMap<String, Transaction> stringTransactionMap = new HashMap<>();
        for (Transaction transaction : transactions) {
            if (transaction.getTimestamp() >= startTimestamp && transaction.getTimestamp()
                    <= endTimestamp && transaction.getCommerciant() != null) {
                commerciants.add(transaction.getCommerciant());
                stringTransactionMap.put(transaction.getCommerciant(), transaction);
            }
        }

        for (String commerciant : commerciants) {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("commerciant", commerciant);
            objectNode.put("total", stringTransactionMap.get(commerciant).getFunds());
            arrayNode.add(objectNode);
        }

        return arrayNode;
    }

    /**
     * This method create a spending report
     * including only payment transaction.
     * @param output
     * @param account
     * @param startTimestamp
     * @param endTimestamp
     * @param timestamp
     */
    public void spendingsReportList(final ArrayNode output, final Account account,
                                    final int startTimestamp,
                                    final int endTimestamp, final int timestamp) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode objectNode = mapper.createObjectNode();
        objectNode.put("command", "spendingsReport");
        ObjectNode outputNode = mapper.createObjectNode();
        outputNode.put("IBAN", account.getAccountIBAN());
        outputNode.put("balance", account.getBalance());
        outputNode.put("currency", account.getCurrency());
        outputNode.set("transactions", spendingsReportTransaction(account.getTransactions(),
                startTimestamp, endTimestamp));
        outputNode.set("commerciants", spendingsReportCommerciants(account.getTransactions(),
                startTimestamp, endTimestamp));
        objectNode.set("output", outputNode);
        objectNode.put("timestamp", timestamp);

        output.add(objectNode);
    }

    /**
     * This method send to the output an
     * error when a classic account is
     * sent to and operation only for
     * savings accounts.
     * @param output
     * @param timestamp
     * @param command
     */
    public void notSavingsAccount(final ArrayNode output,
                                  final int timestamp, final String command) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode objectNode = mapper.createObjectNode();
        objectNode.put("command", command);
        ObjectNode outputNode = mapper.createObjectNode();
        outputNode.put("description", "This is not a savings account");
        outputNode.put("timestamp", timestamp);
        objectNode.set("output", outputNode);
        objectNode.put("timestamp", timestamp);

        output.add(objectNode);
    }

    /**
     * This method send to output an error
     * when a spending report is requested
     * for saving account.
     * @param output
     * @param timestamp
     */
    public void notForSavingsAccounts(final ArrayNode output, final int timestamp) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode objectNode = mapper.createObjectNode();
        objectNode.put("command", "spendingsReport");
        ObjectNode outputNode = mapper.createObjectNode();
        outputNode.put("error", "This kind of report is not supported for a saving account");
        objectNode.set("output", outputNode);
        objectNode.put("timestamp", timestamp);

        output.add(objectNode);
    }

}
