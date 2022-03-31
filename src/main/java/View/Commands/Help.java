
package View.Commands;

import Model.Exceptions.WrongCommandInputException;
import View.ConsoleClient.ConsoleClient;

public class Help extends AbstractCommand{

    public Help() {
        super("Help", "Выводит информацию по доступным командам");
    }

    @Override
    public boolean execute(String argument) {
        try {
            if (argument.isEmpty()) return true;
            else throw new WrongCommandInputException();
        }catch (WrongCommandInputException exception){
            ConsoleClient.printError("Команда " + getName() + " введена с ошибкой: " +
                    "команда не должна содержать символы после своего названия!");
        }
        return false;
    }

    public String getMessage(){
        return "help - Выводит информацию по доступным командам";
    }
}

