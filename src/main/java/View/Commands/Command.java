package View.Commands;

import Model.Exceptions.IncorrectScriptException;

public interface Command {
    String getDescription();
    String getName();
    boolean execute (String argument) throws IncorrectScriptException;
}
