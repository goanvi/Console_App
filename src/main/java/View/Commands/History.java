package View.Commands;

import Model.Exceptions.WrongCommandInputException;
import View.ConsoleClient.ConsoleClient;

public class History extends  AbstractCommand{
    public History() {
        super("History", "Выводит последние 11 команд (без их аргументов)");
    }

    @Override
    public boolean execute(String argument) {
        try{
            if (argument.isEmpty()) return true;
            else throw new WrongCommandInputException();
        }catch (WrongCommandInputException exception){
            ConsoleClient.printError("Команда " + getName() + " введена с ошибкой: " +
                    "команда не должна содержать символы после своего названия!");
        }
        return false;
    }

    public String getMessage(){
        return "history - Выводит последние 11 команд (без их аргументов)";
    }
}
