package view.command.commands;

import controller.CollectionManager;
import view.command.AbstractCommand;
import view.exceptions.IncorrectInputException;
import view.exceptions.IncorrectScriptException;
import view.command.exceptions.WrongCommandInputException;
import view.utility.Asker;
import view.console.ConsoleClient;

import java.util.NoSuchElementException;

public class RemoveById extends AbstractCommand {
    CollectionManager collectionManager;
    ConsoleClient consoleClient;

    public RemoveById(CollectionManager collectionManager, ConsoleClient consoleClient) {
        super("Remove_by_id", "Удаляет элемент из коллекции по его id");
        this.collectionManager = collectionManager;
        this.consoleClient = consoleClient;
    }

    @Override
    public boolean execute(String argument) throws IncorrectScriptException {
        String input;
        try{
            if (!argument.isEmpty()){
//                ConsoleClient.println("Ведите id элемента!");
////                if (Asker.getFileMode()){
////                    Scanner scriptScanner = ConsoleClient.getScriptScanner();
////                    input = scriptScanner.nextLine().trim();
////                }else input = scanner.nextLine().trim();
//                input = consoleClient.readLine();
                int id = Integer.parseInt(argument.trim());
                if (!collectionManager.getCollection().removeIf(studyGroup -> studyGroup.getID()==id))
                    throw new IncorrectInputException();
                ConsoleClient.println("Элемент успешно удален!");
                return true;
            }else throw new WrongCommandInputException();
        }catch (IncorrectInputException exception){
            ConsoleClient.printError("Такого id не существует!");
            if (Asker.getFileMode()) throw new IncorrectScriptException();
        }catch (WrongCommandInputException exception){
            ConsoleClient.printError("Команда " + getName() + " введена с ошибкой: " +
                    "команда не должна содержать символы после своего названия!");
            if (Asker.getFileMode()) throw new IncorrectScriptException();
        }catch (NumberFormatException exception){
            ConsoleClient.printError("Значением поля должно являться число!");
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
        return "remove_by_id  - Удаляет элемент из коллекции по его id";
    }
}
