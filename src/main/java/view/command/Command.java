package view.command;

import view.exceptions.IncorrectScriptException;

public interface Command {
    String getDescription();
    String getName();
    boolean execute (String argument) throws IncorrectScriptException;
}
