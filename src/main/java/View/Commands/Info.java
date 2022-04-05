package View.Commands;

import Controller.CollectionManager;
import Model.Exceptions.IncorrectScriptException;
import Model.Exceptions.WrongCommandInputException;
import View.Asker;
import View.ConsoleClient.ConsoleClient;

public class Info extends AbstractCommand {
    CollectionManager collectionManager;

    public Info(CollectionManager manager) {
        super("Info", "Выводит в стандартный поток вывода информацию о коллекции" +
                " (тип, дата инициализации, количество элементов и т.д.)");
        this.collectionManager = manager;
    }

    @Override
    public boolean execute(String argument) throws IncorrectScriptException{
        try {
            if (argument.isEmpty()) {
                ConsoleClient.println(collectionManager.getCollectionType());
                ConsoleClient.println(collectionManager.getCollectionSize());
                ConsoleClient.println(collectionManager.getLastLoadTime());
                ConsoleClient.println("Информация о коллекции успешно выведена!");
                return true;
            } else throw new WrongCommandInputException();
        } catch (WrongCommandInputException exception) {
            ConsoleClient.printError("Команда " + getName() + " введена с ошибкой: " +
                    "команда не должна содержать символы после своего названия!");
            if (Asker.getFileMode()) throw new IncorrectScriptException();
        }
        return false;
    }

    public String getMessage() {
        return "info - Выводит в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)";
    }
}
