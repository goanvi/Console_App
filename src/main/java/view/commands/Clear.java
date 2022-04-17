package view.commands;

import controller.CollectionManager;
import view.exceptions.IncorrectScriptException;
import view.commands.exceptions.WrongCommandInputException;
import view.utility.Asker;
import view.console.ConsoleClient;

import java.util.NoSuchElementException;

public class Clear extends AbstractCommand {
    private CollectionManager collectionManager;

    public Clear(CollectionManager manager) {
        super("Clear", "Очищает коллекцию");
        this.collectionManager = manager;
    }

    @Override
    public boolean execute(String argument) throws IncorrectScriptException {
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
            if (Asker.getFileMode()) throw new IncorrectScriptException();
        }catch (NoSuchElementException exception){
            ConsoleClient.printError("Значение поля не распознано!");
            if (Asker.getFileMode()) throw new IncorrectScriptException();
        } catch (IllegalStateException exception) {
            ConsoleClient.printError("Непредвиденная ошибка!");
            System.exit(0);
        }
        return false;
    }

    public String getMessage() {
        return "clear - Очищает коллекцию";
    }
}
