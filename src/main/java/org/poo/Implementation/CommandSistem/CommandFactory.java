package org.poo.Implementation.CommandSistem;

import com.fasterxml.jackson.databind.node.ArrayNode;
import org.poo.Implementation.Bank;
import org.poo.Implementation.CommandSistem.CommandsImplementation.PrintUsers;
import org.poo.Implementation.CommandSistem.CommandsImplementation.AddAccount;
import org.poo.Implementation.CommandSistem.CommandsImplementation.CreateCard;
import org.poo.Implementation.CommandSistem.CommandsImplementation.CreateOneTimeCard;
import org.poo.Implementation.CommandSistem.CommandsImplementation.AddFunds;
import org.poo.Implementation.CommandSistem.CommandsImplementation.DeleteAccount;
import org.poo.Implementation.CommandSistem.CommandsImplementation.DeleteCard;
import org.poo.Implementation.CommandSistem.CommandsImplementation.PayOnline;
import org.poo.Implementation.CommandSistem.CommandsImplementation.SetMinimumBalance;
import org.poo.Implementation.CommandSistem.CommandsImplementation.SendMoney;
import org.poo.Implementation.CommandSistem.CommandsImplementation.PrintTransaction;
import org.poo.Implementation.CommandSistem.CommandsImplementation.SetAlias;
import org.poo.Implementation.CommandSistem.CommandsImplementation.CheckCardStatus;
import org.poo.Implementation.CommandSistem.CommandsImplementation.ChangeInterestRate;
import org.poo.Implementation.CommandSistem.CommandsImplementation.SplitPayment;
import org.poo.Implementation.CommandSistem.CommandsImplementation.Report;
import org.poo.Implementation.CommandSistem.CommandsImplementation.SpendingsReport;
import org.poo.Implementation.CommandSistem.CommandsImplementation.AddInterest;
import org.poo.Implementation.CommandSistem.CommandsImplementation.DefaultCommand;
import org.poo.fileio.CommandInput;

public final class CommandFactory {

    private CommandFactory() {
        throw new UnsupportedOperationException(
                "This is a factory class and cannot be instantiated");
    }

    /**
     * This function command factory and is use is
     * to parse the commands.
     * @param commandInput
     * @param bank
     * @param output
     * @return
     */
    public static Commands createCommand(final CommandInput commandInput,
                                         final Bank bank, final ArrayNode output) {
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
            case "changeInterestRate" -> new ChangeInterestRate(bank, commandInput, output);
            case "splitPayment" -> new SplitPayment(bank, commandInput);
            case "report" -> new Report(bank, commandInput, output);
            case "spendingsReport" -> new SpendingsReport(bank, commandInput, output);
            case "addInterest" -> new AddInterest(bank, commandInput, output);
            default -> new DefaultCommand();
        };
    }
}
