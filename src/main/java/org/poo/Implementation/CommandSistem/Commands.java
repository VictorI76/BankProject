package org.poo.Implementation.CommandSistem;

import org.poo.Implementation.TranzactionThings.Visitor;

public abstract class Commands {
    public void accept(Visitor v) {
        v.visit(this);
    }
    public void executeCommand() {}
}
