package org.poo.Implementation.CommandSistem;

import com.fasterxml.jackson.databind.node.ArrayNode;
import org.poo.Implementation.Bank;
import org.poo.Implementation.CommandSistem.CommandsImplementation.*;
import org.poo.fileio.CommandInput;

public class CommandFactory {
    public static Commands createCommand(CommandInput commandInput, Bank bank, ArrayNode output) {
        return switch (commandInput.getCommand()) {
            case "printUsers" -> new PrintUsers(bank, commandInput, output);
            case "addAccount" -> new AddAccount(bank, commandInput, output);
            case "createCard" -> new CreateCard(bank, commandInput);
            case "createOneTimeCard" -> new CreateOneTimeCard(bank, commandInput);
            case "addFunds" -> new AddFunds(bank, commandInput);
            case "deleteAccount" -> new DeleteAccount(bank, commandInput, output);
            case "deleteCard" -> new DeleteCard(bank, commandInput);
            case "payOnline" -> new PayOnline(bank, commandInput, output);
            case "setMinimumBalance" -> new SetMinimumBalance(bank, commandInput);
            case "sendMoney" -> new SendMoney(bank, commandInput);
            case "printTransactions" -> new PrintTransaction(bank, commandInput, output);
            case "setAlias" -> new SetAlias(bank, commandInput);
            case "checkCardStatus" -> new CheckCardStatus(bank, commandInput, output);
            default -> null;
        };
    }
}
