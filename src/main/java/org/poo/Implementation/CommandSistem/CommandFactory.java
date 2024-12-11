package org.poo.Implementation.CommandSistem;

import org.poo.fileio.CommandInput;

public class CommandFactory {
    public Commands createCommand(CommandInput commandInput) {
        switch (commandInput.getCommand()) {
            case "printUsers":
                return null;
                break;

            case "addAccount":
                return null;
            break;

            case "createCard":
                return null;
            break;

            case "createOneTimeCard":
                return null;
            break;

            case "addFunds":
                return null;
            break;

            case "deleteAccount":
                return null;
            break;

            case "deleteCard":
                return null;
            break;

            case "payOnline":
                return null;
            break;
        }
    }
}
