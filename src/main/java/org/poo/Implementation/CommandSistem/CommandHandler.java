package org.poo.Implementation.CommandSistem;

import com.fasterxml.jackson.databind.node.ArrayNode;
import org.poo.Implementation.Bank;
import org.poo.fileio.CommandInput;

public class CommandHandler {
    public static void commandRunner(CommandInput[] commandInput, Bank bank, ArrayNode output) {
        for (CommandInput command : commandInput) {
            Commands commandToExec = CommandFactory.createCommand(command, bank, output);
            commandToExec.executeCommand();
        }
    }
}
