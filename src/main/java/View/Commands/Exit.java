package View.Commands;

import Model.Exceptions.IncorrectScriptException;
import Model.Exceptions.WrongCommandInputException;
import View.Asker;
import View.ConsoleClient.ConsoleClient;

public class Exit extends AbstractCommand{
    public Exit() {
        super("Exit", "Завершает программу (без сохранения в файл)");
    }

    @Override
    public boolean execute(String argument) throws IncorrectScriptException {
        try {
            if (argument.isEmpty()){
                System.exit(0);
                ConsoleClient.println("Выход из программы успешно выполнен!");
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
        return "exit - Завершает программу (без сохранения в файл)";
    }
}
