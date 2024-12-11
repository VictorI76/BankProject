package org.poo.Implementation;

import com.fasterxml.jackson.databind.node.ArrayNode;
import org.poo.Implementation.BankThings.Bank;
import org.poo.Implementation.BankThings.BankActions;
import org.poo.fileio.CommandInput;
import org.poo.fileio.ObjectInput;

import java.util.Arrays;

public class CommandsHandler {

    public void dataParser(ObjectInput input, Bank bank) {
        for (int i = 0; i < input.getUsers().length; i++) {
            User user = new User(input.getUsers()[i]);
            bank.addUser(user);
        }

        if (input.getExchangeRates() != null && input.getExchangeRates().length > 0) {
            bank.setExchange(new Exchange(input.getExchangeRates()));
        }
    }

    public void CommandCenter(CommandInput[] commandInput, BankActions bankActions, ArrayNode output) {
        OutputCreator outputCreator = new OutputCreator();

        System.out.println("\n\n\n------------------NEW TEST----------------\n\n\n");

        for (CommandInput command : commandInput) {
            switch (command.getCommand()) {
                case "printUsers":
                    outputCreator.usersList(output, bankActions.getUsersList(), command.getTimestamp());
                    break;

                case "addAccount":
                    bankActions.addNewAccount(command.getEmail(), command.getCurrency(), command.getAccountType(), command.getInterestRate(), command.getTimestamp());
                    System.out.println("a intray aici pentru timestamp ul == " + command.getTimestamp());
                    System.out.println("a intrat pentru comanda == " + command);
                    break;

                case "createCard":
                    bankActions.createNewCard(command.getEmail(), command.getAccount(), command.getTimestamp());
                    break;

                case "createOneTimeCard":
                    bankActions.createNewOneTimeCard(command.getEmail(), command.getAccount(), command.getTimestamp());
                    break;

                case "addFunds":
                    bankActions.addFunds(command.getAccount(), command.getAmount());
                    break;

                case "deleteAccount":
                    bankActions.deleteAccount(command.getAccount(), command.getEmail(), command.getTimestamp(), output);
                    break;

                case "deleteCard":
                    bankActions.deleteCard(command.getCardNumber(), command.getEmail(), command.getTimestamp());
                    break;

                case "payOnline":
                    bankActions.payOnline(command.getCardNumber(), command.getAmount(), command.getCurrency(), command.getTimestamp(), command.getDescription(), command.getCommerciant(), output);
                    break;
            }
        }
    }
}
