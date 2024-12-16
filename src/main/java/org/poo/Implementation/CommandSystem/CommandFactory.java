package org.poo.Implementation.CommandSystem;

import com.fasterxml.jackson.databind.node.ArrayNode;
import org.poo.Implementation.Bank;
import org.poo.Implementation.CommandSystem.CommandsImplementation.PrintUsers;
import org.poo.Implementation.CommandSystem.CommandsImplementation.AddAccount;
import org.poo.Implementation.CommandSystem.CommandsImplementation.CreateCard;
import org.poo.Implementation.CommandSystem.CommandsImplementation.CreateOneTimeCard;
import org.poo.Implementation.CommandSystem.CommandsImplementation.AddFunds;
import org.poo.Implementation.CommandSystem.CommandsImplementation.DeleteAccount;
import org.poo.Implementation.CommandSystem.CommandsImplementation.DeleteCard;
import org.poo.Implementation.CommandSystem.CommandsImplementation.PayOnline;
import org.poo.Implementation.CommandSystem.CommandsImplementation.SetMinimumBalance;
import org.poo.Implementation.CommandSystem.CommandsImplementation.SendMoney;
import org.poo.Implementation.CommandSystem.CommandsImplementation.PrintTransaction;
import org.poo.Implementation.CommandSystem.CommandsImplementation.SetAlias;
import org.poo.Implementation.CommandSystem.CommandsImplementation.CheckCardStatus;
import org.poo.Implementation.CommandSystem.CommandsImplementation.ChangeInterestRate;
import org.poo.Implementation.CommandSystem.CommandsImplementation.SplitPayment;
import org.poo.Implementation.CommandSystem.CommandsImplementation.Report;
import org.poo.Implementation.CommandSystem.CommandsImplementation.SpendingsReport;
import org.poo.Implementation.CommandSystem.CommandsImplementation.AddInterest;
import org.poo.Implementation.CommandSystem.CommandsImplementation.DefaultCommand;
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
