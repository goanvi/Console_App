package View.Commands;

import Controller.CollectionManager;
import Model.Exceptions.WrongCommandInputException;
import View.ConsoleClient.ConsoleClient;

public class Clear extends AbstractCommand {
    CollectionManager collectionManager;

    public Clear(CollectionManager manager) {
        super("Clear", "Очищает коллекцию");
        this.collectionManager = manager;
    }

    @Override
    public boolean execute(String argument) {
        try {
            if (argument.isEmpty()){
                collectionManager.clearCollection();
                ConsoleClient.println("Очистка коллекции успешно проведена!");
                return true;
            }
            else throw new WrongCommandInputException();

        }catch (WrongCommandInputException exception){
            ConsoleClient.printError("Команда " + getName() + " введена с ошибкой: " +
                    "команда не должна содержать символы после своего названия!");
        }
        return false;
    }

    public String getMessage() {
        return "clear - Очищает коллекцию";
    }
}
