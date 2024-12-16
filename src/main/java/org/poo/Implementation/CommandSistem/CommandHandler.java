package org.poo.Implementation.CommandSistem;

import com.fasterxml.jackson.databind.node.ArrayNode;
import org.poo.Implementation.Bank;
import org.poo.fileio.CommandInput;

public final class CommandHandler {

    private CommandHandler() {
        throw new UnsupportedOperationException(
                "This is the command class and it is not intended to be instantiated");
    }

    /**
     * This function executes the commands.
     * @param commandInput
     * @param bank
     * @param output
     */
    public static void commandRunner(final CommandInput[] commandInput,
                                     final Bank bank, final ArrayNode output) {
        for (CommandInput command : commandInput) {
            Commands commandToExec = CommandFactory.createCommand(command, bank, output);
            commandToExec.executeCommand();
        }
    }
}
