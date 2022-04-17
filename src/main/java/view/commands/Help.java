
package view.commands;

import controller.CommandManager;
import view.commands.exceptions.WrongCommandInputException;
import view.console.ConsoleClient;
import view.exceptions.IncorrectScriptException;
import view.utility.Asker;


public class Help extends AbstractCommand{
    private CommandManager commandManager;

    public Help(CommandManager commandManager) {
        super("Help", "Выводит информацию по доступным командам");
        this.commandManager = commandManager;
    }

    @Override
    public boolean execute(String argument) throws IncorrectScriptException {
        try {
            if (argument.isEmpty()){
                commandManager.getCommands().forEach((key,value) -> ConsoleClient.println(key + "  -  " + value.getDescription()));
                ConsoleClient.println("Справка по командам успешно выведена!");
                return true;
            }
            else throw new WrongCommandInputException();
        }catch (WrongCommandInputException exception){
            ConsoleClient.printError("Команда " + getName() + " введена с ошибкой: " +
                    "команда не должна содержать символы после своего названия!");
            if (Asker.getFileMode()) throw new IncorrectScriptException();
        }
        return false;
    }

    public String getMessage(){
        return "help - Выводит информацию по доступным командам";
    }
}

