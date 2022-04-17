
package View.Commands;

import Controller.CommandManager;
import Model.Exceptions.IncorrectScriptException;
import Model.Exceptions.WrongCommandInputException;
import View.Utility.Asker;
import View.ConsoleClient.ConsoleClient;

import java.util.Map;


public class Help extends AbstractCommand{
    CommandManager commandManager;
    Map<String,AbstractCommand> commands;

    public Help(CommandManager commandManager) {
        super("Help", "Выводит информацию по доступным командам");
        this.commandManager = commandManager;
        this.commands = commandManager.getCommands();
    }

    @Override
    public boolean execute(String argument) throws IncorrectScriptException {
        try {
            if (argument.isEmpty()){
                commands.forEach((key,value) -> ConsoleClient.println(key + "  -  " + value.getDescription()));
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

    public Map<String , AbstractCommand> getMap(){
        return commands;
    }

    public String getMessage(){
        return "help - Выводит информацию по доступным командам";
    }
}

