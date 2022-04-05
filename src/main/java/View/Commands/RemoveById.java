package View.Commands;

import Controller.CollectionManager;
import Controller.IdManager;
import Model.Exceptions.IncorrectInputException;
import Model.Exceptions.IncorrectScriptException;
import Model.Exceptions.WrongCommandInputException;
import View.Asker;
import View.ConsoleClient.ConsoleClient;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class RemoveById extends AbstractCommand {
    CollectionManager collectionManager;
    Scanner scanner;

    public RemoveById(CollectionManager collectionManager, Scanner scanner) {
        super("Remove_by_id", "Удаляет элемент из коллекции по его id");
        this.collectionManager = collectionManager;
        this.scanner = scanner;
    }

    @Override
    public boolean execute(String argument) throws IncorrectScriptException {
        String input;
        try{
            if (argument.isEmpty()){
                ConsoleClient.println("Ведите id элемента!");
                if (Asker.getFileMode()){
                    Scanner scriptScanner = ConsoleClient.getScriptScanner();
                    input = scriptScanner.nextLine().trim();
                }else input = scanner.nextLine().trim();
                int id = Integer.parseInt(input);
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
